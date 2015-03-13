package example

import org.apache.spark.sql.test._
import org.scalatest.FunSuite
import org.scalacheck.Prop.{forAll, exists}
import org.scalatest.prop.Checkers._

/**
 * Created by jianshuang on 3/11/15.
 */
class ExampleTest extends FunSuite {

  import Example._
  implicit def sqlc = TestSQLContext

  test("randomDataset has correct #rows") {
    val d = randomDataset(10)
    d.collect().foreach(println)

    assert(d.collect().size === 10)
    assert(randomDataset(0).collect().size === 0)
  }

  test("randomInt properties: from is inclusive, to is exclusive") {
    val propInclusiveFrom = exists { (from: Int) =>
      randomInt(from, from + 10) == from
      randomInt(from, from + 1) == from
      randomInt(from, from + 0) == from
      randomInt(from, from + -1) == from
      randomInt(from, from + -10) == from
    }

    val propExclusiveTo = forAll { (from: Int, to: Int) =>
      if (from != to)
        randomInt(from, to) != to
      else
        randomInt(from, to) == to
    }

    check(propInclusiveFrom)
    check(propExclusiveTo)
  }

}
