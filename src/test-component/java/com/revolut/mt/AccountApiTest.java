package com.revolut.mt;

import io.restassured.http.ContentType;
import org.junit.Test;

import static com.revolut.mt.PathTestConstants.ACCOUNTS;
import static io.restassured.RestAssured.given;

public class AccountApiTest extends BaseApiTest {


  @Test
  public void retrieve_all_accounts() {

    given()
            .when()
            .get(ACCOUNTS)
            .then()
            .assertThat()
            .statusCode(200);
  }

  @Test
  public void retrieve_one_account() {
    // Arrange
    final String accountNumber = "/45645647575757";

    given()
            .when()
            .get(ACCOUNTS + accountNumber)
            .then()
            .assertThat()
            .statusCode(200);
  }

  @Test
  public void create_an_account() {

    // Arrange
    final String bodyContent = "{\"initialAmount\": \"30\"}";

    given()
            .contentType(ContentType.JSON)
            .body(bodyContent)
            .when()
            .post(ACCOUNTS)
            .then()
            .assertThat()
            .statusCode(201);
  }

}
