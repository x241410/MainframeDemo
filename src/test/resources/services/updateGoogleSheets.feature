Feature: Update App Status in GoogleSheets

  Background: 
    * def currentThread = java.lang.Thread.currentThread().getId()
    * def access_token = karate.properties['karate.bearerToken']
    * def sheetId = karate.properties['karate.sheetId']
    * def range = karate.properties['karate.sheetRange']
    * def payload = karate.properties['karate.payload']
#    * def appStatus = karate.properties['karate.appStatus'+currentThread]
#    * def executedAt = karate.properties['karate.executedAt'+currentThread]
#    * def rowNumber = karate.properties['karate.rowNum'+currentThread]
#    * def applicationName = karate.properties['karate.applicationName'+currentThread]
#    * def cmdbId = karate.properties['karate.cmdbId'+currentThread]
#    * def priority = karate.properties['karate.priority'+currentThread]
#    * def platform = karate.properties['karate.platform'+currentThread]
#    * def primeName = karate.properties['karate.primeName'+currentThread]
#    * def managerName = karate.properties['karate.managerName'+currentThread]
#    * def directorName = karate.properties['karate.directorName'+currentThread]

  Scenario: Update Googlesheet
    Given url 'https://sheets.googleapis.com/v4/spreadsheets/'+sheetId+'/values/'+ range
    And param valueInputOption = 'USER_ENTERED'
    And header Accept = '*/*'
    And header Content-Type = 'application/json'
    And header Authorization = 'Bearer '+ access_token
#    And request {range: #(range), majorDimension: 'ROWS',  values: [[#(rowNumber),#(applicationName),#(cmdbId),#(priority),#(platform),#(primeName),#(managerName),#(directorName),#(appStatus), #(executedAt)]  ]}
    And request payload
    * configure proxy = 'http://198.161.14.25:8080'
    When method PUT
    Then match status 200
