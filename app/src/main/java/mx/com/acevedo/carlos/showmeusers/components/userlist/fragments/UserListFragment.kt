package mx.com.acevedo.carlos.showmeusers.components.userlist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import mx.com.acevedo.carlos.showmeusers.components.userlist.models.UserModel
import mx.com.acevedo.carlos.showmeusers.components.userlist.viewmodels.UserListViewModel
import mx.com.acevedo.carlos.showmeusers.components.userlist.views.UserItemView
import mx.com.acevedo.carlos.showmeusers.databinding.FragmentUserListBinding

@AndroidEntryPoint
class UserListFragment : Fragment() {

    private val viewModel by viewModels<UserListViewModel>()

    private val viewBinding by lazy {
        FragmentUserListBinding.inflate(layoutInflater)
    }

    private val itemsAdapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = viewBinding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        bindViewModel()
    }

    /**
     * Binds user model list live data with the view
     */
    private fun bindViewModel() {
        viewModel.getUserModelListObserver().observe(viewLifecycleOwner, ::updateItemList)
        viewModel.showError().observe(viewLifecycleOwner, ::showError)
        viewModel.showSwipeLoading()
            .observe(viewLifecycleOwner, viewBinding.swipeRefreshUserList::setRefreshing)
    }

    /**
     * Shows error message to user
     */
    private fun showError(errorMessage: String) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }

    /**
     * Updates users list user interface
     */
    private fun updateItemList(userModelList: List<UserModel>) {
        itemsAdapter.update(userModelList.map { UserItemView(it) })
    }

    /**
     * Setting up recycler view
     */
    private fun initRecyclerView() {
        with(viewBinding) {
            recyclerViewUserList.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = itemsAdapter
            }
            swipeRefreshUserList.setOnRefreshListener {
                viewModel.updateUserList()
            }
        }
    }
}