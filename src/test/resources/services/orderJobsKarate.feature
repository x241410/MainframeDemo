@Ignore
Feature: Operation Ping V1_0

  Background: Configuration - Set up the authentication, Headers, and params for jobs
  #Configure the Authentication
    * def token = karate.properties['karate.loginToken']
    * def currentThread = java.lang.Thread.currentThread().getId()
    * def foldername = karate.properties['karate.jobsFolderName'+currentThread]
    * def json = ({ctm: 'ctmit04',folder:foldername ,createDuplicate: 'false',hold: 'true',ignoreCriteria: 'false',independentFlow: 'true'})
    * print json


#    * header Authorization = call read('classpath:basic-auth.js') credentials.dataGrid[0]

  Scenario: Get Jobs Id
    #Set endpoint url
    Given url ENDPOINT_NON_PROD +'run/order'
    * header Authorization = 'Bearer ' + token
    * print ENDPOINT_LAIRD_PT
    And request json
    When method post

		#Request XML passed for the operation and printing the same for verification
    Then status 200

#    * print response


