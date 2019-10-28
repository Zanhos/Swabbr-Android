package com.laixer.navigation.features

import android.content.Intent
import com.laixer.navigation.loadIntentOrNull

object SampleNavigation : DynamicFeature<Intent> {

    const val VLOG_ID_KEYS = "VLOG_ID_KEYS"

    private const val VLOG_LIST = "com.laixer.sample.presentation.vloglist.VlogListActivity"
    private const val VLOG_DETAILS = "com.laixer.sample.presentation.vlogdetails.VlogDetailsActivity"

    override val dynamicStart: Intent?
        get() = VLOG_LIST.loadIntentOrNull()

    fun vlogDetails(vlogIds: ArrayList<String>): Intent? =
        VLOG_DETAILS.loadIntentOrNull()
            ?.apply {
                putExtra(VLOG_ID_KEYS, vlogIds)
            }
}
