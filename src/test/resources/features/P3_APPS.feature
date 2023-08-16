Feature: BPED Apps HealthCheck

  Background: 
    Given test data configuration for "Hybrid Framework Mainframe Automation"

	@Mainframe @ALLAPPS @CAMSAB @P3-APPS
  Scenario: CAMS: AB
    Given Test "CAMSAB" Applications
    
  @Mainframe @ALLAPPS @CAMSBC @P3-APPS
  Scenario: CAMS: BC
    Given Test "CAMSBC" Applications
    
   

