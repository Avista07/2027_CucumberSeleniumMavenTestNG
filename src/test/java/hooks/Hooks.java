package hooks;

import drivers.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ConfigReader;

public class Hooks {

  @Before
  public void setUp() {
    String url = ConfigReader.getProperty("base.url");
    System.out.println("Before Hooks Running " + "base.url=" + url);
    DriverManager.getDriver().get(url);
  }

  @After
  public void tearDown(Scenario scenario) {
    // if (scenario.isFailed()) {
    //     byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver())
    //             .getScreenshotAs(OutputType.BYTES);
    //     scenario.attach(screenshot, "image/png", scenario.getName());
    // }
    System.out.println("After Hooks Running ");
    DriverManager.quitDriver();
  }
}
