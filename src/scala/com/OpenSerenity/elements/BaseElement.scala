package com.OpenSerenity.elements

import com.OpenSerenity.utils.Waiter
import com.OpenSerenity.exceptions.AssertionException
import com.OpenSerenity.dsl.predicates.Be

class BaseElement[TElement <: BaseElement[TElement]] {
  var locator: String = null
  def setLocator(locator: String) = this.locator = locator

  var nativeElement: NativeElement = null
  def setNativeElement(nativeElement: NativeElement) = this.nativeElement = nativeElement

  def init() = {}

  override def toString: String = {
    try {
      return String.format("%s[locator=%s]", nativeElement.getTagName(), locator)
    } catch {
      case e: Exception => {
        e.printStackTrace()
        return null
      }
    }
  }

  def should(assertion: BaseElement[_] => Boolean): BaseElement[TElement] = {
    var result: Boolean = true;
    try {
      Waiter.default.waitFor(() => assertion(this))
    } catch {
      case ex: Exception => result = false
    }

    if (!result) throw new AssertionException()

    return this
  }

  def waitFor(assertion: BaseElement[_] => Boolean) = should(assertion)

  def getChild[T <: BaseElement[T]](locator: String)(implicit m: scala.reflect.Manifest[T]) = {
    if (locator == null) throw new IllegalArgumentException("locator must be not null")

    val element: T = m.runtimeClass.newInstance().asInstanceOf
    element.setNativeElement(nativeElement.findChild(locator))
    element.setLocator(locator)
    element.init()

    element
  }

  def isVisible = nativeElement.isDisplayed()
  def isInVisible = !nativeElement.isExists() || !nativeElement.isDisplayed()
  def isExists = nativeElement.isExists()
  def isSelected = nativeElement.isSelected()
  def isEmpty = getText() == null || getText().isEmpty

  def click = {
    waitFor (Be visible())
    nativeElement.click()
    this
  }

  def typeText(text: String) = {
    waitFor (Be visible())
    nativeElement.sendKeys(text)
    this
  }

  def clear = {
    waitFor (Be visible())
    nativeElement.clear()
    this
  }

  def getText() = {
    waitFor (Be visible())
    nativeElement.getText()
  }

  def getAttribute(attributeName: String) = nativeElement.getAttribute(attributeName)
}
