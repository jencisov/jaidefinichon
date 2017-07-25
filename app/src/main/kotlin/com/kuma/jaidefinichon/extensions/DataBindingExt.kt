import android.app.Activity
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by Kuma on 5/11/17.
 */
fun Activity.inflateActivityBindingLayout(layoutRes: Int): ViewDataBinding {
  return DataBindingUtil.setContentView(this, layoutRes)
}

fun inflateFragmentBindingLayout(inflater: LayoutInflater, layoutRes: Int,
                                 container: ViewGroup?): ViewDataBinding {
  return DataBindingUtil.inflate(inflater, layoutRes, container, false)
}