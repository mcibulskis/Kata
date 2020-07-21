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
  def classify(side1, side2, side3) do
    cond do
      is_illegal(side1, side2, side3) -> {:error, :illegal_triangle}
      is_equilateral(side1, side2, side3) -> {:ok, :equilateral}
      is_isosceles(side1, side2, side3) -> {:ok, :isosceles}
      true -> {:ok, :scalene}
    end
  end

  defp is_illegal(s1, s2, s3) do
    s1 + s2 <= s3 or s2 + s3 <= s1 or s1 + s3 <= s2
  end

  defp is_equilateral(s1, s2, s3) do
    s1 == s2 and s1 == s3
  end

  defp is_isosceles(s1, s2, s3) do
    s1 == s2 or s1 == s3 or s2 == s3
  end
end
