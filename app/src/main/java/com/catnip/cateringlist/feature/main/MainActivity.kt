package com.catnip.cateringlist.feature.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.catnip.cateringlist.R
import com.catnip.cateringlist.feature.location.FragmentDialogLocation
import com.catnip.cateringlist.utils.result.ResultState
import com.catnip.cateringlist.utils.rv.MarginItemDecoration
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var adapter: CateringAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        getData()
    }

    private fun initView(){
        rv_catering?.layoutManager = LinearLayoutManager(this)
        rv_catering?.addItemDecoration(MarginItemDecoration(resources.getDimension(R.dimen._8sdp).toInt()))
        adapter = CateringAdapter {
            Toast.makeText(this,it.catererName,Toast.LENGTH_SHORT).show()
        }
        rv_catering.adapter = adapter
    }

    private fun getData() {
        mainViewModel.getCateringData()
        mainViewModel.catering.observe(this, Observer {
            when (it) {
                is ResultState.Progress -> {
                    pb_catering_list.visibility = View.VISIBLE
                }
                is ResultState.Success -> {
                    tv_data_size_info.text = String.format(getString(R.string.text_show_total_data),it.data.caterings.size)
                    pb_catering_list.visibility = View.GONE
                    adapter.addData(it.data.caterings)
                }
                is ResultState.Error -> {
                    Toast.makeText(this,getString(R.string.text_fail_load_catering), Toast.LENGTH_SHORT).show()
                    pb_catering_list.visibility = View.GONE
                }
            }
        })
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.menu_location -> {
                showLocationDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showLocationDialog() {
        FragmentDialogLocation.show(supportFragmentManager)
    }


}
