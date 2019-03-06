package com.omegajak.artisanapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_creation_details.*

class CreationDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creation_details)

        val creationData = intent.getSerializableExtra(EXTRA_CREATION) as CreationData

        detailsCreationName.text = creationData.name
        detailsDescription.text = creationData.fullDescription
    }
}
