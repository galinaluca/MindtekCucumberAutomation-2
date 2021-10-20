package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiTests {
    public static void main(String[] args) {

        /*
        Do GET call.
        get employee with employees id 100
        /api/employees/100
        URL + Headers
        URL=Base URL +endpoints
        Given BaseURL
        And Headers (Accept-->application/Json)
        When Endpoints

         */

        Response response= given().baseUri("http://devenv-apihr-arslan.herokuapp.com")
                .and().accept(ContentType.JSON)
                .when().get("/api/employees/100");

        response.then().log().all();
        System.out.println(response.statusCode());
        System.out.println(response.body().asString());
        System.out.println(response.body().as(HashMap.class));

       Map<String,Object> responseData=response.body().as(HashMap.class);
        System.out.println(responseData.get("employeeId"));

        /*
        Post employee
        Request: BaseURL+Endpoint+Headers+Json body
        Given BaseUrl
        And Accept->application json
        And ContentType->application json
        When Json body
        And send post call
         */
        Response postResponse=given().baseUri("http://devenv-apihr-arslan.herokuapp.com")
                .and().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .when().body("{\n" +
                        "\"firstName\" : \"John\",\n" +
                        " \"lastName\" : \"Doe\"\n" +
                        " }")
                .and().post("/api/employees");
        System.out.println(postResponse.statusCode());

        /*
        REQUEST
        Delete call:
        Given BaseUrl
        When delete call
        RESPONSE  Status call
         */
        Response deleteResponse=given().baseUri("http://devenv-apihr-arslan.herokuapp.com")
                .when().delete("/api/employees/206");
        System.out.println(deleteResponse.statusCode());


        Response response1= ApiUtils.getCall("api/departments/10");
        System.out.println(response1.body().asString());

    }


}
