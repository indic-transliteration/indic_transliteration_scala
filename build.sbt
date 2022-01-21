
// Source instrctions: http://www.scala-sbt.org/1.0/docs/Using-Sonatype.html . Not completed.

name := "indic-transliteration"

scalaVersion := "2.12.8"
val logbackVersion = "1.2.3"
val json4sVersion = "3.6.1"
val scalatestVersion = "3.0.5"

resolvers += Resolver.sonatypeRepo("releases")
resolvers += Resolver.sonatypeRepo("snapshots")
resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % logbackVersion
  ,"ch.qos.logback" % "logback-core" % logbackVersion
  ,"org.json4s" % "json4s-ast_2.12" % json4sVersion
  ,"org.json4s" % "json4s-native_2.12" % json4sVersion
  ,"tech.sparse" % "toml-scala_2.12" % "0.2.2"
 //    ,"com.github.sanskrit-coders" % "sanskrit-lttoolbox" % "0.1"
  //  ,"com.github.sanskrit-coders" % "indic-transliteration" % "1.8"
)

libraryDependencies += "org.scalactic" %% "scalactic" % scalatestVersion
libraryDependencies += "org.scalatest" %% "scalatest" % scalatestVersion % "test"

//unmanagedJars in (Compile, run) += file("/home/vvasuki/indic-transliteration/target/indic-transliteration-1.8.jar")
//unmanagedClasspath in (Compile, run) += file("/home/vvasuki/indic-transliteration/target/indic-transliteration-1.8/classes")
//unmanagedSources in (Compile, run)  += file("/home/vvasuki/indic-transliteration/src/main/scala")

scmInfo := Some(
  ScmInfo(
    url("https://github.com/indic-transliteration/indic_transliteration_scala"),
    "scm:git@github.com:indic-transliteration/indic_transliteration_scala.git"
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
