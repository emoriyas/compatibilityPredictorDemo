package dh.demo.service;

import dh.demo.model.ApplicantCompatibility;
import dh.demo.model.Person;

/**
 * Predicts compatibility between the team members and applicants.
 */
public interface CompatibilityPredictor {

    /**
     * Scores each applicant's predicted compatibility with the team
     * based on the applicant's and the team's attributes.
     *
     * @param team Array of team members.
     * @param applicants Array of applicants.
     * @return Array of scored applicants.
     * @throws IllegalArgumentException if an empty array is provided.
     */
    public ApplicantCompatibility[] predictCompatibility(Person[] team, Person[] applicants);
}