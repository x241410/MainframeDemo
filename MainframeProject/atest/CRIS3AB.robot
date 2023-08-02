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
CRIS3: Customer Records Information System 3 / Service Order System: AB

    Log To Console    ${\n}Step1- Login into the TPX AB Cris3 in ${REGION} Region
    Screenshot.Take Screenshot        SS1_CRIS3AB_LAUNCH.jpg 
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
    Write Bare    /for cris3
    Screenshot.Take Screenshot        SS2_CRIS3AB_LOGIN.jpg
    Send Enter
    ${read_app_loginTitle}    Read    17    031    35
    Should Be Equal As Strings    ${CRIS_APP_LOGINPAGE}    ${read_app_loginTitle}
    Write Bare    ${APP_USERNAME}
    Write Bare    ${APP_PASSWORD}
    Send Enter
    ${read_app_Title}    Read    01    027    13
    Screenshot.Take Screenshot        SS3_CRIS3AB_DASHBOARD.jpg 
    Log To Console    ${\n}Actual App Dashboard Title is ${read_app_Title} and Expected is ${CRIS_APP_TITLE}
    Log To Console    ${\n}Step2- Login into CRIS3 Application is successful.
    Should Be Equal As Strings    ${CRIS_APP_TITLE}    ${read_app_Title}
    Log To Console    ${\n}Step3- Open BSC Screen to verify CRIS3 AB.
    ${AB_TN}=    evaluate    ${AB_PRE_TN}${POST_TN}
    Write Bare    ${AB_TN}
    Move Next Field
    Write Bare    ${SCREEN}
    Write Bare    ${MONTH}
    Send Enter
    Screenshot.Take Screenshot        SS4_CRIS3AB_BSC_PAGE.jpg
    ##Health Check##

    #1. Verify screen name
    ${read_screen_title}    Read    01    035    27
    Should Be Equal As Strings    ${SCREEN_TITLE}    ${read_screen_title}

    #2. Verify TN Number
    ${read_screen_tn}    Read    01    007    7
    Should Be Equal As Strings    ${POST_TN}    ${read_screen_tn}    strip_spaces=${True}

    Log To Console    ${\n}Step4- CRIS3 AB Health Check is verified.
    Send PF    1
    Sleep    3s

*** Keywords ***
Suite Setup
    Open Connection    ${TPX_AB}
    Create Directory    ${FOLDER}
    Set Screenshot Directory    ${FOLDER}
    Change Wait Time    0.4
    Change Wait Time After Write    0.4
    Sleep    3s

Suite Teardown
    Send PF    1
    Close Connection
    Sleep    1s