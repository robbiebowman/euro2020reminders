import java.io.File
import java.time.*
import java.time.format.DateTimeFormatter

const val reminderMins = 15
const val scriptRunnerTimezone = "America/New_York"
const val inputListTimezone = "UTC+2"

fun main(args: Array<String>) {
    val teamLines = readInput("teams.txt")
    val teams = teamLines.map(::parseTeam)

    val matchLines = readInput("matches.txt")
    val matches = matchLines.map { parseMatch(teams, it) }

    matches.forEach {
        val nyTime = it.time.minusMinutes(reminderMins.toLong()).withZoneSameInstant(ZoneId.of(scriptRunnerTimezone))
        val slackTime = nyTime.format(DateTimeFormatter.ofPattern("HH:mm"))
        val slackDate = nyTime.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))
        println("/remind #football (${it.teams.first.owner}) ${it.teams.first.name} vs. ${it.teams.second.name} (${it.teams.second.owner}) kicks off in $reminderMins min! at $slackTime on $slackDate")
    }
}

fun parseTeam(teamLine: String): Team {
    val teamAndOwner = teamLine.split(',').map { it.trim() }
    return Team(teamAndOwner.first(), teamAndOwner.last())
}

fun parseMatch(teams: List<Team>, matchLine: String): Match {
    val values = matchLine.split(',').map { it.trim() }
    val localTime = LocalDateTime.parse("${values[0]} ${values[1]}", DateTimeFormatter.ofPattern("d MMMM yyyy HH:mm"))
    return Match(
        Pair(
            teams.first { it.name == values[2] },
            teams.first { it.name == values[3] }
        ),
        ZonedDateTime.ofLocal(localTime, ZoneId.of(inputListTimezone), ZoneOffset.ofHours(-4))
    )
}

fun readInput(fileLocation: String): List<String> {
    val lines = mutableListOf<String>()
    File(fileLocation).forEachLine { lines += it }
    return lines
}

data class Match(
    val teams: Pair<Team, Team>,
    val time: ZonedDateTime,
)

data class Team(
    val name: String,
    val owner: String
)