defmodule Fibonacci do
  @moduledoc """
  Documentation for `Fibonacci`.
  """

  @doc """
  Returns a stream representing the Fibonacci sequence

  ## Examples

      iex> Fibonacci.sequence() |> Enum.take(1)
      [0]

  """
  def sequence do
    Stream.cycle([0])
  end
end
