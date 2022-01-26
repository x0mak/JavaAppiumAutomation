package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;

public class ArticleTests extends CoreTestCase {

    private lib.ui.MainPageObject MainPageObject;
    protected void setUp() throws Exception{
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testCompareArticleTitle(){

        SearchPageObject SearchPageObject= new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String article_title = ArticlePageObject.getArticleTitle();

        assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title);
    }

    @Test
    public void testSwipeArticle(){

        SearchPageObject SearchPageObject= new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Appium");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();
    }

    @Test
    public void testSaveTwoArticleAndDeleteOne(){

        String search_wikipedia_locator = "//*[contains(@text, 'Search Wikipedia')]";
        String search_locator = "//*[contains(@text, 'Search…')]";
        String title_locator = "org.wikipedia:id/view_page_title_text";
        String more_options_locator = "//android.widget.ImageView[@content-desc='More options']";
        String add_to_list_locator = "//*[@text='Add to reading list']";
        String navigate_up_locator = "//android.widget.ImageButton[@content-desc='Navigate up']";
        String java_text_locator = "//*[@text='Java (programming language)']";
        String search_linkinpark_line = "Linkin Park discography";
        String name_of_folder = "Favorite list";


        MainPageObject.waitForElementAndClick(
                By.xpath(search_wikipedia_locator),
                "Cannot find 'Search Wikipedia' input",
                5);

        MainPageObject.waitForElementAndSendKeys(
                By.xpath(search_locator),
                "Java",
                "Cannot find search input",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find anything about java",
                5);

        MainPageObject.waitForElementPresent(
                By.id(title_locator),
                "Cannot find article title",
                15);

        MainPageObject.waitForElementAndClick(
                By.xpath(more_options_locator),
                "Cannot find button to open article options",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath(add_to_list_locator),
                "Cannot find options to add article to reading list",
                5);

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' tip overlay",
                5);

        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to see name of article folder",
                5);

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot find input to see name of article folder",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press Ok button",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath(navigate_up_locator),
                "Cannot close article, cannot find x link",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath(search_wikipedia_locator),
                "Cannot find 'Search Wikipedia' input",
                5);

        MainPageObject.waitForElementAndSendKeys(
                By.xpath(search_locator),
                search_linkinpark_line,
                "Cannot find search input",
                5);

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        MainPageObject.waitForElementAndClick(
                By.xpath(search_result_locator),
                "Cannot find anything by the request " + search_linkinpark_line,
                15);

        MainPageObject.waitForElementPresent(
                By.id(title_locator),
                "Cannot find article title",
                15);

        MainPageObject.waitForElementAndClick(
                By.xpath(more_options_locator),
                "Cannot find button to open article options",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath(add_to_list_locator),
                "Cannot find options to add article to reading list",
                5);

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/item_title"),
                "Cannot find list for saving",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath(navigate_up_locator),
                "Cannot close article, cannot find x link",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button to My List",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text= '"+ name_of_folder + "']"),
                "Cannot find created folder",
                5);

        MainPageObject.swipeElementToLeft(
                By.xpath(java_text_locator),
                "Cannot find saved article");

        MainPageObject.waitForElementNotPresent(
                By.xpath(java_text_locator),
                "Cannot delete saved article",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='"+ search_linkinpark_line + "']"),
                "Cannot find" + search_linkinpark_line,
                5);

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/view_page_title_text'][@text='"+ search_linkinpark_line + "']"),
                "Cannot find article title",
                15);
    }

    @Test
    public void testCheckTitle(){
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5);

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Cannot find search input",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Search Wikipedia' input",
                5);

        MainPageObject.assertElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find title");
    }


}
