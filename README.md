# Euro 2020 Match Slack Reminder Generator
## What is this?
A simple Kotlin script to generate reminders for your slack to notify sweepstakes team owners when their team is about to play.
[Match schedules were taken from Wikipedia](https://en.wikipedia.org/wiki/UEFA_Euro_2020). Each section was pasted into `matches.txt` and cleaned up with some regex: find `(\d+ \w+ \d+)\n(\d+:\d+[^\n]*)\n([\w ]+)\tMatch \d+\t ?([\w ]+)\n\tReport\n[^\n]*\n` and replace `$1, $2, $3, $4`
Only group stages are contained in `matches.txt`. More reminders will need to be generated once teams are known for later rounds.

## How do I run this for my Slack?
1. After you've drawn teams, replace the placeholders of each `@Owner's slack username` in `teams.txt`.
2. Change `scriptRunnerTimezone` to your timezone (assuming you are also inputting these reminders to Slack).
3. Run `GenerateReminders.kt` and paste the output into your Slack

## Example output
```
/remind #football (@Alice) Turkey vs. Italy (@Eileen) kicks off in 15 min! at 14:45 on 11 June 2021
/remind #football (@James) Wales vs. Switzerland (@Adam) kicks off in 15 min! at 08:45 on 12 June 2021
/remind #football (@Alice) Turkey vs. Wales (@James) kicks off in 15 min! at 11:45 on 16 June 2021
/remind #football (@Eileen) Italy vs. Switzerland (@Adam) kicks off in 15 min! at 14:45 on 16 June 2021
/remind #football (@Adam) Switzerland vs. Turkey (@Alice) kicks off in 15 min! at 11:45 on 20 June 2021
/remind #football (@Eileen) Italy vs. Wales (@James) kicks off in 15 min! at 11:45 on 20 June 2021
/remind #football (@Rachel) Denmark vs. Finland (@Fred) kicks off in 15 min! at 11:45 on 12 June 2021
```
