package com.finki.smartToBuyApp

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
@RequestMapping("api/test")
class Test {

    @GetMapping("tests")
    fun getTest() = listOf<String>("Test", "razbiram", "italijanski")
}