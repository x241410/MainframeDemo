Feature: Get En Sec from GITHUB

  Scenario: GetAuth code to generate access token
    * def keyName = karate.properties['karate.keyName']
    * def ghpt = karate.properties['karate.ghpt']
    Given url 'https://api.github.com/repos/telus/cdo-test-automation-bped-app-health-check/actions/variables/'+ keyName
    And header Authorization = 'Bearer '+ ghpt
    * configure proxy = 'http://198.161.14.25:8080'
    When method GET
    * def keyVal =  response.value
    Then match status 200
