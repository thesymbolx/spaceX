package evans.dale.spacex.utils

import android.content.res.Resources
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import evans.dale.spacex.R
import java.lang.Exception


@BindingAdapter("imageUrl")
fun imageUrl(view: ImageView, url: String?) {
    val circularProgressDrawable = CircularProgressDrawable(view.context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()

    Glide
        .with(view)
        .asBitmap()
        .load(url)
        .error(R.drawable.spacex_logo)
        .fitCenter()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(circularProgressDrawable)
        .into(view)
}

@BindingAdapter("app:setImageId")
fun setImageId(view: ImageView, id: Int) {
    try {
        view.setImageResource(id)
    } catch (e: Resources.NotFoundException) {
    }
}