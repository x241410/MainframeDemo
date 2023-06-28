@Ignore
Feature: Operation Ping V1_0

  Background: Configuration - Set up the authentication, Headers, and params
  #Configure the Authentication
    * def ctmusername = karate.properties['karate.cmusername']
    * def ctmpassword = karate.properties['karate.cmpassword']
      # Set a JSON object as a variable
    * def json = ({ username: ctmusername, password: ctmpassword })


#    * header Authorization = call read('classpath:basic-auth.js') credentials.dataGrid[0]

  Scenario: Login to ControlM
  	#Set endpoint url
    Given url ENDPOINT_NON_PROD +'session/login'
    * print ENDPOINT_LAIRD_PT

    And request json
    When method post
    And def token = response.token

		#Request XML passed for the operation and printing the same for verification
    Then status 200



