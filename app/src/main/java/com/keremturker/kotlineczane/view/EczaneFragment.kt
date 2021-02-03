package com.keremturker.kotlineczane.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.keremturker.kotlineczane.R
import com.keremturker.kotlineczane.model.Eczane


class EczaneFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_eczane, container, false)
    }

    var eczane = Eczane("", "", "", "", "1,1")
    private lateinit var eczaneIlce: TextView
    private lateinit var eczaneAdi: TextView
    private lateinit var eczaneAdres: TextView
    private lateinit var eczaneTelefon: TextView
    private lateinit var eczaneGit: Button


    override
    fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.let {

            eczane = EczaneFragmentArgs.fromBundle(it).eczane

        }



        (activity as AppCompatActivity).supportActionBar?.title = FeedFragment.sehir

        eczaneIlce = view.findViewById(R.id.eczaneIlce)
        eczaneAdi = view.findViewById(R.id.eczaneAdi)
        eczaneAdres = view.findViewById(R.id.eczaneAdres)
        eczaneTelefon = view.findViewById(R.id.eczaneTelefon)
        eczaneGit = view.findViewById(R.id.eczaneGit)



        eczaneIlce.text = eczane.ilce
        eczaneAdi.text = eczane.ad
        eczaneAdres.text = eczane.adres
        eczaneTelefon.text = eczane.telefon

        eczaneGit.setOnClickListener {

            try {
                val uri = "http://maps.google.com/maps?q=loc:${eczane.konum}"
                var intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                intent.setPackage("com.google.android.apps.maps")
                startActivity(intent)
            } catch (e: Exception) {

                Toast.makeText(context, "${e.localizedMessage}", Toast.LENGTH_SHORT).show()
            }


        }

    }

}