package com.github.concussionconnect.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.github.concussionconnect.Model.SymptomAdapter;
import com.github.concussionconnect.Model.SymptomModel;
import com.github.concussionconnect.R;

import java.util.ArrayList;

public class ResearchSymptomsActivity extends Activity implements View.OnClickListener{
    private ArrayList<SymptomModel> symptomList;
    private Button next;
    private ListView viewList;
    SymptomAdapter sympAdapt;
    Bundle bundle1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_symptoms);
        symptomList = SymptomModel.getSymptomArray(getResources().getStringArray(R.array.symptom_list));
        next = (Button) findViewById(R.id.nextButton1);
        next.setOnClickListener(this);
        viewList = (ListView) findViewById(R.id.listView2);
        sympAdapt = new SymptomAdapter(symptomList, this);
        viewList.setAdapter(sympAdapt);

    }
    @Override
    public void onClick(View v) {
        if(v == next) {
            startActivity(new Intent(this, OpeningPageForTests.class));
        }
    }

}