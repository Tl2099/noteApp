package tl209.brain_bai10_singleton.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tl209.brain_bai10_singleton.data.RemoteDataSource
import tl209.brain_bai10_singleton.data.model.Student

class StudentViewModel : ViewModel() {
    private val _students = MutableLiveData<List<Student>>()
    val student: LiveData<List<Student>> = _students

    fun loadStudents() {
        val dataSource = RemoteDataSource.instance
        dataSource?.let {
            viewModelScope.launch(Dispatchers.IO) {
                val studentList = dataSource.loadStudents()
                _students.postValue(studentList)
            }
        }
    }
}