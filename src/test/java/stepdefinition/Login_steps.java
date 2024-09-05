package stepdefinition;

import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Browser;
import pages.Login_page;


public class Login_steps {
	WebDriver driver=Browser.driver;
	Login_page lp;

	@Given("user launches the url")
	public void user_launches_the_url() throws InterruptedException {
		lp = new Login_page(driver);
		Thread.sleep(3000);
		lp.homepageloginbtn();
	}

	@When("user enters {string} and {string}")
	public void user_enters_and(String username, String password) throws Throwable {
		Thread.sleep(3000);
		lp.userentersusername(username);
		lp.userenterspassword(password);
		
	}
	@And("user tries to logged into the application")
	public void user_tries_to_logged_into_the_application() {
		lp.Loginbtn();
	}

	@Then("validate the Alerts {string}")
	public void validate_the_alerts(String string) {
		lp.validatehomepage(string);
	}
}
