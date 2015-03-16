package example

import java.sql.Timestamp

import org.apache.spark.sql.test._
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.Gen._
import org.scalacheck.Prop.{exists, forAll}
import org.scalatest.FunSuite
import org.scalatest.prop.Checkers._

/**
 * Created by jianshuang on 3/11/15.
 */
class ExampleTest extends FunSuite {

  import example.Example._

  implicit def sqlc = TestSQLContext

  test("randomDataset has correct #rows") {
    val d = randomDataset(10)
//    d.collect().foreach(println)

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

  val datasetGen =
    for (
      id <- arbitrary[String];
      ts <- choose(0L, System.currentTimeMillis());
      v <- arbitrary[Double];
      tag <- choose(1, 4)
    ) yield RandomRow(id, new Timestamp(ts), v, tag, "")

  test("tag range is in range") {
    check(
      forAll(datasetGen) { d =>
        d.tag >= 1 && d.tag < 5
      })
  }

}
