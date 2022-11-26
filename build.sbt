
// Source instructions:
// https://github.com/xerial/sbt-sonatype
// See also sonatype.sbt and ~/.sbt/1.0/sonatype.sbt
// Currently stuck at https://github.com/xerial/sbt-sonatype/issues/330 .

name := "indic-transliteration"

scalaVersion := "2.13.10"
val logbackVersion = "1.2.3"
val json4sVersion = "4.0.6"
val scalatestVersion = "3.2.14"

resolvers +=
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
resolvers +=
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/releases"
resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % logbackVersion
  ,"ch.qos.logback" % "logback-core" % logbackVersion
  ,"org.json4s" % "json4s-ast_2.13" % json4sVersion
  ,"org.json4s" % "json4s-native_2.13" % json4sVersion
  ,"tech.sparse" % "toml-scala_2.13" % "0.2.2"
 //    ,"com.github.sanskrit-coders" % "sanskrit-lttoolbox" % "0.1"
  //  ,"com.github.sanskrit-coders" % "indic-transliteration" % "1.8"
)

//libraryDependencies += "org.scalactic" %% "scalactic" % scalatestVersion
libraryDependencies += "org.scalatest" %% "scalatest" % scalatestVersion % "test"

//unmanagedJars in (Compile, run) += file("/home/vvasuki/indic-transliteration/target/indic-transliteration-1.8.jar")
//unmanagedClasspath in (Compile, run) += file("/home/vvasuki/indic-transliteration/target/indic-transliteration-1.8/classes")
//unmanagedSources in (Compile, run)  += file("/home/vvasuki/indic-transliteration/src/main/scala")



assembly / assemblyOutputPath := file("bin/artifacts/indic-transliteration.jar")
//assembly / mainClass := Some("stardict_sanskrit.commandInterface")


publishMavenStyle := true
publishTo := sonatypePublishToBundle.value

import ReleaseTransformations._

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  releaseStepCommand("assembly"),
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  releaseStepCommand("publishSigned"),
  releaseStepCommand("sonatypeBundleRelease"),
  setNextVersion,
  commitNextVersion,
  pushChanges
)
