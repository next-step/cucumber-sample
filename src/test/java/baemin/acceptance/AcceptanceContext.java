package baemin.acceptance;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AcceptanceContext {

    public RequestSpecification request;
    public Response response;
    public Map<String, Object> storage;

    public void invokeHttpGet(String path, Object... pathParams) {
        request = RestAssured.given().log().all();
        response = request.when().get(path, pathParams);
        response.then().log().all();
    }

    public void invokeHttpPost(String path, Object data) {
        request = RestAssured
                .given().log().all()
                .body(data).contentType(ContentType.JSON);
        response = request.post(path);
        response.then().log().all();
    }
}
