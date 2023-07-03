Feature: BPED Apps HealthCheck

  Background: 
    Given test data configuration for "BPED Apps HealthCheck"

  #================
  #P1 Priority Apps
  #================
  @RRW @ALLAPPS @P1-APPS
  Scenario: Residential Resellers
    Given user login into "RRW"
    Then verify RRW Homepage is displayed
    Then perform search from RRW
    Then logout from "RRW"

  @VPOP @ALLAPPS @P1-APPS @CONTROL
  Scenario: VPOP - Batch
    Then verify "VPOP-INTERNAL" Jobs in ControlM

  @VPOP @ALLAPPS @P1-APPS
  Scenario: VPOP - INTERNAL
    Given user login into "VPOP-INTERNAL"
    Then verify VPOP-Internal Homepage is displayed
    Then perform search from VPOP-Internal

  @VPOP @ALLAPPS @P1-APPS
  Scenario: VPOP - EXTERNAL
    Given user login into "VPOP-EXTERNAL"
    Then verify VPOP-External Homepage is displayed
    Then perform search from VPOP-External
    Then logout from "VPOP-External"

  @OST @ALLAPPS @P1-APPS
  Scenario: Order Status Tool
    Given user login into "OST"
    Then verify OST Homepage is displayed
    Then verify all header link is working of OST
    Then logout from "OST"

  @OST @ALLAPPS @P1-APPS @CONTROL
  Scenario: Order Status Tool - Batch
    Then verify "OST" Jobs in ControlM

  @LSR @ALLAPPS @P1-APPS @LSRUI
  Scenario: Local Service Request
    Given user login into "LSR"
    Then verify LSR Homepage is displayed
    Then perform search from LSR
    Then logout from "LSR"

  @LSR @ALLAPPS @P1-APPS @CONTROL
  Scenario: Local Service Request - Batch
    Then verify "LSR" Jobs in ControlM

  @REX @ALLAPPS @P1-APPS
  Scenario: Rebiller
    Given user login into "REX"
    Then verify REX Rebiller Express Homepage is displayed
    Then perform search from REX
    Then logout from "REX"

  @REX @ALLAPPS @P1-APPS @CONTROL
  Scenario: Rebiller - Batch
    Then verify "REX" Jobs in ControlM

  @AAB @ALLAPPS @P1-APPS @CONTROL
  Scenario: Assurance Account Batch
    Then verify "AAB" Jobs in ControlM

  @LEGACY-IVS @ALLAPPS @P1-APPS
  Scenario: Evolve Voice Services - Legacy
    Given user login into "LEGACY-IVS"
    Then verify Legacy IVS Homepage is displayed
    Then perform search from Legacy IVS
    Then logout from "LEGACY-IVS"

  @IVS2-Internal @ALLAPPS @P1-APPS @IVS2-UI
  Scenario: Evolve Voice Services - INTERNAL
    Given user login into "IVS2-Internal"
    Then verify IVS2-Internal Homepage is displayed
    Then perform search from IVS2 Upgrade Internal
    Then logout from "IVS2-Internal"

  @IVS2-EXTERNAL @ALLAPPS @P1-APPS @IVS2-UI
  Scenario: Evolve Voice Services - EXTERNAL
    Given user login into "IVS2-EXTERNAL"
    Then verify IVS2-EXTERNAL Homepage is displayed
    Then perform search from IVS2 Upgrade External
    Then logout from "IVS2-External"

  @CMS @ALLAPPS @P1-APPS
  Scenario: CMS Web Service
    Then verify CMS WebService response

  @KBG @ALLAPPS @P1-APPS
  Scenario: Kenan Billing Gateway
    Then verify "KBG" Jobs in ControlM

  @CLECOSS @ALLAPPS @P1-APPS
  Scenario: CLEC OSS
    Then verify CLEC OSS WebService response

  @KBP @P1-APPS
  Scenario: Kenan Billing Platform
    Given user login into "KBP"
    And verify KCB application links downloadable
    And Search For Customer In KBP
    
  @TFOS @ALLAPPS @P1-APPS
  Scenario: TollFreeOrderStatusRESTSvc
    Then verify TOLL FREE ORDER STATUS WebService response

  @CSOM @ALLAPPS @P1-APPS @NAPI
  Scenario: CRISServiceOrderManagementAPI
    Then verify CSOM WebService response
    
  @GNBE @ALLAPPS @P1-APPS
  Scenario: GOnet Billing Engine
    Given user login into "GNBE"
    Then verify GOnet Billing Engine Homepage is displayed
    Then verify all servers are running
    
  @Mainframe @ALLAPPS @P1-APPS
  Scenario: Customer Records Information System 1 / Inquiry: AB
    Given Test Mainframe Applications
    
  @Mainframe @ALLAPPS @SOECS @P1-APPS
  Scenario: Service Order Entry and Control System
    Given Test SOECS Applications
   