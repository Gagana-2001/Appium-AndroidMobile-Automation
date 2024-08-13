package appium.com.pageObjectModels;

import java.util.Set;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import appium.com.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HybridAppPage extends AndroidActions {

	public AndroidDriver driver;

	public HybridAppPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@FindBy(name = "q")
	private WebElement searchField;

	public void switchContextToWeb(String con) {
		Set<String> contexts = driver.getContextHandles();
		for (String context : contexts) {
			System.out.println(context);
		}
		driver.context(con);
	}

	public void browserSearch(String word) {
		waitForWebElementToAppear(searchField);
		searchField.sendKeys(word);
		searchField.sendKeys(Keys.ENTER);
	}

	public void switchBackToNativeApp(String context) {
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.context(context);
	}

}
