package com.OpenSerenity.webDriver

import com.OpenSerenity.core.{LocalStorage, TestContext, DriverFactory, Browser}
import com.OpenSerenity.utils.Waiter
import org.openqa.selenium._
import com.OpenSerenity.elements.{BaseElement, WdNativeElement, NativeElement}
import org.apache.commons.lang3.StringUtils
import scala.Predef.String
import java.io.{IOException, File}
import org.apache.commons.io.FileUtils
import java.util.{Calendar, Date}
import scala.collection.JavaConverters._
import scala.collection.mutable

class WebDriverBrowser extends Browser {
  def findElement[TElement <: BaseElement[TElement]](locator: String, selectFrame: () => Browser)(implicit m: scala.reflect.Manifest[TElement]): TElement = {
    Waiter.default.waitFor(() => driver.findElements(By.cssSelector(locator)).size > 0)
    val nativeElement: NativeElement = new WdNativeElement(() => {
        selectFrame()
        findWdNativeElement(locator)
    })
    val element: TElement = m.runtimeClass.newInstance().asInstanceOf
    element.setNativeElement(nativeElement)
    element.setLocator(locator)
    element.init
    return element
  }

  private def findWdNativeElement(locator: String): WebElement = {
    Waiter.default.waitFor(() => driver.findElements(By.cssSelector(locator)).size > 0)
    return driver.findElement(By.cssSelector(locator))
  }

  def start {
    driver = DriverFactory.createBrowser
    localStorage = new WebDriverLocalStorage(driver)
    maximize
  }

  def stop {
    driver.quit
  }

  def open(url: String) {
    if (url.startsWith("http")) {
      driver.navigate.to(url)
      return
    }
    driver.navigate.to(TestContext.configuration.getBaseDomain + url)
  }

  def close {
    driver.close
  }

  def resizeTo(width: Int, height: Int) {
    driver.manage.window.setSize(new Dimension(width, height))
  }

  def maximize {
    driver.manage.window.maximize
  }

  def back {
    driver.navigate.back
  }

  def refresh {
    driver.navigate.refresh
  }

  def selectFrame(locator: String) {
    if (StringUtils.isNotBlank(locator)) {
      driver.switchTo.frame(locator)
    }
    else {
      driver.switchTo.defaultContent
    }
  }

  def selectWindow(handle: String) {
    driver.switchTo.window(handle)
  }

  def closeWindow(handle: String) {
    driver.switchTo.window(handle).close
  }

  def getCurrentWindowHandle: String = {
    return driver.getWindowHandle
  }

  def getAllWindowsHandles: mutable.Set[String] = driver.getWindowHandles().asScala

  def getTitle: String = {
    return driver.getTitle
  }

  def getLocation: String = {
    return driver.getCurrentUrl
  }

  def getMessageBoxText: String = {
    val alert: Alert = driver.switchTo.alert
    return alert.getText
  }

  def acceptMessageBox {
    val alert: Alert = driver.switchTo.alert
    alert.accept
  }

  def dismissMessageBox {
    val alert: Alert = driver.switchTo.alert
    alert.dismiss
  }

  def captureEntirePageScreenShot(filename: String) {
    if (driver.isInstanceOf[TakesScreenshot]) {
      val tempFile: File = (driver.asInstanceOf[TakesScreenshot]).getScreenshotAs(OutputType.FILE)
      try {
        FileUtils.copyFile(tempFile, new File("screenshots/" + new Date + ".png"))
      }
      catch {
        case e: IOException => {
          e.printStackTrace
        }
      }
    }
  }

  def createCookie(name: String, value: String) {
    val cal: Calendar = Calendar.getInstance
    cal.setTime(new Date)
    cal.add(Calendar.MONTH, 1)
    val cookie: Cookie = new Cookie(name, value, null, cal.getTime)
    driver.manage.addCookie(cookie)
  }

  def getCookie(name: String): String = {
    val cookie: Cookie = driver.manage.getCookieNamed(name)
    if (cookie == null) {
      return null
    }
    return cookie.getValue
  }

  def deleteCookie(name: String, path: String) {
    driver.manage.deleteCookieNamed(name)
  }

  def acceptAnyAlert {
  }

  def getLocalStorage: LocalStorage = {
    return localStorage
  }

  private[webDriver] var driver: WebDriver = null
  private[webDriver] var localStorage: LocalStorage = null
}

