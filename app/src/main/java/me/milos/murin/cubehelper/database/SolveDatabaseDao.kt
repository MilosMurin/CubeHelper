package me.milos.murin.cubehelper.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 * Trieda obsahujuca metody na pracu s databazou poskladani
 */
@Dao
interface SolveDatabaseDao {

    /**
     * Vlozi dane poskladanie do databazy
     */
    @Insert
    suspend fun insert(solve: Solve)

    /**
     * Vymaze dane poskladanie z databazy
     */
    @Delete
    suspend fun delete(solve: Solve)

    /**
     * Vrati zoznam vsetkych poskladani zoradeny podla trvania poskladania
     */
    @Query("SELECT * from past_solves_table order by solve_time")
    suspend fun getSorted(): List<Solve>

    /**
     * Vrati poslednych [amount] poskladani
     */
    @Query("select solve_time from past_solves_table order by solveId desc limit :amount")
    suspend fun getAverageOf(amount: Int): List<Long>

    /**
     * Vrati najlepsie poskladanie podla trvania poskladania
     */
    @Query("select solve_time from past_solves_table order by solve_time limit 1")
    suspend fun getBestSolve(): Long?

    /**
     * Vrati najhorsie poskladanie podla trvania poskladania
     */
    @Query("select solve_time from past_solves_table order by solve_time desc limit 1")
    suspend fun getWorstSolve(): Long?

    /**
     * Vrati pocet vsetkych poskladani
     */
    @Query("select count(*) from past_solves_table")
    suspend fun getNumOfSolves(): Long?

    /**
     * Vrati zoznam poslednych 5 poskladani na vypocitanie preimeru na sutazi
     */
    @Query("select solve_time from past_solves_table order by solveId desc limit 5")
    suspend fun getCompAverage(): List<Long>
}