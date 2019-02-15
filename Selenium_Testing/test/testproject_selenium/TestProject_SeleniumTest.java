package testproject_selenium;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * This is a test unit that would test the given web site.
 *
 * I, Leslie Col-iteng, 000772220, certify that this material 
 * is my original work. No other person's work has been used 
 * without due acknowledgment. 
 * reference code from mark.yendt@mohawkcollege.ca
 * 
 * @author Leslie Coliteng
 * December 07 2018
 * 
 */
public class TestProject_SeleniumTest {
    

    enum Browser {
        FIREFOX, CHROME, IE
    };

    Browser browser = Browser.IE;
    
    private int count;
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    private WebDriver startBrowser(Browser browser) throws Exception {
        WebDriver tempDriver = null;
        // Tested at College on Nov 19,2018
        if (browser == Browser.CHROME) {
            System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");
            tempDriver = new ChromeDriver();
        } else if (browser == Browser.FIREFOX) {
            System.setProperty("webdriver.gecko.driver", "resources\\geckodriver.exe");
            // May need to do this if not in path
            String pathToBinary = "C:\\Program Files\\Mozilla Firefox 61-32-bit\\firefox.exe";
            System.setProperty("webdriver.firefox.driver",pathToBinary);
            FirefoxProfile firefoxProfile = new FirefoxProfile();
            tempDriver = new FirefoxDriver();
        } else if (browser == Browser.IE) {
            System.setProperty("webdriver.ie.driver", "resources\\IEDriverServer.exe");
            tempDriver = new InternetExplorerDriver();
        }
        return tempDriver;
    }

