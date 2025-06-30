package com.iscoding.mytask.presentation.util

import android.content.Context
import androidx.annotation.StringRes

sealed class UiText {
    data class DynamicString(val value: String) : UiText()
    class StringResource(
        @StringRes val id: Int,
        val args: Array<Any> = arrayOf()
    ) : UiText()
    data class DynamicStringList(val messages: List<String>) : UiText()

    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> context.getString(id, *args)
            is DynamicStringList -> messages.joinToString(separator = "\n")

        }
    }
}
//// this for compose
//import android.content.Context
//import androidx.annotation.StringRes
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.platform.LocalContext
//
//sealed class UiText {
//    data class DynamicString(val value: String) : UiText()
//    class StringResource(
//        @StringRes val id: Int,
//        val args: Array<Any> = arrayOf()
//    ) : UiText()
//
//    @Composable
//    fun asString(): String {
//        return when (this) {
//            is DynamicString -> value
//            is StringResource -> LocalContext.current.getString(id, *args)
//        }
//    }
//
//    fun asString(context: Context): String {
//        return when (this) {
//            is DynamicString -> value
//            is StringResource -> context.getString(id, *args)
//        }
//    }
//}