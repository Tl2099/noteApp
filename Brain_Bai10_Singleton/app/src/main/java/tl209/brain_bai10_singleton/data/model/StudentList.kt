package tl209.brain_bai10_singleton.data.model

import com.fasterxml.jackson.annotation.JsonProperty

data class StudentList (
    @JsonProperty("students") val students: List<Student>
)