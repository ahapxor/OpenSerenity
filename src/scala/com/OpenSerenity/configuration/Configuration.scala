package com.OpenSerenity.configuration

trait Configuration {
  def getBaseDomain(): String
  def getTimeout: Long
  def getElementFindTimeout: Long
  def getRetryTimeout: Long
  def getBrowser: String
}
