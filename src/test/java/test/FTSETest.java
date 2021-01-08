package test;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FTSETest {
	private WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/resources/drivers/chromedriver.exe");
        
        ChromeOptions cOptions = new ChromeOptions();
		cOptions.setHeadless(false);
		
		driver = new ChromeDriver(cOptions);
		driver.manage().window().setSize(new Dimension(1366, 768));
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        driver.get("https://www.hl.co.uk/shares/stock-market-summary/ftse-100");
        driver.findElement(By.cssSelector("#acceptCookie")).click();
    }
    
    @After
    public void tearDown() {
        driver.close();
    }
    
    @Test
    public void ftseSearch(){
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	WebElement Element = driver.findElement(By.linkText("RISERS"));
    	js.executeScript("arguments[0].scrollIntoView();", Element);
        WebElement risers = driver.findElement(By.cssSelector("#view-constituents > ul > li:nth-child(2) > a > strong"));
        risers.click();
        WebElement highest = driver.findElement(By.cssSelector("#ls-perc-SBRY-L"));
        String highText = highest.getText();
        assertEquals(highText, highest.getText());
        WebElement Element2 = driver.findElement(By.linkText("FALLERS"));
    	js.executeScript("arguments[0].scrollIntoView();", Element2);
        WebElement fallers = driver.findElement(By.cssSelector("#content_div_40583 > ul > li:nth-child(3) > a > strong") );
        fallers.click();
        WebElement lowest = driver.findElement(By.cssSelector("#ls-perc-AUTO-L"));
       String lowText = lowest.getText();
       assertEquals(lowText, lowest.getText());

        
    }
}


