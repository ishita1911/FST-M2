package liveProject;


import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GitHubProject {

    RequestSpecification requestSpec;
    String baseURI="https://api.github.com";
    int sshKeyId;

    String sshKey;





    @BeforeClass
    public void setUp(){
        requestSpec= new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "token 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")
                .setBaseUri(baseURI)
                .build();

        sshKey="ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQCwNGLq2KpwpyHgyGJOLLeBMxQrcrMr2IApgHIRt8skI3FjLQpC2SjG/4rJjHv2qZOAQwSOeNa6ZwRkdHrkMWXGFE1R5ZIitAcRx1LdYB9tpA+CpLQzR1VF/3d9vmbA7zLtihFcgLMnx+4NyC6bnyPAw3zqLTzHXaXPd5FsQEc/svMof0JXE0p6uBn5QcWNw9s1PVsluIHumQigDKC3M3ekPYOZloeRhsfuEFpLO9wKedSx3bL/CmeN+Ofv1QZ64EIJn4t/D7fyCoUw6xwt2odDvU5VhZyvQlVSzIYhg6Ry50rn0aj/+loARvfC4D9llj343S60kmd9xLfY2ibL7Dv/";



    }
    @Test(priority =1)
    public void postSSHKey(){
        String reqBody= "{\"title\": \"TestKey\", \"key\": \""+ sshKey +"\"}";
        Response response=
                given().spec(requestSpec)
                        .body(reqBody)
                        .when()
                        .post("/user/keys");

        String resBody= response.getBody().asPrettyString();
        System.out.println(resBody);
        sshKeyId= response.then().extract().path("id");

        response.then().statusCode(201);
    }

    @Test(priority = 2)
    public void getSSHKey(){
        Response response=
                given().spec(requestSpec)
                        .pathParam("keyId", sshKeyId)
                        .when()
                        .get("/user/keys/{keyId}");
        String resBody= response.getBody().asPrettyString();
        System.out.println(resBody);

        response.then().statusCode(200);
    }

    @Test(priority = 3)
    public void deleteSSHKey(){
        Response response=
                given().spec(requestSpec)
                        .pathParam("keyId", sshKeyId)
                        .when()
                        .delete("/user/keys/{keyId}");
        String resBody= response.getBody().asPrettyString();
        System.out.println(resBody);
        response.then().statusCode(204);

    }

}
