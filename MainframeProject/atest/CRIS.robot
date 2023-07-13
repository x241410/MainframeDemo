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
Customer Records Information System 2 / Update: AB

    Log To Console    ${\n}Step1- Login into the TPX AB Cris in IMSE Env
    Screenshot.Take Screenshot        SS1_CRISAB_LAUNCH_IMSI.jpg 
    ${read_env_title}    Read    19    013    41
    Log To Console    ${\n}Actual Env Title is ${read_env_title} and Expected is ${WELCOME_TITLE}
    Should Be Equal As Strings    ${WELCOME_TITLE}    ${read_env_title}
    Write Bare    ${WRITE_TEXT}
    Send Enter
    Write Bare    ${ENV_USERNAME}
    Move Next Field
    Write Bare    ${ENV_PASSWORD}
    Send Enter
    Write Bare    ${SELECT_NEWS}
    Send Enter
    Write Bare    /for cris
    Screenshot.Take Screenshot        SS2_CRISAB_LOGIN.jpg 
    Send Enter
    ${read_app_loginTitle}    Read    17    031    35
    Should Be Equal As Strings    ${CRIS_APP_LOGINPAGE}    ${read_app_loginTitle}
    Write Bare    ${APP_USERNAME}
    Write Bare    ${APP_PASSWORD}
    Send Enter
    ${read_app_Title}    Read    01    027    13
    Screenshot.Take Screenshot        SS3_CRISAB__DASHBOARD.jpg 
    Log To Console    ${\n}Step2- Loggin succssfully in CRIS Application
    Log To Console    ${\n}Actual App Dashboard Title is ${read_app_Title} and Expected is ${CRIS_APP_TITLE}
    Should Be Equal As Strings    ${CRIS_APP_TITLE}    ${read_app_Title}
    ${read_app_Title}    Read    01    027    13
    Log To Console    ${\n}Actual App Dashboard Title is ${read_app_Title} and Expected is ${CRIS_APP_TITLE}
    Should Be Equal As Strings    ${CRIS_APP_TITLE}    ${read_app_Title}
    Log To Console    ${\n}Step3- Enter details for Install Order
    Write Bare in Position    in    23    032
    Send Enter
    sleep    2s
    Screenshot.Take Screenshot        SS4_CRISAB_INSTALL_ORDER.jpg
    ${BILLING_NAME}=    evaluate    utils.billingName()    modules=utils
    Write Bare in Position    ${BILLING_NAME}    04    006
    Write Bare in Position    ${SA_HOUSE_No}    05    012
    Write Bare in Position    ${sa_area}    05    022
    Write Bare in Position    ${sa_city}    05    041
    Write Bare in Position    ${sa_PINCODE}    05    064
    Write Bare in Position    ${IN_KEY}    23    007
    Delete Field    23    047
    Send Enter
    Screenshot.Take Screenshot        SS5_CRISAB_BSC_PAGE.jpg
    Log To Console    ${\n}Step4- Enter BSC Page Details and Go to the LST Screen
    Write Bare in Position    ${IN_SVC}    08    007
    Send Enter
    Send Enter
    Screenshot.Take Screenshot        SS6_CRISAB_FINAL_PAGE.jpg
    Log To Console    ${\n}Step5- Enter LST Page Details and Go to the CCD Screen
    Write Bare in Position    ${No_Number_Listed}    01    035
    Write Bare in Position    ${Listing_Type}    08    004
    Send Enter
    Send Enter
    Write Bare in Position    ${bus_type}    07    011
    Send Enter
    Screenshot.Take Screenshot        SS7_CRISAB_CCD_PAGE.jpg
    Log To Console    ${\n}Step6- Enter CCD Page Details & skip CLR Page and Jump to the EQP Screen
    Send PF    9
    Send Enter
    Send Enter
    Screenshot.Take Screenshot        SS8_CRISAB_EQP_PAGE.jpg
    Log To Console    ${\n}Step7- Enter EQP Page Details and Go to the BIL Screen
    Write Bare in Position    ${add_s&e1}    09    002
    Write Bare in Position    ${add_s&e2}    11    002
    Write Bare in Position    ${DIR}    21    028
    Send Enter
    Send Enter
    Log To Console    ${\n}Step8- Enter BIL Page Details & skip TEL page and Jump to the RMK Screen
    Write Bare in Position    ${BILL_CHARGE}    08    007
    Write Bare in Position    ${BILL_CHARGE}    08    023
    Send Enter
    Send Enter
    Send Enter
    Screenshot.Take Screenshot        SS9_CRISAB_RMK_PAGE.jpg
    Log To Console    ${\n}Step9- Enter RMK Page Details and Go to the Service Order Screen
    ${KEY_PHONE_No}=    evaluate    utils.telephone()    modules=utils
    Write Bare in Position    ${KEY_PHONE_No}    01    004
    ${REQUEST_DATE}=    evaluate    utils.requestDate()    modules=utils
    ${DUE_DATE}=    evaluate    utils.dueDate()    modules=utils
    Write Bare in Position    ${REQUEST_DATE}    07    012
    Write Bare in Position    ${DUE_DATE}    07    027
    Delete Field    09    013
    Write Bare in Position    ${INSTALL}    09    11
    Write Bare in Position    ${FIRST_NAME}    10    007
    Write Bare in Position    ${PHONE_NO}    10    038
    Send Enter
    Screenshot.Take Screenshot        SS10_CRISAB_VERIFY_RMK_DETAILS.jpg
    Send Enter
    Delete Field    23    047
    Delete Field    23    079
    Write Bare in Position    sor    23    030
    Send Enter
    Screenshot.Take Screenshot        SS11_CRISAB_COMPLETION_DETAILS.jpg
    Write Bare in Position    com    23    030
    Send Enter
    Write Bare in Position    rmk    23    047
    Send Enter
    Write Bare in Position    ${DUE_DATE}    21    007
    Write Bare in Position    ${COMPLETION_ID}    21    023
    Write Bare in Position    ${COMPLETE_TIME}    21    034
    Write Bare in Position    ${COMPLETION_CK}    21    048
    Send Enter
    Delete Field    23    047
    Delete Field    23    079
    Write Bare in Position    sor    23    030
    Send Enter
    Screenshot.Take Screenshot        SS12_CRISAB_VERIFY_COMPLETED_STATUS.jpg
    Log To Console    ${\n}Step10- On Service Order Screen Varify Order is completed or not
    ${read_order_status}    Read    06    030    9
    Log To Console    ${\n}Actual Install Order is ${read_order_status} and Expected is ${ORDER_STATUS}
    Should Be Equal As Strings    ${ORDER_STATUS}    ${read_order_status}
    Send PF    1
    Log To Console    ${\n}Step11- Once Order is completed Logout the CRIS Application
    ${read_app_loginTitle}    Read    17    031    35
    Log To Console    ${\n}Actual Title after logout App is ${read_app_loginTitle} and Expected is ${CRIS_APP_LOGINPAGE}
    Should Be Equal As Strings    ${CRIS_APP_LOGINPAGE}    ${read_app_loginTitle}
    Screenshot.Take Screenshot        SS13_CRISAB_LOGOUT.jpg
    Log To Console    ${\n}Step12- Logout Cris Application successfully
    sleep    5s
    Send PF    1

*** Keywords ***
Suite Setup
    Open Connection    ${HOST_cris}
    Create Directory    ${FOLDER}
    Empty Directory    ${FOLDER}
    Set Screenshot Directory    ${FOLDER}
    Change Wait Time    0.4
    Change Wait Time After Write    0.4
    Sleep    3s

Suite Teardown
    Send PF    1
    Close Connection
    Sleep    1s