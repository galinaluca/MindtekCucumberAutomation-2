package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class APITest {
    public static void main(String[] args) {
//Authentication API call
        Response response=given().baseUri("https://mindtek-restapi.herokuapp.com")
                .and().accept("application/json")
                .and().contentType(ContentType.JSON)
                .when().body("{\n" +
                        "    \"password\": \"MindtekStudent\",\n" +
                        "    \"username\": \"Mindtek\"\n" +
                        "}")
                .and().post("/authenticate");
        String token=response.body().jsonPath().getString("jwt");
        System.out.println(token);

        /*
        Create account; POST
        Request; 1.Endpoint, 2.Body, 3.Headers
         */
         Map<String,Object> header=new HashMap<>();
         header.put("Authorization","Bearer " +token);

        Response posResponse=given().baseUri("https://mindtek-restapi.herokuapp.com")
                .and().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().auth().oauth2(token)
            //    .and().headers(header)
                .when().body("{\n" +
                        "    \"accountType\": \"credit\",\n" +
                        "    \"balance\":50000\n" +
                        "}")
                .and().post("/api/account");
        posResponse.then().log().all();
        posResponse.then().log().status();


    }
}
