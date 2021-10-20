package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RestAssuredTest {
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
        Response response= given().baseUri("http://devenv-apihr-arslan.herokuapp.com/api/employees")
                .and().accept("application/json")
                .when().get();
        /*
        Response:
        Json
        Status code
         */
        System.out.println(response.statusCode());
        System.out.println(response.body().asString());
        /*
        Post Employee
        POST
        1.URL
        2.Headers
        #.Body(JSon)
        Response
        Status code
        Json data

         */

        Response PostResponse=given().baseUri("http://devenv-apihr-arslan.herokuapp.com")
                .and().accept("application/json")
                .and().contentType(ContentType.JSON)
                .when().body("{\n" +
                        " \"firstName\" : \"Galina\",\n" +
                        " \"lastName\" : \"Luca\"\n" +
                        " }")
                .and().post("/api/employees");
        System.out.println(PostResponse.statusCode());
        System.out.println(PostResponse.body().asString());
        String employeeId=PostResponse.jsonPath().getString("employeeId");

        /*
        Update first name employee
        Request:
        PUT
        1.URL
        2.Headers
        3.Json body
        Response
        1.Body
        2.Status code
         */
        Response putResponse=given().baseUri("http://devenv-apihr-arslan.herokuapp.com")
                .and().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .when().body("{\n" +
                        " \"firstName\" : \"Marlin\",\n" +
                        " \"lastName\" : \"Monroe\"\n" +
                        " }\n")
                .and().put("/api/employees/" + employeeId);
        System.out.println(putResponse.statusCode());
        System.out.println(putResponse.body().asString());

        /*
        Delete call
        Request
        1.URL
        2.Headers
        Response
        1.Status code
         */

        Response DeleteResponse=given().baseUri("http://devenv-apihr-arslan.herokuapp.com")
                .when().delete("/api/employees/" + employeeId);
        System.out.println(DeleteResponse.statusCode());

    }
}
