package com.noackexpected.bowling

import scala.None

case class Frame(val frameNumber: Int, val firstRoll: Option[Int], val secondRoll: Option[Int], val thirdRoll: Option[Int]) {
  def this(frameNumber: Int, firstRoll: Option[Int]) = this(frameNumber, firstRoll, None, None)
  def this(frameNumber: Int, firstRoll: Option[Int], secondRoll: Option[Int]) = this(frameNumber, firstRoll, secondRoll, None)

  // validation is not exhaustive...
  (frameNumber, firstRoll, secondRoll, thirdRoll) match {
    case (fn, _, _, _) if (fn <= 0) => require(false, "The frame number must be greater than 0")
    case (fn, _, _, _) if (fn > 10) => require(false, "The frame number must be less than or equal to 10")
    case (fn, Some(r1), Some(r2), Some(r3)) if (fn != 10) => require(false, "A third roll may not be specified in a frame other than the 10th frame")
    case (fn, Some(r1), _, _) if (r1 < 0) => require(false, "A roll may not be less than 0")
    case (fn, Some(r1), Some(r2), _) if (r2 < 0) => require(false, "A roll may not be less than 0")
    case (fn, Some(r1), Some(r2), Some(r3)) if (r3 < 0) => require(false, "A roll may not be less than 0")
    case (fn, Some(r1), _, _) if (r1 > 10) => require(false, "A roll may not be greater than 10")
    case (fn, Some(r1), Some(r2), _) if (r2 > 10) => require(false, "A roll may not be greater than 10")
    case (fn, Some(r1), Some(r2), Some(r3)) if (r3 > 10) => require(false, "A roll may not be greater than 10")
    case (fn, Some(r1), Some(r2), Some(r3)) if (r1 + r2 < 10) => require(false, "A third roll is allowed on the 10th frame only if a spare or strike is rolled in the first two rolls")
    case _ => Unit
  }

  def isFull(): Boolean = {
    this match {
      case Frame(10, Some(r1), Some(r2), None) if (r1 + r2 >= 10) => false
      case Frame(10, Some(r1), None, None) if (r1 == 10) => false
      case Frame(_, Some(r1), None, None) if (r1 < 10) => false
      case _ => true
    }
  }

  def needsAdditionalRolls(): Int = {
    (firstRoll, secondRoll, thirdRoll) match {
      case (Some(r1), None, None) if (r1 == 10) => 2
      case (Some(r1), Some(r2), None) if (r1 + r2 == 10) => 1
      case _ => 0
    }
  }

  def score(subsequentFrames: Seq[Frame]): Int = {
    def sumOfRollsFromSubsequentFrames(subsequentFrames: Seq[Frame]): Int = {
      val numRolls = needsAdditionalRolls()
      subsequentFrames match {
        case Frame(_, Some(r1), None, None) :: Frame(_, Some(nr1), _, _) :: _ if (numRolls == 2 && r1 == 10) => r1 + nr1
        case Frame(_, Some(r1), Some(r2), _) :: _ if (numRolls == 2) => r1 + r2
        case Frame(_, Some(r1), _, _) :: _ if (numRolls >= 1) => r1
        case _ => 0
      }
    }

    val scoreFromThisFrame = this match {
      case Frame(10, Some(r1), Some(r2), Some(r3)) => r1 + r2 + r3
      case Frame(_, Some(r1), None, None) => r1
      case Frame(_, Some(r1), Some(r2), None) => r1 + r2
      case _ => 0
    }

    scoreFromThisFrame + sumOfRollsFromSubsequentFrames(subsequentFrames)
  }
}
