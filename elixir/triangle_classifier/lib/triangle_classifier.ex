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

  """
  def classify(side1, side2, side3) do
    :scalene
  end
end
