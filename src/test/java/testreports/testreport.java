package testreports;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import junit.framework.Assert;

public class testreport {
WebDriver driver;
String baseurl ="https://keralaproperty.in/member/login/";
ExtentSparkReporter reporter;
ExtentTest test;
ExtentReports extent;

@BeforeTest
public void beftest()
{
	
	reporter=new ExtentSparkReporter("./report/myreport11.html");
	reporter.config().setDocumentTitle("Automationreport");
	reporter.config().setReportName("functional test");
	reporter.config().setTheme(Theme.DARK);
	extent= new ExtentReports();
	extent.attachReporter(reporter);
	extent.setSystemInfo("hostname", "localhost");
	extent.setSystemInfo("os", "windows11");
	extent.setSystemInfo("testername","Hari");
	extent.setSystemInfo("Browser Name", "chrome");
	
	driver = new ChromeDriver();
}
@BeforeMethod
public void setup()
{
	driver.get(baseurl);
}
@Test
public void Eptitleverification() throws Exception
{
	test=extent.createTest("kptitleverification");
	String Exp="facebook";
	String actual = driver.getTitle();
	System.out.println(actual);
	Assert.assertEquals(Exp, actual);
	System.out.println("hello");
}

@Test
public void kpbuttontest() throws Exception
{
	test=extent.createTest("kpbuttontest");
	String buttontext=driver.findElement(By.xpath("/html/body/header/div[1]/div/a[1]")).getAttribute("value");
	Assert.assertEquals(buttontext, "Login");
}

@Test
public void Kplogotest() throws Exception
{
	test=extent.createTest("Kplogotest");
	boolean b = driver.findElement(By.xpath("/html/body/header/div[2]/div/a[1]/img")).isDisplayed();
	Assert.assertTrue(b);
}

@AfterTest
public void teardown()
{
	extent.flush();
}
@AfterMethod
public void browserclose(ITestResult result) throws Exception
{
	if(result.getStatus()==ITestResult.FAILURE)
	{
		test.log(Status.FAIL, "test cae failed is"+result.getName());
		test.log(Status.FAIL, "test cae failed is"+result.getThrowable());
		String screenshotpath=testreport.screenshotMethod(driver,result.getName());
		test.addScreenCaptureFromPath(screenshotpath);
	}
	else if(result.getStatus()==ITestResult.SKIP)
	{
		test.log(Status.SKIP,  "test case skipped is"+result.getName());
	}
	else if(result.getStatus()==ITestResult.SUCCESS)
	{
		test.log(Status.PASS, "test case skipped is"+result.getName());
	}
}
private static String screenshotMethod(WebDriver driver, String screenshotname) throws IOException {
	// TODO Auto-generated method stub
	File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	String destination="./Screenshot"+screenshotname+".png";
	FileHandler.copy(src, new File(destination));
	return destination;
}
}
