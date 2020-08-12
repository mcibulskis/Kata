defmodule Grid do
  @moduledoc """
  Represents the grid space within which cells reside.
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
    |> Enum.reduce(%{}, &(Map.put(&2, &1, %Cell{})))
  end

  defp build_grid_coordinates(rows, cols) do
    for row <- 1..rows, col <- 1..cols do
      {row, col}
    end
  end
end
