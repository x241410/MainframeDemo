@Ignore
Feature: Operation Ping V1_0

  Background: Configuration - Set up the authentication, Headers, and params for status
  #Configure the Authentication
    * def currentThread = java.lang.Thread.currentThread().getId()

    * def jobs = karate.properties['karate.jobsFolderName'+currentThread]
    * def crp = 'karate.CurrentRun' + currentThread
    * def cr = karate.properties[crp];
    * def token = karate.properties['karate.loginToken'];
    * def query = ({runNo:cr})
    * def jobNamep = 'karate.JobID'+currentThread
    * def jobName = karate.properties[jobNamep];


  Scenario: Login to ControlM
    #Set endpoint url
    Given url ENDPOINT_NON_PROD +'run/job/'+jobName+'/output'
    * header Authorization = 'Bearer ' + token
    And params query

    When method get

		#Request XML passed for the operation and printing the same for verification
    Then status 200

#    * print response



