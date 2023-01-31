package testScripts;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

public class prac {

    WebDriver driver;

    @BeforeTest
    public void before()
    {
        WebDriverManager.chromedriver().setup();
           driver=new ChromeDriver();
           driver.manage().window().maximize();
           driver.get("https://test.natgashub.com/");


    }

    @Test
    public void test() throws InterruptedException {

        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("document.getElementById('Email').value='Neha.sharma@enercross.com'");
        js.executeScript("document.getElementById('Password').value='Neha@1993'");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
//js.executeScript("window.scrollBy(0,500)");
        WebElement  ele=driver.findElement(By.xpath("//a[contains(text(),'GasPro@NatGasHub.com')]"));
js.executeScript("arguments[0].scrollIntoView();",ele);
Thread.sleep(5000);

Actions ac=new Actions(driver);
ac.moveToElement(ele).build().perform();
ac.dragAndDrop(ele,ele).build().perform();
ac.doubleClick(ele).build().perform();

  Select sc=new Select(ele);
  sc.selectByIndex(0);
  sc.selectByVisibleText("d");
  sc.selectByValue(" ");
  js.executeScript("window.scrollBy(0,350)");







    }

    @AfterTest
    public void teardown()
    {
driver.close();
    }
}

