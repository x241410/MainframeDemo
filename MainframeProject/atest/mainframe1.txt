*** Settings ***
Documentation     These tests verify All mainframe application is Up or Not
Suite Setup       Suite Setup
Suite Teardown    Suite Teardown
Library           ../Mainframe3270/    run_on_failure_keyword=None
Library           Dialogs
Library           OperatingSystem
Library           String
Library           ./utils.py
Library           Screenshot
Resource          mainframe_variables.robot
*** Test Cases ***
CRIS3: Customer Records Information System 3 / Service Order System: AB
    Log To Console    ${\n}Step1- Login into the TPX AB Cris in IMSE Env    no_newline=${False}
    ${read_env_title}    Read    19    013    41
    Log To Console    ${\n}Actual Env Title is ${read_env_title} and Expected is ${WELCOME_TITLE} no_newline=${False}
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
    Send Enter
    ${read_app_loginTitle}    Read    17    031    35
    Should Be Equal As Strings    ${CRIS_APP_LOGINPAGE}    ${read_app_loginTitle}
    Write Bare    ${APP_USERNAME}
    Write Bare    ${APP_PASSWORD}
    Send Enter
    ${read_app_Title}    Read    01    027    13
    Log To Console    ${\n}Actual App Dashboard Title is ${read_app_Title} and Expected is ${CRIS_APP_TITLE} no_newline=${False}
    Should Be Equal As Strings    ${CRIS_APP_TITLE}    ${read_app_Title}
    # Take Screenshot
    Log To Console    ${\n}Step2- Loggin succssfully in CRIS Application    no_newline=${False}
    # Take Screenshot
    ${read_app_Title}    Read    01    027    13
    Log To Console    ${\n}Actual App Dashboard Title is ${read_app_Title} and Expected is ${CRIS_APP_TITLE} no_newline=${False}
    Should Be Equal As Strings    ${CRIS_APP_TITLE}    ${read_app_Title}
    Log To Console    ${\n}Step3- Enter details for Install Order    no_newline=${False}
    Write Bare in Position    in    23    032
    Send Enter
    sleep    5s
    
    ${BILLING_NAME}=    evaluate    utils.billingName()    modules=utils
    sleep    5s
    Write Bare in Position    ${BILLING_NAME}    04    006
    Write Bare in Position    ${SA_HOUSE_No}    05    012
    Write Bare in Position    ${sa_area}    05    022
    Write Bare in Position    ${sa_city}    05    041
    Write Bare in Position    ${sa_PINCODE}    05    064
    Write Bare in Position    ${IN_KEY}    23    007
    Delete Field    23    047
    Send Enter
    # Take Screenshot
    Log To Console    ${\n}Step4- Enter BSC Page Details and Go to the LST Screen    no_newline=${False}
    Write Bare in Position    ${IN_SVC}    08    007
    Send Enter
    Send Enter
    # Take Screenshot
    Log To Console    ${\n}Step5- Enter LST Page Details and Go to the CCD Screen    no_newline=${False}
    Write Bare in Position    ${No_Number_Listed}    01    035
    Write Bare in Position    ${Listing_Type}    08    004
    Send Enter
    Send Enter
    Write Bare in Position    ${bus_type}    07    011
    Send Enter
    # Take Screenshot
    Log To Console    ${\n}Step6- Enter CCD Page Details & skip CLR Page and Jump to the EQP Screen    no_newline=${False}
    Send PF    9
    Send Enter
    Send Enter
    # Take Screenshot
    Log To Console    ${\n}Step7- Enter EQP Page Details and Go to the BIL Screen    no_newline=${False}
    Write Bare in Position    ${add_s&e1}    09    002
    Write Bare in Position    ${add_s&e2}    11    002
    Write Bare in Position    ${DIR}    21    028
    Send Enter
    Send Enter
    Log To Console    ${\n}Step8- Enter BIL Page Details & skip TEL page and Jump to the RMK Screen    no_newline=${False}
    Write Bare in Position    ${BILL_CHARGE}    08    007
    Write Bare in Position    ${BILL_CHARGE}    08    023
    Send Enter
    Send Enter
    Send Enter
    # Take Screenshot
    Log To Console    ${\n}Step9- Enter RMK Page Details and Go to the Service Order Screen    no_newline=${False}
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
    # Take Screenshot
    Send Enter
    # Take Screenshot
    Delete Field    23    047
    Delete Field    23    079
    Write Bare in Position    sor    23    030
    Send Enter
    # Take Screenshot
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
    # Take Screenshot
    Log To Console    ${\n}Step10- On Service Order Screen Varify Order is completed or not no_newline=${False}
    ${read_order_status}    Read    06    030    9
    Log To Console    ${\n}Actual Install Order is ${read_order_status} and Expected is ${ORDER_STATUS} no_newline=${False}
    Should Be Equal As Strings    ${ORDER_STATUS}    ${read_order_status}
    Send PF    1
    # Take Screenshot
    Log To Console    ${\n}Step11- Once Order is completed Logout the CRIS Application no_newline=${False}
    ${read_app_loginTitle}    Read    17    031    35
    Log To Console    ${\n}Actual Title after logout App is ${read_app_loginTitle} and Expected is ${CRIS_APP_LOGINPAGE} no_newline=${False}
    Should Be Equal As Strings    ${CRIS_APP_LOGINPAGE}    ${read_app_loginTitle}
    # Take Screenshot
    Log To Console    ${\n}Step12- Logout Cris Application successfully    no_newline=${False}
    sleep    5s
    Send PF    1

