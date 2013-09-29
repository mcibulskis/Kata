package com.noackexpected.conwayGameOfLife

case class Environment(val livingCellList: List[Cell]) {
  def transition: Environment = { new Environment(Nil) }
}
