package appium.com.testcases.mobileBrowser;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import appium.com.baseTest.MobileBrowserBaseTest;
import appium.com.pageObjectModels.mobileBrowser.CartPage;
import appium.com.pageObjectModels.mobileBrowser.PDPPage;

public class PDPPageTest extends MobileBrowserBaseTest {

	public PDPPage pdpPage;
	public CartPage cartPage;
	public Map<String, String> dataMap;

	@BeforeMethod
	public void setUp() throws IOException {
		dataMap = getExcelData();
		log.info("Fetching data from excel");
		pdpPage = new PDPPage(MobileBrowserBaseTest.driver);
	}

	@Test(priority = 1)
	public void verifyTheProductNameInPDP() {
		String productName = dataMap.get("ProductName");
		Boolean match = pdpPage.verifyTheProductNameInPDP(productName);
		Assert.assertTrue(match);
		log.info("Veriying PDP page for product {} " + productName);
	}

	@Test(priority = 2, dependsOnMethods = "verifyTheProductNameInPDP")
	public void addProductToCart() {
		pdpPage.addProductToCart();
		log.info("Adding Product to Cart");
	}

	@Test(priority = 3, dependsOnMethods = "addProductToCart")
	public void navigateToCartPage() {
		cartPage = pdpPage.navigateToCartPage();
		log.info("Navigating to Cart Page");
	}
}