package testScripts;

import analyzer.RetryAnalyzer;
import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.MsinPage;
import utilities.utils;

import java.util.List;
import java.util.Scanner;

public class MsinPage_TestCases extends TestBase {
    LoginPage lp;
    DashboardPage dp;
    MsinPage mp;
    utilities.utils ut;
    MsinPage_TestCases()
    {
        super();
    }

    @BeforeMethod
    public void Setup()
    {
        intialization();
        lp=new LoginPage();
        dp=new DashboardPage();
        mp=new MsinPage();
        driver.get(prop.getProperty("dgocurl"));
         mp.msinuiopen();

        }

    @Test(priority =1,testName = "Verify msin page is opened")
    public void open_msinpage()
    {
         String msintext= driver.findElement(By.xpath("//span[contains(text(),'Measurement Information')]")).getText();
        Assert.assertEquals(msintext,prop.getProperty("verifymeasurementtext"));
    }

    @Test(priority =2,testName = "Verify tooltip message in custom views")
    public void msin_tooltiptext() throws InterruptedException {
      String text=  mp.msintooltipverify();
      Assert.assertEquals(text,"To save a custom view: Select your desired pipeline(s) from the pipeline dropdown menu and/or change the column order in the table below by dragging & dropping\n" +
                   "                 any of the column headings to change their order. Then click the 'Save' button.");
   //     Assert.assertEquals(text,prop.getProperty("customviewtolltiptext"));
    }

    @Test(priority=3,testName = "Verify text present with download button")
    public void downloadbuttontext()
    {
       String text= mp.msindownloadmessageverify();
        Assert.assertEquals(text,"(You can download maximum 5,000 records)");

    }

    @Test(priority=4,testName = "Verify Download button is appearing on screen")
            public void downloadbuttonpresent()
    {
              Assert.assertTrue(mp.downloadbuttonpresent());
    }

    @Test(priority=5,testName = "Verify Download button is clickable")
    public void downloadbuttonclickable() throws InterruptedException {
      mp.downloadexcelverify();
       }


    @Test(priority=6,testName = "Verify delete icon working in custom view")
    public void customviewdelete() throws InterruptedException {
        driver.findElement(By.xpath("//div[@class='filter-option']")).click();
        List<WebElement> customlist = driver.findElements(By.xpath("//a[@role='option']"));
        Thread.sleep(2000);
        int noofviews = customlist.size();
        for (int a = 0; a < 2; a++) {
            if (noofviews >= 2) {
                driver.findElement(By.xpath("(//span[@class='glyphicon glyphicon-trash'])[1]")).click();
                Thread.sleep(2000);
                driver.switchTo().alert().accept();
                driver.navigate().refresh();
                List<WebElement> deletedcustomlist = driver.findElements(By.xpath("//a[@role='option']"));
                Thread.sleep(2000);
                int viewcount = deletedcustomlist.size();
                if (viewcount == (noofviews - 1)) {
                    System.out.println("Profile deleted successfully");
                } else {
                    System.out.println("Profile not deleted");
                }
                break;
            } else {
                WebElement showentry = driver.findElement(By.xpath("//select[@name='tblMsin_length']"));
                Select sc = new Select(showentry);
                sc.selectByValue("50");
                driver.findElement(By.id("addbtn")).click();
                driver.findElement(By.id("name")).sendKeys("msin1");
                driver.findElement(By.id("savepopupbtn")).click();
            }
        }
    }

    @Test(priority=7,testName = "Verify save button is appearing in custom view")

    public void customviewsavevisiblity() throws InterruptedException {
       Boolean val= mp.savebuttonvisible();
        Assert.assertTrue(val);
    }

    @Test(priority=8,testName = "Verify save button is working in custom view")

    public void customviewsaveworking() throws InterruptedException {
        mp.savebuttonworking("viewname");
    }


    @AfterMethod
    public void teardown() throws InterruptedException {

        driver.close();

    }

}
