package stepDefinitions;

import io.cucumber.java.Before;

public class Hooks {

    AddPlaceTest test;
    //Execute this code only when place id is null

    @Before("@DeletePlace")
    public void beforeScenario(){

        test  = new AddPlaceTest();
        if(AddPlaceTest.place_ID == null) {
            test.add_place_payload_and_and("Desoza", "Frech", "Asia cauvery Street");
            test.user_calls_with_http_request("AddPlaceAPI", "Post");
            test.verify_place_id_created_map_to_using("Desoza", "getPlaceAPI");
        }

    }
}
