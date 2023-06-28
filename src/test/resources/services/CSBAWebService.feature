@Ignore
Feature: Operation Ping V1_0

  Background: Configuration - Set up the authentication, Headers, and params
  #Configure the Authentication
 * def payload = '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ping="http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/types/ping_v2">	<soapenv:Header/>	<soapenv:Body>	 <ping:ping>	<!--Optional:-->	<operationName>createBillingAccount</operationName>	<!--Optional:-->	<deepPing></deepPing>	</ping:ping>	</soapenv:Body>	</soapenv:Envelope>'
		
    #* configure proxy = 'http://198.161.14.25:8080'
    

  Scenario: Login to ControlM
  	#Set endpoint url
    Given url ENDPOINT_CSBA
    
    And request payload
    And header Content-Type = 'text/xml; charset=utf-8'
    When method post
		#Request XML passed for the operation and printing the same for verification
    Then status 200