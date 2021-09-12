package mx.com.acevedo.carlos.showmeusers.components.userlist.views

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import mx.com.acevedo.carlos.showmeusers.R
import mx.com.acevedo.carlos.showmeusers.components.userlist.models.UserModel
import mx.com.acevedo.carlos.showmeusers.databinding.UserItemViewBinding
import mx.com.acevedo.carlos.showmeusers.utils.LoadImageUtil

class UserItemView(val userModel: UserModel) : BindableItem<UserItemViewBinding>() {

    /**
     * Binds item view with [userModel] values
     */
    override fun bind(viewBinding: UserItemViewBinding, position: Int) {
        with(viewBinding) {
            textViewUserName.text = userModel.name
            textViewUserNationality.text = userModel.nationality

            LoadImageUtil.loadImageFromUrl(
                userModel.profilePictureSmall,
                imageViewUserPicture
            )
        }
    }

    override fun getLayout() = R.layout.user_item_view

    override fun initializeViewBinding(view: View) = UserItemViewBinding.bind(view)
}