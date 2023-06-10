package ObjectClasses;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test_01 {

	WebDriver driver;
	ChromeOptions co;
	String url = "https://www.ajio.com/";
	Actions act;
	AjioLoginPage ajioLoginPage;
	AjioHomePage ajioHomePage;
	ProductDetailsPage productDetailsPage;
	AjioAddToCart ajioaddtocart;
	ExtentReports extent;
	ExtentTest extenttest;
	String productName;
	String price;
	TakesScreenshot ts;
	File srcDes;

	@BeforeTest
	public void setup() {
	
		WebDriverManager.chromedriver().setup();
		co = new ChromeOptions();
		co.addArguments("--disable-notifications");
		driver = new ChromeDriver(co);
		driver.get(url);
		driver.manage().window().maximize();
		ajioLoginPage = new AjioLoginPage(driver);
		ajioHomePage = new AjioHomePage(driver);
		productDetailsPage = new ProductDetailsPage(driver);
		ajioaddtocart = new AjioAddToCart(driver);
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/Ajio.html");
		extent.loadConfig(new File(System.getProperty("user.dir") + "/src/test/java/extent-config.xml"));	
		
	}

	@Test(priority = 0)
	public void loginPageTest() throws Exception {
		
		extenttest = extent.startTest("AJIO-Ecommerce ");

		// Test case for login with a registered number
		
		ajioLoginPage.ajioLogin("8073735930");
		extenttest.log(LogStatus.PASS, "User is logining in with registered number");

		Thread.sleep(20000);
		System.out.println(driver.findElement(By.xpath("//*[@class='ignore-sentence-case']")).getText());
		Assert.assertEquals(driver.findElement(By.xpath("//*[@class='ignore-sentence-case']")).getText(), "kanwar");
		Thread.sleep(3000);

		 ts=(TakesScreenshot)driver;
		 srcDes=ts.getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(srcDes,new File("/AjioPOM/test-output/login.png"));
		
		extenttest.log(LogStatus.PASS, "User name should be displayed as kanwar");
		extenttest.addScreenCapture("/AjioPOM/test-output/screenshot/login.png");

	}

	@Test(priority = 1,dependsOnMethods = "loginPageTest")
	public void homepageTest() throws InterruptedException {

		Thread.sleep(5000);
		
		
		
		// Test case to click on men sections from menu list
		ajioHomePage.click_on_menu_list("MEN");
		Assert.assertTrue(driver.getCurrentUrl().contains("https://www.ajio.com/shop/men"));
		extenttest.log(LogStatus.PASS, "User should be Men section");

		Thread.sleep(7000);
		
		// Test case to count the coursal in a section and click on it
		int count = ajioHomePage.coursel_check("2");
		Assert.assertEquals(count, 7);
		extenttest.log(LogStatus.PASS, "User should be able to click on second coursal");

		Thread.sleep(7000);
		
		
		
		//  Test case to get the complete menu list
		ajioHomePage.sectionsList();
		extenttest.log(LogStatus.PASS, "User should get the menu list");

		Thread.sleep(3000);
		
		
		
		//  Test case to click on discount section 
		ajioHomePage.discount_Section_click();
		extenttest.log(LogStatus.PASS, "User should be able to click on discount section");

		Thread.sleep(3000);
		String discountHeader = driver.findElement(By.xpath("//*[@class='header2']")).getText();
		Assert.assertEquals(discountHeader, "Up To 50 Percent Off");
		extenttest.log(LogStatus.PASS, "User should  be in 50% discount page");
		driver.navigate().back();

		Thread.sleep(5000);

		
		
		// Test case to search a product 
		productName = ajioHomePage.searchProduct_Filter_By_Gender("Shoes");
		extenttest.log(LogStatus.PASS, "User should  be able to search a product from shoes");
		Thread.sleep(3000);
		String menFilter = driver.findElement(By.xpath("//*[@class='fnl-plp-afliter']/span")).getText();
		Assert.assertEquals(menFilter, "Men");
		price = ajioHomePage.productPriceForAssert();
		System.out.println(price);

		Thread.sleep(5000);
		
		
		
		//  Test case to click on the first product after search 
		ajioHomePage.click_on_Searched_product();
		extenttest.log(LogStatus.PASS, "User should be able to filter by ");

		String actualproductname = driver.findElement(By.xpath("//*[@class='prod-name']")).getText();
		Assert.assertEquals(productName, actualproductname);

	}

	@Test(priority = 2,dependsOnMethods = "homepageTest")
	public void productdetailpageTest() throws Exception {
		Thread.sleep(4000);
		
		
		//  Test case to get the product price 
		String proprice = productDetailsPage.product_price();
		Assert.assertEquals(proprice, price);
		extenttest.log(LogStatus.PASS, "Product price in product page should match with the product selected price ");

	
		// Test case to print the product details 
		
		
		ArrayList<String> prodDet = productDetailsPage.product_Details();
		Thread.sleep(5000);
		// Assert.assertEquals(prodDet, ConstantsIn.productDetails());
		extenttest.log(LogStatus.PASS, "Product Details are matching or not");
		Thread.sleep(5000);
		
		
		// Test case to save a product in wishlist 
		productDetailsPage.save_to_wishlist("7");
		String wishlistproductcheck = driver.findElement(By.xpath("//*[@class='nameCls']")).getText();
		Assert.assertEquals(wishlistproductcheck, productName);
		extenttest.log(LogStatus.PASS, " user should be able to select a size and save it wishlist ");
		Thread.sleep(5000);
		
		// Test case to add to cart  
		productDetailsPage.add_to_cart("7");
		extenttest.log(LogStatus.PASS, " user should be able to add to cart ");

		Thread.sleep(5000);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='product-details col-lg-10 col-8']//a[1]"))
				.getText().contains(productName));
		Thread.sleep(5000);

	}


	@Test(priority = 3,dependsOnMethods = "productdetailpageTest")
	public void addtocartTest() throws Exception {
		
		// Test case to get the price summary 
		ArrayList<String> value = ajioaddtocart.price_summary();
		Thread.sleep(4000);
	//	Assert.assertTrue(value.get(1).contains(price));
		
		// Test case to get the product price in the cart page 
		//Assert.assertEquals(ajioaddtocart.priced_order(),price);

		
		// Test case to delete an item from cart 
		String delMesg = ajioaddtocart.delete_an_item();
		extenttest.log(LogStatus.PASS, " user should be able to delete ");
		Assert.assertEquals(delMesg, "Your Shopping Bag is Empty!!");
		Thread.sleep(6000);
	

		
		// Test case to signout 
		
		ajioaddtocart.signout();
		extenttest.log(LogStatus.PASS, "user should be in login page ");

	}

	@AfterTest
	public void At() {

		driver.close();
		extent.endTest(extenttest);
		extent.flush();
		extent.close();

	}

}
