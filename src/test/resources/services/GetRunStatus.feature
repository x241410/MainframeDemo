
Feature: DataGridMgmtSvc - Validate the ping
    
  @v1-0
  Scenario: Get Jobs RunStatus
    #Operation 1
    * print "RESPONSE || :"
    When def Output = call read(PATH_DATAGRIDMGMT_OPS+'getRunsInfoKarate.feature')
     #Status
    * def OutputStatus = Output.responseStatus
#    * print JobsIDStatus
    * def OutputResponse = Output.response
    #Validation
    Then match OutputStatus == 200