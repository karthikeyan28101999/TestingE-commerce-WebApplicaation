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
		String brwsrName=propertyFile.getProperty("browser");
		
		//condition for which browser to initialized.
		if(brwsrName.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
		    driver=new ChromeDriver();
		}
		else if(brwsrName.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
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
