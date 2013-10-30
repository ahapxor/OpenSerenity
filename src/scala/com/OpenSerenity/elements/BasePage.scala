package com.OpenSerenity.elements

import com.OpenSerenity.core.TestContext
import scala.Predef.String
import com.OpenSerenity.utils.Waiter

object BasePage {
  def open[TPage <: BasePage[TPage]](action: () => Unit)(implicit m: scala.reflect.Manifest[TPage]) = {
    val page: TPage = m.runtimeClass.newInstance().asInstanceOf
    page.open(action)
    page
  }
}

class BasePage[T <: BasePage[T]] {
  def open(action: () => Unit) = {
    action()
    windowHandle = TestContext.browser.getCurrentWindowHandle
    init
    waitForLoad
  }

  protected def openWindow(action: () => Unit) = throw new UnsupportedOperationException

  protected def init= {
    content = TestContext.browser.findElement[Element]( "body", () => {
        TestContext.browser.selectWindow(windowHandle)
        TestContext.browser.selectFrame(null)
        null
      })
  }

  def isStale = isClosed

  def getTitle: String = {
    selectWindow
    return TestContext.browser.getTitle
  }

  def waitForLoad = Waiter.default.waitFor(() => {
    try !isStale
    catch {
      case ex: Exception => false
    }
  })

  def selectWindow {
    try {
      if (windowHandle eq TestContext.browser.getCurrentWindowHandle) {
        return
      }
    }
    catch {
      case ex: Exception => {
        ex.printStackTrace
      }
    }
    TestContext.browser selectWindow windowHandle
  }

  def close: Unit = {
    if (isClosed) return
    selectWindow
    TestContext.browser closeWindow windowHandle
  }

  def isClosed: Boolean = {
    return !TestContext.browser.getAllWindowsHandles.contains(windowHandle)
  }

  def acceptAlert: BasePage[T] = {
    TestContext.browser.acceptMessageBox
    this
  }

  def acceptConfirmation: BasePage[T] = {
    TestContext.browser.acceptMessageBox
    this
  }

  def dismissConfirmation: BasePage[T] = {
    TestContext.browser.dismissMessageBox
    this
  }

  protected var content: BaseElement[_] = null
  protected var windowHandle: String = null
}


