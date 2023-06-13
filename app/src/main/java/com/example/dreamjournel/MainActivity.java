package com.example.dreamjournel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView add,summary;


    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add=findViewById(R.id.Add_card);
        summary=findViewById(R.id.summary_cardview);


        add.setOnClickListener(this);
        summary.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent i;

        switch(v.getId()){

            case R.id.Add_card:i=new Intent(this,Add.class);startActivity(i);
            break;
           case R.id.summary_cardview:i=new Intent(this,Summary.class);startActivity(i);
          break;
            default:break;
        }


    }
}
