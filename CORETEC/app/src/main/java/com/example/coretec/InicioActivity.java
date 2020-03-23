package com.example.coretec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.internal.metrics.Tag;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.file.Files;
import java.util.Arrays;

public class InicioActivity extends AppCompatActivity {

    private Button btn;
    private Button btn1;
    private CallbackManager mCallbackManager;
    private static final String TAG = "FACELOG";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    Animation fromtop, fromBotton;
    ImageView mcore;
    ImageView mcorelogo;
    private Button bfacebook;
    private Button mgoogle;
    private SignInButton signInButton;
    private Button inicio;
    private GoogleSignInClient mGoogleSignInClient;
    private int RC_SIGN_IN = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        //ANIMACION
        mgoogle = (Button) findViewById(R.id.btn_login_google);
        mcore = (ImageView) findViewById(R.id.core);
        mcorelogo = (ImageView) findViewById(R.id.corelogo);
        btn = (Button) findViewById(R.id.btn_crear);
        btn1 = (Button) findViewById(R.id.btn_iniciar);
        bfacebook = (Button) findViewById(R.id.btn_login_facebook);

        //ANIMACIONM
        fromBotton = AnimationUtils.loadAnimation(this, R.anim.fromboton);
        fromtop = AnimationUtils.loadAnimation(this, R.anim.fromimg);
        btn1.setAnimation(fromBotton);
        btn.setAnimation(fromBotton);
        bfacebook.setAnimation(fromBotton);
        mgoogle.setAnimation(fromBotton);
        mcorelogo.setAnimation(fromtop);
        mcore.setAnimation(fromtop);


        //BOTON  ENVIAR A CREAR CUENTA
        btn = (Button) findViewById(R.id.btn_crear);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(InicioActivity.this, "¡Genial! Ahora juntos crearemos tu cuenta", Toast.LENGTH_LONG).show();
                //ACCEDER A ACTIVITY REGISTRO
                Intent btn = new Intent(InicioActivity.this, RegistroActivity.class);
                startActivity(btn);
            }
        });

        //BOTON  ENVIAR A INICIAR SESION
        btn1 = (Button) findViewById(R.id.btn_iniciar);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(InicioActivity.this, "¡Ingresa tu cuenta!", Toast.LENGTH_LONG).show();
                //ACCEDER A ACTIVITY REGISTRO
                Intent btn1 = new Intent(InicioActivity.this, LoginActivity.class);
                startActivity(btn1);
            }
        });

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        //GOOGLE FIREBASE
        inicio = (Button) findViewById(R.id.btn_login_google);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });


        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();
        //FACEBOOK BOTON PERZONALIZADO
        bfacebook = (Button) findViewById(R.id.btn_login_facebook);
        bfacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bfacebook.setEnabled(false);

                LoginManager.getInstance().logInWithReadPermissions(InicioActivity.this, Arrays.asList("email", "public_profile"));
                LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d(TAG, "facebook:onSuccess:" + loginResult);
                        handleFacebookAccessToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        Log.d(TAG, "facebook:onCancel");
                        // ...
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d(TAG, "facebook:onError", error);
                        // ...
                    }
                });
            }

        });

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user!=null){
                    startActivity(new Intent(InicioActivity.this, MenuActi.class));
                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser !=null){
            updateUI();
        }
    }

    private void updateUI() {

        Intent account = new Intent (InicioActivity.this, MenuActi.class);
        startActivity(account);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //FIREBASWE GOOGLE
        if (requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount>task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
        

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);

    }
    //GOOGLE FIREBASE
    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            String name = user.getDisplayName();
                            Toast.makeText(InicioActivity.this, "Bienvenido " + name, Toast.LENGTH_LONG).show();
                            updateUI();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(InicioActivity.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    //FACEBOOK FIREBASE
    private void handleFacebookAccessToken(AccessToken accessToken) {
        Log.d(TAG, "handleFacebookAccessToken:" + accessToken);

        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                String name = user.getDisplayName();
                                String email = user.getEmail();
                                Toast.makeText(InicioActivity.this, "Bienvenido " + name, Toast.LENGTH_LONG).show();
                                bfacebook.setEnabled(true);
                                updateUI();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithCredential:failure", task.getException());
                                Toast.makeText(InicioActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                bfacebook.setEnabled(true);
                                //updateUI();
                            }

                        }
                    }
                });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


}

