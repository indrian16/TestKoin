package io.indrian16.testkoin.view.remote

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import io.indrian16.testkoin.R
import io.indrian16.testkoin.data.local.AppDatabase
import io.indrian16.testkoin.data.model.User
import io.indrian16.testkoin.data.remote.UserGenerate
import io.indrian16.testkoin.repository.LocalRepository
import io.indrian16.testkoin.repository.RemoteRepository
import io.indrian16.testkoin.repository.Repository
import io.indrian16.testkoin.util.showToast
import io.indrian16.testkoin.view.main.adapter.RvUser
import kotlinx.android.synthetic.main.fragment_remote.*

class RemoteFragment : Fragment(), RemoteContract.View, RvUser.OnUserClickListener {

    companion object {

        fun newInstance() = RemoteFragment()
    }

    private lateinit var presenter: RemoteContract.Presenter
    private val mAdapter = RvUser(this)

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        val userDao = AppDatabase.getInstance(context!!).userDao()
        val localRepository = LocalRepository(userDao)
        val remoteRepository = RemoteRepository(UserGenerate())
        val repository = Repository(localRepository, remoteRepository)

        presenter = RemotePresenter(this, repository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_remote, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRv()
        setupListener()
        presenter.getUsers()
    }

    private fun setupRv() {

        rvRemote.apply {

            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    private fun setupListener() {

        swipeLayout.setOnRefreshListener { presenter.getUsers() }
    }

    override fun updateDataRv(userList: List<User>) {

        mAdapter.updateData(userList)
    }

    override fun showLoading() {

        swipeLayout.isRefreshing = true
    }

    override fun hideLoading() {

        swipeLayout.isRefreshing = false
    }

    override fun showError(error: String) {

        showToast(error)
    }

    override fun addUserInfo(name: String) {

        showToast(getString(R.string.success_add) + " $name")
    }

    override fun onClickUser(user: User) {

        showToast(getString(R.string.user_click) + " ${user.name}")
    }

    override fun onLongClickUser(user: User) {

        val addDialog = AlertDialog.Builder(activity).apply {

            setTitle(R.string.add_user)
            setPositiveButton(R.string.add) { _, _ ->

                presenter.addUser(user)
            }
            setNegativeButton(R.string.dismiss) { _, _ ->

                showToast(getString(R.string.dismiss))
            }

        }.create()
        addDialog.show()
    }

    override fun onDetach() {
        super.onDetach()
        presenter.unSubscribe()
    }
}
