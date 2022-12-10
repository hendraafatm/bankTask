package com.app.bankmisr.presentation.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.app.bankmisr.databinding.FragmentDetailsBinding
import com.app.bankmisr.utils.getCurrencyCustomList
import com.app.bankmisr.utils.getDateAfterThreeDaysFormatted
import com.app.bankmisr.utils.getTodayDateFormatted
import com.app.check.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val mViewModel: DetailsViewModel by viewModels()

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()

    }

    private fun initViewModel() {
        mViewModel.currencyHistoryLiveData.observe(viewLifecycleOwner) { historyRes ->
            val list = getCurrencyCustomList(historyRes)
            Log.d("currencyHistory", list.toString())
        }

        mViewModel.getCurrencyHistory(
            args.base,
            args.symbols,
            getTodayDateFormatted(),
            getDateAfterThreeDaysFormatted()
        )
    }
}