package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPageObject extends  MainPageObject {
    private static final String SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Поиск по Википедии')]";
    private static final String SEARCH_INPUT = "//*[contains(@text, 'Поиск')]";
    private static final String SEARCH_RESULT = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='{SUBSTRING}']";
    private static  final String SAVE_BUTTON = "//android.widget.TextView[@content-desc=\"Сохранить\"]";
    private static  final String BACK_BUTTON = "//android.widget.ImageButton[@content-desc=\"Перейти вверх\"]";
    private static  final String SAVE_LIST = "//android.widget.FrameLayout[@content-desc=\"Сохранено\"]";
    private  static final String SAVE_LIST_IN_SAVED = "//android.view.ViewGroup[@resource-id=\"org.wikipedia:id/item_title_container\"]";
    private static final String SAVED_TITLE = "//android.view.ViewGroup[@resource-id=\"org.wikipedia:id/page_list_item_container\"]//*[@text='{SUBSTRING}']";
    private static final String SAVED_TITLE_DELETE_OPTION = "//android.widget.TextView[@resource-id=\"org.wikipedia:id/title\" and @text=\"Удалить все из офлайн-режима\"]";
    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    private WebElement waitForElementPresent(By by, String errorMessage, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private void waitForElementAndClick(By by, String errorMessage, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by ,errorMessage, timeoutInSeconds);
        element.click();
    }

    private void waitForElementAndSendKeys(By by, String value, String errorMessage, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by ,errorMessage, timeoutInSeconds);
        element.sendKeys(value);
    }

    private void waitForElementAndLongPress(By by, String errorMessage, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by ,errorMessage, timeoutInSeconds);
        TouchAction action = new TouchAction(driver);
        action.longPress(element).release().perform();
    }

    public void initSearchInput(){
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Невозможно нажать на поле ввода", 15);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Невозможно найти поле ввода", 15);
    }

    public void typeSearchLine(String searchLine){
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), searchLine,"Невозможно найти поле ввода", 15);
    }

    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT.replace("{SUBSTRING}", substring);
    }

    private static  String getSavedTitleElement(String substring){
        return SAVED_TITLE.replace("{SUBSTRING}", substring);
    }

    public void waitForSearchResultAndClick(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Невозможно найти: " + substring, 15);
    }

    public void saveToList(){
        this.waitForElementAndClick(By.xpath(SAVE_BUTTON), "Невозможно добавить в избранное", 15);
    }

    public void goToBack(){
        this.waitForElementAndClick(By.xpath(BACK_BUTTON), "Невозможно уйти назад", 15);
    }

    public void goToSaveList(){
        this.waitForElementAndClick(By.xpath(SAVE_LIST), "Невозможно найти сохраненное", 15);
    }

    public void goToSaveListInSavedAndGetOptions(){
        this.waitForElementAndLongPress(By.xpath(SAVE_LIST_IN_SAVED), "Невозможно отрыть сохраненное в сохраненном", 15);
    }

    public void deleteSavedTitle() {
        this.waitForElementAndClick(By.xpath(SAVED_TITLE_DELETE_OPTION), "Невозможно удалить статью", 15);
    }
}