package pl.palusinskifilip.usingokhttpandretrofit

import android.content.Context

class MainTextFormatter(private val
                        applicationContext: Context
) {
    fun getCounterText(count: Int) =
        applicationContext.getString(R.string.total_request_count, count)
}