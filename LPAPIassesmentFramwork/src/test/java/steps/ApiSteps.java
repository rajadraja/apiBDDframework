package steps;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import io.restassured.RestAssured;
import org.junit.Assert;

public class ApiSteps {

	private Response response;

	@Step("Create a endpoint")
	public String createRequest(String searchText, String apiKey, String limit) {

		return  "search?api_key=" + apiKey + "&limit=" + limit + "&q=" + searchText;
		
//		return endpoint;
	}

	@Step("Get response for given endpoint")
	public void getResponse(String endPoint) {
		response = RestAssured.when().get(endPoint);
	}

	// method can be utilized - example for maintenance
	@Step("Send post request for given endpoint and get response")
	public void postRequest() {
		// body to post request
	}

	@Step("Send delete request for given endpoint and get response")
	public void deleteResponse(String endPoint) {
		response = RestAssured.when().delete(endPoint);
	}

	@Step("Validate the status code")
	public void validateStatusCode(int code) {

		response.then().statusCode(code);
	}

	@Step("Verify the response values")
	public void verifyResponseBody(String key, String value) {

		String res = response.body().asString();
		Assert.assertTrue(res.contains(value));
		Assert.assertTrue(res.contains(key));
	}

}
