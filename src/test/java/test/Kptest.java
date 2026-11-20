package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pagepkg.Keralapage;



public class Kptest {
	WebDriver driver;
	
	@BeforeTest
	public void setup()
	{
		driver = new ChromeDriver();
	}
	@BeforeMethod
	public void url()
	{
		driver.get("https://keralaproperty.in/member/login/");
	}
	@Test
	public void testlogin()
	{
		Keralapage ob = new Keralapage(driver);
		ob.setvalues("abc@gmail.com","abcd");
		ob.login();
	}
	@AfterTest
	public void close()
	{
		driver.quit();
	}
}
