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

  it should "return 0 rolls needed if no rolls from the next frame are needed for scoring when the frame has score less than 10" in {
    val impl = new Frame(1, Some(4))
    impl.needsRollsFromNextFrame should equal(0)
  }

  it should "return 1 roll needed if the frame had a spare in a frame prior to the 10th" in {
    val impl = new Frame(2, Some(3), Some(7))
    impl.needsRollsFromNextFrame should equal(1)
  }

  it should "return 2 rolls needed if the frame had a strike in a frame prior to the 10th" in {
    val impl = new Frame(1, Some(10))
    impl.needsRollsFromNextFrame should equal(2)
  }

  it should "return 0 rolls needed if the 10th frame had a strike in the third roll" in {
    val impl = new Frame(10, Some(8), Some(2), Some(10))
    impl.needsRollsFromNextFrame should equal(0)
  }

  it should "return 0 rolls needed if the 10th frame had a spare in the first and second roll" in {
    val impl = new Frame(10, Some(8), Some(2), Some(7))
    impl.needsRollsFromNextFrame should equal(0)
  }

  it should "return the correct frame number for the frame" in {
    val impl = new Frame(3, Some(4))
    impl.frameNumber should equal(3)
  }

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
