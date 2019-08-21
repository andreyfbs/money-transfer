package com.revolut.mt;

import static com.revolut.mt.PathTestConstants.ACCOUNTS;
import static com.revolut.mt.PathTestConstants.TRANSFERS;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import io.restassured.http.ContentType;
import org.junit.Test;

public class TransferApiTest extends BaseApiTest {

  @Test
  public void transfer_money_success() {
    // Arrange
    final String accountNumberFrom = createAccountAndGetAccountNumber("1000.00");
    final String accountNumberTo = createAccountAndGetAccountNumber("2000.00");
    final String transferValue = "100.50";
    final String bodyContent =
        "{\"accountNumberFrom\": \"" + accountNumberFrom + "\"," +
            "\"accountNumberTo\": \"" + accountNumberTo + "\"," +
            "\"transferValue\": \"" + transferValue + "\"}";

    given()
            .contentType(ContentType.JSON)
            .body(bodyContent)
            .when()
            .post(TRANSFERS)
            .then()
            .assertThat()
            .statusCode(201);
  }

  @Test
  public void transfer_money_invalid_accountFrom() {
    // Arrange
    final String accountNumberFrom = "d3c89c1a-adf6-4ccc";
    final String accountNumberTo = createAccountAndGetAccountNumber("2000.00");
    final String transferValue = "100.50";
    final String bodyContent =
        "{\"accountNumberFrom\": \"" + accountNumberFrom + "\"," +
            "\"accountNumberTo\": \"" + accountNumberTo + "\"," +
            "\"transferValue\": \"" + transferValue + "\"}";

    given()
        .contentType(ContentType.JSON)
        .body(bodyContent)
        .when()
        .post(TRANSFERS)
        .then()
        .assertThat()
        .statusCode(404);
  }

  @Test
  public void transfer_money_invalid_accountTo() {
    // Arrange
    final String accountNumberFrom = createAccountAndGetAccountNumber("2000.00");
    final String accountNumberTo = "d3c89c1a-adf6-4ccc";
    final String transferValue = "100.50";
    final String bodyContent =
        "{\"accountNumberFrom\": \"" + accountNumberFrom + "\"," +
            "\"accountNumberTo\": \"" + accountNumberTo + "\"," +
            "\"transferValue\": \"" + transferValue + "\"}";

    given()
        .contentType(ContentType.JSON)
        .body(bodyContent)
        .when()
        .post(TRANSFERS)
        .then()
        .assertThat()
        .statusCode(404);
  }

  @Test
  public void transfer_money_invalid_transferValue() {
    // Arrange
    final String accountNumberFrom = createAccountAndGetAccountNumber("2000.00");
    final String accountNumberTo = "d3c89c1a-adf6-4ccc";
    final String transferValue = "aaaaaaa";
    final String bodyContent =
        "{\"accountNumberFrom\": \"" + accountNumberFrom + "\"," +
            "\"accountNumberTo\": \"" + accountNumberTo + "\"," +
            "\"transferValue\": \"" + transferValue + "\"}";

    given()
        .contentType(ContentType.JSON)
        .body(bodyContent)
        .when()
        .post(TRANSFERS)
        .then()
        .assertThat()
        .statusCode(400);
  }

  @Test
  public void transfer_money_invalid_negative_transferValue() {
    // Arrange
    final String accountNumberFrom = createAccountAndGetAccountNumber("2000.00");
    final String accountNumberTo = "d3c89c1a-adf6-4ccc";
    final String transferValue = "-98.98";
    final String bodyContent =
        "{\"accountNumberFrom\": \"" + accountNumberFrom + "\"," +
            "\"accountNumberTo\": \"" + accountNumberTo + "\"," +
            "\"transferValue\": \"" + transferValue + "\"}";

    given()
        .contentType(ContentType.JSON)
        .body(bodyContent)
        .when()
        .post(TRANSFERS)
        .then()
        .assertThat()
        .statusCode(400);
  }

