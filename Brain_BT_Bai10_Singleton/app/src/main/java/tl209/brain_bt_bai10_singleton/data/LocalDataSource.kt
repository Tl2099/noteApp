package tl209.brain_bt_bai10_singleton.data

import android.content.res.Resources
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import tl209.brain_bt_bai10_singleton.R
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Reader
import java.io.StringWriter
import java.io.Writer
import java.nio.charset.StandardCharsets

class LocalDataSource private constructor(
    private val resources: Resources
){
    private val countries: MutableList<Country> = mutableListOf()

    private fun getJSONFromFile(): String {
        val writer: Writer = StringWriter()
        val buffer = CharArray(1024)
        try {
            resources.openRawResource(R.raw.country).use { inputStream ->
                val reader: Reader = BufferedReader(
                    InputStreamReader(inputStream, StandardCharsets.UTF_8)
                )
                var size: Int
                while ((reader.read(buffer)))
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun getCountries(): List<Country>{
        val mapper = jacksonObjectMapper()
        val jsonString = getJSONFromFile()
        val data = try {
            mapper.readValue<List<Country>>(jsonString)
        }
    }
}