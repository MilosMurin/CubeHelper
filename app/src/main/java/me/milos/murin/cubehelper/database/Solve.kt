package me.milos.murin.cubehelper.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Trieda reprezentujuca jeden cas poskladania kocky
 * @property solveId poradie poskladania
 * @property solveTime trvanie poskladania v milisekundach
 * @property dateTime datum a cas poskladania v milisekundach
 * @property scramble kroky podla ktorych bola rozlozena kocka pri poskladani
 */
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
