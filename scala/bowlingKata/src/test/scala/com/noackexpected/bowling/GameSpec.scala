package com.noackexpected.bowling

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class GameSpec extends FlatSpec with ShouldMatchers {

  private def createFixture(rolls: Seq[Int]) = {
    val impl = new Game
    rolls.map { roll => impl.roll(roll) }
    impl
  }

  //
  // checking rolls placed in correct frames
  //

  behavior of "Rolls should be placed in the correct frames"

  it should "have no frames if no rolls have been made" in {
    val impl = new Game
    impl.lastFrame() should equal(0)
  }

  it should "have one frame if one roll has been made" in {
    val impl = createFixture(List(3))
    impl.lastFrame() should equal(1)
  }

  it should "have one frame if two rolls making a spare have been made" in {
    val impl = createFixture(List(3, 7))
    impl.lastFrame() should equal(1)
  }

  it should "have two frames if two rolls with the first being a strike was made" in {
    val impl = createFixture(List(10, 3))
    impl.lastFrame() should equal(2)
  }

  it should "have two frames if three rolls with the first frame being a spare" in {
    val impl = createFixture(List(3, 7, 4))
    impl.lastFrame() should equal(2)
  }

  it should "have three frames if three rolls with the first two being strikes were made" in {
    val impl = createFixture(List(10, 10, 4))
    impl.lastFrame() should equal(3)
  }

  it should "have 10 frames if 12 strikes were made" in {
    val impl = createFixture(List(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10))
    impl.lastFrame() should equal(10)
  }

  //
  // scoring
  //

  behavior of "Scoring should work properly"

  it should "have a score equal to zero if no rolls have been made" in {
    val impl = new Game
    impl.score() should equal(0)
  }

  it should "have a score equal to the roll if there has only been a single roll" in {
    val impl = createFixture(List(5))
    impl.score() should equal(5)
  }

  it should "have a score equal to 10 if they rolled a strike on the first roll" in {
    val impl = createFixture(List(10))
    impl.score() should equal(10)
  }

  it should "have a score equal to 7 if they rolled a 3 on the first roll and a 4 on the second" in {
    val impl = createFixture(List(3, 4))
    impl.score() should equal(7)
  }

  it should "have a score equal to 10 if they rolled a 3, 4, and 3" in {
    val impl = createFixture(List(3, 4, 3))
    impl.score() should equal(10)
  }

  it should "have a score equal to 16 if they rolled a spare and a 3" in {
    val impl = createFixture(List(3, 7, 3))
    impl.score() should equal(16)
  }

  it should "have a score equal to 60 if they rolled three strikes" in {
    val impl = createFixture(List(10, 10, 10))
    impl.score() should equal(60)
  }

  it should "have a score equal to 90 if they rolled four strikes" in {
    val impl = createFixture(List(10, 10, 10, 10))
    impl.score() should equal(90)
  }

  it should "have a score equal to 120 if they rolled five strikes" in {
    val impl = createFixture(List(10, 10, 10, 10, 10))
    impl.score() should equal(120)
  }

  it should "have a score equal to 150 if they rolled six strikes" in {
    val impl = createFixture(List(10, 10, 10, 10, 10, 10))
    impl.score() should equal(150)
  }

  it should "have a score equal to 180 if they rolled seven strikes" in {
    val impl = createFixture(List(10, 10, 10, 10, 10, 10, 10))
    impl.score() should equal(180)
  }

  it should "have a score equal to 210 if they rolled eight strikes" in {
    val impl = createFixture(List(10, 10, 10, 10, 10, 10, 10, 10))
    impl.score() should equal(210)
  }

  it should "have a score equal to 240 if they rolled nine strikes" in {
    val impl = createFixture(List(10, 10, 10, 10, 10, 10, 10, 10, 10))
    impl.score() should equal(240)
  }

  it should "have a score equal to 270 if they rolled ten strikes" in {
    val impl = createFixture(List(10, 10, 10, 10, 10, 10, 10, 10, 10, 10))
    impl.score() should equal(270)
  }

  it should "have a score equal to 290 if they rolled eleven strikes" in {
    val impl = createFixture(List(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10))
    impl.score() should equal(290)
  }

  it should "have a score equal to 300 if they rolled a perfect game" in {
    val impl = createFixture(List(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10))
    impl.score() should equal(300)
  }
}
