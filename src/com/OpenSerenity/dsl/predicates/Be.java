package com.OpenSerenity.dsl.predicates;


import com.OpenSerenity.dsl.Assertion;
import com.OpenSerenity.elements.BaseElement;

public class Be {
    public static Assertion visible = new Assertion() {
        @Override
        public boolean invoke(BaseElement element) throws Exception {
            return element.isVisible();
        }
    };
    public static Assertion invisible = new Assertion() {
        @Override
        public boolean invoke(BaseElement element) throws Exception {
            return !element.isVisible();
        }
    };
}
