package com.my.numberscompose.data.model

import com.my.numberscompose.domain.model.ModelNumber

fun ModelNumberFromRetrofit.mapToModel() = ModelNumber(info = infoAboutNumber, number = number)
