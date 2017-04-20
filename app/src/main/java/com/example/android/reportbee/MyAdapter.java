package com.example.android.reportbee;

/**
 * Created by Anurag10 on 4/12/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anurag10 on 2/23/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<ListStudent> listStudents;
    private Context context;
    private String spinnerItem;

    public MyAdapter(List<ListStudent> listStudents, Context context) {
        this.listStudents = listStudents;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.footer, parent, false));

        } else {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.attendance_card, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ListStudent listStudent = listStudents.get(position);
       // Log.d("Footer",""+position+listStudents.size());
        if (++position == getItemCount()) {
            holder.saveButton.setText("Save");
            try{holder.saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Attendance Saved ", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, CalenderUI.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);
                }
            });}
            catch (Throwable e){
                e.printStackTrace();
            }
        }
        else

        {
            holder.textViewRollNumber.setText(listStudent.getRollNo());
            holder.textViewStudentName.setText(listStudent.getStudentName());
            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, listStudent.getRollNo(), Toast.LENGTH_LONG).show();
                }
            });
            //Spinner Drop Down Element
            List<String> categories = new ArrayList<String>();
            categories.add("Present");
            categories.add("Absent");
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, categories) {
                @Override
                public boolean isEnabled(int position) {
                    if (position == 0) {
                        // Disable the first item from Spinner
                        // First item will be use for hint
                        return true;
                    } else {
                        return true;
                    }
                }

                @Override
                public View getDropDownView(int position, View convertView,
                                            ViewGroup parent) {
                    View view = super.getDropDownView(position, convertView, parent);
                    TextView tv = (TextView) view;
                    if (position == 0) {
                        // Set the hint text color gray
                        tv.setBackgroundColor(Color.GREEN);

                    } else {
                        tv.setBackgroundColor(Color.RED);
                    }
                    (tv).setGravity(Gravity.CENTER);
                    return view;
                }
            };
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            holder.spinner.setAdapter(adapter);
            //After Selecting Spinner Item
            holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    spinnerItem = (String) parent.getItemAtPosition(position);
                    if (spinnerItem.equals("Absent")) {
                        holder.spinner.setBackgroundColor(Color.RED);
                    } else {
                        holder.spinner.setBackgroundColor(Color.rgb(66, 244, 167));
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }
    }

    @Override
    public int getItemCount() {
            return listStudents.size();
    }

    private final int TITLE = 0;
    private final int LOAD_MORE = 1;
    @Override
    public int getItemViewType(int position) {
        return (++position == listStudents.size()) ? LOAD_MORE : TITLE;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewRollNumber;
        public TextView textViewStudentName;
        public LinearLayout linearLayout;
        public Button saveButton;
        public  Spinner spinner;
        public ViewHolder(View itemView){
            super(itemView);
            textViewRollNumber = (TextView) itemView.findViewById(R.id.textViewRollNumber);
            textViewStudentName = (TextView) itemView.findViewById(R.id.textViewStudentName);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
            spinner = (Spinner) itemView.findViewById(R.id.markAttendance);
            saveButton = (Button) itemView.findViewById(R.id.saveButton);

        }
    }

}