CRIS SS: Customer Records Information System Special Services
    Log To Console    ${\n}Step1- Login into the TPX AB Cris Application    no_newline=${False}
    Write Bare    /for cris
    Send Enter
    ${read_app_loginTitle}    Read    17    031    35
    Log To Console    ${\n}Actual App Login Page Title is ${read_app_loginTitle} and Expected is ${CRIS_APP_LOGINPAGE} no_newline=${False}
    Should Be Equal As Strings    ${CRIS_APP_LOGINPAGE}    ${read_app_loginTitle}
    Take Screenshot
    Write Bare    ${APP_USERNAME}
    Write Bare    ${APP_PASSWORD}
    Send Enter
    ${read_app_Title}    Read    01    027    13
    Log To Console    ${\n}Actual App Dashboard Title is ${read_app_Title} and Expected is ${CRIS_APP_TITLE} no_newline=${False}
    Should Be Equal As Strings    ${CRIS_APP_TITLE}    ${read_app_Title}
    Take Screenshot
    Log To Console    ${\n}Step2- Loggin succssfully in CRIS Application    no_newline=${False}
    Log To Console    ${\n}Step3- verify Cris Application healthcheck    no_newline=${False}
    Send PF    1
    ${read_app_loginTitle}    Read    17    031    35
    Log To Console    ${\n}Actual Title after logout App is ${read_app_loginTitle} and Expected is ${CRIS_APP_LOGINPAGE} no_newline=${False}
    Should Be Equal As Strings    ${CRIS_APP_LOGINPAGE}    ${read_app_loginTitle}
    Log To Console    ${\n}Step4- Logout Cris Application successfully    no_newline=${False}
    Take Screenshot

