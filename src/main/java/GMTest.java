import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;


public class GMTest {

    public static ChromeDriver driver;
    public static String CityName = "Rome";
    public static String URL = "https://www.google.com/maps";
    public static int screenshots = 3;

    @BeforeTest
    public static void BeforeTest() throws Exception {

        System.setProperty("web-driver.chrome.driver", "C:/Users/Tomer/Desktop/Selenium/chromedriver.exe");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        System.out.println("Opening Chrome");

    }

    @Test
    public static void FindRomeInGoogleMaps() throws Exception {

        driver.get(URL);
        System.out.println("Opening Google maps");
        driver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement searchField = driver.findElement(By.xpath("//*[@id=\"searchboxinput\"]"));
        searchField.sendKeys(CityName);
        System.out.println("Typed the city name in the text field. City name: " + CityName);
        Thread.sleep(500);

        WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"searchbox-searchbutton\"]"));
        searchButton.click();
        System.out.println("Clicked on the 'Search' button");
        Thread.sleep(3500);

        WebElement zoomIn = driver.findElement(By.xpath("//*[@id=\"widget-zoom-in\"]"));

        for (int i=0; i<screenshots; i++) {

            String pathName = "C:\\Users\\Tomer\\Desktop\\SelPics//ScreenShot" + (i+1) + ".png"; // enter local path to save the screenshots

            System.out.println("Taking screenshot #" + (i+1));

            Screenshot entirePageScreenShot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
            ImageIO.write(entirePageScreenShot.getImage(), "png", new File(pathName));
            Thread.sleep(1500);

            System.out.println("Zooming in");
            zoomIn.click();
            Thread.sleep(1500);

        }

    }

    @AfterTest
    public static void AfterTest() {

        driver.quit();

    }

}
