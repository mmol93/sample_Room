package com.example.sample_room

import androidx.room.*

@Dao
interface RoomMemoDAO {
    @Query("SELECT * FROM room_memo")
    fun getAll() : List<RoomMemo>

    // OnConflictStrategy.REPLACE
    // : 해당 no에 이미 값이 있는데 insert를 할 경우 덮어쓰기를 한다
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(memo:RoomMemo)

    @Delete
    fun delete(memo: RoomMemo)

    @Update
    fun update(memo: RoomMemo)

    @Query("UPDATE room_memo SET content = :editText, dateTime = :dateTime WHERE `no` = :index")
    fun updateByIndex(editText : String, dateTime : Long, index : Long)

    @Query("DELETE FROM room_memo WHERE `no` = :index")
    fun deleteByIndex(index : Long)

}