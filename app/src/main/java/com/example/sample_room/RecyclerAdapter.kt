package com.example.sample_room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sample_room.databinding.ItemRecyclerBinding
import java.text.SimpleDateFormat

class RecyclerAdapter(val roomMemoList : List<RoomMemo>) : RecyclerView.Adapter<RecyclerAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.
        inflate(LayoutInflater.from(parent.context), parent, false)

        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setMemo(roomMemoList[position])
    }

    override fun getItemCount() = roomMemoList.size

    class Holder(val binding : ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root){
        // 매개 변수 roomMemo에는 해당 row(=position)의 모든 컬럼 값이 들어있다
        fun setMemo(roomMemo:RoomMemo){
            with(binding){
                textNo.text = "${roomMemo.no}"
                textContent.text = roomMemo.content

                val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm")
                textDate.text = sdf.format(roomMemo.dateTime)
            }
        }
    }
}