package tl209.brain_bai10_singleton.ui.detail

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import tl209.brain_bai10_singleton.R
import tl209.brain_bai10_singleton.data.model.Student
import tl209.brain_bai10_singleton.utils.Utils

class StudentDetailActivity: AppCompatActivity() {
    private lateinit var imageAvatar: ImageView
    private lateinit var textId: TextView
    private lateinit var textFullName: TextView
    private lateinit var textGpa: TextView
    private lateinit var textGender: TextView
    private lateinit var textEmail: TextView
    private lateinit var textBirthDate: TextView
    private lateinit var textAddress: TextView
    private lateinit var textMajor: TextView
    private lateinit var textYear: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)){v, insets ->
            val systemDars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemDars.left, systemDars.top, systemDars.right, systemDars.bottom)
            insets
        }
        setupView()
        setupViewModel()
    }

    private fun setupViewModel() {
        val viewmodel = ViewModelProvider(this)[StudentDetailViewModel::class.java]
        val id = intent.getStringExtra(EXTRA_STUDENT_ID)
        id?.let {
            viewmodel.loadStudent(it)
        }
        viewmodel.student.observe(this){studen ->
            showStudentDetail(studen)
        }
    }

    private fun showStudentDetail(student: Student){
        textId.text = getString(R.string.id,student.id)
        textFullName.text = getString(R.string.full_name, student.fullName.toString())
        textGpa.text = getString(R.string.gpa, student.gpa)
        textYear.text = getString(R.string.year, student.year)
        textGender.text = getString(R.string.gender, student.gender)
        textEmail.text = getString(R.string.email, student.email)
        textBirthDate.text = getString(R.string.birth_date, Utils.dateToString(student.birthDate))
        textAddress.text = getString(R.string.address, student.address)
        textMajor.text = getString(R.string.major, student.major)
        imageAvatar.setImageResource(Utils.getAvatar(student.gender))
    }

    private fun setupView() {
        imageAvatar = findViewById(R.id.image_detail_avatar)
        textId = findViewById(R.id.text_detail_id)
        textFullName = findViewById(R.id.text_detail_full_name)
        textGpa = findViewById(R.id.text_detail_gpa)
        textGender = findViewById(R.id.text_detail_gender)
        textEmail = findViewById(R.id.text_detail_email)
        textBirthDate = findViewById(R.id.text_detail_birth_date)
        textAddress = findViewById(R.id.text_detail_address)
        textMajor = findViewById(R.id.text_detail_major)
        textYear = findViewById(R.id.text_detail_year)
  }

    companion object{
        const val EXTRA_STUDENT_ID = "student_id"
    }
}