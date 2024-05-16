package com.tarea7.examen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class pantallaRegistro extends AppCompatActivity {


   private TextView tv_DatosObtenidos;
    private Button btn_confirmar,btn_cancelar;

    private int entradas,precio,total;
    private String partido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pantalla_registro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tv_DatosObtenidos=findViewById(R.id.tvDatos);
        btn_confirmar=findViewById(R.id.btn_confirmar);
        btn_cancelar=findViewById(R.id.btn_cancelar);

        CapturarDatos();
        modificarTvDatos();
        listenerBotones();
    }

    private void CapturarDatos(){
        Bundle bundle=getIntent().getExtras();
        entradas=bundle.getInt("entradas");
        precio=bundle.getInt("precio");
        total= entradas*precio;
        partido=bundle.getString("partido").toUpperCase();
    }

    public void modificarTvDatos(){
        String datos="Partido: "+partido+"\n" +
                "Nº de Entradas: "+entradas+"\n" +
                "Precio: "+precio+"\n" +
                "TOTAL: "+total+"€";
        tv_DatosObtenidos.setText(datos);
    };

    public void listenerBotones() {
        btn_confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}