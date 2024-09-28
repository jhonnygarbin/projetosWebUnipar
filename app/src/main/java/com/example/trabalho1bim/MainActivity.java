package com.example.trabalho1bim;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etEmail;
    private EditText etIdade;
    private EditText etDisciplina;
    private EditText etNota1;
    private EditText etNota2;
    private Button btnEnviar;
    private Button btnLimpar;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNome = findViewById(R.id.etNome);
        etEmail = findViewById(R.id.etEmail);
        etIdade = findViewById(R.id.etIdade);
        etDisciplina = findViewById(R.id.etDisciplina);
        etNota1 = findViewById(R.id.etNota1);
        etNota2 = findViewById(R.id.etNota2);
        btnEnviar = findViewById(R.id.btnEnviar);
        btnLimpar = findViewById(R.id.btnLimpar);
        tvResultado = findViewById(R.id.tvResultado);

        btnEnviar.setOnClickListener(v -> {
            if (validarCampos()) {
                exibirDados();
            }
        });

        btnLimpar.setOnClickListener(v -> limparCampos());
    }

    private boolean validarCampos() {
        String nome = etNome.getText().toString();
        String email = etEmail.getText().toString();
        String idade = etIdade.getText().toString();
        String nota1 = etNota1.getText().toString();
        String nota2 = etNota2.getText().toString();

        if (nome.isEmpty()) {
            showToast("O campo de nome está vazio");
            return false;
        }
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showToast("O email é inválido");
            return false;
        }
        if (idade.isEmpty() || !isNumeric(idade) || Integer.parseInt(idade) <= 0) {
            showToast("A idade deve ser um número positivo");
            return false;
        }
        if (nota1.isEmpty() || !isNumeric(nota1) || Float.parseFloat(nota1) < 0 || Float.parseFloat(nota1) > 10) {
            showToast("A nota do 1º bimestre deve ser um número entre 0 e 10");
            return false;
        }
        if (nota2.isEmpty() || !isNumeric(nota2) || Float.parseFloat(nota2) < 0 || Float.parseFloat(nota2) > 10) {
            showToast("A nota do 2º bimestre deve ser um número entre 0 e 10");
            return false;
        }
        return true;
    }

    private void exibirDados() {
        String nome = etNome.getText().toString();
        String email = etEmail.getText().toString();
        String idade = etIdade.getText().toString();
        String disciplina = etDisciplina.getText().toString();
        float nota1 = Float.parseFloat(etNota1.getText().toString());
        float nota2 = Float.parseFloat(etNota2.getText().toString());
        float media = (nota1 + nota2) / 2;
        String status = media >= 6 ? "Aprovado" : "Reprovado";

        String resultado = "Nome: " + nome + "\n" +
                "Email: " + email + "\n" +
                "Idade: " + idade + "\n" +
                "Disciplina: " + disciplina + "\n" +
                "Notas 1º e 2º Bimestres: " + nota1 + ", " + nota2 + "\n" +
                "Média: " + media + "\n" +
                "Status: " + status;

        tvResultado.setText(resultado);
    }

    private void limparCampos() {
        etNome.setText("");
        etEmail.setText("");
        etIdade.setText("");
        etDisciplina.setText("");
        etNota1.setText("");
        etNota2.setText("");
        tvResultado.setText("");
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}