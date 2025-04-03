package tl209.brain_bai10_singleton.data.model

import retrofit2.Response
import retrofit2.http.GET

interface StudentService {
    @GET("resources/braniumapis/student.json")
    suspend fun getStudents(): Response<StudentList>
}

