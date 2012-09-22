package jousse
package util

import com.twitter.util.Time
import com.twitter.util.Duration
import com.twitter.conversions.time._
import scala.math._
import java.text.SimpleDateFormat
import java.util.Date
import net.sf.jfuzzydate.{FuzzyDateFormat, FuzzyDateFormatter}

object StringHelper extends StringHelper with Conversions

trait StringHelper {

  private object Slugifier {
    def slugify(text: String) = trimUnderscores(removeNonWord(text))

    def removeNonWord(text: String) = """\W+""".r.replaceAllIn(text, "_")

    def trimUnderscores(text: String) = """^\_|\_$""".r.replaceAllIn(text, "")
  }

  def slugify(text: String): String = Slugifier.slugify(text)

  def formatDate(date: Time) = date format "dd/MM/yyy"

  def getYear(date: Time) = date format "yyy"

  def getMonth(date: Time) = date format "MM"

  def formatDistance(date: Date) = fuzzyFormatter formatDistance date

  private val fuzzyFormatter = FuzzyDateFormat.getInstance

  def pluralize(s: String, n: Int) = "%d %s%s".format(n, s, if (n > 1) "s" else "")
}
