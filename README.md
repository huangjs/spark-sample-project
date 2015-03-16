# Sample Spark project

## Highlights

- Sane compiler flags to scala
- `sbt console` starts a Spark and SparkSQL context
- Advanced static analyzer reports possible bugs and bad coding practices

## Libraries included

- Spark 1.3.0
- Scala 2.11.6
- ScalaTest 2.2.4
- ScalaCheck 1.12.2

## Static Analyzer

### linter (https://github.com/HairyFotr/linter)

Usage: automatically runs during Compilation and evaluation in console

### sbt-scapegoat (https://github.com/sksamuel/sbt-scapegoat)

Usage: automatically runs during Compilation

Open target/scala-2.11/scapegoat.xml or target/scala-2.11/scapegoat.html

## Coding Style Checker

### ScalaStyle

Usage: ```sbt scalastyle```

Open target/scalastyle-result.xml

Check level are all "warn", change to "error" if you want to reject code changes when integrated with CI tools.

## Author

- Jianshi Huang (jianshuang@paypal.com; jianshi.huang@gmail.com)
