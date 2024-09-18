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
        String features = System.getProperty("features");
        if (features != null && !features.isEmpty()) {
            System.out.println("Using feature files from system property: " + features);
            System.setProperty("cucumber.features", features);
        }
    }
}
