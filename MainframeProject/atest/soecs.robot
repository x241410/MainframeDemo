*** Settings ***
Documentation     These tests verify SOECS application is Up or Not
Suite Setup       Suite Setup
Suite Teardown    Suite Teardown
Library           ../Mainframe3270/    run_on_failure_keyword=None
Library           Dialogs
Library           OperatingSystem
Library           String
Library           Screenshot
Library           ./utils.py
Resource          mainframe_variables.robot
*** Test Cases ***
Service Order Entry and Control System

    Log To Console    ${\n}Step1- Login into the SOECS Application
    Screenshot.Take Screenshot        SS1_SOECS_LAUNCH_PAGE.jpg
    Write Bare    ${SOECS_username}
    Send Enter
    Write Bare    ${SOECS_password}
    Send Enter
    sleep    2s
    Screenshot.Take Screenshot        SS2_SOECS_DASHBOARD.jpg
    Log To Console    ${\n}Step2- SOECS Application logout
    Write Bare in Position    3    23    057
    Send Enter
    Screenshot.Take Screenshot        SS3_SOECS_LOGOUT_PAGE.jpg

*** Keywords ***

Suite Setup
    Open Connection    ${HOST_soecs}
    Create Directory    ${FOLDER}
    Set Screenshot Directory    ${FOLDER}
    Change Wait Time    0.4
    Change Wait Time After Write    0.4
    Sleep    3s

Suite Teardown
    Close Connection
    Sleep    1s