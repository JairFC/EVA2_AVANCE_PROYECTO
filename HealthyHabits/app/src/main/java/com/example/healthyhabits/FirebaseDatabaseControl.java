package com.example.healthyhabits;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;


public class FirebaseDatabaseControl {

    private static Query mQuery;
    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;
    private static AdapterEmergency adapterRoutine;
    private static ArrayList<EmergencyObject> mAdapterItems;
    private static ArrayList<String> mAdapterKeys;

    public static DatabaseReference getDatabaseReference() {
        return databaseReference;
    }

    public static void setUpDataBase() {
        if (firebaseDatabase == null) {
            firebaseDatabase = FirebaseDatabase.getInstance();
            //firebaseDatabase.setPersistenceEnabled(true);
            databaseReference = firebaseDatabase.getReference();
        }
    }

    public static Query getQuery(String userId) {
        mQuery = databaseReference.child("Users").child("Customers").child(userId).child("Emergencias");
        return mQuery;
    }

    public static AdapterEmergency Adapter(String userId) {
        if (adapterRoutine == null) {
            adapterRoutine = new AdapterEmergency(getQuery(userId), EmergencyObject.class, mAdapterItems, mAdapterKeys);
            return adapterRoutine;
        } else {
            return adapterRoutine;
        }
    }
}
