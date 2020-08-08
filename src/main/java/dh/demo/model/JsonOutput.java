package dh.demo.model;

/**
 * Java representation of the JSON output including an array of scored applicants.
 */
public class JsonOutput {
    private ApplicantCompatibility[] scoredApplicants;

    public JsonOutput (ApplicantCompatibility[] scoredApplicants) {
        this.scoredApplicants = scoredApplicants;
    }

    public ApplicantCompatibility[] getScoredApplicants() {
        return scoredApplicants;
    }
}