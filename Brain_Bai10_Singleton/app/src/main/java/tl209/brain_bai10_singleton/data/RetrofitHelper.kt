package tl209.brain_bai10_singleton.data

import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import tl209.brain_bai10_singleton.data.model.StudentService

object RetrofitHelper {
    private const val BASE_URL = "https://thantrieu.com/"
    //Tao ra 1 cai instance noi nao dung StudentService thi
    //goi RetrofitHelper.instance sau do goi den cac method can thiet
    val instance: StudentService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
            .create(StudentService::class.java)
    }
}