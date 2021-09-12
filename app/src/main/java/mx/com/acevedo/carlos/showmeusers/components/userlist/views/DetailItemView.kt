package mx.com.acevedo.carlos.showmeusers.components.userlist.views

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import mx.com.acevedo.carlos.showmeusers.R
import mx.com.acevedo.carlos.showmeusers.databinding.DetailItemViewBinding

class DetailItemView(private val title: String, private val content: String) :
    BindableItem<DetailItemViewBinding>() {

    /**
     * Binds item view with [title] and [content] values
     */
    override fun bind(viewBinding: DetailItemViewBinding, position: Int) {
        with(viewBinding) {
            textViewContent.text = content
            textViewTitle.text = title
        }
    }

    override fun getLayout() = R.layout.detail_item_view

    override fun initializeViewBinding(view: View) = DetailItemViewBinding.bind(view)
}