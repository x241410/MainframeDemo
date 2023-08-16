Feature: BPED Apps HealthCheck

  Background: 
    Given test data configuration for "Hybrid Framework Mainframe Automation"

  @Mainframe @ALLAPPS @P1-APPS @CRISAB
  Scenario: Customer Records Information System 1 / Inquiry: AB
    Given Test "CRISAB" Applications
    
 	@Mainframe @ALLAPPS @CRIS3AB @P1-APPS
  Scenario: CRIS3: Customer Records Information System 3 / Service Order System: AB
    Given Test "CRIS3AB" Applications