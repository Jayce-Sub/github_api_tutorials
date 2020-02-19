package com.jaycesub.github_api_tutorials.view.list

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jaycesub.github_api_tutorials.R
import com.jaycesub.github_api_tutorials.model.User
import com.jaycesub.github_api_tutorials.view.adapter.UserListAdapter
import kotlinx.android.synthetic.main.activity_userlist.*

class UserListActivity : AppCompatActivity(), UserListContract.View {

    private lateinit var presenter: UserListPresenter

    private var adapter = UserListAdapter(ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userlist)

        presenter = UserListPresenter(this)

        initView()
        initListener()
    }

    private fun initView() {
        recyclerView_userList.also {
            it.layoutManager = LinearLayoutManager(this@UserListActivity)
            it.adapter = adapter
        }
    }

    private fun initListener() {
        editText_search.addTextChangedListener(object: TextWatcher {

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.q = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Nothing to do.
            }

            override fun afterTextChanged(s: Editable?) {
                // Nothing to do.
            }

        })

        button_search.setOnClickListener {
            presenter.search()
        }

        adapter.setOnItemClickListener(object: UserListAdapter.OnItemClickListener {
            override fun onClick(view: View?, position: Int) {
                // TODO : show user details
            }

        })
    }

    override fun showUserList(user: User) {
        textView_totalCount.text = String.format("total count : %d", user.total_count)
        adapter.setItemList(user.items)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
    }
}