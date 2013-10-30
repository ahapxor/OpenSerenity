package com.OpenSerenity.utils

import com.OpenSerenity.core.TestContext
import java.util.Date
import com.OpenSerenity.exceptions.TimeoutException


class Waiter(timeout: Long, retryTime: Long) {
  def this() = this(TestContext.configuration.getTimeout, TestContext.configuration.getRetryTimeout)
  def this(timeout: Long) = this(timeout, TestContext.configuration.getRetryTimeout)


  def waitFor(waitCondition: () => Boolean): Unit = {
    try {
      val start: Long = new Date().getTime()
      do {
        try {
          if (waitCondition()) return
        } catch {
          case e: Exception => e.printStackTrace()
        } finally {
          sleepFor(retryTime)
        }
      } while (!timeoutReached(start))
    } catch {
      case e: Exception => e.printStackTrace()
    }

    throw new TimeoutException()
  }

  private def timeoutReached(start: Long): Boolean = (new Date().getTime() - start + retryTime) > timeout
  private def sleepFor(millis: Long) = Thread.sleep(millis)
}

object Waiter {
  def default = new Waiter()
  def withTimeout(_timeout: Long) = new Waiter(timeout = _timeout)
}
