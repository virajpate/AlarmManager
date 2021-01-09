package com.example.testapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.Activity.EditAlarmActivity;
import com.example.testapp.Activity.MainActivity;
import com.example.testapp.R;
import com.example.testapp.model.EntityClass;

import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.viewHolder>{
    public interface OnDeleteClickListener {
        void OnDeleteClickListener(EntityClass entityClass);
    }

    private final LayoutInflater layoutInflater;
    Context mcontext;
    private List<EntityClass> entityClassList;
    private OnDeleteClickListener onDeleteClickListener;

    public AlarmAdapter(Context context,OnDeleteClickListener listener) {
       layoutInflater=LayoutInflater.from(context);
       mcontext=context;
        this.onDeleteClickListener = listener;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View itemview=layoutInflater.inflate(R.layout.alarmlist_container_layout,parent,false);
      viewHolder vHolder=new viewHolder(itemview);
      return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        if (entityClassList != null){
            holder.message.setText(entityClassList.get(position).getMessage());
            holder.timedate.setText(entityClassList.get(position).getDate()+" "+entityClassList.get(position).getTime());
        }
        else {
            holder.message.setText("Null value");
            holder.timedate.setText("Null value");
        }

    }

    @Override
    public int getItemCount() {
       if (entityClassList !=null){
           return entityClassList.size();
       }
       else {
           return 0;
       }
    }

    public void setAlarm(List<EntityClass> entityClassLis){
        entityClassList=entityClassLis;
        notifyDataSetChanged();
    }

    class viewHolder extends RecyclerView.ViewHolder{

        private TextView message,timedate;
        private ImageView btnedit,btnDelete;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            message=itemView.findViewById(R.id.tv_message);
            timedate=itemView.findViewById(R.id.tv_date);
            btnedit=itemView.findViewById(R.id.btn_edit);
            btnDelete=itemView.findViewById(R.id.btn_delete);


            btnedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(mcontext, EditAlarmActivity.class);
                    int position=getAdapterPosition();

                    intent.putExtra("id",entityClassList.get(position).getId());
                    intent.putExtra("message",entityClassList.get(position).getMessage());
                    intent.putExtra("time",entityClassList.get(position).getTime());
                    intent.putExtra("date",entityClassList.get(position).getDate());
                    ((Activity)mcontext).startActivityForResult(intent, MainActivity.Update_Alarm_REQ_CODE);

                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    if(onDeleteClickListener != null){
                        onDeleteClickListener.OnDeleteClickListener(entityClassList.get(position));

                    }
                }
            });

        }


    }
}
