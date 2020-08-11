defmodule CellTest do
  use ExUnit.Case
  doctest Cell

  test "living cell will be dead next generation when number of living neighbors is less than 2" do
    assert Cell.will_be_alive?(%Cell{living_neighbors: 0, state: :alive}) == false
    assert Cell.will_be_alive?(%Cell{living_neighbors: 1, state: :alive}) == false
  end

  test "living cell will be dead next generation when number of living neighbors is greater than 3" do
    assert Cell.will_be_alive?(%Cell{living_neighbors: 4, state: :alive}) == false
    assert Cell.will_be_alive?(%Cell{living_neighbors: 5, state: :alive}) == false
    assert Cell.will_be_alive?(%Cell{living_neighbors: 6, state: :alive}) == false
    assert Cell.will_be_alive?(%Cell{living_neighbors: 7, state: :alive}) == false
    assert Cell.will_be_alive?(%Cell{living_neighbors: 8, state: :alive}) == false
  end

  test "living cell will be alive next generation when it was alive and has two or three living neighbors" do
    assert Cell.will_be_alive?(%Cell{living_neighbors: 2, state: :alive}) == true
    assert Cell.will_be_alive?(%Cell{living_neighbors: 3, state: :alive}) == true
  end

  test "dead cell will be alive next generation when it was dead and has three living neighbors" do
    assert Cell.will_be_alive?(%Cell{living_neighbors: 3, state: :dead}) == true
  end

  test "dead cell will be dead next generation when it was dead and has any other number of living neighbors" do
    assert Cell.will_be_alive?(%Cell{living_neighbors: 0, state: :dead}) == false
    assert Cell.will_be_alive?(%Cell{living_neighbors: 1, state: :dead}) == false
    assert Cell.will_be_alive?(%Cell{living_neighbors: 2, state: :dead}) == false
    assert Cell.will_be_alive?(%Cell{living_neighbors: 4, state: :dead}) == false
    assert Cell.will_be_alive?(%Cell{living_neighbors: 5, state: :dead}) == false
    assert Cell.will_be_alive?(%Cell{living_neighbors: 6, state: :dead}) == false
    assert Cell.will_be_alive?(%Cell{living_neighbors: 7, state: :dead}) == false
    assert Cell.will_be_alive?(%Cell{living_neighbors: 8, state: :dead}) == false
  end
end
