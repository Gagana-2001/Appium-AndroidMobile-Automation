package appium.com.testcases.mobileBrowser;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import appium.com.baseTest.MobileBrowserBaseTest;
import appium.com.pageObjectModels.mobileBrowser.ProductCatalogPage;

public class LoginPageTest extends MobileBrowserBaseTest {

	public ProductCatalogPage productCatalogPage;
	public Map<String, String> dataMap;

	@BeforeMethod
	public void setUp() throws IOException {
		log.info("Fetching data from excel");
		dataMap = getExcelData();
	}

	@Test(priority = 1)
	public void login() {
		String userName = dataMap.get("UserName");
		String password = dataMap.get("Password");
		productCatalogPage = loginPage.login(userName, password);
		log.info("Logging into application with valid username {} and password {} " + userName + password);
	}
}