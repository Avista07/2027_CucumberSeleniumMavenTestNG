package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {
  private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

  public static WebDriver getDriver() {
    if (driverThreadLocal.get() == null) {
      ChromeOptions options = new ChromeOptions();
      // Recommended for stability when running multiple browsers in parallel
      // options.addArguments("--headless=false", "--disable-gpu", "--window-size=1920,1080");
      options.addArguments("--window-size=1920,1080");
      driverThreadLocal.set(new ChromeDriver(options));
    }
    return driverThreadLocal.get();
  }

  public static void quitDriver() {
    if (driverThreadLocal.get() != null) {
      driverThreadLocal.get().quit();
      driverThreadLocal.remove(); // Crucial to prevent memory leaks
    }
  }
}
