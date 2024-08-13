package appium.com.pageObjectModels.mobileBrowser;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import appium.com.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage extends AndroidActions {

	public AndroidDriver driver;

	public LoginPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@FindBy(xpath = "//input[@placeholder='Username']")
	private WebElement userNameField;

	@FindBy(xpath = "//input[@placeholder='Password']")
	private WebElement passwordField;

	@FindBy(xpath = "//input[@type='submit']")
	private WebElement loginBtn;

	public ProductCatalogPage login(String userName, String password) {
		userNameField.sendKeys(userName);
		passwordField.sendKeys(password);
		loginBtn.click();

		return new ProductCatalogPage(driver);
	}
}