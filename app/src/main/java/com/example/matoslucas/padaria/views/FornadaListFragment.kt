package com.example.matoslucas.padaria.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.matoslucas.padaria.R

class FornadaListFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //        if (getArguments() != null) {
        //            mParam1 = getArguments().getString(ARG_PARAM1);
        //            mParam2 = getArguments().getString(ARG_PARAM2);
        //        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fornada_list, container, false)
    }

    companion object {

        fun newInstance(): FornadaListFragment {
//        Bundle args = new Bundle();
            //        args.putString(ARG_PARAM1, param1);
            //        args.putString(ARG_PARAM2, param2);
            //        fragment.setArguments(args);
            return FornadaListFragment()
        }
    }


}
