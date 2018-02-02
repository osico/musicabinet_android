package com.musicabinet.mobile.model.lesson.machine

/**
 * @author Kirchhoff-
 */
data class FileDataItem(var tone: String?, var chord: String?,
                        var noteInformation: String?, val tag: String) : Comparable<FileDataItem> {

    override fun compareTo(other: FileDataItem): Int {
        val leftRow = tag.substring(0, tag.length - 1).toInt()
        val rightRow = other.tag.substring(0, other.tag.length - 1).toInt()

        return leftRow - rightRow
    }
}