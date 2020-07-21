defmodule TriangleClassifierTest do
  use ExUnit.Case
  doctest TriangleClassifier

  test "correctly classifies a scalene triangle when all sides are not equal" do
    assert TriangleClassifier.classify(1, 2, 3) == :scalene
  end

  test "correctly identifies an isosceles triangle when two of the three sides are equal" do
    assert TriangleClassifier.classify(1, 1, 2) == :isosceles
    assert TriangleClassifier.classify(1, 2, 1) == :isosceles
    assert TriangleClassifier.classify(2, 1, 1) == :isosceles
  end

  test "correctly identifies an equilateral triangle when all three sides are equal" do
    assert TriangleClassifier.classify(1, 1, 1) == :equilateral
  end
end
