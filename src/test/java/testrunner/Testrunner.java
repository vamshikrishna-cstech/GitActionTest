package testrunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "classpath:features",  // Use classpath for features path
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
