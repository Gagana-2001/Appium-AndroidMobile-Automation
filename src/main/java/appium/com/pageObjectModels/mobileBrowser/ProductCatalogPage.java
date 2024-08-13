package appium.com.pageObjectModels.mobileBrowser;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import appium.com.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductCatalogPage extends AndroidActions {

	public AndroidDriver driver;

	public ProductCatalogPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@FindBy(xpath = "//a[@id='item_4_title_link']/div")
	private WebElement productNameEle;

	public PDPPage getProduct(String productName) {
		waitForWebElementToAppear(productNameEle);
		productNameEle.getText().equalsIgnoreCase(productName);
		productNameEle.click();

		return new PDPPage(driver);
	}
}