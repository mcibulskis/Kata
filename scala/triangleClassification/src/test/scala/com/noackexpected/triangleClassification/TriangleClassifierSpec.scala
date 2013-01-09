package com.noackexpected.triangleClassification

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import com.noackexpected.triangleClassification.TriangleType._

class TriangleClassifierSpec extends FlatSpec with ShouldMatchers {
  private val impl = new TriangleClassifier

  it should "return equilateral when all sides equal" in {
    impl.classify(3, 3, 3) should be(Equilateral)
  }

  it should "return isosceles when sides 1 and 2 are equal and side 3 is different" in {
    impl.classify(3, 3, 2) should be(Isosceles)
  }

  it should "return isosceles when sides 2 and 3 are equal and side 1 is different" in {
    impl.classify(2, 3, 3) should be(Isosceles)
  }

  it should "return isosceles when sides 1 and 3 are equal and side 2 is different" in {
    impl.classify(3, 2, 3) should be(Isosceles)
  }

  it should "return scalene when no sides are equal" in {
    impl.classify(2, 5, 4) should be(Scalene)
  }

  it should "throw an IllegalArgumentException if it is not a legal triangle (side 1 + side 2 == side 3)" in {
    intercept[IllegalArgumentException] {
      impl.classify(1, 2, 3)
    }
  }

  it should "throw an IllegalArgumentException if it is not a legal triangle (side 1 + side 3 == side 2)" in {
    intercept[IllegalArgumentException] {
      impl.classify(1, 3, 2)
    }
  }

  it should "throw an IllegalArgumentException if it is not a legal triangle (side 2 + side 3 == side 1)" in {
    intercept[IllegalArgumentException] {
      impl.classify(3, 1, 2)
    }
  }
}
