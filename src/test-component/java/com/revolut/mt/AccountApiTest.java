package com.revolut.mt;

import static com.revolut.mt.PathTestConstants.ACCOUNTS;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import org.junit.Test;

public class AccountApiTest extends BaseApiTest {

  @Test
  public void retrieve_all_accounts_success_empty() {
    // Arrange
    final int numberOfTransfersExpected = 0;

    given()
            .when()
            .get(ACCOUNTS)
            .then()
            .assertThat()
            .body("size()", is(numberOfTransfersExpected))
            .statusCode(200);
  }

  @Test
  public void retrieve_all_transfer_success_with_accounts() {
    // Arrange
    final int numberOfTransfersExpected = 4;
    // Execute 4 transfers
    create_an_account_success();
    create_an_account_success();
    create_an_account_success();
    create_an_account_success();

    given()
        .when()
        .get(ACCOUNTS)
        .then()
        .assertThat()
        .body("size()", is(numberOfTransfersExpected))
        .statusCode(200);
  }

  @Test
  public void retrieve_one_account_success() {
    // Arrange
    // Create an account
    final String accountNumber = createAccountAndGetAccountNumber();

    given()
            .when()
            .get(ACCOUNTS + "/" + accountNumber)
            .then()
            .assertThat()
            .body("accountNumber", is(equalTo(accountNumber)))
            .statusCode(200);
  }

  @Test
  public void retrieve_one_account_notFound() {
    // Arrange
    final String accountNumberNotRegistered = "d3c89c1a-adf6-4ccc";

    given()
        .when()
        .get(ACCOUNTS + "/" + accountNumberNotRegistered)
        .then()
        .assertThat()
        .statusCode(404);
  }

  @Test
  public void create_an_account_success() {
    // Arrange
    final String bodyContent = "{\"initialAmount\": \"30.98\"}";

    given()
            .contentType(ContentType.JSON)
            .body(bodyContent)
            .when()
            .post(ACCOUNTS)
            .then()
            .assertThat()
            .body("accountNumber", not(isEmptyString()))
            .statusCode(201);
  }

  @Test
  public void create_an_account_invalid_initialAmount() {
    // Arrange
    final String bodyContent = "{\"initialAmount\": \"xxx\"}";

    given()
        .contentType(ContentType.JSON)
        .body(bodyContent)
        .when()
        .post(ACCOUNTS)
        .then()
        .assertThat()
        .body("accountNumber", not(isEmptyString()))
        .statusCode(400);
  }

  @Test
  public void create_an_account_invalid_negativeAmount() {
    // Arrange
    final String bodyContent = "{\"initialAmount\": \"-30.98\"}";

    given()
        .contentType(ContentType.JSON)
        .body(bodyContent)
        .when()
        .post(ACCOUNTS)
        .then()
        .assertThat()
        .body("accountNumber", not(isEmptyString()))
        .statusCode(400);
  }

  @Test
  public void create_an_account_without_initialAmount() {
    given()
        .contentType(ContentType.JSON)
        .when()
        .post(ACCOUNTS)
        .then()
        .assertThat()
        .body("accountNumber", not(isEmptyString()))
        .statusCode(400);
  }

  private String createAccountAndGetAccountNumber() {
    final String bodyContent = "{\"initialAmount\": \"100\"}";
    return given()
        .contentType(ContentType.JSON)
        .body(bodyContent)
        .when()
        .post(ACCOUNTS)
        .then()
        .extract().path("accountNumber");
  }

}
