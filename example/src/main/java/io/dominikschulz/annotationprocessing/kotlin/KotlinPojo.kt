package io.dominikschulz.annotationprocessing.kotlin

import io.dominikschulz.slimorm.Field


data class KotlinPojo(@set:Field("_ID") @get:Field("_ID") var id : Long,
                      @set:Field("name") @get:Field("name") var name : String,
                      @set:Field("optional") @get:Field("optional") var optionalLong : Long?) {
    constructor() : this(0L,"", null)
}