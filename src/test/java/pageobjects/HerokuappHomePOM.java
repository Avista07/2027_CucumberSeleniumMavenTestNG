package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HerokuappHomePOM extends BasePage {

  private final By firstProductTitle = By.cssSelector(".inventory_item_name");
  private final By firstAddToCartBtn = By.cssSelector(".btn_inventory");
  private final By cartBadge = By.className("shopping_cart_badge");

  public HerokuappHomePOM(WebDriver driver) {
    super(driver);
  }

  public void Navigate_to_Herokuapp() {
    driver.get("https://www.facebook.com");
  }

  public String getTitle() {
    return driver.getTitle();
  }

  public String captureFirstProductName() {
    return getText(firstProductTitle);
  }

  public void addFirstProductToCart() {
    click(firstAddToCartBtn);
  }

  public String getCartItemCount() {
    return getText(cartBadge);
  }
}
