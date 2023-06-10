package ObjectClasses;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class AjioHomePage {
	
	WebDriver driver;
	Actions act;
	
	public AjioHomePage(WebDriver driver)
	{
		this.driver=driver;
	}

	By produtNameForAssert=By.className("(//*[@class='nameCls'])[1]");
	By menu_list=By.xpath("//*[@class='level-first false']/li");
	By logo=By.xpath("//*[@href='/']/img");
	By courselList=By.xpath("(//*[@class='indicators'])[1]/div");
	By section1=By.xpath("(//img[@name='main-banner'])[29]");
	By section2=By.xpath("(//img[@name='main-banner'])[44]");
	By section3=By.xpath("(//img[@name='main-banner'])[52]");
	By section4=By.xpath("(//img[@name='main-banner'])[62]");
	By section5=By.xpath("(//img[@name='main-banner'])[126]");
	By section6=By.xpath("(//img[@name='main-banner'])[134]");
    By discountSec=By.xpath("(//*[@data-gtm-bnr-id='8ae9c855-c96c-4f52-8062-db4acc31d85f'])[2]");
    By searchProduct=By.name("searchVal");
    By searchButton=By.xpath("//*[@type='submit']");
    By filter_searchedProduct=By.cssSelector("label[for='Men']");
    By click_on_product=By.xpath("(//*[@class='rilrtl-lazy-img  rilrtl-lazy-img-loaded'])[1]");
    By productPriceForAssert=By.xpath("(//*/span[@class='price  '])[1]");
	/*
	 * public void pageLogo() throws Exception { driver.findElement(logo).click();
	 * Thread.sleep(5000); }
	 */
    
	/**
	 * method return the product price
	 * @return
	 */
    public String productPriceForAssert() {
    	return driver.findElement(productPriceForAssert).getText();
    }
    
    
    /**
     * will return boolean value if menu list count matches
     * @return
     */
	public boolean menu_list()
	{
	List<WebElement> menu_count=driver.findElements(menu_list);

	if (menu_count.size()==5) {
		return true;
	}
	return false;
	
	
	}
	
	/***
	 * User can give a section name and this method will click on it 
	 * @param menuName
	 */
	public void click_on_menu_list(String menuName)
	{
		List<WebElement> menu_count=driver.findElements(menu_list);
		for(WebElement e:menu_count)
		{
			if(e.getText().equals(menuName))
			{
				e.click();
				break;
			}
		}
	
	
	}
	/***
	 * user can give coursel number and it will click on that element, also this method will return coursel count
	 * @param courCount
	 * @return
	 */
	public int coursel_check(String courCount) {
	List<WebElement> listofCrousal=driver.findElements(courselList);
		System.out.println(listofCrousal.size());
		JavascriptExecutor js=(JavascriptExecutor)driver;
		act=new Actions(driver);
		js.executeScript("window.scrollBy(0,250)");
		for(WebElement e:listofCrousal)
		{
			if(e.getText().equals(courCount))
			{
				act.moveToElement(e).click().build().perform();
				//e.click();
				break;
			}
		}
		
		return listofCrousal.size();
		
	}
	/***
	 * Will return the sections as list
	 * @return
	 */
	public ArrayList<String> sectionsList()
	{
		ArrayList<String> sections=new ArrayList<String>();
		sections.add(driver.findElement(section1).getAttribute("src"));
		sections.add(driver.findElement(section2).getAttribute("src"));
		sections.add(driver.findElement(section3).getAttribute("src"));
		sections.add(driver.findElement(section4).getAttribute("src"));
		sections.add(driver.findElement(section5).getAttribute("src"));
		sections.add(driver.findElement(section6).getAttribute("src"));

		return sections;


	}
	
	
	/***
	 *  this method will click on discount section 
	 */
	public void discount_Section_click()
	{
	JavascriptExecutor js=(JavascriptExecutor)driver;
	js.executeScript("window.scrollBy(0,400)");	
	
	driver.findElement(discountSec).click();
	}
	/***
	 * search method which we will click on search product name given
	 * @param productName
	 * @return
	 * @throws InterruptedException
	 */
	public String searchProduct_Filter_By_Gender(String productName) throws InterruptedException
	{
		 driver.findElement(searchProduct).sendKeys(productName);
		   driver.findElement(searchButton).click();
		   driver.findElement(filter_searchedProduct).click();
		   Thread.sleep(5000);
		   return driver.findElement(By.xpath("(//*[@class='nameCls'])[1]")).getText();
	
	}
	
	/**
	 * This method will click on first product from the filter one
	 * @throws InterruptedException
	 */
	
	public void click_on_Searched_product() throws InterruptedException
	{
		driver.findElement(click_on_product).click();
		Set<String> windows= driver.getWindowHandles();   
	  	Thread.sleep(3000);
	  Iterator<String> values=windows.iterator();
	  	
	  while(values.hasNext())
	  {
		String parentWindow= values.next();
		driver.switchTo().window(parentWindow);
		String childWindow= values.next();
		driver.switchTo().window(childWindow);
		  
	  }
	  	
	      
	}
	
}
