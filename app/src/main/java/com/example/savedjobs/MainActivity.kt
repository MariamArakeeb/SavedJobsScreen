package com.example.savedjobs


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.ColorRes
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.res.vectorResource



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SavedJobsScreen()
        }
    }
}

data class Job(
    val title: String,
    val Company: String,
    val Location: String,
    val Salary: String,
    var isSaved: Boolean = true
)

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun SavedJobsScreen() {
    val jobList = remember {
        mutableStateListOf(
            Job("Software Engineer", "Google", "Mountain View, CA", "$120K"),
            Job("Product Manager", "Facebook", "Menlo Park, CA", "$140K"),
            Job("UX Designer", "Creative Studio", "San Francisco, CA", "$130K"),
            Job("UX Designer", "Creative Studio", "San Francisco, CA", "$130K"),
            Job("UX Designer", "Creative Studio", "San Francisco, CA", "$130K"),
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            "Saved Jobs", fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(end = 48.dp), color = Color.White
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Back action */ }) {
                        Icon(
                            Icons.Default.ArrowBack, contentDescription = "Back",
                            modifier = Modifier.size(28.dp), tint = Color.White
                        )
                    }
                },
                actions = {
                    var expanded by remember { mutableStateOf(false) }

                    Box {
                        IconButton(onClick = { expanded = true }) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "More options",
                                tint = Color.White
                            )
                        }

                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            DropdownMenuItem(
                                onClick = {
                                    expanded = false
                                    jobList.clear()
                                },
                                text = { Text("Clear All") }
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.teal_700),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        },
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                SavedJobsContent(jobList = jobList)
            }
        },
    )
}

@Composable
fun SavedJobsContent(jobList: SnapshotStateList<Job>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(
                        rememberScrollState(),
                        flingBehavior = ScrollableDefaults.flingBehavior()
                    )
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                jobList.forEachIndexed { index, job ->
                    InfoCard(
                        job = job,
                        onRemoveJob = {
                            jobList.removeAt(index)
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun InfoCard(job: Job, onRemoveJob: (Job) -> Unit) {
    val isSaved = remember { mutableStateOf(job.isSaved) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(2.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.work),
                        contentDescription = null,
                        tint = colorResource(id = R.color.orange),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(7.dp))
                    Text(
                        text = job.title,
                        fontSize = 23.sp,
                        color = colorResource(id = R.color.teal_700)
                    )
                }

                IconButton(
                    onClick = {
                    job.isSaved = !job.isSaved
                    if (!job.isSaved) onRemoveJob(job)
                }
                ) {
                    Icon(
                        imageVector = if (job.isSaved) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                        contentDescription = "Save Job",
                        tint = if (isSaved.value) Color.White else Color.Gray
                    )
                }
            }

            Text(
                text = job.Company,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(7.dp))
            Row {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = colorResource(id = R.color.orange),
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(7.dp))
                Text(
                    text = job.Location,
                    fontSize = 18.sp,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(7.dp))
            Row {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.dollar),
                    contentDescription = null,
                    tint = colorResource(id = R.color.orange),
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "Salary: ${job.Salary}",
                    fontSize = 18.sp,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.width(3.dp))
            Row {
                Button(
                    onClick = { /* TODO: Apply Action */ },
                    modifier = Modifier
                        .width(130.dp)
                        .height(80.dp)
                        .padding(20.dp),
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(
                            id = R.color.teal_700
                        )
                    )
                ) {
                    Text("View", fontSize = 16.sp, color = Color.White)
                }
            }
        }
    }
}
