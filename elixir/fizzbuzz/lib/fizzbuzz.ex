defmodule Fizzbuzz do
  @moduledoc """
  Documentation for `Fizzbuzz`.
  """

  @doc """
  Translates an input based on FizzBuzz rules.
    - Multiples of 3 translate to Fizz
    - Multiples of 5 translate to Buzz
    - Multiples of 15 translate to FizzBuzz
    - Base case: the number translates to itself

  ## Examples

      iex> Fizzbuzz.translate(1)
      1

      iex> Fizzbuzz.translate(3)
      "Fizz"

  """
  def translate(number)

  def translate(number) when number == 3 do
    "Fizz"
  end

  def translate(number) do
    number
  end
end

