package com.example.pgm7

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.slidingpanelayout.widget.SlidingPaneLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val slidingPaneLayout = findViewById<SlidingPaneLayout>(R.id.sliding_pane_layout)
        ViewCompat.setOnApplyWindowInsetsListener(slidingPaneLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val listView = findViewById<ListView>(R.id.list_view)
        val detailTitle = findViewById<TextView>(R.id.detail_title)
        val detailImage = findViewById<ImageView>(R.id.detail_image)
        val detailDescription = findViewById<TextView>(R.id.detail_description)

        val shapesList = listOf(
            ShapeItem(
                "Circle",
                R.drawable.ic_circle,
                "A circle is a perfectly round shape with no corners or edges. In geometry, it is defined as the set of all points in a plane that are at a given distance from a central point."
            ),
            ShapeItem(
                "Square",
                R.drawable.ic_square,
                "A square is a regular quadrilateral with four equal sides and four equal 90-degree angles. It possesses high symmetry and is a fundamental shape in architecture and design."
            ),
            ShapeItem(
                "Star",
                R.drawable.ic_star,
                "A star polygon is a self-intersecting, star-shaped polygon. This geometric figure features five pointed corners, commonly used in flags, symbology, and stellar graphics."
            ),
            ShapeItem(
                "Heart",
                R.drawable.ic_heart,
                "A heart shape is a classic mathematical cardioid curve, long utilized as a symbol of affection, emotion, and love across global cultures and UI iconography."
            ),
            ShapeItem(
                "Triangle",
                R.drawable.ic_triangle,
                "A triangle is a polygon with three edges and three vertices. It is the simplest possible polygon and forms the structural bedrock of modern engineering and trigonometry."
            )
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_activated_1,
            shapesList.map { it.name }
        )
        listView.adapter = adapter
        listView.choiceMode = ListView.CHOICE_MODE_SINGLE

        fun updateDetails(item: ShapeItem) {
            detailTitle.text = item.name
            detailImage.setImageResource(item.drawableResId)
            detailDescription.text = item.description
        }

        // Set default item selection on launch
        if (shapesList.isNotEmpty()) {
            listView.setItemChecked(0, true)
            updateDetails(shapesList[0])
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedShape = shapesList[position]
            updateDetails(selectedShape)
            slidingPaneLayout.openPane()
        }

        // Handle adaptive system back navigation
        val onBackPressedCallback = object : OnBackPressedCallback(
            slidingPaneLayout.isSlideable && slidingPaneLayout.isOpen
        ), SlidingPaneLayout.PanelSlideListener {

            init {
                slidingPaneLayout.addPanelSlideListener(this)
            }

            override fun handleOnBackPressed() {
                slidingPaneLayout.closePane()
            }

            override fun onPanelSlide(panel: View, slideOffset: Float) {}

            override fun onPanelOpened(panel: View) {
                isEnabled = true
            }

            override fun onPanelClosed(panel: View) {
                isEnabled = false
            }
        }

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }
}
