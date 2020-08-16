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
    assert Grid.neighbors_of({3, 2}, Grid.new(4, 4)) == [
             {2, 1},
             {2, 2},
             {2, 3},
             {3, 1},
             {3, 3},
             {4, 1},
             {4, 2},
             {4, 3}
           ]
  end

  test "edge coordinates do not have neighbors outside of the grid" do
    assert Grid.neighbors_of({2, 1}, Grid.new(3, 3)) == [
             {1, 1},
             {1, 2},
             {2, 2},
             {3, 1},
             {3, 2}
           ]

    assert Grid.neighbors_of({1, 2}, Grid.new(3, 3)) == [
             {1, 1},
             {1, 3},
             {2, 1},
             {2, 2},
             {2, 3}
           ]

    assert Grid.neighbors_of({2, 3}, Grid.new(3, 3)) == [
             {1, 2},
             {1, 3},
             {2, 2},
             {3, 2},
             {3, 3}
           ]

    assert Grid.neighbors_of({3, 2}, Grid.new(3, 3)) == [
             {2, 1},
             {2, 2},
             {2, 3},
             {3, 1},
             {3, 3}
           ]
  end

  test "corner coordinates do not have neighbors outside of the grid" do
    assert Grid.neighbors_of({1, 1}, Grid.new(3, 4)) == [
             {1, 2},
             {2, 1},
             {2, 2}
           ]

    assert Grid.neighbors_of({1, 4}, Grid.new(3, 4)) == [
             {1, 3},
             {2, 3},
             {2, 4}
           ]

    assert Grid.neighbors_of({3, 4}, Grid.new(3, 4)) == [
             {2, 3},
             {2, 4},
             {3, 3}
           ]

    assert Grid.neighbors_of({3, 1}, Grid.new(3, 4)) == [
             {2, 1},
             {2, 2},
             {3, 2}
           ]
  end

  test "grid visuals provide a compressed representation of the state of the grid" do
    assert Grid.to_visual(%Grid{
             rows: 2,
             cols: 3,
             cells: %{
               {1, 1} => %Cell{state: :alive},
               {1, 2} => %Cell{state: :alive},
               {1, 3} => %Cell{state: :dead},
               {2, 1} => %Cell{state: :alive},
               {2, 2} => %Cell{state: :dead},
               {2, 3} => %Cell{state: :alive}
             }
           }) == [["o", "o", "."], ["o", ".", "o"]]
  end
end
