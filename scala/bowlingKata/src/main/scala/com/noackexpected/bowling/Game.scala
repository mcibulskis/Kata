package com.noackexpected.bowling

class Game {
  private var frames: Seq[Frame] = Seq()

  def roll(numberOfPins: Int): Unit = {
    def addToFrameList(frames: Seq[Frame], roll: Int): Seq[Frame] = {
      frames match {
        case Nil => List(new Frame(1, Some(numberOfPins)))
        case head :: Nil if head.isFull() => List(head, new Frame(head.frameNumber + 1, Some(numberOfPins)))
        case head :: Nil if !head.isFull() => List(fillFrame(head, roll))
        case head :: tail => List(head) ++ addToFrameList(tail, roll)
      }
    }

    def fillFrame(frame: Frame, roll: Int): Frame = {
      frame match {
        case Frame(fn, r1, None, None) => new Frame(fn, r1, Some(roll))
        case Frame(fn, r1, r2, None) => new Frame(fn, r1, r2, Some(roll))
      }
    }

    frames = addToFrameList(frames, numberOfPins)
  }

  def lastFrame(): Int = {
    frames match {
      case Nil => 0
      case _ => frames.last.frameNumber
    }
  }

  def score(): Int = {
    def sumScores(frames: Seq[Frame]): Int = {
      frames match {
        case Nil => 0
        case head :: Nil => head.score(Nil)
        case head :: tail => head.score(tail) + sumScores(tail)
      }
    }
    sumScores(frames)
  }
}
