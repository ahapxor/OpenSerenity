package com.OpenSerenity.core;

import com.OpenSerenity.configuration.Configuration;
import com.OpenSerenity.configuration.XmlConfiguration;

public abstract class TestContext {
    public static Browser browser;
    public static Configuration configuration = new XmlConfiguration();
}