    private void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }


    /**
     Example Test case - driver is assummed to be set-up
     Your other test cases need to resemble these 
     Obtain more test cases by recording scripts using Katalon and exporting to
     Java - Junit 4 Web-Driver format
     */
    public void testLoginAdmin() throws Exception {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://csunix.mohawkcollege.ca/tooltime/comp10066/A3/login.php");
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("adminP6ss");
        driver.findElement(By.name("Submit")).click();
        driver.findElement(By.linkText("User Admin")).click();
        driver.findElement(By.linkText("Logout")).click();
        driver.findElement(By.id("loginname")).click();
        try {
            assertEquals("Not Logged In", driver.findElement(By.id("loginname")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
    
    /**
     * This is a trial test
     * @throws Exception 
     */
  /*public void practiceTestCase() throws Exception {
    driver.get("https://csunix.mohawkcollege.ca/tooltime/comp10066/A3/index.php");
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("admin");
    driver.findElement(By.name("password")).click();
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("adminP6ss");
    driver.findElement(By.name("Submit")).click();
    driver.findElement(By.linkText("View Logs")).click();
    driver.findElement(By.linkText("30")).click();
    try {
      assertEquals(driver.findElement(By.xpath("//div[@id='body']/div/div/div[3]/table/tbody/tr[577]/td[4]")).getText(), "Login");
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.linkText("Logout")).click();
  }*/
  
  /**
   * Create a non-admin and verify if user can log in with the created account
   * @throws Exception 
   */
 public void testCreate() throws Exception {
    driver.get("https://csunix.mohawkcollege.ca/tooltime/comp10066/A3/");
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("admin");
    driver.findElement(By.name("password")).click();
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("adminP6ss");
    driver.findElement(By.name("Submit")).click();
    try {
      assertEquals(driver.findElement(By.id("loginname")).getText(), "User: admin");
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals(driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Logout'])[1]/following::h3[1]")).getText(), "Admin");
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.linkText("User Admin")).click();
    driver.findElement(By.id("compid")).click();
    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("lesCee");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("jetFuelMeltsSteel001!");
    driver.findElement(By.name("activate")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Admin'])[1]/following::input[2]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Admin'])[1]/following::input[2]")).click();
    driver.findElement(By.name("Add New Member")).click();
    driver.findElement(By.linkText("Home")).click();
    driver.findElement(By.linkText("Logout")).click();
    try {
      assertEquals(driver.findElement(By.id("loginname")).getText(), "Not Logged In");
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("lesCee");
    driver.findElement(By.name("password")).click();
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("jetFuelMeltsSteel001!");
    driver.findElement(By.name("Submit")).click();
    try {
      assertEquals(driver.findElement(By.id("loginname")).getText(), "User: lesCee");
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.linkText("Logout")).click();
  }
 
 
 /**
  * Delete the non-admin Account that was previously deleted
  * @throws Exception 
  */
  public void testDelete() throws Exception {
    driver.get("https://csunix.mohawkcollege.ca/tooltime/comp10066/A3/login.php");
    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("admin");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("adminP6ss");
    driver.findElement(By.name("searchByPC")).submit();
    driver.findElement(By.id("loginname")).click();
    try {
      assertEquals(driver.findElement(By.id("loginname")).getText(), "User: admin");
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.linkText("User Admin")).click();
    driver.get("https://csunix.mohawkcollege.ca/tooltime/comp10066/A3/adminuser.php?act=delete&username=lesCee");
    driver.findElement(By.linkText("here")).click();
    try {
      assertEquals(driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Members Administration'])[1]/following::div[1]")).getText(), "User lesCee was successfully deleted.");
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.linkText("Logout")).click();
  }
  
  
  /**
   * Checks if all the city options work
   * Test if each company listed in each city are located in that city
   * Verifies if the number of companies shown in the city select-option, is accurate
   * 
   * @throws Exception 
   */
  public void testDirectory() throws Exception {
    driver.get("https://csunix.mohawkcollege.ca/tooltime/comp10066/A3/login.php");
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("admin");
    driver.findElement(By.name("password")).click();
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("adminP6ss");
    driver.findElement(By.name("Submit")).click();
    driver.findElement(By.linkText("Directory")).click();
    driver.findElement(By.id("city")).click();
    
    List<WebElement> foundCity = driver.findElements(By.xpath("//option"));
    int cityCount = foundCity.size();   // counts how many citys exist;
    
    // loops and selects thought every city and verify the listed companies
    // CHANGE THE cityCount to a value if testing just a few number of cities
    for(int i = 1; i < cityCount; i++){
        if( i != 87 ){
            new Select(driver.findElement(By.id("city"))).selectByIndex(i);     //selects the city by index
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='City:'])[1]/following::option["+(i+1)+"]")).click();
            driver.findElement(By.name("submit")).click();
            try {
              String chosenCity = driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='City:'])[1]/following::option["+(i+1)+"]")).getText(); //gets the selected city option eg. "Ajax {1}"
              ///System.out.println(chosenCity);

              int industryCount = Integer.parseInt(chosenCity.substring(chosenCity.indexOf('{')+1, chosenCity.indexOf('}')));   // gets the number in the city option eg Ajax {1} = "1"
              ///System.out.println(industryCount);

              int verifyCompCount = 0;      // counts the number of companies that were verified

              for(int n = 1; n <= industryCount; n++){

                String industryAddress = driver.findElement(By.xpath("(//ul[@id='company_"+n+"']//li[3])")).getText();      // gets the content of the 3rd li tag
                ///System.out.println(chosenCity.substring(0,chosenCity.indexOf(' ')));
                ///System.out.println(industryAddress);
                    
                    //checks if the address contains the city name
                    if(industryAddress.contains(chosenCity.substring(0, chosenCity.indexOf(" ")))){
                        assertTrue(true);
                        verifyCompCount++ ;
                        System.out.println(chosenCity+" Passed!");
                    }else{
                        throw new Error("This company is not located in the city!");
                    }
                }
                
                // verify if the number of listed companies is equal to the one stated in the option
                if( verifyCompCount == industryCount){
                     assertTrue(true);
                     verifyCompCount = 0;
                }else{
                    throw new Error("The number lised in "+chosenCity+" is innacurate!");
                }

            }catch (Error e) {
                    verificationErrors.append(e.toString());
            }
        }
        
      }
    driver.findElement(By.linkText("Home")).click();
    driver.findElement(By.linkText("Logout")).click();
  }

    /**
     * This code will run all of the test cases - DO NOT CHANGE except to add
     * more test cases as shown below:
     */
    @Test
    public void testRunner() {
        // Will run through asll enum cases 
        for (Browser browser : Browser.values()) {
            try {

                driver = startBrowser(browser);

                //----------------------------------------
                // PUT YOUR TEST CASES AFTER this line 
                testLoginAdmin();
                testCreate();
                testDelete();
                testDirectory();

                // DO NOT MODIFY below line
                // ----------------------------------------
                closeBrowser();
                
            } catch (Exception ex) {
                System.err.println("Exception caught starting " + browser + " " + ex.getMessage());
            }

        }
    }
} 
