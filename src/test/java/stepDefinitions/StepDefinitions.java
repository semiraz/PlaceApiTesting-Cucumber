package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;


import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class StepDefinitions extends Utils {

    private ResponseSpecification resSpec;
    private RequestSpecification res;
    private Response response;

    private final TestDataBuild data = new TestDataBuild();
    static String place_id;


    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {

        res = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));
    }


    @When("user calls {string} with {string} http request")
    public void user_calls_with_post_http_request(String resource, String httpMethod) {

        APIResources resourceApi = APIResources.valueOf(resource);
        System.out.println(resourceApi.getResource());

        //build ResponseSpecBuilder
        resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        if (httpMethod.equalsIgnoreCase("POST")) {
            response = res.when().post(resourceApi.getResource());
        } else if (httpMethod.equalsIgnoreCase("GET")) {
            response = res.when().get(resourceApi.getResource());
        }
    }

    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {
        assertEquals(expectedValue, getJsonPath(response, keyValue));
    }

    @Then("verify place_id created maps to {string} using {string}")
    public void verifyPlace_idCreatedMapsToUsing(String expectedName, String resource) throws IOException {
        //requestSpec:
        place_id = getJsonPath(response, "place_id");
        res = given().spec(requestSpecification()).queryParam("place_id", place_id);

        user_calls_with_post_http_request(resource, "GET");
        String actualName = getJsonPath(response, "name");
        assertEquals(expectedName, actualName);
    }

    @Given("Delete Place Payload")
    public void delete_place_payload() throws IOException {
        res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
    }
}















