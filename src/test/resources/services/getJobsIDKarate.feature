@Ignore
Feature: Operation Ping V1_0

  Background: Configuration - Set up the authentication, Headers, and params for jobs
  #Configure the Authentication
    * def currentThread = java.lang.Thread.currentThread().getId()
    * def jobss = karate.properties['karate.jobsFolderName'+currentThread]
    * def token = karate.properties['karate.loginToken']
    * def ctmid = karate.properties['karate.ctm']

    * def query = ({limit:1000,folder:jobss,ctm:ctmid,deleted:'false'})
    * print query;

  Scenario: Get Jobs Id
    #Set endpoint url
    Given url ENDPOINT_NON_PROD +'run/jobs/status'
    * header Authorization = 'Bearer ' + token
    And params query
    When method get

    #Request XML passed for the operation and printing the same for verification
    Then status 200

#    * print response


