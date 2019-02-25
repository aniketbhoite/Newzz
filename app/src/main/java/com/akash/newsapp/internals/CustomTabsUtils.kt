package com.akash.newsapp.internals

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import com.akash.newsapp.R

internal class CustomTabsUtils {
    companion object {
        private var builder: CustomTabsIntent? = null
        fun launch(activity: Activity, url: String) {
            if (builder == null) {
                builder = CustomTabsIntent.Builder()
                    .setToolbarColor(ContextCompat.getColor(activity.baseContext, R.color.colorPrimary))
                    .setShowTitle(true)
                    .setStartAnimations(activity.baseContext,R.anim.slide_in_right,R.anim.slide_out_left)
                    .setExitAnimations(activity.baseContext,android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                    .build()
            }
            builder?.launchUrl(activity, Uri.parse(url))
        }
    }

}