package mx.com.acevedo.carlos.showmeusers.components.userlist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint
import mx.com.acevedo.carlos.showmeusers.components.userlist.di.UserDetailInjectorHelper
import mx.com.acevedo.carlos.showmeusers.components.userlist.models.UserModel
import mx.com.acevedo.carlos.showmeusers.components.userlist.viewmodels.UserDetailViewModel
import mx.com.acevedo.carlos.showmeusers.components.userlist.views.DetailItemView
import mx.com.acevedo.carlos.showmeusers.databinding.FragmentUserDetailBinding
import mx.com.acevedo.carlos.showmeusers.utils.LoadImageUtil
import javax.inject.Inject

@AndroidEntryPoint
class UserDetailFragment : Fragment() {

    @Inject
    lateinit var assistedFactory: UserDetailViewModel.Factory

    private val args by navArgs<UserDetailFragmentArgs>()

    private val viewModel: UserDetailViewModel by viewModels {
        UserDetailInjectorHelper.provideFactory(
            assistedFactory,
            args.userModel
        )
    }

    private val viewBinding by lazy {
        FragmentUserDetailBinding.inflate(layoutInflater)
    }

    private val userDetailAdapter by lazy {
        GroupieAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = viewBinding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setUi(viewModel.getUserModel())
    }

    /**
     * Set [userModel] in detail view
     */
    private fun setUi(userModel: UserModel) {
        with(viewBinding) {
            textViewUserName.text = userModel.name
            LoadImageUtil.loadRoundedImageFromUrl(userModel.profilePictureLarge, imageViewUserPicture)
            setUserDetailItemList()
        }
    }

    /**
     * Add user detail item list to view
     */
    private fun setUserDetailItemList() {
        userDetailAdapter.addAll(
            viewModel.getDetailItemModelList().map {
                DetailItemView(it.first, it.second)
            }
        )
    }

    /**
     * Setting up recycler view
     */
    private fun initRecyclerView() {
        with(viewBinding) {
            recyclerViewUserDetail.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = userDetailAdapter
            }
        }
    }
}