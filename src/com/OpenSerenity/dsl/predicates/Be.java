package com.OpenSerenity.dsl.predicates;


import com.OpenSerenity.dsl.Assertion;
import com.OpenSerenity.elements.BaseElement;

public class Be {
    public static Assertion visible = new Assertion() {
        @Override
        public boolean invoke(BaseElement element) throws Exception {
            boolean result = element.isVisible();
            System.out.println(String.format("%s is visible %s", element.toString(), result));
            return result;
        }
    };
    public static Assertion invisible = new Assertion() {
        @Override
        public boolean invoke(BaseElement element) throws Exception {
            boolean result = element.isInVisible();
            System.out.println(String.format("%s is visible %s", element.toString(), result));
            return result;
        }
    };
}
