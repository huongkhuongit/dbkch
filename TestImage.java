package abd;


import org.openqa.selenium.By;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
class output{
	public output() {}
	private String name, url;
	private Integer price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
}
public class TestImage {

	WebDriver driver;
	
	@Test
	public void webtest() throws InterruptedException
	{
		
		String url1="https://tiki.vn/";
		String url2="https://www.thegioididong.com/";
		// get output1
		System.setProperty("webdriver.chrome.driver","D:\\WebDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		
		
		//get information 1
		driver.get (url1);
		driver.manage().window().maximize();	
		//search
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("iPhone 11");
		driver.findElement(By.xpath("//input[@type='text']/following-sibling::button")).click();
		output op1= new output();
		Thread.sleep(10000);
		driver.findElement(By.xpath("//button[@id='onesignal-slidedown-cancel-button']")).click();
		op1.setName(driver.findElement(By.xpath("//span[text()='Điện Thoại iPhone 11 64GB  - Hàng  Chính Hãng']")).getText()); 
		String price=(driver.findElement(By.xpath("//span[text()='Điện Thoại iPhone 11 64GB  - Hàng  Chính Hãng']/parent::div[@class='name']/following-sibling::div[@class='price-discount']/div[@class='price-discount__price']")).getText());
		driver.findElement(By.xpath("//span[text()='Điện Thoại iPhone 11 64GB  - Hàng  Chính Hãng']")).click();
		op1.setUrl(driver.getCurrentUrl());
		op1.setPrice(convertPrice(price));
		Thread.sleep(2000);
		//get information 2
		driver.get (url2);	
		//search
		driver.findElement(By.xpath("//input[@id='search-keyword']")).sendKeys("iPhone 11");
		driver.findElement(By.xpath("//input[@id='search-keyword']")).sendKeys(Keys.ENTER);
		output op2= new output();
		WebElement iphone= driver.findElement(By.xpath("//h3[text()='iPhone 11 64GB']"));
		op2.setName(driver.findElement(By.xpath("//h3[text()='iPhone 11 64GB']")).getText()); 
		String price1=(driver.findElement(By.xpath("//h3[text()='iPhone 11 64GB']/following-sibling::div/strong")).getText());
		iphone.click();
		op2.setUrl(driver.getCurrentUrl());
		op2.setPrice(convertPrice(price1));
		if(op1.getPrice()>op2.getPrice())
		{
			System.out.println(op1.getName());
			System.out.println(op1.getPrice());
			System.out.println(op1.getUrl());
			System.out.println("=============================");
			System.out.println(op2.getName());
			System.out.println(op2.getPrice());
			System.out.println(op2.getUrl());
		}
		else
		{
			System.out.println(op2.getName());
			System.out.println(op2.getPrice());
			System.out.println(op2.getUrl());
			System.out.println("=============================");
			System.out.println(op1.getName());
			System.out.println(op1.getPrice());
			System.out.println(op1.getUrl());
		}
	}
	public Integer convertPrice(String price)
	{
		price= price.replace(",", "");
		price= price.replace("₫", "");
		 price= price.replace(".", "");
		 price = price.trim(); 
		 Integer str1 = Integer.parseInt(price); 
		return str1;
	}

}
