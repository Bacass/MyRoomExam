package com.lee.myroomexam.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user WHERE name LIKE :name AND age LIKE :age LIMIT 1")
    fun findByNameAndAge(name: String, age: Int): User

    /**
     * onConflict = REPLACE 만약 같은 내용이 이미 있다면 덮어쓴다는 옵션이다.
     */
    @Insert(onConflict = REPLACE)
    fun insertUser(user: User)

    @Delete
    fun deleteUser(user: User)
}