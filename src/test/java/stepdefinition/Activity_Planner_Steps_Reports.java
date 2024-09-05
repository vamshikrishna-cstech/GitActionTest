package stepdefinition;

import org.openqa.selenium.WebDriver;

import pages.Activity_Planner_page_report;
import pages.Browser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


public class Activity_Planner_Steps_Reports {
	WebDriver driver=Browser.driver;
	Activity_Planner_page_report ap;
	
	@Then("user will click on Reports Feature")
	public void user_will_click_on_reports_feature() {
		ap=new Activity_Planner_page_report(driver);
		ap.Reportsfeature();
	}

	@Given("User will click on View Report button of Activity Planner")
	public void user_will_click_on_view_report_button_of_activity_planner() {
		ap.viewreport();
	}

	@Then("User tries to Generate the Report {string} {string} {string} {string}")
	public void user_tries_to_generate_the_report(String string, String string2, String string3, String string4) {
//		ap.parentchildwindow();
//		ap.triestoadd(string, string2, string3, string4);
	}

	@Then("Validate the Alerts and Report {string}")
	public void validate_the_alerts_and_report(String string) {
		//ap.alerts(string);
	}

}
