package com.telus.bped.utils;

import com.intuit.karate.core.FeatureResult;
import com.telus.api.test.utils.APIJava;
import com.test.reporting.Reporting;
import com.test.utils.EncryptDecrypt;
import com.test.utils.EncryptionUtils;
import com.test.utils.Status;
import com.test.utils.SystemProperties;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Map;

public class GoogleSheetsUtils {

    public static final JSONArray jsonArray = new JSONArray();
    private static String accessToken = "";
    private static String karateTokenStr = "karate.bearerToken";
    private static String karateSheetIdStr = "karate.sheetId";
    private static String karateSheetNameStr = "karate.sheetName";
    private static String responseValueStr = "responseValues";
    private static String readGSheetClassPathStr = "classpath:services/readGoogleSheets.feature";
    private static String tempEnvVar = "NON-PROD";
    private static String encryptionDecryptionKey = SystemProperties.getStringValue("selenium.key");
    private static int updatedRow = 0;
    private static JSONObject gSheetConfigVar = null;

    /**
     * Method Description: This method is used to generate the access token
     *
     * @return accessToken
     */
    public static String getAccessToken() throws Exception {
        String cid = "";
        String csec = "";
        String cRT = "";

        GoogleSheetsUtils googleSheetsUtils = new GoogleSheetsUtils();
        Map<String, Object> apiOperation ;


            cid = googleSheetsUtils.getKeyValue("GH_CD_RT");
            csec = googleSheetsUtils.getKeyValue("GH_CS_RT");
            cRT = googleSheetsUtils.getKeyValue("GH_RT");

            System.setProperty("karate.cid", cid);
            System.setProperty("karate.csec", csec);
            System.setProperty("karate.cRT", cRT);

            FeatureResult result = APIJava.runKarateFeatureReturnResultSet(tempEnvVar,
                    "classpath:services/getAuthToken.feature");
            apiOperation = result.getResultAsPrimitiveMap();
            accessToken = apiOperation.get("access_token").toString();
            Reporting.logReporter(Status.INFO, "Access Token Generated Successfully !");

        return accessToken;
    }


    public static JSONArray updateBulKDataIntoGSheets(JSONArray p1Apps, JSONArray p2Apps, JSONArray p3Apps) throws Exception {

        GoogleSheetsUtils googleSheetsUtils = new GoogleSheetsUtils();

        JSONArray writeDataArray = new JSONArray();

        String token = GoogleSheetsUtils.getAccessToken();

        JSONArray baseDataAppDetailsArray = googleSheetsUtils.readBaseGoogleSheet(token);
        int rowIndex = readConsolidatedGoogleSheet(token);
        int lastUsedRow = rowIndex;
        updatedRow = rowIndex;

        writeDataArray.put(generateGoogleSheetArray(p1Apps, baseDataAppDetailsArray));
        writeDataArray.put(generateGoogleSheetArray(p2Apps, baseDataAppDetailsArray));
        writeDataArray.put(generateGoogleSheetArray(p3Apps, baseDataAppDetailsArray));
//        writeDataArray.put(generateGoogleSheetArray(mainframes_apps, baseDataAppDetailsArray));
//        writeDataArray.put(generateGoogleSheetArray(soecs_apps, baseDataAppDetailsArray));

        JSONObject payload = generatePayload(jsonArray, lastUsedRow, updatedRow);

        System.setProperty(karateTokenStr, token);
        System.setProperty(karateSheetIdStr, getSheetId());
        System.setProperty(karateSheetNameStr, getSheetUpdateName());
        System.setProperty("karate.payload", payload.toString());
        System.setProperty("karate.sheetRange", payload.getString("range"));

        APIJava.runKarateFeatureReturnResultSet(tempEnvVar, "classpath:services/updateGoogleSheets.feature");


        return writeDataArray;
    }

    public static JSONObject generatePayload(JSONArray writeDataArray, int lastUsedRow, int updatedRow) {

        String sheet = getSheetUpdateName();

        JSONObject payload = new JSONObject();

        payload.put("range", sheet + "!A" + ++lastUsedRow + ":K" + updatedRow);
        payload.put("majorDimension", "ROWS");
        payload.put("values", writeDataArray);
        return payload;
    }