Install_Order
    Log To Console    ${\n}Step1- Login into the TPX AB Cris Application to Install Order    no_newline=${False}
    Write Bare    ${APP_USERNAME}
    Write Bare    ${APP_PASSWORD}
    Send Enter
    Take Screenshot
    ${read_app_Title}    Read    01    027    13
    Log To Console    ${\n}Actual App Dashboard Title is ${read_app_Title} and Expected is ${CRIS_APP_TITLE} no_newline=${False}
    Should Be Equal As Strings    ${CRIS_APP_TITLE}    ${read_app_Title}
    Log To Console    ${\n}Step2- Enter IN under function to install order on CRIS page and Go to the NAM Page no_newline=${False}
    Write Bare in Position    in    23    032
    Send Enter
    Take Screenshot
    Log To Console    ${\n}Step3- Enter NAM Page Details and Go to the BSC Screen    no_newline=${False}
    ${BILLING_NAME}=    evaluate    utils.billingName()    modules=utils
    Write Bare in Position    ${BILLING_NAME}    04    006
    Write Bare in Position    ${SA_HOUSE_No}    05    012
    Write Bare in Position    ${sa_area}    05    022
    Write Bare in Position    ${sa_city}    05    041
    Write Bare in Position    ${sa_PINCODE}    05    064
    Write Bare in Position    ${IN_KEY}    23    007
    Delete Field    23    047
    Send Enter
    Take Screenshot
    Log To Console    ${\n}Step4- Enter BSC Page Details and Go to the LST Screen    no_newline=${False}
    Write Bare in Position    ${IN_SVC}    08    007
    Send Enter
    Send Enter
    Take Screenshot
    Log To Console    ${\n}Step5- Enter LST Page Details and Go to the CCD Screen    no_newline=${False}
    Write Bare in Position    ${No_Number_Listed}    01    035
    Write Bare in Position    ${Listing_Type}    08    004
    Send Enter
    Send Enter
    Write Bare in Position    ${bus_type}    07    011
    Send Enter
    Take Screenshot
    Log To Console    ${\n}Step6- Enter CCD Page Details & skip CLR Page and Jump to the EQP Screen    no_newline=${False}
    Send PF    9
    Send Enter
    Send Enter
    Take Screenshot
    Log To Console    ${\n}Step7- Enter EQP Page Details and Go to the BIL Screen    no_newline=${False}
    Write Bare in Position    ${add_s&e1}    09    002
    Write Bare in Position    ${add_s&e2}    11    002
    Write Bare in Position    ${DIR}    21    028
    Send Enter
    Send Enter
    Log To Console    ${\n}Step8- Enter BIL Page Details & skip TEL page and Jump to the RMK Screen    no_newline=${False}
    Write Bare in Position    ${BILL_CHARGE}    08    007
    Write Bare in Position    ${BILL_CHARGE}    08    023
    Send Enter
    Send Enter
    Send Enter
    Take Screenshot
    Log To Console    ${\n}Step9- Enter RMK Page Details and Go to the Service Order Screen    no_newline=${False}
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
    Take Screenshot
    Send Enter
    Take Screenshot
    Delete Field    23    047
    Delete Field    23    079
    Write Bare in Position    sor    23    030
    Send Enter
    Take Screenshot
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
    Take Screenshot
    Log To Console    ${\n}Step10- On Service Order Screen Varify Order is completed or not no_newline=${False}
    ${read_order_status}    Read    06    030    9
    Log To Console    ${\n}Actual Install Order is ${read_order_status} and Expected is ${ORDER_STATUS} no_newline=${False}
    Should Be Equal As Strings    ${ORDER_STATUS}    ${read_order_status}
    Send PF    1
    Take Screenshot
    Log To Console    ${\n}Step11- Once Order is completed Logout the CRIS Application no_newline=${False}
    ${read_app_loginTitle}    Read    17    031    35
    Log To Console    ${\n}Actual Title after logout App is ${read_app_loginTitle} and Expected is ${CRIS_APP_LOGINPAGE} no_newline=${False}
    Should Be Equal As Strings    ${CRIS_APP_LOGINPAGE}    ${read_app_loginTitle}
    Take Screenshot
    Log To Console    ${\n}Step12- Logout Cris Application successfully    no_newline=${False}
    sleep    30s
*** Keywords ***
Suite Setup
    Open Connection    ${HOST}
    Create Directory    ${FOLDER}
    Empty Directory    ${FOLDER}
    Set Screenshot Folder    ${FOLDER}
    Change Wait Time    0.4
    Change Wait Time After Write    0.4
    Sleep    3s

