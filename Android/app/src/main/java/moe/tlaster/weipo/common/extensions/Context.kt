package moe.tlaster.weipo.common.extensions

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.io.Serializable

inline fun <reified T : Activity> Context.openActivity(vararg params: Pair<String, Any?>) {
    startActivity(createIntent(this, T::class.java, params))
}

inline fun <reified T : Activity> Activity.openActivityForResult(
    requestCode: Int,
    vararg params: Pair<String, Any?>
) {
    startActivityForResult(createIntent(this, T::class.java, params), requestCode)
}

inline fun <reified T : Activity> Fragment.openActivityForResult(
    requestCode: Int,
    vararg params: Pair<String, Any?>
) {
    startActivityForResult(context?.let { createIntent(it, T::class.java, params) }, requestCode)
}

fun <T> createIntent(ctx: Context, clazz: Class<out T>, params: Array<out Pair<String, Any?>>): Intent {
    val intent = Intent(ctx, clazz)
    if (params.isNotEmpty()) {
        fillIntentArguments(intent, params)
    }
    return intent
}

fun Context.toast(content: String) {
    Toast.makeText(this, content, Toast.LENGTH_SHORT).show()
}

private fun fillIntentArguments(intent: Intent, params: Array<out Pair<String, Any?>>) {
    params.forEach {
        when (val value = it.second) {
            null -> intent.putExtra(it.first, null as Serializable?)
            is Int -> intent.putExtra(it.first, value)
            is Long -> intent.putExtra(it.first, value)
            is CharSequence -> intent.putExtra(it.first, value)
            is String -> intent.putExtra(it.first, value)
            is Float -> intent.putExtra(it.first, value)
            is Double -> intent.putExtra(it.first, value)
            is Char -> intent.putExtra(it.first, value)
            is Short -> intent.putExtra(it.first, value)
            is Boolean -> intent.putExtra(it.first, value)
            is Serializable -> intent.putExtra(it.first, value)
            is Bundle -> intent.putExtra(it.first, value)
            is Parcelable -> intent.putExtra(it.first, value)
            is Array<*> -> when {
                value.isArrayOf<CharSequence>() -> intent.putExtra(it.first, value)
                value.isArrayOf<String>() -> intent.putExtra(it.first, value)
                value.isArrayOf<Parcelable>() -> intent.putExtra(it.first, value)
                else -> throw Error("Intent extra ${it.first} has wrong type ${value.javaClass.name}")
            }
            is IntArray -> intent.putExtra(it.first, value)
            is LongArray -> intent.putExtra(it.first, value)
            is FloatArray -> intent.putExtra(it.first, value)
            is DoubleArray -> intent.putExtra(it.first, value)
            is CharArray -> intent.putExtra(it.first, value)
            is ShortArray -> intent.putExtra(it.first, value)
            is BooleanArray -> intent.putExtra(it.first, value)
            else -> throw Error("Intent extra ${it.first} has wrong type ${value.javaClass.name}")
        }
        return@forEach
    }
}

fun Context.openBrowser(value: String) {
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(value)))
}


fun Context.getStatusBarHeight(): Int {
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    return resources.getDimensionPixelSize(resourceId)
}

fun Context.getNavigationBarHeight(): Int {
    val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
    return resources.getDimensionPixelSize(resourceId)
}


fun Context.toggleSoftInput() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(0, 0)
}

@TargetApi(android.os.Build.VERSION_CODES.P)
fun Activity.getTopCutoutHeight(): Int {
    val decorView = window.decorView
    var cutOffHeight = 0
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
        val windowInsets = decorView.rootWindowInsets
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            val displayCutout = windowInsets.displayCutout
            if (displayCutout != null) {
                val list = displayCutout.boundingRects
                for (rect in list) {
                    if (rect.top == 0) {
                        cutOffHeight += rect.bottom - rect.top
                    }
                }
            }
        }

    }
    return cutOffHeight
}
