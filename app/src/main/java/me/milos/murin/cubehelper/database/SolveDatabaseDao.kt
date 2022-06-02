package me.milos.murin.cubehelper.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SolveDatabaseDao {

    @Insert
    suspend fun insert(solve: Solve)

    @Update
    suspend fun update(solve: Solve)

    @Delete
    suspend fun delete(solve: Solve)

    @Query("SELECT * from past_solves_table order by solve_time")
    suspend fun getSorted(): List<Solve>

    @Query("select * from past_solves_table where solveId = :id")
    suspend fun getSolve(id: Long): Solve?

    @Query("select solve_time from past_solves_table order by solveId desc limit :amount")
    suspend fun getAverageOf(amount: Int): List<Long>

    @Query("select solve_time from past_solves_table order by solve_time limit 1")
    suspend fun getBestSolve(): Long?

    @Query("select solve_time from past_solves_table order by solve_time desc limit 1")
    suspend fun getWorstSolve(): Long?

    @Query("select count(*) from past_solves_table")
    suspend fun getNumOfSolves(): Long?

    @Query("select solve_time from past_solves_table order by solveId desc limit 5")
    suspend fun getCompAverage(): List<Long>
}