
Feature: DataGridMgmtSvc - Validate the ping
    
  @v1-0
  Scenario: Validate the ping V1_0 to the service DataGridMgmtSvc
    #Operation 1
#    * print "RESPONSE || :"
   	When def Login = call read(PATH_DATAGRIDMGMT_OPS+'bpedKarate.feature')
    #Status
#    * print Login.response
    * def LoginStatus = Login.responseStatus
    * print LoginStatus
    * def LoginResponse = Login.response
    * def token = LoginResponse.token
    * def token1 = Login.token
    #Validation
    Then match LoginStatus == 200