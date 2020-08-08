package dh.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import dh.demo.model.JsonInput;
import dh.demo.model.JsonOutput;
import dh.demo.service.CompatibilityPredictor;

/**
 * Controller
 */
@Controller
public class compatibilityPredictorController {

    @Autowired
    private CompatibilityPredictor predictor;

    /**
     * Takes a POST request with a JSON body containing an array of team
     * members and an array of applicants and returns an array of applicant's
     * predicted compatibility scores.
     *
     * @param json
     * @return Array of scored applicants.
     */
    @PostMapping("/predict")
    @ResponseBody
    public JsonOutput predictCompatibility (@RequestBody JsonInput json) {
        if (json.getTeam().length == 0) {
            throw new IllegalArgumentException("Team array must not be empty.");
        }
        if (json.getApplicants().length == 0) {
            throw new IllegalArgumentException("Applicants array must not be empty.");
        }

        return new JsonOutput(predictor.predictCompatibility(json.getTeam(), json.getApplicants()));
    }
}
