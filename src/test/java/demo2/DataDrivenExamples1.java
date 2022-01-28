package demo2;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class DataDrivenExamples1 extends DataForTests {

    //@Test(dataProvider = "DataForPost")
    public void test_post(String firstName, String lastName, int subjectId) {
        JSONObject request = new JSONObject();
        request.put("firstName", firstName);
        request.put("lastName", lastName);
        request.put("subjectId", subjectId);

        baseURI = "http://localhost:3000/";

        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                header("Content-Type", "application/json").
                body(request.toJSONString()).
                when().
                post("/users").
                then().
                statusCode(201).
                log().all();
    }

    @Test(dataProvider = "DeleteData")
    public void test_delete(int userId) {

        baseURI = "http://localhost:3000/";

        when().
                delete("/users/"+userId).
                then().
                statusCode(200);
    }

    @Parameters({"userId"})
    @Test
    public void test_delete2(int userId) {
        System.out.println("Value for userId is: "+userId);
        baseURI = "http://localhost:3000/";

        when().
                delete("/users/"+userId).
                then().
                statusCode(200);
    }
}
