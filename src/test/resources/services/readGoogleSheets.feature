Feature: Read and update Data from GoogleSheet

  Background: 
    * def access_token = karate.properties['karate.bearerToken']
    * def sheetId = karate.properties['karate.sheetId']
    * def sheetName = karate.properties['karate.sheetName']

  Scenario: Get Googlesheet data
    Given url 'https://sheets.googleapis.com/v4/spreadsheets/'+sheetId+'/values/'+sheetName
    And header Accept = '*/*'
    And header Authorization = 'Bearer ' + access_token
    * configure proxy = 'http://198.161.14.25:8080'
    When method GET
    * def OutputResponse = response
    * def responseValues = response.values
    Then match status 200
