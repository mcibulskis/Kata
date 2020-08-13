defmodule GridTest do
  use ExUnit.Case
  doctest Grid

  test "creation of a new grid sets the rows and columns appropriately" do
    assert %Grid{rows: 1, cols: 1} = Grid.new(1, 1)
    assert %Grid{rows: 5, cols: 7} = Grid.new(5, 7)
  end

  test "creation of a new grid creates a map of cells to grid spaces" do
    assert %Grid{
             rows: 3,
             cols: 2,
             cells: %{
               {1, 1} => %Cell{},
               {1, 2} => %Cell{},
               {2, 1} => %Cell{},
               {2, 2} => %Cell{},
               {3, 1} => %Cell{},
               {3, 2} => %Cell{}
             }
           } == Grid.new(3, 2)
  end

  test "interior coordinate has fully surrounding neighbors" do
    assert Grid.neighbors_of({3, 2}) == [{2, 1}, {2, 2}, {2, 3}, {3, 1}, {3, 3}, {4, 1}, {4, 2}, {4, 3}]
  end
end
