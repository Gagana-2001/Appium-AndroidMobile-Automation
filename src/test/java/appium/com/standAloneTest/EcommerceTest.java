package appium.com.standAloneTest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import appium.com.baseTest.BaseTest;
import appium.com.pageObjectModels.CartPage;
import appium.com.pageObjectModels.CatalogPage;
import appium.com.pageObjectModels.FormPage;
import appium.com.pageObjectModels.HybridAppPage;

public class EcommerceTest extends BaseTest {

	@BeforeMethod
	public FormPage launchApplication() throws URISyntaxException, IOException {
		configure();
		driver = configureDriverForApp();
		formPage = new FormPage(driver);

		return formPage;
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test(dataProvider = "getData")
	public void placeOrder(HashMap<String, String> input) throws InterruptedException {
		formPage.selectCountry(input.get("CountryName"));
		formPage.setNameField(input.get("Name"));
		formPage.setGender(input.get("Gender"));
		CatalogPage catalogPage = formPage.submitForm();
		catalogPage.addProductToCart(0);
		catalogPage.addProductToCart(0);
		CartPage cartPage = catalogPage.goToCartPage();
		cartPage.verifyProductInCart(input.get("Product1"));
		cartPage.verifyProductInCart(input.get("Product2"));
		cartPage.sumOfPricesOfProductInCart();
		cartPage.getTotalAmountDisplayed();
		cartPage.readTermsAndCondition();
		cartPage.applyDiscount();
		HybridAppPage hybridAppPage = cartPage.submitOrder();
		hybridAppPage.switchContextToWeb(input.get("WebContext"));
		hybridAppPage.browserSearch(input.get("text"));
		hybridAppPage.switchBackToNativeApp(input.get("NativeContext"));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "\\src\\test\\java\\appium\\com\\data\\Data.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}
}