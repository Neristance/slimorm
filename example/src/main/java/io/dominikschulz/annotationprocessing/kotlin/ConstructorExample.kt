package io.dominikschulz.annotationprocessing.kotlin

import io.dominikschulz.slimorm.ColumnName
import io.dominikschulz.slimorm.Field
import io.dominikschulz.slimorm.PojoCreator


data class ConstructorExample @PojoCreator constructor(@ColumnName("_ID")  @get:Field("_ID") var id : Long,
                                                       @ColumnName("name") @get:Field("name") var name : String,
                                                       @ColumnName("optional") @get:Field("optional") var optionalLong : Long?,
                                                       @get:Field("anotherColumn") val anotherColumn :  String) {
}