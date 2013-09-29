package com.noackexpected.conwayGameOfLife

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class CellSpec extends FlatSpec with ShouldMatchers {
  behavior of "Living cells on a transition"

  it should "not be alive if it has no living adjacent cells" in {
    LivingCell.transitionWithAdjacentLivingCells(0) should be(DeadCell)
  }

  it should "be alive if it has two living adjacent cells" in {
    LivingCell.transitionWithAdjacentLivingCells(2) should be(LivingCell)
  }

  it should "be alive if it has three living adjacent cells" in {
    LivingCell.transitionWithAdjacentLivingCells(3) should be(LivingCell)
  }

  it should "not be alive if there are more than three living adjacent cells" in {
    LivingCell.transitionWithAdjacentLivingCells(4) should be(DeadCell)
  }

  behavior of "Dead cells on a transition"

  it should "be alive if it has exactly three living adjacent cells" in {
    DeadCell.transitionWithAdjacentLivingCells(3) should be(LivingCell)
  }

  it should "be dead if it has any other number of living adjacent cells" in {
    DeadCell.transitionWithAdjacentLivingCells(0) should be(DeadCell)
  }
}
