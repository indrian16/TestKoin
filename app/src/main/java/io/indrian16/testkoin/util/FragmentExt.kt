package io.indrian16.testkoin.util

import android.support.v4.app.Fragment
import android.widget.Toast

fun Fragment.showToast(message: String) = Toast.makeText(context, message, Toast.LENGTH_LONG).show()