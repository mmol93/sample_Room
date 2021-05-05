package com.example.sample_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.sample_room.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binder by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var helper : RoomHelper
    lateinit var memoAdapter : RecyclerAdapter
    lateinit var memoDAO : RoomMemoDAO
    private val memoList = mutableListOf<RoomMemo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binder.root)

        // Room의 helper를 정의 및 생성
        // 첫 번째: context
        // 두 번째: RoomHelper 클래스
        // 세 번째: 데이터 베이스 이름 지정 (테이블 이름과 다름)
        // allowMainThreadQueries: 메인 스레드에서도 room 데이터 베이스를 사용할 수 있게 하는 것(지향함)
        helper = Room.databaseBuilder(applicationContext, RoomHelper::class.java, "room_db")
            .allowMainThreadQueries().build()

        memoDAO = helper.roomMemoDao()

        // recyclerView의 adapter 생성
        memoAdapter = RecyclerAdapter(memoList)

        refreshAdapter()

        with(binder){
            // 리사이클러뷰에 어댑터 적용
            recyclerMemo.adapter = memoAdapter
            recyclerMemo.layoutManager = LinearLayoutManager(this@MainActivity)

            btnSave.setOnClickListener {
                val content = editMemo.text.toString()
                if (content.isNotEmpty()){
                    val dateTime = System.currentTimeMillis()
                    // 입력한 텍스트와 현재 날짜를 content, dateTime 컬럼에 각각 넣게 지정
                    val memo = RoomMemo(content, dateTime)
                    // helper를 이용하여 입력한 데이터를 insert하게 한다
                    helper.roomMemoDao().insert(memo)
                    refreshAdapter()
                }
            }
        }
    }

    fun refreshAdapter(){
        memoList.clear()
        memoList.addAll(memoDAO.getAll())
        memoAdapter.notifyDataSetChanged()
    }
}