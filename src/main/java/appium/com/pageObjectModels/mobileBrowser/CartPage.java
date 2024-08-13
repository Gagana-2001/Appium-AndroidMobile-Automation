package appium.com.pageObjectModels.mobileBrowser;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import appium.com.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions {

	public AndroidDriver driver;

	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@FindBy(css = ".inventory_item_name")
	public List<WebElement> cartProductEle;

	@FindBy(css = "#checkout")
	public WebElement checkoutBtn;

	public Boolean verifyTheProductInCart(String productName) {
		waitForWebElementsToAppear(cartProductEle);
		Boolean match = cartProductEle.stream().anyMatch(prod -> prod.getText().equalsIgnoreCase(productName));

		return match;
	}

	public CheckoutPage navigateToCheckOutPage() {
		checkoutBtn.click();

		return new CheckoutPage(driver);
	}

}
