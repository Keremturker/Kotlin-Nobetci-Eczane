package com.keremturker.kotlineczane.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.keremturker.kotlineczane.R
import com.keremturker.kotlineczane.adapter.AdapterEczane
import com.keremturker.kotlineczane.viewmodel.FeedViewModel


class FeedFragment : Fragment() {


    private lateinit var rvList: RecyclerView
    private lateinit var btnAra: Button
    private lateinit var spSehir: Spinner
    private lateinit var pbLoading: ProgressBar
    private lateinit var txtError: TextView
    private lateinit var viewModel: FeedViewModel
    private var adapterEczane = AdapterEczane(arrayListOf())

    companion object {

        var sehir = ""
    }


    override

    fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvList = view.findViewById(R.id.rv_eczane)
        btnAra = view.findViewById(R.id.btn_ara)
        spSehir = view.findViewById(R.id.sp_sehir)
        pbLoading = view.findViewById(R.id.pb_loading)
        txtError = view.findViewById(R.id.txt_error)
        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)

        (activity as AppCompatActivity).supportActionBar?.title = "Nöbetçi Eczane"


        viewModel.addSehir(context)

        rvList.layoutManager = LinearLayoutManager(context)
        rvList.adapter = adapterEczane




        observeLiveData()

        btnAra.setOnClickListener {

            val sehir_item = spSehir.selectedItem.toString()


            viewModel.getData(sehir_item, "")

            sehir = sehir_item

        }


    }

    private fun observeLiveData() {

        viewModel.eczaneList.observe(viewLifecycleOwner, {

            it?.let {

                rvList.visibility = View.VISIBLE
                adapterEczane.updateEczaneList(it)


            }

        })

        viewModel.sehirList.observe(viewLifecycleOwner, { list ->


            context?.let {
                spSehir.adapter = ArrayAdapter(
                    it,
                    android.R.layout.simple_spinner_item, list
                )
            }


        })



        viewModel.eczaneLoading.observe(viewLifecycleOwner, {


            it?.let {

                if (it) {

                    pbLoading.visibility = View.VISIBLE
                    rvList.visibility = View.GONE
                    txtError.visibility = View.GONE

                } else {
                    pbLoading.visibility = View.GONE
                }
            }


        })

        viewModel.eczaneError.observe(viewLifecycleOwner, {

            it?.let {


                if (it) {


                    txtError.visibility = View.VISIBLE


                } else {


                    txtError.visibility = View.GONE

                }


            }
        })


    }
}