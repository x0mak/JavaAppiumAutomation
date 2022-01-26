package tests;

import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchTests extends CoreTestCase {

    private lib.ui.MainPageObject MainPageObject;
    protected void setUp() throws Exception{
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testSearch(){

        SearchPageObject SearchPageObject= new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch(){

        SearchPageObject SearchPageObject= new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();

    }

    @Test
    public void testAmountOfNotEmptySearch(){

        SearchPageObject SearchPageObject= new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park Discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticle();

        assertTrue("We found too few results!",
                amount_of_search_results > 0);
    }

    @Test
    public void testAmountOfEmptySearch() {

        SearchPageObject SearchPageObject= new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        String search_line = "345345234525423544";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testCheckExpectedText(){
        MainPageObject.waitForElementPresent(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5);

        MainPageObject.assertElementHasText(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Search Wikipedia",
                "Text is incorrect",
                5);
    }

    @Test
    public void testCancelSearchAndCheckList(){
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5);

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Harry Potter",
                "Cannot find search input",
                5);

        assertNotNull("Список пустой", MainPageObject.getElements(By.id("org.wikipedia:id/page_list_item_container")));

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find 'х' to cancel search",
                5);

        assertNull("Список не пустой", MainPageObject.getElements(By.id("org.wikipedia:id/page_list_item_container")));

    }

    @Test
    public void testCheckTextInSearchResults(){
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5);

        String searchCriteria = "Harry Potter";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                searchCriteria,
                "Cannot find search input",
                5);

        List<WebElement> searchResultTitles = MainPageObject.getElements(By.id("org.wikipedia:id/page_list_item_title"));
        assertNotNull("Список пустой", searchResultTitles);

        searchResultTitles.forEach(title -> assertTrue(title.getText().toLowerCase().contains(searchCriteria.toLowerCase())));
    }
}
