package appium.com.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.testng.annotations.AfterSuite;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class CommonBaseTest {

	public Properties prop;
	public AppiumDriverLocalService service;
	public static AndroidDriver driver;
	public static Logger log;

	public void configure() throws URISyntaxException, IOException {
		log = LogManager.getLogger(CommonBaseTest.class);

		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\appium\\com\\resources\\Global.properties");
		prop.load(fis);

		service = new AppiumServiceBuilder()
				.withAppiumJS(new File(
						"C:\\Users\\Gagana.CM\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress(prop.getProperty("ipAddress")).usingPort(Integer.parseInt(prop.getProperty("port")))
				.build();
		log.info("Starting Appium Server");
		service.start();
	}

	public AndroidDriver configureDriverForApp() throws MalformedURLException, URISyntaxException {
		log.info("Staring UiAutomator2Options");
		UiAutomator2Options options = new UiAutomator2Options();
		if (prop.getProperty("DeviceType").equalsIgnoreCase("Real")) {
			options.setDeviceName(prop.getProperty("RealDeviceName"));
			options.setUdid(prop.getProperty("Udid"));
			options.setPlatformName(prop.getProperty("PlatformName"));
			options.setPlatformVersion(prop.getProperty("PlatformVersion"));
		} else {
			options.setDeviceName(prop.getProperty("VirtualDeviceName"));
		}

		options.setApp(System.getProperty("user.dir") + "\\src\\test\\java\\appium\\com\\data\\General-Store.apk");
		options.setChromedriverExecutable(System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
		log.info("Android driver Creation for Native Apps-Hybrid Apps");
		driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return driver;
	}

	public AndroidDriver configureDriverForMobileBrowsers() throws MalformedURLException, URISyntaxException {
		UiAutomator2Options options = new UiAutomator2Options();
		log.info("Staring UiAutomator2Options");
		if (prop.getProperty("DeviceType").equalsIgnoreCase("Real")) {
			options.setDeviceName(prop.getProperty("RealDeviceName"));
			options.setUdid(prop.getProperty("Udid"));
			options.setPlatformName(prop.getProperty("PlatformName"));
			options.setPlatformVersion(prop.getProperty("PlatformVersion"));
		} else {
			options.setDeviceName(prop.getProperty("VirtualDeviceName"));
		}

		options.setChromedriverExecutable(System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
		options.setCapability("browserName", "Chrome");
		log.info("Android driver Creation for Mobile Browser application");
		driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
		driver.get("https://www.saucedemo.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return driver;
	}

	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			log.info("Closing Driver connection");
			driver.quit();
		}
	}

	public List<HashMap<String, String>> getJsonData(String filePath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}

	public String getScreenShot(String testCase, AndroidDriver driver) throws IOException {
		File source = driver.getScreenshotAs(OutputType.FILE);
		File path = new File(System.getProperty("user.dir") + "//Test-Result//ScreenShots//" + testCase + ".png");
		FileUtils.copyFile(source, path);

		return System.getProperty("user.dir") + "//Test-Result//ScreenShots//" + testCase + ".png";
	}
}