package com.fox.feeditemsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fox.feeditemsapp.model.FeedItem
import com.fox.feeditemsapp.repository.Repository
import com.fox.feeditemsapp.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val feedItemStateFlow: MutableStateFlow<ResponseState<List<FeedItem>>>,
    private val repository: Repository
) : ViewModel() {

    fun getFeedItems() = viewModelScope.launch {
        feedItemStateFlow.value = ResponseState.Loading
        repository.getFeedItems()
            .flowOn(Dispatchers.IO)
            .catch { e ->
                feedItemStateFlow.value = ResponseState.Error(e.toString())
            }
            .collect {
                feedItemStateFlow.value = ResponseState.Success(it)
            }
    }

}