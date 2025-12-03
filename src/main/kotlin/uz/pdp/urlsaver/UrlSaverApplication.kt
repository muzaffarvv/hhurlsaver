package uz.pdp.urlsaver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UrlSaverApplication

fun main(args: Array<String>) {
    runApplication<UrlSaverApplication>(*args)
}
