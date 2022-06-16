package me.milos.murin.cubehelper.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Trieda reprezentujuca databazu poskladani
 */
@Database(entities = [Solve::class], version = 1, exportSchema = false)
abstract class SolveDatabase : RoomDatabase() {

    abstract val solveDatabaseDao: SolveDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: SolveDatabase? = null

        /**
         * Vrati jedinu instanciu databazy, ak nie je tak ju vytvori
         */
        fun getInstance(context: Context): SolveDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, SolveDatabase::class.java,
                        "solve_history_database").fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}