package org.eshendo.aztroapp.callback

import android.view.View

interface BeginCallback {
    fun toNextFragment(title: View, img: View, btn: View)
}