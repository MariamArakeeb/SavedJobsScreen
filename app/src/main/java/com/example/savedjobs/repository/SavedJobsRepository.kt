package com.example.savedjobs.repository

//import com.example.savedjobs.data.JobDao
//import com.example.savedjobs.data.JobEntity
//
//class JobRepository(private val jobDao: JobDao) {
//    suspend fun getAllJobs(): List<JobEntity> = jobDao.getAllJobs()
//    suspend fun saveJob(job: JobEntity) = jobDao.insertJob(job)
//    suspend fun removeJob(job: JobEntity) = jobDao.deleteJob(job)
//}
import com.example.savedjobs.data.JobDao
import com.example.savedjobs.data.JobEntity
import kotlinx.coroutines.flow.Flow

class JobRepository(private val jobDao: JobDao) {
    val allJobs: Flow<List<JobEntity>> = jobDao.getAllJobs()

    suspend fun insert(job: JobEntity) {
        jobDao.insertJob(job)
    }

    suspend fun delete(job: JobEntity) {
        jobDao.deleteJob(job)
    }

    suspend fun clearAll() {
        jobDao.clearAll()
    }
}
