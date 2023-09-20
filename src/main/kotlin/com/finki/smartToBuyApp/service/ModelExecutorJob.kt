package com.finki.smartToBuyApp.service

import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.InputStreamReader

@Service
@EnableScheduling
class ModelExecutorJob(
    private val userService: UserService,
    private val suggestedProductService: SuggestedProductService,
    private val productService: ProductService,
) {
    @Scheduled(fixedRate = 60000 * 60) // Schedule the job to run every 1 hour
//    @Scheduled(fixedRate = 60000) // Schedule the job to run every 1 minute
    fun runScheduledJob() {
        println("Scheduled job is running...")
        val users = userService.findAll()
        users.forEach { user ->
            try {
                val processBuilder =
                    ProcessBuilder(
                        "D:\\Andrej\\smartToBuyApp-model\\Scripts\\python.exe",
                        "D:\\Andrej\\smartToBuyApp-model\\model\\predict-grocery-shopping.py",
                        "${user.id}"
                    )
                val process = processBuilder.start()
                val exitCode = process.waitFor()

                if (exitCode == 0) {
                    val reader = BufferedReader(InputStreamReader(process.inputStream))
                    val groceriesList = mutableListOf<String>()
                    var line: String?

                    while (reader.readLine().also { line = it } != null) {
                        groceriesList.add(line!!)
                    }

                    groceriesList.forEach { productId ->
                        suggestedProductService.save(
                            user,
                            productService.findById(productId.toLong())
                        )
                    }

                } else {
                    println("Python script execution failed with exit code $exitCode.")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


    }
}
