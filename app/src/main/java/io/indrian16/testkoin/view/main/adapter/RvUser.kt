package io.indrian16.testkoin.view.main.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.indrian16.testkoin.R
import io.indrian16.testkoin.data.model.User
import kotlinx.android.synthetic.main.user_item.view.*

class RvUser(private val listener: OnUserClickListener) : RecyclerView.Adapter<RvUser.UserHolder>() {

    private var userList: List<User> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): UserHolder {

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.user_item, parent, false)
        return UserHolder(view)
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: UserHolder, position: Int) = holder.bind(userList[position])

    fun updateData(userList: List<User>) {

        this.userList = userList
        notifyDataSetChanged()
    }

    inner class UserHolder(view: View) : RecyclerView.ViewHolder(view) {

        @SuppressLint("SetTextI18n")
        fun bind(user: User) {

            itemView.apply {

                tvName.text = user.name
                tvAge.text = "Age: ${user.age}"

                setOnClickListener { listener.onClickUser(user) }
                setOnLongClickListener { listener.onLongClickUser(user); true }
            }
        }
    }

    interface OnUserClickListener {

        fun onClickUser(user: User)

        fun onLongClickUser(user: User)
    }
}