package testrunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",  // Default path
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
        if (featuresPath != null && !featuresPath.isEmpty()) {
            System.setProperty("cucumber.features", featuresPath);
        }
    }
}
