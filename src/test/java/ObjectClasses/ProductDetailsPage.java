package ObjectClasses;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductDetailsPage {

	WebDriver driver;

	public ProductDetailsPage(WebDriver driver) {
		this.driver = driver;
	}

	By productPrice = By.xpath("//*[@class='prod-sp']");

	By productDetails = By.xpath("//*[@class='prod-desc']//ul/li");

	By sizeList = By.xpath("(//*[@class='slick-track'])[3]/div/div/span");
	By saveToWishlist = By.xpath("//*[@class='btn-gray']/span");
	By wishlist = By.xpath("//*[@href='/wishlist']");
	By delete_item_from_wishlist = By.xpath("//*[@class='ic-delete rilrtl-remove-product']");
	By save_to_cart = By.xpath("//*[@class='btn-gold']");
	By add_to_cart = By.xpath("//*[@class='ic-cart ']");
    
	/***
	 * method returns product price
	 * @return
	 */
	public String product_price() {
		return driver.findElement(productPrice).getText();
	}
	

	
	
/***
 * this method return the product details
 * @return
 */
	public ArrayList<String> product_Details() {
		List<WebElement> prodList = driver.findElements(productDetails);
		ArrayList<String> proList = new ArrayList<String>();

		for (WebElement e : prodList) {
			String value = e.getText();
			proList.add(value);

		}
		System.out.println(proList);
		return proList;

	}
/**
 * The method saves to wishlist by giving specific size
 * @param size
 * @throws InterruptedException
 */
	public void save_to_wishlist(String size) throws InterruptedException {
		List<WebElement> sizelist = driver.findElements(sizeList);

		System.out.println(sizelist.size());
		for (WebElement e : sizelist) {
			if (e.getText().equals(size)) {
				Thread.sleep(2000);

				e.click();
				break;
			}
		}
		driver.findElement(saveToWishlist).click();
		Thread.sleep(4000);
		driver.findElement(wishlist).click();

	}

/***
 * this method is to add to cart where user can give the size
 * @param size
 * @throws Exception
 */
	public void add_to_cart(String size) throws Exception {
		Thread.sleep(3000);
		driver.findElement(delete_item_from_wishlist).click();
		Thread.sleep(2000);
		driver.navigate().back();
		Thread.sleep(4000);
		List<WebElement> sizelist = driver.findElements(sizeList);

		System.out.println(sizelist.size());
		for (WebElement e : sizelist) {
			if (e.getText().equals(size)) {
				Thread.sleep(2000);

				e.click();
				break;
			}
		}

		driver.findElement(save_to_cart).click();
		Thread.sleep(3000);
		driver.findElement(add_to_cart).click();

	}

}
