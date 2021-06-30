package StepDefinitions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

//@RunWith(Cucumber.class)
@CucumberOptions(features="classpath:features",
        glue={"classpath:StepDefinitions"},
        plugin = { "pretty","html:target/reports/HTMLReport.html",
                "json:target/reports/cucumber.json",
                "junit:target/reports/cucumber.xml"})
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
