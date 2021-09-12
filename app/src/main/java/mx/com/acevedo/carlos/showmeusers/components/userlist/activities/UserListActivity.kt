package mx.com.acevedo.carlos.showmeusers.components.userlist.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import mx.com.acevedo.carlos.showmeusers.R
import mx.com.acevedo.carlos.showmeusers.components.userlist.fragments.UserListFragment
import mx.com.acevedo.carlos.showmeusers.components.userlist.fragments.UserListFragmentDirections
import mx.com.acevedo.carlos.showmeusers.components.userlist.models.UserModel
import mx.com.acevedo.carlos.showmeusers.databinding.ActivityUserListBinding

@AndroidEntryPoint
class UserListActivity :
    AppCompatActivity(),
    UserListFragment.UserListFragmentListener {

    private val viewBinding by lazy {
        ActivityUserListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
    }

    override fun onUserItemClick(userModel: UserModel) {
        navigateToDetailView(userModel)
    }

    private fun navigateToDetailView(userModel: UserModel) {
        findNavController(R.id.navHostFragment).navigate(
            UserListFragmentDirections.actionToUserDetailFragment(userModel)
        )
    }
}