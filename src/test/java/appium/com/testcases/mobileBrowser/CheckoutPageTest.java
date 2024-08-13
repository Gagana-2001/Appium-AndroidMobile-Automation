package appium.com.testcases.mobileBrowser;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import appium.com.baseTest.MobileBrowserBaseTest;
import appium.com.pageObjectModels.mobileBrowser.CheckoutPage;
import appium.com.pageObjectModels.mobileBrowser.OrderConfirmationPage;

public class CheckoutPageTest extends MobileBrowserBaseTest {

	public CheckoutPage checkOutPage;
	public OrderConfirmationPage orderConfPage;
	public Map<String, String> dataMap;

	@BeforeMethod
	public void setUp() throws IOException {
		dataMap = getExcelData();
		log.info("Fetching data from excel");
		checkOutPage = new CheckoutPage(MobileBrowserBaseTest.driver);
	}

	@Test(priority = 1)
	public void fillCheckoutDetails() {
		String fname = dataMap.get("FirstName");
		String lname = dataMap.get("LastName");
		String zipCode = dataMap.get("ZipCode");
		checkOutPage.enterCheckoutDetails(fname, lname, zipCode);
		log.info("Entering checkout details FirstName{}, Lastname{},ZipCode {} " + fname + lname + zipCode);
	}

	@Test(priority = 2, dependsOnMethods = "fillCheckoutDetails")
	public void contineToOrder() {
		checkOutPage.continueToPlaceOder();
		log.info("Continuing to place order");
	}

	@Test(priority = 3, dependsOnMethods = "contineToOrder")
	public void verifyTheProductInCheckout() {
		String productName = dataMap.get("ProductName");
		Boolean match = checkOutPage.verifyTheProductInCheckoutPage(productName);
		Assert.assertTrue(match);
		log.info("Verifying the product {} in checkout page" + productName);
	}

	@Test(priority = 4, dependsOnMethods = "verifyTheProductInCheckout")
	public void placeOder() {
		orderConfPage = checkOutPage.placeOrder();
		log.info("Placing an order");
	}
}