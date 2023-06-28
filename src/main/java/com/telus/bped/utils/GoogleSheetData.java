package com.telus.bped.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;

import com.test.reporting.Reporting;
import com.test.utils.Status;

public class GoogleSheetData {

	private GoogleSheetData() {
		throw new IllegalStateException("Utility class");
	}

	private static List<JSONObject> newList = Collections.synchronizedList(new ArrayList<JSONObject>());

	public static void writeScenarioStatus(JSONObject obj) {
		Reporting.logReporter(Status.INFO, " write status data : " + obj);
		GoogleSheetData.newList.add(obj);
	}

	public static List<JSONObject> getExecutionStatus() {
		return newList;
	}
}
