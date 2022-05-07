package exa.ycf.expbdd_jv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

class EtudiantDataControl {

    // Champs de la base de données
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private String[] allColumns = {DBHelper.ETUDIANT_ID, DBHelper.ETUDIANT_NAME,
            DBHelper.ETUDIANT_NOTE};

    public EtudiantDataControl(Context context) {
        dbHelper = new DBHelper(context);
        open();
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Long addEtudiant(Etudiant etud) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.ETUDIANT_NAME, etud.getName());
        values.put(dbHelper.ETUDIANT_NOTE, etud.getNote());
        long insertId = database.insert(dbHelper.TABLE_NAME, null, values);
        return insertId;
    }

    public int updateEtudiant(Etudiant etud) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.ETUDIANT_NAME, etud.getName());
        values.put(dbHelper.ETUDIANT_NOTE, etud.getNote());
        int nbr = database.update(dbHelper.TABLE_NAME, values, dbHelper.ETUDIANT_ID
                + " = ?", new String[]{String.valueOf(etud.getId())});
        return nbr;
    }

    public void deleteEtudiant(long id) {
        database.delete(dbHelper.TABLE_NAME, dbHelper.ETUDIANT_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public Etudiant findEtudiant(long id) {
        Cursor c = database.query(dbHelper.TABLE_NAME, allColumns,
                dbHelper.ETUDIANT_ID + " = ?", new String[]{String.valueOf(id)},
                null, null, null);
        Etudiant etud = new Etudiant(c.getLong(0), c.getString(1), c.getDouble(2));
        c.close();
        return etud;
    }

    public Cursor readAll(){
        String query = "SELECT * FROM " + dbHelper.TABLE_NAME;
        return database.rawQuery(query,null);
    }

    public void deleteAll(){
        String query = "DELETE FROM " + dbHelper.TABLE_NAME;
        database.execSQL(query);
    }

}
