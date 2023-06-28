
Feature: DataGridMgmtSvc - Validate the ping
    
  @v1-0
  Scenario: Get Jobs ID
    #Operation 1
    * print "RESPONSE || :"
    When def JobsID = call read(PATH_DATAGRIDMGMT_OPS+'orderJobsKarate.feature')
     #Status
#    * print JobsID.response
    * def JobsIDStatus = JobsID.responseStatus
#    * print JobsIDStatus
    * def OrderRes = JobsID.response
    #Validation
    Then match JobsIDStatus == 200