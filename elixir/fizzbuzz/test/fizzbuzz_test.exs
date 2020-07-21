defmodule FizzbuzzTest do
  use ExUnit.Case
  doctest Fizzbuzz

  test "non-special numbers should translate to themselves" do
    assert Fizzbuzz.translate(1) == 1
    assert Fizzbuzz.translate(7) == 7
  end
end

