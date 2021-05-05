package com.example.sample_room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "room_memo")
class RoomMemo{
    // no에 값이 없을 때 자동증가된 숫자 값을 db에 입력해준다.
    @PrimaryKey
    var no : Long? = null
    @ColumnInfo
    var content : String = ""
    @ColumnInfo
    var dateTime : Long = 0

    // 생성자를 만들 시 다른 클래스에서 클래스 생성자 생성 없이 바로 넣을 수 있다
    constructor(content : String, dateTime : Long) {
        this.content = content
        this.dateTime = dateTime
    }
}