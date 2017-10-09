package com.github.concussionconnect.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import com.github.concussionconnect.Model.ChecklistAdapter;
import com.github.concussionconnect.Model.ChecklistModel;
import com.github.concussionconnect.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SymptomsActivity extends Activity implements View.OnClickListener {
    private ArrayList<ChecklistModel> sympList;
    private Button nextButton;
    private ListView listView;
    ChecklistAdapter checklistAdapter;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);
        sympList = ChecklistModel.getChecklistArray(getResources().getStringArray(R.array.symptom_list));
        nextButton =  (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listView1);
        checklistAdapter = new ChecklistAdapter(sympList, this);
        listView.setAdapter(checklistAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ChecklistModel checklistModel = ((ChecklistAdapter) listView.getAdapter()).getWordList().get(position);
                CheckBox box = (CheckBox) view.findViewById(R.id.checkBox1);
                box.setChecked(!box.isChecked());
            }
        });
        bundle = getIntent().getExtras();
    }
    @Override
    public void onClick(View v) {
        if (v == nextButton) {
            ArrayList<String> symptoms = new ArrayList<>();
            for (ChecklistModel x : checklistAdapter.getWordList()) {
                if (x.isChecked()) {
                    symptoms.add(x.getWord());
                }
            }
            if (symptoms.size() == 0) {
                symptoms.add("none");
            }
            bundle.putStringArrayList("symptoms", symptoms);
            startActivity(new Intent(this, WordLearnActivity.class).putExtras(bundle));
        }
    }
}
