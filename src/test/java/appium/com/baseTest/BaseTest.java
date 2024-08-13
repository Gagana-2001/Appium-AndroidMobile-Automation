package appium.com.baseTest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import org.testng.annotations.BeforeSuite;

import appium.com.common.CommonBaseTest;
import appium.com.common.ExcelDataDriven;
import appium.com.pageObjectModels.FormPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseTest extends CommonBaseTest {

	public AppiumDriverLocalService service;
	public static AndroidDriver driver;
	public FormPage formPage;

	@BeforeSuite
	public FormPage launchApplication() throws URISyntaxException, IOException {
		configure();
		driver = configureDriverForApp();
		formPage = new FormPage(driver);

		return formPage;
	}

	public Map<String, String> getExcelData() throws IOException {
		ExcelDataDriven excelData = new ExcelDataDriven();
		Map<String, String> dataMap = excelData
				.getData(System.getProperty("user.dir") + "\\src\\test\\java\\appium\\com\\data\\TestData.xlsx");

		return dataMap;
	}
}