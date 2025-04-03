package tl209.brain_bai10_singleton.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tl209.brain_bai10_singleton.data.RemoteDataSource
import tl209.brain_bai10_singleton.data.model.Student

class StudentDetailViewModel: ViewModel() {
    private val _student = MutableLiveData<Student>()
    val student: LiveData<Student> = _student

    fun loadStudent(studentId: String){
        val dataSource = RemoteDataSource.instance
        val student = dataSource?.getStudentById(studentId)
        student?.let {
            _student.value = it
        }
    }
}