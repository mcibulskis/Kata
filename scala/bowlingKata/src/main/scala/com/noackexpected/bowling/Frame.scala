package com.noackexpected.bowling

import scala.None

class Frame(val frameNumber: Int, val firstRoll: Option[Int], val secondRoll: Option[Int], val thirdRoll: Option[Int]) {
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

  def needsRollsFromNextFrame(): Int = {
    (firstRoll, secondRoll, thirdRoll) match {
      case (Some(r1), None, None) if (r1 == 10) => 2
      case (Some(r1), Some(r2), None) if (r1 + r2 == 10) => 1
      case _ => 0
    }
  }
}
