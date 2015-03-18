package example

import org.apache.spark.sql.{SQLContext, DataFrame}

/**
 * Created by jianshuang on 3/11/15.
 */
object Example {

  case class RandomRow(id: String, timestamp: java.sql.Timestamp, value: Double, tag: Int, comment: String) {
    def this(id: String) = {
      this(
        id,
        new java.sql.Timestamp(System.currentTimeMillis()),
        (math.random * 100).round / 100.0,
        randomInt(1, 5),
        ""
      )
    }
  }

  /**
   * Returns a random dataset of [[RandomRow]]
   *
   * @param nrows   number of rows
   */
  def randomDataset(nrows: Int)(implicit sqlc: SQLContext): DataFrame = {
    val data = 0.until(nrows).map(i => new RandomRow(i.toString))
    sqlc.createDataFrame(sqlc.sparkContext.parallelize(data))
  }

  /**
   * Returns pseudo-random integer from specific range.
   *
   * Generated number is great or equals to min parameter value and less then max parameter value.
   * Uses [[scala.math.random]]
   *
   * @param min    lower (inclusive) boundary
   * @param max    higher (exclusive) boundary
   */
  def randomInt (min: Int, max: Int): Int = {
    min + (math.random * (max - min)).toInt
  }

}
