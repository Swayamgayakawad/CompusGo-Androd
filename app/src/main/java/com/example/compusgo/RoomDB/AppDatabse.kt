package RoomDB

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [StudentDB::class], version = 1, exportSchema = false)
abstract class AppDatabse : RoomDatabase() {
    abstract fun studentDao(): StudentDao
}