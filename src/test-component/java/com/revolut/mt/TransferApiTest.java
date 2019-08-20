package com.revolut.mt;

import io.restassured.http.ContentType;
import org.junit.Test;

import static com.revolut.mt.PathTestConstants.ACCOUNTS;
import static com.revolut.mt.PathTestConstants.TRANSFERS;
import static io.restassured.RestAssured.given;

public class TransferApiTest extends BaseApiTest {


  @Test
  public void transfer_money() {

    // Arrange
    final String bodyContent =
            "{\"accountNumberFrom\": \"30\"," +
            "\"accountNumberTo\": \"30\"," +
            "\"transferValue\": \"30\"}";

    given()
            .contentType(ContentType.JSON)
            .body(bodyContent)
            .when()
            .post(TRANSFERS)
            .then()
            .assertThat()
            .statusCode(201);
  }

}
