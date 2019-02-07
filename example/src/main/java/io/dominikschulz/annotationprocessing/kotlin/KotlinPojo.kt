package io.dominikschulz.annotationprocessing.kotlin

import io.dominikschulz.slimorm.Field


data class KotlinPojo(@set:Field(columnName = "_ID") @get:Field(columnName = "_ID") var id : Long,
                      @set:Field(columnName = "name") @get:Field(columnName = "name") var name : String,
                      @set:Field(columnName = "optional") @get:Field(columnName = "optional") var optionalLong : Long?) {
    constructor() : this(0L,"", null)
}