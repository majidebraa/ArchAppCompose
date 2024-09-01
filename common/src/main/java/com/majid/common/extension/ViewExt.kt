package com.majid.common.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.google.android.material.snackbar.Snackbar
import com.majid.common.utils.Event

/**
 * Transforms static java function Snackbar.make() to an extension function on View.
 */
fun AppCompatActivity.showSnackbar(snackbarText: String, timeLength: Int) {
    Snackbar.make(findViewById(android.R.id.content), snackbarText, timeLength).show()
}

/**
 * Triggers a snackbar message when the value contained by snackbarTaskMessageLiveEvent is modified.
 */
fun AppCompatActivity.setupSnackbar(lifecycleOwner: LifecycleOwner, snackbarEvent: LiveData<Event<Int>>, timeLength: Int) {
    snackbarEvent.observe(lifecycleOwner) { event ->
        event.getContentIfNotHandled()?.let { res ->
            showSnackbar(getString(res), timeLength)
        }
    }
}