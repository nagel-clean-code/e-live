package com.example.e_live

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.e_live.databinding.FragmentCardBinding
import com.example.e_live.presentation.viewmodels.CardViewModel
import java.util.*


class CardFragment : Fragment(), SeekBar.OnSeekBarChangeListener {

    companion object {
        fun newInstance() = CardFragment()
    }
    var size_fir = arrayOf("10 sm", "50 sm", "100 sm", "150 sm", "200 sm", "300 sm")
    private lateinit var viewModel: CardViewModel
    private var _binding: FragmentCardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCardBinding.inflate(inflater, container, false)
        binding.seekbarValue.setOnSeekBarChangeListener(this);
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        binding.dateIn.setOnClickListener {
            val datePickerDialog = DatePickerDialog(context!!, DatePickerDialog.OnDateSetListener { view, myear, mmonth, mdayOfMonth ->
                binding.dateIn.text = "$mdayOfMonth/$mmonth/$myear"
            }, year, month, day)
            datePickerDialog.show()
        }
        binding.dateOut.setOnClickListener {
            val datePickerDialog = DatePickerDialog(context!!, DatePickerDialog.OnDateSetListener { view, myear, mmonth, mdayOfMonth ->
                binding.dateOut.text = "$mdayOfMonth/$mmonth/$myear"
            }, year, month, day)
            datePickerDialog.show()
        }
        return binding.root
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        binding.size.text = seekBar?.progress.toString()
        val price = progress * 2.5 + 21
        binding.price.text = price.toInt().toString()
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {}

    override fun onStopTrackingTouch(seekBar: SeekBar) {
        binding.size.text = seekBar.progress.toString()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[CardViewModel::class.java]
        // TODO: Use the ViewModel
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}