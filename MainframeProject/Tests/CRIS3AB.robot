*** Settings ***
Documentation     This test will verify CRIS3 AB application is Up or Not
Suite Setup       Suite Setup
Suite Teardown    Suite Teardown
Library           ../Mainframe3270/    run_on_failure_keyword=None
Library           Dialogs
Library           OperatingSystem
Library           String
Library           Screenshot
Resource          mainframe_variables.robot
Resource          resources/CommonKeywords.robot
*** Test Cases ***
CRIS3: Customer Records Information System 3 / Service Order System: AB

    Login Into The Mainframe Region AB and verify
    
    Login Into the CRIS3 Application and verify
    
    Open CRIS3 AB BSC page to verify Health Check

    Logout CRIS Application

*** Keywords ***
Suite Setup
    Suite Setup for TPX_AB

Suite Teardown
    Logout CRIS Application
    Close Connection
    Sleep    1s