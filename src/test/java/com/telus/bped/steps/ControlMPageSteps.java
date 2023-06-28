package com.telus.bped.steps;

import com.telus.bped.pages.ControlMPage;
import com.telus.bped.stepsdefinition.StepDefinitions;
import com.test.files.interaction.ReadJSON;
import com.test.reporting.Reporting;
import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.Validate;
import com.test.utils.Status;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ControlMPageSteps extends BaseSteps {


    static ControlMLoginPageSteps controlMLoginPageSteps = new ControlMLoginPageSteps();
//    private static JSONObject jsonFile = new JSONObject(ReadJSON.parse("TestData/BPEDTestData.json"));
//    private static JSONObject appNames = jsonFile.getJSONObject("JOBSFOLDER");

    public static void searchJobsAndTheirStatus(String applicationName) {


        controlMLoginPageSteps.appLogin_ControlM();
        //Searching For the Jobs

        ControlMPage controlMPage = new ControlMPage();
        BaseSteps.Waits.waitForElementVisibilityLongWait(controlMPage.monitoringTab);
        boolean actualRes = controlMPage.isMonitoringLinkDisplayed();
        Validate.assertEquals(actualRes, true, "HomePage is not displayed", false);
        Reporting.logReporter(Status.INFO, " Homepage is displayed");
        BaseSteps.Clicks.clickElement(controlMPage.monitoringTab);
        BaseSteps.Waits.waitForElementVisibilityLongWait(controlMPage.searchJobsInput);

        String jobsFolder = StepDefinitions.getJobsFolder(applicationName);
        System.out.println(jobsFolder);
        BaseSteps.SendKeys.clearFieldAndSendKeys(controlMPage.searchJobsInput, jobsFolder);
        BaseSteps.Clicks.clickElement(controlMPage.searchJobButton);
        //
        BaseSteps.Waits.waitForElementVisibilityLongWait(controlMPage.jobStatus);
        actualRes = controlMPage.isSearchDone();
        Validate.assertEquals(actualRes, true, "Job Search is not displayed", false);
        Reporting.logReporter(Status.INFO, " Job Search  is displayed");
        BaseSteps.Waits.waitForElementVisibilityLongWait(controlMPage.jobStatus);
        //  BaseSteps.Clicks.clickElement(controlMPage.resizeName);
        // BaseSteps.Clicks.clickElement(controlMPage.resizeStatus);

   /*
     Fetching and Mapping JOBS status with name:


   */
        int size = BaseSteps.Finds.findElements(By.xpath("//*[@col-id='JobLabel' and @role='gridcell']")).size();


        String xpathName = "(//*[@col-id='JobLabel' and @role='gridcell']//*//*//*)[";
        // String xpathStatus = "(//*[@col-id='JobStatus' and @role='gridcell']//span)[";
        //     String xpathStatus = "(//*[@col-id='JobStatus' and @role='gridcell']//span)[";
        HashMap<String, HashMap<String, ArrayList<String>>> jobInfo = new HashMap<>();


        for (int i = 1, j = 1; i <= size; i++, j = j + 2) {
            HashMap<String, ArrayList<String>> statuses = new HashMap<>();

            String name = BaseSteps.Finds.findElement(By.xpath(xpathName + i + "]")).getText();


            BaseSteps.Clicks.clickElement(BaseSteps.Finds.findElement(By.xpath(xpathName + i + "]")));
            BaseSteps.Clicks.clickElementLongWait(BaseSteps.Finds.findElement(By.xpath("//div[@class=\"ui-tabs-scrollable show-drop-down\"]/button[@ng-mousedown=\"scrollButtonDown('right', $event)\"]")));
            ArrayList<String> runs = new ArrayList<>();

            try {
                BaseSteps.Clicks.clickElementLongWait(BaseSteps.Finds.findElement(By.xpath("//*[@index=\"vm.getTabEnumFromString('Output')\"]")));
                BaseSteps.Waits.waitForElementToBeClickableLongWait(BaseSteps.Finds.findElement(By.xpath("//*[@translate='ViewLatest']/..")));
                BaseSteps.Waits.waitGeneric(4000);
                BaseSteps.Clicks.clickElementLongWait(BaseSteps.Finds.findElement(By.xpath("//*[@translate='ViewLatest']/..")));
                int jobsRuns = BaseSteps.Finds.findElements(By.xpath("//li[@ng-repeat='output in vm.outputList']//a")).size();

                for (int k = 1; k <= jobsRuns; k++) {
                    String a = BaseSteps.Finds.findElement(By.xpath("(//li[@ng-repeat='output in vm.outputList']//a)[" + k + "]")).getText();
                    String[] b = a.split(",");
                    ArrayList<String> aaa = new ArrayList<>(Arrays.asList(b));
                    runs.addAll(aaa);
                    statuses.put(aaa.get(0), aaa);

                }

            } catch (Exception e) {
                runs.add("No Outputs Found for this Job" + i);
                runs.add(" 0");
                statuses.put(runs.get(0), runs);
            }

            jobInfo.put(name, statuses);

        }

        String jj = jobInfo.toString().replaceAll("]", "]\n");

        Reporting.logReporter(Status.INFO, "JobNames with Their Statuses: \n" + jj);


    }

//    public static String getJobsFolder(String appName) {
//
//        String applicationName = appName.toUpperCase();
//
//        if(applicationName.contains("VPOP")){
//            applicationName = ReadJSON.getString(appNames, "VPOP");
//
//        }else {
//            applicationName=ReadJSON.getString(appNames,applicationName);
//        }
//        return applicationName;
//    }


}
