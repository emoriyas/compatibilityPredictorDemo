package dh.demo.model;

/**
 * Applicant's compatibility score with the existing team.
 */
public class ApplicantCompatibility {
    private String name;
    private double score;

    public ApplicantCompatibility (String name, double score) {
        if (score > 1) {
            score = 1;
        }

        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }
}
