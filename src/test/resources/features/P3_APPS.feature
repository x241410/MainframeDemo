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

  #================
  #Mainframe Apps
  #================
  
  @Mainframe @ALLAPPS @CAMSAB @P3-APPS-MF
  Scenario: CAMS: AB
    Given Test "CAMSAB" Applications
    
  @Mainframe @ALLAPPS @CAMSBC @P3-APPS-MF
  Scenario: CAMS: BC
    Given Test "CAMSBC" Applications
    
  @Mainframe @ALLAPPS  @TRIADAB @P3-APPS-MF
  Scenario: Triad: AB
    Given Test "CAMSAB" Applications

	@Mainframe @ALLAPPS @TRIADBC @P3-APPS-MF
  Scenario: Triad: BC
    Given Test "CAMSBC" Applications
    
	@Mainframe @ALLAPPS @P3-APPS-MF @PAPSAB
  Scenario: PreAuthorized Payment System: AB
    Given Test "PAPSAB" Applications
    
  @Mainframe @ALLAPPS @P3-APPS-MF @FPOAB
  Scenario: Flexible Payment Options: AB
    Given Test "FPOAB" Applications
    
  @Mainframe @ALLAPPS @P3-APPS @PAPSBC
  Scenario: PreAuthorized Payment System: BC
    Given Test "PAPSBC" Applications
    
  @Mainframe @ALLAPPS @P3-APPS-MF @FPOBC
  Scenario: Flexible Payment Options: BC
    Given Test "FPOBC" Applications
 ##AB
  @Mainframe	@P3-APPS-MF @CAPSAB
  Scenario: Customer Activity Processing System: AB
    Given Test "CRISAB" Applications
		Given Test "CRIS3AB" Applications
		Given Test "CAMSAB" Applications
		
	@Mainframe	@P3-APPS-MF @CUBSAB
  Scenario: Customized Utility Billing System: AB
    Given Test "CRISAB" Applications
		Given Test "CRIS3AB" Applications
		Given Test "CAMSAB" Applications
		
	@Mainframe	@P3-APPS-MF @PUBSAB
  Scenario: Printer Utility BMP System: AB
    Given Test "CRISAB" Applications
		Given Test "CRIS3AB" Applications
		Given Test "CAMSAB" Applications
		
	@Mainframe	@P3-APPS-MF @SECABSAB
  Scenario: Small Exchange Carrier Access Billing System: AB
    Given Test "CRISAB" Applications
		Given Test "CRIS3AB" Applications
		Given Test "CAMSAB" Applications
		
		
		##BC
	@Mainframe	@P3-APPS-MF @ABCDBC
  Scenario: Accurate Business Customer Data
    Given Test "CRISBC" Applications
		Given Test "CRIS3BC" Applications
		Given Test "CAMSBC" Applications

	@Mainframe	@P3-APPS-MF @BSOBC
  Scenario: Bulk Service Order
    Given Test "CRISBC" Applications
		Given Test "CRIS3BC" Applications
		Given Test "CAMSBC" Applications
		
	@Mainframe	@P3-APPS-MF @CAPSBC
  Scenario: Customer Activity Processing System: BC
    Given Test "CRISBC" Applications
		Given Test "CRIS3BC" Applications
		Given Test "CAMSBC" Applications
		
		
	@Mainframe	@P3-APPS-MF @CUBSBC
  Scenario: Customized Utility Billing System: BC
    Given Test "CRISBC" Applications
		Given Test "CRIS3BC" Applications
		Given Test "CAMSBC" Applications
		
	@Mainframe	@P3-APPS-MF @EBBC
  Scenario: E911: Billing
    Given Test "CRISBC" Applications
		Given Test "CRIS3BC" Applications
		Given Test "CAMSBC" Applications
		
		
	@Mainframe	@P3-APPS-MF	@MASBBC
  Scenario: Major Accounts Summary Billing
    Given Test "CRISBC" Applications
		Given Test "CRIS3BC" Applications
		Given Test "CAMSBC" Applications
		
		
	@Mainframe	@P3-APPS-MF	@MCRBC
  Scenario: Marketing Cross Reference
    Given Test "CRISBC" Applications
		Given Test "CRIS3BC" Applications
		Given Test "CAMSBC" Applications
		
		
	@Mainframe	@P3-APPS-MF @NCAMSBC
  Scenario: National Carrier Access Management System
    Given Test "CRISBC" Applications
		Given Test "CRIS3BC" Applications
		Given Test "CAMSBC" Applications
		
	@Mainframe	@P3-APPS-MF @PUBSIIBC
  Scenario: Printer Utility BMP System II: BC
    Given Test "CRISBC" Applications
		Given Test "CRIS3BC" Applications
		Given Test "CAMSBC" Applications
		
	@Mainframe	@P3-APPS-MF @TRIHSBC
  Scenario: Trouble Reporting Information Handling System
    Given Test "CRISBC" Applications
		Given Test "CRIS3BC" Applications
		Given Test "CAMSBC" Applications
