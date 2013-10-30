package com.OpenSerenity.elements

abstract class BaseListElement[TElement <: BaseElement[TElement]] extends BaseElement[TElement] {
  def selectByIndex(index: Int)
  def selectByText(optionText: String)
  def getSelectedItemText(): String
  def items(): List[TElement]

  def get(text: String): TElement = items find(text == _.getText()) get
  def get(index: Int): TElement = items()(index)
}