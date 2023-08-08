*** Settings ***
Documentation     This test will verify CAMS AB application is Up or Not
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
CAMS: AB

    Login Into The Mainframe Region AB and verify
    
    Login Into the CRIS Application and verify
    
    Open CAMS Collection Screen to verify Health Check

    Logout CAMS Application

*** Keywords ***
Suite Setup
    Suite Setup for TPX_AB

Suite Teardown
    Logout CAMS Application
    Close Connection
    Sleep    1s