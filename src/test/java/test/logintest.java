package test;

import java.io.IOException;
import java.time.Duration;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.Base;
import pageObject.Index;
import pageObject.Login;

public class logintest extends Base {
	public WebDriver driver;
	Logger log;
	@Test(dataProvider = "userData")
	public void login(String mail,String password,String expect) throws InterruptedException
	{
		log.info("landing homepage page");
		driver.get(propertyFile.getProperty("url"));
		
		Login login=new Login(driver);
		Index home=new Index(driver);
		
		log.info("click myaccount function in home page");
		home.myAccount().click();
                log.info("click login");
	        home.myAccountOption().get(1).click();
	    log.info("page is get direct to loginpage");
	    //to verify it entering login page or Not
	    String loginpagetitle=driver.getTitle();
	    log.info("check title of the login page");
	    Assert.assertEquals(loginpagetitle,"Account Login");
		log.debug("enter username");
		login.email().sendKeys(mail);
		log.debug("enter password");
		login.password().sendKeys(password);
                log.debug("click submit button");
		login.password().sendKeys(Keys.ENTER);
		//login.submit();
		if(expect.equals("sucessfull"))
		{ 
			log.info("if username  and  password are correct. Current page is  taken to my  account page");
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
			wait.until(ExpectedConditions.titleIs("My Account"));
			String myTitleAct=driver.getTitle();
			log.debug("check  login page is changed to MyAccount page");
			Assert.assertEquals(myTitleAct,"My Account");
                        driver.manage().deleteAllCookies();
		}
		else
		{
			log.info("user credientials  is wrong so it show warning on the same page");
			String actualWarning=login.warning().getText();
			String expctwarning="Warning: No match for E-Mail Address and/or Password.";
			//if(i==1)
				Assert.assertEquals(actualWarning,expctwarning);
			//else
				//Assert.assertEquals(actualWarning,"Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.");
		}
	}
	
	
	@DataProvider
	public String[][] userData()
	{
		String[][] data= {
				{"professional@gmail.com","professional","sucessfull"},
				{"professional@gmail.com","abcd","unsucessfull"},
				{"abcd@gmail.com","monkey","professional"},
				{"abcd@gmail.com","xyz","unsucessful"}
		};
		
		return data;
	}
	
	
	@BeforeTest
	public void start() throws IOException
	{
		PropertyConfigurator.configure("resource\\log.properties");
		log=LogManager.getLogger(HomeTest.class.getName());
		driver=initializeDriver();
	}
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
}
