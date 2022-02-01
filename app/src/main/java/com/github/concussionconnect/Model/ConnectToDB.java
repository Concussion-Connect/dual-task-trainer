package com.github.concussionconnect.Model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static android.content.ContentValues.TAG;

/**
 * Created by unkadi on 11/28/17.
 *
 * 7 November 2020
 * Functions in this file use a Firebase instance managed by VIP BTAP (Brain Trauma Assessment Protocol).
 */

public class ConnectToDB {

    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String sessionsDbCollectionName = "sessions";
    private static final String resultsDbCollectionName = "results";

    public static void saveTestResult(Map<String, Object> params) {
        db.collection(resultsDbCollectionName).add(params);
    }

    public static void getSessionReference(
        String id,
        OnSuccessListener<DocumentSnapshot> successCallback,
        OnFailureListener failureCallback
    ) {
        db.collection(sessionsDbCollectionName).document(id).get()
            .addOnSuccessListener(successCallback)
            .addOnFailureListener(failureCallback);
    }

    // Creates a trainer session to indicate an in-progress trial.
    // If this trainer session already exists, it will be overridden -- beware!
    // Note that this success callback returns Void, and not DocumentReference
    public static void createTrainerSession(
        String id,
        int wordList,
        int currentTrial,
        String testType,
        boolean sessionFinished,
        OnSuccessListener<Void> successCallback,
        OnFailureListener failureCallback
    ) {
        // Create a new user with a first and last name
        Map<String, Object> dataToSave = new HashMap<>();
        dataToSave.put("wordList", wordList);
        dataToSave.put("currentTrial", currentTrial);
        dataToSave.put("testType", testType);
        dataToSave.put("sessionFinished", sessionFinished);

        // Add a new document with a generated ID
        db.collection(sessionsDbCollectionName)
            .document(id)
            .set(dataToSave)
            .addOnSuccessListener(successCallback)
            .addOnFailureListener(failureCallback);
    }

    public static void updateTrainerSessionTrial(
        String id,
        int currentTrial,
        OnSuccessListener<Void> successCallback,
        OnFailureListener failureCallback
    ) {
        // Set the "isCapital" field of the city 'DC'
        db.collection(sessionsDbCollectionName).document(id)
            .update("currentTrial", currentTrial)
            .addOnSuccessListener(successCallback)
            .addOnFailureListener(failureCallback);
    }

    public static void updateTrainerSessionTrial(
            String id,
            int currentTrial,
            String testType,
            OnSuccessListener<Void> successCallback,
            OnFailureListener failureCallback
    ) {
        // Set the "isCapital" field of the city 'DC'
        db.collection(sessionsDbCollectionName).document(id)
                .update(
                        "currentTrial", currentTrial,
                        "testType", testType)
                .addOnSuccessListener(successCallback)
                .addOnFailureListener(failureCallback);
    }

    public static void deleteTrainerSession(
        String id,
        OnSuccessListener<Void> successCallback,
        OnFailureListener failureCallback
    ) {
        // Set the "isCapital" field of the city 'DC'
        db.collection(sessionsDbCollectionName).document(id)
            .delete()
            .addOnSuccessListener(successCallback)
            .addOnFailureListener(failureCallback);
    }
}
