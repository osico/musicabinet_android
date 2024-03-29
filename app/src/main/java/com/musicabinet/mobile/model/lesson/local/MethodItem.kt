package com.musicabinet.mobile.model.lesson.local

import com.musicabinet.mobile.model.lesson.remote.StoredFile

/**
 * @author Kirchhoff-
 */
data class MethodItem(var description: String?, val information: String?, var name: String?,
                      val video: StoredFile?, val page: Int)