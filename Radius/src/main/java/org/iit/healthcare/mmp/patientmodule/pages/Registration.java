package org.iit.healthcare.mmp.patientmodule.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Registration {
	
	public WebDriver driver ;
	
	
	@Test(priority = 1)

	public void registerprofile() throws Exception
	
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/");
		driver.manage().window().maximize();

		//driver.findElement(By.xpath("//a[contains(text(),'Patient Login')]")).click();
		driver.findElement(By.xpath("//div[3]/a[contains(text(),'Register')]")).click();
		WebElement Firstname = driver.findElement(By.xpath("//input[@id='firstname']"));
		Firstname.click();
		Firstname.sendKeys("Rose");
		WebElement Lastname = driver.findElement(By.xpath("//input[@id='lastname']"));
		Lastname.sendKeys("Garden");
	    WebElement Date = driver.findElement(By.xpath("//input[@id='datepicker']"));
	    Date.sendKeys("08/08/88");
	    WebElement License = driver.findElement(By.xpath("//input[@id='license']"));
	    License.sendKeys("12345670");
	    WebElement ssn = driver.findElement(By.xpath("//input[@id='ssn']"));
	    ssn.sendKeys("123400089");
	    WebElement state = driver.findElement(By.xpath("//input[@id='state']"));
	    state.sendKeys("Georgia");
	    WebElement city = driver.findElement(By.xpath("//input[@id='city']"));
	    city.sendKeys("johns creek");
	    WebElement address = driver.findElement(By.xpath("//input[@id='address']"));
	    address.sendKeys("11540 crestview ter");
	    WebElement zipcode = driver.findElement(By.xpath("//input[@id='zipcode']"));
	    zipcode.sendKeys("30024");
	    WebElement age = driver.findElement(By.xpath("//input[@id='age']"));
	    age.sendKeys("33");
	    WebElement height = driver.findElement(By.xpath("//input[@id='height']"));
	    height.sendKeys("5.3");
	    WebElement weight= driver.findElement(By.xpath("//input[@id='weight']"));
	    weight.sendKeys("130");
	    WebElement pharmacy = driver.findElement(By.xpath("//input[@id='pharmacy']"));
	    pharmacy.sendKeys("publix pahrmacy");
	    WebElement pharma_add = driver.findElement(By.xpath("	//input[@id='pharma_adress']"));
	    pharma_add.sendKeys("Shakerag complex");
	    WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
	    email.sendKeys("Rose1234@gmail.com");
	    WebElement username = driver.findElement(By.xpath("//input[@id='username']"));
	    username.sendKeys("rosegarden1234");
	    WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
	    password.sendKeys("Publix1234");
	    WebElement confirmpassword = driver.findElement(By.xpath("//input[@id='confirmpassword']"));
	    confirmpassword.sendKeys("Publix1234");
	    String security = "//select[@id='security']";
	    Select securityy = new Select(driver.findElement(By.xpath(security)));
		securityy.selectByIndex(2);
		WebElement answer = driver.findElement(By.xpath("//input[@id='answer']"));
	    answer.sendKeys("browny");
	    WebElement Savebutton = driver.findElement(By.xpath("//div[1]/div[2]/p[1]/input[1][@type='submit']"));
	//    WebElement Savebuttonn = driver.findElements(By.xpath("//body/div[@id='containerform']/div[@id='form_name']/div[1]/div[2]/p[1]/input[1]"));
	/*   String Savebutton = "//body/div[@id='containerform']/div[@id='form_name']/div[1]/div[2]/p[1]/input[1]";
	   Select Savebuttonn = new Select(driver.findElements(By.xpath(Savebutton))); */
	   Savebutton.click(); 
	    
	   System.out.println(driver.switchTo().alert().getText());
       driver.switchTo().alert().accept();
		
		
	/*	driver.findElement(By.id("First Name")).sendKeys("Rose");
		driver.findElement(By.id("Last Name")).sendKeys("Garden");
		driver.findElement(By.id("datepicker")).sendKeys("08/08/88"); */	
		
	}
	@Test(priority = 5)
    public void close() 
		{
			
			driver.quit();
		

	}  
	
	

}
