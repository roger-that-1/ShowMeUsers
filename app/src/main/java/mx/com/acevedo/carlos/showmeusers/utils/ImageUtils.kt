package mx.com.acevedo.carlos.showmeusers.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

object LoadImageUtil {

    /**
     * Loads Image from Url in an ImageView
     * @param url Url where the image will be downloaded
     * @param imageView ImageView where the image will be placed
     */
    fun loadImageFromUrl(url: String?, imageView: ImageView) {
        if (url.isNullOrBlank().not()) {
            Glide.with(imageView.rootView.context).load(url).into(imageView)
        }
    }
}