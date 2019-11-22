package com.roguebluesoftware.lagomwinterstream.impl

import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.roguebluesoftware.lagomwinterstream.api.LagomWinterStreamService
import com.roguebluesoftware.lagomwinter.api.LagomWinterService

import scala.concurrent.Future

/**
  * Implementation of the LagomWinterStreamService.
  */
class LagomWinterStreamServiceImpl(lagomWinterService: LagomWinterService) extends LagomWinterStreamService {
  def stream = ServiceCall { hellos =>
    Future.successful(hellos.mapAsync(8)(lagomWinterService.hello(_).invoke()))
  }
}
