defmodule CellTest do
  use ExUnit.Case
  doctest Cell

  test "cell will be dead next generation when number of living neighbors is less than 2" do
    assert Cell.will_be_alive?(%Cell{living_neighbors: 0}) == false
    assert Cell.will_be_alive?(%Cell{living_neighbors: 1}) == false
  end

  test "cell will be dead next generation when number of living neighbors is greater than 3" do
    assert Cell.will_be_alive?(%Cell{living_neighbors: 4}) == false
    assert Cell.will_be_alive?(%Cell{living_neighbors: 5}) == false
    assert Cell.will_be_alive?(%Cell{living_neighbors: 6}) == false
    assert Cell.will_be_alive?(%Cell{living_neighbors: 7}) == false
    assert Cell.will_be_alive?(%Cell{living_neighbors: 8}) == false
  end

  test "cell will be alive next generation when it was alive and has two or three living neighbors" do
    assert Cell.will_be_alive?(%Cell{living_neighbors: 2, state: :alive}) == true
    assert Cell.will_be_alive?(%Cell{living_neighbors: 3, state: :alive}) == true
  end

#  test "cell will be alive next generation when it was dead and has three living neighbors" do
#    assert Cell.will_be_alive?(%Cell{living_neighbors: 3, state: :dead}) == true
#  end
#
#  test "cell will be dead next generation when it was dead and has two living neighbors" do
#    assert Cell.will_be_alive?(%Cell{living_neighbors: 2, state: :dead}) == false
#  end
end

