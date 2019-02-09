import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

inline fun <T> LiveData<T>.observe(owner: LifecycleOwner, crossinline observer: (t: T?) -> Unit) {
    observe(owner, Observer { observer(it) })
}