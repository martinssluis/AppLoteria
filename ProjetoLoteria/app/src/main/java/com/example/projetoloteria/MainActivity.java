package com.example.projetoloteria;

import android.os.Bundle;
import android.widget.GridView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;
import java.util.Arrays;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

private Random random;
private GridView grid;
private RelativeLayout mainLayout;
private int[] cores = { //paleta de cores do filme do coringa
        //prefixo 0xFF para depois o código hexadecimal da cor
        0xFF9E1E1D, // vermelho
        0xFF197A60, // verde
        0xFFD48800, //amarelo
        0xFF484c88 //azul
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        random = new Random();
        grid = findViewById(R.id.gridView1);
        mainLayout = findViewById(R.id.main);
        atualizarNumeros();
    }

    private void atualizarNumeros() {
        Integer[] numeros = new Integer[6];
        for (int i = 0; i < 6; i++) {
            numeros[i] = random.nextInt(100);
            for (int j = 0; j < i; j++){
                if (numeros[i].equals(numeros[j])) {
                    i--;
                    break;
                }
            }
        }
        Arrays.sort(numeros);
        ArrayAdapter<Integer> adaptador = new ArrayAdapter<>(this, R.layout.evento, R.id.numero, numeros);
        grid.setAdapter(adaptador);
    }
    public void onClick(View view) {
        atualizarNumeros();
        Toast.makeText(this, "Números Sorteados", Toast.LENGTH_SHORT).show();

        int cor = cores[random.nextInt(cores.length)];
        mainLayout.setBackgroundColor(cor);
    }
}