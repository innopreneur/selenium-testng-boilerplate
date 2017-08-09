package org.test.testcases;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.test.config.BrowserConfiguration;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.yaml.snakeyaml.Yaml;

public class TestBase {
    private BrowserConfiguration config;

    public BrowserConfiguration getConfig() {
        System.out.println("Prop: " +  System.getProperty("webdriver.chrome.driver"));
        return this.config;
    }


    @BeforeSuite
    @Parameters("testPropertiesFilePath")
    public void setupTestEnvironment(String testPropertiesFilePath) throws IOException{
        InputStream input = null;
        Yaml yaml = new Yaml();  
        input = Files.newInputStream( Paths.get(testPropertiesFilePath)); 
             this.config = yaml.loadAs(input, BrowserConfiguration.class);
             System.setProperty("webdriver.chrome.driver", (String) this.config.getChrome().get("driverPath"));
    }
}
