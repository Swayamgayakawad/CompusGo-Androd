package RoomDB
import androidx.room.Insert
import androidx.room.Dao
import androidx.room.Query

@Dao
interface StudentDao {
    @Insert
    suspend fun insertStudent(student: StudentDB)
    @Query("SELECT * FROM student_table WHERE USN = :usn")
    suspend fun getStudentByUSN(usn: String): StudentDB?
    @Query("SELECT * FROM student_table LIMIT 1")
    suspend fun getFirstStudent(): StudentDB?

}
