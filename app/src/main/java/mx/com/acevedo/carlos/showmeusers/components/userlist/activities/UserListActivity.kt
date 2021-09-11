package mx.com.acevedo.carlos.showmeusers.components.userlist.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import mx.com.acevedo.carlos.showmeusers.databinding.ActivityUserListBinding

@AndroidEntryPoint
class UserListActivity : AppCompatActivity() {

    private val viewBinding by lazy {
        ActivityUserListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
    }
}