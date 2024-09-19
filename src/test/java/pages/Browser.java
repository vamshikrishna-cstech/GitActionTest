package pages;

import java.time.Duration;
import java.util.Base64;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import org.openqa.selenium.OutputType;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.cucumber.java.Scenario;

public class Browser {
	public static WebDriver driver;

	@Before
	public void setUpBrowser() {
		// Get the browser mode from a system property or environment variable
		String browserMode = System.getProperty("browser.mode", "headless"); // Default to headless

		// Set up Chrome options
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");

		if ("headless".equalsIgnoreCase(browserMode)) {
			// Headless mode specific options
			options.addArguments("--headless");
			options.addArguments("--disable-gpu"); // Recommended for headless mode
			//options.addArguments("--window-size=1920x1080"); // Optional: Set window size
			options.addArguments("start-maximized");
		} else {
			// Options for head-full mode
			options.addArguments("start-maximized");
			// Additional options can be added as needed
		}

		// Set up ChromeDriver using WebDriverManager
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);

		// Configure WebDriver timeouts and initial settings
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://vcaretest.azurewebsites.net/");
	}

	@After
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
		    // Take a screenshot on failure
		    TakesScreenshot screenshot = (TakesScreenshot) driver;
		    byte[] screenshotBytes = screenshot.getScreenshotAs(OutputType.BYTES);

		    // Encode the screenshot in Base64 format
		    String base64Screenshot = Base64.getEncoder().encodeToString(screenshotBytes);

		    // Attach the Base64 screenshot to the Cucumber report
		    scenario.attach(screenshotBytes, "image/png", "screenshot");

		    // Attach the screenshot to the Extent report using MediaEntityBuilder
		    ExtentCucumberAdapter.getCurrentStep().fail("Test failed. Screenshot attached.",
		        MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
		}

		// Quit the driver
		if (driver != null) {
			driver.quit();
		}
	}
}
