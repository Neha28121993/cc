package utilities;

import base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class utils extends TestBase {

    public void mousehover(WebElement ele) {
        Actions ac = new Actions(driver);
        ac.moveToElement(ele).build().perform();
    }

    public void selectbyvalue(WebElement ele,String val)
    {
        Select sc=new Select(ele);
        sc.selectByValue(val);

    }
    public void selectbytext(WebElement ele,String text)
    {
        Select sc=new Select(ele);
        sc.selectByVisibleText(text);
    }
    public void selectbyindex(WebElement ele,int index)
    {
        Select sc=new Select(ele);
        sc.selectByIndex(index);
    }

}
