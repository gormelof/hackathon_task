package net.gormelof.hackathontask.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import net.gormelof.hackathontask.R;

import java.util.Calendar;

public class UserInputActivity extends AppCompatActivity {
    private static final String TAG = UserInputActivity.class.getSimpleName().toUpperCase();

    EditText startDate;
    EditText endDate;
    EditText maxCount;
    EditText minCount;
    Button changeStartDate;
    Button changeEndDate;
    Button sendData;

    DatePickerDialog.OnDateSetListener mDateSetListenerStart;
    DatePickerDialog.OnDateSetListener mDateSetListenerEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_input);

        startDate = (EditText) findViewById(R.id.startDate);
        endDate = (EditText) findViewById(R.id.endDate);
        maxCount = (EditText) findViewById(R.id.maxCount);
        minCount = (EditText) findViewById(R.id.minCount);

        changeStartDate = (Button) findViewById(R.id.changeStartDate);
        changeEndDate = (Button) findViewById(R.id.changeEndDate);
        sendData = (Button) findViewById(R.id.sendData);

        changeStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        UserInputActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListenerStart,
                        year,month,day
                );

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        changeEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        UserInputActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListenerEnd,
                        year,month,day
                );

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListenerStart = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String date = Integer.toString(year) + "-" + Integer.toString(month+1) + "-" + Integer.toString(day);
                startDate.setText(date);
                Log.i("TAG", year + "---" + month + "---" + day);
            }
        };

        mDateSetListenerEnd = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String date = Integer.toString(year) + "-" + Integer.toString(month+1) + "-" + Integer.toString(day);
                endDate.setText(date);
                Log.i("TAG", year + "---" + month + "---" + day);
            }
        };

        sendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String start = startDate.getText().toString();
                String end = endDate.getText().toString();
                String max = maxCount.getText().toString();
                String min = minCount.getText().toString();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                intent.putExtra("start_date", start);
                intent.putExtra("end_date", end);
                intent.putExtra("max_count", max);
                intent.putExtra("min_count", min);

                startActivity(intent);
            }
        });
    }
}
