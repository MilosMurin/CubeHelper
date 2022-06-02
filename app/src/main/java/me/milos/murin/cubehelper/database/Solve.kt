package me.milos.murin.cubehelper.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "past_solves_table")
data class Solve(
    @PrimaryKey(autoGenerate = true)
    var solveId: Long = 0L,
    @ColumnInfo(name = "solve_time")
    var solveTime: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "date_of_solve")
    var dateTime: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "solved_scramble")
    var scramble: String = ""
)
