package com.example.assignment.coreBase

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow

open class BaseViewmodel : ViewModel() {

    /**
     *  apiChannel send API request/response State
     *  apiFlow collect states for show hide dialog and also Error Handling
     */
    var apiChannel = Channel<APIState<Any>>()

    var apiFlow = apiChannel.consumeAsFlow()


}
