package com.noackexpected.kata.scala.bankocr.v1

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class DigitRepresentationSpec extends FlatSpec with ShouldMatchers {
  private val impl = new DigitRepresentation

  //=======================
  // generation of position descriptors for the character grid
  //
  behavior of "Generation of position descriptors for the digit character grid"

  it should "generate a sequence containing all the coordinates on the grid for the characters for the digit" in {
    val builtGrid = impl.generatePositionGridAsSequence()

    builtGrid should equal(Seq((0,0), (1,0), (2,0), (0,1), (1,1), (2,1), (0,2), (1,2), (2,2), (0,3), (1,3), (2,3)))
  }

  it should "generate a grid containing all the coordinates on the grid for the characters for the digit" in {
    val builtGrid = impl.generatePositionGrid()

    builtGrid should equal(Seq(
      Seq((0,0), (1,0), (2,0)),
      Seq((0,1), (1,1), (2,1)),
      Seq((0,2), (1,2), (2,2)),
      Seq((0,3), (1,3), (2,3))
    ))
  }

  //===========================================================================
  // calculating the character at a position for a digit
  //
  behavior of "Calculating the character at a position for a digit"

  it should "calculate the correct character at all positions for 0" in {
    val builtCharacters = buildCharactersForDigit(0)

    builtCharacters should equal(digit0)
  }

  it should "calculate the correct character at all positions for 1" in {
    val builtCharacters = buildCharactersForDigit(1)

    builtCharacters should equal(digit1)
  }

  it should "calculate the correct character at all positions for 2" in {
    val builtCharacters = buildCharactersForDigit(2)

    builtCharacters should equal(digit2)
  }

  it should "calculate the correct character at all positions for 3" in {
    val builtCharacters = buildCharactersForDigit(3)

    builtCharacters should equal(digit3)
  }

  it should "calculate the correct character at all positions for 4" in {
    val builtCharacters = buildCharactersForDigit(4)

    builtCharacters should equal(digit4)
  }

  it should "calculate the correct character at all positions for 5" in {
    val builtCharacters = buildCharactersForDigit(5)

    builtCharacters should equal(digit5)
  }

  it should "calculate the correct character at all positions for 6" in {
    val builtCharacters = buildCharactersForDigit(6)

    builtCharacters should equal(digit6)
  }

  it should "calculate the correct character at all positions for 7" in {
    val builtCharacters = buildCharactersForDigit(7)

    builtCharacters should equal(digit7)
  }

  it should "calculate the correct character at all positions for 8" in {
    val builtCharacters = buildCharactersForDigit(8)

    builtCharacters should equal(digit8)
  }

  it should "calculate the correct character at all positions for 9" in {
    val builtCharacters = buildCharactersForDigit(9)

    builtCharacters should equal(digit9)
  }

  private def buildCharactersForDigit(digit: Int): Seq[Seq[Char]] = {
    (0 until 4).map {
      rowIndex =>
        (0 until 3).map {
          columnIndex =>
            impl.calculateCharacterAtPositionForDigit(digit, columnIndex, rowIndex)
        }
    }
  }

  val digit0 = Seq(
    Seq(' ', '_', ' '),
    Seq('|', ' ', '|'),
    Seq('|', '_', '|'),
    Seq(' ', ' ', ' ')
  )

  val digit1 = Seq(
    Seq(' ', ' ', ' '),
    Seq(' ', ' ', '|'),
    Seq(' ', ' ', '|'),
    Seq(' ', ' ', ' ')
  )

  val digit2 = Seq(
    Seq(' ', '_', ' '),
    Seq(' ', '_', '|'),
    Seq('|', '_', ' '),
    Seq(' ', ' ', ' ')
  )

  val digit3 = Seq(
    Seq(' ', '_', ' '),
    Seq(' ', '_', '|'),
    Seq(' ', '_', '|'),
    Seq(' ', ' ', ' ')
  )

  val digit4 = Seq(
    Seq(' ', ' ', ' '),
    Seq('|', '_', '|'),
    Seq(' ', ' ', '|'),
    Seq(' ', ' ', ' ')
  )

  val digit5 = Seq(
    Seq(' ', '_', ' '),
    Seq('|', '_', ' '),
    Seq(' ', '_', '|'),
    Seq(' ', ' ', ' ')
  )

  val digit6 = Seq(
    Seq(' ', '_', ' '),
    Seq('|', '_', ' '),
    Seq('|', '_', '|'),
    Seq(' ', ' ', ' ')
  )

  val digit7 = Seq(
    Seq(' ', '_', ' '),
    Seq(' ', ' ', '|'),
    Seq(' ', ' ', '|'),
    Seq(' ', ' ', ' ')
  )

  val digit8 = Seq(
    Seq(' ', '_', ' '),
    Seq('|', '_', '|'),
    Seq('|', '_', '|'),
    Seq(' ', ' ', ' ')
  )

  val digit9 = Seq(
    Seq(' ', '_', ' '),
    Seq('|', '_', '|'),
    Seq(' ', '_', '|'),
    Seq(' ', ' ', ' ')
  )


  //===========================================================================
  // calculating the possible digits based on characters at positions
  //
  behavior of "Calculating the possible digits based on characters at positions"

  it should "calculate the correct possible digits at 0,0 when the character is ' '" in {
    val digits = impl.calculatePossibleDigits(' ', 0, 0)

    digits should equal(Seq(Some(0), Some(1), Some(2), Some(3), Some(4), Some(5), Some(6), Some(7), Some(8), Some(9)))
  }

  it should "calculate the correct possible digits at 1,0 when the character is ' '" in {
    val digits = impl.calculatePossibleDigits(' ', 1, 0)

    digits should equal(Seq(None, Some(1), Some(4)))
  }

  it should "calculate the correct possible digits at 2,0 when the character is ' '" in {
    val digits = impl.calculatePossibleDigits(' ', 2, 0)

    digits should equal(Seq(Some(0), Some(1), Some(2), Some(3), Some(4), Some(5), Some(6), Some(7), Some(8), Some(9)))
  }

  it should "calculate the correct possible digits at 0,1 when the character is ' '" in {
    val digits = impl.calculatePossibleDigits(' ', 0, 1)

    digits should equal(Seq(None, Some(1), Some(2), Some(3), Some(7)))
  }

  it should "calculate the correct possible digits at 1,1 when the character is ' '" in {
    val digits = impl.calculatePossibleDigits(' ', 1, 1)

    digits should equal(Seq(None, Some(0), Some(1), Some(7)))
  }

  it should "calculate the correct possible digits at 2,1 when the character is ' '" in {
    val digits = impl.calculatePossibleDigits(' ', 2, 1)

    digits should equal(Seq(None, Some(5), Some(6)))
  }

  it should "calculate the correct possible digits at 0,2 when the character is ' '" in {
    val digits = impl.calculatePossibleDigits(' ', 0, 2)

    digits should equal(Seq(None, Some(1), Some(3), Some(4), Some(5), Some(7), Some(9)))
  }

  it should "calculate the correct possible digits at 1,2 when the character is ' '" in {
    val digits = impl.calculatePossibleDigits(' ', 1, 2)

    digits should equal(Seq(None, Some(1), Some(4), Some(7)))
  }

  it should "calculate the correct possible digits at 2,2 when the character is ' '" in {
    val digits = impl.calculatePossibleDigits(' ', 2, 2)

    digits should equal(Seq(None, Some(2)))
  }

  it should "calculate the correct possible digits at 0,3 when the character is ' '" in {
    val digits = impl.calculatePossibleDigits(' ', 0, 3)

    digits should equal(Seq(Some(0), Some(1), Some(2), Some(3), Some(4), Some(5), Some(6), Some(7), Some(8), Some(9)))
  }

  it should "calculate the correct possible digits at 1,3 when the character is ' '" in {
    val digits = impl.calculatePossibleDigits(' ', 1, 3)

    digits should equal(Seq(Some(0), Some(1), Some(2), Some(3), Some(4), Some(5), Some(6), Some(7), Some(8), Some(9)))
  }

  it should "calculate the correct possible digits at 2,3 when the character is ' '" in {
    val digits = impl.calculatePossibleDigits(' ', 2, 3)

    digits should equal(Seq(Some(0), Some(1), Some(2), Some(3), Some(4), Some(5), Some(6), Some(7), Some(8), Some(9)))
  }

  it should "calculate the correct possible digits at 0,0 when the character is '_'" in {
    val digits = impl.calculatePossibleDigits('_', 0, 0)

    digits should equal(Seq(None))
  }

  it should "calculate the correct possible digits at 1,0 when the character is '_'" in {
    val digits = impl.calculatePossibleDigits('_', 1, 0)

    digits should equal(Seq(None, Some(0), Some(2), Some(3), Some(5), Some(6), Some(7), Some(8), Some(9)))
  }

  it should "calculate the correct possible digits at 2,0 when the character is '_'" in {
    val digits = impl.calculatePossibleDigits('_', 2, 0)

    digits should equal(Seq(None))
  }

  it should "calculate the correct possible digits at 0,1 when the character is '_'" in {
    val digits = impl.calculatePossibleDigits('_', 0, 1)

    digits should equal(Seq(None))
  }

  it should "calculate the correct possible digits at 1,1 when the character is '_'" in {
    val digits = impl.calculatePossibleDigits('_', 1, 1)

    digits should equal(Seq(None, Some(2), Some(3), Some(4), Some(5), Some(6), Some(8), Some(9)))
  }

  it should "calculate the correct possible digits at 2,1 when the character is '_'" in {
    val digits = impl.calculatePossibleDigits('_', 2, 1)

    digits should equal(Seq(None))
  }

  it should "calculate the correct possible digits at 0,2 when the character is '_'" in {
    val digits = impl.calculatePossibleDigits('_', 0, 2)

    digits should equal(Seq(None))
  }

  it should "calculate the correct possible digits at 1,2 when the character is '_'" in {
    val digits = impl.calculatePossibleDigits('_', 1, 2)

    digits should equal(Seq(None, Some(0), Some(2), Some(3), Some(5), Some(6), Some(8), Some(9)))
  }

  it should "calculate the correct possible digits at 2,2 when the character is '_'" in {
    val digits = impl.calculatePossibleDigits('_', 2, 2)

    digits should equal(Seq(None))
  }

  it should "calculate the correct possible digits at 0,3 when the character is '_'" in {
    val digits = impl.calculatePossibleDigits('_', 0, 3)

    digits should equal(Seq(None))
  }

  it should "calculate the correct possible digits at 1,3 when the character is '_'" in {
    val digits = impl.calculatePossibleDigits('_', 1, 3)

    digits should equal(Seq(None))
  }

  it should "calculate the correct possible digits at 2,3 when the character is '_'" in {
    val digits = impl.calculatePossibleDigits('_', 2, 3)

    digits should equal(Seq(None))
  }

  it should "calculate the correct possible digits at 0,0 when the character is '|'" in {
    val digits = impl.calculatePossibleDigits('|', 0, 0)

    digits should equal(Seq(None))
  }

  it should "calculate the correct possible digits at 1,0 when the character is '|'" in {
    val digits = impl.calculatePossibleDigits('|', 1, 0)

    digits should equal(Seq(None))
  }

  it should "calculate the correct possible digits at 2,0 when the character is '|'" in {
    val digits = impl.calculatePossibleDigits('|', 2, 0)

    digits should equal(Seq(None))
  }

  it should "calculate the correct possible digits at 0,1 when the character is '|'" in {
    val digits = impl.calculatePossibleDigits('|', 0, 1)

    digits should equal(Seq(None, Some(0), Some(4), Some(5), Some(6), Some(8), Some(9)))
  }

  it should "calculate the correct possible digits at 1,1 when the character is '|'" in {
    val digits = impl.calculatePossibleDigits('|', 1, 1)

    digits should equal(Seq(None))
  }

  it should "calculate the correct possible digits at 2,1 when the character is '|'" in {
    val digits = impl.calculatePossibleDigits('|', 2, 1)

    digits should equal(Seq(None, Some(0), Some(1), Some(2), Some(3), Some(4), Some(7), Some(8), Some(9)))
  }

  it should "calculate the correct possible digits at 0,2 when the character is '|'" in {
    val digits = impl.calculatePossibleDigits('|', 0, 2)

    digits should equal(Seq(None, Some(0), Some(2), Some(6), Some(8)))
  }

  it should "calculate the correct possible digits at 1,2 when the character is '|'" in {
    val digits = impl.calculatePossibleDigits('|', 1, 2)

    digits should equal(Seq(None))
  }

  it should "calculate the correct possible digits at 2,2 when the character is '|'" in {
    val digits = impl.calculatePossibleDigits('|', 2, 2)

    digits should equal(Seq(None, Some(0), Some(1), Some(3), Some(4), Some(5), Some(6), Some(7), Some(8), Some(9)))
  }

  it should "calculate the correct possible digits at 0,3 when the character is '|'" in {
    val digits = impl.calculatePossibleDigits('|', 0, 3)

    digits should equal(Seq(None))
  }

  it should "calculate the correct possible digits at 1,3 when the character is '|'" in {
    val digits = impl.calculatePossibleDigits('|', 1, 3)

    digits should equal(Seq(None))
  }

  it should "calculate the correct possible digits at 2,3 when the character is '|'" in {
    val digits = impl.calculatePossibleDigits('|', 2, 3)

    digits should equal(Seq(None))
  }
}
