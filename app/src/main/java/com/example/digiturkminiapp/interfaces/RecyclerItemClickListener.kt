package com.example.digiturkminiapp.interfaces

import android.view.View
import java.text.FieldPosition

interface RecyclerItemClickListener {
    fun onItemClick(view: View, position: Int)
}