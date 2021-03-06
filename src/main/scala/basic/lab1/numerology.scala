package basic.lab1

object numerology extends App {

  // problem 1
  def kingdom(n: Int): Int =
    if (n % 2 == 0)
      if (10 < n)
        if (n % 100 == 0)
          2
        else
          1
      else
        3
    else
      4

  // testing
  println(s"kingdom(5) = ${kingdom(5)}")     // 4
  println(s"kingdom(11) = ${kingdom(11)}")   // 4
  println(s"kingdom(16) = ${kingdom(16)}")   // 1
  println(s"kingdom(5) = ${kingdom(5)}")     // 4
  println(s"kingdom(200) = ${kingdom(200)}") // 2

  // problem 2
  def order(n: Int): Int =
    if (n < 0)
      0
    else
      family(n) * ilk(n) + genus(n)

  def family(n: Int) =
    if (n % 3 == 0)
      1
    else
      2

  def ilk(n: Int) =
    if (n == 50)
      3
    else
      4

  def genus(n: Int) =
    if (n % 7 == 0)
      5
    else
      6

  println(s"order(-1) = ${order(-1)}")   // 0
  println(s"order(15) = ${order(15)}")   // 10
  println(s"order(50) = ${order(50)}")   // 12
  println(s"order(49) = ${order(49)}")   // 13
  println(s"order(21) = ${order(21)}")   // 9

  // problem 3

  // original version
  // Missing an else statement if the number isn't greater positive
  def species(n: Int) =
    if (0 < n) if (n % 2 == 0) 1 else 2

  // corrected version
  def species2(n: Int): Int =
    if (n > 0)
      if (n % 2 == 0)
        1
      else
        2
    else
      2

  println(s"species(1) = ${species(1)}")
  println(s"species(-1) = ${species(-1)}")
  println(s"species(4) = ${species(4)}")
  println(s"species2(1) = ${species2(1)}")
  println(s"species2(-1) = ${species2(-1)}")
  println(s"species2(4) = ${species2(4)}")



  // problem 4

  // odd positives are realm 1
  def realm1(n: Int): Int =
    if (n > 0)
      if (n % 2 != 0)
        1
      else throw new Exception
    else throw new Exception



  // even positives not divisible by 3 are realm 2
  def realm2(n: Int): Int =
    if (n > 0)
      if (n % 2 == 0)
        if (n % 3 != 0)
          2
        else throw new Exception
      else throw new Exception
    else throw new Exception

  // even positives divisible by 6 and 7 are realm 3
  def realm3(n: Int): Int =
    if (n > 0)
      if (n % 6 == 0)
        if (n % 7 == 0)
          3
        else throw new Exception
      else throw new Exception
    else throw new Exception

  def realm(n: Int): Int =
    try {
      realm1(n)
    } catch {
      case e: Exception => try {
        realm2(n)
      } catch {
        case e: Exception => try {
          realm3(n)
        } catch {
          case e: Exception => 0
        }
      }
    }


  println(s"realm(0) = ${realm(0)}")   // 0
  println(s"realm(4) = ${realm(4)}")   // 2
  println(s"realm(5) = ${realm(5)}")   // 1
  println(s"realm(42) = ${realm(42)}") // 3
  println(s"realm(9) = ${realm(9)}")   // 1

  // problem 5

  // odd positives are realm 1
  def realm1Opt(n: Int): Option[Int] =
    if (n > 0)
      if (n % 2 != 0)
        Some(1)
      else None
    else None


  // even positives not divisible by 3 are realm 2
  // another style: check for bad news first
  def realm2Opt(n: Int): Option[Int] =
    if (n > 0)
      if (n % 2 == 0)
        if (n % 3 != 0)
          Some(2)
        else None
      else None
    else None

  // even positives divisible by 6 and 7 are realm 3
  def realm3Opt(n: Int): Option[Int] =
    if (n > 0)
      if (n % 6 == 0)
        if (n % 7 == 0)
          Some(3)
        else None
      else None
    else None

  def realmOpt(n: Int): Option[Int] =
    if (realm1Opt(n) != None)
      Some(1)
    else if  (realm2Opt(n) != None)
      Some(2)
    else if (realm3Opt(n) != None)
      Some(3)
    else
      None

  println(s"realmOpt(0) = ${realmOpt(0)}")   // 0
  println(s"realm2Opt(4) = ${realm2Opt(4)}")   // 2
  println(s"realmOpt(42) = ${realmOpt(42)}") // 3
  println(s"realmOpt(9) = ${realmOpt(9)}")   // 1

}
