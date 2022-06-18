import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Training01 {

    public static ChromeDriver driver;
    public static String ExText = "קניית מתנות ליום האב";

    @BeforeClass
    public static void Before() throws Exception {

        System.setProperty("web-driver.chrome.driver", "C:/Users/Tomer/Desktop/Selenium/chromedriver.exe");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("https://www.amazon.com/");
        driver.manage().window().maximize();
        Thread.sleep(2000);

    }

    @Test
    public static void Test01() throws Exception {

        Thread.sleep(2000);

        WebElement header = driver.findElement(By.id("swm-link"));

        String headerText = header.getText().trim();
        Assert.assertEquals(headerText, ExText);
        System.out.println("The text is OK!");

    }

    @AfterClass
    public static void After() throws Exception {

        driver.quit();
    }
}
