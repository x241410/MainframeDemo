*** Settings ***
Documentation     These tests verify SOECS application is Up or Not
Suite Setup       Suite Setup
Suite Teardown    Suite Teardown
Library           ../Mainframe3270/    run_on_failure_keyword=None
Library           Dialogs
Library           OperatingSystem
Library           String
Library           Screenshot
Library           SeleniumLibrary
Library           ./utils.py
Resource          mainframe_variables.robot
*** Test Cases ***
Service Order Entry and Control System

    ${screenLoc}    Screenshot.Take Screenshot        SOECS_first.jpg
    Capture Page Screenshot    ${FOLDER}/screenshot.png
    Log To Console    ${screenLoc}
    Log To Console    ${\n}Step1- Login into the SOECS Application
    Screenshot.Take Screenshot        SOECS_launchSOECS.jpg
    Write Bare    ${SOECS_username}
    Send Enter
    Write Bare    ${SOECS_password}
    Send Enter
    sleep    2s
    Screenshot.Take Screenshot        SOECS_Dashboard.jpg
    Write Bare in Position    3    23    057
    Log To Console    ${\n}Step2- SOECS Application logout successfully
    Send Enter

*** Keywords ***

Suite Setup
    Open Connection    ${HOST_soecs}
    Create Directory    ${FOLDER}
    Set Screenshot Directory    ${FOLDER}
    Change Wait Time    0.4
    Change Wait Time After Write    0.4
    Sleep    3s

Suite Teardown
    Send PF    1
    Close Connection
    Sleep    1s