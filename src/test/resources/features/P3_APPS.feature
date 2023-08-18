Feature: BPED Apps HealthCheck

  Background: 
    Given test data configuration for "BPED Apps HealthCheck"

  #================
  #P3 Priority Apps
  #================
  @MileageCalculator @ALLAPPS @P3-APPS
  Scenario: Mileage Calculator
    #Given launch Application Mileage Calculator
    Given user open "MILEAGECALCULATOR" Application
    Then verify Mileage Calculator Homepage is displayed
    Then perform search from Mileage Calculator
    And verify search from Mileage Calculator

  @OrderInquiry @ALLAPPS @P3-APPS
  Scenario: Order Inquiry
    Given user login into "OrderInquiry"
    Then verify OrderInquiry Homepage is displayed
    Then perform search from OrderInquiry

  @CustomerFulfillment @ALLAPPS @P3-APPS
  Scenario: Customer Fulfillment
    Given user login into "CustomerFulfillment"
    Then verify CustomerFulfillment Homepage is displayed
    And verify product Type populated

  @CSBA @ALLAPPS @P3-APPS @NAPI
  Scenario: CSBA
    Then verify CSBA WebService response
    
	@MITS @ALLAPPS @P3-APPS
  Scenario: MITS Reporting
    Then verify MITS Reporting DB

  @testLynx
  Scenario: Lynx Ticket
    Given user login into "lynx"
    Then verify lynx homepage

	@Mainframe @ALLAPPS @P3-APPS @PAPSAB
  Scenario: PreAuthorized Payment System: AB
    Given Test "PAPSAB" Applications
    
  @Mainframe @ALLAPPS @P3-APPS @FPOAB
  Scenario: Flexible Payment Options: AB
    Given Test "FPOAB" Applications
    
  @Mainframe @ALLAPPS @P3-APPS @PAPSBC
  Scenario: PreAuthorized Payment System: BC
    Given Test "PAPSBC" Applications
    
  @Mainframe @ALLAPPS @P3-APPS @FPOBC
  Scenario: Flexible Payment Options: BC
    Given Test "FPOBC" Applications
