package com.example.moviesapp.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviesapp.Domain.FilmItemModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainRepository {
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    //LiveData: A lifecycle-aware data holder that allows UI components (like ViewModel) to observe changes and update automatically.
    //MutableLiveData: A mutable version of LiveData, allowing updates to the stored data.
    fun loadUpcoming(): LiveData<MutableList<FilmItemModel>>{
        val listData = MutableLiveData<MutableList<FilmItemModel>>()
        val ref = firebaseDatabase.getReference("Upcomming")
        //Continuously listens for changes in Firebase.
        ref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                //snapshot represents the entire "Upcoming" node.
                val lists = mutableListOf<FilmItemModel>()
                for(childSnapshot in snapshot.children){
                    val item = childSnapshot.getValue(FilmItemModel::class.java) //Converts Firebase data into FilmItemModel
                    item?.let { lists.add(it) }
                }
                listData.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return listData
    }

    fun loadItems(): LiveData<MutableList<FilmItemModel>>{
        val listData = MutableLiveData<MutableList<FilmItemModel>>()
        val ref = firebaseDatabase.getReference("Items")
        ref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<FilmItemModel>()
                for(childSnapshot in snapshot.children){
                    val item = childSnapshot.getValue(FilmItemModel::class.java)
                    item?.let { lists.add(it) }
                }
                listData.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return listData
    }
}