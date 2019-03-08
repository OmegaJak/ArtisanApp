package com.omegajak.artisanapp

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import kotlinx.android.synthetic.main.arcanism_card_description.view.*
import kotlinx.android.synthetic.main.creation_card.view.*
import kotlinx.android.synthetic.main.non_arcanism_card_description.view.*

const val EXTRA_CREATION = "com.omegajak.artisanapp.CREATION"

class CreationAdapter(private val context: Context) : RecyclerView.Adapter<CreationAdapter.CreationViewHolder>() {
    init {
        DailyCreationManager.onDailyCreationAdded = {
            notifyItemInserted(DailyCreationManager.dailyCreations.size - 1)
        }

        DailyCreationManager.onDailyCreationRemoved = { position ->
            notifyItemRemoved(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreationViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.creation_card, parent, false)
        return CreationViewHolder(view)
    }

    override fun onBindViewHolder(holder: CreationViewHolder, position: Int) {
        holder.setCreation(DailyCreationManager.dailyCreations[position])

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
                putExtra(EXTRA_CREATION, DailyCreationManager.dailyCreations[position].creationData)
            }
            startActivity(context, intent, null)
        }

        val typeSpecificData = DailyCreationManager.dailyCreations[position].creationData.typeSpecificData
        when (typeSpecificData) {
            is ArcanismData -> {
                val spinner = holder.itemView.spellSelector.arcansimSpellSpinner
                ArrayAdapter<String>(
                        context,
                        R.layout.support_simple_spinner_dropdown_item,
                        typeSpecificData.validSpells.toTypedArray()
                ).also { adapter ->
                    adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
                    spinner.adapter = adapter
                }
                spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        Log.d(TAG, "onNothingSelected")
                    }

                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        Log.d(TAG, "onItemSelected: " + typeSpecificData.validSpells[position])
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return DailyCreationManager.numDistinctDailyCreations()
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
                creation.decrementNumMade()
            }
        }

        val currentNumCreationsTextView = itemView.currentNumCreations
        val incrementButton = itemView.incrementCreations
        init {
            incrementButton.setOnClickListener {
                creation.incrementNumMade()
            }
        }

        private lateinit var creation: DailyCreation

        fun setCreation(creation: DailyCreation) {
            this.creation = creation
            creation.onNumMadeChanged = { updateNumCreations()}

            name.text = creation.creationData.name
            characteristic.text = creation.creationData.characteristic
            brief.text = creation.creationData.briefDescription
            perCreationCost.text = creation.artistryPointCost.toString()

            if (creation.creationData.typeSpecificData.creationType == TypeSpecificData.CreationType.Arcanism) {
                itemView.spellSelector.visibility = View.VISIBLE
                itemView.creationDescriptions.visibility = View.GONE
            } else {
                itemView.creationDescriptions.visibility = View.VISIBLE
                itemView.spellSelector.visibility = View.GONE
            }

            updateNumCreations()
        }

        private fun updateNumCreations() {
            currentNumCreationsTextView.text = creation.numMade.toString()
            totalCreationCost.text = creation.getTotalCost().toString()
        }
    }
}