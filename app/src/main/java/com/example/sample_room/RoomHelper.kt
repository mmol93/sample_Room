package com.example.sample_room

import androidx.room.Database
import androidx.room.RoomDatabase

// 어떤 테이블을 쓰는지 Database로 알려준다
// 즉, 여러 개의 테이블을 지정할 수 도 있다
@Database(entities = arrayOf(RoomMemo::class), version = 1)
abstract class RoomHelper : RoomDatabase(){
    // 즉, 이 메소드를 호출하면 RoomMemoDAO가 반환되어
    // get, insert, delete 등을 할 수 있다
    abstract fun roomMemoDao() : RoomMemoDAO
}