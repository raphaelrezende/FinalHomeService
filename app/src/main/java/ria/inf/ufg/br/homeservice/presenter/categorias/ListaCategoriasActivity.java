package ria.inf.ufg.br.homeservice.presenter.categorias;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.List;

import ria.inf.ufg.br.homeservice.BaseActivity;
import ria.inf.ufg.br.homeservice.R;
import ria.inf.ufg.br.homeservice.data.CategoriaDAO;
import ria.inf.ufg.br.homeservice.model.Categoria;

public class ListaCategoriasActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_categorias);

        initView();
        criaBotaoVoltar();
    }

    public void initView(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ListaCategoriasFragment fragment = new ListaCategoriasFragment();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }
}
