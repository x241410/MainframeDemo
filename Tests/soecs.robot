*** Settings ***
Documentation     These tests verify SOECS application is Up or Not
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
Service Order Entry and Control System

    Login into SOECS application

    Verify SOECS homepage is displayed

    Logout from SOECS application


*** Keywords ***
Suite Setup
    Suite Setup for SOECS

Suite Teardown
    Close Connection
    Sleep    1s