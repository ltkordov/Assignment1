package com.ltkordov.ltkordov_rapidrecall.`mvc-abstracts`

// Implemented as described in "MVC and Android" slides, slide 46
// Because this is not an interface for a class but instead the Type of a function inside a controller class
// I chose to not give it an interface box in the UML
// This is because no class implements it, only methods do
fun interface TCommand {
    fun execute()
}