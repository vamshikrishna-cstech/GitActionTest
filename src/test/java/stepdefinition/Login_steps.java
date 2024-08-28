package stepdefinition;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Login_page;


public class Login_steps {
	WebDriver driver;
	Login_page lp;

	@Given("user launches the url")
	public void user_launches_the_url() {
		lp = new Login_page(driver);
		lp.setUpBrowser();
	}

	@When("user enters {string} and {string}")
	public void user_enters_and(String username, String password) throws Throwable {
		lp = new Login_page(driver);
//		lp.userentersusername(username);
//		lp.userenterspassword(password);
		
	}

	@Then("user should be logged into the application")
	public void user_should_be_logged_into_the_application() {
		//lp = new Login_page(driver);
		
	}
	
	@And("user tries to logged into the application")
	public void user_tries_to_logged_into_the_application() {
		//lp.Loginbtn();
	}

	@Then("validate the Alerts {string}")
	public void validate_the_alerts(String string) {
		//lp.validatehomepage(string);
	}
}
