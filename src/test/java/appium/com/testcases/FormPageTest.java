package appium.com.testcases;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import appium.com.baseTest.BaseTest;
import appium.com.pageObjectModels.CatalogPage;

public class FormPageTest extends BaseTest {

	public CatalogPage catalogPage;
	public Map<String, String> dataMap;

	@BeforeMethod
	public void setUp() throws IOException {
		dataMap = getExcelData();
		log.info("Fetching data from excel");
	}

	@Test(priority = 1)
	public void selectCountry() throws IOException {
		String countryName = dataMap.get("CountryName");
		formPage.selectCountry(countryName);
		log.info("Selecting country {}" + countryName);
	}

	@Test(priority = 2)
	public void setName() throws IOException {
		String name = dataMap.get("Name");
		formPage.setNameField(name);
		log.info("Specifying name as {}" + name);
	}

	@Test(priority = 3)
	public void selectGender() throws IOException {
		String gender = dataMap.get("Gender");
		formPage.setGender(gender);
		log.info("Specifying gender as {}" + gender);
	}

	@Test(priority = 4, dependsOnMethods = "setName")
	public void submitForm() {
		catalogPage = formPage.submitForm();
		log.info("Submitting form");
	}
}