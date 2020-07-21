defmodule TriangleClassifierTest do
  use ExUnit.Case
  doctest TriangleClassifier

  test "correctly classifies a scalene triangle when all sides are not equal" do
    assert TriangleClassifier.classify(1, 2, 3) == :scalene
  end
end
