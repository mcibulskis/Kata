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

      iex> Fizzbuzz.translate(5)
      "Buzz"

      iex> Fizzbuzz.translate(15)
      "FizzBuzz"

  """
  def translate(number)

  def translate(number) when rem(number, 15) == 0 do
    "FizzBuzz"
  end

  def translate(number) when rem(number, 3) == 0 do
    "Fizz"
  end

  def translate(number) when rem(number, 5) == 0 do
    "Buzz"
  end

  def translate(number) do
    number
  end
end

