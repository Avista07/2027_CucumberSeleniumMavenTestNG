package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
  private static Properties properties;

  public static synchronized void loadProperties() {
    if (properties != null) {
      return;
    }

    // Defaults to "qa" if -Denv is not passed via CLI
    String env = System.getProperty("env", "qa").toLowerCase().trim();
    String filePath = "src/test/resources/config/" + env + ".properties";

    properties = new Properties();
    try (FileInputStream fis = new FileInputStream(filePath)) {
      properties.load(fis);
    } catch (IOException e) {
      throw new RuntimeException("Failed to load configuration file for environment: " + env, e);
    }
  }

  public static String getProperty(String key) {
    // 1. Check CLI system properties (-Dkey=value) first
    String cliValue = System.getProperty(key);
    if (cliValue != null && !cliValue.trim().isEmpty()) {
      return cliValue.trim();
    }

    // 2. Fallback to the loaded environment .properties file
    if (properties == null) {
      loadProperties();
    }

    String fileValue = properties.getProperty(key);
    if (fileValue == null) {
      throw new RuntimeException(
          "Property '" + key + "' not found in CLI arguments or active config file.");
    }
    return fileValue.trim();
  }
}
