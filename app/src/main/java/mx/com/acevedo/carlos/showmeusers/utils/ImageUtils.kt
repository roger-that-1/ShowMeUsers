package mx.com.acevedo.carlos.showmeusers.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

object LoadImageUtil {

    private const val ROUNDING_RADIUS = 16

    /**
     * Loads Image from Url in an ImageView
     * @param url where the image will be downloaded
     * @param imageView where the image will be placed
     */
    fun loadImageFromUrl(url: String?, imageView: ImageView) =
        loadUrlImage(imageView, url)?.into(imageView)

    /**
     * Loads Image with rounded corners from Url in an ImageView
     * @param url where the image will be downloaded
     * @param imageView where the image will be placed
     */
    fun loadRoundedImageFromUrl(url: String?, imageView: ImageView) {
        val requestOptions =
            RequestOptions().transform(CenterCrop(), RoundedCorners(ROUNDING_RADIUS))

        loadUrlImage(imageView, url)?.apply(requestOptions)?.into(imageView)
    }

    /**
     * Gets load url image Glide instance, it should do nothing when [url] is null or blank
     * @param url where the image will be downloaded
     * @param imageView required to get context
     */
    private fun loadUrlImage(imageView: ImageView, url: String?) =
        url.takeIf { it.isNotNullOrBlank() }?.let {
            Glide.with(imageView.rootView.context).load(it)
        }
}