package org.iit.healthcare.TestBase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBaseClass {
	WebDriver driver;
    @BeforeTest
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
}
