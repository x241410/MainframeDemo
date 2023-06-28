Feature: Get OAuth Token for GoogleSheet

  Scenario: GetAuth Token to generate access token
    Given url 'https://oauth2.googleapis.com/token'
    And form field client_id = karate.properties['karate.cid']
    And form field client_secret = karate.properties['karate.csec']
    And form field grant_type = 'refresh_token'
    And form field refresh_token = karate.properties['karate.cRT']
    * configure proxy = 'http://198.161.14.25:8080'
    When method POST
    * def access_token =  response.access_token
    Then match status 200
