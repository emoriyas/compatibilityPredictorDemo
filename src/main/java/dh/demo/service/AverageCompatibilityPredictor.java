package dh.demo.service;

import java.util.ArrayList;
import java.util.List;

import dh.demo.model.ApplicantCompatibility;
import dh.demo.model.Attributes;
import dh.demo.model.Person;

/**
 * Compatibility predictor that assumes that an applicant with attributes
 * that deviates from the team's average will be a better fit for the team than
 * an applicant that is similar to the team's average.
 */
public class AverageCompatibilityPredictor implements CompatibilityPredictor {

    @Override
    public ApplicantCompatibility[] predictCompatibility(Person[] teamArray, Person[] applicantsArray) {

        // Requires at least one team member and one applicant.
        if (teamArray.length == 0) {
            throw new IllegalArgumentException("There are no team members.");
        }
        if (applicantsArray.length == 0) {
            throw new IllegalArgumentException("There are no applicants.");
        }

        int teamTotalIntelligence = 0;
        int teamTotalStrength = 0;
        int teamTotalEndurance = 0;
        int teamTotalSpicyFoodTolerance = 0;

        double averageTeamIntelligence;
        double averageTeamStrength;
        double averageTeamEndurance;
        double averageTeamSpicyFoodTolerance;

        List<ApplicantCompatibility> compatibilityScores = new ArrayList<>();

        // Loops through team team member to calculate average attributes.
        for (Person teamMember : teamArray) {
            Attributes teamMemberAttribute = teamMember.getAttributes();
            teamTotalIntelligence = teamTotalIntelligence + teamMemberAttribute.getIntelligence();
            teamTotalStrength = teamTotalStrength + teamMemberAttribute.getStrength();
            teamTotalEndurance = teamTotalEndurance + teamMemberAttribute.getEndurance();
            teamTotalSpicyFoodTolerance = teamTotalSpicyFoodTolerance + teamMemberAttribute.getSpicyFoodTolerance();
        }

        averageTeamIntelligence = teamTotalIntelligence/teamArray.length;
        averageTeamStrength = teamTotalStrength/teamArray.length;
        averageTeamEndurance = teamTotalEndurance/teamArray.length;
        averageTeamSpicyFoodTolerance = teamTotalSpicyFoodTolerance/teamArray.length;

        // Looks through each applicants and score them by matching with the team's avg attributes.
        for (Person applicant : applicantsArray) {
            compatibilityScores.add(new ApplicantCompatibility(applicant.getName(),
                    calculateScore(applicant,averageTeamIntelligence,
                            averageTeamStrength, averageTeamEndurance, averageTeamSpicyFoodTolerance)));
        }
        return compatibilityScores.stream().toArray(ApplicantCompatibility[] :: new);
    }

    /**
     * Calculates applicant's compatibility with the team by comparing his/her attributes
     * to the average attributes of the team.
     *
     * @param applicant The applicant to score.
     * @param averageTeamIntelligence Team's average intelligence attribute.
     * @param averageTeamStrength Team's average strength attribute.
     * @param averageTeamEndurance Team's average endurance attribute.
     * @param averageTeamSpicyFoodTolerance Team's average spicy food tolerance.
     * @return Applicant's compatibility score.
     */
    private double calculateScore(Person applicant, double averageTeamIntelligence,
            double averageTeamStrength, double averageTeamEndurance, double averageTeamSpicyFoodTolerance) {

        double score = 0;
        Attributes applicantAttribute = applicant.getAttributes();

        score = score + calculateWeight(averageTeamIntelligence, applicantAttribute.getIntelligence());
        score = score + calculateWeight(averageTeamStrength, applicantAttribute.getStrength());
        score = score + calculateWeight(averageTeamEndurance, applicantAttribute.getEndurance());
        score = score + calculateWeight(averageTeamSpicyFoodTolerance, applicantAttribute.getSpicyFoodTolerance());

        return score;
    }

    /**
     * Returns a weighted score based on the applicant's attribute deviance
     * from the team's average attribute. Higher the deviance, higher the weighted
     * score.
     *
     * @param teamAvg Team's average attribute value.
     * @param applicantAttribute Applicant's attribute value.
     * @return Compatibility score.
     */
    private double calculateWeight(double teamAvg, int applicantAttribute) {
        // Rational is that a team member with drastically different attributes
        // from the team's average is able to give a fresh perspective.
        if (Math.abs(applicantAttribute - teamAvg) > 3) {
            return 0.25;
        } else if (Math.abs(applicantAttribute - teamAvg) > 1) {
            return 0.1;
        }
        return 0;
    }
}
