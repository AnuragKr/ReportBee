package com.example.android.reportbee;

/**
 * Created by Anurag10 on 4/12/2017.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Anurag10 on 2/23/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<ListStudent> listStudents;
    private Context context;

    public MyAdapter(List<ListStudent> listStudents, Context context){
        this.listStudents = listStudents;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendance_card,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ListStudent listStudent = listStudents.get(position);
        holder.textViewRollNumber.setText(listStudent.getRollNo());
        holder.textViewStudentName.setText(listStudent.getStudentName());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, listStudent.getRollNo(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listStudents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewRollNumber;
        public TextView textViewStudentName;
        public LinearLayout linearLayout;
        public ViewHolder(View itemView){
            super(itemView);
            textViewRollNumber = (TextView) itemView.findViewById(R.id.textViewRollNumber);
            textViewStudentName = (TextView) itemView.findViewById(R.id.textViewStudentName);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }
    }

}