    public static int readConsolidatedGoogleSheet(String token) {

        System.setProperty(karateTokenStr, token);
        System.setProperty(karateSheetIdStr, getSheetId());
        System.setProperty(karateSheetNameStr, getSheetUpdateName());

        /**
         * Call Read Googlesheet API
         */

        FeatureResult result1 = APIJava.runKarateFeatureReturnResultSet(tempEnvVar, readGSheetClassPathStr);

        Map<String, Object> apiOperationReadMainSheet = result1.getResultAsPrimitiveMap();
        JSONArray jObjMainSheet = new JSONArray(apiOperationReadMainSheet.get(responseValueStr).toString());
        return jObjMainSheet.length();

    }

    /**
     * @param pApps
     * @param baseDataAppDetailsArray
     * @return
     */
    public static JSONArray generateGoogleSheetArray(JSONArray pApps, JSONArray baseDataAppDetailsArray) {

        for (int i = 0; i < pApps.length(); i++) {
            JSONObject jObj = (JSONObject) pApps.get(i);
            String appName = jObj.getString("appName");
            String appStatus = jObj.getString("appStatus");
            String executedAt = jObj.getString("executedAt");
            String date = jObj.getString("executedAt").split(" ")[0];

            for (int j = 0; j < baseDataAppDetailsArray.length(); j++) {
                int appIndex = GenericUtils.getIndexFromJsonObject(baseDataAppDetailsArray, appName);
                JSONArray dataObject1 = new JSONArray();

                dataObject1.put(updatedRow + "");
                updatedRow++;

                JSONArray dataObject = (JSONArray) baseDataAppDetailsArray.get(appIndex - 1);

				/*
				 * for (Object a : dataObject) { dataObject1.put(a); }
				 */
                
                for (int l=0; l< dataObject.length(); l++) {
                	 dataObject1.put(dataObject.get(l));
                }

                if (dataObject1.toString().contains(appName)) {
                    dataObject1.put(appStatus);
                    dataObject1.put(executedAt);
                    dataObject1.put(date);
                    jsonArray.put(dataObject1);
                    break;
                }
            }
        }

        return jsonArray;
    }

    /**
     * Method Description: To get the sheet Id
     *
     * @return
     */
    public static String getSheetId() {

        return getgSheetConfigVar().get("gSheetId").toString();
    }

    /**
     * Method Description: To get the sheet Name
     *
     * @return
     */
    public static String getSheetName() {
        return getgSheetConfigVar().get("gSheetName").toString();

    }

    /**
     * Method Description: To get the sheet Name
     *
     * @return
     */
    public static String getSheetUpdateName() {
        return getgSheetConfigVar().get("gSheetUpdateName").toString();

    }

    /**
     * Method Description: To get the sheet start column
     *
     * @return
     */
    public static String getStartColumn() {

        return getgSheetConfigVar().get("start_column").toString();

    }

    /**
     * Method Description: To get the sheet end column
     *
     * @return
     */
    public static String getEndColumn() {
        return getgSheetConfigVar().get("end_column").toString();

    }

    public static JSONObject getgSheetConfigVar() {
        return gSheetConfigVar;
    }

    public static void setgSheetConfigVar(JSONObject gSheetConfigVar) {
        GoogleSheetsUtils.gSheetConfigVar = gSheetConfigVar;
    }

    public JSONObject getJSONObjectFromGit(String value) {
        String a = value;
        a = a.replace("\\r\\n\\t\\", "");
        a = a.replace("\\r", "");
        a = a.replace("\\n", "");
        a = a.replace("\\t", "");
        a = a.replace("t\\", "");
        a = a.replace("\\", "");

        return new JSONObject(a);

    }

