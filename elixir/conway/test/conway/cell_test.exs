defmodule CellTest do
  use ExUnit.Case
  doctest Cell

  test "canary test" do
    assert Cell.will_be_alive?() == false
  end
end

