package com.omegajak.artisanapp

import android.app.Application

class ArtisanApplication : Application() {
    companion object {
        private lateinit var mContext: ArtisanApplication
        fun getContext() : ArtisanApplication {
            return mContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        mContext = this
    }
}
