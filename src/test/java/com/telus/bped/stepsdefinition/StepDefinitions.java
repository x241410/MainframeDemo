package com.telus.bped.stepsdefinition;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.intuit.karate.core.FeatureResult;
import com.telus.api.test.utils.APIJava;
import com.telus.bped.steps.*;

import com.telus.bped.utils.DBUtils;
import com.telus.bped.utils.GenericUtils;
import com.telus.bped.utils.GoogleSheetData;
import com.telus.bped.utils.GoogleSheetsUtils;
import com.telus.bped.utils.MainframeUtils;
import com.test.files.interaction.ReadJSON;
import com.test.reporting.Reporting;
import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.BaseTest;
import com.test.ui.actions.Validate;
import com.test.ui.actions.WebDriverSession;
import com.test.ui.actions.WebDriverSteps;
import com.test.utils.EncryptionUtils;
import com.test.utils.Status;
import com.test.utils.SystemProperties;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.sikuli.basics.Settings;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.telus.bped.steps.LoginPageSteps.userAccessVar;

/**
 * ***************************************************************************
 * DESCRIPTION: This class contains the steps implementations for the BPED
 * application smoke tests AUTHOR: x241410
 * ***************************************************************************
 */

public class StepDefinitions extends BaseTest {

    public static JSONArray mainframeAppStatus = null;
    public static JSONArray mainframeCrisStatus = null;
    public static JSONArray mainframeSoecsStatus = null;
    static JSONObject appNames = null;
    String testCaseDescription = null;
    String environment = null;
    String dataFilePath = null;
    JSONObject commonDataJson = null;
    JSONObject commonDataVar = null;
    String lsrSearch = null;
    String ivs2Search = null;
    String cmsCustomerId = null;
    String csomOrderKey = null;
    String clecoss = null;
    String orderInquiryOrderNumber = null;
    String contarctNum = null;
    JSONObject dbAuthVar = null;
    String BPMPR_host = null;
    String BPMPR_port = null;
    String BPMPR_serviceName = null;
    String BPMPR_username = null;
    String BPMPR_password = null;
    LoginPageSteps loginPageSteps = new LoginPageSteps();

    public static String getJobsFolder(String appName) {

        String applicationName = appName.toUpperCase();

        if (applicationName.contains("VPOP")) {
            applicationName = ReadJSON.getString(appNames, "VPOP");

        } else {
            applicationName = ReadJSON.getString(appNames, applicationName);
        }
        return EncryptionUtils.decode(applicationName);
    }

    public static void captureScreenshots(String fileName) {

        try {
            String ssPath = SystemProperties.getStringValue("mainframe.build.report.artifact.path");
            String ssDirectory = System.getProperty("user.dir") + "/" + ssPath;
            File f = new File(ssDirectory);
            GenericUtils.getAllImagesHelper(f, fileName);
        } catch (IOException e) {
            Reporting.logReporter(Status.DEBUG, "Unable to capture screenshots");
        }
    }

