// Your sbt build file. Guides on how to write one can be found at
// http://www.scala-sbt.org/0.13/docs/index.html

scalaVersion := "2.11.6"

// force scalaVersion
ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }

sparkVersion := "1.3.0-rc2"

// Change org name and project name
sparkPackageName := "organization/spark-sample-project"

// Don't forget to set the version
version := "0.1.0-SNAPSHOT"

// All Spark Packages need a license
licenses := Seq("Apache-2.0" -> url("http://opensource.org/licenses/Apache-2.0"))

// Java version
javacOptions ++= Seq("-source", "1.7", "-target", "1.7")

// add a JVM option to use when forking a JVM for 'run'
javaOptions ++= Seq("-Xmx1G")

// append -deprecation to the options passed to the Scala compiler
scalacOptions ++= Seq("-deprecation", "-unchecked")

// Use local repositories by default
resolvers ++= Seq(
  Resolver.defaultLocal,
  Resolver.mavenLocal,
  // For Typesafe goodies, if not available through maven
  //"Typesafe" at "http://repo.typesafe.com/typesafe/releases",
  // For Spark development versions, if you don't want to build spark yourself
  "Apache Staging" at "https://repository.apache.org/content/repositories/staging/"
  )


// Add Spark components this package depends on, e.g, "mllib", ....
sparkComponents ++= Seq("sql", "mllib")

// uncomment and change the value below to change the directory where your zip artifact will be created
// spDistDirectory := target.value

// add any sparkPackageDependencies using sparkPackageDependencies.
// e.g. sparkPackageDependencies += "databricks/spark-avro:0.1"


/// Dependencies

// scala modules (should be included by spark, just an exmaple)
//libraryDependencies ++= Seq(
//  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
//  "org.scala-lang" % "scala-compiler" % scalaVersion.value
//  )

// spark modules (should be included by spark-sql, just an example)
//libraryDependencies += "org.apache.spark" %% "spark-catalyst" % sparkVersion.value % "provided"

// logging
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0"

// testing
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

//libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.12.2" % "test"


/// Compiler plugins

// linter: static analysis for scala
resolvers += "Linter Repository" at "https://hairyfotr.github.io/linteRepo/releases"

addCompilerPlugin("com.foursquare.lint" %% "linter" % "0.1.8")


/// console

// define the statements initially evaluated when entering 'console', 'consoleQuick', or 'consoleProject'
// but still keep the console settings in the sbt-spark-package plugin
initialCommands in console += """
    |def time[T](f: => T): T = {
    |  import System.{currentTimeMillis => now}
    |  val start = now
    |  try { f } finally { println("Elapsed: " + (now - start)/1000.0 + " s") }
    |}
    |
    |@transient val sqlc = new org.apache.spark.sql.SQLContext(sc)
    |implicit def sqlContext = sqlc
    |import sqlc._
    |
    |""".stripMargin
