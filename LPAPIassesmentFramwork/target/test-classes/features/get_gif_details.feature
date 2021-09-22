@Scenarios
Feature: Get gif details for search text
  User want to use get api to search gif for given text

  @possitive
  Scenario Outline: User finds gifs using search text with valid apikey
  
		Given user search <searchText> for gif using <validApiKey> <limit>
    When user send a get request
    Then user should get API status code as <status> 
    And  user get response <key> as <searchText>

    Examples: 
      | validApiKey 										 | searchText | limit |status | key   |
      | h03z6Xg5GGacOcPbNWLD3HJtkBJkMIgW | love 			| 1     | 200   | title |
      

  @negative
  Scenario Outline: User finds gifs using search text with invalid apikey
    
    Given user search <searchText> for gif using <invalidApiKey> <limit>
    When user send a get request
    Then user should get API status code as <status> 
    And  user get response <key> as <value> 

    Examples: 
      | invalidApiKey  | searchText  | limit  |status  | key      | value 															|
      | invalid        | love 			 | 1      | 403    | message  | Invalid authentication credentials  |