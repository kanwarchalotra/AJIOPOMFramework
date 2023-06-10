package ObjectClasses;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class AjioAddToCart {

WebDriver driver;


public AjioAddToCart(WebDriver driver)
{
	this.driver=driver;
}

By price=By.xpath("//*[@class='net-price best-price-strip']");
By addWithDiscount=By.xpath("//*[@class='price-value bold-font']");
By delete=By.xpath("//*[@class='delete-btn']");
By empty_cart_mesg=By.xpath("//*[@class='empty-msg']");
By logo=By.xpath("//a[@href='/']//img");
By signout=By.xpath("(//*[@class=' guest-header']/ul/li/a)[2]");
By delPopup=By.xpath("(//*[@class='delete-btn'])[2]");
By order_details=By.xpath("//*[@class='price-summary']");
By total_order_amount=By.xpath("//*[@class='price-value bold-font']");

/**
 * returns the product price 
 * @return
 */

public String product_cart_price() {
	return driver.findElement(price).getText();
}

/***
 * this method is used to delete an item
 * @return
 * @throws Exception
 */

public String delete_an_item() throws Exception
{
	
	driver.findElement(delete).click();
	Thread.sleep(3000);
	driver.findElement(delPopup).click();
	Thread.sleep(2000);

return driver.findElement(empty_cart_mesg).getText();
	
}

/**
 * method returns the price of the product
 * @return
 */
public String priced_order()
{
	return driver.findElement(total_order_amount).getText();
}
/**
 * this method will return the list of price summary
 * @return
 */

public ArrayList<String> price_summary()
{
	List<WebElement> pricedet=driver.findElements(order_details);
	ArrayList<String> li=new ArrayList<String>();
	for(WebElement e:pricedet)
	{
	String val=e.getText();
	li.add(val);
	}
	System.out.println(li);
	return li;
}


/**
 * this method will signout an user
 * @throws Exception
 */
public void signout() throws Exception
{	Thread.sleep(4000);
  Actions act=new Actions(driver);
     act.click(driver.findElement(logo)).build().perform();

     Thread.sleep(8000);
	act.click(driver.findElement(signout)).build().perform();

	
}




	
}
