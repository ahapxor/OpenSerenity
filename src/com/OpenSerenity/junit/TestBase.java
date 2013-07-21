package com.OpenSerenity.junit;

import com.OpenSerenity.core.TestContext;
import com.OpenSerenity.elements.BasePage;
import com.OpenSerenity.functionalInterfaces.Action;
import com.OpenSerenity.webDriver.WebDriverBrowser;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public abstract class TestBase<TPage extends BasePage> {
    @BeforeClass
    public static void setUpClass() throws Exception {
        TestContext.browser = new WebDriverBrowser();
        TestContext.browser.start();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        openTargetPage();
    }

    @After
    public void tearDown() {
    }

    protected void openTargetPage() throws Exception {
        if(page != null)
        {
            page.selectWindow();
        }
        openPage(getTargetPageUrl());
    }

    protected abstract String getTargetPageUrl();

    private void openPage(final String url) throws Exception {
        this.page = BasePage.open(getPageClass(), new Action() {
            @Override
            public void invoke(Object o) {
                TestContext.browser.selectFrame(null);
                TestContext.browser.open(url);
                TestContext.browser.acceptAnyAlert();
            }
        });
    }

    protected abstract Class<TPage> getPageClass();

    private TPage page;

    protected TPage getPage() {
        return page;
    }

}
