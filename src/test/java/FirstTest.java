import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
 
/*****************************************************************************
 * Author:      Onur Baskirt
 * Description: This is the first Selenium TestNG test.
 *              It opens swtestacademy homepage and prints and checks its title.
*******************************************************************************/
 
public class FirstTest {
 
    //-----------------------------------Global Variables-----------------------------------
    //Declare a Webdriver variable
    public WebDriver driver;
 
    //Declare a test URL variable
    public String testURL = "http://www.getapp.com/";
 
    //-----------------------------------Test Setup-----------------------------------
    @BeforeMethod
    public void setupTest (){
    	
    	System.setProperty("webdriver.chrome.driver", "D:\\personal\\selenium\\chromedriver.exe");
        //Create a new ChromeDriver
        driver = new ChromeDriver();
 
        //Go to www.swtestacademy.com
        driver.navigate().to(testURL);
    }
 
    //-----------------------------------Tests-----------------------------------
    @Test
    public void firstTest () {
        //Get page title
        String title = driver.getTitle();
 
        //Print page's title
        System.out.println("Page Title: " + title);
 
        //Assertion
        Assert.assertEquals(title, "Business Software Reviews, SaaS & Cloud Applications Directory | GetApp®", "Title assertion is failed!");
    }
 
    //-----------------------------------Test TearDown-----------------------------------
    @AfterMethod
    public void teardownTest (){
        //Close browser and end the session
        driver.quit();
    }
}