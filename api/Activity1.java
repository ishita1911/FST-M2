package activities;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Activity1 {

    String baseURI="https://petstore.swagger.io/v2/pet";
    Response response;

    @Test(priority = 1)
    public void postPet(){
        Map<String, Object> reqBody= new HashMap<>();
        reqBody.put("id", 77232);
        reqBody.put("name", "Riney");
        reqBody.put("status", "alive");

        response=
                    given().contentType(ContentType.JSON)
                        .body(reqBody)
                        .when().post(baseURI);
        System.out.println(response.getBody().asPrettyString());
        response.then().body("id", equalTo(77232));
        response.then().body("name", equalTo("Riney"));
        response.then().body("status", equalTo("alive"));
    }

    @Test(priority = 2)
    public void getPet(){
        response=
                given().contentType(ContentType.JSON)
                        .pathParam("petId", "77232")
                        .when().get(baseURI+"/{petId}");
        System.out.println(response.getBody().asPrettyString());
        response.then().body("id", equalTo(77232));
        response.then().body("name", equalTo("Riney"));
        response.then().body("status", equalTo("alive"));
    }
    @Test(priority = 3)
    public void deletePet(){
        response=
                given().contentType(ContentType.JSON)
                        .pathParam("petId","77232")
                        .when().delete(baseURI+"/{petId}");
        System.out.println(response.getBody().asPrettyString());
        response.then().body("code", equalTo(200));
        response.then().body("message", equalTo("77232"));
    }

}
