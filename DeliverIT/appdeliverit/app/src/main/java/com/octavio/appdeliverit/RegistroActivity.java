package com.octavio.appdeliverit;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.octavio.appdeliverit.Model.Cliente;
import com.sourcey.appdeliverit.R;
import com.octavio.appdeliverit.Util.Estatico;

import butterknife.ButterKnife;
import butterknife.Bind;

public class RegistroActivity extends AppCompatActivity {
    private static final String TAG = "RegistroActivity";

    @Bind(R.id.input_name) EditText _campoTextoNome;
    @Bind(R.id.input_email) EditText _campoTextoEmail;
    @Bind(R.id.input_password) EditText _campoTextoSenha;
    @Bind(R.id.input_telefone) EditText _campoTextoTelefone;
    @Bind(R.id.btn_signup) Button _botaoRegistrar;
    @Bind(R.id.link_login) TextView _linkLogin;

    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        ButterKnife.bind(this);

        _botaoRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });

        _linkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void registrar() {
        Log.d(TAG, "Registrar");

        if (!valido()) {
            registroFalhou();
            return;
        }

        _botaoRegistrar.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(RegistroActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Criando conta...");
        progressDialog.show();

        String nome = _campoTextoNome.getText().toString();
        String email = _campoTextoEmail.getText().toString();
        String senha = _campoTextoSenha.getText().toString();
        String telefone = _campoTextoTelefone.getText().toString();

        Cliente objCliente = new Cliente();
        objCliente.setNome(nome);
        objCliente.setEmail(email);
        objCliente.setSenha(senha);
        objCliente.setTelefone(telefone);
        objCliente.setLocal(Estatico.getLocal());

        objCliente.cadastraCliente(objCliente,getApplicationContext());


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        registroSucedido();
                        progressDialog.dismiss();
                    }
                }, 3000);



    }

    public void registroSucedido() {
        _botaoRegistrar.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void registroFalhou() {

        Toast.makeText(getBaseContext(), "Erro ao efetuar login", Toast.LENGTH_LONG).show();
        _botaoRegistrar.setEnabled(true);
    }

    public boolean valido() {
        boolean valido = true;

        String nome = _campoTextoNome.getText().toString();
        String email = _campoTextoEmail.getText().toString();
        String senha = _campoTextoSenha.getText().toString();
        String telefone = _campoTextoTelefone.getText().toString();


        if (nome.isEmpty() || nome.length() < 3) {
            _campoTextoNome.setError("No mínimo 3 caracteres!");
            valido = false;
        } else {
            _campoTextoNome.setError(null);
        }

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

        if(telefone.isEmpty() || !Patterns.PHONE.matcher(telefone).matches()){
            _campoTextoTelefone.setError("Digite um número de telefone válido!");
            valido = false;
        } else {
            _campoTextoTelefone.setError(null);
        }

        return valido;
    }


}