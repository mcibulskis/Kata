package com.noackexpected.bowling

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class FrameSpec extends FlatSpec with ShouldMatchers {

  it should "return 4 if 4 was supplied as the first roll" in {
    val impl = new Frame(1, Some(4))
    impl.firstRoll should equal(Some(4))
    impl.secondRoll should be(None)
    impl.thirdRoll should be(None)
  }

  it should "return 4 and 5 for the first and second rolls" in {
    val impl = new Frame(1, Some(4), Some(5))
    impl.firstRoll should equal(Some(4))
    impl.secondRoll should equal(Some(5))
    impl.thirdRoll should be(None)
  }

  it should "return 4, 6, and 7 for the first, second, and third rolls" in {
    val impl = new Frame(10, Some(4), Some(6), Some(7))
    impl.firstRoll should equal(Some(4))
    impl.secondRoll should equal(Some(6))
    impl.thirdRoll should equal(Some(7))
  }

  //
  // Decide whether a frame is "full" (needs no further rolls)
  //
  behavior of "Decide whether a frame is full (needs no further rolls)"

  it should "return false if the frame has only a single roll that was not a strike" in {
    val impl = new Frame(3, Some(4))
    impl.isFull() should equal(false)
  }

  it should "return false if the frame has two rolls, is a spare, and is the 10th frame" in {
    val impl = new Frame(10, Some(4), Some(6))
    impl.isFull() should equal(false)
  }

  it should "return false if the frame has one roll that is a strike and is in the 10th frame" in {
    val impl = new Frame(10, Some(10))
    impl.isFull() should equal(false)
  }

  it should "return false if the frame has two rolls, has a strike as the first roll and a non-strike as the second roll in the 10th frame" in {
    val impl = new Frame(10, Some(10), Some(6))
    impl.isFull() should equal(false)
  }

  it should "return true if the frame has two rolls and is not the 10th frame" in {
    val impl = new Frame(3, Some(4), Some(5))
    impl.isFull() should equal(true)
  }

  it should "return true if the frame has three rolls and is the 10th frame" in {
    val impl = new Frame(10, Some(4), Some(6), Some(5))
    impl.isFull() should equal(true)
  }

  //
  // Calculate the score of this frame
  //
  behavior of "Calculating the score for this frame"

  it should "return 0 if no rolls in this frame" in {
    val impl = new Frame(3, None)
    impl.score(List()) should equal(0)
  }

  it should "return the first roll as the score if only one roll in this frame" in {
    val impl = new Frame(3, Some(3))
    impl.score(List()) should equal(3)
  }

  it should "return the first roll plus the second roll if no next frame" in {
    val impl = new Frame(3, Some(7), Some(3))
    impl.score(List()) should equal(10)
  }

  it should "return the sum of all three rolls if it is the 10th frame" in {
    val impl = new Frame(10, Some(7), Some(3), Some(5))
    impl.score(List()) should equal(15)
  }

  it should "return the sum of two rolls if it is in the 10th frame and has fewer than 3 rolls" in {
    val impl = new Frame(10, Some(6), Some(3))
    impl.score(List()) should equal(9)
  }

  it should "return the sum of the first two rolls in the frame plus the first from the next frame on a spare" in {
    val impl = new Frame(3, Some(7), Some(3))
    val nextFrame = new Frame(4, Some(4), Some(5))
    impl.score(List(nextFrame)) should equal(14)
  }

  it should "return the sum of the first roll plus the first and second rolls from the next frame on a strike" in {
    val impl = new Frame(3, Some(10))
    val nextFrame = new Frame(4, Some(4), Some(5))
    impl.score(List(nextFrame)) should equal(19)
  }

  it should "return the sum of the first roll plus the first from the next frame on a strike if a second roll is not available" in {
    val impl = new Frame(3, Some(10))
    val nextFrame = new Frame(4, Some(4))
    impl.score(List(nextFrame)) should equal(14)
  }

  it should "return the sum of the first roll from this frame, the next frame, and the frame after if two strikes were thrown in a row" in {
    val impl = new Frame(3, Some(10))
    val nextFrame = new Frame(4, Some(10))
    val thirdFrame = new Frame(5, Some(3))
    impl.score(List(nextFrame, thirdFrame)) should equal(23)
  }

  it should "return 20 as the score for two strikes, with one in the 9th frame and one in the 10th frame" in {
    val impl = new Frame(9, Some(10))
    val nextFrame = new Frame(10, Some(10))
    impl.score(List(nextFrame)) should equal(20)
  }

  it should "return 30 as the score for the frame for three strikes, with one in the 9th frame and two in the 10th frame" in {
    val impl = new Frame(9, Some(10))
    val nextFrame = new Frame(10, Some(10), Some(10))
    impl.score(List(nextFrame)) should equal(30)
  }

  it should "return 30 as the score for three strikes in the 10th frame" in {
    val impl = new Frame(10, Some(10), Some(10), Some(10))
    impl.score(Nil) should equal(30)
  }

  //
  // Rolls from the next frame needed to score this frame fully
  //
  behavior of "Number of rolls needed for scoring from the next frame should be calculated correctly"

  it should "return 0 rolls needed if no rolls from the next frame are needed for scoring when the frame has score less than 10" in {
    val impl = new Frame(1, Some(4))
    impl.needsAdditionalRolls should equal(0)
  }

  it should "return 1 roll needed if the frame had a spare in a frame prior to the 10th" in {
    val impl = new Frame(2, Some(3), Some(7))
    impl.needsAdditionalRolls should equal(1)
  }

  it should "return 2 rolls needed if the frame had a strike in a frame prior to the 10th" in {
    val impl = new Frame(1, Some(10))
    impl.needsAdditionalRolls should equal(2)
  }

  it should "return 0 rolls needed if the 10th frame had a strike in the third roll" in {
    val impl = new Frame(10, Some(8), Some(2), Some(10))
    impl.needsAdditionalRolls should equal(0)
  }

  it should "return 0 rolls needed if the 10th frame had a spare in the first and second roll" in {
    val impl = new Frame(10, Some(8), Some(2), Some(7))
    impl.needsAdditionalRolls should equal(0)
  }

  it should "return the correct frame number for the frame" in {
    val impl = new Frame(3, Some(4))
    impl.frameNumber should equal(3)
  }

  //
  // Sanity checks
  //
  behavior of "Values of a Frame should be sanity-checked"

  it should "throw an illegal argument exception if the frame number is less than 1" in {
    intercept[IllegalArgumentException] {
      new Frame(0, Some(1))
    }
  }

  it should "throw an illegal argument exception if the frame number is greater than 10" in {
    intercept[IllegalArgumentException] {
      new Frame(11, Some(1))
    }
  }

  it should "throw an illegal argument exception if the first roll is less than 0" in {
    intercept[IllegalArgumentException] {
      new Frame(1, Some(-1))
    }
  }

  it should "throw an illegal argument exception if the first roll is greater than 10" in {
    intercept[IllegalArgumentException] {
      new Frame(1, Some(11))
    }
  }

  it should "throw an illegal argument exception if the second roll is less than 0" in {
    intercept[IllegalArgumentException] {
      new Frame(1, Some(3), Some(-1))
    }
  }

  it should "throw an illegal argument exception if the second roll is greater than 10" in {
    intercept[IllegalArgumentException] {
      new Frame(1, Some(3), Some(11))
    }
  }

  it should "throw an illegal argument exception if the third roll is less than 0" in {
    intercept[IllegalArgumentException] {
      new Frame(1, Some(3), Some(7), Some(-1))
    }
  }

  it should "throw an illegal argument exception if the third roll is greater than 10" in {
    intercept[IllegalArgumentException] {
      new Frame(1, Some(3), Some(7), Some(11))
    }
  }

  it should "throw an illegal argument exception if the third roll is provided when the frame is not frame 10" in {
    intercept[IllegalArgumentException] {
      new Frame(5, Some(7), Some(3), Some(2))
    }
  }

  it should "throw an illegal argument exception if the third roll is provided in the 10th frame if the first two rolls do not add to at least 10" in {
    intercept[IllegalArgumentException] {
      new Frame(10, Some(2), Some(3), Some(4))
    }
  }
}
