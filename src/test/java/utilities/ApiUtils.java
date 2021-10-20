package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdk.jfr.Configuration;

import static io.restassured.RestAssured.given;

public class ApiUtils {
    /*
    Static methods
    .getCall(String endpoint,);->  request-parameters ; return Response
    .postCall(String endpoint, Object body); return response
    .putCall(String endpoint, Object body); return response
    .deleteCall(String endpoint,);return response no body
     */
    public static Response getCall(String endpoint) {
        Response response = given().baseUri(ConfigReader.getProperty("HrAppBaseUri"))
                .and().accept(ContentType.JSON)
                .when().get(endpoint);
        return response;
    }

    public static Response postCall(String endpoint, Object body) {
        Response response = given().baseUri(ConfigReader.getProperty("HrAppBaseUri"))
                .and().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .when().body(body)
                .and().post(endpoint);
        return response;
    }

    public static Response putCall(String endpoint, Object body) {
        Response response = given().baseUri(ConfigReader.getProperty("HrAppBaseUri"))
                .and().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .when().body(body)
                .and().put(endpoint);
        return response;
    }
    public static Response deleteCall(String endpoint) {
        Response response=given().baseUri(ConfigReader.getProperty("HrAppBaseUri"))
                .when().delete(endpoint);
        return response;

    }

}