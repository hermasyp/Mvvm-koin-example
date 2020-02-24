package com.catnip.cateringlist.feature.location


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.catnip.cateringlist.R
import com.catnip.cateringlist.feature.main.CateringAdapter
import com.catnip.cateringlist.utils.result.ResultState
import com.catnip.cateringlist.utils.rv.MarginItemDecoration
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_dialog_location.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class FragmentDialogLocation : BottomSheetDialogFragment() {

    private val TAG = FragmentDialogLocation::class.java.simpleName
    private val viewModel: LocationViewModel by viewModel()
    private lateinit var adapter: LocationAdapter


    companion object{
        fun show(supportFragmentManager: FragmentManager){
            FragmentDialogLocation().show(supportFragmentManager,
                "location_dialog");
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dialog_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        getData()
    }

    private fun initView(){
        rv_locations?.layoutManager = LinearLayoutManager(context)
        adapter = LocationAdapter {
            Toast.makeText(context,it.name, Toast.LENGTH_SHORT).show()
            dismiss()
        }
        rv_locations.adapter = adapter
        iv_close_dialog.setOnClickListener{
            dismiss()
        }
        btn_apply.setOnClickListener {
            dismiss()
        }

    }

    private fun getData() {
        viewModel.getLocationData()
        viewModel.locations.observe(this, Observer {
            when (it) {
                is ResultState.Progress -> {
                    pb_location_list.visibility = View.VISIBLE
                }
                is ResultState.Success -> {
                    pb_location_list.visibility = View.GONE
                    adapter.addData(it.data.locations)
                }
                is ResultState.Error -> {
                    pb_location_list.visibility = View.GONE
                    Toast.makeText(context,getString(R.string.text_fail_load_location), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}
