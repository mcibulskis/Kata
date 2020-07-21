defmodule TriangleClassifier do
  @moduledoc """
  Documentation for `TriangleClassifier`.
  """

  @doc """
  This method accepts three numeric values each representing the length of one side of a triangle. It
  returns a symbol, :scalene, :isosceles, or :equilateral, where the return value corresponds to the
  actual type of the triangle.

  ## Examples

      iex> TriangleClassifier.classify(1, 2, 3)
      :scalene

      iex> TriangleClassifier.classify(1, 1, 3)
      :isosceles

  """
  def classify(side1, side2, side3)

  def classify(side1, side2, side3) when side1 == side2 do
    :isosceles
  end

  def classify(_side1, _side2, _side3) do
    :scalene
  end
end
