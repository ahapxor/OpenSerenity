package com.OpenSerenity.tests.mainPage;

import com.OpenSerenity.dsl.predicates.Be;
import com.OpenSerenity.junit.TestBase;
import com.OpenSerenity.pages.MainPage;
import org.junit.Test;

public class MainPageTest extends TestBase<MainPage> {

    @Test
    public void testJoiButtonIsVisible() {
        getPage().getJoinButton().should(Be.visible);
    }
}
