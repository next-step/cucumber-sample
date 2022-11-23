package baemin.acceptance;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class StoreStepDefinitions extends AcceptanceStep {

    @When("사장님이 입점을 신청한다")
    public void 사장님이입점을신청한다() {
        Map<String, String> params = new HashMap<>();
        params.put("name", "그집");
        var response = RestAssured
                .given().log().all()
                .body(params)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/store-applies")
                .then().log().all()
                .extract();

        String name =  response.jsonPath().getString(".name");
        context.storage.put("name", name);
    }

    @Then("어드민이 입점 신청 목록에서 확인할 수 있다")
    public void 어드민이입점신청목록에서확인할수있다() {
        var response = RestAssured
                .given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/store-applies")
                .then().log().all()
                .extract();
        assertThat(response.jsonPath().getList(".name", String.class)).isEqualTo("그집");
    }
}
