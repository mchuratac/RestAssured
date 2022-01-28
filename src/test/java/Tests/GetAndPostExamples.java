package Tests;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class GetAndPostExamples {
//class 6 : get and post
    //@Test
    public void testGet() {
        baseURI = "https://reqres.in/api";
        given().
                get("/users?page=2").
        then().statusCode(200).
                body("data[4].first_name", equalTo("George")).
                body("data.first_name", hasItems("George", "Rachel"));

    }


    @Test
    public void testPost() {
        Map<String, Object> map = new HashMap<String, Object>();
/*     map.put("name","Raghav");
     map.put("job","Teacher");
     System.out.println(map);*/
        JSONObject request = new JSONObject();
        request.put("name", "Magaly");
        request.put("job", "Teacher");
        System.out.println(request);

        baseURI = "https://reqres.in/api";
        given().
                header("ContentType","application/Json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
        when().
                post("/users").
        then()
                .statusCode(201)
                .log().all();


    }

}
