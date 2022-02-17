package com.cydeo.tests.homeworks;

import com.cydeo.utility.BrowserUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryLoginTest {

    @DisplayName("Library Login Testing")
    @ParameterizedTest
    @CsvFileSource(resources = "/libraryLogin.csv",numLinesToSkip = 1)
    public void testLogin(String email, String password, String currentURL){

        WebDriverManager.chromedriver().setup();
        WebDriver driver =new ChromeDriver();

        driver.get("https://library2.cybertekschool.com/login.html");
        BrowserUtil.waitFor(2);
        driver.findElement(By.id("inputEmail")).sendKeys(email);
        BrowserUtil.waitFor(2);
        driver.findElement(By.id("inputPassword")).sendKeys(password);
        BrowserUtil.waitFor(2);
        driver.findElement(By.tagName("button")).click();

        BrowserUtil.waitFor(2);
        assertTrue(driver.getCurrentUrl().contains(currentURL));

        BrowserUtil.waitFor(2);
        driver.quit();

    }


}
