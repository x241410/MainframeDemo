*** Settings ***
Library           ../../Mainframe3270/
Library           Dialogs
Library           OperatingSystem
Library           String
Library           Screenshot

*** Keywords ***
Connect to region
    [Arguments]    ${region}
    Write Bare    ${region}
    Send Enter

Login Into Region
    [Arguments]    ${username}    ${password}    ${ypos}    ${xpos}    ${pxpos}
    Write Bare In Position    ${username}    ${ypos}    ${xpos}
    Write Bare In Position    ${password}    ${ypos}    ${pxpos}
    Send Enter

Login Into Application
    [Arguments]    ${username}    ${password}
    Write Bare    ${username}
    Write Bare    ${password}
    Send Enter

Write Text
    [Arguments]    ${text}
    Write Bare    ${text}

Write Text on specific position
    [Arguments]    ${txt}    ${ypos}    ${xpos}
    Write Bare In Position    ${txt}    ${ypos}    ${xpos}

Write Text and Enter
    [Arguments]    ${text}
    Write Bare    ${text}
    Send Enter

Write Text and Move to next field
    [Arguments]    ${text}
    Write Bare    ${text}
    Move Next Field

Read Text
    [Arguments]    ${ypos}    ${xpos}    ${length}
    ${string}    Read    ${ypos}    ${xpos}    ${length}
    [return]  ${string}
 
Compare Two String
    [Arguments]    ${Actual}    ${Expected} 
    Should Be Equal As Strings    ${Actual}    ${Expected}


Add Two String value
    [Arguments]    ${str1}    ${str2}
    ${string}=    evaluate    ${str1}${str2}
    [return]  ${string}

Log Reporting
    [Arguments]    ${LogMessage}
    Log To Console    ${\n}${LogMessage}

Capture Screenshot
    [Arguments]    ${screenshotName}  
    Screenshot.Take Screenshot    ${screenshotName}

Assertion Fail
    [Arguments]    ${failMessage}
    Fail    ${failMessage}

Verify actual and expected string
    [Arguments]    ${ypos}    ${xpos}    ${length}    ${expectedStr}
    ${actualStr}    Read    ${ypos}    ${xpos}    ${length}
    Log To Console    ${\n}Actual value: ${actualStr} and Expected value: ${expectedStr}
    Should Be Equal As Strings    ${actualStr}    ${expectedStr}

Get Current File Name Without Extension
    ${suite_source}    Get Variable Value    ${SUITE SOURCE}
    ${file_name}    Evaluate    os.path.basename($suite_source).split('.')[0]
    [Return]    ${file_name}

Verify Page Contain Text
    [Arguments]    ${text}
    Wait Field Detected
    Page Should Contain Any String    ${text}
    Log To Console    ${\n}Page contains expected value ${text}

Verify actual and expected string without log
    [Arguments]    ${ypos}    ${xpos}    ${length}    ${expectedStr}
    ${actualStr}    Read    ${ypos}    ${xpos}    ${length}
    Should Be Equal As Strings    ${actualStr}    ${expectedStr}