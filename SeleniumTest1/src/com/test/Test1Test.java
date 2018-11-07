package com.test;

 import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
 
public class Test1Test {
 
	public WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
@BeforeMethod	
	public void SetUpMethod()
	{
		try {
			//System.setProperty("webdriver.chrome.driver", "C:\\Users\\swapn\\OneDrive\\Desktop\\MyProgramz\\MyDownloads\\Softwares\\Selenium\\chromedriver_win32\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 5000);		
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			js =(JavascriptExecutor)driver;
		} catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

 @Test (priority = 0)
 /*This method verifies the Page title*/
 public void test_Page_Title()
 {
	 String title = null;
	 driver.get("https://plinqit.com/");
	 title = driver.getTitle();
	 System.out.println("Title" + title);
	 assertTrue(title.equalsIgnoreCase("Plinqit - Fun Size Savings |"));
	 driver.close();
 }
 @Test (priority = 1)
 /*This method verifies the login functionality with 
  * invalid credentials*/
 public void test_Login_InvalidCredentials() throws InterruptedException
 {
	 driver.get("https://plinqit.com/");
	 WebElement login = driver.findElement(By.xpath("//*[@id=\"mk-header-1\"]/div/div/div[2]/div/div[2]/div"));
	 js.executeScript("arguments[0].click();",login);
	 Thread.sleep(1000);
	 driver.findElement(By.id("email")).sendKeys("abc@gmail.com");
	 driver.findElement(By.id("password")).sendKeys("abc123456");
	 Thread.sleep(1000);
	 driver.findElement(By.xpath("//*[@id=\"form_login\"]/div[3]/button")).click();
	 Thread.sleep(1000);
	 Boolean display = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]")).isDisplayed();
	 driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div")).click();
	 Thread.sleep(1000);
	 assertTrue(display);
	 driver.close();
 }
 
 @Test (priority = 2)
 /*This method verifies 
  * the FAQ page*/
 public void test_FAQ_Page() throws InterruptedException
 {
	 String url = null;
	 driver.navigate().to("https://plinqit.com/");
	 WebElement element = driver.findElement(By.xpath("//*[@id=\"mk-header-1\"]/div/div/div[2]/div/div[1]/div/div[1]"));
	 js.executeScript("arguments[0].click();",element);
	 Thread.sleep(1000);
	 element = driver.findElement(By.cssSelector("#menu-item-333 > a > span"));
	 js.executeScript("arguments[0].click();",element);
	 url = driver.getCurrentUrl();
	 Thread.sleep(1000);
	 assertTrue(url.equals("https://plinqit.com/faqs/"));
	 driver.close();
 }
  @AfterMethod
 public void Close()
 {
	 //driver.close();
 }
}