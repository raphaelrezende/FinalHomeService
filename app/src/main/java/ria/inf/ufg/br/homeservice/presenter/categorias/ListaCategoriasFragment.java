package ria.inf.ufg.br.homeservice.presenter.categorias;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.LinkedList;
import java.util.List;

import ria.inf.ufg.br.homeservice.BaseFragment;
import ria.inf.ufg.br.homeservice.R;
import ria.inf.ufg.br.homeservice.data.CategoriaDAO;
import ria.inf.ufg.br.homeservice.model.Categoria;
import ria.inf.ufg.br.homeservice.web.WebListaCategorias;


public class ListaCategoriasFragment extends BaseFragment {
    private List<Categoria> categoriaList;
    private AdapterCategoria adapter;


    public ListaCategoriasFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_categorias, container, false);

        categoriaList = new LinkedList<>();
        //setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        initRecycler();
        getCategorias();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void initRecycler() {
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new AdapterCategoria(categoriaList, getActivity());
        recyclerView.setAdapter(adapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Exception exception) {
        dismissDialog();
        showAlert(exception.getMessage());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(List<Categoria> categorias) {
        dismissDialog();
        adapter.setCategorias(categorias);
        adapter.notifyDataSetChanged();
    }


    private void getCategorias() {
        showDialogWithMessage(getString(R.string.load_categorias));
        CategoriaDAO categoriaDAO = new CategoriaDAO(getActivity());
        adapter.setCategorias(categoriaDAO.getAll());
        adapter.notifyDataSetChanged();
        dismissDialog();
    }
}
