package com.musicabinet.mobile.ui.cabinet

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.musicabinet.mobile.R
import com.musicabinet.mobile.ui.slidemenu.SlideMenuActivity
import kotlinx.android.synthetic.main.activity_cabinet.*
import org.jetbrains.anko.toast

/**
 * @author Kirchhoff-
 */
class CabinetActivity : SlideMenuActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewPager.adapter = CabinetAdapter(this)
        tabLayout.setupWithViewPager(viewPager)

        tvSignIn.setOnClickListener {
            toast("Move to SignUp Screen")
        }
    }

    override fun inflateLayout(): View {
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        return inflater.inflate(R.layout.activity_cabinet, null, false)
    }

}