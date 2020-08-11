defmodule Cell do
  @moduledoc """
  Represents a cell within the "world".
  """

  defstruct state: :dead, living_neighbors: 0

  @doc """
  Determines whether this cell will be alive or dead for the next generation.

  1. Any live cell with two or three live neighbors survives in the next generation
  2. Any dead cell with three live neighbors becomes a live cell in the next generation
  3. Any other live cell dies in the next generation
  4. Any other dead cell remains dead in the next generation

  ## Examples

      iex> Cell.will_be_alive?(%Cell{state: :alive, living_neighbors: 1})
      false

      iex> Cell.will_be_alive?(%Cell{state: :alive, living_neighbors: 5})
      false

      iex> Cell.will_be_alive?(%Cell{state: :alive, living_neighbors: 2})
      true

      iex> Cell.will_be_alive?(%Cell{state: :dead, living_neighbors: 3})
      true

  """
  def will_be_alive?(%Cell{living_neighbors: 2, state: :alive}), do: true
  def will_be_alive?(%Cell{living_neighbors: 3, state: :alive}), do: true
  def will_be_alive?(%Cell{living_neighbors: 3, state: :dead}), do: true
  def will_be_alive?(_cell), do: false
end
