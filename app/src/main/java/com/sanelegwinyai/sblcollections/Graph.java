package com.sanelegwinyai.sblcollections;

import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Color;

import android.os.Bundle;
import android.util.Log;


import com.github.mikephil.charting.charts.BarChart;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Graph extends AppCompatActivity {


    ArrayList barArraylist;
    String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
    Query query = dbRef.child("categories").orderByChild("id");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_graph);
        BarChart barChart = findViewById(R.id.BarChart);

        getData();
        BarDataSet barDataSet = new BarDataSet(barArraylist, "Graph");
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(true);
    }

    private void getData()
    {
        barArraylist = new ArrayList();
        barArraylist.add(new BarEntry(2f,1));
    }

    public void onDataChange(DataSnapshot dataSnapshot) {
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            long count = ds.child("categories").getChildrenCount();

            Log.d("TAG", "Count:" + count);
        }
    }
}
