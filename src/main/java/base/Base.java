package base;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	public WebDriver driver;
	public Properties propertyFile;
	String projectPath=System.getProperty("user.dir");
	
	public WebDriver initializeDriver() throws IOException
	{
		propertyFile=new Properties();
		FileInputStream file=new FileInputStream(projectPath+"\\resource\\userdata.properties");
		propertyFile.load(file);
		String brwsrName=System.getProperty("browser");
		
		//condition for which browser to initialized.
		if(brwsrName.contains("chrome"))
		{
		    WebDriverManager.chromedriver().setup();
                     ChromeOptions option=new ChromeOptions();
                     if(brwsrName.equals("chromeheadless"))
                     {
                       option.addArguments("headless");
                     }
		    driver=new ChromeDriver(option);
		}
		else if(brwsrName.contains("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
                        FirefoxOptions option=new FirefoxOptions();
                        if(brwsrName.equals("firefoxheadless"))
                        {
                          option.addArguments("-headless");
                         }
			driver=new FirefoxDriver(option);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}
	
	public String takeScreenShot(WebDriver driver,String testname )
	{
		File sourcefie=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destination=projectPath+"\\screenshot\\"+testname+".png";
		try {
			FileUtils.copyFile(sourcefie,new File(destination));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destination;
	}
}
