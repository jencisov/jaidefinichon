import android.app.Activity
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kuma.jaidefinichon.app.ui.adapter.Adapter
import com.kuma.jaidefinichon.databinding.PostItemBinding

fun ViewGroup.inflateBindingLayout(adapter: Adapter): ViewDataBinding {
    return PostItemBinding.inflate(LayoutInflater.from(context), this, false)
}

fun Activity.inflateActivityBindingLayout(layoutRes: Int): ViewDataBinding {
    return DataBindingUtil.setContentView(this, layoutRes)
}

fun inflateFragmentBindingLayout(inflater: LayoutInflater, layoutRes: Int,
                                 container: ViewGroup?): ViewDataBinding {
    return DataBindingUtil.inflate(inflater, layoutRes, container, false)
}