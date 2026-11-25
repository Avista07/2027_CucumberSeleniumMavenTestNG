package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

@CucumberOptions(
    features = "src/test/resources/features",
    tags = "",
    glue = {"stepdefinitions", "hooks"},
    monochrome = true)
public class RunCucumberTest extends AbstractTestNGCucumberTests {

  @Override
  @DataProvider(parallel = true)
  public Object[][] scenarios(ITestContext context) {
    return super.scenarios(context);
  }
}
