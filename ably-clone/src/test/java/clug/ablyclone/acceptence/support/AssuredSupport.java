package clug.ablyclone.acceptence.support;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class AssuredSupport {

  public static ExtractableResponse<Response> get(final String uri) {
    return RestAssured
        .given().log().ifValidationFails()
        .when().log().ifValidationFails()
        .get(uri)
        .then().log().ifError()
        .extract();
  }
}
