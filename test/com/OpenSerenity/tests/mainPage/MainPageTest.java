package com.OpenSerenity.tests.mainPage;

import com.OpenSerenity.dsl.predicates.Be;
import com.OpenSerenity.junit.TestBase;
import com.OpenSerenity.pages.MainPage;
import org.junit.Test;

public class MainPageTest extends TestBase<MainPage> {

    @Override
    protected String getTargetPageUrl() {
        return "";
    }

    @Override
    protected Class<MainPage> getPageClass() {
        return MainPage.class;
    }

    @Test
    public void testJoinButtonIsVisible() throws Exception {
        getPage()
                .getJoinButton()
                .should(Be.invisible);
    }

}
