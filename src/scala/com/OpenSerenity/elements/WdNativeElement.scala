package com.OpenSerenity.elements

import org.openqa.selenium.{StaleElementReferenceException, By, WebElement}
import scala.Predef.String
import com.OpenSerenity.utils.Waiter
import com.OpenSerenity.core.TestContext

class WdNativeElement extends NativeElement {
  private[elements] def getWdElement: WebElement = {
    if (isStale) {
      wdElement = wdElementSelector()
    }
    wdElement
  }

  def this(wdElementSelector: () => WebElement) {
    this()
    this.wdElementSelector = wdElementSelector
  }

  def getTagName: String = {
    try {
      return wdElement.getTagName
    }
    catch {
      case ex: Exception => {
        ex.printStackTrace
        return ""
      }
    }
  }

  def getText = getWdElement.getText.trim
  def isEnabled = getWdElement.isEnabled
  def isSelected = getWdElement.isSelected
  def isDisplayed = getWdElement.isDisplayed

  def isExists = {
    try {
      getWdElement != null
    }
    catch {
      case ex: Exception => {
        ex.printStackTrace
        false
      }
    }
  }

  def clear: Unit = getWdElement.clear()
  def sendKeys(text: String): Unit = getWdElement.sendKeys(text)
  def submit: Unit = getWdElement.submit()
  def click: Unit = getWdElement.click()
  def altClick = throw new UnsupportedOperationException

  def getAttribute(attributeName: String) = {
    try {
      getWdElement.getAttribute(attributeName)
    }
    catch {
      case ex: Exception => {
        ex.printStackTrace
        ""
      }
    }
  }

  def getCssValue(propertyName: String) = {
    try {
      getWdElement.getCssValue(propertyName)
    }
    catch {
      case ex: Exception => {
        ex.printStackTrace
        ""
      }
    }
  }

  def findChild(locator: String): NativeElement = {
    return new WdNativeElement(() => {
        Waiter
          .withTimeout(TestContext.configuration.getElementFindTimeout)
          .waitFor(() => getWdElement.findElement(By.cssSelector(locator)) != null)
        getWdElement.findElement(By.cssSelector(locator))
    })
  }

  def findChildren(locator: String): List[NativeElement] = {
    throw new NotImplementedError()
  }

  def isStale = {
    try {
      if (wdElement == null) {
        true
      }
      wdElement.findElement(By.xpath("."))
      val res: Boolean = wdElement.isDisplayed
      false
    }
    catch {
      case ex: StaleElementReferenceException => {
        ex.printStackTrace
        true
      }
    }
  }

  private var wdElementSelector: () => WebElement = null
  private var wdElement: WebElement = null
}


