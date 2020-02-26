package com.jaycesub.github_api_tutorials.view.list

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jaycesub.github_api_tutorials.Constants.INTENT_LOGIN
import com.jaycesub.github_api_tutorials.R
import com.jaycesub.github_api_tutorials.model.Items
import com.jaycesub.github_api_tutorials.model.Users
import com.jaycesub.github_api_tutorials.view.adapter.UserLIstATAdapter
import com.jaycesub.github_api_tutorials.view.adapter.UserListAdapter
import com.jaycesub.github_api_tutorials.view.user.UserActivity
import kotlinx.android.synthetic.main.activity_userlist.*

class UserListActivity : AppCompatActivity(), UserListContract.View {

    private val TRIGGER_AUTO_COMPLETE = 100;
    private val AUTO_COMPLETE_DELAY = 300L;

    private lateinit var presenter: UserListPresenter
    private lateinit var userLIstATAdapter: UserLIstATAdapter
    private lateinit var handler: Handler

    private var adapter = UserListAdapter(ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userlist)

        presenter = UserListPresenter(this)

        initHandler()
        initView()
        initListener()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_userlist, menu)
        return true
    }

    private fun initHandler() {
        handler = Handler(Handler.Callback { msg ->
            if(msg.what == TRIGGER_AUTO_COMPLETE) {
                if(autoCompleteTextView_search.text.isNotEmpty()) {
                    presenter.requestPreview()
                }
            }
            false
        })
    }

    private fun initView() {
        recyclerView_userList.also {
            it.layoutManager = LinearLayoutManager(this@UserListActivity)
            it.adapter = adapter
        }
        userLIstATAdapter = UserLIstATAdapter(this, R.layout.item_preview, ArrayList())
        autoCompleteTextView_search.threshold = 2
        autoCompleteTextView_search.setAdapter(userLIstATAdapter)

        setSupportActionBar(toolbar_userList)
    }

    private fun initListener() {
        autoCompleteTextView_search.setOnItemClickListener { parent, view, position, id ->
            userLIstATAdapter.getItem(position)?.login?.let {
                presenter.q = it
                presenter.search()
            }
        }

        autoCompleteTextView_search.addTextChangedListener(object: TextWatcher {

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.q = s.toString()
                handler.removeMessages(TRIGGER_AUTO_COMPLETE)
                handler.sendEmptyMessageDelayed(TRIGGER_AUTO_COMPLETE, AUTO_COMPLETE_DELAY)
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
                presenter.requestUserInfo(position)
            }

        })
    }

    override fun showPreview(users: Users) {
        userLIstATAdapter.setItems(users.items)
    }

    override fun showUserList(users: Users) {
        textView_totalCount.text = String.format("total count : %d", users.total_count)
        adapter.setItemList(users.items)
    }

    override fun showUserInfo(login: String) {
        val intent = Intent(this, UserActivity::class.java)
        intent.putExtra(INTENT_LOGIN, login)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
    }
}