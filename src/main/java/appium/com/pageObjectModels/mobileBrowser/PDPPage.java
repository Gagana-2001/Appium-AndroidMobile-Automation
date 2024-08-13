package appium.com.pageObjectModels.mobileBrowser;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import appium.com.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class PDPPage extends AndroidActions {

	public AndroidDriver driver;

	public PDPPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@FindBy(css = ".inventory_details_name.large_size")
	private WebElement productNameEle;

	@FindBy(css = "#add-to-cart")
	private WebElement addToCartBtn;

	@FindBy(css = ".shopping_cart_link")
	private WebElement cartIcon;

	public Boolean verifyTheProductNameInPDP(String productName) {
		waitForWebElementToAppear(productNameEle);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		Boolean match = productNameEle.getText().equalsIgnoreCase(productName);

		return match;
	}

	public void addProductToCart() {
		addToCartBtn.click();
	}

	public CartPage navigateToCartPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-1000)");
		waitForWebElementToAppear(cartIcon);
		cartIcon.click();

		return new CartPage(driver);
	}
}