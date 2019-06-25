import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
 
/*****************************************************************************
 * Author:      Xavier Cuadrado
 * Description: Selenium TestNG test.
 * 				test01VerifyTitle
 *             		It opens AppFinder page and prints and checks its title.
 *             	test02PathFinderDescription
 *             		It verifies AppFinder description text
 *              test0NSearchApp 
 *             		Given a keyword it searchs for apps with provided keyword.
*******************************************************************************/
 
public class AppFinder {
 
    //-----------------------------------Global Variables-----------------------------------
    //Declare a Webdriver variable
    private static WebDriver driver = null;
    private static WebElement element = null;
 
    //Declare a test URL variable
    private String testURL = "https://www.getapp.com/appfinder";
    private String keyword = "pdf";
    private String app = "Reader Plus";
    
    //-----------------------------------Test Setup-----------------------------------
    @BeforeMethod
    public void setupTest (){
    	
    	System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
        //Create a new ChromeDriver
        driver = new ChromeDriver();
 
        //Go to www.swtestacademy.com
        driver.navigate().to(testURL);
        driver.manage().window().maximize();
    }
 
    //-----------------------------------Tests-----------------------------------
    @Test
    public void test01VerifyTitle () {
        //Get page title
        String title = driver.getTitle();
       
        //Print page's title
 
        //Assertion
        Assert.assertEquals(title, "AppFinder Tool for Software Requirements Research | GetApp®", "Title assertion is failed!");
    }

    @Test
    public void test03PathFinderDescription() {
    	String actual_desc1=null, expected_desc1 ="AppFinder Tool for Software Requirements Research";
    	String actual_desc2=null, expected_desc2 = "Our AppFinder tool will help you sift through the noise and create a shortlist of apps that best meet your needs. With AppFinder, you are not only able to pick what features matter to you, you can also assign their importance. It's a tool that will save you time and money to find the best business app for your company. Check out some of our most popular AppFinder categories below";
    	WebDriverWait wait = new WebDriverWait(driver, 10);
    	
    	element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"secondary-header\"]/div/div[2]/h1")));
        actual_desc1=element.getText();
    	Assert.assertEquals(actual_desc1, expected_desc1, "AppFinder Title Description Title does not match!");
    	
    	element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"yield\"]/div/div[1]/div/p")));
        actual_desc2=element.getText();
    	Assert.assertEquals(actual_desc2, expected_desc2, "AppFinder Main Description does not match!");
    	
    	
    }
    @Test
    public void test0NSearchApp () {

    
    	WebDriverWait wait = new WebDriverWait(driver, 10);
    	element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("appendedInputButton")));
         element.sendKeys(keyword+Keys.RETURN);
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(app)));  
        Assert.assertEquals(element.getText(),app,"Searched element assertion is failed!");
        element.click();

    }
    //-----------------------------------Test TearDown-----------------------------------
    @AfterMethod
    public void teardownTest (){
        //Close browser and end the session
        driver.quit();
    }
}