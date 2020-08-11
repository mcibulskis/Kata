defmodule CellTest do
  use ExUnit.Case
  doctest Cell

  test "living cell will be dead next generation when number of living neighbors is less than 2" do
    assert Cell.next(%Cell{living_neighbors: 0, state: :alive}) == %Cell{state: :dead}
    assert Cell.next(%Cell{living_neighbors: 1, state: :alive}) == %Cell{state: :dead}
  end

  test "living cell will be dead next generation when number of living neighbors is greater than 3" do
    assert Cell.next(%Cell{living_neighbors: 4, state: :alive}) == %Cell{state: :dead}
    assert Cell.next(%Cell{living_neighbors: 5, state: :alive}) == %Cell{state: :dead}
    assert Cell.next(%Cell{living_neighbors: 6, state: :alive}) == %Cell{state: :dead}
    assert Cell.next(%Cell{living_neighbors: 7, state: :alive}) == %Cell{state: :dead}
    assert Cell.next(%Cell{living_neighbors: 8, state: :alive}) == %Cell{state: :dead}
  end

  test "living cell will be alive next generation when it was alive and has two or three living neighbors" do
    assert Cell.next(%Cell{living_neighbors: 2, state: :alive}) == %Cell{state: :alive}
    assert Cell.next(%Cell{living_neighbors: 3, state: :alive}) == %Cell{state: :alive}
  end

  test "dead cell will be alive next generation when it was dead and has three living neighbors" do
    assert Cell.next(%Cell{living_neighbors: 3, state: :dead}) == %Cell{state: :alive}
  end

  test "dead cell will be dead next generation when it was dead and has any other number of living neighbors" do
    assert Cell.next(%Cell{living_neighbors: 0, state: :dead}) == %Cell{state: :dead}
    assert Cell.next(%Cell{living_neighbors: 1, state: :dead}) == %Cell{state: :dead}
    assert Cell.next(%Cell{living_neighbors: 2, state: :dead}) == %Cell{state: :dead}
    assert Cell.next(%Cell{living_neighbors: 4, state: :dead}) == %Cell{state: :dead}
    assert Cell.next(%Cell{living_neighbors: 5, state: :dead}) == %Cell{state: :dead}
    assert Cell.next(%Cell{living_neighbors: 6, state: :dead}) == %Cell{state: :dead}
    assert Cell.next(%Cell{living_neighbors: 7, state: :dead}) == %Cell{state: :dead}
    assert Cell.next(%Cell{living_neighbors: 8, state: :dead}) == %Cell{state: :dead}
  end

  test "next generation cells all start with a count of no living neighbors" do
    assert Cell.next(%Cell{living_neighbors: 0, state: :alive}) == %Cell{living_neighbors: 0}
    assert Cell.next(%Cell{living_neighbors: 2, state: :alive}) == %Cell{living_neighbors: 0, state: :alive}
    assert Cell.next(%Cell{living_neighbors: 4, state: :alive}) == %Cell{living_neighbors: 0}
    assert Cell.next(%Cell{living_neighbors: 3, state: :dead}) == %Cell{living_neighbors: 0, state: :alive}
    assert Cell.next(%Cell{living_neighbors: 1, state: :dead}) == %Cell{living_neighbors: 0}
    assert Cell.next(%Cell{living_neighbors: 7, state: :dead}) == %Cell{living_neighbors: 0}
  end
end
