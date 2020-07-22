defmodule Fibonacci do
  @moduledoc """
  Documentation for `Fibonacci`.
  """

  @doc """
  Returns a stream representing the Fibonacci sequence

  ## Examples

      iex> Fibonacci.sequence() |> Enum.take(10)
      [0, 1, 1, 2, 3, 5, 8, 13, 21, 34]

  """
  def sequence do
    Stream.resource(
      fn -> [] end,
      &next_fib/1,
      fn fibs -> fibs end
    )
  end

  defp next_fib([]) do
    {[0], [0]}
  end

  defp next_fib([0]) do
    {[1], [0, 1]}
  end

  defp next_fib([prev1, prev2]) do
    {[prev1 + prev2], [prev2, prev1 + prev2]}
  end
end
