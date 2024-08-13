package appium.com.pageObjectModels.mobileBrowser;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import appium.com.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class OrderConfirmationPage extends AndroidActions {

	public AndroidDriver driver;

	public OrderConfirmationPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@FindBy(xpath = "//div[@id='checkout_complete_container']/h2")
	private WebElement confMessage;

	public Boolean verifyConfirmationMessage(String msg) {
		waitForWebElementToAppear(confMessage);
		Boolean match = confMessage.getText().equalsIgnoreCase(msg);

		return match;
	}
}