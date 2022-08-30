package activities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Activity2  {
    String baseURI="https://petstore.swagger.io/v2/user";
    Response response;

    @Test(priority = 1)
    public void addUser()throws IOException {
        File file= new File("src/test/resources/User.json");
        FileInputStream inputJSON= new FileInputStream(file);
        byte [] bytes=new byte[(int) file.length()];
        inputJSON.read(bytes);
        String reqBody= new String(bytes, "UTF-8");
        System.out.println(reqBody);


        response=
                given().contentType(ContentType.JSON)
                        .body(reqBody)
                        .when().post(baseURI);



}

    @Test(priority = 2)
    public void getUser() throws FileNotFoundException {
        File output= new File("src/test/resources/userGETResponse.json");

        response=
                given().contentType(ContentType.JSON)
                        .pathParam("username","Alaric")
                        .when().get(baseURI+"/{username}");
        String resBody= response.getBody().asPrettyString();
        try {

            output.createNewFile();
            FileWriter writer= new FileWriter(output);
            writer.write(resBody);
            writer.close();
        }
        catch(IOException exp){
            exp.printStackTrace();

        }
        response.then().body("username",equalTo("Alaric"));
        response.then().body("id",equalTo(9921));
        response.then().body("firstName",equalTo("Justin"));
        response.then().body("lastName",equalTo("Case"));
        response.then().body("email",equalTo("justincase@mail.com"));
        response.then().body("password",equalTo("password123"));
        response.then().body("phone",equalTo("9812763450"));

    }

    @Test(priority = 3)
    public void deleteUser(){
        response=
                given().contentType(ContentType.JSON)
                        .pathParam("username","Alaric")
                        .when().delete(baseURI+"/{username}");

        response.then().body("code", equalTo(200));
        response.then().body("message",equalTo("Alaric"));
    }

}
