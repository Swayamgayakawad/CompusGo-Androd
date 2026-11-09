package RoomDB


class StudentRepository(
    private val studentDao: StudentDao
) {

    suspend fun insertStudent(student: StudentDB) {
        studentDao.insertStudent(student)
    }

    suspend fun getStudentByUSN(usn: String): StudentDB? {
        return studentDao.getStudentByUSN(usn)
    }
    suspend fun checkUser(): StudentDB? {
        return studentDao.getFirstStudent()
    }
}
