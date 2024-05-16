package com.tarea7.examen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //listar los componente de la vista que vamos a interacturar
    private EditText et_pricio,et_num_entrada;
    private Spinner sp_partido;
    private Button btn_listar,btn_registrar;
    private RadioButton rb1,rb2;

    //variables locales
    private int precio,entradas;

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

        sp_partido=findViewById(R.id.sp_partido);
        et_pricio=findViewById(R.id.et_precio);
        et_num_entrada=findViewById(R.id.et_n_entradas);
        rb1=findViewById(R.id.rb1);
        rb2=findViewById(R.id.rb2);
        btn_registrar=findViewById(R.id.btn_registrar);
        btn_listar=findViewById(R.id.btn_listado);

        cambiarPrecio();
        anadirlistener();

        cargarSpiner(sp_partido,R.array.array_partidos);
        listenerbtnListar();
        listenerbtnResgistro();
        et_num_entrada.setText(2+"");
    }


    //mis metodos
    public void cambiarPrecio(){
        if (rb1.isChecked()){
            precio=20;
        }else {
            precio=40;
        }
        et_pricio.setText(precio+"€");
    }

    public void cargarSpiner(Spinner sp, int array){
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,array, android.R.layout.simple_spinner_item);
        sp.setAdapter(adapter);
    }
    public void anadirlistener(){
        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarPrecio();
            }
        });

        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarPrecio();
            }
        });
    }

    public void listenerbtnResgistro(){


        btn_registrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (entreadaSinDatos()){
                    Intent i = new Intent(getApplicationContext(), pantallaRegistro.class);

                    //añadir datos a enviar a la segunda pantalla
                    i.putExtra("precio",precio);
                    i.putExtra("entradas",entradas);
                    i.putExtra("partido",sp_partido.getSelectedItem().toString());
                    i.putExtra("grada",rb1.getText().toString());

                    startActivity(i);
                }
            }
        });

    }

    public void listenerbtnListar(){
        btn_listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), pantallaListado.class);

                startActivity(i);

            }
        });
    }
    public boolean entreadaSinDatos(){
        if(et_num_entrada.getText().toString().isEmpty()||et_num_entrada.getText().toString().equalsIgnoreCase("0")){
            Toast.makeText(this,"Introduce numero de entrada para continuar", Toast.LENGTH_LONG).show();
            return false;
        }
        entradas=Integer.parseInt(et_num_entrada.getText().toString());
        return true;
    }
}