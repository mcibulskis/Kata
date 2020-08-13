# Conway

Straight-forward approach to a flat, rectangular grid for Conway's Game of Life.

1. Any live cell with two or three live neighbors survives in the next generation
2. Any dead cell with three live neighbors becomes a live cell in the next generation
3. Any other live cell dies in the next generation
4. Any other dead cell remains dead in the next generation

Notes:
 - This implementation feels wasteful in terms of how it needs to constantly throw away
   and replace cell values in the grid just to build the next generation state
 - This implementation is begging to be refactored to make cells individual agents, with a
   picture of generation state obtained by simply asking all the agents for their state

- TODO: generation calculation
- TODO: querying of neighbors' current state
- TODO: display current generation grid
- TODO: iterating through generations
