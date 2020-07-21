defmodule TriangleClassifier do
  @moduledoc """
  Documentation for `TriangleClassifier`.
  """

  @doc """
  This method accepts three numeric values each representing the length of one side of a triangle. It
  returns a symbol, :scalene, :isosceles, or :equilateral, where the return value corresponds to the
  actual type of the triangle.

  ## Examples

      iex> TriangleClassifier.classify(4, 2, 3)
      {:ok, :scalene}

      iex> TriangleClassifier.classify(2, 2, 3)
      {:ok, :isosceles}

      iex> TriangleClassifier.classify(1, 1, 1)
      {:ok, :equilateral}

      iex> TriangleClassifier.classify(1, 1, 4)
      {:error, :illegal_triangle}
  """
  def classify(side1, side2, side3)

  def classify(side1, side2, side3) when side1 + side2 <= side3 do
    {:error, :illegal_triangle}
  end

  def classify(side1, side2, side3) when side1 == side2 and side1 == side3 do
    {:ok, :equilateral}
  end

  def classify(side1, side2, side3) when side1 == side2 or side1 == side3 or side2 == side3 do
    {:ok, :isosceles}
  end

  def classify(_side1, _side2, _side3) do
    {:ok, :scalene}
  end
end
