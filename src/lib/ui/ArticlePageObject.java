package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{

    private static final String
            TITLE = "id:org.wikipedia:id/view_page_title_text",
            FOOTER_ELEMENT = "id:org.wikipedia:id/page_external_link",
            OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']",
            OPTIONS_ADD_TO_NY_LIST_BUTTON = "xpath://*[@text='Add to reading list']",
            ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button",
            MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "xpath://*[@text='OK']",
            CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";

    public ArticlePageObject(AppiumDriver driver){
        super(driver);
    }

    public WebElement waitForTitleElement(){
        return this.waitForElementPresent(TITLE, "Cannot find article title on page",15);
    }

    public String getArticleTitle(){
        WebElement title_element = waitForTitleElement();
        return title_element.getText();
    }

    public void swipeToFooter(){
        this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find the end of article",10);
    }

    public void addArticleToMyList(String name_of_folder) {

        clickOptionsButtonAndAddToMyList();

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'Got it' tip overlay",
                5);

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input to see name of article folder",
                5);


        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot find input to see name of article folder",
                5);

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press Ok button",
                5);
    }

    public void closeArticle(){
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot close article, cannot find x link",
                5);
    }

    public void addArticleToExistingList() {
        clickOptionsButtonAndAddToMyList();

        this.waitForElementAndClick(
                "id:org.wikipedia:id/item_title",
                "Cannot find list for saving",
                5);

        this.waitForElementAndClick(
                "xpath://android.widget.ImageButton[@content-desc='Navigate up']",
                "Cannot close article, cannot find x link",
                5);

    }

        public void goToTheList (String name_of_folder){
            this.waitForElementAndClick(
                "xpath://android.widget.FrameLayout[@content-desc='My lists']",
                "Cannot find navigation button to My List",
                5);

        this.waitForElementAndClick(
                "xpath://*[@text= '"+ name_of_folder + "']",
                "Cannot find created folder",
                5);
    }

    public void assertTitlePresent () {
        this.assertElementPresent(
                TITLE,
                "Cannot find title");

    }

    private void clickOptionsButtonAndAddToMyList() {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5);

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_NY_LIST_BUTTON,
                "Cannot find options to add article to reading list",
                5);
    }
}

