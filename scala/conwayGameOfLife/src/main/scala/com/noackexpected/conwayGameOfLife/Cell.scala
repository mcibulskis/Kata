package com.noackexpected.conwayGameOfLife

trait Cell {
  def transitionWithAdjacentLivingCells(livingNeighborCount: Int): Cell
}

object DeadCell extends Cell {
  def transitionWithAdjacentLivingCells(livingNeighborCount: Int): Cell = {
    livingNeighborCount match {
      case 3 => LivingCell
      case _ => DeadCell
    }
  }
}

object LivingCell extends Cell {
  def transitionWithAdjacentLivingCells(livingNeighborCount: Int): Cell = {
    livingNeighborCount match {
      case 2 => LivingCell
      case 3 => LivingCell
      case _ => DeadCell
    }
  }
}
