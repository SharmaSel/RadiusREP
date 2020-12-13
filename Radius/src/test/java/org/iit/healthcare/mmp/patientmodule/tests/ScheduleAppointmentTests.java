package org.iit.healthcare.mmp.patientmodule.tests;


import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.iit.healthcare.TestBase.TestBaseClass;
import org.iit.healthcare.mmp.patientmodule.pages.HomePage;
import org.iit.healthcare.mmp.patientmodule.pages.Loginpage;
import org.iit.healthcare.mmp.patientmodule.pages.ScheduleAppointmentPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ScheduleAppointmentTests extends TestBaseClass

{
	WebDriver driver;

	public void instantiateDriver()
	{	
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}
	public void launchApplication(String url)
	{
		driver.get(url);
	}
	public void login(String uname,String pword)
	{
		//login
		driver.findElement(By.id("username")).sendKeys(uname);
		driver.findElement(By.id("password")).sendKeys(pword);
		//type=submit and it is available in the html form
		driver.findElement(By.name("submit")).click();

	}
	public void navigationMenuItem(String tabName)
	{
		//Navigation to the module
		driver.findElement(By.xpath("//span[contains(text(), '"+tabName+"')]")).click();

	}
	public void bookAnAppointment(String doctorName) throws InterruptedException
	{
		//Create new appointment
		driver.findElement(By.xpath("//input[@value='Create new appointment']")).click();

		driver.findElement(By.xpath("//h4[contains(text(),'"+doctorName+"')]/ancestor::ul/following-sibling::button[@id='opener']")).click();
		appointmentDetailsHMap.put("doctorName",doctorName);

		//Switch to a frame
		driver.switchTo().frame("myframe");
		driver.findElement(By.id("datepicker")).sendKeys("11/30/2020");
		appointmentDetailsHMap.put("dateofAppointment","11/30/2020");

		Select timeSelect = new Select(driver.findElement(By.id("time")));
		timeSelect.selectByVisibleText("10Am");
		appointmentDetailsHMap.put("time","10Am");

		Thread.sleep(3000);

		driver.findElement(By.id("ChangeHeatName")).click();

		driver.findElement(By.id("sym")).sendKeys("Fever and Cold");
		appointmentDetailsHMap.put("sym","Fever and Cold");

		driver.findElement(By.xpath("//input[@value='Submit']")).click();
	}
	public boolean validatePatientPortalMessage()
	{
		//Validation-1
		WebDriverWait wait = new WebDriverWait(driver,30);
		Boolean result = wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//h3[@class='panel-title']"), "Patient Portal"));
		return result;
	}
	public HashMap<String, String> validateApptDetailsinHomePage()
	{

		//Validation -2 Appointment details are displayed in the home page 
		//table[@class='table']//tr[1]/td[1] - dateofAppointment
		//table[@class='table']//tr[1]/td[2]-  time
		//table[@class='table']//tr[1]/td[3] - sym
		//table[@class='table']//tr[1]/td[4] - doctorName
		HashMap<String,String> homePageDetailsHMap = new HashMap<String,String>();
		homePageDetailsHMap.put("dateofAppointment",driver.findElement(By.xpath("//table[@class='table']//tr[1]/td[1]")).getText());
		homePageDetailsHMap.put("time",driver.findElement(By.xpath("//table[@class='table']//tr[1]/td[2]")).getText());
		homePageDetailsHMap.put("sym",driver.findElement(By.xpath("//table[@class='table']//tr[1]/td[3]")).getText());
		homePageDetailsHMap.put("doctorName",driver.findElement(By.xpath("//table[@class='table']//tr[1]/td[4]")).getText());
		return homePageDetailsHMap;
	}
	public HashMap<String, String> validateApptDetailsinSPage()
	{
		//a[contains(text(),'Time')] - time
		//a[contains(text(),'Provider')] - doctorName
		//a[contains(text(),'Symptoms')]- sym
		//div[@class='panel panel-info']//h3[@class='panel-title'] - 	dateofAppointment


		HashMap<String,String> sPageHMap = new HashMap<String,String>();
		sPageHMap.put("dateofAppointment",driver.findElement(By.xpath("//div[@class='panel panel-info']//h3[@class='panel-title']")).getText().trim());


		String timeWE = driver.findElement(By.xpath("//a[contains(text(),'Time')]")).getText();
		String timeWEArr[] = timeWE.split(":");
		sPageHMap.put("time",timeWEArr[1].trim());

		String symWE= driver.findElement(By.xpath("//a[contains(text(),'Symptoms')]")).getText();
		String symWEArr[] = symWE.split(":");
		sPageHMap.put("sym",  symWEArr[1].trim());

		String doctorNameWE= driver.findElement(By.xpath("//a[contains(text(),'Provider')]")).getText();
		String doctorNameWEArr[] =doctorNameWE.split(":");
		sPageHMap.put("doctorName",doctorNameWEArr[1].trim() );
		return sPageHMap;
		
	}
	HashMap<String,String> appointmentDetailsHMap = new HashMap<String,String>();
	@Test
	public void validateScheduleAppointment() throws InterruptedException
	{
		instantiateDriver();
		
		launchApplication("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		Loginpage lpage = new Loginpage(driver);
		lpage.login("ria1","Ria12345");
		
		HomePage hpage = new HomePage(driver);
		
		hpage.navigationMenuItem("Schedule Appointment");
		bookAnAppointment("Dr.Beth");
	
		boolean result = validatePatientPortalMessage();
		Assert.assertTrue(result);

		HashMap<String,String> homePageDetailsHMap=hpage.validateApptDetailsinHomePage();
		Assert.assertEquals(appointmentDetailsHMap, homePageDetailsHMap);


		hpage.navigationMenuItem("Schedule Appointment");
	 ScheduleAppointmentPage sPage = new ScheduleAppointmentPage(driver);
	 
		HashMap<String,String> sPageHMap=sPage.validateApptDetailsinSPage();
		Assert.assertEquals(appointmentDetailsHMap, sPageHMap);




	}

}


