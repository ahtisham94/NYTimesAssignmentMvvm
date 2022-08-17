package com.example.assignment.extensions

import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.DialogFragmentNavigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide

fun ImageView.loadImage(type: Any) {
    Glide.with(this.context)
        .load(type)
        .centerCrop()
        .into(this)
}

fun View.setOnSingleClickListener(block: () -> Unit) {
    setOnClickListener(OnSingleClickListener(block))
}

fun Fragment.safeNavigateFromNavController(directions: NavDirections) {
    try {
        val navController = findNavController()
        when (val destination = navController.currentDestination) {
            is FragmentNavigator.Destination -> {
                if (javaClass.name == destination.className) {
                    navController.navigate(directions)
                }
            }
            is DialogFragmentNavigator.Destination -> {
                if (javaClass.name == destination.className) {
                    navController.navigate(directions)
                }
            }
        }

    } catch (e: Exception) {

    }

}

