import jousse.util.Conversions
import scala.language.implicitConversions

package object jousse
  extends Conversions {

  /**
  * K combinator implementation
  * Provides oneliner side effects
  * See http://hacking-scala.posterous.com/side-effecting-without-braces
  */

  implicit def addKcombinator[A](any: A) = new {
    def kCombinator(sideEffect: A => Unit): A = {
      sideEffect(any)
      any
    }
    def ~(sideEffect: A => Unit): A = kCombinator(sideEffect)
  }
}
