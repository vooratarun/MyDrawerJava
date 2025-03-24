package com.example.mydrawerjava.kotlinpractice

fun main(args: Array<String>) {
    //For loop
    for (i in 1..10) {
        if (i % 2 == 0)
            println(i)
    }
    println()

    for (i in 6 downTo 0 step 3) {
        println(i)
    }
    println()

    for (i in 5.downTo(1) step 3) {
        println(i)
    }
    println()

    //While loop
    var j = 1;
    while (j < 5) {
        println(j)
        j++
    }

    //Do-while loop
    do {
        println(j)
        j++
    } while ((j < 5))
    println()

    //break statement
    for (i in 1..5) {
        if (i == 4)
            break
        println(i)
    }
    println()

    loopBreaker@ for (i in 1..3) {
        for (k in 1..3) {
            if (i == 2 && k == 2)
                break@loopBreaker
            println("$i $k")
        }
    }
    println()

    //continue statement
    for (i in 1..5) {
        if (i == 4)
            continue
        println(i)
    }
    println()

    for (i in 1..3) {
        for (k in 1..3) {
            if (i == 2 && k == 2)
                continue
            println("$i $k")
        }
    }
    println()

    loopCont@ for (i in 1..3) {
        for (k in 1..3) {
            if (i == 2 && k == 2)
                continue@loopCont
            println("$i $k")
        }
    }
}