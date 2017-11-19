// Source instrctions: http://www.scala-sbt.org/1.0/docs/Using-Sonatype.html . Not completed.

name := "indic-transliteration"
version := "1.8"

scalaVersion := "2.12.3"

resolvers += Resolver.sonatypeRepo("releases")
resolvers += Resolver.sonatypeRepo("snapshots")
resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % "0.9.29"
  ,"ch.qos.logback" % "logback-core" % "0.9.29"
  ,"org.json4s" % "json4s-ast_2.12" % "3.5.2"
  ,"org.json4s" % "json4s-native_2.12" % "3.5.2"
 //    ,"com.github.sanskrit-coders" % "sanskrit-lttoolbox" % "0.1"
  //  ,"com.github.sanskrit-coders" % "indic-transliteration" % "1.8"
)

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.4"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test"

//unmanagedJars in (Compile, run) += file("/home/vvasuki/indic-transliteration/target/indic-transliteration-1.8.jar")
//unmanagedClasspath in (Compile, run) += file("/home/vvasuki/indic-transliteration/target/indic-transliteration-1.8/classes")
//unmanagedSources in (Compile, run)  += file("/home/vvasuki/indic-transliteration/src/main/scala")

scmInfo := Some(
  ScmInfo(
    url("https://github.com/sanskrit-coders/indic-transliteration"),
    "scm:git@github.com:sanskrit-coders/indic-transliteration.git"
  )
)

useGpg := true
publishMavenStyle := true
publishTo := Some(
  if (isSnapshot.value)
    Opts.resolver.sonatypeSnapshots
  else
    Opts.resolver.sonatypeStaging
)

import ReleaseTransformations._

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  releaseStepCommand("publishSigned"),
  setNextVersion,
  commitNextVersion,
  releaseStepCommand("sonatypeReleaseAll"),
  pushChanges
)
