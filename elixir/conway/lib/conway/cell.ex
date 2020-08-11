defmodule Cell do
  @moduledoc """
  Represents a cell within the "world".
  """

  defstruct state: :dead, living_neighbors: 0

  @doc """
  Generates the cell state representation for the cell for the next generation.

  1. Any live cell with two or three live neighbors survives in the next generation
  2. Any dead cell with three live neighbors becomes a live cell in the next generation
  3. Any other live cell dies in the next generation
  4. Any other dead cell remains dead in the next generation

  ## Examples

      iex> Cell.next(%Cell{state: :alive, living_neighbors: 1})
      %Cell{state: :dead}

      iex> Cell.next(%Cell{state: :alive, living_neighbors: 5})
      %Cell{state: :dead}

      iex> Cell.next(%Cell{state: :alive, living_neighbors: 2})
      %Cell{state: :alive}

      iex> Cell.next(%Cell{state: :dead, living_neighbors: 3})
      %Cell{state: :alive}

  """
  def next(%Cell{living_neighbors: 2, state: :alive}), do: %Cell{state: :alive}
  def next(%Cell{living_neighbors: 3, state: :alive}), do: %Cell{state: :alive}
  def next(%Cell{living_neighbors: 3, state: :dead}), do: %Cell{state: :alive}
  def next(_cell), do: %Cell{}
end
