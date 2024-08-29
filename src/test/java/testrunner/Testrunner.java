package testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features/Login.feature",
    glue = "stepdefinition",
    monochrome = true,
    plugin = {
        "pretty", // Optional, adds more readable output in the console
        "tech.grasshopper.extentreports.cucumber.adapter.ExtentCucumberAdapter:" // Generates the Extent report
    }
)
public class Testrunner {

}
