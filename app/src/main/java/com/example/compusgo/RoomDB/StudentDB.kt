package RoomDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
data class StudentDB (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var USN: String = "",
    val Branch: String = "",
    val Year: Int = 0,
    val seralNo: Int = 0,
    val Semester: Int = 0,
    var Name: String = "",
    var Phone: String = "",
    var password: String = ""
)