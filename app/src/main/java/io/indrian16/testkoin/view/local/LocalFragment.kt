package io.indrian16.testkoin.view.local

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
import kotlinx.android.synthetic.main.fragment_local.*

class LocalFragment : Fragment(), LocalContract.View, RvUser.OnUserClickListener {

    companion object {

        fun newInstance() = LocalFragment()
    }

    private lateinit var presenter: LocalContract.Presenter
    private val mAdapter = RvUser(this)

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        val userDao = AppDatabase.getInstance(context!!).userDao()
        val localRepository = LocalRepository(userDao)
        val remoteRepository = RemoteRepository(UserGenerate())
        val repository = Repository(localRepository, remoteRepository)

        presenter = LocalPresenter(this, repository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_local, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRv()
        setupListener()
        presenter.getUsers()
    }

    private fun setupRv() {

        rvLocal.apply {

            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    private fun setupListener() {

        swipeLayout.setOnRefreshListener {

            presenter.getUsers()
            swipeLayout.isRefreshing = false
        }
    }

    override fun updateDataRv(userList: List<User>) {

        mAdapter.updateData(userList)
    }

    override fun showDeleteInfo(name: String) {

        showToast(getString(R.string.success_del) + " $name")
    }

    override fun showError(error: String) {

        showToast(error)
    }

    override fun onClickUser(user: User) {

        showToast(getString(R.string.user_click) + " ${user.name}")
    }

    override fun onLongClickUser(user: User) {

        val deleteDialog = AlertDialog.Builder(activity).apply {

            setTitle(getString(R.string.del_user))
            setPositiveButton(getString(R.string.del)) { _, _ ->

                presenter.deleteUser(user)
            }
            setNegativeButton(getString(R.string.dismiss)) { _, _ ->

                showToast(getString(R.string.dismiss))
            }
        }
        deleteDialog.show()
    }
}
