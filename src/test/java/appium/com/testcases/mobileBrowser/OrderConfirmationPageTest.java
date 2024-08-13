package appium.com.testcases.mobileBrowser;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import appium.com.baseTest.MobileBrowserBaseTest;
import appium.com.pageObjectModels.mobileBrowser.OrderConfirmationPage;

public class OrderConfirmationPageTest extends MobileBrowserBaseTest {
	public OrderConfirmationPage orderConfPage;
	public Map<String, String> dataMap;

	@BeforeMethod
	public void setUp() throws IOException {
		dataMap = getExcelData();
		orderConfPage = new OrderConfirmationPage(MobileBrowserBaseTest.driver);
	}

	@Test(priority = 1)
	public void verifyConfirmationMessage() {
		String msg = dataMap.get("ConfirmationMessage");
		Boolean match = orderConfPage.verifyConfirmationMessage(msg);
		Assert.assertTrue(match);
	}
}