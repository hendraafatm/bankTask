package com.app.bankmisr.presentation.conversion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.app.bankmisr.R
import com.app.bankmisr.databinding.FragmentCurrencyConvertBinding
import com.app.bankmisr.presentation.details.DetailsFragment
import com.app.check.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyConvertFragment : BaseFragment() {

    private lateinit var binding: FragmentCurrencyConvertBinding
    private val mViewModel: ConversionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCurrencyConvertBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        initViewModel()
    }

    private fun initViewModel() {
        mViewModel.currencyConvertResultLiveData.observe(viewLifecycleOwner) { result ->
            binding.tvAmountAfter.text = result.toString()
        }
        mViewModel.symbolsLiveData.observe(viewLifecycleOwner) { currencyList ->
            updateCurrencySpinners(currencyList)
        }
        mViewModel.showLoading.observe(viewLifecycleOwner) { show ->
            handleLoading(show)
        }
        mViewModel.showError.observe(viewLifecycleOwner) { message ->
            showToast(message)
        }
    }

    private fun updateCurrencySpinners(currencyList: List<String>?) {
        currencyList?.let { list ->
            activity?.let {
                val adapter = ArrayAdapter(it, android.R.layout.simple_spinner_item, list)
                binding.fromSpinner.adapter = adapter
                binding.toSpinner.adapter = adapter

                binding.fromSpinner.onItemSelectedListener =
                    object : AdapterView.OnItemSelectedListener {
                        override fun onNothingSelected(parent: AdapterView<*>?) {}

                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            onCheckConversion()
                        }
                    }

                binding.toSpinner.onItemSelectedListener =
                    object : AdapterView.OnItemSelectedListener {
                        override fun onNothingSelected(parent: AdapterView<*>?) {}

                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            onCheckConversion()
                        }

                    }
            }
        }
    }

    private fun initUI() {
        binding.etAmountBefore.doAfterTextChanged {
            onCheckConversion()
        }
        binding.btnDetails.setOnClickListener {
            onShowDetails()
        }
        binding.tvSwitch.setOnClickListener {
            onSwitchCurrency()
        }
    }

    private fun onSwitchCurrency() {
        val fromSelectedPosition = binding.fromSpinner.selectedItemPosition
        val toSelectedPosition = binding.toSpinner.selectedItemPosition

        binding.fromSpinner.setSelection(toSelectedPosition)
        binding.toSpinner.setSelection(fromSelectedPosition)
    }

    private fun onShowDetails() {
        val baseCurrency = binding.fromSpinner.selectedItem.toString()
        val symbolsCurrency = binding.toSpinner.selectedItem.toString()

        if (baseCurrency.isEmpty() || symbolsCurrency.isEmpty()) {
            Toast.makeText(activity, getString(R.string.select_currencies), Toast.LENGTH_LONG)
                .show()
            return
        }

        if (baseCurrency == symbolsCurrency) {
            Toast.makeText(
                activity,
                getString(R.string.select_different_currencies),
                Toast.LENGTH_LONG
            ).show()
            return
        }

        binding.etAmountBefore.text.clear()
        binding.tvAmountAfter.text = null

        findNavController().navigate(
            CurrencyConvertFragmentDirections.actionCurrencyConvertFragmentToDetailsFragment(
                baseCurrency,
                symbolsCurrency
            )
        )
    }

    private fun onCheckConversion() {
        val baseCurrency = binding.fromSpinner.selectedItem?.toString()
        val symbolsCurrency = binding.toSpinner.selectedItem?.toString()
        val amount = binding.etAmountBefore.text.toString()

        // conversion will not happen if the two selections are equals.
        if (!baseCurrency.isNullOrEmpty()
            && !symbolsCurrency.isNullOrEmpty()
            && baseCurrency != symbolsCurrency
            && amount.isNotEmpty()
        ) {
            try {
                val numberToConvert = amount.toDouble()
                mViewModel.currencyConvert(symbolsCurrency, baseCurrency, numberToConvert)
            } catch (ex: NumberFormatException) {
                Toast.makeText(activity, getString(R.string.invalid_num), Toast.LENGTH_LONG).show()
            }
        }
    }
}