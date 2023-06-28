@Ignore
Feature: Operation Ping V1_0

  Background: Configuration - Set up the authentication
  #Configure the Authentication
    * def tffsusername = karate.properties['karate.restusername']
    * def tffspassword = karate.properties['karate.restpassword']
      # Set a JSON object as a variable
    * def json = ({ username: tffsusername, password: tffspassword })
    * header Authorization = call read('classpath:basic-auth.js') json
    #* header Authorization= Basic QVBQX1JFQklMTEVSOnNvYW9yZ2lk
    
   Scenario: TOLLFREEORDERSTATUS
  	#Set endpoint url
    Given url ENDPOINT_TOLLFREEORDERSTATUS
    #* configure proxy = 'http://198.161.14.25:8080'
    When method get
		#When Rest Operation GET
    Then status 200