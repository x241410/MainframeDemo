package com.telus.cucumber.plugin;

import com.epam.reportportal.cucumber.ScenarioReporter;
import com.epam.reportportal.utils.MemoizingSupplier;
import com.epam.ta.reportportal.ws.model.StartTestItemRQ;
import com.test.utils.SystemProperties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Properties;


public class ReportPortalCucumberPlugin extends ScenarioReporter {

	private static final String REPORT_PORTAL_PATH = "reportportal.properties";
	private static final String REPORT_PORTAL_SUITE_NAME = "rp.suite.name";

	/**
	 * Start root suite
	 */
	protected void startRootItem() {
		rootSuiteId = new MemoizingSupplier<>(() -> {
			StartTestItemRQ rq = new StartTestItemRQ();
			rq.setName(getProperty(REPORT_PORTAL_SUITE_NAME));
			rq.setStartTime(Calendar.getInstance().getTime());
			rq.setType("SUITE");
			return getLaunch().startTestItem(rq);
		});
	}

	private static Properties loadProperties(String propertiesFilename) {
		Properties prop = new Properties();

		ClassLoader loader = ReportPortalCucumberPlugin.class.getClassLoader();

		try (InputStream stream = loader.getResourceAsStream(propertiesFilename)) {
			if (stream == null) {
				throw new FileNotFoundException();
			}
			prop.load(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	private static String getProperty(String key) {
		String value;
		Properties prop = loadProperties(REPORT_PORTAL_PATH);
		if (SystemProperties.isValueSet("rp.enable") && SystemProperties.isValueSet(key)) {
			value =  SystemProperties.getStringValue(key);
		}else {
			value = prop.getProperty(key);
		}		
		return (value == null || value.equals(""))? "Root User Story" : value;
	}

}