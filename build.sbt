import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.4",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "ai-alpakka",
    libraryDependencies += scalaTest % Test
  )

resolvers += Resolver.mavenLocal

// mast to have jboss repo for jms.jar
// dont understand why it is not there in mvn central repo.
resolvers += "JBoss Repo" at "https://repository.jboss.org"

libraryDependencies += "com.lightbend.akka" %% "akka-stream-alpakka-jms" % "0.17"
libraryDependencies += "javax.jms" % "jms" % "1.1"
libraryDependencies += "org.apache.activemq" % "activemq-all" % "5.15.3"


