package appium.com.pageObjectModels;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import appium.com.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CatalogPage extends AndroidActions {

	AndroidDriver driver;

	public CatalogPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD TO CART']")
	private List<WebElement> addToCartBtn;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement cartIcon;

	public void addProductToCart(int index) {
		addToCartBtn.get(index).click();
	}

	public CartPage goToCartPage() throws InterruptedException {
		cartIcon.click();
		Thread.sleep(3000);

		return new CartPage(driver);
	}
}