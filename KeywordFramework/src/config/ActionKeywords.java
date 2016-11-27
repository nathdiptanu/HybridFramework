package config;

import static dataExecution.DriverScript.OR;
import static org.junit.Assert.assertEquals;
import utility.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import dataExecution.DriverScript;


public class ActionKeywords {
public static WebDriver driver=null;
private static boolean acceptNextAlert = true;

public static By locatorValue(String Object, String data) {
	By by;
	switch (Object) {
	case "id":
		by = By.id(Object);
		break;
	case "name":
		by = By.name(Object);
		break;
	case "xpath":
		by = By.xpath(Object);
		break;
	case "css":
		by = By.cssSelector(Object);
		break;
	case "linkText":
		by = By.linkText(Object);
		break;
	case "partialLinkText":
		by = By.partialLinkText(Object);
		break;
	default:
		by = null;
		break;
	}
	return by;
}

public static void click(String object,String data){
	
	Log.info("Click on WebElement" + object);
    driver.findElement(By.xpath(OR.getProperty(object))).click();
}

public static void refresh(String object,String data){
	
	Log.info("Refresh Page");
    driver.navigate().refresh();
}

public static void click_CSS(String object,String data) throws InterruptedException{
	
	Log.info("Click on WebElement" + object);
    driver.findElement(By.cssSelector(OR.getProperty(object))).click();
    Thread.sleep(2000);
	}

public static void openBrowser(String object,String data){		
	Log.info("Opening Browser");
	try{
		//If value of the parameter is Mozilla, this will execute
		if(data.equals("Mozilla")){
			driver=new FirefoxDriver();
			Log.info("Mozilla browser started");
			}
		else if(data.equals("IE")){
			//You may need to change the code here to start IE Driver
			driver=new InternetExplorerDriver();
			Log.info("IE browser started");
			}
		else if(data.equals("Chrome")){
			System.setProperty("webdriver.chrome.driver","C:\\chromedriver_win32\\chromedriver.exe");
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			Log.info("Chrome browser started");
			}

		int implicitWaitTime=(10);
		driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}catch (Exception e){
		Log.info("Not able to open the Browser --- " + e.getMessage());
		DriverScript.bResult = false;
	}
}

public static void navigate(String object,String data){	
	Log.info("Navigating to URL");
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.get(Constants.URL);
	}

public static void input(String object,String data){
	
	Log.info("Enter the text username");
	driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
	}


public static void waitFor(String object,String data) throws Exception{
	Log.info("Wait for 10 seconds");
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	
	}



public static void click_Logout(String object,String data){
	Log.info("Lick on MyAccount");
	Log.info("Move to element");
	Log.info("Click on Logout");
	Actions act=new Actions(driver);
    WebElement wb=driver.findElement(By.xpath(OR.getProperty(object)));
    act.moveToElement(wb).click().build().perform();
	}


public static void closeBrowser(String object,String data){
	Log.info("Close Browser");
		driver.quit();
	}

public static void closeAlertAndGetItsText(String object,String data) {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      assertEquals(data, alertText);
      return;
    } finally {
      acceptNextAlert = true;
    }
    
  }
  
public static void selectOption(String object,String data) throws InterruptedException {
	
	    String expected=data;
	    List<WebElement> list=driver.findElements(By.xpath(OR.getProperty(object)));
	   
	    for(int i=0;i<list.size()-1;i++){
	    	System.out.println(list.get(i).getText());
	    	if(expected.equals(list.get(i).getText())){
	    		list.get(i).click();
	    		Thread.sleep(2000);
	    		break;
	    	}
	    	
}}

}

