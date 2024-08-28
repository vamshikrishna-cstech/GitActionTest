package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login_page {
	WebDriver driver;
	WebDriverWait wait;

	@FindBy(id = "email1")
	WebElement txt_username;

	@FindBy(id = "password")
	WebElement txt_password;

	@FindBy(id = "txtlog")
	WebElement loginbtn;

	// @FindBy(xpath = "//title[text()='Vcare Cloud - DASHBOARD']")
	// WebElement homepage;

	@FindBy(xpath = "//p[text()='Employees Count']")
	WebElement homepage;

	public Login_page(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void userentersusername(String username) {
		txt_username.sendKeys(username);
	}

	public void userenterspassword(String password) {
		txt_password.sendKeys(password);
	}

	public void Loginbtn() {
		loginbtn.click();
	}

	public void validatelogin() {
		if (driver.getTitle().contains("VCareCloud - Sign In")) {
			System.out.println(driver.getTitle());
		}
	}

	public void validatehomepage(String string) {

		try {
			if (homepage.isDisplayed()) {
				System.out.println(string);
			}
		} catch (Exception e) {
			if (txt_username.isDisplayed()) {
				System.out.println(string);
			}

		}

	}

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
			options.addArguments("--window-size=1920x1080"); // Optional: Set window size
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

}
