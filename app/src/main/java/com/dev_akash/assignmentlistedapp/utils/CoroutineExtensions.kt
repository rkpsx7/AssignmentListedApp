package com.dev_akash.assignmentlistedapp.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

val io: CoroutineDispatcher = Dispatchers.IO

fun ViewModel.ioJob(block: suspend CoroutineScope.() -> Unit): Job =
    viewModelScope.async(io) {
        block()
    }