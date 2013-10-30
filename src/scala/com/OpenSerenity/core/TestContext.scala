package com.OpenSerenity.core

import com.OpenSerenity.configuration.{XmlConfiguration, Configuration}

object TestContext {
  var browser: Browser = null
  val configuration: Configuration  = new XmlConfiguration()
}