Suite Teardown
    Send PF    1
    Close Connection
    Sleep    1s
    # Verify String Not Found
    # \ \ \ \ [Arguments] \ \ \ ${keyword} \ \ \ ${string} \ \ \ ${ignore_case}=${False}
    # \ \ \ \ ${expected_error} \ \ \ Set Variable \ \ \ The string "${string}" was not found
    # \ \ \ \ Run Keyword And Expect Error \ \ \ ${expected_error} \ \ \ ${keyword} \ \ \ ${string} \ \ \ ignore_case=${ignore_case}
    # Verify String Not Found In List
    # \ \ \ \ [Arguments] \ \ \ ${keyword} \ \ \ ${string_list} \ \ \ ${string_position} \ \ \ ${ignore_case}=${False}
    # \ \ \ \ ${not_found_string} \ \ \ Set Variable If \ \ \ ${ignore_case}==${False} \ \ \ ${string_list[${${string_position}-1}]} \ \ \ ${string_list[${${string_position}-1}].lower()}
    # \ \ \ \ ${expected_error} \ \ \ Set Variable \ \ \ The string "${not_found_string}" was not found
    # \ \ \ \ Run Keyword And Expect Error \ \ \ ${expected_error} \ \ \ ${keyword} \ \ \ ${string_list} \ \ \ ignore_case=${ignore_case}
    # Verify List Not Found
    # \ \ \ \ [Arguments] \ \ \ ${keyword} \ \ \ ${list} \ \ \ ${ignore_case}=${False}
    # \ \ \ \ ${expected_error} \ \ \ Set Variable \ \ \ The strings "${list}" were not found
    # \ \ \ \ Run Keyword And Expect Error \ \ \ EQUALS: ${expected_error} \ \ \ ${keyword} \ \ \ ${list} \ \ \ ignore_case=${ignore_case}
    # Verify Pattern Not Found
    # \ \ \ \ [Arguments] \ \ \ ${keyword} \ \ \ ${string} \ \ \ ${ignore_case}=${False}
    # \ \ \ \ ${not_found_string} \ \ \ Set Variable If \ \ \ ${ignore_case}==${False} \ \ \ ${string} \ \ \ ${string.lower()}
    # \ \ \ \ ${expected_error} \ \ \ Set Variable \ \ \ No matches found for "${not_found_string}" pattern
    # \ \ \ \ IF \ \ \ ${ignore_case}
    # \ \ \ \ \ \ \ \ Run Keyword And Expect Error \ \ \ ${expected_error} \ \ \ ${keyword} \ \ \ ${string} \ \ \ ignore_case=${ignore_case}
    # \ \ \ \ ELSE
    # \ \ \ \ \ \ \ \ Run Keyword And Expect Error \ \ \ ${expected_error} \ \ \ ${keyword} \ \ \ ${string}
    # \ \ \ \ END
    # Verify String Does Not Appear X Times
    # \ \ \ \ [Arguments] \ \ \ ${keyword} \ \ \ ${string} \ \ \ ${wrong_number_of_times} \ \ \ ${right_number_of_times} \ \ \ ${ignore_case}=${False}
    # \ \ \ \ ${expected_error} \ \ \ Set Variable \ \ \ The string "${string}" was not found "${wrong_number_of_times}" times, it appears "${right_number_of_times}" times
    # \ \ \ \ Run Keyword And Expect Error \ \ \ ${expected_error} \ \ \ ${keyword} \ \ \ ${TEXT_TO_COUNT} \ \ \ 1 \ \ \ ignore_case=${ignore_case}
    # Verify String Found
    # \ \ \ \ [Arguments] \ \ \ ${keyword} \ \ \ ${string} \ \ \ ${ignore_case}=${False}
    # \ \ \ \ ${expected_error} \ \ \ Set Variable \ \ \ The string "${string}" was found
    # \ \ \ \ Run Keyword And Expect Error \ \ \ ${expected_error} \ \ \ ${keyword} \ \ \ ${string} \ \ \ ignore_case=${ignore_case}
    # Verify List Found
    # \ \ \ \ [Arguments] \ \ \ ${keyword} \ \ \ ${string_list} \ \ \ ${string_position} \ \ \ ${ignore_case}=${False}
    # \ \ \ \ ${expected_error} \ \ \ Set Variable \ \ \ The string "${string_list[${${string_position}-1}]}" was found
    # \ \ \ \ Run Keyword And Expect Error \ \ \ ${expected_error} \ \ \ ${keyword} \ \ \ ${string_list} \ \ \ ignore_case=${ignore_case}
    # Verify Wait Until String
    # \ \ \ \ [Arguments] \ \ \ ${keyword} \ \ \ ${string}
    # \ \ \ \ ${expected_error} \ \ \ Set Variable \ \ \ String "${string}" not found in 5 seconds
    # \ \ \ \ Run Keyword And Expect Error \ \ \ ${expected_error} \ \ \ ${keyword} \ \ \ ${string}
    # Verify Wait Until String With Timeout
    # \ \ \ \ [Arguments] \ \ \ ${keyword} \ \ \ ${string} \ \ \ ${timeout}
    # \ \ \ \ ${expected_error} \ \ \ Set Variable \ \ \ String "${string}" not found in ${timeout} seconds
    # \ \ \ \ Run Keyword And Expect Error \ \ \ ${expected_error} \ \ \ ${keyword} \ \ \ ${string} \ \ \ ${timeout}
