package com.noackexpected.triangleClassification

import com.noackexpected.triangleClassification.TriangleType._

class TriangleClassifier {
  def classify(side1: Int, side2: Int, side3: Int) = {
    def isEquilateral(s1: Int, s2: Int, s3: Int): Boolean = (s2 == s3 && s1 == s2)
    def isIsosceles(s1: Int, s2: Int, s3: Int): Boolean = (s2 == s3 || s1 == s2 || s1 == s3)
    def isValidTriangle(s1: Int, s2: Int, s3: Int): Boolean = (s1 + s2 > s3) && (s1 + s3 > s2) && (s2 + s3 > s1)

    (side1, side2, side3) match {
      case (s1, s2, s3) if !isValidTriangle(s1, s2, s3) => throw new IllegalArgumentException("Not a valid triangle")
      case (s1, s2, s3) if isEquilateral(s1, s2, s3) => Equilateral
      case (s1, s2, s3) if isIsosceles(s1, s2, s3) => Isosceles
      case _ => Scalene
    }
  }
}
