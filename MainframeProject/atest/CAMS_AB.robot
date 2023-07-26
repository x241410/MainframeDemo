*** Settings ***
Documentation     These tests verify All mainframe application is Up or Not
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
CAMS: AB

    Log To Console    ${\n}Step1- Login into the TPX AB Cris in ${REGION} Env
    Screenshot.Take Screenshot        SS1_CRIS_LAUNCH_IMSI.jpg 
    ${read_env_title}    Read    19    013    41
    Log To Console    ${\n}Actual Env Title is ${read_env_title} and Expected is ${WELCOME_TITLE}
    Should Be Equal As Strings    ${WELCOME_TITLE}    ${read_env_title}
    Write Bare    ${REGION}
    Send Enter
    Write Bare    ${REG_USERNAME}
    Move Next Field
    Write Bare    ${REG_PASSWORD}
    Send Enter
    Write Bare    ${SELECT_NEWS}
    Send Enter
    Write Bare    /for cris
    Screenshot.Take Screenshot        SS2_CRIS_LOGIN.jpg
    Send Enter
    ${read_app_loginTitle}    Read    17    031    35
    Should Be Equal As Strings    ${CRIS_APP_LOGINPAGE}    ${read_app_loginTitle}
    Write Bare    ${APP_USERNAME}
    Write Bare    ${APP_PASSWORD}
    Send Enter
    ${read_app_Title}    Read    01    027    13
    Screenshot.Take Screenshot        SS3_CRIS_DASHBOARD.jpg 
    Log To Console    ${\n}Step2- Login into CRIS Application is successful.
    Log To Console    ${\n}Actual App Dashboard Title is ${read_app_Title} and Expected is ${CRIS_APP_TITLE}
    Should Be Equal As Strings    ${CRIS_APP_TITLE}    ${read_app_Title}
    Log To Console    ${\n}Step3- Open Collection Screen to verify CAMS.
    Write Bare in Position    swi    23    032
    Send Enter
    ${read_collection_screen_Title}    Read    01    031    17
    Screenshot.Take Screenshot        SS4_CAMS_PAGE.jpg
    Should Be Equal As Strings    ${CAMS_COLLECTION_SCREEN_PAGE}    ${read_collection_screen_Title}
    Send PF    3
    Send PF    1
    Sleep    3s

*** Keywords ***
Suite Setup
    Open Connection    ${HOST_cris}
    Create Directory    ${FOLDER}
    Set Screenshot Directory    ${FOLDER}
    Change Wait Time    0.4
    Change Wait Time After Write    0.4
    Sleep    3s

Suite Teardown
    Send PF    3
    Send PF    1
    Close Connection
    Sleep    1s