# Live Football World Cup Scoreboard

A simple Java application that simulates a **live football scoreboard** for World Cup matches. It supports live match
tracking, score updates, and displays a sorted summary of ongoing games by total score.

---

## Features

- Start a new game (initial score is always 0-0)
- Update score of a match
- Finish a game (remove it from scoreboard)
- Get a live summary of all games, ordered by:
    1. Total score (descending)
    2. Most recently added (for ties)

---

### Assumptions

- Match is started exactly after added to the score board
- Name of teams are case-insensitive, all characters are allowed
- Each match is uniquely identified by **the pair of teams in the given order**: homeTeam vs awayTeam

