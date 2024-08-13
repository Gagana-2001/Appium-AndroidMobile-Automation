package appium.com.testcases;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import appium.com.baseTest.BaseTest;
import appium.com.common.Retry;
import appium.com.pageObjectModels.CartPage;
import appium.com.pageObjectModels.HybridAppPage;

public class CartPageTest extends BaseTest {

	public Map<String, String> dataMap;
	double sumOfPrice;
	double totalAmount;
	public CartPage cartPage;
	public HybridAppPage hybridAppPage;

	@BeforeMethod
	public void setUp() throws IOException {
		dataMap = getExcelData();
		log.info("Fetching data from excel");
		cartPage = new CartPage(BaseTest.driver);
	}

	@Test(priority = 1)
	public void verifyTheCartForProduct() {
		String product1 = dataMap.get("Product1");
		String product2 = dataMap.get("Product2");
		cartPage.verifyProductInCart(product1);
		log.info("Verify the cart for product {}" + product1);
		cartPage.verifyProductInCart(product2);
		log.info("Verify the cart for product {}" + product2);
	}

	@Test(priority = 2, dependsOnMethods = "verifyTheCartForProduct")
	public void verifySumOfProductPrice() {
		sumOfPrice = cartPage.sumOfPricesOfProductInCart();
		log.info("Verifying the total prices of products");
	}

	@Test(priority = 3, dependsOnMethods = "verifySumOfProductPrice")
	public void verifyTheTotalAmountDisplayed() {
		totalAmount = cartPage.getTotalAmountDisplayed();
		Assert.assertEquals(sumOfPrice, totalAmount);
		log.info("Verifying Total price displayed with sum of prices of products");
	}

	@Test(priority = 4)
	public void verifyTermsAndConditions() {
		cartPage.readTermsAndCondition();
		log.info("Reading Terms and conditions");
	}

	@Test(priority = 5)
	public void applyDiscount() {
		cartPage.applyDiscount();
		log.info("Applying discounts");
	}

	@Test(priority = 6)
	public void placeOrder() throws InterruptedException {
		hybridAppPage = cartPage.submitOrder();
		log.info("Submitting Order");
	}
}