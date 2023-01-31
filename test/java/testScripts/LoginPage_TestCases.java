package testScripts;

import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class LoginPage_TestCases extends TestBase {
    LoginPage lp;
    DashboardPage dp;
    public LoginPage_TestCases()
    {
       super();
    }

    @BeforeMethod
    public void Setup()
    {
        intialization();
        lp=new LoginPage();
        dp=new DashboardPage();
        driver.get(prop.getProperty("url"));
    }

    @Test(dataProvider="credentials",priority=1,testName = "Verify login functionality with different set of data")
    public void loginpage_correctcredentials(String scenario,String name,String pass) {
        lp.login(name, pass);
        if (scenario.equalsIgnoreCase("bothcorrect")) {
            WebElement shippertext = driver.findElement(By.xpath("//b[contains(text(),'Shipper:')]"));
            Assert.assertTrue(shippertext.isDisplayed());
        } else if (scenario.equalsIgnoreCase("bothwrong")) {
            String errormessage= lp.login_errormessage();
            Assert.assertEquals(errormessage, "Username doesn't exist.");
        } else if (scenario.equalsIgnoreCase("correctusername")) {
            String errormessage= lp.login_errormessage();
            Assert.assertEquals(errormessage, "Incorrect Password.");
        } else if (scenario.equalsIgnoreCase("correctpassword")) {
            String errormessage= lp.login_errormessage();
            Assert.assertEquals(errormessage, "Username doesn't exist.");
        } else if (scenario.equalsIgnoreCase("BlankUsernamePass")) {
            String errormessage= lp.login_errormessage();
            Assert.assertEquals(errormessage, "Email and Password can't be left blank.");
        }else if (scenario.equalsIgnoreCase("BlankUsername")) {
            String errormessage= lp.login_errormessage();
            Assert.assertEquals(errormessage, "Email and Password can't be left blank.");
        }else if (scenario.equalsIgnoreCase("Blankpassword")) {
            String errormessage= lp.login_errormessage();
            Assert.assertEquals(errormessage, "Email and Password can't be left blank.");
        }
    }


    @Test(priority=2,testName = "Verify Natgashub text on the login page")
    public void login_Natgashubtext()
    {
        WebElement text=driver.findElement(By.xpath("//div[contains(text(),'NatGasHub')]"));
        Assert.assertTrue(text.isDisplayed());
    }

    @Test(priority=3,testName = "Verify Rememberme text on the login page")
    public void login_rememberme() throws Exception {
        try {
            WebElement text = driver.findElement(By.xpath("//label[contains(text(),'Remember Me')]"));
            Assert.assertTrue(text.isDisplayed());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Remember me checkbox not present");
        }
    }



    @Test(priority=4,testName = "Verify Rememberme checkbox clickable")
    public void login_remembermecheckbox() throws Exception {
        try {
            lp.login_checkbox();
            Thread.sleep(3000);
            lp.login_checkbox();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Test(priority=5,testName = "Verify forgotpassword link on the login page")
    public void login_forgotpassword() throws Exception {
        try {
            WebElement text = driver.findElement(By.linkText("Forgot Password?"));
            Assert.assertTrue(text.isDisplayed());

        }catch(Exception e){
            throw new Exception("Forgot Password link not present");
        }

           }

    @Test(priority=6,testName = "Verify forgotpassword link open forgot password page")
    public void login_forgotpasswordpage() throws Exception {
        try {
            driver.findElement(By.linkText("Forgot Password?")).click();
            WebElement text = driver.findElement(By.linkText("//p[text()='You can reset your password here.']"));
            Assert.assertTrue(text.isDisplayed());

        }catch(Exception e){
           e.printStackTrace();
        }

    }


    @DataProvider(name="credentials")
    public Object[][] getData(){
        return new Object[][]{
                {"bothcorrect","Neha.sharma@enercross.com","Neha@1993" },
                {"bothwrong","Neha.shaa@enercross.com","Neh@93"},
                {"correctusername","Neha.sharma@enercross.com","Neh@93"},
                {"correctpassword","Neha.shma@enercross.com","Neha@93"},
                {"BlankUsernamePass","",""},
                {"BlankUsername","","Neha@1993"},
                {"Blankpassword","Neha.sharma@enercross.com",""},


        };
    }

   @AfterMethod
    public void teardown() throws InterruptedException {

         driver.close();

    }
}
