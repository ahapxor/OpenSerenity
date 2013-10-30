package com.OpenSerenity.core

import scala.Predef.String
import com.OpenSerenity.elements.BaseElement

abstract trait Browser {
  def findElement[TElement <: BaseElement[TElement]](locator: String, selectFrame: () => Browser)(implicit m: scala.reflect.Manifest[TElement]): TElement;
  def start
  def stop
  def open(url: String)
  def close
  def resizeTo(width: Int, height: Int)
  def maximize
  def back
  def refresh
  def selectFrame(locator: String)
  def selectWindow(handle: String)
  def closeWindow(handle: String)
  def getCurrentWindowHandle: String
  def getAllWindowsHandles: scala.collection.mutable.Set[String]
  def getTitle: String
  def getLocation: String
  def getMessageBoxText: String
  def acceptMessageBox
  def dismissMessageBox
  def captureEntirePageScreenShot(filename: String)
  def createCookie(name: String, value: String)
  def getCookie(name: String): String
  def deleteCookie(name: String, path: String)
  def acceptAnyAlert
  def getLocalStorage: LocalStorage
}
