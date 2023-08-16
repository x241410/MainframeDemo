Feature: BPED Apps HealthCheck

  Background: 
    Given test data configuration for "Hybrid Framework Mainframe Automation"

  @Mainframe @ALLAPPS @SOECS @P2-APPS
  Scenario: Service Order Entry and Control System
    Given Test "SOECS" Applications