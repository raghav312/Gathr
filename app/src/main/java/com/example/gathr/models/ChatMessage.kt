package com.example.gathr.models

//data class for firebase db
// storing user chat messages in this format
class ChatMessage(
    val id:String="",
    val text: String="",
    val fromId: String="",
    val toId: String="",
    val timestamp: Long = -1
){
}