    @Given("test data configuration for {string}")
    public void test_data_configuration_for(String scriptName) {
        testCaseDescription = "The purpose of this test case is to verify \"" + scriptName + "\" workflow.";
        environment = SystemProperties.EXECUTION_ENVIRONMENT;
//        dataFilePath = "\\TestData\\" + "BPEDTestData.json";
//        JSONObject jsonFileObjectForDetails = GenericUtils.getJSONObjectFromJSONFile(dataFilePath);
        GoogleSheetsUtils googleSheetsUtils = new GoogleSheetsUtils();
        try {
            String commonData = googleSheetsUtils.getKeyValue("BPED_TEST_DATA", true);
            commonDataVar = googleSheetsUtils.getJSONObjectFromGit(commonData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        commonDataJson = commonDataVar.getJSONObject(environment);
        appNames = commonDataVar.getJSONObject("JOBSFOLDER");
        lsrSearch = EncryptionUtils.decode(commonDataJson.getString("LSR_SEARCH"));
        contarctNum = EncryptionUtils.decode(commonDataJson.getString("HUMBOLDT_CONTRACT_NUMBER"));
        ivs2Search = EncryptionUtils.decode(commonDataJson.getString("IVS2_SEARCH"));
        orderInquiryOrderNumber = EncryptionUtils.decode(commonDataJson.getString("OrderInquiry_OrderNumber"));
        cmsCustomerId = EncryptionUtils.decode(commonDataJson.getString("CMS_CUSTOMER_ID"));
        csomOrderKey = EncryptionUtils.decode(commonDataJson.getString("CSOM_TestData"));

        clecoss = EncryptionUtils.decode(commonDataJson.getString("CLECOSS_TestData"));

        Reporting.logReporter(Status.INFO,
                "Automation Configuration - Environment Configured for Automation Execution [" + environment + "]");
        Reporting.logReporter(Status.INFO, "Test Case Name : [" + scriptName + "]");
        Reporting.logReporter(Status.INFO, "Test Case Description : [" + testCaseDescription + "]");

    }

    @Given("user login into {string}")
    public void user_login_into(String applicationName) {
        loginPageSteps.appLogin(applicationName);
    }

    @Given("user open {string} Application")
    public void user_openApprication(String applicationName) {
        loginPageSteps.openApplication(applicationName);
    }

    @Then("user logInTo DYNATRACE Application")
    public void logInToDynaTrace() {
        CoreBillingAccountMgmtPageSteps.clickSSO();
        loginPageSteps.singInToApplication("DYNATRACE");
        BaseSteps.Waits.waitForAjaxLoaderSpinnerInvisibility(1, 10);
    }

    @Then("verify DYNATRACE application logged in successfully")
    public void verifyDynaTraceHomeScreen() {
        CoreBillingAccountMgmtPageSteps.verifyLoginSucceeded();
    }

    @Then("verify BLIF Homepage is displayed")
    public void verify_blif_homepage_is_displayed() {
        BLIFPageSteps BLIFSteps = new BLIFPageSteps();
        BLIFSteps.verifyHomePage();
    }

    @Then("verify XM Gateway application Homepage")
    public void verify_XMLgateWay_homepage_is_displayed() {
        XMLGatewayPageSteps xMLGatewayPageSteps = new XMLGatewayPageSteps();
        xMLGatewayPageSteps.verifyXMLGatewayApplicationHomePage();
    }

    @Then("perform search from BLIF")
    public void perform_search_from_blif() {
        BLIFPageSteps BLIFSteps = new BLIFPageSteps();
        BLIFSteps.performSearchFromBlif();
    }

    @Then("verify LDORS Homepage is displayed")
    public void verify_ldors_homepage_is_displayed() {
        LDORSPageSteps LDORSSteps = new LDORSPageSteps();
        LDORSSteps.verifyHomePage();
    }

    /*
     *
     * CONTROl M Step definitions
     *
     *
     */

    @Then("perform search from LDORS")
    public void perform_search_from_ldors() {
        LDORSPageSteps LDORSSteps = new LDORSPageSteps();
        LDORSSteps.performSearchFromLDORS();
    }

    @Then("verify VPOP-Internal Homepage is displayed")
    public void verify_vpop_internal_homepage_is_displayed() {
        VPOPPageSteps VPOPSteps = new VPOPPageSteps();
        VPOPSteps.verifyVPOPInternalHomePage();
    }

    @Then("perform search from VPOP-Internal")
    public void perform_search_from_vpop_internal() {
        VPOPPageSteps VPOPSteps = new VPOPPageSteps();
        VPOPSteps.performSearchFromVPOPInternal();
    }

    @Then("verify RRW Homepage is displayed")
    public void verify_rrw_homepage_is_displayed() {
        RRWPageSteps RRWSteps = new RRWPageSteps();
        RRWSteps.verifyHomePage();
    }

    @Then("perform search from RRW")
    public void perform_search_from_rrw() {

        RRWPageSteps RRWSteps = new RRWPageSteps();
        RRWSteps.performSearchFromRRW();

    }

    @Then("verify REX Rebiller Express Homepage is displayed")
    public void verify_rex_homepage_is_displayed() {
        REXPageSteps rexSteps = new REXPageSteps();
        rexSteps.verifyREXHomePage();
    }

    @Then("perform search from REX")
    public void search_from_rex() {
        REXPageSteps rexSteps = new REXPageSteps();
        rexSteps.searchREX();
    }

    @Then("verify IVS2-Internal Homepage is displayed")
    public void verify_IVS2Internal_homepage_is_displayed() {
        IVSPageSteps ivs2ExternalSteps = new IVSPageSteps();
        ivs2ExternalSteps.verifyIVS2InternalHomePage();
    }

    @Then("perform search from IVS2 Upgrade Internal")
    public void search_from_IVS2_Internal() {
        IVSPageSteps ivs2ExternalSteps = new IVSPageSteps();
        ivs2ExternalSteps.performSearchFromIVS2Internal();
    }

    @Then("verify IVS2-EXTERNAL Homepage is displayed")
    public void verify_IVS2External_homepage_is_displayed() {
        IVSPageSteps ivs2ExternalSteps = new IVSPageSteps();
        ivs2ExternalSteps.verifyIVS2ExternalHomePage();
    }

    @Then("perform search from IVS2 Upgrade External")
    public void search_from_IVS2_External() {
        IVSPageSteps ivs2ExternalSteps = new IVSPageSteps();
        ivs2ExternalSteps.searchIVS2External();
    }

    @Then("verify VPOP-External Homepage is displayed")
    public void verify_VPOP_External_homepage_is_displayed() {
        VPOPPageSteps vpopSteps = new VPOPPageSteps();
        vpopSteps.verifyVPOP_ExternalHomePage();
    }

    @Then("perform search from VPOP-External")
    public void search_from_VPOP_External() {
        VPOPPageSteps vpopSteps = new VPOPPageSteps();
        vpopSteps.searchVPOPExternal();
    }

    @Then("verify ART ASR Reporting Tool Homepage is displayed")
    public void verify_ART_homepage_is_displayed() {
        ARTPageSteps ARTSteps = new ARTPageSteps();
        ARTSteps.verifyARTHomePage();
    }

    @Then("perform search from ART")
    public void search_from_ART() {
        ARTPageSteps ARTSteps = new ARTPageSteps();
        ARTSteps.performSearchFromART();
    }

    @Then("verify Contract Suite Homepage is displayed")
    public void verify_CS_homepage() {
        CONTRACTSUITEPageSteps CSSteps = new CONTRACTSUITEPageSteps();
        CSSteps.verifyCSHomePage();
    }

    @Then("perform search from Contract Suite")
    public void search_from_CS() {
        CONTRACTSUITEPageSteps CSSteps = new CONTRACTSUITEPageSteps();
        CSSteps.performSearchFromCS();
    }

    @Then("verify Legacy IVS Homepage is displayed")
    public void verify_LegacyIVS_homepage() {
        IVSPageSteps IVSPageSteps = new IVSPageSteps();
        IVSPageSteps.verifyLegacyIVSHomePage();
    }

    @Then("perform search from Legacy IVS")
    public void search_from_LegacyIVS() {
        IVSPageSteps IVSPageSteps = new IVSPageSteps();
        IVSPageSteps.performSearchFromLegacyIVS(ivs2Search);
    }

    @Then("verify TLC Homepage is displayed")
    public void verify_TLC_homepage() {
        TLCPageSteps TLCPageSteps = new TLCPageSteps();
        TLCPageSteps.verifyTLCHomePage();
    }

    @Then("perform search from TLC")
    public void search_from_TLC() {
        TLCPageSteps TLCPageSteps = new TLCPageSteps();
        TLCPageSteps.performSearchFromTLC();
    }

    @Then("verify HUMBOLDT Homepage is displayed")
    public void verify_HUMBOLDT_homepage() {
        HUMBOLDTPageSteps HUMBOLDTPageSteps = new HUMBOLDTPageSteps();
        HUMBOLDTPageSteps.verifyHumboldtHomePage();
    }

    @Then("perform search from HUMBOLDT")
    public void search_from_HUMBOLDT() {
        HUMBOLDTPageSteps HUMBOLDTPageSteps = new HUMBOLDTPageSteps();
        HUMBOLDTPageSteps.performSearchFromHUMBOLDT(contarctNum);
    }

    @Then("verify Appian Homepage is displayed")
    public void verify_Appian_homepage() {
        AppianPageSteps AppianPageSteps = new AppianPageSteps();
        AppianPageSteps.verifyHomePage();
    }

    @Then("verify link from Appian")
    public void verify_link_from_Appian() {
        AppianPageSteps AppianPageSteps = new AppianPageSteps();
        AppianPageSteps.verifySubscriptionPage();
    }

    @Then("verify OST Homepage is displayed")
    public void verify_OST_homepage() {
        OSTPageSteps OSTPageSteps = new OSTPageSteps();
        OSTPageSteps.verifyOSTHomePage();
    }

    @Then("verify all header link is working of OST")
    public void verify_header_link_of_OST() {
        OSTPageSteps OSTPageSteps = new OSTPageSteps();
        OSTPageSteps.verifyHeaderLink();
    }

    @Then("verify LSR Homepage is displayed")
    public void verify_LSR_homepage() {
        LSRPageSteps LSRPageSteps = new LSRPageSteps();
        LSRPageSteps.verifyLSRHomePage();
    }

    @Then("perform search from LSR")
    public void search_from_LSR() {
        LSRPageSteps LSRPageSteps = new LSRPageSteps();
        LSRPageSteps.performSearchFromLSR(lsrSearch);
    }

    @Given("launch Application Mileage Calculator")
    public void launch_App() {

        MileageCalculatorPageSteps MileageCalculatorPageSteps = new MileageCalculatorPageSteps();
        MileageCalculatorPageSteps.launchMileageCalculator();
    }

    @Then("verify Mileage Calculator Homepage is displayed")
    public void verify_MileageCalculator_homepage() {

        MileageCalculatorPageSteps MileageCalculatorPageSteps = new MileageCalculatorPageSteps();
        MileageCalculatorPageSteps.verifyHomePage();
    }

    @Then("perform search from Mileage Calculator")
    public void search_from_MileageCalculator() {
        MileageCalculatorPageSteps MileageCalculatorPageSteps = new MileageCalculatorPageSteps();
        MileageCalculatorPageSteps.performSearchFromMileageCalculator();
    }

    @And("verify search from Mileage Calculator")
    public void verify_search_from_MileageCalculator() {
        MileageCalculatorPageSteps MileageCalculatorPageSteps = new MileageCalculatorPageSteps();
        MileageCalculatorPageSteps.verifySearchMileageCalculator();
    }

    @Then("verify MITS Reporting DB")
    public void verify_MITS_DB() throws SQLException, IOException {

        GoogleSheetsUtils googleSheetsUtils = new GoogleSheetsUtils();
        try {
            String dbAuth = googleSheetsUtils.getKeyValue("BPMPR_DB_AUTH", true);
            dbAuthVar = googleSheetsUtils.getJSONObjectFromGit(dbAuth);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        JSONObject dbAuthJson = dbAuthVar.getJSONObject(environment);
        BPMPR_host = EncryptionUtils.decode(dbAuthJson.getString("host"));
        BPMPR_port = EncryptionUtils.decode(dbAuthJson.getString("port"));
        BPMPR_serviceName = EncryptionUtils.decode(dbAuthJson.getString("serviceName"));
        BPMPR_username = EncryptionUtils.decode(dbAuthJson.getString("BPMPR_username"));
        BPMPR_password = EncryptionUtils.decode(dbAuthJson.getString("BPMPR_password"));
        DBUtils dbUtils = new DBUtils();

        // dbUtils.dbConnect(BPMPR_host, BPMPR_port, BPMPR_serviceName, BPMPR_username,
        // BPMPR_password);
        Connection Conn = dbUtils.dbConnect(BPMPR_host, BPMPR_port, BPMPR_serviceName, BPMPR_username, BPMPR_password);
        String tableName = "TELUS_PDL_NEW";
        String query = "SELECT COUNT(*) FROM " + tableName;
        Statement statement = Conn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        resultSet.next();

        int rowCount = resultSet.getInt(1);

        if (rowCount > 1) {
            Reporting.logReporter(Status.INFO,
                    "The table '" + tableName + "' contains data and Total Records is " + rowCount);
        } else {
            Assert.fail("The table '" + tableName + "' does not contain data.");
        }
        // MITSPageSteps.verifyDB();

        dbUtils.callDBDisconnect();

    }

    @Then("logout from {string}")
    public void logout_from(String appName) {
        LoginPageSteps loginSteps = new LoginPageSteps();
        loginSteps.logoutApp(appName);
    }

    @Then("verify {string} Jobs in ControlM")
    public void verifyControlMJOBs(String applicationName) {
        testCallFeature(applicationName);
    }

    @Then("verify OrderInquiry Homepage is displayed")
    public void verify_orderInquiry_homepage_is_displayed() {
        ORDERINQUIRYPageSteps orderinquiryPageSteps = new ORDERINQUIRYPageSteps();
        orderinquiryPageSteps.verifyOrderInquiryHomePage();
    }

    @Then("perform search from OrderInquiry")
    public void perform_search_from_OrderInquiry() {
        ORDERINQUIRYPageSteps orderinquiryPageSteps = new ORDERINQUIRYPageSteps();
        orderinquiryPageSteps.performSearchFromOrderInquiry(orderInquiryOrderNumber);
    }

    @Then("verify GOnet Billing Engine Homepage is displayed")
    public void verify_GOnet_Billing_Engine_homepage_is_displayed() {
        GNBEPageSteps GNBEPageSteps = new GNBEPageSteps();
        GNBEPageSteps.isGNBEHomepageDisplayed();
    }

    @Then("verify all servers are running")
    public void verify_jobs_are_working() {
        GNBEPageSteps GNBEPageSteps = new GNBEPageSteps();
        GNBEPageSteps.openServerPage();
        GNBEPageSteps.verifyServers();
    }

    @After()
    public void update_gSheet(Scenario scenario) throws IOException, GeneralSecurityException {

        JSONObject statusObj = new JSONObject();

        statusObj.put("appName", scenario.getName());
        statusObj.put("appStatus", scenario.getStatus().toString());
        statusObj.put("executedAt", GenericUtils.getDateInMMDDYYYYHHMMSSInPST() + " (PST)");

        Collection<String> tags = scenario.getSourceTagNames();
        Reporting.logReporter(Status.INFO, "LogInfo===> tags: " + tags);
        if (tags.contains("@P1-APPS") || tags.contains("@P1-APPS-MF")) {
            statusObj.put("P1_APPS", "P1_APPS");
        } else if (tags.contains("@P2-APPS") || tags.contains("@P2-APPS-MF")) {
            statusObj.put("P2_APPS", "P2_APPS");
        } else if (tags.contains("@P3-APPS") || tags.contains("@P3-APPS-MF")) {
            statusObj.put("P3_APPS", "P3_APPS");
        }

        GoogleSheetData.writeScenarioStatus(statusObj);

    }

    // @Then("^call API {string} class$")
    public void testCallFeature(String applicationName) {
        String jobsFolder = getJobsFolder(applicationName);
        String[] jobsFolders = jobsFolder.split("_");
        int execount = jobsFolders.length;

        for (int i = 1; i <= execount; i++) {

            jobsFolder = jobsFolders[execount - 1];

            // Extract values (Must be declared in the feature as "def = variable" to be
            // able to retrieve them)

            Map<String, Object> apiOperation = new HashMap<>();
            Map<String, Object> apiOperation1 = new HashMap<>();
            Map<String, Object> apiOperation2 = new HashMap<>();
            Map<String, Object> apiOperationo = new HashMap<>();
            try {
                boolean jobFailBoolean = false;

                System.setProperty("karate.jobsFolderName" + java.lang.Thread.currentThread().getId(), jobsFolder);
                Reporting.logReporter(Status.INFO,
                        System.getProperty("karate.jobsFolderName" + java.lang.Thread.currentThread().getId()));
                Reporting.logReporter(Status.INFO, "Executing for Jobs Folder : " + jobsFolder);
                // setJobFolder(jobsFolder);// Possible with system property

                JSONObject creds = getControlMCred();

                System.setProperty("karate.cmusername", creds.getString("username"));
                System.setProperty("karate.cmpassword", creds.getString("password"));
                if (environment.equals("PROD")) {
                    System.setProperty("karate.ctm", "ctmpr");
                } else {
                    System.setProperty("karate.ctm", "ctmit04");
                }

                FeatureResult result = APIJava.runKarateFeatureReturnResultSet(environment,
                        "classpath:services/LoginControlM.feature");
                apiOperation = result.getResultAsPrimitiveMap();

                String token = apiOperation.get("token") + "";

                System.setProperty("karate.loginToken", token);
                // setToken(token); // possible with system property

                FeatureResult result1 = APIJava.runKarateFeatureReturnResultSet(environment,
                        "classpath:services/GetJobsID.feature");
                apiOperation1 = result1.getResultAsPrimitiveMap();

                int count = Integer.parseInt(apiOperation1.get("count").toString());

                if (count == 0) {
                    if (environment.equals("NON-PROD")) {
                        FeatureResult resulto = APIJava.runKarateFeatureReturnResultSet(environment,
                                "classpath:services/OrderJobs.feature");

                        Reporting.logReporter(Status.INFO, "Jobs Has Been Ordered for the folder : " + jobsFolder);

                        BaseSteps.Waits.waitGeneric(3000);

                        result1 = APIJava.runKarateFeatureReturnResultSet(environment,
                                "classpath:services/GetJobsID.feature");
                        apiOperation1 = result1.getResultAsPrimitiveMap();
                    }
                }

                String jobsIDs = apiOperation1.get("statuses").toString();
                count = Integer.parseInt(apiOperation1.get("count").toString());

                FeatureResult result2 = null;

                final JSONArray jsonArray = new JSONArray(jobsIDs);
                for (int ids = 0; ids < count; ids++) {
                    String jobName, jobID, jobStatus;
                    int runs;
                    jobName = jsonArray.getJSONObject(ids).getString("name");
                    jobID = jsonArray.getJSONObject(ids).getString("jobId");
                    runs = jsonArray.getJSONObject(ids).getInt("numberOfRuns");
                    jobStatus = jsonArray.getJSONObject(ids).getString("status");

                    if (jobStatus.equals("Ended OK")) {
                        Reporting.logReporter(Status.PASS,
                                "Job with Job Name : " + jobName + "Has Status As Ended OK.Hence It is Passed.");
                    } else if (jobStatus.equals("Ended Not OK")) {
                        Reporting.logReporter(Status.FAIL,
                                "Status for the Job : " + jobName + " is Not Ended Okay.Therefore is Fail");
                        jobFailBoolean = true;
                    } else {
                        if (runs == 0) {
                            Reporting.logReporter(Status.INFO, "Job with Job Name : " + jobName
                                    + " Has not been Executed and Has Run Count for 0. So, no output has been provided.");
                        } else {
                            System.setProperty("karate.JobName" + java.lang.Thread.currentThread().getId(), jobName);
                            System.setProperty("karate.JobID" + java.lang.Thread.currentThread().getId(), jobID);
                            System.setProperty("karate.Runs" + java.lang.Thread.currentThread().getId(), runs + "");
                            int run = 1;
                            System.setProperty("karate.CurrentRun" + java.lang.Thread.currentThread().getId(),
                                    run + "");
                            result2 = APIJava.runKarateFeatureReturnResultSet(environment,
                                    "classpath:services/GetRunStatus.feature");
                            apiOperation2 = result2.getResultAsPrimitiveMap();
                            String output, status = "";
                            output = apiOperation2.get("OutputResponse").toString();
                            if (output.contains("exit")) {
                                status = output.split(" exit ")[1].trim();
                            } else {
                                status = "Execution Stopped Abruptly and Did not End with any Exit Code";
                            }

                            if (status.equals("0")) {
                                Reporting.logReporter(Status.INFO, "Exit Code for the Job : " + jobName + " is : "
                                        + status + " And Therefore is  Pass");
                            } else {
                                jobFailBoolean = true;
                                Reporting.logReporter(Status.FAIL, "Exit Code for the Job : " + jobName + " is : "
                                        + status + " And Therefore is  Fail");
                            }
//                            Validate.assertEquals(status, "0", true);

                        }

                    }
                }
                if (jobFailBoolean) {
                    Validate.assertTrue(false, true, "Status Of Job  is Not Ended Ok.");
                }
                if (result.isFailed()) {
                    Reporting.logReporter(Status.FAIL, result.getErrorsCombined().toString());
                    Validate.assertTrue(false, true, "Result Of ControlM API is failed");

                }
                if (result1.isFailed()) {
                    Reporting.logReporter(Status.FAIL, result1.getErrorsCombined().toString());
                    Validate.assertTrue(false, true, "Result Of ControlM API is failed");

                }
                if (result2 != null && result2.isFailed()) {
                    Reporting.logReporter(Status.FAIL, result2.getErrorsCombined().toString());
                    Validate.assertTrue(false, true, "Result Of ControlM API is failed");

                }

            } catch (Exception e) {
                e.printStackTrace();
                Reporting.logReporter(Status.FAIL, e.getMessage());
                Validate.assertTrue(false, true, e.getMessage());

//            System.out.println(e.fillInStackTrace());
            }
        }
    }

    private JSONObject getControlMCred() {

        JSONObject jsonObject = new JSONObject();

        JSONObject userAccess = userAccessVar.getJSONObject(SystemProperties.EXECUTION_ENVIRONMENT);
        String username = ReadJSON.getString(userAccess, "ControlM_username");
        String password = ReadJSON.getString(userAccess, "ControlM_password");
        jsonObject.put("password", EncryptionUtils.decode(password));
        jsonObject.put("username", EncryptionUtils.decode(username));
        return jsonObject;
    }

    private JSONObject getSOAPAPICred() {

        JSONObject jsonObject = new JSONObject();

        JSONObject userAccess = userAccessVar.getJSONObject(SystemProperties.EXECUTION_ENVIRONMENT);
        String username = ReadJSON.getString(userAccess, "SOAPCMS_username");
        String password = ReadJSON.getString(userAccess, "SOAPCMS_password");
        jsonObject.put("password", EncryptionUtils.decode(password));
        jsonObject.put("username", EncryptionUtils.decode(username));
        return jsonObject;
    }

    private JSONObject getCAMAPICred() {

        JSONObject jsonObject = new JSONObject();

        JSONObject userAccess = userAccessVar.getJSONObject(SystemProperties.EXECUTION_ENVIRONMENT);
        String username = ReadJSON.getString(userAccess, "CAM_username");
        String password = ReadJSON.getString(userAccess, "CAM_password");

        jsonObject.put("password", EncryptionUtils.decode(password));
        jsonObject.put("username", EncryptionUtils.decode(username));
        return jsonObject;
    }

    private JSONObject getCSOMAPICred() {

        JSONObject jsonObject = new JSONObject();

        JSONObject userAccess = userAccessVar.getJSONObject(SystemProperties.EXECUTION_ENVIRONMENT);
        String username = ReadJSON.getString(userAccess, "CSOM_username");
        String password = ReadJSON.getString(userAccess, "CSOM_password");
        jsonObject.put("password", EncryptionUtils.decode(password));
        jsonObject.put("username", EncryptionUtils.decode(username));
        return jsonObject;
    }

    @Then("verify CMS WebService response")
    public void verifyCMSWebService() {

        JSONObject creds = getSOAPAPICred();
        System.setProperty("karate.soapusername", creds.getString("username"));
        System.setProperty("karate.soappassword", creds.getString("password"));

        System.setProperty("karate.cmsTestData", cmsCustomerId);

        Map<String, Object> apiOperation = new HashMap<>();

        FeatureResult result = APIJava.runKarateFeatureReturnResultSet(environment,
                "classpath:services/SoapApiRun.feature");
        apiOperation = result.getResultAsPrimitiveMap();
        String a = apiOperation.get("Response").toString();

        if (a.contains("contractId")) {
            Reporting.logReporter(Status.PASS, "Contracts Found for the Given Customer ID Hence it is passed.");

        } else {
            Reporting.logReporter(Status.FAIL, "Contracts Not Found for the Given Customer ID Hence it is Failed.");
            Validate.assertTrue(false, true, "Contracts not Found for the Customer ID.");

        }
    }

    @And("verify CLEC OSS WebService response")
    public void verifyCLECOSSWebService() {
        System.setProperty("karate.TestData", clecoss);
        Map<String, Object> apiOperation;
        FeatureResult result = APIJava.runKarateFeatureReturnResultSet(environment,
                "classpath:services/SoapApiRunCLEC.feature");
        apiOperation = result.getResultAsPrimitiveMap();
        String a = apiOperation.get("Response").toString();

        if (a.contains("OcnSpid")) {
            Reporting.logReporter(Status.PASS, "Records Found for the Details Hence it is passed.");
        } else {
            Reporting.logReporter(Status.FAIL, "Records Not Found for the Details Hence it is Failed.");
            Validate.assertTrue(false, true, "Records not Found for the Details Hence it is Failed.");
        }
    }

    private JSONObject getTFOSApiCred() {

        JSONObject jsonObject = new JSONObject();

        JSONObject userAccess = userAccessVar.getJSONObject(SystemProperties.EXECUTION_ENVIRONMENT);
        String username = ReadJSON.getString(userAccess, "TOLLFREEORDERSTATUS_username");
        String password = ReadJSON.getString(userAccess, "TOLLFREEORDERSTATUS_password");
        jsonObject.put("password", EncryptionUtils.decode(password));
        jsonObject.put("username", EncryptionUtils.decode(username));
        return jsonObject;
    }

    @Then("verify TOLL FREE ORDER STATUS WebService response")
    public void verifyTFOSWebService() {

        JSONObject TFOScreds = getTFOSApiCred();
        System.setProperty("karate.restusername", TFOScreds.getString("username"));
        System.setProperty("karate.restpassword", TFOScreds.getString("password"));
        Map<String, Object> apiOperation1 = new HashMap<>();

        FeatureResult result = APIJava.runKarateFeatureReturnResultSet(environment,
                "classpath:services/ApiRunTOLLFREEORDERSTATUS.feature");

        apiOperation1 = result.getResultAsPrimitiveMap();
        String a = apiOperation1.get("Response").toString();

        if (a.contains("buildDate")) {
            Reporting.logReporter(Status.PASS, "Records Found for the Details Hence it is passed.");
        } else {
            Reporting.logReporter(Status.FAIL, "Records Not Found for the Details Hence it is Failed.");
            Validate.assertTrue(false, true, "Records not Found for the Details Hence it is Failed.");
        }
    }

    @Then("verify CSBA WebService response")
    public void verifyCSBAWebService() {

        Map<String, Object> apiOperation1 = new HashMap<>();

        FeatureResult result = APIJava.runKarateFeatureReturnResultSet(environment,
                "classpath:services/SoapApiRunCSBA.feature");

        apiOperation1 = result.getResultAsPrimitiveMap();
        String a = apiOperation1.get("Response").toString();

        if (a.contains("pingResponse")) {
            Reporting.logReporter(Status.PASS, "Ping Response Received. Hence it is passed.");

        } else {
            Reporting.logReporter(Status.FAIL, "Ping Response not Received.Hence it is Failed.");
            Validate.assertTrue(false, true, "Ping Response not Received.Hence it is Failed.");

        }

    }

    @Then("verify CAM WebService response")
    public void verifyCAMTokenWebService() {

        JSONObject creds = getCAMAPICred();
        System.setProperty("karate.camusername", creds.getString("username"));
        System.setProperty("karate.campassword", creds.getString("password"));
        Map<String, Object> apiOperation1 = new HashMap<>();
        FeatureResult result = APIJava.runKarateFeatureReturnResultSet(environment,
                "classpath:services/ApiRunCoreAccountManagement.feature");

        apiOperation1 = result.getResultAsPrimitiveMap();
        String a = apiOperation1.get("Response").toString();
        if (a.contains("@type")) {
            Reporting.logReporter(Status.PASS, "Information Found for the Given Data Hence it is passed.");

        } else {
            Reporting.logReporter(Status.FAIL, "Information Not Found for the Given Data Hence it is Failed.");
            Validate.assertTrue(false, true, "Information Not Found for the Given Data Hence it is Failed.");

        }

    }

    @Then("verify CSOM WebService response")
    public void verifyCSOMMTokenWebService() {

        JSONObject creds = getCSOMAPICred();
        System.setProperty("karate.csomusername", creds.getString("username"));
        System.setProperty("karate.csompassword", creds.getString("password"));
        System.setProperty("karate.csomTestData", csomOrderKey);

        Map<String, Object> apiOperation1 = new HashMap<>();
        FeatureResult result = APIJava.runKarateFeatureReturnResultSet(environment,
                "classpath:services/ApiRunCrisServiceOrderManagement.feature");

        apiOperation1 = result.getResultAsPrimitiveMap();
        String a = apiOperation1.get("Response").toString();
        if (a.contains("serviceOrderItem")) {
            Reporting.logReporter(Status.PASS, "Information Found for the Given Data Hence it is passed.");

        } else {
            Reporting.logReporter(Status.FAIL, "Information Not Found for the Given Data Hence it is Failed.");
            Validate.assertTrue(false, true, "Information Not Found for the Given Data Hence it is Failed.");

        }

    }

    @Then("verify ECB application logged in successfully")
    public void verifyECBAppsLoggedIn() {
        ECBPageSteps ECBPageSteps = new ECBPageSteps();
        ECBPageSteps.verifyHomePage("ECB");
    }

    @And("verify ECB application links downloadable")
    public void verifyECBAppsDownloadable() {
        ECBPageSteps ECBPageSteps = new ECBPageSteps();
        ECBPageSteps.verifyLinkIsDownloadable("ECB");
    }

    @Then("verify WFMA Homepage is displayed")
    public void verifyWFMAHomePage() {
        WFMAPageSteps WFMAPageSteps = new WFMAPageSteps();
        WFMAPageSteps.verifyHomePage();
    }

    @And("verify OCP1 Homepage is displayed")
    public void verifyOCP1Page() {
        OutageCalculationProgramPageSteps outageCalculationProgramPageSteps = new OutageCalculationProgramPageSteps();
        outageCalculationProgramPageSteps.verifyOCP1HomePage();
    }

    @And("verify OCP2 Homepage is displayed")
    public void verifyOCP2Page() {
        OutageCalculationProgramPageSteps outageCalculationProgramPageSteps = new OutageCalculationProgramPageSteps();
        outageCalculationProgramPageSteps.verifyOCP2HomePage();
    }

    @Then("search OCP1 outageInformation")
    public void searchOCP1OutageInformation() {
        OutageCalculationProgramPageSteps outageCalculationProgramPageSteps = new OutageCalculationProgramPageSteps();
        outageCalculationProgramPageSteps.searchOCP1OutageInformation();
    }

    @And("verify OCP1 Search outage Information")
    public void verifyOCP1SearchOutageInformation() {
        OutageCalculationProgramPageSteps outageCalculationProgramPageSteps = new OutageCalculationProgramPageSteps();
        outageCalculationProgramPageSteps.verifyOCP1SearchOutageInformation();
    }

    @Then("logged in OCP2 prod database")
    public void LoggedInOCP2ProdDataBase() {
        OutageCalculationProgramPageSteps outageCalculationProgramPageSteps = new OutageCalculationProgramPageSteps();
        outageCalculationProgramPageSteps.LoggedInOCP2ProdDataBase();
    }

    @Then("Navigate OCP2 Monthly RollUps")
    public void navigateToMonthlyRollUp() {
        OutageCalculationProgramPageSteps outageCalculationProgramPageSteps = new OutageCalculationProgramPageSteps();
        outageCalculationProgramPageSteps.navigateToMonthlyRollUp();
    }

    @And("verify OCP2 Monthly RollUps")
    public void verifyOCP2MonthlyRollUp() {
        OutageCalculationProgramPageSteps outageCalculationProgramPageSteps = new OutageCalculationProgramPageSteps();
        outageCalculationProgramPageSteps.verifyOCP2MonthlyRollUp();
    }

    @And("verify messaging bridge has Forwarding messages")
    public void verifyMessagingBridge() {
        WFMAPageSteps WFMAPageSteps = new WFMAPageSteps();
        WFMAPageSteps.verifyMessagingBridge();
    }

    @And("verify KCB application links downloadable")
    public void verifyKCBAppsDownloadable() {
        ECBPageSteps ECBPageSteps = new ECBPageSteps();
        ECBPageSteps.verifyLinkIsDownloadable("Kenan Customer Center");

    }

    @And("Search For Customer In KBP")
    public void executeCitrix() {
        String USER_DIR_PATH = File.separator + "src" + File.separator + "test" + File.separator + "resources"
                + File.separator;
        String screenCapturePath = System.getProperty("user.dir") + "\\target\\extent-reports\\PassedTestsScreenshots";
        Settings.ActionLogs = false;
        BaseSteps.Waits.waitGeneric(15000);
        Screen s = new Screen();
        try {
            BaseSteps.Waits.waitGeneric(3000);
            s.click();
            BaseSteps.Waits.waitGeneric(3000);
            s.type("x245310");
            BaseSteps.Waits.waitGeneric(3000);
            s.type(Key.TAB);
            BaseSteps.Waits.waitGeneric(3000);
            s.find(System.getProperty("user.dir") + USER_DIR_PATH + "images\\passwordK.png").click();
//

//            s.findAny("password.png"); //identify pause button
            writeIntoCitrix(s, "VGVsdXMxMg==");
            BaseSteps.Waits.waitGeneric(7000);

            Reporting.logReporter(Status.INFO, "Login Details",
                    MediaEntityBuilder.createScreenCaptureFromPath(s.capture().save(screenCapturePath)).build());
            s.find(System.getProperty("user.dir") + USER_DIR_PATH + "images\\login.png").click();

            Reporting.logReporter(Status.INFO, "After Login",
                    MediaEntityBuilder.createScreenCaptureFromPath(s.capture().save(screenCapturePath)).build());
            BaseSteps.Waits.waitGeneric(7000);

            s.find(System.getProperty("user.dir") + USER_DIR_PATH + "images\\link.png").click();
            BaseSteps.Waits.waitGeneric(7000);
            Reporting.logReporter(Status.INFO, "After Search Link",
                    MediaEntityBuilder.createScreenCaptureFromPath(s.capture().save(screenCapturePath)).build());

            s.type("100100");
            for (int i = 0; i < 10; i++) {
                try {
                    Reporting.logReporter(Status.INFO, "After input", MediaEntityBuilder
                            .createScreenCaptureFromPath(s.capture().save(screenCapturePath)).build());

                    s.find(System.getProperty("user.dir") + USER_DIR_PATH + "images\\search.png").click();
                    s.find(System.getProperty("user.dir") + USER_DIR_PATH + "images\\search.png").click();

                    break;
                } catch (FindFailed findFailed) {
                    s.click(System.getProperty("user.dir") + USER_DIR_PATH + "images\\downArrow.png");
                    i++;
                }
            }

            BaseSteps.Waits.waitGeneric(8000);

            Reporting.logReporter(Status.INFO, "After Search",
                    MediaEntityBuilder.createScreenCaptureFromPath(s.capture().save(screenCapturePath)).build());

            s.find(System.getProperty("user.dir") + USER_DIR_PATH + "images\\cuslink.png").click();

            try {
                BaseSteps.Waits.waitGeneric(8000);
                s.find(System.getProperty("user.dir") + USER_DIR_PATH + "images\\accountSum.png");
                System.out.println("passed");
                Reporting.logReporter(Status.INFO, "After Customer Link Clicked",
                        MediaEntityBuilder.createScreenCaptureFromPath(s.capture().save(screenCapturePath)).build());

                Validate.assertTrue(true, true, "Details Found");
            } catch (FindFailed f) {
                Validate.assertTrue(false, "Details not Found", true, "Details Found");
                Reporting.logReporter(Status.FAIL, f.getMessage(),
                        MediaEntityBuilder.createScreenCaptureFromPath(s.capture().save(screenCapturePath)).build());

            }

            s.find(System.getProperty("user.dir") + USER_DIR_PATH + "images\\close").click();

        } catch (FindFailed e) {
            e.printStackTrace();
            Validate.assertTrue(false, true, "No Image Found");
            Reporting.logReporter(Status.FAIL, e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromPath(s.capture().save(screenCapturePath)).build());
            Reporting.logReporter(Status.FAIL, "Unable to Find the Image with error " + e.getMessage());
            try {
                s.find(System.getProperty("user.dir") + USER_DIR_PATH + "images\\close").click();
            } catch (FindFailed ex) {
                Reporting.logReporter(Status.FAIL, e.getMessage(),
                        MediaEntityBuilder.createScreenCaptureFromPath(s.capture().save(screenCapturePath)).build());
            }

        }

//

    }

    @And("verify ECB Customer Search")
    public void executeCitrixECB() {
        String USER_DIR_PATH = File.separator + "src" + File.separator + "test" + File.separator + "resources"
                + File.separator;
        String screenCapturePath = System.getProperty("user.dir") + "\\target\\extent-reports\\PassedTestsScreenshots";
        Settings.ActionLogs = false;
        BaseSteps.Waits.waitGeneric(120000);
        Screen s = new Screen();
        try {
            BaseSteps.Waits.waitGeneric(5000);
            s.click();
            BaseSteps.Waits.waitGeneric(5000);
            s.click();

            s.find(System.getProperty("user.dir") + USER_DIR_PATH + "images\\usernameECB.png").click();
            BaseSteps.Waits.waitGeneric(5000);

            writeIntoCitrix(s, "eDI0NTMxMA==");

            BaseSteps.Waits.waitGeneric(5000);
            s.type(Key.TAB);

            BaseSteps.Waits.waitGeneric(5000);
            s.find(System.getProperty("user.dir") + USER_DIR_PATH + "images\\PasswordECB.png").click();
            BaseSteps.Waits.waitGeneric(5000);

            writeIntoCitrix(s, "QnAzZC4zMTAh");
            BaseSteps.Waits.waitGeneric(7000);

            Reporting.logReporter(Status.INFO, "Login Details",
                    MediaEntityBuilder.createScreenCaptureFromPath(s.capture().save(screenCapturePath)).build());

            s.type(Key.TAB);
            s.find(System.getProperty("user.dir") + USER_DIR_PATH + "images\\EnterECB.png").click();

            BaseSteps.Waits.waitGeneric(3000);

            BaseSteps.Waits.waitGeneric(7000);

            Reporting.logReporter(Status.INFO, "After Login",
                    MediaEntityBuilder.createScreenCaptureFromPath(s.capture().save(screenCapturePath)).build());
            BaseSteps.Waits.waitGeneric(7000);

            s.find(System.getProperty("user.dir") + USER_DIR_PATH + "images\\okbuttonecb.png").click();

            Reporting.logReporter(Status.INFO, "After first Pop Up",
                    MediaEntityBuilder.createScreenCaptureFromPath(s.capture().save(screenCapturePath)).build());
            BaseSteps.Waits.waitGeneric(7000);

            s.find(System.getProperty("user.dir") + USER_DIR_PATH + "images\\okbuttonecb.png").click();

            Reporting.logReporter(Status.INFO, "After Second Pop Up",
                    MediaEntityBuilder.createScreenCaptureFromPath(s.capture().save(screenCapturePath)).build());
            BaseSteps.Waits.waitGeneric(7000);

            s.find(System.getProperty("user.dir") + USER_DIR_PATH + "images\\ManageContractECB.png").click();

            BaseSteps.Waits.waitGeneric(7000);
            Reporting.logReporter(Status.INFO, "After Manage Contract Button",
                    MediaEntityBuilder.createScreenCaptureFromPath(s.capture().save(screenCapturePath)).build());

            BaseSteps.Waits.waitGeneric(7000);

            s.find(System.getProperty("user.dir") + USER_DIR_PATH + "images\\okbuttonecb.png").click();

            Reporting.logReporter(Status.INFO, "After first Pop Up",
                    MediaEntityBuilder.createScreenCaptureFromPath(s.capture().save(screenCapturePath)).build());
            BaseSteps.Waits.waitGeneric(7000);

            s.find(System.getProperty("user.dir") + USER_DIR_PATH + "images\\okbuttonecb.png").click();

            Reporting.logReporter(Status.INFO, "After Second Pop Up",
                    MediaEntityBuilder.createScreenCaptureFromPath(s.capture().save(screenCapturePath)).build());
            BaseSteps.Waits.waitGeneric(7000);

            BaseSteps.Waits.waitGeneric(8000);

            try {
                BaseSteps.Waits.waitGeneric(8000);
                s.find(System.getProperty("user.dir") + USER_DIR_PATH + "images\\TableHeaderECB.png");
                System.out.println("passed");
                Reporting.logReporter(Status.INFO, "Table Header Loaded",
                        MediaEntityBuilder.createScreenCaptureFromPath(s.capture().save(screenCapturePath)).build());
                Validate.assertTrue(true, true, "Table Header Loaded");
            } catch (FindFailed f) {
                Validate.assertTrue(false, "Table Header not Loaded", true, "Details Found");
                Reporting.logReporter(Status.FAIL, f.getMessage(),
                        MediaEntityBuilder.createScreenCaptureFromPath(s.capture().save(screenCapturePath)).build());

            }

            s.find(System.getProperty("user.dir") + USER_DIR_PATH + "images\\closeECB").click();
            BaseSteps.Waits.waitGeneric(5000);

        } catch (FindFailed e) {
            e.printStackTrace();
            Validate.assertTrue(false, true, "No Image Found");
            Reporting.logReporter(Status.FAIL, e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromPath(s.capture().save(screenCapturePath)).build());
            Reporting.logReporter(Status.FAIL, "Unable to Find the Image with error " + e.getMessage());
            try {
                s.find(System.getProperty("user.dir") + USER_DIR_PATH + "images\\closeECB").click();
            } catch (FindFailed ex) {
                Reporting.logReporter(Status.FAIL, ex.getMessage(),
                        MediaEntityBuilder.createScreenCaptureFromPath(s.capture().save(screenCapturePath)).build());
            }

        }

//

    }

    private void writeIntoCitrix(Screen s, String text) {
        s.type(EncryptionUtils.decode(text));

    }

//    @Then("verify WFMA Homepage is displayed")
//    public void verify_WFMA_homepage_is_displayed() {
//        WFMAPageSteps WFMAPageSteps = new WFMAPageSteps();
//        WFMAPageSteps.verifyHomePage();
//    }

    @Then("verify CustomerFulfillment Homepage is displayed")
    public void verify_CustomerFulfillment_homepage_is_displayed() {
        CustomerFulfillmentPageSteps customerFulfillmentPageSteps = new CustomerFulfillmentPageSteps();
        customerFulfillmentPageSteps.verifyHomePage();
    }

    @And("verify product Type populated")
    public void verify_product_type_populated() {
        CustomerFulfillmentPageSteps customerFulfillmentPageSteps = new CustomerFulfillmentPageSteps();
        customerFulfillmentPageSteps.verifyProductTypeloadedAndDBworking();
    }

    @Then("reset DYNATRACE Application")
    public void resetDynaTrace() {
        CoreBillingAccountMgmtPageSteps.resetFilter();
    }

    @And("Navigate to services in DYNATRACE")
    public void navigateDynatraceServices() {
        CoreBillingAccountMgmtPageSteps.navigateToServices();
    }

    @And("select CoreBillingAccountMgmtAPIV1 & verify Failure Rate")
    public void selectCoreBillingAccountManagementAPIV1AndVerifyFailureRate() {
        CoreBillingAccountMgmtPageSteps.verifyCoreAccountManagementAPIFailureRate();
    }

    @Then("verify BRS application logged in successfully")
    public void verifyBRSHomeScreen() {
        BRSPageSteps.verifyLoginSucceeded();
    }

    @And("search BRS Pilot Number")
    public void searchPilotNumber() {
        BRSPageSteps.searchPilotNumber();
    }

    @Then("verify BRS retrieve the bill")
    public void verifyPilotNumberSearchResult() {
        BRSPageSteps.verifyPilotNumberSearchResult();
    }

    @Then("verify lynx homepage")
    public void verify_lynx_homepage() {
        /**
         * System.setProperty("webdriver.ie.driver",
         * "./src/test/resources/drivers/IEDriverServer.exe");
         *
         * InternetExplorerOptions options = new InternetExplorerOptions();
         * options.setCapability("ignoreBrowserProtectedModeSettings", true);
         * options.ignoreZoomSettings(); options.requireWindowFocus(); WebDriver driver
         * = new InternetExplorerDriver(options); driver.get("http://btsn000003/lynx");
         *
         **/
        LynxPageSteps LynxPageSteps = new LynxPageSteps();
        WebDriverSteps.openApplication("LYNX");
        BaseSteps.Waits.waitGeneric(2000);

        Validate.takeStepScreenShot("Lynx login screen");

        BaseSteps.Waits.waitGeneric(2000);

        LynxPageSteps.printFramesOnTheWebPage();

        LynxPageSteps.verifyHomePage();

        WebDriverSteps.getWebDriverSession().switchTo().defaultContent();

        LynxPageSteps.printFramesOnTheWebPage();

        Reporting.logReporter(Status.INFO, WebDriverSteps.getWebDriverSession().getPageSource().toString());

        WebDriverSession.getWebDriverSession().switchTo().frame("_sweclient");
        LynxPageSteps.printFramesOnTheWebPage();

        WebDriverSession.getWebDriverSession().switchTo().frame("_swecontent");
        LynxPageSteps.printFramesOnTheWebPage();

        WebDriverSession.getWebDriverSession().switchTo().frame("_sweview");
        LynxPageSteps.printFramesOnTheWebPage();

        WebDriverSession.getWebDriverSession().switchTo().frame("_svf1");

        JavascriptExecutor executor = (JavascriptExecutor) WebDriverSession.getWebDriverSession();
        executor.executeScript("arguments[0].click();",
                WebDriverSession.getWebDriverSession().findElement(By.id("SWEApplet2")));

        // LynxPageSteps.navigateToRespectiveTabs("");
        Validate.takeStepScreenShot("Trouble Ticket screen");
    }

//    @Given("Test Mainframe Applications")
//    public void verifyMainframeApps() throws IOException {
//
//        MainframeSteps MainframeSteps = new MainframeSteps();
//
//        /*
//         * String mainframeDirPath =
//         * SystemProperties.getStringValue("mainframe.folder.path"); String
//         * mainframeFWDir = System.getProperty("user.dir") + "\\MainframeProject";
//         * MainframeSteps.createMFDir(mainframeDirPath, mainframeFWDir);
//         */
//
//        JSONObject userAccess = userAccessVar.getJSONObject(SystemProperties.EXECUTION_ENVIRONMENT);
//
//        String imse_username = EncryptionUtils.decode(userAccess.getString("TPX_AB_IMSE_ENV_USERNAME"));// .getString("TPX_AB_IMSE_ENV_USERNAME");
//        String imse_pass = EncryptionUtils.decode(userAccess.getString("TPX_AB_IMSE_ENV_PASSWORD"));
//        String cris_username = EncryptionUtils.decode(userAccess.getString("TPX_AB_IMSE_APP_USERNAME"));
//        String cris_pass = EncryptionUtils.decode(userAccess.getString("TPX_AB_IMSE_APP_PASSWORD"));
//
//        String cmd = "cmd /c \"python -m robot --variable ENV_USERNAME:" + imse_username + " --variable ENV_PASSWORD:"
//                + imse_pass + " --variable APP_USERNAME:" + cris_username + " --variable APP_PASSWORD:" + cris_pass
//                + " " + robotFilePath;
//
//
//
//        try {
////            MainframeSteps.launchMainframeApplication(cmd);
//
//
//        } catch (Exception e) {
//            Reporting.logReporter(Status.INFO, "Unable to validate CRIS application health check" + e);
//        } finally {
//            captureScreenshots("CRIS");
//        }
//
////        mainframeCrisStatus = MainframeSteps.getMainframeAppStatus();
//
//    }

    @Given("Test {string} Applications")
    public void verifyMainframeApp(String applicationName) throws Exception {

        String status;

        status = getApplicationStatusIfAlreadyExist(applicationName);


        if (status.equalsIgnoreCase("Passed")) {
            Validate.assertTrue(true, true, "Application Passed");
            Reporting.logReporter(Status.PASS, "Application Passed");
        } else if (status.equalsIgnoreCase("Failed")) {
            Validate.assertTrue(false, true, "Application Failed");
            Reporting.logReporter(Status.FAIL, "Application Failed");
        }
        if (status.equals("NOTFOUND")) {
            MainframeUtils MainframeUtils = new MainframeUtils();

            String robotFilePath = System.getProperty("user.dir") + File.separator + "MainframeProject" + File.separator
                    + "Tests" + File.separator + applicationName + ".robot";

            //String buildUrl = System.getenv("BUILD_URL");
            String buildUrl = System.getenv("BUILD_URL");
            String wsUrl = buildUrl.split("BPED_Mainframe_Test")[0].concat("BPED_Mainframe_Test/ws/");
            String reportFilePath = SystemProperties.getStringValue("mainframe.build.report.artifact.path")
                    + applicationName + "_Report" + ".html";
            String logFilePath = SystemProperties.getStringValue("mainframe.build.report.artifact.path") + applicationName
                    + "_Log" + ".html";
            String reportFileArtifectPath = wsUrl + reportFilePath;
            String logFileArtifectPath = wsUrl + logFilePath;

            try {
                String command = MainframeUtils.generateCommand(getEnviornmentVariablesForApplication(applicationName),
                        robotFilePath, reportFilePath, reportFileArtifectPath, logFilePath, logFileArtifectPath);

                MainframeUtils.launchMainframeApplication(command);

            } catch (Exception e) {
                Reporting.logReporter(Status.INFO,
                        "Unable to validate " + applicationName + " application health check" + e);
            } finally {
                captureScreenshots(applicationName);
            }
            mainframeAppStatus = MainframeUtils.getMainframeAppStatus();
        }
    }

    /**
     * @param applicationName
     * @return
     */
    private HashMap<String, String> getEnviornmentVariablesForApplication(String applicationName) {
        JSONObject userAccess = userAccessVar.getJSONObject(SystemProperties.EXECUTION_ENVIRONMENT);

        switch (applicationName) {
            case "CRISAB":
            case "CAMSAB":
            case "CRIS3AB":
            case "CAMSBC":
            case "CRISBC":
            case "CRIS3BC":
            case "FPOAB":
            case "PAPSAB":
            case "FPOBC":
            case "PAPSBC":
            case "CRISSSBC": {
                String tn_number = null;
                String region = null;
                if (applicationName.contains("CAMSAB") || applicationName.contains("CRISAB")
                        || applicationName.contains("CRIS3AB") || applicationName.contains("PAPSAB")
                        || applicationName.contains("FPOAB")) {
                    tn_number = EncryptionUtils.decode(userAccess.getString("POST_TN_AB"));
                    region = EncryptionUtils.decode(userAccess.getString("TPX_AB_REGION"));
                } else {
                    tn_number = EncryptionUtils.decode(userAccess.getString("POST_TN_BC"));
                    region = EncryptionUtils.decode(userAccess.getString("TPX_BC_REGION"));
                }
                String region_username = EncryptionUtils.decode(userAccess.getString("TPX_AB_BC_REGION_USERNAME"));// .getString("TPX_AB_IMSE_ENV_USERNAME");
                String region_pass = EncryptionUtils.decode(userAccess.getString("TPX_AB_BC_REGION_PASSWORD"));
                String cris_username = EncryptionUtils.decode(userAccess.getString("TPX_AB_BC_CRIS_USERNAME"));
                String cris_pass = EncryptionUtils.decode(userAccess.getString("TPX_AB_BC_CRIS_PASSWORD"));

                HashMap<String, String> keys = new HashMap<>();

                keys.put("POST_TN", tn_number);
                keys.put("REGION", region);
                keys.put("REG_USERNAME", region_username);
                keys.put("REG_PASSWORD", region_pass);
                keys.put("APP_USERNAME", cris_username);
                keys.put("APP_PASSWORD", cris_pass);
//                keys.put("PATH",robotFilePath);

                return keys;
            }
            case "SOECS": {
                String soecs_username = EncryptionUtils.decode(userAccess.getString("SOECS_USERNAME"));
                String soecs_pass = EncryptionUtils.decode(userAccess.getString("SOECS_PASSWORD"));
                HashMap<String, String> keys = new HashMap<>();
                keys.put("SOECS_USERNAME", soecs_username);
                keys.put("SOECS_PASSWORD", soecs_pass);
//                keys.put("PATH",robotFilePath);

                return keys;
            }

            default:
                return null;
        }
    }


    public String getApplicationStatusIfAlreadyExist(String applicationName) throws Exception {
        String status = "NOTFOUND";
        String executedList = GoogleSheetData.newList.toString();

        GoogleSheetsUtils googleSheetsUtils = new GoogleSheetsUtils();
        String token = GoogleSheetsUtils.getAccessToken();
        JSONArray baseDataAppDetailsArray = googleSheetsUtils.readMainframeApplicationsSheet(token);
        for (int j = 0; j < baseDataAppDetailsArray.length(); j++) {
            int appIndex = GenericUtils.getIndexFromJsonObject(baseDataAppDetailsArray, applicationName);
            JSONArray dataObject = (JSONArray) baseDataAppDetailsArray.get(appIndex - 1);
            String dependentAppsList = "";


            if (dataObject.toString().contains(applicationName)) {
                dependentAppsList = dataObject.getString(1);
            }

            for(JSONObject a:GoogleSheetData.newList){
              if(dependentAppsList.contains(a.getString("appName"))){
                 status=a.getString("appStatus");

              }

            }




        }






//        if (applicationName.equals("CRISAB")) {
//            if (executedList.contains("Customer Records Information System 1 / Inquiry: AB")
//                    ||
//                    executedList.contains("Customer Records Information System 2 / Update: AB")
//                    ||
//                    executedList.contains("Customer Data Base System: AB")
//                    ||
//                    executedList.contains("Consolidated Billing Number: AB")
//                    ||
//                    executedList.contains("Customer Activity Processing System: AB")
//                    ||
//                    executedList.contains("Customized Utility Billing System: AB")
//                    ||
//                    executedList.contains("Printer Utility BMP System: AB")
//                    ||
//                    executedList.contains("Small Exchange Carrier Access Billing System: AB")
//
//            ) {
//                for (JSONObject obj : GoogleSheetData.newList) {
//                    if (obj.get("appName").equals("Customer Records Information System 1 / Inquiry: AB")
//
//                            ||
//                            obj.get("appName").equals("Customer Records Information System 2 / Update: AB")
//                            ||
//                            obj.get("appName").equals("Customer Data Base System: AB")
//                            ||
//                            obj.get("appName").equals("Consolidated Billing Number: AB")
//                            ||
//                            obj.get("appName").equals("Customer Activity Processing System: AB")
//                            ||
//                            obj.get("appName").equals("Customized Utility Billing System: AB")
//                            ||
//                            obj.get("appName").equals("Printer Utility BMP System: AB")
//                            ||
//                            obj.get("appName").equals("Small Exchange Carrier Access Billing System: AB")
//                    ) {
//                        status = obj.getString("appStatus");
//                    }
//                }
//
//
//            }
//
//
//        } else if (applicationName.equals("CRISBC")) {
//            if (executedList.contains("Customer Records Information System 1 / Inquiry: BC")
//                    ||
//                    executedList.contains("Customer Records Information System 2 / Update: BC")
//                    ||
//                    executedList.contains("Customer Data Base System: BC")
//                    ||
//                    executedList.contains("Consolidated Billing Number: BC")
//                    ||
//                    executedList.contains("Accurate Business Customer Data")
//                    ||
//                    executedList.contains("Bulk Service Order")
//                    ||
//                    executedList.contains("Customer Activity Processing System: BC")
//                    ||
//                    executedList.contains("Customized Utility Billing System: BC")
//                    ||
//                    executedList.contains("E911: Billing")
//                    ||
//                    executedList.contains("Major Accounts Summary Billing")
//                    ||
//                    executedList.contains("Marketing Cross Reference")
//                    ||
//                    executedList.contains("National Carrier Access Management System")
//                    ||
//                    executedList.contains("Printer Utility BMP System II: BC")
//                    ||
//                    executedList.contains("Trouble Reporting Information Handling System")
//
//            ) {
//                for (JSONObject obj : GoogleSheetData.newList) {
//                    if (obj.get("appName").equals("Customer Records Information System 1 / Inquiry: BC")
//                            ||
//                            obj.get("appName").equals("Customer Records Information System 2 / Update: BC")
//                            ||
//                            obj.get("appName").equals("Customer Data Base System: BC")
//                            ||
//                            obj.get("appName").equals("Consolidated Billing Number: BC")
//                            ||
//                            obj.get("appName").equals("Accurate Business Customer Data")
//                            ||
//                            obj.get("appName").equals("Bulk Service Order")
//                            ||
//                            obj.get("appName").equals("Customer Activity Processing System: BC")
//                            ||
//                            obj.get("appName").equals("Customized Utility Billing System: BC")
//                            ||
//                            obj.get("appName").equals("E911: Billing")
//                            ||
//                            obj.get("appName").equals("Major Accounts Summary Billing")
//                            ||
//                            obj.get("appName").equals("Marketing Cross Reference")
//                            ||
//                            obj.get("appName").equals("National Carrier Access Management System")
//                            ||
//                            obj.get("appName").equals("Printer Utility BMP System II: BC")
//                            ||
//                            obj.get("appName").equals("Trouble Reporting Information Handling System")
//
//                    ) {
//                        status = obj.getString("appStatus");
//                    }
//
//
//                }
//
//
//            }
//
//        } else if (applicationName.equals("CRIS3AB")) {
//            if (executedList.contains("CRIS3: Customer Records Information System 3 / Service Order System: AB")
//                    ||
//                    executedList.contains("Due Date System: AB")
//                    ||
//                    executedList.contains("Consolidated Billing Number: AB")
//                    ||
//                    executedList.contains("Customer Activity Processing System: AB")
//                    ||
//                    executedList.contains("Printer Utility BMP System: AB")
//                    ||
//                    executedList.contains("Customized Utility Billing System: AB")
//                    ||
//                    executedList.contains("Small Exchange Carrier Access Billing System: AB")
//
//
//            ) {
//                for (JSONObject obj : GoogleSheetData.newList) {
//                    if (obj.get("appName").equals("CRIS3: Customer Records Information System 3 / Service Order System: AB")
//                            ||
//                            obj.get("appName").equals("Due Date System: AB")
//                            ||
//                            obj.get("appName").equals("Consolidated Billing Number: AB")
//                            ||
//                            obj.get("appName").equals("Customer Activity Processing System: AB")
//                            ||
//                            obj.get("appName").equals("Printer Utility BMP System: AB")
//                            ||
//                            obj.get("appName").equals("Customized Utility Billing System: AB")
//                            ||
//                            obj.get("appName").equals("Small Exchange Carrier Access Billing System: AB")
//
//
//                    ) {
//                        status = obj.getString("appStatus");
//                    }
//
//
//                }
//
//
//            }
//
//        } else if (applicationName.equals("CRIS3BC")) {
//            if (executedList.contains("CRIS3: Customer Records Information System 3 / Service Order System: BC")
//                    ||
//                    executedList.contains("Due Date System: BC")
//                    ||
//                    executedList.contains("Consolidated Billing Number: BC")
//                    ||
//                    executedList.contains("Accurate Business Customer Data")
//                    ||
//                    executedList.contains("Bulk Service Order")
//                    ||
//                    executedList.contains("Customer Activity Processing System: BC")
//                    ||
//                    executedList.contains("Customized Utility Billing System: BC")
//                    ||
//                    executedList.contains("E911: Billing")
//                    ||
//                    executedList.contains("Major Accounts Summary Billing")
//                    ||
//                    executedList.contains("Marketing Cross Reference")
//                    ||
//                    executedList.contains("National Carrier Access Management System")
//                    ||
//                    executedList.contains("Printer Utility BMP System II: BC")
//                    ||
//                    executedList.contains("Trouble Reporting Information Handling System")
//
//
//            ) {
//                for (JSONObject obj : GoogleSheetData.newList) {
//                    if (obj.get("appName").equals("CRIS3: Customer Records Information System 3 / Service Order System: BC")
//                            ||
//                            obj.get("appName").equals("Due Date System: BC")
//                            ||
//                            obj.get("appName").equals("Consolidated Billing Number: BC")
//                            ||
//                            obj.get("appName").equals("Accurate Business Customer Data")
//                            ||
//                            obj.get("appName").equals("Bulk Service Order")
//                            ||
//                            obj.get("appName").equals("Customer Activity Processing System: BC")
//                            ||
//                            obj.get("appName").equals("Customized Utility Billing System: BC")
//                            ||
//                            obj.get("appName").equals("E911: Billing")
//                            ||
//                            obj.get("appName").equals("Major Accounts Summary Billing")
//                            ||
//                            obj.get("appName").equals("Marketing Cross Reference")
//                            ||
//                            obj.get("appName").equals("National Carrier Access Management System")
//                            ||
//                            obj.get("appName").equals("Printer Utility BMP System II: BC")
//                            ||
//                            obj.get("appName").equals("Trouble Reporting Information Handling System")
//
//                    ) {
//                        status = obj.getString("appStatus");
//
//                    }
//
//
//                }
//
//
//            }
//
//        } else if (applicationName.equals("CAMSAB")) {
//            if (executedList.contains("CAMS: AB")
//                    ||
//                    executedList.contains("Triad: AB")
//
//
//            ) {
//                for (JSONObject obj : GoogleSheetData.newList) {
//                    if (obj.get("appName").equals("CAMS: AB")
//                            ||
//                            obj.get("appName").equals("Triad: AB")
//
//
//                    ) {
//                        status = obj.getString("appStatus");
//
//                    }
//
//
//                }
//
//
//            }
//
//        } else if (applicationName.equals("CAMSBC")) {
//            if (executedList.contains("CAMS: BC")
//                    ||
//                    executedList.contains("Triad: BC")
//
//
//            ) {
//                for (JSONObject obj : GoogleSheetData.newList) {
//                    if (obj.get("appName").equals("CAMS: BC")
//                            ||
//                            obj.get("appName").equals("Triad: BC")
//
//                    ) {
//                        status = obj.getString("appStatus");
//
//                    }
//
//                }
//
//
//            }
//
//        }

        return status;

    }

}