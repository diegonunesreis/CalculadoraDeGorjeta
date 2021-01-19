package com.example.calculadoradegorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private SeekBar seekBarPorcentagem;
    private EditText editTextConta;
    private TextView textViewPorcentagem, textViewGorjeta, textViewTotal;
    private int porcentagem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBarPorcentagem = findViewById(R.id.seekBarPorcentagem);
        editTextConta = findViewById(R.id.editTextConta);
        textViewPorcentagem = findViewById(R.id.textViewValorPorcentagem);
        textViewGorjeta = findViewById(R.id.textViewValorGorjeta);
        textViewTotal = findViewById(R.id.textViewValorTotal);

        seekBarPorcentagem.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                porcentagem = progress;
                textViewPorcentagem.setText(porcentagem + "%");
                calcular();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void calcular(){
        String valorContaString = editTextConta.getText().toString();
        NumberFormat formatter = new DecimalFormat("###,###,##0.00");
        if(!"".equals(valorContaString)){
            Double valorConta = Double.parseDouble(valorContaString);
            if(valorConta > 0.0){
                Double gorjeta = (valorConta * porcentagem) / 100;
                Double total = valorConta + gorjeta;
                textViewGorjeta.setText("R$ " + formatter.format(gorjeta));
                textViewTotal.setText("R$ " + formatter.format(total));
            }
        }
        else{
            Toast.makeText(
                    getApplicationContext(),
                    "Digite um valor primeiro",
                    Toast.LENGTH_LONG
            ).show();
        }
    }
}