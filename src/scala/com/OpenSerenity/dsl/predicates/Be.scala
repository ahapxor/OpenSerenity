package com.OpenSerenity.dsl.predicates;

import com.OpenSerenity.elements.BaseElement

object Be {
  def visible() : BaseElement[_] => Boolean = (element : BaseElement[_]) => element.isVisible
  def inVisible() : BaseElement[_] => Boolean = (element : BaseElement[_]) => element.isInVisible
  def empty() : BaseElement[_] => Boolean = (element : BaseElement[_]) => element.isEmpty
  def notEmpty() : BaseElement[_] => Boolean = (element : BaseElement[_]) => !element.isEmpty
  def selected() : BaseElement[_] => Boolean = (element : BaseElement[_]) => element.isSelected
  def notSelected() : BaseElement[_] => Boolean = (element : BaseElement[_]) => !element.isSelected
}