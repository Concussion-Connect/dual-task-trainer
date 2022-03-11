package com.github.concussionconnect.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.github.concussionconnect.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScreeningQuestionnairePart2 extends Activity implements View.OnClickListener {

    private Button back, Continue;
    private Bundle bundle;
    private CheckBox maleCheckBox, femaleCheckBox, raceNative, raceAsian, raceBlack, raceHawaiian,
            raceWhite, ethnicityHispanic, ethnicityNonHispanic, yesEnglish, noEnglish, schoolElem,
            schoolMiddle, schoolHigh, schoolDip, schoolSomeCollege, schoolTrade, schoolAssoc,
            schoolBach, schoolMaster, schoolPhD, concussionYes, concussionNo, alcoholYes, alcoholNo,
            ADHDYes, ADHDNo, epilepsyYes, epilepsyNo, ADHDMedication24Yes, ADHDMedication24No,
            injurySports1, injuryVehicle1, injuryBlunt1, injuryOther1,
            injurySports2, injuryVehicle2, injuryBlunt2, injuryOther2,
            injurySports3, injuryVehicle3, injuryBlunt3, injuryOther3;
    private EditText age, timesPerWeek, avgLength, ADHDMedications, timesConcussion, dateOfLastConc,
            dateOfConc1, duration1, sportName1, days1, comment1,
            dateOfConc2, duration2, sportName2, days2, comment2,
            dateOfConc3, duration3, sportName3, days3, comment3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screening_questionnaire_part2);

        connectWidgets();
        back = findViewById(R.id.backFromQuest);
        Continue = findViewById(R.id.Continue);
        back.setOnClickListener(this);
        Continue.setOnClickListener(this);
        bundle = getIntent().getExtras();
    }

    private void connectWidgets() {
        maleCheckBox = findViewById(R.id.maleCheckBox);
        femaleCheckBox = findViewById(R.id.femaleCheckBox);
        raceNative = findViewById(R.id.raceNative);
        raceAsian = findViewById(R.id.raceAsian);
        raceBlack = findViewById(R.id.raceBlack);
        raceHawaiian = findViewById(R.id.raceHawaiian);
        raceWhite = findViewById(R.id.raceWhite);
        ethnicityHispanic = findViewById(R.id.ethnicityHispanic);
        ethnicityNonHispanic = findViewById(R.id.ethnicityNonHispanic);
        yesEnglish = findViewById(R.id.yesEnglish);
        noEnglish = findViewById(R.id.noEnglish);
        schoolElem = findViewById(R.id.schoolElem);
        schoolMiddle = findViewById(R.id.schoolMiddle);
        schoolHigh = findViewById(R.id.schoolHigh);
        schoolDip = findViewById(R.id.schoolDip);
        schoolSomeCollege = findViewById(R.id.schoolSomeCollege);
        schoolTrade = findViewById(R.id.schoolTrade);
        schoolAssoc = findViewById(R.id.schoolAssoc);
        schoolBach = findViewById(R.id.schoolBach);
        schoolMaster = findViewById(R.id.schoolMaster);
        schoolPhD = findViewById(R.id.schoolPhD);
        concussionYes = findViewById(R.id.concussionYes);
        concussionNo = findViewById(R.id.concussionNo);
        alcoholYes = findViewById(R.id.alcoholYes);
        alcoholNo = findViewById(R.id.alcoholNo);
        ADHDYes = findViewById(R.id.ADHDYes);
        ADHDNo = findViewById(R.id.ADHDNo);
        epilepsyYes = findViewById(R.id.epilepsyYes);
        epilepsyNo = findViewById(R.id.epilepsyNo);
        injurySports1 = findViewById(R.id.injurySports1);
        injuryVehicle1 = findViewById(R.id.injuryVehicle1);
        injuryBlunt1 = findViewById(R.id.injuryBlunt1);
        injuryOther1 = findViewById(R.id.injuryOther1);
        injurySports2 = findViewById(R.id.injurySports2);
        injuryVehicle2 = findViewById(R.id.injuryVehicle2);
        injuryBlunt2 = findViewById(R.id.injuryBlunt2);
        injuryOther2 = findViewById(R.id.injuryOther2);
        injurySports3 = findViewById(R.id.injurySports3);
        injuryVehicle3 = findViewById(R.id.injuryVehicle3);
        injuryBlunt3 = findViewById(R.id.injuryBlunt3);
        injuryOther3 = findViewById(R.id.injuryOther3);
        ADHDMedication24Yes = findViewById(R.id.ADHDMedication24Yes);
        ADHDMedication24No = findViewById(R.id.ADHDMedication24No);

        age = findViewById(R.id.age);
        timesPerWeek = findViewById(R.id.timesPerWeek);
        avgLength = findViewById(R.id.avgLength);
        ADHDMedications = findViewById(R.id.ADHDMedications);
        timesConcussion = findViewById(R.id.timesConcussion);
        dateOfLastConc = findViewById(R.id.dateOfLastConc);
        dateOfConc1 = findViewById(R.id.dateOfConc1);
        duration1 = findViewById(R.id.duration1);
        sportName1 = findViewById(R.id.sportName1);
        days1 = findViewById(R.id.days1);
        comment1 = findViewById(R.id.comment1);
        dateOfConc2 = findViewById(R.id.dateOfConc2);
        duration2 = findViewById(R.id.duration2);
        sportName2 = findViewById(R.id.sportName2);
        days2 = findViewById(R.id.days2);
        comment2 = findViewById(R.id.comment2);
        dateOfConc3 = findViewById(R.id.dateOfConc3);
        duration3 = findViewById(R.id.duration3);
        sportName3 = findViewById(R.id.sportName3);
        days3 = findViewById(R.id.days3);
        comment3 = findViewById(R.id.comment3);
    }

    private void saveInput() {
        HashMap<String, Object> subjectBiographicalData = new HashMap<>();
        ArrayList<String> gender = new ArrayList<>();
        addIfChecked(gender, maleCheckBox);
        addIfChecked(gender, femaleCheckBox);
        subjectBiographicalData.put("Gender", String.join(", ", gender));

        ArrayList<String> race = new ArrayList<>();
        addIfChecked(race, raceNative);
        addIfChecked(race, raceAsian);
        addIfChecked(race, raceBlack);
        addIfChecked(race, raceHawaiian);
        addIfChecked(race, raceWhite);
        subjectBiographicalData.put("Race", String.join(", ", race));

        ArrayList<String> hispanic = new ArrayList<>();
        addIfChecked(hispanic, ethnicityHispanic);
        addIfChecked(hispanic, ethnicityNonHispanic);
        subjectBiographicalData.put("Ethnicity", String.join(", ", hispanic));

        ArrayList<String> english = new ArrayList<>();
        addIfChecked(english, yesEnglish);
        addIfChecked(english, noEnglish);
        subjectBiographicalData.put("Can Speak English", String.join(", ", english));

        ArrayList<String> highestEducation = new ArrayList<>();
        addIfChecked(highestEducation, schoolElem);
        addIfChecked(highestEducation, schoolMiddle);
        addIfChecked(highestEducation, schoolHigh);
        addIfChecked(highestEducation, schoolDip);
        addIfChecked(highestEducation, schoolSomeCollege);
        addIfChecked(highestEducation, schoolTrade);
        addIfChecked(highestEducation, schoolAssoc);
        addIfChecked(highestEducation, schoolBach);
        addIfChecked(highestEducation, schoolMaster);
        addIfChecked(highestEducation, schoolPhD);
        subjectBiographicalData.put("What is the highest degree or level of school you have " +
                "completed? If currently enrolled, highest degree received",
                String.join(", ", highestEducation));


        ArrayList<String> highestEducation = new ArrayList<>();

    }

    private void addIfChecked(List<String> list, CheckBox item) {
        if (item.isChecked()) {
            list.add(item.getText().toString());
        }
    }

    public void onClick(View v) {
        if (v == back) {
            Intent intent = new Intent(this, ScreeningQuestionnairePart1.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }

        if (v == Continue) {
            saveInput();
            Intent intent = new Intent(this, ScreeningQuestionnairePart3.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}