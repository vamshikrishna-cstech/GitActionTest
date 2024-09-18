package testrunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = {"classpath:features"},  // Default path; this will be overridden by the system property if set
    glue = {"stepdefinition", "pages"},
    tags = "@smoketest or @regression",
    monochrome = true,
    plugin = {
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
    }
)
public class Testrunner {
    static {
        String featuresPath = System.getProperty("features");
        System.out.println("Features Path: " + featuresPath);
        if (featuresPath != null && !featuresPath.isEmpty()) {
            System.setProperty("cucumber.features", featuresPath);
        }
    }
}
