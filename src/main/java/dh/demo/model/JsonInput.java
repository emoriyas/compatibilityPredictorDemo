package dh.demo.model;

/**
 * Java object representation of the expected JSON input including an array of team members
 * and an array of applicants.
 */
public class JsonInput {
    private Person[] team;
    private Person[] applicants;

    public Person[] getTeam () {
        return team;
    }

    public Person[] getApplicants () {
        return applicants;
    }
}
