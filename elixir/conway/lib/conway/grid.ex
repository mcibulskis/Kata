defmodule Grid do
  @moduledoc """
  Represents the grid space within which cells reside.

  Edges of the grid are "hard" and do not wrap, which means
  that coordinates on the edge of the grid have fewer neighbors
  than coordinates in the interior of the grid.
  """

  @enforce_keys [:rows, :cols]
  defstruct [:rows, :cols, cells: %{}]

  @doc """
  Constructs a new grid of the specified dimensions

  ## Examples

      iex> Grid.new(2, 3)
      %Grid{
        cells: %{
          {1, 1} => %Cell{living_neighbors: 0, state: :dead},
          {1, 2} => %Cell{living_neighbors: 0, state: :dead},
          {1, 3} => %Cell{living_neighbors: 0, state: :dead},
          {2, 1} => %Cell{living_neighbors: 0, state: :dead},
          {2, 2} => %Cell{living_neighbors: 0, state: :dead},
          {2, 3} => %Cell{living_neighbors: 0, state: :dead}
        },
        cols: 3,
        rows: 2
      }

  """
  def new(rows, cols) do
    %Grid{rows: rows, cols: cols, cells: build_cell_map(rows, cols)}
  end

  defp build_cell_map(rows, cols) do
    build_grid_coordinates(rows, cols)
    |> Enum.reduce(%{}, &Map.put(&2, &1, %Cell{}))
  end

  defp build_grid_coordinates(rows, cols) do
    for row <- 1..rows, col <- 1..cols do
      {row, col}
    end
  end

  @doc """
  Returns a list of tuples denoting the coordinates of the neighbors of the
  specified coordinate.

  ## Examples

      iex> Grid.neighbors_of({2, 2}, Grid.new(3, 3))
      [{1, 1}, {1, 2}, {1, 3},
       {2, 1},         {2, 3},
       {3, 1}, {3, 2}, {3, 3}]

      iex> Grid.neighbors_of({1, 1}, Grid.new(3, 3))
      [        {1, 2},
       {2, 1}, {2, 2}]

  """
  def neighbors_of({row, col}, %Grid{rows: rows, cols: cols}) do
    for n_row <- bounded(row - 1, rows)..bounded(row + 1, rows),
        n_col <- bounded(col - 1, cols)..bounded(col + 1, cols),
        {n_row, n_col} != {row, col} do
      {n_row, n_col}
    end
  end

  defp bounded(target, _max) when target < 1, do: 1
  defp bounded(target, max) when target > max, do: max
  defp bounded(target, _max), do: target
end
