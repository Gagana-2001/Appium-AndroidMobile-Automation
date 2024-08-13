package appium.com.testcases.mobileBrowser;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import appium.com.baseTest.MobileBrowserBaseTest;
import appium.com.pageObjectModels.mobileBrowser.CartPage;
import appium.com.pageObjectModels.mobileBrowser.CheckoutPage;

public class CartPageTest extends MobileBrowserBaseTest {

	public CartPage cartPage;
	public CheckoutPage checkoutPage;
	public Map<String, String> dataMap;

	@BeforeMethod
	public void setUp() throws IOException {
		dataMap = getExcelData();
		log.info("Fetching data from excel");
		cartPage = new CartPage(MobileBrowserBaseTest.driver);
	}

	@Test(priority = 1)
	public void verifyForProductInCart() {
		String productName = dataMap.get("ProductName");
		Boolean match = cartPage.verifyTheProductInCart(productName);
		Assert.assertTrue(match);
		log.info("Verifying cart for product {} " + productName);
	}

	@Test(priority = 2, dependsOnMethods = "verifyForProductInCart")
	public void navigateToCheckoutPage() {
		checkoutPage = cartPage.navigateToCheckOutPage();
		log.info("Navigating to checkoutPage");
	}
}