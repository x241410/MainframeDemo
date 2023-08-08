*** Settings ***
Library           ../../Mainframe3270/
Library           Dialogs
Library           OperatingSystem
Library           String
Library           Screenshot
Library           ../utils/genericUtils.py
Resource          mainframe_variables.robot
Resource          BaseKeywords.robot

*** Keywords ***
##Keywords for AB region Login
Login Into The Mainframe Region AB and verify
    Connect to AB Region
    Login into the AB Region

Connect to AB Region
    Log Reporting    Step1- Login into the TPX AB in ${REGION} region
    Capture Screen    LAUNCH.jpg             
    Verify actual and expected string    19    013    41    ${WELCOME_TITLE}
    Log Reporting    Region page displayed successfully
    Connect to region    ${REGION}

Login into the AB Region
    Login Into Region    ${REG_USERNAME}    ${REG_PASSWORD}    17    015    034
    Write Text and Enter    ${SELECT_NEWS}
    Capture Screen    LOGIN.jpg

##Keywords of Application login

Login Into the CRIS3 Application and verify
	Enter CRIS3 Application name and verify
	Login into the Application with valid credentials
	
Login Into the CRIS Application and verify
	Enter CRIS Application name and verify
	Login into the Application with valid credentials


Enter CRIS3 Application name and verify
    Log Reporting    Step2- Login into CRIS3 Application
    Write Text and Enter    /for cris3
    Verify actual and expected string    17    031    35    ${CRIS_APP_LOGINPAGE}
    Log Reporting    CRIS3 login page displayed successfully
    Capture Screen        LOGINPAGE.jpg
	
Enter CRIS Application name and verify 
    Log Reporting    Step2- Login into CRIS Application
    Write Text and Enter    /for cris
    Verify actual and expected string    17    031    35    ${CRIS_APP_LOGINPAGE}
    Log Reporting    CRIS login page displayed successfully
    Capture Screen        LOGINPAGE.jpg
    
Login into the Application with valid credentials
    Login Into Application    ${APP_USERNAME}    ${APP_PASSWORD}
    Verify actual and expected string    01    027    13    ${CRIS_APP_TITLE}
    Log Reporting    Homepage displayed successfully
    Capture Screen        DASHBOARD.jpg

##Keywords for AB BSC verification
Open CRIS3 AB BSC page to verify Health Check
    Log Reporting    Step3- Open BSC Screen to verify CRIS3 AB.
    Enter required details of AB region to navigate to BSC screen
    Verify BSC Screen
    Verify TN Number

Open CRIS AB BSC page to verify Health Check
    Log Reporting    Step3- Open BSC Screen to verify CRIS AB.
    Enter required details of AB region to navigate to BSC screen
    Verify BSC Screen
    Verify TN Number

Enter required details of AB region to navigate to BSC screen  
    ${TN}    Add Two String value    ${AB_PRE_TN}    ${POST_TN}    
    Write Text and Move to next field    ${TN}
    Write Text    ${SCREEN}
    Write Text and Enter    ${MONTH}
    Capture Screen        BSC_PAGE.jpg

##Keywords for BC Region Login
Login Into The Mainframe Region BC and verify
    Connect to BC Region
    Login into the BC Region

Connect to BC Region
    Log Reporting    Step1- Login into the TPX BC in ${REGION} region
    Capture Screen        LAUNCH.jpg
    Verify actual and expected string    04    002    20    ${REGION_TITLE}
    Log Reporting    Region page displayed successfully
    Connect to region    ${REGION}
	
Login into the BC Region
    Write Text and Enter    /for signon
    Verify actual and expected string    01    025    05    ${REGION_LOGIN_TITLE}
    Log Reporting    Region sign in page displayed.
    Login Into Region    ${REG_USERNAME}    ${REG_PASSWORD}    03    028    053
    Verify actual and expected string    20    020    22    ${LOGIN_ALERT}
    Log Reporting    Region logged in successfully
    Capture Screen        REGION_LOGIN.jpg
    ${PRESS_ESC}=    evaluate    utils.clearScreen()    modules=utils
    Sleep    3s

##Keywords for BC BSC verification
Enter required details of BC region to navigate to BSC screen
    ${TN}    Add Two String value    ${BC_PRE_TN}    ${POST_TN}
    Write Text and Move to next field    ${TN}
    Write Text    ${SCREEN}
    Write Text and Enter    ${MONTH}
    Capture Screen        BSC_PAGE.jpg

Open CRIS BC BSC page to verify Health Check
    Log Reporting    Step3- Open BSC Screen to verify CRIS BC.
    Enter required details of BC region to navigate to BSC screen
    Verify BSC Screen
    Verify TN Number

Open CRIS3 BC BSC page to verify Health Check
    Log Reporting    Step3- Open BSC Screen to verify CRIS3 BC.
    Enter required details of BC region to navigate to BSC screen
    Verify BSC Screen
    Verify TN Number

##Common Keywords for All testcases

Open CAMS Collection Screen to verify Health Check
    Log Reporting    Step3- Open Collection Screen to verify CAMS.
    Write Text on specific position    ${CAMS_SCREEN}    23    032
    Send Enter
    Verify actual and expected string    01    031    17    ${CAMS_COLLECTION_SCREEN_PAGE}
    Log Reporting    Collection Screen displayed successfully.
    Capture Screen    SS4_CAMSAB_PAGE.jpg

Verify BSC Screen
    #1. Verify screen name
    ${read_screen_title}    Read Text    01    035    27
    #Invalid TPX/NPP verification
    IF    "${read_screen_title}" == "${SCREEN_TITLE}"
        Compare Two String    ${SCREEN_TITLE}    ${read_screen_title}
        Log Reporting    BSC Screen Displayed successfully
    ELSE
        Assertion Fail    Alert- Please Enter valid TN Number
    END

Verify TN Number
    #2. Verify TN number name
    Verify actual and expected string    01    007    7    ${POST_TN}
    Log Reporting    TN number is displayed.
    Log Reporting    ${TEST_NAME} Health Check is verified.

Logout CRIS Application
    Send PF    1
    Sleep    2s 

Logout CAMS Application
    Send PF    3
    Send PF    1
    Sleep    2s 

Capture Screen
    [Arguments]    ${ssname}
    ${suite_source}    Get Variable Value    ${SUITE SOURCE}
    ${file_name}    Evaluate    os.path.basename($suite_source).split('.')[0]
    Screenshot.Take Screenshot    ${file_name}_${ssname}

Suite Setup for TPX_AB
    Open Connection    ${TPX_AB}
    Create Directory    ${FOLDER}
    Set Screenshot Directory    ${FOLDER}
    Change Wait Time    0.4
    Change Wait Time After Write    0.4
    Sleep    3s

Suite Setup for TPX_BC
    Open Connection    ${TPX_BC}
    Create Directory    ${FOLDER}
    Set Screenshot Directory    ${FOLDER}
    Change Wait Time    0.4
    Change Wait Time After Write    0.4
    Sleep    3s

    

