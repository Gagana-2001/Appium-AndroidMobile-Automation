package appium.com.testcases;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import appium.com.baseTest.BaseTest;
import appium.com.pageObjectModels.HybridAppPage;

public class HybridAppTest extends BaseTest {

	public Map<String, String> dataMap;
	public HybridAppPage hybridAppPage;

	@BeforeMethod
	public void setUp() throws IOException {
		dataMap = getExcelData();
		log.info("Fetching data from excel");
		hybridAppPage = new HybridAppPage(BaseTest.driver);
	}

	@Test(priority = 1)
	public void switchContext() {
		String webContext = dataMap.get("WebContext");
		hybridAppPage.switchContextToWeb(webContext);
		log.info("Switching to {} web context" + webContext);
	}

	@Test(priority = 2, dependsOnMethods = "switchContext")
	public void performActionOnWeb() {
		String text = dataMap.get("Text");
		hybridAppPage.browserSearch(text);
		log.info("Browsing for text {} " + text);
	}

	@Test(priority = 3, dependsOnMethods = "performActionOnWeb")
	public void switchBackToNative() {
		String nativeContext = dataMap.get("NativeContext");
		hybridAppPage.switchBackToNativeApp(nativeContext);
		log.info("Switching back to {} native context" + nativeContext);
	}

}
