package com.musicabinet.mobile.model.lesson.machine.diagram

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class DiagramImageResponse(@SerializedName("elements") val elements: HashMap<String, DiagramImageItem>)