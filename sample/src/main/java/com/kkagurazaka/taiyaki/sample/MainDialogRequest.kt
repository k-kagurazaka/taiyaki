package com.kkagurazaka.taiyaki.sample

import com.kkagurazaka.taiyaki.DialogRequest

sealed class MainDialogRequest : DialogRequest

data class DialogWithParamRequest(val param: String) : MainDialogRequest()

object DialogWithoutParamRequest : MainDialogRequest()
