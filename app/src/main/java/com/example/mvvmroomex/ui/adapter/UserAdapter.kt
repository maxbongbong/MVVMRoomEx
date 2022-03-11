package com.example.mvvmroomex.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmroomex.R
import com.example.mvvmroomex.model.User
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter(val context: Context?, val list: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.CustomViewHolder>() {

    interface SelectItem {
        fun selectItem(position: Int, type: String)
    }

    var selectItem: SelectItem? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserAdapter.CustomViewHolder, position: Int) {
        if (list.size > position) {
            holder.bindView(context, position, selectItem, list[position], list.size)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class CustomViewHolder(override val containerView: View?) :
        RecyclerView.ViewHolder(containerView!!), LayoutContainer {

        fun bindView(
            context: Context?,
            position: Int,
            selectItem: SelectItem?,
            user: User,
            size: Int
        ) {

            with(itemView) {

                context?.let {
                    val id = user.id
                    tvUserId.text = id.toString()

                    val name = user.name
                    tvUserName.text = name

                    val age = user.age.toString()
                    tvUserAge.text = age

                    btnDelete.tag = position
                    btnDelete.setOnClickListener {
                        val pos = it.tag.toString().toInt()
                        selectItem?.selectItem(pos, "delete")
                    }
                }
            }

        }
    }
}