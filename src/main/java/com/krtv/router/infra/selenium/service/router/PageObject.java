package com.krtv.router.infra.selenium.service.router;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.concurrent.TimeUnit;

@Log4j2
public abstract class PageObject {

    private ChromeOptions options;
    protected WebDriver browser;
    protected String url;
    protected String urlBaseWithCredentials;

    @Value("${config.pathWebDriver}")
    private String pathWebDriver;

    @Value("${config.timeout}")
    private Integer timeout;

    @Value("${config.sleepStep}")
    protected Integer sleepStep;

    @Value("${config.enableHeadless}")
    private boolean enableHeadless;

    protected void start(WebDriver browser) {
        defineArguments();

        log.info("Path chromedriver: {}", pathWebDriver);

        this.browser = browser == null
                ? new ChromeDriver(options)
                : browser;

        configure();
    }

    protected void setUrlBase(String urlBase) {
        this.url = urlBase;
    }

    protected void setUrlBaseWithCredentials(String urlBase, String username, String password) {
        this.setUrlBase(urlBase);

        String urlBaseWithoutProtocol = urlBase.contains("https://")
                ? urlBase.replace("https://", "")
                : urlBase.replace("http://", "");

        UriComponentsBuilder ucb = UriComponentsBuilder.fromUriString("http://{username}:{password}@" + urlBaseWithoutProtocol);

        this.urlBaseWithCredentials = ucb.build().expand(username, password).encode().toUri().toString();

    }

    public final void defineArguments() {
        System.setProperty("webdriver.chrome.driver", this.pathWebDriver);
        options = new ChromeOptions();
        options.addArguments("--disable-blink-features=\"BlockCredentialedSubresources\"");
        options.addArguments("--no-sandbox");

        if (enableHeadless)
            options.addArguments("--headless");
    }

    public final void configure() {
        this.browser
                .manage()
                .timeouts()
                .implicitlyWait(this.timeout, TimeUnit.SECONDS)
                .pageLoadTimeout(this.timeout, TimeUnit.SECONDS);
    }

    public void  close() {
        log.info("PageObject - Close was called.");
        this.browser.quit();

    }

}
