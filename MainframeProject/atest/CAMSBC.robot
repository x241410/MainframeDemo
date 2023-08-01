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
CAMS: BC

    Log To Console    ${\n}Step1- Enter region and Login into the region
    Screenshot.Take Screenshot        SS1_CAMSBC_REGION.jpg
    ${read_region_title}    Read    04    002    20
    Log To Console    ${\n}Actual Env Title is ${read_region_title} and Expected is ${REGION_TITLE}
    Should Be Equal As Strings    ${REGION_TITLE}    ${read_region_title}
    Write Bare    ${BC_REGION}
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
    Screenshot.Take Screenshot        SS2_CAMSBC_REGION_LOGIN.jpg
    Log To Console    ${\n}Step2- Login into    ${BC_REGION}    is successful.
    ${PRESS_ESC}=    evaluate    utils.clearScreen()    modules=utils
    Sleep    3s
    Write Bare    /for cris
    Send Enter
    Write Bare    ${APP_USERNAME}
    Write Bare    ${APP_PASSWORD}
    Send Enter
    ${read_app_Title}    Read    01    027    13
    Screenshot.Take Screenshot        SS3_CAMSBC_DASHBOARD.jpg 
    Log To Console    ${\n}Step3- Login into CRIS BC Application is successful.
    Log To Console    ${\n}Actual App Dashboard Title is ${read_app_Title} and Expected is ${CRIS_APP_TITLE}
    Should Be Equal As Strings    ${CRIS_APP_TITLE}    ${read_app_Title}
    Log To Console    ${\n}Step4- Open Collection Screen to verify CAMS.
    Write Bare in Position    swi    23    032
    Send Enter

    #Health Check
    ${read_collection_screen_Title}    Read    01    031    17
    Screenshot.Take Screenshot        SS4_CAMSBC_PAGE.jpg
    Should Be Equal As Strings    ${CAMS_COLLECTION_SCREEN_PAGE}    ${read_collection_screen_Title}
    Log To Console    ${\n}Step5- CAMS BC Health Check is verified.
    Send PF    3
    Send PF    1
    Sleep    3s

*** Keywords ***
Suite Setup
    Open Connection    ${TPX_BC}
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