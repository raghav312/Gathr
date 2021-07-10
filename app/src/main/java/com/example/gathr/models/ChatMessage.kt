package com.example.gathr.models

class ChatMessage(
    val id:String="",
    val text: String="",
    val fromId: String="",
    val toId: String="",
    val timestamp: Long = -1
){
}