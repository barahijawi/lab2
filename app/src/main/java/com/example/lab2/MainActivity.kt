// Your package name
package com.example.lab2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab2.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val chemicals = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAdd.setOnClickListener {
            val chemicalName = binding.editTextChemical.text.toString().trim()
            if (chemicalName.isNotBlank()) {
                addChemical(chemicalName)
            } else {
                binding.textViewMessage.text = "Please enter a chemical name."
            }
        }

        binding.buttonGuess.setOnClickListener {
            val guess = binding.editTextChemical.text.toString().trim()
            if (guess.isNotBlank()) {
                guessChemical(guess)
            } else {
                binding.textViewMessage.text = "Please enter a chemical name to guess."
            }
        }
    }

    private fun addChemical(chemicalName: String) {
        if (chemicalName in chemicals) {
            binding.textViewMessage.text = "Chemical '$chemicalName' is already available."
        } else {
            chemicals.add(chemicalName)
            binding.textViewMessage.text = "Chemical '$chemicalName' added successfully."
            binding.editTextChemical.text.clear()
        }
    }

    private fun guessChemical(guess: String) {
        if (chemicals.isEmpty()) {
            binding.textViewMessage.text = "No chemicals to guess. Please add some first."
            return
        }

        val randomChemical = chemicals.random()
        if (guess.equals(randomChemical, ignoreCase = true)) {
            binding.textViewMessage.text = "Congratulations! You guessed it right. It was $randomChemical."
        } else {
            binding.textViewMessage.text = "Sorry, wrong guess. The correct answer was $randomChemical."
        }
        binding.editTextChemical.text.clear()
    }
}
