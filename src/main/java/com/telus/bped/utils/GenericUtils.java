package com.telus.bped.utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.test.files.interaction.ReadJSON;
import com.test.logging.Logging;
import com.test.reporting.Reporting;
import com.test.utils.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * ***************************************************************************
 * DESCRIPTION: This class contains reusable Java methods AUTHOR: x241410
 * ***************************************************************************
 */

public class GenericUtils {

	private GenericUtils() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Description: The purpose of this method is to fetch the json key value for
	 * given parameters
	 *
	 * @param fileName - name of the json file
	 * @param node     - json node
	 * @param key      - json key
	 * @return String output value
	 */
	public static String getKeyValueFromJsonNode(String fileName, String node, String key) {
		JSONObject obj = null;
		String output = null;

		try {
			JSONObject jsonFile = new JSONObject(ReadJSON.parse(fileName));
			obj = jsonFile.getJSONObject(node);
			output = obj.getString(key);
		} catch (JSONException e) {
			Logging.logReporter(Status.DEBUG, "JSON_EXCEPTION " + e);
		}

		return output;
	}

	/**
	 * Description: The purpose of this method is to get the json object from a json
	 * file
	 *
	 * @param fileName
	 * @return jsonObj
	 */
	public static JSONObject getJSONObjectFromJSONFile(String fileName) {
		JSONObject jsonObj = null;

		try {
			jsonObj = new JSONObject(ReadJSON.parse(fileName));

		} catch (JSONException e) {
			Logging.logReporter(Status.DEBUG, "JSON_EXCEPTION " + e);
		}

		return jsonObj;
	}

	/**
	 * Description: The purpose of this method is to return PST Date time
	 *
	 * @return current system date in mm/dd/yyyy format
	 */
	public static String getDateInMMDDYYYYHHMMSSInPST() {
		Instant instant = Instant.now();
		ZonedDateTime zdtNewYork = instant.atZone(ZoneId.of("America/Los_Angeles"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		return zdtNewYork.format(formatter);
	}

	/**
	 * @param jObj
	 * @param val
	 * @return
	 */
	public static int getIndexFromJsonObject(JSONArray jObj, String val) {

		int c = 0;
		for (int i = 0; i < jObj.length(); i++) { // this will iterate through key1 - key3
			String str = jObj.get(i).toString().split(",")[0].split(",")[0].replace("\"", "").replace("[", "");
			if (str.equalsIgnoreCase(val)) {
				c = i + 1;
				break;
			}
		}
		return c;
	}

	public static void main1(String[] args) {
		Path sourceFolder = Paths.get(
				System.getProperty("user.dir") + "\\Mainframe Demo Codes POCMainframeAppUpdatedmainframeScreenshots");
		Path targetFolder = Paths.get(System.getProperty("user.dir") + "\\screenshots");
		try {
			// Copy the entire folder and its contents
			copyFolder(sourceFolder, targetFolder);
			System.out.println("Folder copied successfully.");
		} catch (IOException e) {
			System.out.println("An error occurred while copying the folder: " + e.getMessage());
		}
	}

	public static void copyFolder(Path sourceFolder, Path targetFolder) throws IOException {
		// Create the target folder if it doesn't exist
		if (!Files.exists(targetFolder)) {
			Files.createDirectories(targetFolder);
		}

		// Copy each file and subfolder recursively
		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(sourceFolder)) {
			for (Path path : directoryStream) {
				Path newPath = targetFolder.resolve(path.getFileName());
				if (Files.isDirectory(path)) {
					copyFolder(path, newPath); // Recursively copy subfolders
				} else {
					Files.copy(path, newPath, StandardCopyOption.REPLACE_EXISTING);
				}
			}
		}
	}

	public static void moveScreenshots() {
		Path sourceFolder = Paths.get(
				System.getProperty("user.dir") + "\\Mainframe Demo Codes POCMainframeAppUpdatedmainframeScreenshots");
		Path targetFolder = Paths.get(System.getProperty("user.dir") + "\\screenshots");
		try {
			// Copy the entire folder and its contents
			copyFolder(sourceFolder, targetFolder);
			System.out.println("Folder copied successfully.");
		} catch (IOException e) {
			System.out.println("An error occurred while copying the folder: " + e.getMessage());
		}
	}

	public static void getAllImagesHelper(File directory, String scenarioName) throws IOException {
		try {
			String buildUrl = System.getenv("BUILD_URL");
			String sspath = buildUrl + "artifact/MainframeProject/atest/screenshots/";
			
			File[] f = directory.listFiles();
			for (File file : f) {
				if (file != null && file.getName().toLowerCase().endsWith(".jpg")
						&& file.getName().toUpperCase().contains(scenarioName)) {
					
					//Reporting.logReporter(Status.INFO, file.getName().toUpperCase(),
							//MediaEntityBuilder.createScreenCaptureFromPath((file.getAbsolutePath())).build());
					
					Reporting.logReporter(Status.INFO, "Path: "+ sspath+file.getName());

					Reporting.logReporter(Status.INFO, file.getName().toUpperCase(),
							MediaEntityBuilder.createScreenCaptureFromPath((sspath+file.getName())).build());
				}

			}
		} catch (Exception e) {
			Reporting.logReporter(Status.INFO, "Directory not found");
		}
	}

}