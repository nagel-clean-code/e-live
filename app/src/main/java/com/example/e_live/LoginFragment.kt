package com.example.e_live

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import com.example.e_live.Constants.Companion.SESSION_RESULT_IMPL
import com.example.e_live.databinding.LoginFragmentBinding
import com.example.e_live.presentation.contract.navigator
import com.example.e_live.presentation.models.state.takeSuccess
import com.example.e_live.presentation.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment() {
    private lateinit var binding: LoginFragmentBinding

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val viewModel: LoginViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginFragmentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initListenerButton()
        setupListenerResult()
        navigator().visibleNavigationMenu(false)
        return binding.root
    }

    private fun initListenerButton(){
        binding.button.setOnClickListener {
            val mail = binding.inputLogin.text.toString()
            val pass = binding.inputPassword.text.toString()
            if( !(mail.isNotBlank() && pass.isNotBlank()) ) { //FIXME пока не сделал логин, потом убрать отрицание
                binding.errorIsEmptyField.visibility = View.GONE
                viewModel.requestLogin(mail, pass)
            }else{
                binding.errorIsEmptyField.visibility = View.VISIBLE
            }
        }
    }

    private fun setupListenerResult() {
        viewModel.loadResultModelLiveData.observe(viewLifecycleOwner) { result ->
            renderResult(
                root = binding.root,
                result = result,
                onSuccessResult = {
                    binding.progressBar.visibility = View.GONE
                    binding.textError.visibility = View.GONE
                    navigator().visibleNavigationMenu(true)
                    returnResult()
                },
                onPending = {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.textError.visibility = View.GONE
                },
                onError = {
                    binding.progressBar.visibility = View.GONE
                    binding.textError.text = it.localizedMessage
                    binding.textError.visibility = View.VISIBLE
                }
            )
        }
    }

    private fun returnResult() {
        parentFragmentManager.popBackStack()
        parentFragmentManager.setFragmentResult(
            Constants.FRAGMENT_LOGIN,
            bundleOf(SESSION_RESULT_IMPL to viewModel.loadResultModelLiveData.value?.takeSuccess())
        )
    }


}