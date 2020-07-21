defmodule FibonacciTest do
  use ExUnit.Case
  doctest Fibonacci

  test "returns 0 as the first element" do
    assert Fibonacci.sequence() |> Enum.take(1) |> hd == 0
  end
end
