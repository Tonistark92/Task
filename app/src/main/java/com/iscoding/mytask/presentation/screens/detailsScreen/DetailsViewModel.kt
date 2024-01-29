package com.iscoding.mytask.presentation.screens.detailsScreen

import androidx.lifecycle.ViewModel
import com.iscoding.mytask.domain.usecases.PostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCase: PostsUseCase,

) : ViewModel() {
    private val _state = MutableStateFlow(DetailsPostState())
    val state: MutableStateFlow<DetailsPostState> get() = _state
//    val args= DetailsFragmentArgs.fromBundle(arguments)
    init {
//        viewModelScope.launch {
//            Log.d("HI", "In Viewmodel")
//            // if it did work i should get the args here but casue nav component had bug
            // the navigation component had bug in identifying the args cant pass the args
            // idk if u want me to use fragment manager to complete the task
            // but i left the code if the nav worked i would use and it would work
//            useCase.getPost(args.id).collect { result ->
//                when (result) {
//                    is Resource.Loading -> {}
//                    is Resource.Success -> {
//                    }
//
//                    is Resource.Error -> {}
//                }
//
//            }
//        }
    }


    fun getPost() {
//        viewModelScope.launch {
//            useCase.getPost(args.id).collect { result ->
//                when (result) {
//                    is Resource.Loading -> {
//                        _state.value = _state.value.copy(isLoading = true)
//                    }
//                    is Resource.Success -> {
//                        if (result.data?.id.toString().isEmpty()) {
//                            _state.value = _state.value.copy(
//                                isLoading = false,
//                                notFound = true
//                            )
//                        } else {
//                            _state.value = _state.value.copy(
//                                post = result.data,
//                                isLoading = false,
//                                notFound = false
//                            )
//                        }
//                    }
//
//                    is Resource.Error -> {
//                        _state.value = _state.value.copy(
//                            isLoading = false,
//                            notFound = true
//                        )
//                    }
//                }
//
//            }
//        }
    }

}