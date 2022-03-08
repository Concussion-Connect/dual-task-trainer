package com.github.concussionconnect.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.github.concussionconnect.Model.ConnectToDB;
import com.github.concussionconnect.Model.SymptomModel;
import com.github.concussionconnect.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDateTime;

import static android.content.ContentValues.TAG;

public class EndAllResearchActivity extends Activity implements View.OnClickListener {

    private Button submitButton;
    private HashMap<String, Object> map;
    Bundle bundle;
    DBConnector connector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_research);

        submitButton = (Button) findViewById(R.id.submitButton);
        TextView displayResults = (TextView) findViewById(R.id.displayResults);

        submitButton.setOnClickListener(this);
        displayResults.setText("");
        bundle = getIntent().getExtras();
        map = new HashMap<>();
        map.put("PARTICIPANT_NAME", bundle.getString("participantName"));
        map.put("PARTICIPANT_ID", bundle.getString("consentID"));
        map.put("TEST_DATETIME", LocalDateTime.now());

        displayResults.append("Name: " + bundle.getString("participantName") + "\n");
        displayResults.append("Test Time and Date: " + map.get("TEST_DATETIME"));

        saveQuestionnaireInfo(bundle, map);

//        displayResults.append("List ID: " + bundle.getInt("listId") + "\n");
        ArrayList<SymptomModel> symptoms = (ArrayList<SymptomModel>) getIntent().getSerializableExtra("symptoms");
        int numSymptoms = 0;
        int severityTotal = 0;
        displayResults.append("\n");
        String symptomString = "";
        for (SymptomModel x : symptoms) {
            if (x.getValue() > 0) {
                symptomString = symptomString.equals("") ? x.getSympName() + ", Severity: " + x.getValue() : symptomString + ", " + x.getSympName() + ", Severity: " + x.getValue();
                numSymptoms++;
            }
            severityTotal += x.getValue();

            map.put(("SYMPTOM" + x.getSympName().toUpperCase()).replace(" ", "").replace("'", ""), x.getValue());
        }
        displayResults.append(symptomString);
        displayResults.append("\nTotal number of symptoms: " + numSymptoms + " of " + symptoms.size() + "\n");
        displayResults.append("Symptom severity score: " + severityTotal + " of " + symptoms.size() * 6);
        displayResults.append("\n\n");

        saveTestResults(map, displayResults);

        String adminId = bundle.getString("ID");
        ConnectToDB.deleteTrainerSession(adminId,
                new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Trainer session deleted!");
                    }
                },
                new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error deleting trainer session", e);
                    }
                }
        );
        //deleteTrainerSession(id);
    }

    private void saveQuestionnaireInfo(Bundle bundle, HashMap<String, Object> map) {
        map.put("STUDY_INFO", bundle.getSerializable("studyInfo"));
        map.put("SUBJECT_CONTACT_INFO", bundle.getSerializable("contactInfo"));
    }

    @Override
    public void onClick(View v) {
        if (v == submitButton) {
            submitButton.setEnabled(false);
            if (connector == null) {
                connector = new DBConnector();
                connector.execute(null, null, null);
            }
        }
    }

    private void saveTestResults(HashMap<String, Object> map, TextView displayResults) {
        //BESS RESULTS
        displayResults.append("BESS Results: " + "\n");
        displayResults.append("BESS Double Leg Errors: " + bundle.getInt("BESSTrial1Errors") + "\n");
        map.put("BESS_DOUBLE_LEG_ERRORS", bundle.getInt("BESSTrial1Errors"));
        displayResults.append("BESS Single Leg Errors: " + bundle.getInt("BESSTrial2Errors") + "\n");
        map.put("BESS_SINGLE_LEG_ERRORS", bundle.getInt("BESSTrial2Errors"));
        displayResults.append("PAUSED 20 SECONDS... " + "\n");
        displayResults.append("BESS Tandem Leg Errors: " + bundle.getInt("BESSTrial4Errors") + "\n");
        map.put("BESS_TANDEM_LEG_ERRORS", bundle.getInt("BESSTrial4Errors"));

        //SCAT RESULTS
        displayResults.append("SCAT Results: " + "\n");
        displayResults.append("SCAT Short Term Memory Score: " + bundle.getInt("numCorrectWordsSCAT2") + "\n");
        map.put("SCAT_SHORT_MEM_SCORE", bundle.getInt("numCorrectWordsSCAT2"));
        displayResults.append("SCAT Month Memory Score: " + bundle.getString("monthsInReverseSCAT3") + "\n");
        map.put("SCAT_MONTHS_MEM_SCORE", bundle.getString("monthsInReverseSCAT3"));
        displayResults.append("SCAT Long Term Memory Score: " + bundle.getInt("numCorrectWordsSCAT4") + "\n");
        map.put("SCAT_LONG_MEM_SCORE", bundle.getInt("numCorrectWordsSCAT4"));

        //DUAL1 RESULTS
        displayResults.append("DUAL 1 Results: " + "\n");
        displayResults.append("DUAL1 Double Leg Errors: " + bundle.getInt("DoubleLegErrorsDualTrial1") + "\n");
        map.put("DUAL1_TRIAL1DOUBLELEGERRORS", bundle.getInt("DoubleLegErrorsDualTrial1"));
        displayResults.append("DUAL1 Short Term Memory Score: " + bundle.getInt("numCorrectWordsDual2") + "\n");
        map.put("DUAL1_TRIAL2MEMORYSCORE", bundle.getInt("numCorrectWordsDual2"));
        displayResults.append("DUAL1 Single Leg Errors: " + bundle.getInt("SingleLegErrorsDualTrial2") + "\n");
        map.put("DUAL1_TRIAL2SINGLELEGERRORS", bundle.getInt("SingleLegErrorsDualTrial2"));
        displayResults.append("DUAL1 Month Memory Score: " + bundle.getString("monthsInReverseDual3") + "\n");
        map.put("DUAL1_TRIAL3MEMORYSCORE", bundle.getString("monthsInReverseDual3"));
        displayResults.append("DUAL1 Tandem Leg Errors: " + bundle.getInt("TandemLegErrorsDualTrial3") + "\n");
        map.put("DUAL1_TRIAL3TANDEMLEGERRORS", bundle.getInt("TandemLegErrorsDualTrial3"));
        displayResults.append("DUAL1 Long Term Memory Score: " + bundle.getInt("numCorrectWordsDual4") + "\n");
        map.put("DUAL1_TRIAL4MEMORYSCORE", bundle.getInt("numCorrectWordsDual4"));

        //DUAL2 RESULTS
        displayResults.append("DUAL 2 Results: " + "\n");
        displayResults.append("DUAL2 Double Leg Errors: " + bundle.getInt("DoubleLegErrorsDual2Trial1") + "\n");
        map.put("DUAL2_TRIAL1DOUBLELEGERRORS", bundle.getInt("DoubleLegErrorsDual2Trial1"));
        displayResults.append("DUAL2 Short Term Memory Score: " + bundle.getInt("numCorrectWordsDual22") + "\n");
        map.put("DUAL2_TRIAL2MEMORYSCORE", bundle.getInt("numCorrectWordsDual22"));
        displayResults.append("DUAL2 Single Leg Errors: " + bundle.getInt("SingleLegErrorsDual2Trial2") + "\n");
        map.put("DUAL2_TRIAL2SINGLELEGERRORS", bundle.getInt("SingleLegErrorsDual2Trial2"));
        displayResults.append("DUAL2 Month Memory Score: " + bundle.getString("monthsInReverseDual23") + "\n");
        map.put("DUAL2_TRIAL3MEMORYSCORE", bundle.getString("monthsInReverseDual23"));
        displayResults.append("DUAL2 Tandem Leg Errors: " + bundle.getInt("TandemLegErrorsDual2Trial3") + "\n");
        map.put("DUAL2_TRIAL3TANDEMLEGERRORS", bundle.getInt("TandemLegErrorsDual2Trial3"));
        displayResults.append("DUAL2 Long Term Memory Score: " + bundle.getInt("numCorrectWordsDual24") + "\n");
        map.put("DUAL2_TRIAL4MEMORYSCORE", bundle.getInt("numCorrectWordsDual24"));
    }

    //The way that players will be hashed. Taking in their name and birthday
    public String sha1Hash(String toHash) {
        String hash = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = toHash.getBytes(StandardCharsets.UTF_8);
            digest.update(bytes, 0, bytes.length);
            bytes = digest.digest();
            hash = bytesToHex(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash;
    }

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    private class DBConnector extends AsyncTask<Void, Void, String> {
        protected String doInBackground(Void... params) {
            try {
                ConnectToDB.saveTestResult(map);
                return "EXECUTED";
            } catch (Exception e) {
                Log.wtf("MyTag", e.getMessage());
                return "FAILURE";
            }
        }

        protected void onPostExecute(String result) {
            if (result.equals("EXECUTED")) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        }
    }
}
