package com.example.projetocalculadorahp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btn0;

    private Button btnEnter;
    private Button btnBackSpace;
    private Button btnAdicao;
    private Button btnSubtracao;
    private Button btnMultiplicacao;
    private Button btnDivisao;
    private  Button btnClear;

    private EditText visor;

    private Button btnPv;
    private Button btnFv;
    private Button btnPmt;
    private Button btnI;
    private Button btnN;
    private Button btnVirgula;

    private Calculadora calculadora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        calculadora = new Calculadora();

        btn1 = findViewById(R.id.btnUm);
        btn2 = findViewById(R.id.btnDois);
        btn3 = findViewById(R.id.btnTres);
        btn4 = findViewById(R.id.btnQuatro);
        btn5 = findViewById(R.id.btnCInco);
        btn6 = findViewById(R.id.btnSeis);
        btn7 = findViewById(R.id.btnSete);
        btn8 = findViewById(R.id.btnOIto);
        btn9 = findViewById(R.id.btnNove);
        btn0 = findViewById(R.id.btnZero);

        btnEnter = findViewById(R.id.btnEnter);
        btnBackSpace = findViewById(R.id.btnBack);
        btnAdicao = findViewById(R.id.btnAdicao);
        btnSubtracao = findViewById(R.id.btnSubtracao);
        btnMultiplicacao = findViewById(R.id.btnMultiplicacao);
        btnDivisao = findViewById(R.id.btnDivisao);
        btnClear = findViewById(R.id.btnClear);

        btnPv = findViewById(R.id.btnPV);
        btnFv = findViewById(R.id.btnFV);
        btnPmt = findViewById(R.id.btnPMT);
        btnI = findViewById(R.id.btnI);
        btnN = findViewById(R.id.btnN);
        btnVirgula = findViewById(R.id.btnVirgula);

        visor = findViewById(R.id.visor);
        visor.setShowSoftInputOnFocus(false);


        btn1.setOnClickListener(botaoCLick("1"));
        btn2.setOnClickListener(botaoCLick("2"));
        btn3.setOnClickListener(botaoCLick("3"));
        btn4.setOnClickListener(botaoCLick("4"));
        btn5.setOnClickListener(botaoCLick("5"));
        btn6.setOnClickListener(botaoCLick("6"));
        btn7.setOnClickListener(botaoCLick("7"));
        btn8.setOnClickListener(botaoCLick("8"));
        btn9.setOnClickListener(botaoCLick("9"));
        btn0.setOnClickListener(botaoCLick("0"));

        btnEnter.setOnClickListener((v) -> {
            // double valor = Double.valueOf(visor.getText().toString());
            //calculadora.setNumero(valor);
            calculadora.enter();

        });

        btnBackSpace.setOnClickListener((v) -> {
            int inicioSelecao = visor.getSelectionStart() - 1;
            inicioSelecao = Math.max(inicioSelecao, 0);
            int finalSelecao = visor.getSelectionEnd();
            visor.getText().delete(inicioSelecao, finalSelecao);
            atualizarNumero();
        });

        btnAdicao.setOnClickListener((v) -> {
            //double valorVisor = Double.valueOf(visor.getText().toString());
            //calculadora.setNumero(valorVisor);
            calculadora.soma();
            atualizarVisor();
            //String valor = String.valueOf(calculadora.getNumero());
            //visor.setText(valor);

        });

        btnSubtracao.setOnClickListener((v) -> {
            //double valorVisor = Double.valueOf(visor.getText().toString());
            //calculadora.setNumero(valorVisor);
            calculadora.subtracao();
            //String valor = String.valueOf(calculadora.getNumero());
            //visor.setText(valor);
            atualizarVisor();
        });

        btnMultiplicacao.setOnClickListener((v) -> {
            //double valorVisor = Double.valueOf(visor.getText().toString());
            //calculadora.setNumero(valorVisor);
            calculadora.multiplicacao();
            //String valor = String.valueOf(calculadora.getNumero());
            //visor.setText(valor);
            atualizarVisor();

        });

        btnDivisao.setOnClickListener((v) -> {
            //double valorVisor = Double.valueOf(visor.getText().toString());
            //calculadora.setNumero(valorVisor);
            calculadora.divisao();
            //String valor = String.valueOf(calculadora.getNumero());
            //visor.setText(valor);
            atualizarVisor();

        });

        btnClear.setOnClickListener((v) -> {
            visor.setText("");
            calculadora = new Calculadora();
        });


        btnVirgula.setOnClickListener((v) -> {
            if (calculadora.getModo() == Calculadora.MODO_EXIBINDO){
                visor.setText("0,0");
                calculadora.setModo(Calculadora.MODO_EDITANDO);
            }
            int inicioSelecao = visor.getSelectionStart();
            int finalSelecao = visor.getSelectionEnd();
            if (calculadora.getModo() == Calculadora.MODO_ERROR) {
                visor.getText().replace(inicioSelecao, finalSelecao, "0,");
            } else {
                visor.getText().replace(inicioSelecao, finalSelecao, ",");
            }
        });

    //Parte 2
        btnPv.setOnClickListener((V) -> {
        if (calculadora.getModo() == Calculadora.MODO_EXIBINDO) {
            String valor = String.format("%.2f", calculadora.calcularPV());
            visor.setText(valor);
        }
        else {
            if (!(calculadora.getModo() == Calculadora.MODO_ERROR)) {
                String visorText = visor.getText().toString().replace(",", ".");
                double valor = Double.valueOf(visorText);
                calculadora.setPv(valor);
            } else {
                double valor = 0.0;
                calculadora.setPv(valor);
            }
            visor.setText("0,0");
        }
        calculadora.setModo(calculadora.MODO_EXIBINDO);
    });

        btnFv.setOnClickListener((V) -> {
        if (calculadora.getModo() == Calculadora.MODO_EXIBINDO) {
            String valor = String.format("%.2f", calculadora.calcularFV());
            visor.setText(valor);
        }
        else {
            if (!(calculadora.getModo() == Calculadora.MODO_ERROR)) {
                String visorText = visor.getText().toString().replace(",", ".");
                double valor = Double.valueOf(visorText);
                calculadora.setFv(valor);
            } else {
                double valor = 0.0;
                calculadora.setFv(valor);
            }
            visor.setText("0,0");
        }
        calculadora.setModo(calculadora.MODO_EXIBINDO);
    });

        btnPmt.setOnClickListener((V) -> {
        if (calculadora.getModo() == Calculadora.MODO_EXIBINDO) {
            String valor = String.format("%.2f", calculadora.calcularPmt());
            visor.setText(valor);
        }
        else {
            if (!(calculadora.getModo() == Calculadora.MODO_ERROR)) {
                String visorText = visor.getText().toString().replace(",", ".");
                double valor = Double.valueOf(visorText);
                calculadora.setPmt(valor);
            } else {
                double valor = 0.0;
                calculadora.setPmt(valor);
            }
            visor.setText("0,0");
        }
        calculadora.setModo(calculadora.MODO_EXIBINDO);
    });

        btnI.setOnClickListener((V) -> {
        if (calculadora.getModo() == Calculadora.MODO_EXIBINDO) {
            String valor = String.format("%.2f", calculadora.calcularI());
            visor.setText(valor);
        }
        else {
            if (!(calculadora.getModo() == Calculadora.MODO_ERROR)) {
                String visorText = visor.getText().toString().replace(",", ".");
                double valor = Double.valueOf(visorText);
                calculadora.seti(valor/100);
            } else {
                double valor = 0.0;
                calculadora.seti(valor/100);
            }
            visor.setText("0,0");
        }
        calculadora.setModo(calculadora.MODO_EXIBINDO);
    });

        btnN.setOnClickListener((V) -> {
        if (calculadora.getModo() == Calculadora.MODO_EXIBINDO) {
            String valor = String.format("%.2f", calculadora.calcularN());
            visor.setText(valor);
        }
        else {
            if (!(calculadora.getModo() == Calculadora.MODO_ERROR)) {
                String visorText = visor.getText().toString().replace(",", ".");
                double valor = Double.valueOf(visorText);
                calculadora.setn(valor);
            } else {
                double valor = 0.0;
                calculadora.setn(valor);
            }
            visor.setText("0,0");
        }
        calculadora.setModo(calculadora.MODO_EXIBINDO);
    });


}

    public View.OnClickListener botaoCLick(final String s) {
        return (v) -> {
            if(calculadora.getModo()== Calculadora.MODO_EXIBINDO){
                visor.setText("");
            }
            int  inicioSelecao = visor.getSelectionStart();
            int finalSelecao = visor.getSelectionEnd();
            visor.getText().replace(inicioSelecao, finalSelecao, s);
            atualizarNumero();
        };
    }

    public void atualizarNumero(){
        String s = visor.getText().toString();
        s= "".equals(s) ? "0" : s;
        calculadora.setNumero(Double.valueOf(s));
    }

    public void atualizarVisor(){
        double numero = calculadora.getNumero();
        visor.setText(String.format("%f", numero));
    }



}