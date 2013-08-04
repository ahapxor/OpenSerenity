package com.OpenSerenity.elements;

import java.util.List;

public abstract class BaseListElement<TElement extends BaseElement> extends BaseElement<TElement> {
    public abstract void selectByIndex(int index) throws Exception;
    public abstract void selectByText(String optionText) throws Exception;
    public abstract String getSelectedItemText() throws Exception;
    public abstract List<TElement> getItems() throws Exception;
    public TElement get(String text) throws Exception {
        for(TElement element : getItems()) {
            if(text.equals(element.getText())) {
                return element;
            }
        }
        throw new Exception("element not found");
    };
    public TElement get(int index) throws Exception {
        return getItems().get(index);
    };
}
