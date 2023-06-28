
Feature: DataGridMgmtSvc - Validate the ping

  @v1-0
  Scenario: Validate the ping V1_0 to the service DataGridMgmtSvc
    #Operation 1
#    * print "RESPONSE || :"
    When def Login = call read(PATH_DATAGRIDMGMT_OPS+'TOLLFREEORDERSTATUSRestService.feature')
    #Status
#    * print Login.response
    * def LoginStatus = Login.responseStatus
    * def Response = Login.response
    * print Response
    * def resultNum = Response.resultNum
     * def successInd = Response.successInd

    #Validation
    Then match LoginStatus == 200
    Then match resultNum == "0"
    Then match successInd == "true"