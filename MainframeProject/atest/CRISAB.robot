*** Settings ***
Documentation     This test will verify CRIS AB application is Up or Not
Suite Setup       Suite Setup
Suite Teardown    Suite Teardown
Library           ../Mainframe3270/    run_on_failure_keyword=None
Library           Dialogs
Library           OperatingSystem
Library           String
Library           Screenshot
Library           ./utils.py
Resource          mainframe_variables.robot
Resource          CommonKeywords.robot
*** Test Cases ***
Customer Records Information System 2 / Update: AB

    Login Into The Mainframe Region AB and verify
    
    Login Into the CRIS Application and verify
    
    Open CRIS AB BSC page to verify Health Check

    Logout CRIS Application

*** Keywords ***
Suite Setup
    Suite Setup for TPX_AB

Suite Teardown
    Logout CRIS Application
    Close Connection
    Sleep    1s