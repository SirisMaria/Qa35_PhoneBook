import manager.ListenerNG;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ListenerNG.class)

public class GoToGoogle {
    WebDriver wd;

    @Test
    public void gogoogle(){
        wd = new ChromeDriver();
        wd.navigate().to("https://www.google.com/");
        wd.close();
    }
}
