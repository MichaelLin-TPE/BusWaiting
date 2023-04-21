package com.bus.buswaiting.http

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import java.io.InputStream


@GlideModule
class UnsafeOkHttpGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        val client = getUnsafeOkHttpClient()
        registry.replace(GlideUrl::class.java,InputStream::class.java,OkHttpUrlLoader.Factory(client))
    }

    private fun getUnsafeOkHttpClient():OkHttpClient{

        val builder = OkHttpClient.Builder()
        val specs: MutableList<ConnectionSpec> = ArrayList()
        specs.add(ConnectionSpec.MODERN_TLS)
        specs.add(ConnectionSpec.COMPATIBLE_TLS)
        specs.add(ConnectionSpec.CLEARTEXT)
        builder.connectionSpecs(specs)

        return builder.build()

    }

}