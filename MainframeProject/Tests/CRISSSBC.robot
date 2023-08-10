*** Settings ***
Documentation     This test will verify CRIS BC application is Up or Not
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
CRIS SS: Customer Records Information System Special Services

    Login Into The Mainframe Region BC and verify

    Login Into the CRIS SS Application and verify

    Open CRIS BC BSC page to verify Health Check
    
    Logout CRIS Application

*** Keywords ***
Suite Setup
    Suite Setup for TPX_BC

Suite Teardown
    Logout CRIS Application
    Close Connection
    Sleep    1s