  @Test
  public void transfer_money_more_than_two_decimals() {
    // Arrange
    final String accountNumberFrom = createAccountAndGetAccountNumber("2000.00");
    final String accountNumberTo = "d3c89c1a-adf6-4ccc";
    final String transferValue = "98.988";
    final String bodyContent =
        "{\"accountNumberFrom\": \"" + accountNumberFrom + "\"," +
            "\"accountNumberTo\": \"" + accountNumberTo + "\"," +
            "\"transferValue\": \"" + transferValue + "\"}";

    given()
        .contentType(ContentType.JSON)
        .body(bodyContent)
        .when()
        .post(TRANSFERS)
        .then()
        .assertThat()
        .statusCode(400);
  }

  @Test
  public void transfer_money_insufficient_funds() {
    // Arrange
    final String accountNumberFrom = createAccountAndGetAccountNumber("1000.00");
    final String accountNumberTo = createAccountAndGetAccountNumber("2000.00");
    final String transferValue = "1000.90";
    final String bodyContent =
        "{\"accountNumberFrom\": \"" + accountNumberFrom + "\"," +
            "\"accountNumberTo\": \"" + accountNumberTo + "\"," +
            "\"transferValue\": \"" + transferValue + "\"}";

    given()
        .contentType(ContentType.JSON)
        .body(bodyContent)
        .when()
        .post(TRANSFERS)
        .then()
        .assertThat()
        .statusCode(400);
  }

  @Test
  public void transfer_money_check_post_balances() {
    // Arrange
    final String accountNumberFrom = createAccountAndGetAccountNumber("1000.09");
    final String accountNumberTo = createAccountAndGetAccountNumber("2000.08");
    final String transferValue = "100.50";
    final String bodyContent =
        "{\"accountNumberFrom\": \"" + accountNumberFrom + "\"," +
            "\"accountNumberTo\": \"" + accountNumberTo + "\"," +
            "\"transferValue\": \"" + transferValue + "\"}";
    final String accountNumberFromPostBalance = "899.59";
    final String accountNumberToPostBalance = "2100.58";

    // Make the transfer
    given()
        .contentType(ContentType.JSON)
        .body(bodyContent)
        .when()
        .post(TRANSFERS)
        .then()
        .assertThat()
        .statusCode(201);

    // Check balance of AccountFrom
    given()
        .when()
        .get(ACCOUNTS + "/" + accountNumberFrom)
        .then()
        .assertThat()
        .body("balanceNumber", is(equalTo(accountNumberFromPostBalance)))
        .statusCode(200);

    // Check balance of AccountTo
    given()
        .when()
        .get(ACCOUNTS + "/" + accountNumberTo)
        .then()
        .assertThat()
        .body("balanceNumber", is(equalTo(accountNumberToPostBalance)))
        .statusCode(200);
  }

  @Test
  public void retrieve_all_transfer_success() {

    // Arrange
    final int numberOfTransfersExpected = 3;
    // Execute 3 transfers
    transfer_money_success();
    transfer_money_success();
    transfer_money_success();

    given()
        .when()
        .get(TRANSFERS)
        .then()
        .assertThat()
        .body("size()", is(numberOfTransfersExpected))
        .statusCode(200);
  }

  @Test
  public void retrieve_all_transfer_empty() {

    // Arrange
    final int numberOfTransfersExpected = 0;

    given()
        .when()
        .get(TRANSFERS)
        .then()
        .assertThat()
        .body("size()", is(numberOfTransfersExpected))
        .statusCode(200);
  }

  private String createAccountAndGetAccountNumber(String initialAmount) {
    final String bodyContent = "{\"initialAmount\": \"" + initialAmount + "\"}";
    return given()
        .contentType(ContentType.JSON)
        .body(bodyContent)
        .when()
        .post(ACCOUNTS)
        .then()
        .extract().path("accountNumber");
  }

}
