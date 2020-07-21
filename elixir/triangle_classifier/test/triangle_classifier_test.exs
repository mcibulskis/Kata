defmodule TriangleClassifierTest do
  use ExUnit.Case
  doctest TriangleClassifier

  test "correctly classifies a scalene triangle when all sides are not equal" do
    assert TriangleClassifier.classify(5, 4, 3) == {:ok, :scalene}
  end

  test "correctly identifies an isosceles triangle when two of the three sides are equal" do
    assert TriangleClassifier.classify(1, 1, 1.5) == {:ok, :isosceles}
    assert TriangleClassifier.classify(1, 1.5, 1) == {:ok, :isosceles}
    assert TriangleClassifier.classify(1.5, 1, 1) == {:ok, :isosceles}
  end

  test "correctly identifies an equilateral triangle when all three sides are equal" do
    assert TriangleClassifier.classify(1, 1, 1) == {:ok, :equilateral}
  end

  test "return an error status when the triangle described is not a legal triangle" do
    assert TriangleClassifier.classify(1, 1, 4) == {:error, :illegal_triangle}
  end
end
