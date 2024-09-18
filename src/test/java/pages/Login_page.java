package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login_page {
	WebDriver driver;

	@FindBy(css = "input[id='email']")
	WebElement txt_username;

	@FindBy(css = "input[id='password']")
	WebElement txt_password;

	@FindBy(xpath = "//div[contains(text(),'Login')]")
	WebElement homepageloginbtn;
	
	@FindBy(xpath = "//button[text()='Login']")
	WebElement loginbtn;

	@FindBy(xpath = "//p[text()='Employees Count']")
	WebElement homepage;

	public Login_page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void homepageloginbtn() {
		homepageloginbtn.click();
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
}
