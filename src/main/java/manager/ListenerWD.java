package manager;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;

public class ListenerWD implements WebDriverListener {
    Logger logger = LoggerFactory.getLogger(ListenerWD.class);


    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        WebDriverListener.super.beforeFindElement(driver, locator);
        logger.info("Before find element By -->"+locator);
    }

    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        WebDriverListener.super.afterFindElement(driver, locator, result);
        logger.info("After find element By -->"+locator);
    }

    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
        WebDriverListener.super.onError(target, method, args, e);
        logger.info("We have a 'problem'");
        logger.info("The name of error method " +method.getName());
      //  logger.info("Describle of error"+ e.getMessage());
       // logger.info("Describle of error"+ e.toString());
        logger.info("Describle of error"+ e.getCause());
       // logger.info("Describle of error"+ e.fillInStackTrace());
        logger.info("**********");
        logger.info(target.toString());

        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        String link = "src/test/screenshots/screen"+i+" .png";
        logger.info("Screen with error is "+ i+" ***" + link);

        ChromeDriver wd = (ChromeDriver) target;
        File tap = ((TakesScreenshot)wd).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(tap,new File(link));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


    }

    @Override
    public void beforeFindElements(WebDriver driver, By locator) {
        WebDriverListener.super.beforeFindElements(driver, locator);
        logger.info("Before find elements By -->"+locator);
    }

    @Override
    public void afterFindElements(WebDriver driver, By locator, List<WebElement> result) {
        WebDriverListener.super.afterFindElements(driver, locator, result);
        logger.info("After find elements By -->"+locator);
        logger.info("Lisy size is -->"+result.size());
    }

}