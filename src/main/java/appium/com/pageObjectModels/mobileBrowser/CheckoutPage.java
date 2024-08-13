package appium.com.pageObjectModels.mobileBrowser;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import appium.com.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CheckoutPage extends AndroidActions {

	public AndroidDriver driver;

	public CheckoutPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@FindBy(xpath = "//input[@placeholder='First Name']")
	private WebElement firstNameField;

	@FindBy(xpath = "//input[@placeholder='Last Name']")
	private WebElement lastNameField;

	@FindBy(xpath = "//input[@placeholder='Zip/Postal Code']")
	private WebElement zipCodeField;

	@FindBy(css = "#continue")
	private WebElement contineBtn;

	@FindBy(css = ".inventory_item_name")
	private WebElement productNameEle;

	@FindBy(css = "#finish")
	private WebElement finishBtn;

	public void enterCheckoutDetails(String fname, String lname, String zipCode) {
		waitForWebElementToAppear(firstNameField);
		firstNameField.sendKeys(fname);
		lastNameField.sendKeys(lname);
		zipCodeField.sendKeys(zipCode);
		driver.hideKeyboard();
	}

	public void continueToPlaceOder() {
		contineBtn.click();
	}

	public Boolean verifyTheProductInCheckoutPage(String productName) {
		waitForWebElementToAppear(productNameEle);
		Boolean match = productNameEle.getText().equalsIgnoreCase(productName);

		return match;
	}

	public OrderConfirmationPage placeOrder() {
		finishBtn.click();

		return new OrderConfirmationPage(driver);
	}
}