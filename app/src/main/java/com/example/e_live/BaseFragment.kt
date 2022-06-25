package com.example.e_live

import android.view.View
import androidx.fragment.app.Fragment
import com.example.e_live.presentation.models.state.ErrorResult
import com.example.e_live.presentation.models.state.PendingResult
import com.example.e_live.presentation.models.state.Result
import com.example.e_live.presentation.models.state.SuccessResult


abstract class BaseFragment : Fragment() {

    fun <T> renderResult(
        root: View, result: Result<T>,
        onPending: () -> Unit,
        onError: (Exception) -> Unit,
        onSuccessResult: (T) -> Unit
    ) {
        when(result){
            is SuccessResult -> onSuccessResult(result.data)
            is PendingResult -> onPending()
            is ErrorResult -> onError(result.exception)
        }
    }
}