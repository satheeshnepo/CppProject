package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features",plugin = "json:target/jsonReports/cucumber-report.json",glue = {"stepdefination"},tags= "@Deleteplace or @Addplace")

public class TestRunner {

}
