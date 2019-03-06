package com.omegajak.artisanapp

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.creation_card.view.*

const val EXTRA_CREATION = "com.omegajak.artisanapp.CREATION"

class CreationAdapter(private val context: Context) : RecyclerView.Adapter<CreationAdapter.CreationViewHolder>() {
    var dailyCreationList: ArrayList<DailyCreation> = ArrayList<DailyCreation>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreationViewHolder {
        dailyCreationList.add(DailyCreation(CreationDataManager.creationDataCollection[0], 1, 4))

        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.creation_card, parent, false)
        return CreationViewHolder(view)
    }

    override fun onBindViewHolder(holder: CreationViewHolder, position: Int) {
        holder.setCreation(dailyCreationList[0])

        holder.itemView.cardView.setOnClickListener {
//            val builder: AlertDialog.Builder? = context?.let {
//                AlertDialog.Builder(it)
//            }
//
//            builder?.setMessage("Test Dialogueususususu")
//                    ?.setTitle("Test Title2")
//
//            val dialog = builder?.create()
//            dialog?.show()

            val intent = Intent(context, CreationDetailsActivity::class.java).apply {
                putExtra(EXTRA_CREATION, dailyCreationList[0].creationData)
            }
            startActivity(context, intent, null)
        }
    }

    override fun getItemCount(): Int {
        return 20
    }

    class CreationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.creationName
        val characteristic = itemView.creationCharacteristic
        val brief = itemView.creationBrief

        val perCreationCost = itemView.perCreationCost
        val totalCreationCost = itemView.totalCreationCost

        val decrementButton = itemView.decrementCreations
        init {
            decrementButton.setOnClickListener {
                if (currentNumCreations > 0) currentNumCreations--
            }
        }

        var currentNumCreations = 1
            set(value) {
                field = value
                updateNumCreations()
            }

        val currentNumCreationsTextView = itemView.currentNumCreations
        val incrementButton = itemView.incrementCreations
        init {
            incrementButton.setOnClickListener {
                currentNumCreations++
            }
        }

        private var creation: DailyCreation? = null

        fun setCreation(creation: DailyCreation) {
            this.creation = creation

            name.text = creation.creationData.name
            characteristic.text = creation.creationData.characteristic
            brief.text = creation.creationData.briefDescription
            perCreationCost.text = creation.artistryPointCost.toString()
        }

        private fun updateNumCreations() {
            currentNumCreationsTextView.text = currentNumCreations.toString()
            totalCreationCost.text = (currentNumCreations * creation!!.artistryPointCost).toString()
        }
    }
}