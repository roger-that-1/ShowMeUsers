package mx.com.acevedo.carlos.showmeusers.components.userlist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import mx.com.acevedo.carlos.showmeusers.R
import mx.com.acevedo.carlos.showmeusers.components.userlist.viewmodels.UserListViewModel

@AndroidEntryPoint
class UserListActivity : AppCompatActivity() {

    private val viewModel: UserListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
    }
}