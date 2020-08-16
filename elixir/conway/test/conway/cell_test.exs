defmodule CellTest do
  use ExUnit.Case
  doctest Cell

  test "living cell will be dead next generation when number of living neighbors is less than 2" do
    assert %Cell{state: :dead} = Cell.next(%Cell{living_neighbors: 0, state: :alive})
    assert %Cell{state: :dead} = Cell.next(%Cell{living_neighbors: 1, state: :alive})
  end

  test "living cell will be dead next generation when number of living neighbors is greater than 3" do
    assert %Cell{state: :dead} = Cell.next(%Cell{living_neighbors: 4, state: :alive})
    assert %Cell{state: :dead} = Cell.next(%Cell{living_neighbors: 5, state: :alive})
    assert %Cell{state: :dead} = Cell.next(%Cell{living_neighbors: 6, state: :alive})
    assert %Cell{state: :dead} = Cell.next(%Cell{living_neighbors: 7, state: :alive})
    assert %Cell{state: :dead} = Cell.next(%Cell{living_neighbors: 8, state: :alive})
  end

  test "living cell will be alive next generation when it was alive and has two or three living neighbors" do
    assert %Cell{state: :alive} = Cell.next(%Cell{living_neighbors: 2, state: :alive})
    assert %Cell{state: :alive} = Cell.next(%Cell{living_neighbors: 3, state: :alive})
  end

  test "dead cell will be alive next generation when it was dead and has three living neighbors" do
    assert %Cell{state: :alive} = Cell.next(%Cell{living_neighbors: 3, state: :dead})
  end

  test "dead cell will be dead next generation when it was dead and has any other number of living neighbors" do
    assert %Cell{state: :dead} = Cell.next(%Cell{living_neighbors: 0, state: :dead})
    assert %Cell{state: :dead} = Cell.next(%Cell{living_neighbors: 1, state: :dead})
    assert %Cell{state: :dead} = Cell.next(%Cell{living_neighbors: 2, state: :dead})
    assert %Cell{state: :dead} = Cell.next(%Cell{living_neighbors: 4, state: :dead})
    assert %Cell{state: :dead} = Cell.next(%Cell{living_neighbors: 5, state: :dead})
    assert %Cell{state: :dead} = Cell.next(%Cell{living_neighbors: 6, state: :dead})
    assert %Cell{state: :dead} = Cell.next(%Cell{living_neighbors: 7, state: :dead})
    assert %Cell{state: :dead} = Cell.next(%Cell{living_neighbors: 8, state: :dead})
  end

  test "next generation cells all start with a count of no living neighbors" do
    assert %Cell{living_neighbors: 0} = Cell.next(%Cell{living_neighbors: 0, state: :alive})
    assert %Cell{living_neighbors: 0} = Cell.next(%Cell{living_neighbors: 2, state: :alive})
    assert %Cell{living_neighbors: 0} = Cell.next(%Cell{living_neighbors: 4, state: :alive})
    assert %Cell{living_neighbors: 0} = Cell.next(%Cell{living_neighbors: 3, state: :dead})
    assert %Cell{living_neighbors: 0} = Cell.next(%Cell{living_neighbors: 1, state: :dead})
    assert %Cell{living_neighbors: 0} = Cell.next(%Cell{living_neighbors: 7, state: :dead})
  end

  test "visually represent the cells in an abbreviated format based solely on current state" do
    assert "." == Cell.to_string(%Cell{living_neighbors: 0, state: :dead})
    assert "." == Cell.to_string(%Cell{living_neighbors: 3, state: :dead})
    assert "o" == Cell.to_string(%Cell{living_neighbors: 0, state: :alive})
    assert "o" == Cell.to_string(%Cell{living_neighbors: 2, state: :alive})
  end
end
