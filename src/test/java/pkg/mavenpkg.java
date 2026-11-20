package pkg;

import java.awt.AWTException;
import java.awt.Robot;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class mavenpkg {
	WebDriver driver;
	String baseUrl ="https://www.amazon.in/";
	
	@BeforeTest
	public void setup()
	{
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
	}
	@BeforeMethod
	public void open()
	{
		driver.get(baseUrl);
		driver.manage().window().maximize();
	}
	@Test
	public void cart() throws AWTException
	{
		Robot robot = new Robot();
		robot.delay(3000);
		driver.findElement(By.xpath("//*[@id=\"twotabsearchtextbox\"]")).sendKeys("Mobile phone", Keys.ENTER);
		robot.delay(3000);
		driver.findElement(By.xpath("//*[@id=\"a-autoid-3\"]/span/input")).click();
		robot.delay(3000);
		driver.findElement(By.xpath("//*[@id=\"ewc-cart-button-loaded-retail\"]/span/span/a")).click();
		robot.delay(3000);
	}
	@AfterTest
	public void close()
	{
		driver.quit();
	}
}
