@Ignore
Feature: Operation Ping V1_0

  Background: Configuration - Set up the authentication, Headers, and params
  #Configure the Authentication
    * def cid = karate.properties['karate.TestData']
    * def payload = '<?xml version="1.0" encoding="UTF-8"?><soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:telus="http://clecoss.telus.com" ><soapenv:Header><PartnerCryptoCertDN>CN=test.clecoss-api.telus.com, O=TELUS Corp., L=Vancouver, ST=British Columbia, C=CA</PartnerCryptoCertDN></soapenv:Header><soapenv:Body><queryBtnRequest xmlns="http://www.oswg.ca/CustomerInfo" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.oswg.ca/CustomerInfo"><BillingAccountDesignator><BillingTelephoneNumber>'+cid+'</BillingTelephoneNumber></BillingAccountDesignator><OcnSpid>2345</OcnSpid></queryBtnRequest></soapenv:Body></soapenv:Envelope>'


  Scenario: CLECOSS
  	#Set endpoint url
    Given url ENDPOINT_CLECOSS
    And request payload
    And header Content-Type = 'text/xml; charset=utf-8'
    And header SOAPAction = ''
    When method post
		#Request XML passed for the operation and printing the same for verification
    Then status 200



