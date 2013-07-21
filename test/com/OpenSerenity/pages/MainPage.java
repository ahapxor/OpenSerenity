package com.OpenSerenity.pages;

import com.OpenSerenity.elements.BasePage;
import com.OpenSerenity.elements.Button;

public class MainPage extends BasePage {
    private Button joinButton;
    public Button getJoinButton() throws InstantiationException, IllegalAccessException {
        if(joinButton == null) {
            joinButton = (Button) content.getChild(Button.class, "#joinOrLoginBtn");
        }
        return joinButton;
    }

    @Override
    public boolean isStale() throws Exception {
        return !getJoinButton().isVisible();
    }
}
