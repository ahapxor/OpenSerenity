package com.OpenSerenity.dsl.predicates;

import com.OpenSerenity.dsl.ElementAssertion;
import com.OpenSerenity.elements.BaseElement;

public class Have {
    public static ElementAssertion text(final String text) {
        ElementAssertion assertion = new ElementAssertion() {
            @Override
            public boolean invoke(BaseElement element) throws Exception {
                String elementText = element.getText();
                boolean result = text.equals(elementText);
                System.out.println(String.format("%s should have text %s, actual text %s", element.toString(), text, elementText));
                return result;
            }
        };
        return assertion;
    }
}
