package com.OpenSerenity.junit;

import com.OpenSerenity.elements.BasePage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class TestBase<TPage extends BasePage> {
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    private TPage page;

    protected TPage getPage() {
        return page;
    }

}
