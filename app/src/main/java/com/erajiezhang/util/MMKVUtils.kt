package com.erajiezhang.util

import com.tencent.mmkv.MMKV

/**
 *
 * @author EraJieZhang
 * @data 2020/5/23
 */
object MMKVUtils {
    var mmkv: MMKV? = null
    init {
        mmkv = MMKV.defaultMMKV()
    }
}