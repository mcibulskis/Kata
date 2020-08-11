defmodule Cell do
  @moduledoc """
  Represents a cell within the "world".
  """

  defstruct [state: :dead, living_neighbors: 0]

  @doc """
  Determines whether this cell will be alive or dead for the next generation.

  1. Any live cell with two or three live neighbors survives in the next generation
  2. Any dead cell with three live neighbors becomes a live cell in the next generation
  3. Any other live cell dies in the next generation
  4. Any other dead cell remains dead in the next generation

  ## Examples

      iex> Cell.will_be_alive?()
      false

  """
  def will_be_alive? do
    false
  end
end
