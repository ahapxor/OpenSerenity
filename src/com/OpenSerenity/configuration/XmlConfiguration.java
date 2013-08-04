package com.OpenSerenity.configuration;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

public class XmlConfiguration implements Configuration {
    public XmlConfiguration() {
        xmlConfigFile = new XMLConfiguration();
        try {
            xmlConfigFile.load("conf/config.xml");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    private XMLConfiguration xmlConfigFile;

    @Override
    public String getBaseDomain() {
        return xmlConfigFile.getString("baseDomain");
    }

    @Override
    public long getTimeout() {
        return xmlConfigFile.getLong("timeout");
    }

    @Override
    public long getElementFindTimeout() {
        return xmlConfigFile.getLong("elementFindTimeout");
    }

    @Override
    public long getRetryTimeout() {
        return xmlConfigFile.getLong("retryTimeout");
    }

    @Override
    public String getBrowser() {
        return xmlConfigFile.getString("browser");
    }
}
