package oop.lab5
import cui.*
import jdk.jshell.spi.ExecutionControl.UserException

import javax.swing.ImageIcon
import scala.collection.mutable
import scala.collection.mutable.*

trait Learner:
  def tell(query: String, answer: String) = "Got it!"

trait Oracle extends Learner:
  val dunno = "Sorry, I don't know"
  var debug = true
  def ask(query: String): String =
    if (debug) println("asking oracle")
    dunno

object QueryConsole extends Console with App with MagicOracle with MathOracle with KbaseOracle with Oracle with GeoOracle with BioOracle with Learner {
  override def execute(query: String): String =
    val eqPos = query.indexOf("=")
    var isFact = ( eqPos != -1)
    if (isFact) {
      tell(query.substring(0, eqPos).trim, query.substring(eqPos + 1).trim)
    } else {
      ask(query.trim)
    }
  repl
}

trait MathOracle extends Oracle {
  override def ask(query: String): String =
    if (debug) println("asking math oracle")
    try {
      this.eval(query)
    } catch {
      case e => super.ask(query)
    }
  private def eval(query: String): String = {
    val tokens = query.split("\\s+")
    if (tokens.length <= 1) throw UserError("Must have at least one argument")
    val args = tokens.drop(1).map(_.toDouble) // might throw here too
    var result = 0.0
    tokens(0) match {
      case "add" => for (arg <- args) result += arg; result.toString
      case "sub" => for (arg <- args) result -= arg; result.toString
      case "mul" => result = 1; for (arg <- args) result *= arg; result.toString
      case "div" => result = 1; for (arg <- args) result /= arg; result.toString
      case _ => throw UserError("Unrecognized operator: " + tokens(0))
    }
  }
}

trait MagicOracle extends Oracle {
  override def ask(query: String): String = {
    if (debug) println("asking magic oracle")
    try {
      this.eval(query)
    } catch {
      case e => super.ask(query)
    }
  }
  private def eval(query: String): String = {
    if (query.takeRight(1) != "?") throw UserError("Not a question")
    else {
      val rng = util.Random()
      var num = rng.nextInt(3)
      num match {
        case 0 =>
          "Ask later"
        case 1 =>
          "Doubtful"
        case 2 =>
          "Perhaps"
        case _ =>
          "It is possible"
      }
    }
  }

}

trait KbaseOracle extends Oracle with Learner {
  var kbase = Map[String, String]()

  override def ask(query: String): String =
    if (debug) println("asking kbase oracle")
    if (kbase.get(query) == None)
      super.ask(query)
    else
      kbase(query)


  override def tell(query: String, answer: String): String =
    kbase(query) = answer
    super.tell(query, answer)

}

trait GeoOracle extends KbaseOracle:
  kbase("capitol of CA") = "Sacramento"
  kbase("capitol of VA") = "Richmond"
  kbase("capitol of NY") = "Albany"
  kbase("capitol of TX") = "Austin"



trait BioOracle extends KbaseOracle:
    kbase("giraffe is a") = "mammal"
    kbase("ant is a") = "insect"
    kbase("snake is a") = "reptile"
    kbase("tuna is a") = "fish"
