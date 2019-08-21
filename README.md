# Money Transfer

## Some Technologies

* Java 8
* Jetty (Embedded)
* Jersey
* Rest-Assured
* Maven

## Build and create the Jar of the Application

mvn clean package

## Run the Application

There is a Jetty Embedded serving the Rest API's of the application.

2 ways:

1. Jar in command line

java -jar target/money-transfer-1.0-SNAPSHOT.jar

2. IDE

Run the class MoneyTransferApplicationRestServer

## Access the Application

The default port is 8010 (check class ApplicationProperties.PORT_SERVER to change):

URL: http://localhost:8010

## Assumptions

To keep the application the simplest way possible.

- The transfers are in the same currency.
- They are online (it means that there is not any mechanism to process the Transfers later, like asynchronous process or jobs routines.

## Endpoint's

| Method | Path                  | Request                                                                                       | Response                                                           | Description
|--------|-----------------------|-----------------------------------------------------------------------------------------------|--------------------------------------------------------------------|--------------------------------------
| POST   | /accounts             | {"initialAmount": "30.98"}                                                                    | {"accountNumber": "d3c89c1a-adf6-4ccc", "balanceNumber": "30.98" } | Create an account
| GET    | /accounts/{accountId} |                                                                                               | {"accountNumber": "d3c89c1a-adf6-4ccc", "balanceNumber": "30.98" } | Retrieve information specifc account
| GET    | /accounts             |                                                                                               | [{"accountNumber": ..., {"accountNumber":...]                      | Get information all accounts
| DELETE | /accounts             |                                                                                               |                                                                    | Delete all accounts (For tests)
| GET    | /transfers            |                                                                                               | [{"code": "d3c89c1a-adf6-4ccc", ...}]                              | Get information all transfers
| DELETE | /transfers            |                                                                                               |                                                                    | Delete all transfers (For tests)
| POST   | /transfers            | {"accountNumberFrom": "100.00", "accountNumberFrom": "100.00", "accountNumberFrom": "100.00"} | {"code": "d3c89c1a-adf6-4ccc"}                                     | Make a transfer between 2 accounts

## Tests

To run them:

mvn clean test

In addition to the common Unit Tests, there is one specific to check the Concurrency (TransferServiceConcurrencyTest.java)

### Component Tests

There are 2 classes in the package test-component to do Black Box Test against the Api's. It uses the Rest-Assured framework.

The port 8090 must be available (It's possible to change in the class TestConstants.java).

This is a interesting article about Microservices Testing:

https://martinfowler.com/articles/microservice-testing/