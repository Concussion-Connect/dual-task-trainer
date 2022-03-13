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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private void saveInputs() {
        HashMap<String, Object> subjectDemographicInformation = new HashMap<>();
        bundle.putSerializable("Subject Demographic Information", subjectDemographicInformation);

        addAllIfChecked(new ArrayList<>(Arrays.asList(maleCheckBox, femaleCheckBox)),
                subjectDemographicInformation, "1. Subject Gender");

        subjectDemographicInformation.put("2. Subject Age", age.getText().toString());

        addAllIfChecked(new ArrayList<>(Arrays.asList(
                raceNative, raceAsian, raceBlack, raceHawaiian, raceWhite)),
                subjectDemographicInformation, "3. Subject Race");

        addAllIfChecked(new ArrayList<>(Arrays.asList(
                ethnicityHispanic, ethnicityNonHispanic)),
                subjectDemographicInformation, "4. Subject Ethnicity");

        addAllIfChecked(new ArrayList<>(Arrays.asList(
                yesEnglish, noEnglish)),
                subjectDemographicInformation, "5. Do you speak English fluently");

        addAllIfChecked(new ArrayList<>(Arrays.asList(
                schoolElem, schoolMiddle, schoolHigh, schoolDip, schoolSomeCollege, schoolTrade,
                schoolAssoc, schoolBach, schoolMaster, schoolPhD)),
                subjectDemographicInformation, "6. What is the highest degree or level of school you have completed? If currently enrolled, highest degree received");

        subjectDemographicInformation.put("7. How many times per week do you practice and/or work out?",
                timesPerWeek.getText().toString());
        subjectDemographicInformation.put("8. What is the average length of a practice or work out session?",
                avgLength.getText().toString());

        HashMap<String, Object> medicalHealthInformation = new HashMap<>();
        bundle.putSerializable("Subject Medical Health Information", medicalHealthInformation);

        addAllIfChecked(new ArrayList<>(Arrays.asList(
                concussionYes, concussionNo)), medicalHealthInformation, "9. Have you been diagnosed with a concussion in the last 6 months?");

        addAllIfChecked(new ArrayList<>(Arrays.asList(
                alcoholYes, alcoholNo)),
                medicalHealthInformation, "10. Have you consumed alcohol or any other potentially mind altering\n" +
                        "drugs or medications within the past 12 hours?");

        Map<String, Object> currentMedicines = new HashMap<>();
        medicalHealthInformation.put("Current Medicines", currentMedicines);
        Map<String, Object> ADHDMedicines = new HashMap<>();
        currentMedicines.put("a. Attention Deficit Disorder (ADD) or Attention Deficit Hyperactivity Disorder (ADHD)", ADHDMedicines);
        addAllIfChecked(new ArrayList<>(Arrays.asList(ADHDYes, ADHDNo)), ADHDMedicines, "Taken");
        ADHDMedicines.put("If Yes, which medication(s) are you taking", ADHDMedications.getText().toString());
        addAllIfChecked(new ArrayList<>(Arrays.asList(ADHDMedication24Yes, ADHDMedication24No)),
                ADHDMedicines, "Did you take this medication last 24 hours?");

        Map<String, Object> epilepsyMedicines = new HashMap<>();
        currentMedicines.put("b. Epilepsy (seizure disorder)", epilepsyMedicines);
        addAllIfChecked(new ArrayList<>(Arrays.asList(epilepsyYes, epilepsyNo)), epilepsyMedicines, "Taken");

        Map<String, Object> concussions = new HashMap<>();
        medicalHealthInformation.put("Concussion History", concussions);
        concussions.put("13a. In your lifetime, how many times have you had a concussion?",
                timesConcussion.getText().toString());
        concussions.put("13b.  When was your most recent concussion?",
                dateOfLastConc.getText().toString());

        Map<String, Object> concussionHistoryDetails = new HashMap<>(3);
        concussions.put("Concussion History Details", concussionHistoryDetails);

        Map<String, Object> concussion1 = new HashMap<>();
        concussionHistoryDetails.put("Concussion 1", concussion1);
        concussion1.put("Date (M/D/Y)", dateOfConc1.getText().toString());
        concussion1.put("Symptom Duration (# days)", duration1.getText().toString());
        addAllIfChecked(new ArrayList<>(Arrays.asList(
                injurySports1, injuryVehicle1, injuryBlunt1, injuryOther1
        )), concussion1, "Injury Mechanism");
        concussion1.put("Sport (if any)", sportName1);
        concussion1.put("How Many Days Until Return to Play", days1);
        concussion1.put("Comments", comment1);

        Map<String, Object> concussion2 = new HashMap<>();
        concussionHistoryDetails.put("Concussion 2", concussion2);
        concussion2.put("Date (M/D/Y)", dateOfConc2.getText().toString());
        concussion2.put("Symptom Duration (# days)", duration2.getText().toString());
        addAllIfChecked(new ArrayList<>(Arrays.asList(
                injurySports2, injuryVehicle2, injuryBlunt2, injuryOther2
        )), concussion2, "Injury Mechanism");
        concussion2.put("Sport (if any)", sportName2);
        concussion2.put("How Many Days Until Return to Play", days2);
        concussion2.put("Comments", comment2);

        Map<String, Object> concussion3 = new HashMap<>();
        concussionHistoryDetails.put("Concussion 3", concussion3);
        concussion3.put("Date (M/D/Y)", dateOfConc3.getText().toString());
        concussion3.put("Symptom Duration (# days)", duration3.getText().toString());
        addAllIfChecked(new ArrayList<>(Arrays.asList(
                injurySports3, injuryVehicle3, injuryBlunt3, injuryOther3
        )), concussion3, "Injury Mechanism");
        concussion3.put("Sport (if any)", sportName3);
        concussion3.put("How Many Days Until Return to Play", days3);
        concussion3.put("Comments", comment3);
    }

    private void addAllIfChecked(List<CheckBox> checkBoxes, Map<String, Object> hashMap, String question) {
        ArrayList<String> checkBoxAnswers = new ArrayList<>(checkBoxes.size());
        for (CheckBox c : checkBoxes) {
            if (c.isChecked()) {
                checkBoxAnswers.add(c.getText().toString());
            }
        }
        hashMap.put(question, String.join(", ", checkBoxAnswers));
    }

    public void onClick(View v) {
        if (v == back) {
            Intent intent = new Intent(this, ScreeningQuestionnairePart1.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }

        if (v == Continue) {
            saveInputs();
            Intent intent = new Intent(this, ScreeningQuestionnairePart3.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}