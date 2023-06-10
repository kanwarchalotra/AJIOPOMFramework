package ObjectClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class AjioLoginPage {
	
	WebDriver driver;
	Actions act;
	
	public AjioLoginPage(WebDriver driver)
	{
	this.driver=driver;	
	}
	
	By Signin=By.xpath("//*[@id='appContainer']/div[1]/div/header/div[1]/ul/li[1]/span");
	By username=By.name("username");
	By loginBttn= By.className("login-btn");
	
	
	/**
	 * user will enter register number
	 * @param mobileNumber
	 */
	
	public void ajioLogin(String mobileNumber)
	{
		act=new Actions(driver);
		WebElement sign=driver.findElement(Signin);
		act.click(sign).build().perform();
		driver.findElement(username).sendKeys(mobileNumber);
		driver.findElement(loginBttn).click();
		
	}
	
	
	
	

}
