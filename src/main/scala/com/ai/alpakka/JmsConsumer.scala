package com.ai.alpakka

import akka.stream.KillSwitch
import akka.stream.alpakka.jms.JmsSourceSettings
import akka.stream.alpakka.jms.scaladsl.JmsSource
import akka.stream.scaladsl.Source
import com.ai.alpakka.JmsComponent.{connectionFactory, topic}

class JmsConsumer extends App {

  val jmsSource: Source[String, KillSwitch] = JmsSource.textSource(
    JmsSourceSettings(connectionFactory)
      .withBufferSize(10)
      .withQueue(topic)
  )

}
