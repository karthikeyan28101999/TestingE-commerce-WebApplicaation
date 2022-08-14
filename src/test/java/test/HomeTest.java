package test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.Base;
import pageObject.Index;
import pageObject.Login;

public class HomeTest extends Base {
	public WebDriver driver;
	Logger log;
	@Test(priority = 0)
	public void titleVerify() throws IOException
	{
		String url=propertyFile.getProperty("url");
		driver.get(url);
		
		log.info("landing on the home page");
		String actual=driver.getTitle();
		log.debug("get title of the home page");
		String expect="Your Store";
		log.debug("check title of the page");
		Assert.assertEquals(actual,expect);
		
		//count slider Windows
		Index home=new Index(driver);
		log.debug("check number of slider in the home page");
		int actCount=home.sliders().size();
	    int expectCount=2;
	    Assert.assertEquals(actCount,expectCount);
	    
		/*
		 * String expectImage="iPhone 6"; boolean
		 * ans=home.sliderImage().getAttribute("alt").contains(expectImage);
		 * Assert.assertTrue(ans);
		 */
		
		int actproductCount=home.Featuredproduct().size();
		int expectproductcount=4;
		log.debug("check number of featured product dispalyed in the home page");
		Assert.assertEquals(actproductCount,expectproductcount);
		
		
		String[] products= {"MacBook","iPhone","Apple Cinema 30"+'"',"Canon EOS 5D"};
		List<String> Expectproduct=Arrays.asList(products);
		
		List<String> actualproducts=home.Featuredproduct().stream().map(s->s.getText()).collect(Collectors.toList());
	    System.out.println(Expectproduct);
	    System.out.println(actualproducts);
		boolean check=actualproducts.equals(Expectproduct);
	    System.out.println(check);
	    log.info("check given list of products is displaced in the feature product");
	    Assert.assertTrue(check); 
	    log.info("click my account");
	    home.myAccount().click();
	    int accountActualCount=home.myAccountOption().size();
	    log.debug("to check number of option in account option");
	    Assert.assertEquals(accountActualCount,2);
	}
	@BeforeTest
	public void start() throws IOException
	{
		PropertyConfigurator.configure("resource\\log.properties");
		log=LogManager.getLogger(HomeTest.class.getName());
		log.info("initilize the browser");
		driver=initializeDriver();
	}
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
   
}
