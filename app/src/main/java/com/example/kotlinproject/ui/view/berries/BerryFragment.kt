package com.example.kotlinproject.ui.view.berries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlinproject.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BerryFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_berry, container, false)
    }

}