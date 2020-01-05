package com.akash.newsapp

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import coil.Coil
import coil.ImageLoader
import coil.util.CoilUtils
import com.akash.newsapp.utils.PreferenceHelper
import com.jakewharton.threetenabp.AndroidThreeTen
import glimpse.core.Glimpse
import okhttp3.OkHttpClient

class NewsApplication : Application() {

    override fun onCreate() {
        instances = this
        super.onCreate()
        AndroidThreeTen.init(this)
        coilSetUp()
    }

    private fun coilSetUp() {
        Coil.setDefaultImageLoader {
            ImageLoader(this) {
                crossfade(true)
                allowHardware(false)
                bitmapPoolPercentage(0.5)
                okHttpClient {
                    OkHttpClient.Builder()
                        .cache(CoilUtils.createDefaultCache(this@NewsApplication))
                        .build()
                }
            }
        }
        Glimpse.init(this)
    }
    companion object {
        lateinit var instances: NewsApplication
        val prefs: PreferenceHelper by lazy { PreferenceHelper(instances) }


        fun isNetworkConnected(): Boolean {
            val connectivityManager =
                instances.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }
}
