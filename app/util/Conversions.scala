package jousse
package util

import scala.language.implicitConversions
import com.twitter.util.Time
import java.util.Date

trait Conversions {

  implicit def twitterTimeToJavaDate(time: Time): Date = time.toDate

  implicit def javaDateToTwitterTime(date: Date): Time = Time(date)
}

