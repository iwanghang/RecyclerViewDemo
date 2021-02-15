package com.iwanghang.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * ViewHolder 减少不必要的调用findViewById，不必要每次都重新加载控件布局
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements View.OnClickListener {

    private Context context;
    private List<String> list;
    // 接口回调
    private OnMyItemClickListener listener;
    private RecyclerView recyclerView;




    // 接口回调 setter getter
//    public OnMyItemClickListener getOnMyItemClickListener() {
//        return listener;
//    }
    public void setOnMyItemClickListener(OnMyItemClickListener listener) {
        this.listener = listener;
    }




    // 当它连接到一个RecyclerView调用的方法
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }
    // 当它与RecyclerView解除连接调用的方法
    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }




    public MyAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        view.setOnClickListener(this);
        return new MyViewHolder(view);
    }
    // 上面 view.setOnClickListener(this);的点击实现方法
    @Override
    public void onClick(View view) {
        if (recyclerView != null && listener != null) {
            int position = recyclerView.getChildAdapterPosition(view);
            listener.onMyItemClick(recyclerView,view,position,list.get(position)); // 接口回调
        }
    }




    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.item_text.setText(list.get(position));
    }
    @Override
    public int getItemCount() {
        return list.size();
    }




    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView item_text;
        public MyViewHolder(View itemView) {
            super(itemView);
            item_text = (TextView) itemView.findViewById(R.id.item_text);
        }
    }





//    public Context getContext() {
//        return context;
//    }
//    public void setContext(Context context) {
//        this.context = context;
//    }
//    public List<String> getList() {
//        return list;
//    }
//    public void setList(List<String> list) {
//        this.list = list;
//    }




    // 接口回调
    public interface OnMyItemClickListener{
        void onMyItemClick(RecyclerView parent, View view, int position, String data);
    }



    // 删除数据
    public void remove(int position){
        list.remove(position);
        //notifyDataSetChanged();// 提醒list刷新，没有动画效果
        notifyItemRemoved(position); // 提醒item删除指定数据，这里有RecyclerView的动画效果
    }
}
