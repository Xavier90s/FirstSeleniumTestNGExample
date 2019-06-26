import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Reporter;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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
 *              test03AppFinderCategories
 *					It navigates to all AppFinder categories
 *				test04AppFinderCriteriaFeatures
 *					It verifies all Criteria Features
 *				test05AppFinderCriteria
 *					It completes an AppFinder search by criteria
 *              test0NMainSearch 
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
    	//driver.get("chrome://settings/");
    	//((JavascriptExecutor) driver).executeScript("chrome.settingsPrivate.setDefaultZoom(0.5);");
        //Go to getapp.com
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
    public void test02PathFinderDescription() {
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
    public void test03AppFinderCategories() {
    	String actualurl = null;
    	String expectedurl1="https://www.getapp.com/finance-accounting-software/accounting/appfinder/requirements/";
    	String expectedurl2="https://www.getapp.com/finance-accounting-software/billing-invoicing/appfinder/requirements/";
    	String expectedurl3="https://www.getapp.com/customer-management-software/crm/appfinder/requirements/";
    	String expectedurl4="https://www.getapp.com/customer-service-support-software/customer-service/appfinder/requirements/";
    	String expectedurl5="https://www.getapp.com/customer-service-support-software/help-desk-ticketing/appfinder/requirements/";
    	String expectedurl6="https://www.getapp.com/hr-employee-management-software/human-resources/appfinder/requirements/";
    	String expectedurl7="https://www.getapp.com/operations-management-software/inventory-management/appfinder/requirements/";
    	String expectedurl8="https://www.getapp.com/marketing-software/marketing-automation/appfinder/requirements/";
    	String expectedurl9="https://www.getapp.com/project-management-planning-software/project-management/appfinder/requirements/";
    	

    	actualurl=appFinderCategory("Accounting");
        Assert.assertEquals(actualurl,expectedurl1,"Redirect to accounting appfinder requirements fails!");
        
        driver.navigate().back();
        actualurl=appFinderCategory("Billing & Invoicing");
        Assert.assertEquals(actualurl,expectedurl2,"Redirect to Billing & Invoicing appfinder requirements fails!");
        
        driver.navigate().back();
        actualurl=appFinderCategory("CRM");
        Assert.assertEquals(expectedurl3,actualurl,"Redirect to CRM appfinder requirements fails!");
    
        
        driver.navigate().back();
        actualurl=appFinderCategory("Customer Service");
        Assert.assertEquals(expectedurl4,actualurl,"Redirect to Customer Service appfinder requirements fails!");
        
        
        
        driver.navigate().back();
        actualurl=appFinderCategory("Help Desk & Ticketing");
        Assert.assertEquals(expectedurl5,actualurl,"Redirect to Help Desk & Ticketing appfinder requirements fails!");
  
        
        driver.navigate().back();
        actualurl=appFinderCategory("Human Resources (HR)");
        Assert.assertEquals(expectedurl6,actualurl,"Redirect to Human Resources (HR)  appfinder requirements fails!");
     
        driver.navigate().back();
        actualurl=appFinderCategory("Inventory Management");
        Assert.assertEquals(expectedurl7,actualurl,"Redirect to Inventory Management appfinder requirements fails!");
       
        
        driver.navigate().back();
        actualurl=appFinderCategory("Marketing Automation");
        Assert.assertEquals(expectedurl8,actualurl,"Redirect to Marketing Automation appfinder requirements fails!");
       
        
        driver.navigate().back();
        actualurl=appFinderCategory("Project Management");
        Assert.assertEquals(expectedurl9,actualurl,"Redirect to Project Management appfinder requirements fails!");
       
    }
    
    public String appFinderCategory(String categoryLink)
    {	
    	String actual = null;
      	element = driver.findElement(By.linkText(categoryLink));
      	Actions actions = new Actions(driver);
      	actions.moveToElement(element);
      	actions.perform();
      	element.click();
        actual=driver.getCurrentUrl();
        return actual;
    }
    
    public String getFeatureDetails(String feature,int detailpos) {
    	WebDriverWait wait = new WebDriverWait(driver, 10);
    	String actualfeature=null, detailedfeature=null;
        element = driver.findElement(By.linkText(feature)); 
        actualfeature=element.getText();
        Assert.assertEquals(actualfeature, feature);

    	if(feature.contains("Customization") == false) {
          	Actions actions = new Actions(driver);
          	actions.moveToElement(element);
          	actions.perform();
          	element.click();
    		element.click();
    	}
      	element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='collapse-"+detailpos+"']/div/table/tbody")));
    	detailedfeature=element.getText();
    	try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	element = driver.findElement(By.linkText(feature));

    	element.click();
    	
    	return detailedfeature;
    }
    @Test
    public void test04AppFinderCriteriaFeatures () {
  
        driver.navigate().to(testURL);
    	String actualurl=null, actualfeaturedesc=null;
    	String expectedurl="https://www.getapp.com/project-management-planning-software/project-management/appfinder/requirements/";
    	actualurl=appFinderCategory("Project Management");
    	Assert.assertEquals(actualurl,expectedurl,"Redirect to Project Management appfinder requirements fails!");
    	
    	actualfeaturedesc=getFeatureDetails("Customization",4);
    	Assert.assertTrue(actualfeaturedesc.contains("Automatic Notifications")&&actualfeaturedesc.contains("Automatic Reminders")&&actualfeaturedesc.contains("Configurable Workflow")&&actualfeaturedesc.contains("Customizable Reporting")&&actualfeaturedesc.contains("Customizable Templates"));
        
    	((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight/5)");
    	actualfeaturedesc=getFeatureDetails("Intelligence and Reporting",5);
    	Assert.assertTrue(actualfeaturedesc.contains("Activity Management")&&actualfeaturedesc.contains("Activity Tracking")&&actualfeaturedesc.contains("Progress Reports")&&actualfeaturedesc.contains("Activity Dashboard")&&actualfeaturedesc.contains("Milestone Tracking"));
        
    	((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight/4.5)");
    	actualfeaturedesc=getFeatureDetails("Usability",7);
    	Assert.assertTrue(actualfeaturedesc.contains("Document Storage")&&actualfeaturedesc.contains("File Transfer")&&actualfeaturedesc.contains("Multi-Currency")&&actualfeaturedesc.contains("Drag & Drop Interface")&&actualfeaturedesc.contains("Access Control"));
        
    	((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight/4)");
    	actualfeaturedesc=getFeatureDetails("Task Management",9);
    	Assert.assertTrue(actualfeaturedesc.contains("Recurring Tasks")&&actualfeaturedesc.contains("Task Planning")&&actualfeaturedesc.contains("Task Scheduling")&&actualfeaturedesc.contains("Task Tracking")&&actualfeaturedesc.contains("To-Do List"));
        
       	((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight/3)");
       	actualfeaturedesc=getFeatureDetails("Permissions/Security",10);
     	Assert.assertTrue(actualfeaturedesc.contains("Audit Trail")&&actualfeaturedesc.contains("Permission Management")&&actualfeaturedesc.contains("Single Sign On")&&actualfeaturedesc.contains("Version Control"));
        
    	((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight/3)");
    	actualfeaturedesc=getFeatureDetails("Integration",18);
    	Assert.assertTrue(actualfeaturedesc.contains("Accounting Integration")&&actualfeaturedesc.contains("Email Integration")&&actualfeaturedesc.contains("Third Party Integration")&&actualfeaturedesc.contains("Data Import/Export")&&actualfeaturedesc.contains("API"));
    	 
       	((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight/3)");
       	actualfeaturedesc=getFeatureDetails("Project Planning and Management",19);
      	Assert.assertTrue(actualfeaturedesc.contains("Gantt Charts")&&actualfeaturedesc.contains("Project Accounting")&&actualfeaturedesc.contains("Project Notes")&&actualfeaturedesc.contains("Project Planning")&&actualfeaturedesc.contains("Project Tracking")&&actualfeaturedesc.contains("Template Management"));
        
    	((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight/3)");
    	actualfeaturedesc=getFeatureDetails("Collaboration",20);
    	Assert.assertTrue(actualfeaturedesc.contains("Collaborative Review")&&actualfeaturedesc.contains("Communication Management")&&actualfeaturedesc.contains("Chat")&&actualfeaturedesc.contains("Collaboration Tools"));
    	((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight/3)");
    	actualfeaturedesc=getFeatureDetails("Accounting",21);
      	Assert.assertTrue(actualfeaturedesc.contains("Financial Management")&&actualfeaturedesc.contains("Invoice Management")&&actualfeaturedesc.contains("Job Costing")&&actualfeaturedesc.contains("Resource Management")&&actualfeaturedesc.contains("Budgeting")&&actualfeaturedesc.contains("Time & Expense Tracking"));
      	 
    	((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight/(3))");
    	actualfeaturedesc=getFeatureDetails("Bug and Issue tracking",22);
    	Assert.assertTrue(actualfeaturedesc.contains("Issue Management")&&actualfeaturedesc.contains("Status Reporting")&&actualfeaturedesc.contains("Status Tracking")&&actualfeaturedesc.contains("Status Reporting")&&actualfeaturedesc.contains("Issue Tracking")&&actualfeaturedesc.contains("Bug Tracking"));
     }
    
    @Test
    public void test05AppFinderCriteria () {
    	String actualurl=null;
    	String expectedurl="https://www.getapp.com/project-management-planning-software/project-management/appfinder/requirements/";
    	actualurl=appFinderCategory("Project Management");
    	Assert.assertEquals(expectedurl,actualurl,"Redirect to Project Management appfinder requirements fails!");	
    	element=driver.findElement(By.xpath("//*[@id=\"price-block\"]/div/div/a[5]"));
    	element.click();
    	element=driver.findElement(By.xpath("//*[@id=\"device-block\"]/div/div[1]"));
    	element.click();
    	element = driver.findElement(By.xpath("//*[@id='collapse-4']/div/table/tbody"));
    	System.out.println("Feature Criteria"+element.getText());
      	element = driver.findElement(By.xpath("//*[@id='collapse-4']/div/table/tbody/tr[4]/td[2]/div[1]/div/a[3]"));
      	Actions actions = new Actions(driver);
      	actions = new Actions(driver);
      	actions.moveToElement(element);
      	actions.perform();
      	element.click();
      	
       	element = driver.findElement(By.id("build-your-scorecard"));
      	actions = new Actions(driver);
      	actions.moveToElement(element);
      	actions.perform();
      
      	element.click();
      	Reporter.log("App search results displayed in url:"+driver.getCurrentUrl());
      		
    	
    	
    }

    @Test
    public void test0NMainSearch () {

    
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