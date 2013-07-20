package com.OpenSerenity.tests.mainPage;

import com.OpenSerenity.junit.TestBase;
import com.OpenSerenity.pages.MainPage;
import org.junit.Test;

public class mainPageTest extends TestBase<MainPage> {

    @Test
    public void testJoiButtonIsVisible() {
        page.getJoinButton().should(Be.visible);
    }
}
