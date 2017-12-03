package com.kuma.jaidefinichon.extensions

fun String.toLongHash(): Long {
    var hash = 98764321261L
    val chars = this.toCharArray()

    for (index in chars.indices) {
        hash = 31 * hash + chars[index].toLong()
    }

    return hash;
}