package com.example.savedjobs.viewmodel

//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.savedjobs.data.JobEntity
//import com.example.savedjobs.repository.JobRepository
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//
//class JobViewModel(private val repository: JobRepository) : ViewModel() {
//
//    private val _savedJobs = MutableStateFlow<List<JobEntity>>(emptyList())
//    val savedJobs: StateFlow<List<JobEntity>> = _savedJobs
//
//    fun loadJobs() {
//        viewModelScope.launch {
//            _savedJobs.value = repository.getAllJobs()
//        }
//    }
//
//    fun toggleSave(job: JobEntity) {
//        viewModelScope.launch {
//            if (_savedJobs.value.contains(job)) {
//                repository.removeJob(job)
//            } else {
//                repository.saveJob(job)
//            }
//            loadJobs()
//        }
//    }
//}
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.savedjobs.data.JobDatabase
import com.example.savedjobs.data.JobEntity
import com.example.savedjobs.repository.JobRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class JobViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: JobRepository

    val jobs = JobDatabase.getDatabase(application).jobDao().getAllJobs()
        .map { it }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        val dao = JobDatabase.getDatabase(application).jobDao()
        repository = JobRepository(dao)
    }

    fun saveJob(job: JobEntity) {
        viewModelScope.launch {
            repository.insert(job)
        }
    }

    fun removeJob(job: JobEntity) {
        viewModelScope.launch {
            repository.delete(job)
        }
    }

    fun clearAll() {
        viewModelScope.launch {
            repository.clearAll()
        }
    }
}

