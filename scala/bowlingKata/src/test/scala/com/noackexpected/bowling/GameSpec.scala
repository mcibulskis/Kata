package com.noackexpected.bowling

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class GameSpec extends FlatSpec with ShouldMatchers {
  private def createFixture(rolls: Seq[Int]) = {
    val impl = new Game
    rolls.map { roll => impl.roll(roll) }
    impl
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
}
