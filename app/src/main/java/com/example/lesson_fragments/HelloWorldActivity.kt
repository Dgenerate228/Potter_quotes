package com.example.lesson_fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lesson_fragments.databinding.ActivityMainBinding
import com.github.javafaker.Faker

class HelloWorldActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var faker = Faker.instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        if (savedInstanceState == null) {
            val fragment = CounterFragments.newInstance(
                counterValue = 1,
                quote = createQuote()
            )
            supportFragmentManager
                .beginTransaction()  // Начинаем транзакцию по изменению фрагментов
                .add(R.id.fragmentContainer, fragment) // Добавляем что-либо во фрагмент
                .commit()
        }
    }

    fun createQuote(): String {
        return faker.harryPotter().quote()
    }

    fun getScreensCount(): Int {
        return supportFragmentManager.backStackEntryCount + 1
    }

}