    /**
     * @param appName
     * @param status  // * @param authCode
     */
    public void updateGoogleSheetViaAPI(String accessToken, String appName, String status) {


        System.setProperty(karateTokenStr, accessToken);
        System.setProperty(karateSheetIdStr, getSheetId());
        System.setProperty(karateSheetNameStr, getSheetName());
        String executedAt = GenericUtils.getDateInMMDDYYYYHHMMSSInPST() + " (PST)";

        /**
         * Call Read Googlesheet API
         */

        FeatureResult result1 = APIJava.runKarateFeatureReturnResultSet(tempEnvVar, readGSheetClassPathStr);
        Map<String, Object> apiOperationRead = result1.getResultAsPrimitiveMap();
        JSONArray jObj = new JSONArray(apiOperationRead.get(responseValueStr).toString());

        int appIndex = GenericUtils.getIndexFromJsonObject(jObj, appName);
        JSONArray dataObject = (JSONArray) jObj.get(appIndex - 1);

        System.setProperty("karate.applicationName" + java.lang.Thread.currentThread().getId(),
                dataObject.getString(0));
        System.setProperty("karate.cmdbId" + java.lang.Thread.currentThread().getId(), dataObject.getString(1));
        System.setProperty("karate.priority" + java.lang.Thread.currentThread().getId(), dataObject.getString(2));
        System.setProperty("karate.platform" + java.lang.Thread.currentThread().getId(), dataObject.getString(3));
        System.setProperty("karate.primeName" + java.lang.Thread.currentThread().getId(), dataObject.getString(4));
        System.setProperty("karate.managerName" + java.lang.Thread.currentThread().getId(),
                dataObject.getString(5));
        System.setProperty("karate.directorName" + java.lang.Thread.currentThread().getId(),
                dataObject.getString(6));

        /**
         * Call Read Googlesheet API
         */

        System.setProperty(karateSheetNameStr, getSheetUpdateName());
        FeatureResult result = APIJava.runKarateFeatureReturnResultSet(tempEnvVar, readGSheetClassPathStr);

        Map<String, Object> apiOperationReadMainSheet = result.getResultAsPrimitiveMap();
        JSONArray jObjMainSheet = new JSONArray(apiOperationReadMainSheet.get(responseValueStr).toString());
        int rowIndex = jObjMainSheet.length() + 1;

        String sheetRange = getSheetUpdateName() + "!" + getStartColumn() + rowIndex + ":" + getEndColumn()
                + rowIndex;

        System.setProperty("karate.rowNum" + java.lang.Thread.currentThread().getId(),
                String.valueOf(rowIndex + 1));
        System.setProperty("karate.sheetRange" + java.lang.Thread.currentThread().getId(), sheetRange);
        System.setProperty("karate.appStatus" + java.lang.Thread.currentThread().getId(), status);
        System.setProperty("karate.executedAt" + java.lang.Thread.currentThread().getId(), executedAt);

        /**
         * Call Update Googlesheet API
         */

        APIJava.runKarateFeatureReturnResultSet(tempEnvVar, "classpath:services/updateGoogleSheets.feature");


        Reporting.logReporter(Status.INFO, "GoogleSheet updated successfully !");

    }

    /**
     * @param key
     * @return keyValue
     * @throws Exception
     */
    public String getKeyValue(String key, boolean... finalDecryptedNotRequired) throws Exception {

        boolean flag = finalDecryptedNotRequired.length > 0 && finalDecryptedNotRequired[0];

        String keValue = "";
        Map<String, Object> apiOperation ;
        System.setProperty("karate.keyName", key);

        String ekey = EncryptionUtils.decode(encryptionDecryptionKey);

        String egpt = SystemProperties.getStringValue("selenium.ghpt");
        String ghpt = EncryptDecrypt.generateDecryptData(egpt, ekey);
        System.setProperty("karate.ghpt", ghpt);

            FeatureResult result = APIJava.runKarateFeatureReturnResultSet(tempEnvVar,
                    "classpath:services/getEnvGHVariables.feature");
            apiOperation = result.getResultAsPrimitiveMap();
            keValue = apiOperation.get("keyVal").toString();
            if (!flag) {
                keValue = EncryptDecrypt.generateDecryptData(keValue, ekey);
            }

        return keValue;

    }

    public JSONArray readBaseGoogleSheet(String token) {

        System.setProperty(karateTokenStr, token);
        System.setProperty(karateSheetIdStr, getSheetId());
        System.setProperty(karateSheetNameStr, getSheetName());

        /**
         * Call Read Googlesheet API
         */

        FeatureResult result1 = APIJava.runKarateFeatureReturnResultSet(tempEnvVar, readGSheetClassPathStr);
        Map<String, Object> apiOperationRead = result1.getResultAsPrimitiveMap();
        return new JSONArray(apiOperationRead.get(responseValueStr).toString());

    }
}
