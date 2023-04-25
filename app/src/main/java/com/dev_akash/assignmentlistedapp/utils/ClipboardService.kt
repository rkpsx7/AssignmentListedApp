package com.dev_akash.assignmentlistedapp.utils

import android.content.ClipData
import android.content.Context
import android.widget.Toast

object ClipboardService {
    fun copyToClipboard(context: Context, text: String) {
        val clipboard =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
        val clip = ClipData.newPlainText("Copied Text", text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, "Link Copied!", Toast.LENGTH_SHORT).show()
    }
}