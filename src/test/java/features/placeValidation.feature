Feature: Validating Place API's

  @AddPlace @Regression
  Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "addPlaceApi" with "POST" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to "<name>" using "getPlaceApi"

    Examples:
      | name        | language | address               |
      | Minie House | English  | World Cross Center 58 |
      | Lovie House | Spanish  | Sea Cross Center 88   |


    @DeletePlace
    Scenario: Verify if Delete PLace functionality is working
      Given Delete Place Payload
      When user calls "deletePlaceApi" with "POST" http request
      Then the API call is success with status code 200
      And "status" in response body is "OK"
