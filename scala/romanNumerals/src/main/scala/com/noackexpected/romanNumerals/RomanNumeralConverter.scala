package com.noackexpected.romanNumerals

class RomanNumeralConverter {
  def convert(arabicNumber: Int): String = {
    arabicNumber match {
      case x if (x - 1000 >= 0) => "M" + convert(x - 1000)
      case x if (x - 900 >= 0) => "CM" + convert(x - 900)
      case x if (x - 500 >= 0) => "D" + convert(x - 500)
      case x if (x - 400 >= 0) => "CD" + convert(x - 400)
      case x if (x - 100 >= 0) => "C" + convert(x - 100)
      case x if (x - 90 >= 0) => "XC" + convert(x - 90)
      case x if (x - 50 >= 0) => "L" + convert(x - 50)
      case x if (x - 40 >= 0) => "XL" + convert(x - 40)
      case x if (x - 10 >= 0) => "X" + convert(x - 10)
      case x if (x - 9 >= 0) => "IX" + convert(x - 9)
      case x if (x - 5 >= 0) => "V" + convert(x - 5)
      case x if (x - 4 >= 0) => "IV" + convert(x - 4)
      case x if (x - 1 >= 0) => "I" + convert(x - 1)
      case _ => ""
    }
  }
}
