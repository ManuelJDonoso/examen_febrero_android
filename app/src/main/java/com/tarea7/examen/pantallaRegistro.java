package com.tarea7.examen;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class pantallaRegistro extends AppCompatActivity {


   private TextView tv_DatosObtenidos;
    private Button btn_confirmar,btn_cancelar;

    private int entradas,precio,total,total_ticket, nTicket;
    private String partido,datos;


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

        cargar();
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
        nTicket=total_ticket;
         datos="Ticket nº:"+nTicket+"\n" +
                "Partido: "+partido+"\n" +
                "Nº de Entradas: "+entradas+"\n" +
                "Precio: "+precio+"\n" +
                "TOTAL: "+total+"€";
        tv_DatosObtenidos.setText(datos);
    };

    public void listenerBotones() {
        btn_confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               guardar();
               finish();
            }
        });

        btn_cancelar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                reset();
                finish();
            }
        });
    }

    public void cargar(){
        int ultimo;
        SharedPreferences sp=getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        total_ticket=sp.getInt("total_ticket",0);
        ultimo=total_ticket-1;
        datos=sp.getString(ultimo+"","sin datos");
        Toast.makeText(this,ultimo+"",Toast.LENGTH_LONG).show();
        Toast.makeText(this,datos,Toast.LENGTH_LONG).show();



    }
    public void guardar(){
        SharedPreferences sp=getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString(total_ticket+"",datos);
        total_ticket++;
        editor.putInt("total_ticket",total_ticket);



        editor.commit();
    }
    public void reset(){
        SharedPreferences sp=getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        // Toast.makeText(this,nTicket+"",Toast.LENGTH_LONG).show();
        total_ticket=1;
        editor.putInt("total_ticket",total_ticket);
        editor.putString(total_ticket+"",datos);


        editor.commit();
    }
}