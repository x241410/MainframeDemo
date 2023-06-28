
Feature: DataGridMgmtSvc - Validate the ping
    
  @v1-0
  Scenario: Get Jobs ID
    #Operation 1
    * print "RESPONSE || :"
    When def JobsID = call read(PATH_DATAGRIDMGMT_OPS+'getJobsIDKarate.feature')
     #Status
#    * print JobsID.response
    * def JobsIDStatus = JobsID.responseStatus
#    * print JobsIDStatus
    * def JobsIDRes = JobsID.response
    * def statuses = JobsIDRes.statuses
    * def count = JobsIDRes.returned
    #Validation
    Then match JobsIDStatus == 200