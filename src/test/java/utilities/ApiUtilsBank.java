package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiUtilsBank {

    public static Response getCall(String endpoint) {
        Response response = given().baseUri(ConfigReader.getProperty("BankAppBaseUri"))
                .and().accept(ContentType.JSON)
                .and().auth().oauth2(generateToken())
                .when().get(endpoint);
        return response;
    }

    private static String generateToken() {
        Response response=given().baseUri(ConfigReader.getProperty("BankAppBaseUri"))
                .and().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .when().body("{\n" +
                        "    \"password\": \"MindtekStudent\",\n" +
                        "    \"username\": \"Mindtek\"\n" +
                        "}")
                .and().post("/authenticate");
        response.then().log().all();
        return response.jsonPath().getString("jwt");
    }

    public static Response postCall(String endpoint, Object body) {
        Response response = given().baseUri(ConfigReader.getProperty("BankAppBaseUri"))
                .and().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().auth().oauth2(generateToken())
                .when().body(body)
                .and().post(endpoint);
        response.then().log().all();
        return response;
    }

    public static Response putCall(String endpoint, Object body) {
        Response response = given().baseUri(ConfigReader.getProperty("BankAppBaseUri"))
                .and().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().auth().oauth2(generateToken())
                .when().body(body)
                .and().put(endpoint);
        response.then().log().all();
        return response;
    }
    public static Response deleteCall(String endpoint) {
        Response response=given().baseUri(ConfigReader.getProperty("BankAppBaseUri"))
                .and().auth().oauth2(generateToken())
                .when().delete(endpoint);
        response.then().log().all();
        return response;


    }


}
