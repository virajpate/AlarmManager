package com.example.testapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.R;
import com.example.testapp.model.EntityClass;

import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.viewHolder>{

    Context mcontext;
    List<EntityClass> entityClassList;

    public AlarmAdapter(Context mcontext, List<EntityClass> entityClassList) {
        this.mcontext = mcontext;
        this.entityClassList = entityClassList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.alarmlist_container_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        holder.message.setText(entityClassList.get(position).getMessage());
        holder.timedate.setText(entityClassList.get(position).getDate()+" "+entityClassList.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return entityClassList.size();
    }

    class viewHolder extends RecyclerView.ViewHolder{

        private TextView message,timedate;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            message=itemView.findViewById(R.id.tv_message);
            timedate=itemView.findViewById(R.id.tv_date);

        }
    }
}
