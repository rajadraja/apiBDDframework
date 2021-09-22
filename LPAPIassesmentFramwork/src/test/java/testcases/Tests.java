package testcases;

import org.junit.runner.RunWith;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import steps.ApiSteps;

@RunWith(SerenityRunner.class)
public class Tests {
	
	@Steps
	ApiSteps apiMethods;
	
	public String endPoint;

	@Before
	public static void init() {
		
		RestAssured.baseURI="https://api.giphy.com";
		RestAssured.basePath="v1/gifs/";
		
	}
	
	@After
	public static void tear() {
		RestAssured.reset();
	}
	
	@Given("^user search (.*) for gif using (.*) (.*)$")
	public void user_search_for_gif_using(String searchtext, String apikey,  String limit) {
		endPoint = apiMethods.createRequest(searchtext, apikey.trim(), limit);
	}
	
	@When("^user send a get request$")
	public void user_send_a_get_request() {
		apiMethods.getResponse(endPoint);
	}
	
	@Then("^user should get API status code as (.*)$")
	public void user_should_get_API_status_code_as(int status) {
		apiMethods.validateStatusCode(status);
	}
	
	@And("^user get response (.*) as (.*)$")
	public void user_get_response(String key, String text) {
		apiMethods.verifyResponseBody(key, text);
	}

}
