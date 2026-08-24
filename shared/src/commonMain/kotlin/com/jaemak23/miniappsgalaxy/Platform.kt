package com.jaemak23.miniappsgalaxy

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform