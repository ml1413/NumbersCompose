package com.my.numberscompose.domain.model

import com.my.numberscompose.data.storage.DB.NumberEntity

fun ModelNumber.mapToEntity() = NumberEntity(text = info, number = number)
