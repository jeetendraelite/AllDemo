package com.DateTimePicker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.alldemo.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DateTimeActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.btn_datepicker)
    Button btn_datepicker;

    @BindView(R.id.btn_timepicker)
    Button btn_timepicker;

    @BindView(R.id.et_date)
    EditText et_date;

    @BindView(R.id.et_time)
    EditText et_time;

    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time);
        ButterKnife.bind(this);

        btn_datepicker.setOnClickListener(this);
        btn_timepicker.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_datepicker:
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month= calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(DateTimeActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        et_date.setText(dayOfMonth+":"+ (month+1) + ":" +year);
                    }
                }, year,month,day);
                datePickerDialog.show();

                break;

            case R.id.btn_timepicker:
                Calendar calendar1 = Calendar.getInstance();
                int Hour = calendar1.get(Calendar.HOUR);
                int Minute= calendar1.get(Calendar.MINUTE);
                timePickerDialog= new TimePickerDialog(DateTimeActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        et_time.setText(hourOfDay + ": "+ minute );
                    }
                },Hour,Minute,false);
                timePickerDialog.show();

                break;
        }
    }
}
