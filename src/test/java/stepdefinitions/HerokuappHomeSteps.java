package stepdefinitions;

import context.ScenarioContext;
import drivers.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageobjects.HerokuappHomePOM;

public class HerokuappHomeSteps {

  // private final WebDriver driver;
  // private final ScenarioContext scenarioContext;

  // public HerokuappHomeSteps(ScenarioContext context) {
  //   this.scenarioContext = context;
  //   this.driver = DriverManager.getDriver();
  // }

  private final HerokuappHomePOM herokuappHomePOM;
  private final ScenarioContext scenarioContext;

  public HerokuappHomeSteps(ScenarioContext scenarioContext) {
    this.scenarioContext = scenarioContext;
    this.herokuappHomePOM = new HerokuappHomePOM(DriverManager.getDriver());
  }

  @Given("Navigate to Herokuapp")
  public void Navigate_to_Herokuapp() {
    String title = herokuappHomePOM.getTitle();
    System.out.println(title);
  }

  @Then("Herokuapp home page title is correct")
  public void Herokuapp_home_page_title_is_correct() {
    String title = herokuappHomePOM.getTitle();
    System.out.println(title);
  }
}
