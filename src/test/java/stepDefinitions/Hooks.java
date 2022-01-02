package stepDefinitions;


import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        //write a code that will give you place id
        //execute this code only when place id is null
        StepDefinitions step = new StepDefinitions();
        if (StepDefinitions.place_id == null) {
            step.add_place_payload_with("Bumbu", "German", "Boji Boji 58");
            step.user_calls_with_post_http_request("addPlaceApi", "POST");
            step.verifyPlace_idCreatedMapsToUsing("Bumbu", "getPlaceApi");
        }

    }
}
