Table of content generated using [this app](https://tableofcontents.herokuapp.com/):

- [Introduction](#introduction)
- [Users](#users)
  - [Library users](#library-users)
    - [Built output](#built-output)
    - [Some known users](#some-known-users)
- [Contributors](#contributors)
  - [Setup](#setup)
  - [Deployment](#deployment)
    - [Regarding **maven targets** in intellij](#regarding-**maven-targets**-in-intellij)
    - [Releasing to maven.](#releasing-to-maven.)
    - [Building a jar.](#building-a-jar.)
  - [Technical choices](#technical-choices)
    - [Scala](#scala)

# Introduction
A collection of scala and java classes for some basic character level processing for the Sanskrit and other Indic (kannada, telugu, etc..) languages, contributed by the open source sanskrit-coders projects and friends.
Some notable facilities:
* Transliterate text from one script or encoding scheme to another.

# Users
## Library users
* Maven repository [here](http://search.maven.org/#search%7Cga%7C1%7Ca%3A%22indic%22) .
* Last update : 2017-05-??

### Built output
* Final jar files
  * out/*.jar [all modules in intellij project]
  * target/*.jar [includes sources and javadocs in separate jars. indic-transliteration module only]
* Classes
  * out/production/*/ [Modules other than indic-transliteration.]
  * target/ [sanskritnlp module output.]

### Some known users
* [stardict-sanskrit]() and sister stardict-.* projects.

## Libraries in other languages
- For python: [indic-transliteration pip](https://pypi.python.org/pypi/indic-transliteration) .
- For Java / Scala: [indic-transliteration](https://search.maven.org/#search%7Cga%7C1%7Ca%3A%22indic-transliteration%22) maven.
- For JS:
  - [sanscript](https://github.com/sanskrit/sanscript.js/blob/3e109b09d0e69de1afb166ebd4d1ffb4e340a0c3/sanscript/sanscript.js) .
  - [salita](https://github.com/mbykov/salita) - limited to a few Roman transliterations and devanAgarI.
- PHP: [Dicrunch ](https://github.com/nareshv/aksharamukha/tree/master/diCrunch) and its use by [akSharamukhA](https://github.com/nareshv/aksharamukha/blob/master/transliterate.php) .

# Contributors
## Setup
* Strongly recomment Intellij Idea IDE.
  * Just point it to the IML file files.
  * If it complains about missing maven dependencies:
     * just open the *Maven projects* widget, refresh and do a full build.
     * Also try Maven -> reimport and *synchronoze* actions in the context menu.
* There may also be eclipse files which haven't been used in a long time.

## Deployment
### Regarding **maven targets** in intellij
  * Easiest: Use the View -> Tool Windows -> "Maven Projects" window.  
  * You can set up a maven goal as a run configuration.
  * In intellij: Don't be fooled by weird messages in the Run widget - look at the messages widget.

### Releasing to maven.
* Note that we're using appengine-maven-plugin in <pom.xml>, and credentials stored in settings.xml (<-- not to be checked in) .
* Deploy snapshot artifacts into repository <https://oss.sonatype.org/content/repositories/snapshots/com/github/sanskrit-coders/sanskritnlp>.
  * Version number ends with -SNAPSHOT. Eg. 1.0-SNAPSHOT
  * Build and release target: clean deploy.
  * intellij target name: "mvn deploy".
* Deploy release artifacts into the [staging repository](https://oss.sonatype.org/content/repositories/releases/com/github/sanskrit-coders/sanskritnlp/) and [here](http://repo1.maven.org/maven2/com/github/sanskrit-coders/) :
  * Repeat the same with a non snapshot version number.
* Releasing to central (if it does not automatically happen):
  * Notes: <http://central.sonatype.org/pages/releasing-the-deployment.html>
  * Artifacts can be examined on Sonatype [here](https://oss.sonatype.org/#nexus-search;quick~sanskrit) and released - if the staging repository is visible there. Otherwise, it may already be deployed in central!
  * Maven target can be used: nexus-staging:release . There is an intellij target of the same name.
  * "After you successfully release, your component will be published to Central, typically within 10 minutes, though updates to search.maven.org can take up to two hours."
* Group id was created under Sonatype:  [here](https://issues.sonatype.org/browse/OSSRH-29183) , and we received exemption from the javadoc requirement alter ([link](https://issues.sonatype.org/browse/MVNCENTRAL-2253?filter=-2)).

### Building a jar.
* Simplest way is to set up a build artifact in intellij IDea.
* There was also a maven target in pom.xml which I've used occasionally.

## Technical choices
### Scala
* One can write much more concise code (1/4th to 1/3rd relative to Java and 3/4ths to 5/6ths relative to Python, according to [this](http://bcomposes.com/2012/03/01/student-questions-about-scala-part-2/) )
  * For example, the ease with which one can iterate in scala using higher order functions (the maps, filters and zips above) available with scala's excellent collections library.
* while not sacrificing the ability to use java libraries, and readability/ speed of java.
* It is increasing in popularity relative to competitors : scala vs clojure ( [Google trends](https://trends.google.com/trends/explore?date=all&q=Scala%20tutorial,Clojure%20tutorial) ), scala vs julia ( [Google trends](https://trends.google.com/trends/explore?date=all&q=Scala%20tutorial,Julia%20tutorial) ).
* [Here](http://bcomposes.com/2011/08/22/first-steps-in-scala-for-first-time-programmers-part-1/) is a good series of blog posts which provide an introduction to Scala.
