package appium.com.testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import appium.com.baseTest.BaseTest;
import appium.com.pageObjectModels.CartPage;
import appium.com.pageObjectModels.CatalogPage;

public class CatalogPageTest extends BaseTest {

	public CatalogPage catalogPage;
	public CartPage cartpage;

	@BeforeMethod
	public void setUp() {
		catalogPage = new CatalogPage(BaseTest.driver);
	}

	@Test(priority = 1)
	public void addItemToCart() {
		catalogPage.addProductToCart(0);
		catalogPage.addProductToCart(0);
		log.info("Adding Products to cart");
	}

	@Test(priority = 2, dependsOnMethods = "addItemToCart")
	public void goToCartPage() throws InterruptedException {
		cartpage = catalogPage.goToCartPage();
		log.info("Navigating to cart Page");
	}
}