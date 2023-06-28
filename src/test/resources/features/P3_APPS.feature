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
