package com.bus.buswaiting.ui.route

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.bus.buswaiting.R
import com.bus.buswaiting.tool.GlideTool
import com.bus.buswaiting.tool.Tool.toDp


class RouteImageDialog(private val imageUrl: String) : DialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        val view: View = inflater.inflate(R.layout.item_route_image_layout, null)
        initView(view)
        val dialog = Dialog(requireActivity())
        // 关闭标题栏，setContentView() 之前调用
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(view)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val window = dialog.window
        val wlp = window!!.attributes
        wlp.width = 340.toDp()
        wlp.height = 340.toDp()
        window.attributes = wlp
        return dialog
    }

    private fun initView(view: View) {
        val imageView = view.findViewById<ImageView>(R.id.image)
        GlideTool.showImage(imageUrl, imageView)
    }

}