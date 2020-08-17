package com.lee.myroomexam.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 테이블명을 따로 지정해줄수 있다.
 * 만약 지정하지 않는다면 클래스명을 따를것이다.
 */
@Entity(tableName = "user")
data class User(
    /**
     * 모든 Entity는 PrivaryKey를 하나씩 포함해야 한다.
     * autoGenerate 옵션을 추가해서 id를 지정하지 않더라고 자동으로 생성하도록 해준다.
     */
    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    /**
     * ColumnInfo는 테이블 내의 스키마를 따로 지정해준다.
     */
    @ColumnInfo(name = "name")
    val userName: String?,
    val age: Int?
)