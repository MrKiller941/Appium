package org.example;

import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;

public class Main extends CoreTestCase {

    private MainPageObject mainPageObject;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mainPageObject = new MainPageObject(driver);
    }

    public void testSearchTest(){
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Хоббит, или Туда и обратно");
        searchPageObject.waitForSearchResultAndClick("Хоббит, или Туда и обратно");
        searchPageObject.saveToList();
        searchPageObject.goToBack();
        searchPageObject.goToBack();
        searchPageObject.goToSaveList();
        searchPageObject.goToSaveListInSavedAndGetOptions();
        searchPageObject.deleteSavedTitle();
    }
}