package lt.markmerkk.mock_app.utils

import android.util.Log

/**
 * @author mariusmerkevicius
 * @since 2016-10-24
 */
object Logger {
    fun log(message: String) {
        Log.d("ProductsApp", message)
    }
}