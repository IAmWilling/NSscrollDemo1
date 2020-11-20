package com.zhy.nsscrolldemo1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_demo1.*

class FragmentDemo1 : Fragment() {

    private val data = arrayOf(
        "aa",
        "bb",
        "cc",
        "dd",
        "aa",
        "bb",
        "cc",
        "dd",
        "aa",
        "bb",
        "cc",
        "dd",
        "aa",
        "bb",
        "cc",
        "dd",
        "aa",
        "bb",
        "cc",
        "dd",
        "aa",
        "bb",
        "cc",
        "dd",
        "aa",
        "bb",
        "cc",
        "dd",
        "aa",
        "bb",
        "cc",
        "dd"
    ) //假数据

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_demo1, container, false)


        return view
    }

}