package com.cydeo.tests.homeworks;

import com.cydeo.utility.BrowserUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;


public class GoogleSearchTesting {

    @DisplayName("Google Search Testing")
    @ParameterizedTest
    @CsvFileSource(resources = "/googleSearch.csv",numLinesToSkip = 1)
    public void test1(String word, String result){
        WebDriverManager.chromedriver().setup();
        WebDriver driver =new ChromeDriver();
        driver.get("https://google.com");
        BrowserUtil.waitFor(2);

        driver.findElement(By.name("q")).sendKeys(word);

        BrowserUtil.waitFor(2);
        driver.findElement(By.name("btnK")).sendKeys(Keys.ENTER);
        BrowserUtil.waitFor(2);

        assertTrue(driver.getTitle().contains(result),"not quite "+driver.getTitle());
        BrowserUtil.waitFor(2);
        driver.quit();


    }


}
