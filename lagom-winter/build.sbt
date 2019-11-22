organization in ThisBuild := "com.roguebluesoftware"
version in ThisBuild := "1.0-SNAPSHOT"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.12.8"

val macwire = "com.softwaremill.macwire" %% "macros" % "2.3.0" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.4" % Test

lazy val `lagom-winter` = (project in file("."))
  .aggregate(`lagom-winter-api`, `lagom-winter-impl`, `lagom-winter-stream-api`, `lagom-winter-stream-impl`)

lazy val `lagom-winter-api` = (project in file("lagom-winter-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `lagom-winter-impl` = (project in file("lagom-winter-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra,
      lagomScaladslKafkaBroker,
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings)
  .dependsOn(`lagom-winter-api`)

lazy val `lagom-winter-stream-api` = (project in file("lagom-winter-stream-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `lagom-winter-stream-impl` = (project in file("lagom-winter-stream-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .dependsOn(`lagom-winter-stream-api`, `lagom-winter-api`)
