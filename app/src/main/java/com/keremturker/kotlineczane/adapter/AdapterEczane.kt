package com.keremturker.kotlineczane.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.keremturker.kotlineczane.R
import com.keremturker.kotlineczane.model.Eczane
import com.keremturker.kotlineczane.view.FeedFragment
import com.keremturker.kotlineczane.view.FeedFragmentDirections

class AdapterEczane(var eczaneList: ArrayList<Eczane>) :
    RecyclerView.Adapter<AdapterEczane.ViewHoler>() {

    class ViewHoler(var view: View) : RecyclerView.ViewHolder(view) {

        val txtIlce: TextView = view.findViewById(R.id.txtilce)
        val txtAdress: TextView = view.findViewById(R.id.txtadres)
        val txtAd: TextView = view.findViewById(R.id.txtad)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoler {

        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_eczane, parent, false)

        return ViewHoler(inflater)

    }

    override fun onBindViewHolder(holder: ViewHoler, position: Int) {


        holder.txtAdress.text = eczaneList[position].adres
        holder.txtAd.text = eczaneList[position].ad
        holder.txtIlce.text = eczaneList[position].ilce

        holder.view.setOnClickListener {

            val action =
                FeedFragmentDirections.actionFeedFragmentToEczaneFragment(
                    eczaneList[position]
                )
            Navigation.findNavController(it).navigate(action)

        }
    }

    override fun getItemCount(): Int {

        return eczaneList.size
    }

    fun updateEczaneList(newEczaneList: List<Eczane>) {

        eczaneList.clear()
        eczaneList.addAll(newEczaneList)
        notifyDataSetChanged()


    }
}