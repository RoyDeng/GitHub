package tw.roy.deng.codility.ui.user

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import dagger.android.support.DaggerFragment
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tw.roy.deng.codility.databinding.FragmentUserBinding
import tw.roy.deng.codility.factory.ViewModelProviderFactory
import tw.roy.deng.codility.ui.user.adapter.UserAdapter
import tw.roy.deng.codility.utils.SharedPreferenceHelper
import javax.inject.Inject

class UserFragment : DaggerFragment() {
    private lateinit var binding: FragmentUserBinding

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private val viewModel by lazy {
        ViewModelProvider(this, factory).get(UserFragmentViewModel::class.java)
    }

    private lateinit var userAdapter: UserAdapter
    private lateinit var prefs: SharedPreferenceHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(inflater)
        prefs = SharedPreferenceHelper.getInstance(requireContext())

        userAdapter = UserAdapter(requireContext(), { user, position ->
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(user.htmlUrl)))
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.usersRecyclerview

        recyclerView.adapter = userAdapter

        with(binding) {
            queryTextInputEdit.setText(prefs.getSearchQuery().toString())

            queryTextInputEdit.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(editable: Editable) {
                    prefs.saveSearchQuery(editable.toString())

                    userAdapter.refresh()
                }

                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                }
            })

            usersSwipeRefresh.setOnRefreshListener {
                userAdapter.refresh()
            }

            userAdapter.addLoadStateListener { loadState ->
                if (loadState.refresh is LoadState.Loading) {
                    binding.retryButton.visibility = View.GONE
                    binding.usersProgressBar.visibility = View.VISIBLE
                } else {
                    binding.usersSwipeRefresh.isRefreshing = false
                    binding.usersProgressBar.visibility = View.GONE

                    val errorState = when {
                        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                        loadState.refresh is LoadState.Error -> {
                            binding.retryButton.visibility = View.VISIBLE
                            loadState.refresh as LoadState.Error
                        }
                        else -> null
                    }
                    errorState?.let {
                        it.error.message?.let { message ->
                            Toasty.error(requireContext(), message, Toast.LENGTH_SHORT, true).show()
                        }
                    }
                }
            }

            retryButton.setOnClickListener {
                userAdapter.retry()
            }
        }

        observeMoreViewModels()
    }

    private fun observeMoreViewModels() {
        with(viewModel) {
            lifecycleScope.launch {
                users.collectLatest {
                    userAdapter.submitData(it)
                }
            }
        }
    }
}