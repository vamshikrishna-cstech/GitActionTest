package testrunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "@features",  // Placeholder to be replaced by system property
    glue = {"stepdefinition", "pages"},
    tags = "@smoketest or @regression", // You can dynamically modify tags if needed
    monochrome = true,
    plugin = {
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
    }
)
public class Testrunner {
    static {
        // Replace placeholder with actual path from system property
        String featuresPath = System.getProperty("features", "src/test/resources/features");
        System.setProperty("features", featuresPath);
    }
}
