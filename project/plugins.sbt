// You may use this file to add plugin dependencies for sbt.

resolvers += "Spark Packages repo" at "https://dl.bintray.com/spark-packages/maven/"

resolvers += "sonatype-releases" at "https://oss.sonatype.org/content/repositories/releases/"

addSbtPlugin("org.spark-packages" %% "sbt-spark-package" % "0.1.1")

// scapegoat: static analysis compiler plugin
addSbtPlugin("com.sksamuel.scapegoat" %% "sbt-scapegoat" % "0.94.6")

// scalastyle: coding style check and enforcer
addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "0.6.0")

