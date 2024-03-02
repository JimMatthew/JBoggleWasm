package org.example.jamweb

import BoggleGame
import CreateBoard
import Greeting
import SERVER_PORT
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    val gameStorage = mutableListOf<BoggleGame>()
    var highScore : HighScoreBoggleGame = HighScoreBoggleGame(null)
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }
    routing {
        get("/") {
            call.respondText("Ktor: ${Greeting().greet()}")
        }
        get("/randomboard"){
            var b = CreateBoard()
            var s = ""
            b.forEach { s += it }
            val bu = boardUnit(s)
            //call.respondText("board=$s")
            call.respondText(Json.encodeToString(s), ContentType.Application.Json)
            //call.respond(bu)
        }
        get("/HighScore") {
            val b = highScore.board
            if (b != null){
                call.respondText(Json.encodeToString(b), ContentType.Application.Json)
            } else {
                call.respondText ("no scores saved", status = HttpStatusCode.BadRequest)
            }
            call.respondText("Ktor: ${Greeting().greet()}")
        }
        route("/submitgame") {
            post {
                val game = call.receive<BoggleGame>()
                println("id: ${game.id} score: ${game.score} words found: ${game.wordsFound}")
                gameStorage.add(game)
                call.respondText ("game saved", status = HttpStatusCode.OK)
                val b = highScore.board
                if (b == null || game.score > b.score) {
                    highScore.board = game
                }
            }
        }

    }
}


data class boardUnit(val board: String)



data class HighScoreBoggleGame(var board: BoggleGame?)


val gameStorage = mutableListOf<BoggleGame>()