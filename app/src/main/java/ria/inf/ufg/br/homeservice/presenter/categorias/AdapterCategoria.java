package ria.inf.ufg.br.homeservice.presenter.categorias;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import ria.inf.ufg.br.homeservice.R;
import ria.inf.ufg.br.homeservice.model.Categoria;


public class AdapterCategoria extends RecyclerView.Adapter<AdapterCategoria.CategoriaViewHolder> {

    /**
     * List of categorias
     */
    private List<Categoria> categorias;

    /**
     * The application context
     */
    private Context context;

    public AdapterCategoria(List<Categoria> categorias, Context context){
        this.categorias = categorias;
        this.context = context;
    }

    @Override
    public AdapterCategoria.CategoriaViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.categorias_linha, viewGroup, false);
        AdapterCategoria.CategoriaViewHolder viewHolder = new AdapterCategoria.CategoriaViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CategoriaViewHolder holder, int position) {
        final Categoria categoria = categorias.get(position);
        holder.nameView.setText(categoria.getNome());
        holder.descriptionView.setText(categoria.getDescricao());
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                abreDescricao(categoria);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }

    public List<Categoria> getcategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public static class CategoriaViewHolder
            extends RecyclerView.ViewHolder {
        TextView nameView;
        TextView descriptionView;

        CategoriaViewHolder(View itemView) {
            super(itemView);
            nameView = (TextView)itemView.findViewById(R.id.label_categoria_title);
            descriptionView = (TextView)itemView.findViewById(R.id.label_categoria_desc);
        }
    }

//    private void abreDescricao(Categoria categoria) {
//        Intent intent = new Intent(this.context, CategoriaDetailActivity.class);
//        EventBus.getDefault().postSticky(categoria);
//        context.startActivity(intent);
//
//
//    }

}