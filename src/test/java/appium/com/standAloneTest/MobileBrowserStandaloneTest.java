package appium.com.standAloneTest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import appium.com.baseTest.MobileBrowserBaseTest;
import appium.com.pageObjectModels.mobileBrowser.CartPage;
import appium.com.pageObjectModels.mobileBrowser.CheckoutPage;
import appium.com.pageObjectModels.mobileBrowser.LoginPage;
import appium.com.pageObjectModels.mobileBrowser.OrderConfirmationPage;
import appium.com.pageObjectModels.mobileBrowser.PDPPage;
import appium.com.pageObjectModels.mobileBrowser.ProductCatalogPage;

public class MobileBrowserStandaloneTest extends MobileBrowserBaseTest {

	@BeforeMethod
	public LoginPage launchApplication() throws URISyntaxException, IOException {
		configure();
		driver = configureDriverForMobileBrowsers();
		loginPage = new LoginPage(driver);

		return loginPage;
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test(dataProvider = "getData")
	public void mobileBrowserPlaceOder(HashMap<String, String> input) {
		ProductCatalogPage productCatalogPage = loginPage.login(input.get("UserName"), input.get("Password"));
		PDPPage pdpPage = productCatalogPage.getProduct(input.get("ProductName"));
		pdpPage.verifyTheProductNameInPDP(input.get("ProductName"));
		pdpPage.addProductToCart();
		CartPage cartPage = pdpPage.navigateToCartPage();
		cartPage.verifyTheProductInCart(input.get("ProductName"));
		CheckoutPage checkoutPage = cartPage.navigateToCheckOutPage();
		checkoutPage.enterCheckoutDetails(input.get("FirstName"), input.get("LastName"), input.get("ZipCode"));
		checkoutPage.continueToPlaceOder();
		checkoutPage.verifyTheProductInCheckoutPage(input.get("ProductName"));
		OrderConfirmationPage orderConfPage = checkoutPage.placeOrder();
		orderConfPage.verifyConfirmationMessage(input.get("ConfMsg"));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "\\src\\test\\java\\appium\\com\\data\\MobileBrowserTestData.json");

		return new Object[][] { { data.get(0) } };
	}
}