package RoomDB

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    @Volatile
    private var INSTANCE: AppDatabse? = null

    fun getDatabase(context: Context): AppDatabse {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabse::class.java,
                "campusgo_database"
            ).build()

            INSTANCE = instance
            instance
        }
    }
}