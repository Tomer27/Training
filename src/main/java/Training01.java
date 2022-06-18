import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.List;

public class Training01 {

    public static ChromeDriver driver;
    public static String ExText1 = "קניית מתנות ליום האב";
    public static String itemToSearch = "PS4";

    @BeforeClass
    public static void Before() throws Exception {

        System.out.println("\n==========================");
        System.out.println("Before Class");
        System.out.println("==========================\n");

        System.setProperty("web-driver.chrome.driver", "C:/Users/Tomer/Desktop/Selenium/chromedriver.exe");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("https://www.amazon.com/");
        driver.manage().window().maximize();
        Thread.sleep(8000);

    }

    @Test
    public static void Test01() throws Exception {

        System.out.println("\n==========================");
        System.out.println("Test 01");
        System.out.println("==========================\n");

        WebElement header = driver.findElement(By.id("swm-link"));

        String headerText = header.getText().trim();
        Assert.assertEquals(headerText, ExText1);
        System.out.println("The 1st text is OK!");
        Thread.sleep(1500);

    }

    @Test
    public static void Test02() throws Exception{

        System.out.println("\n==========================");
        System.out.println("Test 02");
        System.out.println("==========================\n");

        WebElement searchBar = driver.findElement(By.id("twotabsearchtextbox"));
        WebElement searchBtn = driver.findElement(By.id("nav-search-submit-button"));

        searchBar.sendKeys(itemToSearch);
        Thread.sleep(500);
        searchBtn.click();
        Thread.sleep(2000);

        String result = driver.findElement(By.xpath("//*[@id='search']/div[1]/div[1]/div/span[3]/div[2]/div[2]/div/div/div/div/div/div/div/div[2]/div/div/div[1]/h2/a/span")).getText();
        if (result.contains(itemToSearch)) {
            System.out.println("Searched word is in the 1st result");
        }else {
            System.err.println("Searched word is not in the 1st result");
        }




    }

    @AfterClass
    public static void After() throws Exception {

        System.out.println("\n==========================");
        System.out.println("After Class");
        System.out.println("==========================\n");

        System.out.println("Closing Chrome");
        driver.quit();
    }
}
