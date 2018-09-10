package com.pkjain.demo.utils

import android.content.res.Resources
import android.support.annotation.RawRes
import com.pkjain.demo.model.FareType
import com.pkjain.demo.model.Rider
import com.pkjain.demo.model.RiderInfo

/**
 *
 */
fun Resources.loadFareInfo(@RawRes resId: Int): List<RiderInfo> {
    return ObjectMapperFactory.create().readValue(openRawResource(resId).bufferedReader().use { it.readText() }.apply {
        ObjectMapperFactory.create().readValue(this, Rider::class.java)
    }, Rider::class.java).transformToRiderInfo();
}

fun Rider.transformToRiderInfo(): List<RiderInfo> {
    return ArrayList<RiderInfo>().apply {
        add(RiderInfo(FareType.Adult, Adult))
        add(RiderInfo(FareType.Child, Child))
        add(RiderInfo(FareType.Senior, Senior))
    }
}
