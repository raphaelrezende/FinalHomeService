package ria.inf.ufg.br.homeservice.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import ria.inf.ufg.br.homeservice.model.Usuario;

/**
 * Created by raphael on 02/05/17.
 */

public class UsuarioDAO extends SQLiteOpenHelper {

    private static final String DB_NAME = "usuarios.db";
    private static final int DB_VERSION = 2;
    private static final String TABLE_USUARIOS = "usuarios";

    //COLUMN_NAMES
    private static final String ROW_ID = "id";
    private static final String ROW_EMAIL = "email";
    private static final String ROW_SENHA = "senha";
    private static final String ROW_NOME = "nome";
    private static final String ROW_CIDADE = "cidade";

    public UsuarioDAO(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_USUARIOS + "("
                + ROW_ID + " INTEGER PRIMARY KEY," + ROW_EMAIL + " TEXT," + ROW_SENHA +
                " TEXT," + ROW_NOME + " TEXT," + ROW_CIDADE + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIOS);
        onCreate(db);
    }

    public void create(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ROW_ID,usuario.getId());
        values.put(ROW_EMAIL, usuario.getEmail());
        values.put(ROW_SENHA, usuario.getSenha());
        values.put(ROW_NOME, usuario.getNome());
        values.put(ROW_CIDADE, usuario.getCidade());


        db.insert(TABLE_USUARIOS, null, values);
        db.close();
    }

    public List<Usuario> getAll() {
        List<Usuario> usuarioList = new ArrayList<Usuario>();

        String selectQuery = "SELECT  * FROM " + TABLE_USUARIOS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Usuario usuario = new Usuario();
                usuario.setId(Integer.parseInt(cursor.getString(0)));
                usuario.setEmail(cursor.getString(1));
                usuario.setSenha(cursor.getString(2));
                usuario.setNome(cursor.getString(3));
                usuario.setCidade(cursor.getString(4));
                // Adding contact to list
                usuarioList.add(usuario);
            } while (cursor.moveToNext());
        }

        return usuarioList;
    }

    public Usuario retrieve(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USUARIOS, new String[] { ROW_ID,
                        ROW_EMAIL, ROW_SENHA, ROW_NOME, ROW_CIDADE }, ROW_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Usuario usuario = new Usuario();
        usuario.setId(Integer.parseInt(cursor.getString(0)));
        usuario.setEmail(cursor.getString(1));
        usuario.setSenha(cursor.getString(2));
        usuario.setNome(cursor.getString(3));
        usuario.setCidade(cursor.getString(4));

        return usuario;
    }

    public int update(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ROW_ID,usuario.getId());
        values.put(ROW_EMAIL, usuario.getEmail());
        values.put(ROW_SENHA, usuario.getSenha());
        values.put(ROW_NOME, usuario.getNome());
        values.put(ROW_CIDADE, usuario.getCidade());

        // updating row
        return db.update(TABLE_USUARIOS, values, ROW_ID + " = ?",
                new String[] { String.valueOf(usuario.getId()) });
    }

    public void delete(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USUARIOS, ROW_ID + " = ?",
                new String[] { String.valueOf(usuario.getId()) });
        db.close();
    }
}
