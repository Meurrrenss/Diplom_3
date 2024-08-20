package site.nomoreparties.stellarburgers.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverConstructor {
    private String browserName;

    // Запустить тесты в Яндекс браузере - mvn clean test -Dbrowser=yandex
    // Запустить тесты в Хроме - mvn clean test

    public WebDriverConstructor() {
        this.browserName = System.getProperty("browser");
    }

    public WebDriver getWebDriver() {
        if (browserName == null) {
            browserName = "chrome";
        }
        WebDriver driver;
        switch (browserName) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "/Users/dlazutin/WebDriver/bin/chromedriver");
                driver = new ChromeDriver();
                //driver.manage().window().maximize();
                break;
            case "yandex":
                System.setProperty("webdriver.chrome.driver", "/Users/dlazutin/WebDriver/bin/yandexdriver");
                driver = new ChromeDriver();
                //driver.manage().window().maximize();
                break;
            default:
                throw new RuntimeException("Некорректный браузер");
        }
        return driver;
    }
}

