package com.example.lesson_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lesson_fragments.databinding.FragmentCounterBinding

class CounterFragments : Fragment() {

    private lateinit var binding: FragmentCounterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCounterBinding.inflate(inflater, container, false)

        binding.counterTextView.text = getString(R.string.screen_label, getCounterValue())
        binding.quoteTextView.text = getQuote()

        binding.launchNextButton.setOnClickListener { launchNext() }
        binding.goBackButton.setOnClickListener { goBack()}

        return binding.root
    }

    private fun launchNext() {
        val fragment = CounterFragments.newInstance(
            counterValue = (requireActivity() as HelloWorldActivity).getScreensCount() +1,
            quote = (requireActivity() as HelloWorldActivity).createQuote()
        )
        parentFragmentManager
            .beginTransaction() // Начинаем транзакцию по изменению наших фрагментов
            .addToBackStack(null)
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    private fun goBack() { // Данная функция типа как кнопка назад
        requireActivity().onBackPressed()
    }

    private fun getCounterValue(): Int = requireArguments().getInt(ARG_COUNTER_VALUE)

    private fun getQuote(): String = requireArguments().getString(ARG_QUOTE)!!

    companion object {

        @JvmStatic
        private val ARG_COUNTER_VALUE = "ARG_COUNTER_VALUE"

        @JvmStatic
        private val ARG_QUOTE = "ARG_QUOTE"

        @JvmStatic
        fun newInstance(counterValue: Int, quote: String): CounterFragments {
            val args = Bundle().apply {
            putInt(ARG_COUNTER_VALUE, counterValue)
            putString(ARG_QUOTE, quote)
            }
            val fragment = CounterFragments()
            fragment.arguments = args
            return fragment
        }

    }
}