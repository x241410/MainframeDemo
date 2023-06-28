Feature: BPED Apps HealthCheck

  Background:
    Given test data configuration for "BPED Apps HealthCheck"

  #================
  #P2 Priority Apps
  #================
  @BLIF @ALLAPPS @P2-APPS
  Scenario: BLIF
    Given user login into "BLIF"
    Then verify BLIF Homepage is displayed
    Then perform search from BLIF
    Then logout from "BLIF"

  @BLIF @ALLAPPS @P2-APPS @CONTROL
  Scenario: BLIF - Batch
    Then verify "BLIF" Jobs in ControlM

  @LDORS @ALLAPPS @P2-APPS
  Scenario: LDORS
    Given user login into "LDORS"
    Then verify LDORS Homepage is displayed
    Then perform search from LDORS

  @LDORS @ALLAPPS @P2-APPS @CONTROL
  Scenario: LDORS - Batch
    Then verify "LDORS" Jobs in ControlM

  @DAS @ALLAPPS @P2-APPS @CONTROL
  Scenario: Directory Assistance System
    Then verify "DAS" Jobs in ControlM

  @ART @ALLAPPS @P2-APPS
  Scenario: ASR Reporting Tool
    Given user login into "ART"
    Then verify ART ASR Reporting Tool Homepage is displayed
    Then perform search from ART
    Then logout from "ART"

  @ECB @ALLAPPS @P2-APPS @CONTROL
  Scenario: Enterprise Contract Builder - Batch
    Then verify "ECB" Jobs in ControlM

  @CS @ALLAPPS @P2-APPS
  Scenario: Contract Suite: Partner
    Given user login into "CONTRACT-SUITE"
    Then verify Contract Suite Homepage is displayed
    Then perform search from Contract Suite
    Then logout from "CONTRACT-SUITE"

  @TLC @ALLAPPS @P2-APPS
  Scenario: TLC: Termination Liability Charge
    Given user login into "TLC"
    Then verify TLC Homepage is displayed
    Then perform search from TLC
    Then logout from "TLC"

  @HUMBOLDT @ALLAPPS @P2-APPS
  Scenario: Humboldt
    Given user login into "HUMBOLDT"
    Then verify HUMBOLDT Homepage is displayed
    Then perform search from HUMBOLDT
    Then logout from "HUMBOLDT"

  @TBBA @ALLAPPS @P2-APPS
  Scenario: TELUS Batch Bill Analyzer
    Then verify "TBBA" Jobs in ControlM

   @XMLAPPLICATION @ALLAPPS @P2-APPS
   Scenario: XML Gateway
     Given user open "XML-GATEWAY" Application
     Then verify XM Gateway application Homepage

#  @BAT @ALLAPPS @P2-APPS
#  Scenario: Bill Analysis Tool
#    Then verify "BAT" Jobs in ControlM

  @CAM @ALLAPPS @P2-APPS @NAPI
  Scenario: CoreAccountManagementAPI
    Then verify CAM WebService response

  @WFMA @ALLAPPS @P2-APPS @PROD-Phase1
  Scenario: WFMA
    Given user login into "WFMA"
    Then verify WFMA Homepage is displayed
    And verify messaging bridge has Forwarding messages

@APPIAN @ALLAPPS @P2-APPS
  Scenario: Appian
    Given user login into "APPIAN"
    Then verify Appian Homepage is displayed
    Then verify link from Appian
    Then logout from "APPIAN"

@OCP @ALLAPPS @P2-APPS @PROD-Phase1
  Scenario: Outage Calculation Program
     Given user login into "OCP1"
     And verify OCP1 Homepage is displayed
     Then search OCP1 outageInformation
     And verify OCP1 Search outage Information
     Given user open "OCP2" Application
     And verify OCP2 Homepage is displayed
     Then logged in OCP2 prod database
     Then Navigate OCP2 Monthly RollUps
     And verify OCP2 Monthly RollUps



  @ECB @P2-APPS
  Scenario: Enterprise Contract Builder
    Given user login into "ECB"
    Then verify ECB application logged in successfully
     And verify ECB application links downloadable
    And verify ECB Customer Search
#    Then logout from "ECB"

@CoreBillingAccountMgmt  @P2-APPS @PROD-Phase1 @CBAM @CoreAccountManagementV1 @ALLAPPS
  Scenario: CoreBillingAccountMgmt
    Given user open "DYNATRACE" Application
    Then  user logInTo DYNATRACE Application
    And verify DYNATRACE application logged in successfully
    Then reset DYNATRACE Application
    And Navigate to services in DYNATRACE
    And select CoreBillingAccountMgmtAPIV1 & verify Failure Rate

@BRS  @P2-APPS @PROD-Phase1 @ALLAPPS
  Scenario: Bill Retrieval System
    Given user login into "BRS"
    Then verify BRS application logged in successfully
    And search BRS Pilot Number
    Then verify BRS retrieve the bill
