*** Settings ***
Documentation     This test will verify CRIS3 BC application is Up or Not
Suite Setup       Suite Setup
Suite Teardown    Suite Teardown
Library           ../Mainframe3270/
Library           Dialogs
Library           OperatingSystem
Library           String
Library           Screenshot
Resource          resources/mainframe_variables.robot
Resource          resources/CommonKeywords.robot
*** Test Cases ***
CRIS3: Customer Records Information System 3 / Service Order System: BC

    Login Into The Mainframe Region BC and verify
    
    Login Into the CRIS3 Application and verify
    
    Open CRIS3 BC BSC page to verify Health Check

    Logout CRIS Application


*** Keywords ***
Suite Setup
    Suite Setup for TPX_BC

Suite Teardown
    Logout CRIS Application
    Close Connection
    Sleep    1s