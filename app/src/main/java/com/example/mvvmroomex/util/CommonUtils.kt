package com.example.mvvmroomex.util

import android.content.Context
import java.lang.StringBuilder

class CommonUtils {

    companion object {

        /**
         * 자동 줄바꿈 방지
         */
        fun makeBreakableText(s: String?): String? {
            val sb = StringBuilder()
            if (s == null) return ""
            for (element in s) {
                sb.append(element)
                sb.append("\u200B")
            }
            if (sb.isNotEmpty()) {
                sb.deleteCharAt(sb.length - 1)
            }
            return sb.toString()
        }


        /**
         * DP/PX 변환
         *
         * @param context
         * @param dp
         * @return
         */
        fun pxFromDp(context: Context, dp: Float): Float {
            val scale = context.resources.displayMetrics.density
            return dp * scale
        }
    }
}