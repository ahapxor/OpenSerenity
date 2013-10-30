package com.OpenSerenity.elements

import scala.Predef.String

trait NativeElement {
  def getTagName(): String
  def getText(): String
  def isEnabled(): Boolean
  def isSelected(): Boolean
  def isDisplayed(): Boolean
  def isExists(): Boolean
  def clear()
  def sendKeys(text: String)
  def submit()
  def click()
  def altClick()
  def getAttribute(attributeName: String): String
  def getCssValue(propertyName: String): String
  def findChild(locator: String): NativeElement
  def findChildren(locator: String): List[NativeElement]
}


