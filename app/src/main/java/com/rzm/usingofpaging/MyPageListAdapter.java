package com.rzm.usingofpaging;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class MyPageListAdapter extends PagedListAdapter<Student, MyPageListAdapter.MyPageListViewHolder> {
    protected MyPageListAdapter() {
        super(new DiffUtil.ItemCallback<Student>() {
            @Override
            public boolean areItemsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
                Log.d("MyPageListAdapter", "areItemsTheSame oldItem.getId() = " + oldItem.getId() + " newItem.getId() = " + newItem.getId());
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
                Log.d("MyPageListAdapter", "areContentsTheSame oldItem = " + oldItem + " newItem = " + newItem);
                return oldItem.equals(newItem);
            }
        });
    }

    @NonNull
    @Override
    public MyPageListAdapter.MyPageListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null);
        return new MyPageListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyPageListViewHolder holder, int position) {
        Student student = getItem(position);
        // item view 出来了， 分页库还在加载数据中，显示 Id加载中
        if (null == student) {
            holder.tvId.setText("Id加载中");
            holder.tvName.setText("Name加载中");
            holder.tvSex.setText("Sex加载中");
        } else {
            holder.tvId.setText(student.getId());
            holder.tvName.setText(student.getName());
            holder.tvSex.setText(student.getSex());
        }
    }

    public static class MyPageListViewHolder extends RecyclerView.ViewHolder {

        TextView tvId;
        TextView tvName;
        TextView tvSex;

        public MyPageListViewHolder(View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id); // ID
            tvName = itemView.findViewById(R.id.tv_name); // 名称
            tvSex = itemView.findViewById(R.id.tv_sex); // 性别
        }
    }
}
