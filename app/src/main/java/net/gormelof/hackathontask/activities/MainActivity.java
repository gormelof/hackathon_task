package net.gormelof.hackathontask.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import net.gormelof.hackathontask.api.HackathonService;
import net.gormelof.hackathontask.api.Pageable;
import net.gormelof.hackathontask.R;
import net.gormelof.hackathontask.api.ServiceGenerator;
import net.gormelof.hackathontask.request.searchrecords.SearchData;
import net.gormelof.hackathontask.response.searchrecords.*;
import net.gormelof.hackathontask.response.searchrecords.Record;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName().toUpperCase();

    TableLayout maintable;
    TableRow tableRow,rowHead;
    LinearLayout separator;
    TextView value;
    TextView textView;
    Pageable<Record> pageableArray;
    Button buttonNext;
    Button buttonPrev;
    TextView textViewPageCount;
    ArrayList<Record> myValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.tvID);
        maintable = (TableLayout) findViewById(R.id.main);
        rowHead = (TableRow) findViewById(R.id.rowHeader);
        buttonNext = (Button) findViewById(R.id.btnNext);
        buttonPrev = (Button) findViewById(R.id.btnPrevious);
        textViewPageCount = (TextView) findViewById(R.id.txtPageCount);
        myValues = new ArrayList<>();
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageableArray.setPage(pageableArray.getNextPage());
                tableRow.removeAllViews();
                displayPage();
                textViewPageCount.setText("Page " + pageableArray.getPage() + " of " + pageableArray.getMaxPages());
            }
        });

        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageableArray.setPage(pageableArray.getPreviousPage());
                tableRow.removeAllViews();
                displayPage();
                textViewPageCount.setText("Page " + pageableArray.getPage() + " of " + pageableArray.getMaxPages());

            }
        });

        HackathonService hackathonService = ServiceGenerator.createService(HackathonService.class);

        Intent intent = getIntent();
        String startDate = intent.getStringExtra("start_date");
        String endDate = intent.getStringExtra("end_date");
        String maxCount = intent.getStringExtra("max_count");
        String minCount = intent.getStringExtra("min_count");

        SearchData data = new SearchData();
        data.setStartDate(startDate);
        data.setEndDate(endDate);
        data.setMinCount(Integer.parseInt(minCount));
        data.setMaxCount(Integer.parseInt(maxCount));

        Call<SearchRecords> call = hackathonService.searchRecords(data);
        // Set up progress before call
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Its loading....");
        progressDoalog.setTitle("Getting Data");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // show it
        progressDoalog.show();
        call.enqueue(new Callback<SearchRecords>() {
            @Override
            public void onResponse(Call<SearchRecords> call, Response<SearchRecords> response) {
                if (response.isSuccessful()) {
                    pageableArray = new Pageable<>(response.body().getRecords());
                    pageableArray.setPageSize(10);
                    pageableArray.setPage(1);
                    textViewPageCount.setText("Page " + pageableArray.getPage() + " of " + pageableArray.getMaxPages());

                    progressDoalog.dismiss();
                    displayPage();
                }
            }

            @Override
            public void onFailure(Call<SearchRecords> call, Throwable t) {
                Log.e(TAG, "ERROR");
                // close it after response
                progressDoalog.dismiss();
            }
        });


    }

    public void displayPage() {
        maintable.removeAllViews();
        maintable.addView(rowHead);
        for (net.gormelof.hackathontask.response.searchrecords.Record v : pageableArray.getListForPage()) {
            tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));

            value = new TextView(this);
            value.setLayoutParams(textView.getLayoutParams());
            value.setGravity(Gravity.CENTER_HORIZONTAL);
            value.setText(v.getId().getId());
            tableRow.addView(value);

            value = new TextView(this);
            value.setLayoutParams(textView.getLayoutParams());
            value.setGravity(Gravity.CENTER_HORIZONTAL);
            value.setText(v.getTotalCount().toString());
            tableRow.addView(value);

            maintable.addView(tableRow);
            addSeparator();
        }
    }

    private void addSeparator() {
        Resources res = MainActivity.this.getResources();
        separator = new LinearLayout(MainActivity.this);
        separator.setOrientation(LinearLayout.VERTICAL);
        separator.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 2));
        separator.setBackgroundColor(Color.parseColor("#5e7974"));
        separator.setDividerDrawable(res.getDrawable(R.drawable.radius_middle));
        maintable.addView(separator);

    }

}