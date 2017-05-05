package ria.inf.ufg.br.homeservice.web;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import okhttp3.Response;
import ria.inf.ufg.br.homeservice.model.Categoria;

/**
 * Created by raphael on 05/05/17.
 */

public class WebListaCategorias extends WebConnection {
    private static final String SERVICENAME = "categorias";

    private String token;

    public WebListaCategorias(String token) {
        super(SERVICENAME);
        this.token = token;
    }

    public WebListaCategorias(){
        super(SERVICENAME);
    }

    @Override
    String getRequestContent() {
        Map<String,String> requestMap = new HashMap<>();
        requestMap.put("token", token);

        JSONObject json = new JSONObject(requestMap);
        String jsonString = json.toString();

        return  jsonString;
    }

    @Override
    void handleResponse(Response response) {
        String responseBody = null;
        List<Categoria> categorias = new LinkedList<>();
        try {
            responseBody = response.body().string();

            JSONObject jsonObject = new JSONObject(responseBody);
            JSONArray arrayCategoriasJSON = jsonObject.getJSONArray("categorias");
            for(int pos=0; pos < arrayCategoriasJSON.length(); pos++){
                JSONObject categoriaAsJSON = arrayCategoriasJSON.getJSONObject(pos);
                Categoria categoria = new Categoria();
                categoria.setDescricao(categoriaAsJSON.getString("descricao"));
                categoria.setNome(categoriaAsJSON.getString("nome"));
                categoria.setId(Integer.parseInt(categoriaAsJSON.getString("id")));
                categorias.add(categoria);
            }
            EventBus.getDefault().post(categorias);
        } catch (IOException e) {
            EventBus.getDefault().post(e);
        } catch (JSONException e) {
            EventBus.getDefault().post(new Exception("A resposta do servidor não é válida"));
        }

    }
}
