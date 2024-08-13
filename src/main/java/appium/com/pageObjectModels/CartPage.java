package appium.com.pageObjectModels;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import appium.com.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions {

	public AndroidDriver driver;

	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
	private List<WebElement> productNameEle;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productPriceEle;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalAmountEle;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
	private WebElement termsAndConditions;

	@AndroidFindBy(id = "android:id/button1")
	private WebElement closeBtn;

	@AndroidFindBy(className = "android.widget.CheckBox")
	private WebElement discountCheckbox;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
	private WebElement placeOrderBtn;

	public boolean verifyProductInCart(String productName) {
		Boolean match = productNameEle.stream().anyMatch(prod -> prod.getText().equalsIgnoreCase(productName));

		return match;
	}

	public double sumOfPricesOfProductInCart() {
		int count = productPriceEle.size();
		double sum = 0;
		for (int i = 0; i < count; i++) {
			String amountText = productPriceEle.get(i).getText();
			Double price = getFormattedAmount(amountText);
			sum = sum + price;
		}

		return sum;
	}

	public double getTotalAmountDisplayed() {
		String totalAmountText = totalAmountEle.getText();
		double totalamount = getFormattedAmount(totalAmountText);

		return totalamount;
	}

	public void readTermsAndCondition() {
		longPress(termsAndConditions);
		closeBtn.click();
	}

	public void applyDiscount() {
		discountCheckbox.click();
	}

	public HybridAppPage submitOrder() throws InterruptedException {
		placeOrderBtn.click();
		Thread.sleep(10000);

		return new HybridAppPage(driver);
	}
}