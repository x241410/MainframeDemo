
Feature: DataGridMgmtSvc - Validate the ping

  @v1-0
  Scenario: Validate the ping V1_0 to the service DataGridMgmtSvc
    #Operation 1
#    * print "RESPONSE || :"
    When def Login = call read(PATH_DATAGRIDMGMT_OPS+'CrisServiceOrderManagementWebService.feature')
    #Status
#    * print Login.response
    * def LoginStatus = Login.responseStatus
    * def Response = Login.response
    * print Response

    #Validation
    Then match LoginStatus == 200