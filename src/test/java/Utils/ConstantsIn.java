package Utils;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

import ObjectClasses.AjioHomePage;

public class ConstantsIn {
	
	WebDriver driver;
	 AjioHomePage ajiohomepage;
	
	
	
	

	public static ArrayList<String>  productDetails()
	{
		ArrayList<String> list=new ArrayList<String>();
		//Wipe with a clean, dry cloth when needed, Croslite upper & sole,
		//Slip-on Styling, 3-month warranty against manufacturing defects,
		//Package contains: 1 pair of sandals,
		//Product Code: 469433553004, About CROCS
	list.add("Wipe with a clean, dry cloth when needed");	
	list.add("Croslite upper & sole");
	list.add("Slip-on Styling");
	list.add("3-month warranty against manufacturing defects");
	list.add("Package contains: 1 pair of sandals");
	list.add("Product Code: 469433553004");
	list.add("About CROCS");
return list;
	
	}
	
	public static ArrayList<String> sectionsList()
	{
		ArrayList<String> list=new ArrayList<String>();
		list.add("https://assets.ajio.com/cms/AJIO/WEB/05062023-d-mhp-top-p1-jack&jones-glito-upto85.jpg");
		list.add("https://assets.ajio.com/cms/AJIO/WEB/05062023-d-mhp-midbanner-p1-dennislingo-upto75.jpg");
		list.add("https://assets.ajio.com/cms/AJIO/WEB/05062023-d-mhp-daily-p1-arrow-aeropostale-min50.jpg");
		list.add("https://assets.ajio.com/cms/AJIO/WEB/01062023-D-MHP-LitSponsorBrands-adidas-4070.jpg");
		list.add("https://assets.ajio.com/cms/AJIO/WEB/05062023-d-mhp-jit-p2-cultsport-alcis-flat50.jpg");
		list.add("https://assets.ajio.com/cms/AJIO/WEB/01062023-D-MHP-HotBrandsPremiumStyles-diesel-fcuk-upto40.jpg");
		
		return list;
	}
}
