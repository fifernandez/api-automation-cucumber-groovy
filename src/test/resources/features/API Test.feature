Feature: Rest API functionality Scenarios
  As a developer
  I want to be sure
  That the Rest API is working as expected


  @TestCaseID:01
  @smoke @regression
  @1 @2 @prod
  Scenario Outline: Verify status code returned is expected
    Given I do a get to the "<Endpoint>" endpoint
    Then the returned status code is: "<ExpectedCode>"

    Examples:
      | Endpoint | ExpectedCode |
      | users    | 200          |
      | todos    | 200          |


  @TestCaseID:02
  @smoke @regression
  @2 @3 @prod
  Scenario Outline: Verify amount of returned items is expected
    Given I do a get to the "<Endpoint>" endpoint
    Then the returned status code is: "200"
    And the response contains "<amount>" items

    Examples:
      | Endpoint | amount |
      | users    | 10     |


  @TestCaseID:03
  @regression
  @4 @prod
  Scenario: Just a failing test
    Given I do a get to the "users" endpoint
    Then the returned status code is: "404"