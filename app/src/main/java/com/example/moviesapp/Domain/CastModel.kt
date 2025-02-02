package com.example.moviesapp.Domain

import java.io.Serializable

data class CastModel(
    var PicUrl: String = "",
    var Actor: String = ""
): Serializable
// Serializable used to convert objects into a byte stream,
// allowing them to be saved to a file, sent over a network, or passed between Android components.