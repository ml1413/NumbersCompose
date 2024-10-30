package com.my.numberscompose.data.storage.DB

import com.my.numberscompose.domain.model.ModelNumber

fun NumberEntity.mapToModel() = ModelNumber(info = text, number = number)
