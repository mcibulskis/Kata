package com.noackexpected.conwayGameOfLife

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec

class EnvironmentSpec extends FlatSpec with ShouldMatchers {
  it should "start with one LivingCell, and after a transition, should have no LivingCells" in {
    (new Environment(List(LivingCell))).transition should equal(new Environment(Nil))
  }

  it should "start with no LivingCells, and after a transition, should have no LivingCells" in {
    (new Environment(Nil)).transition should equal(new Environment(Nil))
  }

  it should "start with three LivingCells, each adjacent to the others, and after a transition, should have four LivingCells, each adjacent to the others" in {

  }
}
