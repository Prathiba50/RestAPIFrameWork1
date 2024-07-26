package stepDefinitions;

import Utils.APIResources;
import Utils.SpecBuilders;
import Utils.TestDataBuild;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class AddPlaceTest  extends  SpecBuilders{


    public static RequestSpecification givenRes;
    public static Response response;
    TestDataBuild data = new TestDataBuild();
    public static String place_ID;


    @Given("Add place Payload {string} and {string} and {string}")
    public void add_place_payload_and_and(String name, String language, String address) {
        givenRes = given().spec(reqSpec()).body(data.addPlace(name,language,address));

    }

    @When("User calls {string} with {string} http request")
    public void user_calls_with_http_request(String resourse, String httpMethod) {
        try {
            //Constructor will be called with value of resource which you have passed
            APIResources resourseApi = APIResources.valueOf(resourse);
            System.out.println(resourseApi.getResourse());

            if (httpMethod.equalsIgnoreCase("POST")) {
                response = givenRes.when().post(resourseApi.getResourse());
                System.out.println("---------response---------------");
                System.out.println(response.asString());
            } else if (httpMethod.equalsIgnoreCase("GET")) {
                response = givenRes.when().get(resourseApi.getResourse());
                System.out.println("---------response---------------");
                System.out.println(response.asString());

            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

//        .then().log().all().spec(resSpec).extract().response();

    @Then("the API call got success with status {int}")
    public void the_api_call_got_success_with_status(Integer int1) {

        assertEquals(response.getStatusCode(),200);
    }


    @And("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {
        System.out.println("Response to validate status: "+response.asPrettyString());
        assertEquals(getJsonPath(response,keyValue),expectedValue);
    }

    @Given("{string} in the body is {string}")
    public void in_the_body_is(String keyValue, String expectedValue) {
        assertEquals(getJsonPath(response,keyValue),expectedValue);

    }

    @Then("verify place_Id created map to {string} using {string}")
    public void verify_place_id_created_map_to_using(String expectedName, String resource) {

        place_ID = getJsonPath(response, "place_id");
        givenRes = given().spec(reqSpec()).queryParam("place_id", place_ID);
        user_calls_with_http_request(resource,"GET");
        String NameInResponse = getJsonPath(response,"name");
        assertEquals(NameInResponse,expectedName);
    }

    @Given("Delete Place payload")
    public void delete_place_payload() {
        givenRes = given().spec(reqSpec()).body(data.deletePlace(place_ID));

    }

}
