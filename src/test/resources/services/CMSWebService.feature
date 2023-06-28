@Ignore
Feature: Operation Ping V1_0

  Background: Configuration - Set up the authentication, Headers, and params
  #Configure the Authentication
    * def ctmusername = karate.properties['karate.soapusername']
    * def ctmpassword = karate.properties['karate.soappassword']
      # Set a JSON object as a variable
    * def json = ({ username: ctmusername, password: ctmpassword })
    * def cid = karate.properties['karate.cmsTestData']
    * print cid

    * def payload = '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:con="http://xmlschema.tmi.telus.com/srv/CMO/ContractMgmt/ContractManagementServiceRequestResponse_v3"> <soapenv:Header/>  <soapenv:Body>   <con:findContractsByCustomerId>   <con:customerId>'+cid+'</con:customerId>  </con:findContractsByCustomerId>   </soapenv:Body>   </soapenv:Envelope>'

    * header Authorization = call read('classpath:basic-auth.js') json

  Scenario: Login to ControlM
  	#Set endpoint url
    Given url ENDPOINT_CMS
    And request payload
    And header Content-Type = 'text/xml; charset=utf-8'
    When method post
		#Request XML passed for the operation and printing the same for verification
    Then status 200



