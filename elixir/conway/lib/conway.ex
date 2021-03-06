defmodule Conway do
  @moduledoc """
  Conway's Game of Life

  Assumes a rectangular grid.  Each cell has 8 neighbors (4 cardinal directions and 4 diagonals).

  1. Any live cell with two or three live neighbors survives in the next generation
  2. Any dead cell with three live neighbors becomes a live cell in the next generation
  3. Any other live cell dies in the next generation
  4. Any other dead cell remains dead in the next generation

  """

  @doc """
  Hello world.

  ## Examples

      iex> Conway.hello()
      :world

  """
  def hello do
    :world
  end
end
