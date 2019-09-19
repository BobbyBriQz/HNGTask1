package com.appsbygreatness.hngtask1;

import android.app.Activity;
import androidx.annotation.NonNull;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.List;

import static com.appsbygreatness.hngtask1.MainActivity.RC_SIGN_IN;

public class FireBaseUtils {


    private static FireBaseUtils firebaseUtils;
    public static FirebaseAuth firebaseAuth;
    public static FirebaseAuth.AuthStateListener authStateListener;
    public static Activity caller;


    private FireBaseUtils(){
    }


    public static void attachListener(){
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    public static void dettachListener(){
        firebaseAuth.removeAuthStateListener(authStateListener);
    }

    private static void signIn() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build());

        // Create and launch sign-in intent
        caller.startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setIsSmartLockEnabled(false)
                        .build(),
                RC_SIGN_IN);
    }


    public static void setUpSignIn(final Activity callerActivity) {
        if (firebaseUtils == null) {
            firebaseUtils = new FireBaseUtils();


            firebaseAuth = FirebaseAuth.getInstance();
            caller = callerActivity;

            authStateListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    if (firebaseAuth.getCurrentUser() == null) {
                        FireBaseUtils.signIn();

                    }

                }
            };
        }


    }





}

