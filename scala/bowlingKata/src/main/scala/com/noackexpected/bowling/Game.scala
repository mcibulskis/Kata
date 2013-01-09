package com.noackexpected.bowling

class Game {
  private var rolls: Seq[Int] = Seq()

  def roll(numberOfPins: Int): Unit = {
    rolls match {
      case allRolls if allRolls.isEmpty => rolls = Seq(numberOfPins)
      case _ => rolls = rolls ++ Seq(numberOfPins)
    }
  }

  def score(): Int = {
    rolls.foldLeft(0) { (sum, roll) => sum + roll }
  }
}
