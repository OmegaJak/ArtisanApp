package com.omegajak.artisanapp

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.view.View
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.creation_card.*

public val TAG: String = "CustomArtisanMessage"

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->

            //DailyCreationManager.addCreation(DailyCreation(CreationDataManager.creationDataCollection[0], 10, 1))
            //DailyCreationManager.addCreation(DailyCreation(CreationDataManager.creationDataCollection[2], 3, 2))

            if (DailyCreationManager.isChoosing) {
                DailyCreationManager.toggleChoosingMode()

                Snackbar.make(view, "Choosing mode: ${DailyCreationManager.isChoosing}", Snackbar.LENGTH_LONG).setAction("Action", null).show()
            } else {
                val builder: AlertDialog.Builder? = this.let {
                    AlertDialog.Builder(it)
                }

                builder?.setMessage("longRestConfirm")
                        ?.setTitle("Are you sure?")
                        ?.setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
                            DailyCreationManager.toggleChoosingMode()

                            Snackbar.make(view, "Choosing mode: ${DailyCreationManager.isChoosing}", Snackbar.LENGTH_LONG).setAction("Action", null).show()
                        }
                        ?.setNegativeButton("No", null)

                val dialog: AlertDialog? = builder?.create()
                dialog?.show()
            }

        }

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)

        DailyCreationManager.onTotalCreationsCostUpdated = {
            allCreationsCost.text = it.toString()
        }
        DailyCreationManager.load()

        val recyclerView = findViewById<RecyclerView>(R.id.creationsRecyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val creationAdapter = CreationAdapter(this)
        recyclerView.adapter = creationAdapter
    }

    override fun onBackPressed() {
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        }

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    fun incrementCreation(view: View) {
        changeCurrentCreationCountBy(1)
    }

    fun decrementCreation(view: View) {
        changeCurrentCreationCountBy(-1)
    }

    fun changeCurrentCreationCountBy(amount: Int) {
        val view = findViewById<TextView>(R.id.currentNumCreations)
        val previousNumCreations = view.text.toString().toInt()
        view.text = (previousNumCreations + amount).toString()
    }
}
