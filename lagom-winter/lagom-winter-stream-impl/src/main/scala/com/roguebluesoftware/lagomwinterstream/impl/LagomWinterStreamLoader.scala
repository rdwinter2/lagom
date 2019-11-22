package com.roguebluesoftware.lagomwinterstream.impl

import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.server._
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import play.api.libs.ws.ahc.AhcWSComponents
import com.roguebluesoftware.lagomwinterstream.api.LagomWinterStreamService
import com.roguebluesoftware.lagomwinter.api.LagomWinterService
import com.softwaremill.macwire._

class LagomWinterStreamLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new LagomWinterStreamApplication(context) {
      override def serviceLocator: NoServiceLocator.type = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new LagomWinterStreamApplication(context) with LagomDevModeComponents

  override def describeService = Some(readDescriptor[LagomWinterStreamService])
}

abstract class LagomWinterStreamApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with AhcWSComponents {

  // Bind the service that this server provides
  override lazy val lagomServer: LagomServer = serverFor[LagomWinterStreamService](wire[LagomWinterStreamServiceImpl])

  // Bind the LagomWinterService client
  lazy val lagomWinterService: LagomWinterService = serviceClient.implement[LagomWinterService]
}
