package com.example.lista_optimizadas;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DB_juegos extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "DBJuegos.db";
    private static final int DATABASE_VERSION = 1;

    private Context contexto;

    public DB_juegos(Context contexto){
        super(contexto, DATABASE_NAME, null, DATABASE_VERSION);
        this.contexto = contexto;
    }

    private String SQLCREATE = "CREATE TABLE Videojuegos (Nombre TEXT, Genero TEXT)";

    private SQLiteDatabase bd = null;

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLCREATE);
    }

    private String SQLDROP = "DROP TABLE IF EXISTS Videojuegos";

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int preVersion, int NewVersion){
        sqLiteDatabase.execSQL(SQLDROP);
        sqLiteDatabase.execSQL(SQLCREATE);
    }

    public void cerrarDB() {
        if (bd !=  null)
        {
            bd.close();
        }
    }

    public void insertarVideojuego(){
        bd =  getWritableDatabase();

        if (bd != null) {
            ContentValues datos = new ContentValues();
            datos.put("Nombre", "BLACK OPS 3");
            datos.put("Genero", "Accion");
            bd.insert("Juegos", "", datos);
            close();
        }
    }
    private String SQLDELETE="DELETE FROM Videojuegos WHERE Genero='Accion'";
    public void BorrarVideojuego(){
        bd = getWritableDatabase();

        if(bd != null){
            bd.execSQL(SQLDELETE);
            close();
        }
    }
    public void actualizarVideojuego() {
        bd = getWritableDatabase();

        if (bd != null){
            ContentValues datos = new ContentValues();
            datos.put("Nombre", "BLACK OPS 3");
            datos.put("Genero", "Accion");

            String where = "Nombre = ?";
            String[] argumentosNuevos = {"BLACK OPS 3"};
            bd.update("Videojuegos", datos, where, argumentosNuevos);
            close();
        }
    }

    public ArrayList<Videojuegos> obetenerTodosLosVideojuegos(){
        bd = getReadableDatabase();

        ArrayList<Videojuegos> videojuego = new ArrayList<>();

        Cursor c = bd.query(
                "Videojuegos",
                null,
                null,
                null,
                null,
                null,
                null,
                null

        );
    }
}