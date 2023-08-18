*** Variables ***
${VISIBLE}               True
${TPX_AB}             sys1tcp.tsl.telus.com
${TPX_BC}             bct1tcp.tsl.telus.com
${HOST_soecs}            soecs.tsl.telus.com
${FOLDER}               ${CURDIR}${/}screenshots

# Region details
${REGION}
${REG_USERNAME}
${REG_PASSWORD}
${SELECT_NEWS}    s
${WELCOME}        PLEASE ENTER THE NAME OF YOUR APPLICATION
${WELCOME_TITLE}    PLEASE ENTER THE NAME OF YOUR APPLICATION

#Application Details
${APP_USERNAME}
${APP_PASSWORD}
${SOECS_username}
${SOECS_password}
${CRIS_APP_TITLE}    CRIS BULLETIN
${CRIS_APP_LOGINPAGE}    Customer Records Information System

#CAMS TC
${CAMS_COLLECTION_SCREEN_PAGE}    Collection Screen
${CAMS_SCREEN}    swi

#For BCTIMSC
${LOGIN_ALERT}    SIGN COMMAND COMPLETED
${REGION_TITLE}    Application Required
${REGION_LOGIN_TITLE}    BCTEL
${BC_PRE_TN}    604
${SCREEN}    bsc
${MONTH}    0
${SCREEN_TITLE}    **(BSC) basic information**

#For IMSC
${AB_PRE_TN}    403
${POST_TN}


# For SOECS
@{SOECS_LOGINPAGE}    Welcome to OpenVMS   
@{SOECS_HOMEPAGE}    TELUS COMMUNICATIONS INC.

#For FLEX
${FLEX_SCREEN}    FLX

#For Pre Authorised payment system
${PAPD_SCREEN}    PRE-AUTHORIZED PAYMENT DETAIL
${START_PAPD}    PAPD
${PAPI_SCREEN}    Pre-Authorized Payment Input/Inquiry
${START_PAPI}    PAPI
${INQ_SCREEN}    INQ