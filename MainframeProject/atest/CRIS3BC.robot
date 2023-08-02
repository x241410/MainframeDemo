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
CRIS3: Customer Records Information System 3 / Service Order System: BC

    Log To Console    ${\n}Step1- Enter region and Login into the	${REGION}	Region
    Screenshot.Take Screenshot        SS1_CRIS3BC_REGION.jpg
    ${read_region_title}    Read    04    002    20
    Log To Console    ${\n}Actual Env Title is ${read_region_title} and Expected is ${REGION_TITLE}
    Should Be Equal As Strings    ${REGION_TITLE}    ${read_region_title}
    Write Bare    ${REGION}
    Send Enter
    Write Bare    /for signon
    Send Enter
    ${read_region_login_title}    Read    01    025    23
    Log To Console    ${\n}Actual Env Title is ${read_region_login_title} and Expected is ${REGION_LOGIN_TITLE}
    Write Bare    ${REG_USERNAME}
    Write Bare    ${REG_PASSWORD}
    Send Enter
    ${read_login_alert}    Read    20    020    22
    Should Be Equal As Strings    ${LOGIN_ALERT}    ${read_login_alert}
    Screenshot.Take Screenshot        SS2_CRIS3BC_REGION_LOGIN.jpg
    Log To Console    ${\n}Step2- Login into    ${REGION}    is successful.
    ${PRESS_ESC}=    evaluate    utils.clearScreen()    modules=utils
    Sleep    5s
    Write Bare    /for cris3
    Send Enter
    Write Bare    ${APP_USERNAME}
    Write Bare    ${APP_PASSWORD}
    Send Enter
    ${read_app_Title}    Read    01    027    13
    Screenshot.Take Screenshot        SS3_CRIS3BC_DASHBOARD.jpg 
    Log To Console    ${\n}Step2- Login into CRIS3 BC Application is successful.
    Log To Console    ${\n}Actual App Dashboard Title is ${read_app_Title} and Expected is ${CRIS_APP_TITLE}
    Should Be Equal As Strings    ${CRIS_APP_TITLE}    ${read_app_Title}
    Log To Console    ${\n}Step3- Open BSC Screen to verify CRIS3 BC.
    ${BC_TN}=    evaluate    ${BC_PRE_TN}${POST_TN}
    Write Bare    ${BC_TN}
    Move Next Field
    Write Bare    ${SCREEN}
    Write Bare    ${MONTH}
    Send Enter
    Screenshot.Take Screenshot        SS4_CRIS3BC_BSC_PAGE.jpg
    ##Health Check##

    #1. Verify screen name
    ${read_screen_title}    Read    01    035    27
    Should Be Equal As Strings    ${SCREEN_TITLE}    ${read_screen_title}

    #2. Verify TN Number
    ${read_screen_tn}    Read    01    007    7
    Should Be Equal As Strings    ${POST_TN}    ${read_screen_tn}    strip_spaces=${True}
    
    Log To Console    ${\n}Step4- CRIS3 BC Health Check is verified.

*** Keywords ***
Suite Setup
    Open Connection    ${TPX_BC}
    Create Directory    ${FOLDER}
    Set Screenshot Directory    ${FOLDER}
    Change Wait Time    0.4
    Change Wait Time After Write    0.4
    Sleep    3s

Suite Teardown
    Send PF    1
    Close Connection
    Sleep    1s