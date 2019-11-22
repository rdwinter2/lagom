package com.roguebluesoftware.lagomwinterstream.api

import akka.NotUsed
import akka.stream.scaladsl.Source
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}

/**
  * The Lagom Winter stream interface.
  *
  * This describes everything that Lagom needs to know about how to serve and
  * consume the LagomWinterStream service.
  */
trait LagomWinterStreamService extends Service {

  def stream: ServiceCall[Source[String, NotUsed], Source[String, NotUsed]]

  override final def descriptor: Descriptor = {
    import Service._

    named("lagom-winter-stream")
      .withCalls(
        namedCall("stream", stream)
      ).withAutoAcl(true)
  }
}

