package appium.com.testcases.mobileBrowser;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import appium.com.baseTest.MobileBrowserBaseTest;
import appium.com.pageObjectModels.mobileBrowser.PDPPage;
import appium.com.pageObjectModels.mobileBrowser.ProductCatalogPage;

public class ProductCatalogPageTest extends MobileBrowserBaseTest {

	public ProductCatalogPage prodCatlogPage;
	public PDPPage pdpPage;
	public Map<String, String> dataMap;

	@BeforeMethod
	public void setUp() throws IOException {
		dataMap = getExcelData();
		log.info("Fetching data from excel");
		prodCatlogPage = new ProductCatalogPage(MobileBrowserBaseTest.driver);
	}

	@Test(priority = 1)
	public void searchForProduct() {
		String productName = dataMap.get("ProductName");
		pdpPage = prodCatlogPage.getProduct(productName);
		log.info("Finding for product {} " + productName);
	}
}