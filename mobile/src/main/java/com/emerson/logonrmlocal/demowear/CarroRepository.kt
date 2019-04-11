package com.emerson.logonrmlocal.demowear

import android.content.Context
import com.emerson.logonrmlocal.shared.Carro
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader

object CarroRepository {

    fun buscarTodos(context: Context): MutableList<Carro> {
        val stream = context.resources.openRawResource(R.raw.carros)
        val reader = BufferedReader(InputStreamReader(stream, "UTF-8"))
        val listType = object : TypeToken<ArrayList<Carro>>() {}.type
        return Gson().fromJson(reader, listType)
    }
}