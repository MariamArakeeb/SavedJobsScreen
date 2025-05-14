package com.example.savedjobs.data

import androidx.room.*
import com.example.savedjobs.data.JobEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface JobDao {
    @Query("SELECT * FROM saved_jobs")
    fun getAllJobs(): Flow<List<JobEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJob(job: JobEntity)

    @Delete
    suspend fun deleteJob(job: JobEntity) : Int

    @Query("DELETE FROM saved_jobs")
    suspend fun clearAll()
}

