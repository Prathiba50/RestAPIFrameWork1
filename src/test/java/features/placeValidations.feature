Feature: Validate Place API's

  @AddPlace @Regression
  Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
    Given Add place Payload "<name>" and "<language>" and "<address>"
    When User calls "AddPlaceAPI" with "Post" http request
    Then the API call got success with status 200
    And  "status" in response body is "OK"
    And  "scope" in the body is "APP"
    And verify place_Id created map to "<name>" using "getPlaceAPI"


    Examples:
      | name   | language | address            |
      | Abownsle | English  | World cross center1 |
      | BBouse | Spanish  | Sea cross center1 |

    @DeletePlace @Regression
    Scenario: Verify if delete functionality is working
      Given Delete Place payload
      When User calls "deletePlaceAPI" with "Post" http request
      Then the API call got success with status 200
      And  "status" in response body is "OK"

