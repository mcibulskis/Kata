defmodule FibonacciTest do
  use ExUnit.Case
  doctest Fibonacci

  test "returns 0 as the first element" do
    assert Fibonacci.sequence() |> Enum.take(1) |> hd == 0
  end

  test "returns 1 as the second element" do
    assert Fibonacci.sequence() |> Enum.take(2) |> tl |> hd == 1
  end
end
