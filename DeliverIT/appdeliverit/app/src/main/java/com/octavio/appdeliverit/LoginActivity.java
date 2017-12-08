package com.octavio.appdeliverit;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.octavio.appdeliverit.Model.Cliente;
import com.sourcey.appdeliverit.R;

import butterknife.ButterKnife;
import butterknife.Bind;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_REGISTRO = 0;

    @Bind(R.id.input_email) EditText _campoTextoEmail;
    @Bind(R.id.input_password) EditText _campoTextoSenha;
    @Bind(R.id.btn_login) Button _botaoLogar;
    @Bind(R.id.link_signup) TextView _linkRegistrar;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrar);
        ButterKnife.bind(this);
        Log.d(TAG, "onCreate");

        _botaoLogar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick - Logar");
                login();

            }
        });

        _linkRegistrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick - LinkRegistrar");
                Intent intent = new Intent(getApplicationContext(), RegistroActivity.class);
                startActivityForResult(intent, REQUEST_REGISTRO);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!valido()) {
            onLoginFailed();
            return;
        }

        _botaoLogar.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Autenticando...");
        progressDialog.show();

        String email = _campoTextoEmail.getText().toString();
        String senha = _campoTextoSenha.getText().toString();

        Cliente login = new Cliente();
            if(login.login(email,senha,getApplicationContext()))
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            else
                onLoginFailed();


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        onLoginSuccess();
                        progressDialog.dismiss();
                    }
                }, 3000);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_REGISTRO) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(getBaseContext(),"Registro efetuado com sucesso!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _botaoLogar.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Falha ao logar!", Toast.LENGTH_LONG).show();
        _botaoLogar.setEnabled(true);
    }

    public boolean valido() {
        boolean valido = true;

        String email = _campoTextoEmail.getText().toString();
        String senha = _campoTextoSenha.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _campoTextoEmail.setError("Entre com um e-mail válido!");
            valido = false;
        } else {
            _campoTextoEmail.setError(null);
        }

        if (senha.isEmpty() || senha.length() < 4 || senha.length() > 10) {
            _campoTextoSenha.setError("A senha deve conter de 4 à 10 caracteres!");
            valido = false;
        } else {
            _campoTextoSenha.setError(null);
        }
        return true;
    }
}
