package com.example.android.reportbee;

/**
 * Created by Anurag10 on 4/12/2017.
 */

public class ListStudent  {
    private String rollNo;
    private String studentName;

    public ListStudent(String rollNo,String studentName){
        this.rollNo = rollNo;
        this.studentName = studentName;
    }

    public String getRollNo(){
        return rollNo;
    }

    public String getStudentName() {
        return studentName;
    }
}
