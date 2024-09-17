package stepdefinition;

import org.openqa.selenium.WebDriver;

import pages.Browser;
import pages.Childs_Daily_Activity_Page;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


public class Childs_Daily_Activity_Steps {
	WebDriver driver=pages.Browser.driver;
	pages.Childs_Daily_Activity_Page cp;
	@Given("User will click on View Report button of Childs Daily Activity")
	public void user_will_click_on_view_report_button_of_childs_daily_activity() {
		cp= new pages.Childs_Daily_Activity_Page(driver);
		cp.viewbtn();
	}

	@Then("User tries to Generate the Childs Daily Activity Report {string} {string} {string}")
	public void user_tries_to_generate_the_childs_daily_activity_report(String string, String string2, String string3) throws Throwable  {
		cp.parentchildwindow();
		cp.triestoadd(string, string2, string3);
	}

	@Then("User will validate the Childs Daily Activity Report Alerts {string}")
	public void user_will_validate_the_childs_daily_activity_report_alerts(String string) {
		cp.pagevalidation(string);
	}
	
}
