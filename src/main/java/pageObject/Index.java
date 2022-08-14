package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


public class Index {
    public WebDriver driver;
     public Index(WebDriver driver)
     {
    	 this.driver=driver;
    	 PageFactory.initElements(driver,this);
     }
    
     private By myAccount=By.xpath("//span[text()='My Account']");
     private By myAccountoption=By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']/li");
     private By sliders=By.xpath("//div[@class='swiper-slide text-center swiper-slide-active']");
     private By sliderImage=By.xpath("//div[@class='swiper-slide text-center swiper-slide-active']/a/img");
     private By featuredProduct=
    		 By.xpath("//div[@class='product-layout col-lg-3 col-md-3 col-sm-6 col-xs-12']/div/div[@class='caption']/h4");
    
     
     public WebElement myAccount()
     {
    	 return driver.findElement(myAccount);
     }
     public List<WebElement> myAccountOption()
     {
		return driver.findElements(myAccountoption);
    	 
     }
     public List<WebElement> sliders()
    {
    	return driver.findElements(sliders);
    }
     
    public WebElement sliderImage()
    {
    	return driver.findElement(sliderImage);
    }
    
    public List<WebElement> Featuredproduct()
    {
    	return driver.findElements(featuredProduct);
    }
    
}
