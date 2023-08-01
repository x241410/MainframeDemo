*** Variables ***
${VISIBLE}               True
${TPX_AB}             sys1tcp.tsl.telus.com
${TPX_BC}             bct1tcp.tsl.telus.com
${HOST_soecs}            soecs.tsl.telus.com
#${FOLDER}    		 	${CURDIR}
${FOLDER}               ${CURDIR}${/}screenshots

# Text to write
${AB_REGION}    imsc
${REG_USERNAME}
${REG_PASSWORD}

${SELECT_NEWS}    s
# Texts in the Mainframe
${WELCOME}        PLEASE ENTER THE NAME OF YOUR APPLICATION
${WELCOME_TITLE}    PLEASE ENTER THE NAME OF YOUR APPLICATION


${APP_USERNAME}
${APP_PASSWORD}

${SOECS_username}    
${SOECS_password}   

${CRIS_APP_TITLE}    CRIS BULLETIN
${CRIS_APP_LOGINPAGE}    Customer Records Information System
${CAMS_COLLECTION_SCREEN_PAGE}    Collection Screen


#For BCTIMSC
${LOGIN_ALERT}    SIGN COMMAND COMPLETED
${REGION_TITLE}    Application Required
${BC_REGION}    bctimsc
${REGION_LOGIN_TITLE}    BCTEL  SIGNON  SCREEN
${BC_PRE_TN}    604
${BC_POST_TN}    2731287
${BC_TN}=    evaluate    ${BC_PRE_TN}${BC_POST_TN}
${SCREEN}    bsc
${MONTH}    0
${SCREEN_TITLE}    **(BSC) basic information** 
