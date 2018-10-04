package pro.ahoora.zhin.om

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import pro.ahoora.zhin.om.adapters.ColorAdapter
import pro.ahoora.zhin.om.utils.Converter


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener, ViewTreeObserver.OnGlobalLayoutListener {


    override fun onGlobalLayout() {
        width = drawerLayout.measuredWidth
        drawerLayout.viewTreeObserver.removeOnGlobalLayoutListener(this)
    }

    override fun onDrawerStateChanged(newState: Int) {
    }

    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
        cl_container.translationX = slideOffset * width
    }

    override fun onDrawerClosed(drawerView: View) {
    }

    override fun onDrawerOpened(drawerView: View) {
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {

        return true
    }


    var width = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        width = Converter.getScreenWidthPx(this) - 300

        //drawerLayout.viewTreeObserver.addOnGlobalLayoutListener(this)

        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)

        nav_view.setNavigationItemSelectedListener(this)
        setNavMenuItemThemeColors(ContextCompat.getColor(this, R.color.blue))
        nav_view.menu.getItem(0).isChecked = true

        drawerLayout.addDrawerListener(this)
        drawerLayout.setScrimColor(ContextCompat.getColor(this, R.color.transparent))
        drawerLayout.drawerElevation = 0f

        val toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name)
        toggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            nav_view.elevation = 0f
        }

        setColorAdapter()


        mb_add.setOnClickListener {
            startActivity(Intent(this, PaintActivity::class.java))
        }

    }

    private fun setColorAdapter() {
        rv_colors.layoutManager = LinearLayoutManager(this)
        rv_colors.adapter = ColorAdapter(this).apply {
            setOnClickListener(object : ColorAdapter.OnClickListener {
                override fun onClick(action: Int, color: Int) {
                    when (action) {
                        /**
                         * 0 -> search background
                         * 1 -> search text
                         * 2 -> toolbar background
                         * 3-> toolbar text
                         * 4 -> background
                         * 5-> navigation background
                         * 6 -> text color
                         */
                        0 -> {
                            et_search.setBackgroundColor(color)
                        }
                        1 -> {
                            et_search.setTextColor(color)
                        }
                        2 -> {
                            toolbar.setBackgroundColor(color)
                        }
                        3 -> {
                            toolbar.setTitleTextColor(color)
                        }
                        4 -> {
                            rv_colors.setBackgroundColor(color)
                        }
                        5 -> {
                            nav_view.setBackgroundColor(color)
                        }
                        6 -> {
                            tv_text.setTextColor(color)
                        }
                        7 -> {
                            view_line1.setBackgroundColor(color)
                            view_line2.setBackgroundColor(color)
                            view_line3.setBackgroundColor(color)
                        }
                        8 -> {
                            rl_bottom.setBackgroundColor(color)
                        }
                    }

                }
            })
        }
    }


    private fun setNavMenuItemThemeColors(color: Int) {
        //Setting default colors for menu item Text and Icon
        val navDefaultTextColor = ContextCompat.getColor(this, R.color.dynamicAndTitleTextColor)
        val navDefaultIconColor = ContextCompat.getColor(this, R.color.staticTextColor)

        //Defining ColorStateList for menu item Text
        val navMenuTextList = ColorStateList(
                arrayOf(intArrayOf(android.R.attr.state_checked),
                        intArrayOf(android.R.attr.state_enabled),
                        intArrayOf(android.R.attr.state_pressed),
                        intArrayOf(android.R.attr.state_focused),
                        intArrayOf(android.R.attr.state_pressed)),
                intArrayOf(color,
                        navDefaultTextColor,
                        navDefaultTextColor,
                        navDefaultTextColor,
                        navDefaultTextColor)
        )

        //Defining ColorStateList for menu item Icon
        val navMenuIconList = ColorStateList(

                arrayOf(intArrayOf(android.R.attr.state_checked),
                        intArrayOf(android.R.attr.state_enabled),
                        intArrayOf(android.R.attr.state_pressed),
                        intArrayOf(android.R.attr.state_focused),
                        intArrayOf(android.R.attr.state_pressed)
                ),

                intArrayOf(color,
                        navDefaultIconColor,
                        navDefaultIconColor,
                        navDefaultIconColor,
                        navDefaultIconColor))

        nav_view.setItemTextColor(navMenuTextList)
        nav_view.setItemIconTintList(navMenuIconList)

    }


}
