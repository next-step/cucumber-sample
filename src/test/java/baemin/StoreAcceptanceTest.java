package baemin;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class StoreAcceptanceTest {
    @Test
    void applyStore() {
        // When 사장님이 입점을 신청한다
        Map<String, String> params = new HashMap<>();
        params.put("name", "그집");

        RestAssured
                .given().log().all()
                .body(params)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/store-applies")
                .then().log().all();

        // Then 어드민이 입점 신청 목록에서 확인할 수 있다
        var response = RestAssured
                .given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/store-applies")
                .then().log().all()
                .extract();
        assertThat(response.jsonPath().getList(".name", String.class)).isEqualTo("그집");
    }
}
