package com.lee.myroomexam.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(User::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    /**
     * UserDao를 반환하는 추상화 메소
     */
    abstract fun userDao(): UserDao

    companion object {
        private val DB_NAME = "user-room-db"

        /**
         * 데이타베이스를 만드는 작업은 리소스를 많이 필요로 한다.
         * 때문에 이 프로젝트 내에서 딱 하나만 만들어서 관리하는 것이 유리하다.
         * 싱글턴으로 만들도록 한다.
         */
        var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        DB_NAME
                    )
//                        .fallbackToDestructiveMigration() // Entity의 구조가 바뀌거나 버전이 올라갔을때 기존 내용을 드랍하고 새로 작성하는 옵션.
                        .build()
                }
            }

            return instance
        }
    }
}