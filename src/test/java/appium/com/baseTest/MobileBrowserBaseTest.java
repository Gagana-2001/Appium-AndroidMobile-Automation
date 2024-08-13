package appium.com.baseTest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import org.testng.annotations.BeforeSuite;

import appium.com.common.CommonBaseTest;
import appium.com.common.ExcelDataDriven;
import appium.com.pageObjectModels.mobileBrowser.LoginPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class MobileBrowserBaseTest extends CommonBaseTest {

	public AppiumDriverLocalService service;
	public static AndroidDriver driver;
	public LoginPage loginPage;

	@BeforeSuite
	public LoginPage launchApplication() throws URISyntaxException, IOException {
		configure();
		driver = configureDriverForMobileBrowsers();
		loginPage = new LoginPage(driver);

		return loginPage;
	}

	public Map<String, String> getExcelData() throws IOException {
		ExcelDataDriven excelData = new ExcelDataDriven();
		Map<String, String> dataMap = excelData.getData(
				System.getProperty("user.dir") + "\\src\\test\\java\\appium\\com\\data\\MobileBrowserTestData.xlsx");

		return dataMap;
	}
}