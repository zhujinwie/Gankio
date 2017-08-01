package com.navgnss.gankio.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.navgnss.gankio.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.MemoryHandler;

/**
 * Created by ZhuJinWei on 2017/4/27.
 */

public class FluidAdapter extends RecyclerView.Adapter<FluidAdapter.MyViewHolder> {

    private Context context;
    private List<String> datas;

    public FluidAdapter(Context context,List<String> datas){

        this.context= context;
        this.datas= datas;



    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder viewHolder=new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_fluid,parent,false));
        return viewHolder;
    }

    /**
     * binding时实现瀑布流效果
     * */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {



        Picasso.with(context).load(datas.get(position*2)).into(holder.iv);
        holder.tv.setText(datas.get(position*2+1));

        holder.iv.getLayoutParams().height=450+(int)Math.random()*500;

    }

    public void addAll(List<String> mDatas,boolean isClear){
        if(isClear)
            datas.clear();

        datas.addAll(mDatas);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView iv;
        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv=(ImageView) itemView.findViewById(R.id.item_meizi_iv);
            tv=(TextView) itemView.findViewById(R.id.item_meizi_tv);
        }
    }
}
