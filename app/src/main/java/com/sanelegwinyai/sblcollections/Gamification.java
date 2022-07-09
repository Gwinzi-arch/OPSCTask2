package com.sanelegwinyai.sblcollections;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.anupkumarpanwar.scratchview.ScratchView;

public class Gamification extends AppCompatActivity {

    private ScratchView scratchView;
    private Button maskBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamification);



        scratchView=findViewById(R.id.scratch_view);
        maskBtn = findViewById(R.id.mask_btn);


        scratchView.setRevealListener(new ScratchView.IRevealListener() {
            @Override
            public void onRevealed(ScratchView scratchView) {
                Toast.makeText(Gamification.this,"Revealed",Toast.LENGTH_SHORT).show();
                maskBtn.setVisibility(View.VISIBLE);


            }

            @Override
            public void onRevealPercentChangedListener(ScratchView scratchView, float percent) {

                maskBtn.setVisibility(View.GONE);
            }
        });

        maskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scratchView.mask();

                Intent i = new Intent(getApplicationContext(), Menu.class);
                startActivity(i);
                finish();
            }
        });
    }
}