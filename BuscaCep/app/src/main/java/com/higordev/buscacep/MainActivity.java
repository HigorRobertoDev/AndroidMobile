package com.higordev.buscacep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewHolder.buttonBuscaCep = findViewById(R.id.button_busca_cep);
        this.mViewHolder.editCep = findViewById(R.id.edit_cep);
        this.mViewHolder.numCep = findViewById(R.id.num_cep);
        this.mViewHolder.logradouro = findViewById(R.id.logradouro);
        this.mViewHolder.complemento = findViewById(R.id.complemento);
        this.mViewHolder.bairro = findViewById(R.id.bairro);
        this.mViewHolder.localidade = findViewById(R.id.localidade);
        this.mViewHolder.uf = findViewById(R.id.uf);
        this.mViewHolder.unidade = findViewById(R.id.unidade);
        this.mViewHolder.ibge = findViewById(R.id.ibge);
        this.mViewHolder.gia = findViewById(R.id.gia);

        this.mViewHolder.buttonBuscaCep.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_busca_cep) {
            String cpf = this.mViewHolder.editCep.getText().toString();
            if ("".equals(cpf)) {
                Toast.makeText(this, "Digite o cep!", Toast.LENGTH_LONG).show();
            } else if (cpf.length() != 8) {
                Toast.makeText(this, "Cep deve ter 8 caracteres!", Toast.LENGTH_LONG).show();
            } else {

                try {
                    Cep cep = new HttpService(cpf).execute().get();
                    this.setFieldsCep(cep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private void setFieldsCep(Cep cep) {
        this.mViewHolder.numCep.setText("CEP: " +cep.getCep());
        this.mViewHolder.logradouro.setText("LOGRADOURO: " + cep.getLogradouro());
        this.mViewHolder.complemento.setText("COMPLEMENTO: " + cep.getComplemento());
        this.mViewHolder.bairro.setText("BAIRRO: " + cep.getBairro());
        this.mViewHolder.localidade.setText("LOCALIDADE: " + cep.getLocalidade());
        this.mViewHolder.uf.setText("UF: " + cep.getUf());
        this.mViewHolder.unidade.setText("UNIDADE: " + cep.getUnidade());
        this.mViewHolder.ibge.setText("IBGE: " + cep.getIbge());
        this.mViewHolder.gia.setText("GIA: " + cep.getGia());
    }

    private static class ViewHolder {
        EditText editCep;
        Button buttonBuscaCep;
        TextView numCep;
        TextView logradouro;
        TextView complemento;
        TextView bairro;
        TextView localidade;
        TextView uf;
        TextView unidade;
        TextView ibge;
        TextView gia;
    }

}
