@Ignore
Feature: Operation Ping V1_0

  Background: Configuration - Set up the authentication, Headers, and params
  Configure the Authentication
    * def csbausername = karate.properties['karate.camusername']
    * def csbapassword = karate.properties['karate.campassword']
#       Set a JSON object as a variable
    * def json = ({ username: csbausername, password: csbapassword })
       #Set endpoint url
    Given url ENDPOINT_CAMToken
    * header Authorization = call read('classpath:basic-auth.js') json

    * form field grant_type = 'client_credentials'
    * form field scope = '2040'
    And header Content-Type = 'application/x-www-form-urlencoded'
    When method post
    Then status 200
    * def accessToken = response.access_token


  Scenario: check Get
    Given url ENDPOINT_CAM
    * header Authorization = 'Bearer ' + accessToken
    * def query = ({btn:'SS1BCFERRIES'})
    And params query
    When method get
    #Request XML passed for the operation and printing the same for verification
    Then status 200
