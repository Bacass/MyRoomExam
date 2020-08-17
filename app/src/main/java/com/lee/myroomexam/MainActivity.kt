package com.lee.myroomexam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.lee.myroomexam.database.AppDatabase
import com.lee.myroomexam.database.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Thread.sleep

class MainActivity : AppCompatActivity() {

    lateinit var db: AppDatabase
    var userList: List<User> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = AppDatabase.getInstance(this)!!

        GlobalScope.launch {    // 1
            insertDB()
            sleep(1000)
            findByNameAndAge()
            sleep(1000)
            loadAllByIds()
            sleep(1000)
            deleteUser()
        }
    }

    private fun insertDB() {
        GlobalScope.launch {    // 1
            db.userDao().insertUser(User(null, userName = "홍길동", age = 24))
            db.userDao().insertUser(User(null, userName = "성춘향", age = 16))
            db.userDao().insertUser(User(null, userName = "이몽룡", age = 18))
            db.userDao().insertUser(User(null, userName = "소방자", age = 21))
            db.userDao().insertUser(User(null, userName = "최향단", age = 17))

            userList = db.userDao().getAll()
            for (user in userList) {
                Log.d("Room", "${user.id} / ${user.userName} / ${user.age}")
            }
            Log.d("Room", "=======================================")
            Log.d("Room", "  ")
        }
    }

    private fun findByNameAndAge() {
        GlobalScope.launch {    // 1
            var user = db.userDao().findByNameAndAge(name = "성춘향", age = 16)
            Log.d("Room", "${user.id} / ${user.userName} / ${user.age}")
        }
        Log.d("Room", "=======================================")
        Log.d("Room", "  ")
    }

    private fun loadAllByIds() {
        GlobalScope.launch {    // 1
            userList = db.userDao().loadAllByIds(intArrayOf(2, 3, 4))
            for (user in userList) {
                Log.d("Room", "${user.id} / ${user.userName} / ${user.age}")
            }
        }
        Log.d("Room", "=======================================")
        Log.d("Room", "  ")
    }

    private fun deleteUser() {
        GlobalScope.launch {    // 1
            db.userDao().deleteUser(User(null, userName = "소방자", age = 21))

            userList = db.userDao().getAll()
            for (user in userList) {
                Log.d("Room", "${user.id} / ${user.userName} / ${user.age}")
            }
        }
        Log.d("Room", "=======================================")
        Log.d("Room", "  ")
    }
}