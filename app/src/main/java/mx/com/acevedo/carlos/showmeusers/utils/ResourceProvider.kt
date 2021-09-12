package mx.com.acevedo.carlos.showmeusers.utils

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourceProvider @Inject constructor(
    @ApplicationContext private val context: Context
) {
    /**
     * Provides a string value from [id] string id string resource value
     */
    fun getString(@StringRes id: Int) = context.getString(id)
}