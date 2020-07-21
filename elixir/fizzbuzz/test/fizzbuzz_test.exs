defmodule FizzbuzzTest do
  use ExUnit.Case
  doctest Fizzbuzz

  test "1 should translate to 1" do
    assert Fizzbuzz.translate(1) == 1
  end
end

