Feature: Validating Place API's
@Addplace @Regression
Scenario Outline:Verify if Place is being successfully added using AddPlaceAPI
      Given Add Place Payload with "<name>" "<language>" "<address>"
      When user calls "addPlaceAPI" with "post" http request
      Then the API Call is success with status code 200
      And "status" in response body is "OK"
      And "scope" in response body is "APP"
      And checkplaceid to "<name>" by using "getPlaceAPI"
     
      
      
Examples:
		|name  | language| address|
		#|Lake  | Tamil   | Gobichettipalayam Arch|
		|Masuthi|French  | Paris|
		
@Deleteplace @Regression
Scenario: verify deleteplaceAPI is working
		Given deletePlaceAPI
		When user calls "deletePlaceAPI" with "delete" http request
		Then the API Call is success with status code 200
		And "status" in response body is "OK"
		
		
		