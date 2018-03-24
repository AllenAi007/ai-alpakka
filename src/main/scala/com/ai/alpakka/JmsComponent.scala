package com.ai.alpakka

import akka.Done
import akka.stream.KillSwitch
import akka.stream.alpakka.jms._
import akka.stream.alpakka.jms.scaladsl._
import akka.stream.scaladsl.{Sink, Source}
import org.apache.activemq.ActiveMQConnectionFactory

import scala.concurrent.Future

/**
  *
  */
object JmsComponent extends App {

  val connectionFactory: javax.jms.ConnectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616")

  val topic = "ai-alpakka-in"

  // producer
  val jmsSink: Sink[String, Future[Done]] = JmsSink.textSink(
    JmsSinkSettings(connectionFactory)
      .withTopic(topic)
      .withAcknowledgeMode(AcknowledgeMode.AutoAcknowledge)
  )

  // consumer
  val jmsSource: Source[String, KillSwitch] = JmsSource.textSource(
    JmsSourceSettings(connectionFactory)
      .withBufferSize(10)
      .withQueue(topic)
  )

  val in = List("a", "b", "c", "d")

  // produce message
  println(s"Sending message -> $in")
  Source(in).runWith(jmsSink)

  // consume message
  println("Pulling message")
  val result = jmsSource.take(in.size).runWith(Sink.seq)
  println(s"Read messages -> $result")

}
