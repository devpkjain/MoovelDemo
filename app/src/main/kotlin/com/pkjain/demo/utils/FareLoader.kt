package com.pkjain.demo.utils

import android.content.res.Resources
import android.support.annotation.RawRes
import com.pkjain.demo.model.Rider
import com.pkjain.demo.model.RiderInfo

/**
 *
 */
fun Resources.loadFareInfo(@RawRes resId: Int): List<RiderInfo> {
    val jsonSource = openRawResource(resId).bufferedReader().use { it.readText() }
    val rider = ObjectMapperFactory.create().readValue(jsonSource, Rider::class.java)
    return rider.transformToRiderInfo();
}

fun Rider.transformToRiderInfo(): List<RiderInfo> {
    val itemList: ArrayList<RiderInfo> = java.util.ArrayList()
    itemList.add(RiderInfo("Adult", Adult))
    itemList.add(RiderInfo("Child", Child))
    itemList.add(RiderInfo("Senior", Senior))
    return itemList;
}