package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login {
	WebDriver driver;
	public Login(WebDriver driver)
	{
		this.driver=driver;
	}
	
    private By email=By.cssSelector("#input-email");
    private By password=By.cssSelector("#input-password");
    private By submit=By.cssSelector("input[type='submit']");
    private By warning=By.xpath("//div[@class='alert alert-danger alert-dismissible']");
    
    public WebElement email()
    {
    	return driver.findElement(email);
    }
    
    public WebElement password()
    {
    	return driver.findElement(password);
    }
    public WebElement submit()
    {
    	  return driver.findElement(submit);
    }
    public WebElement warning()
    {
    	return driver.findElement(warning);
    }
}
