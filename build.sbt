name := "GuiceSample"

version := "0.1"

scalaVersion := "2.12.7"

val scalaTestVersion = "3.0.5"
val guiceVersion = "4.2.1"

resolvers ++= Seq(
  "apache-snapshots" at "https://repository.apache.org/content/repositories/releases/"
)

libraryDependencies ++= Seq(
  "com.google.inject" % "guice" % guiceVersion,
    "org.scalatest" %% "scalatest" % scalaTestVersion % "test"
)
