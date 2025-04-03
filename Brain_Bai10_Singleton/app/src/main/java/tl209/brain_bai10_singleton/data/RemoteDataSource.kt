package tl209.brain_bai10_singleton.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tl209.brain_bai10_singleton.data.model.Student

class RemoteDataSource private constructor() {
    private val students = mutableListOf<Student>()

    suspend fun loadStudents(): List<Student> {
        return withContext(Dispatchers.IO) {
            val service = RetrofitHelper.instance
            val response = service.getStudents()
            if (response.isSuccessful) {
                response.body()?.let {
                    students.clear()
                    students.addAll(it.students)
                }
            }
            students
        }
    }

    fun getStudentById(studentId: String): Student? {
        return students.find { it.id == studentId }
    }

    companion object {
        @get:Synchronized
        var instance: RemoteDataSource? = null
            get() {
                if (field == null) {
                    field = RemoteDataSource()
                }
                return field
            }
            private set
    }
}