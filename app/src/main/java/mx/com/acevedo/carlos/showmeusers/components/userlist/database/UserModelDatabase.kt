package mx.com.acevedo.carlos.showmeusers.components.userlist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import mx.com.acevedo.carlos.showmeusers.components.userlist.models.UserDbModel

/**
 * This is where [UserModelDatabase] singleton is instantiated
 */
@Database(entities = [UserDbModel::class], version = 1)
abstract class UserModelDatabase : RoomDatabase() {

    abstract fun userModelDao(): UserModelDao

    companion object {

        /**
         * Name of file database [SHEETS_DB]
         */
        private const val SHEETS_DB = "UsersModelDatabase.db"

        @Volatile
        private var INSTANCE: UserModelDatabase? = null

        fun getInstance(context: Context): UserModelDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                UserModelDatabase::class.java, SHEETS_DB
            )
                .fallbackToDestructiveMigration()
                .build()
    }
}