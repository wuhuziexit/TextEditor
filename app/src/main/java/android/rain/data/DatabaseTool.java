package android.rain.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Arrays;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

public class DatabaseTool {
    private DatabaseHelper dbHelper;
    private String table;

    public DatabaseTool(DatabaseHelper dbHelper, String table) {
        this.dbHelper = dbHelper;
        this.table = table;
    }

    public DatabaseTool(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    /**
     * 添加数据的方法
     *
     * @param table   要添加的数据表
     * @param adds    字段
     * @param addData 字段对应数据
     */
    public void insert(String table, String[] adds, String[] addData) {
        //添加数据
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        for (int i = 0; i < adds.length; i++) {
            values.put(adds[i], addData[i]);
        }
        long newRowId = db.insert(table, null, values);
        Log.d(TAG, "用户" + Arrays.toString(addData) + "插入成功。行ID: " + newRowId);

        db.close();
    }


    /**
     * 修改数据的方法
     *
     * @param r           待修改字段匹配的数据  account
     * @param whereClause "account = ?"
     * @param rUP         待修改字段名
     * @param newRUP      要修改的字段数据
     */
    public void update(String r, String whereClause, String rUP, String newRUP) {
//        修改数据
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(rUP, newRUP);

        int rowsAffected = db.update(table, values, whereClause, new String[]{r});
        Log.d(TAG, "用户更新成功。受影响的行: " + rowsAffected);

        db.close();
    }

    public void delete(String account) {
//        删除
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int rowsAffected = db.delete("users", "account = ?", new String[]{account});
        Log.d(TAG, "用户删除成功。受影响的行: " + rowsAffected);

        db.close();
    }

    /**
     * @param selectIS   匹配的字段数据
     * @param projection 字段集合
     * @param selection  查询 "selectIS = ?"
     *                   cursor.moveToFirst()    游标移动到第一行
     *                   .moveToNext() 游标移动到下一行
     */
    public void get(String selectIS, String[] projection, String selection, String selectR) {
//        查询
        SQLiteDatabase db = dbHelper.getReadableDatabase();

//        String[] projection = {"selectIS", "password"};
//        String selection = "selectIS = ?";
        String[] selectionArgs = {selectIS};

        Cursor cursor = db.query(table, projection, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            String author = cursor.getString(cursor.getColumnIndexOrThrow(selectR));
            Log.d(TAG, "Account: " + selectIS + ", Password: " + author);
        } else {
            Log.d(TAG, "找不到用户.");
        }

        cursor.close();
        db.close();
    }

    /**
     * @param selectIS   匹配的字段
     * @param projection 字段集合
     * @param selection  查询 "selectIS = ?"
     *                   cursor.moveToFirst()    游标移动到第一行
     *                   .moveToNext() 游标移动到下一行
     */
    public Cursor get(String selectIS, String[] projection, String selection) {
//        查询
        SQLiteDatabase db = dbHelper.getReadableDatabase();

//        String[] projection = {"selectIS", "password"};
//        String selection = "selectIS = ?";
        String[] selectionArgs = {selectIS};
        Cursor query = db.query(table, projection, selection, selectionArgs, null, null, null);
        return query;
    }

    /**
     * 添加数据的方法
     *
     * @param adds    字段
     * @param addData 字段对应数据
     */
    public void insert(String[] adds, String[] addData) {
        //添加数据
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        for (int i = 0; i < adds.length; i++) {
            values.put(adds[i], addData[i]);
        }
        long newRowId = db.insert(table, null, values);
        Log.d(TAG, "用户" + Arrays.toString(addData) + "插入成功。行ID: " + newRowId);

        db.close();
    }

    public boolean iseExist(String field, String fieldData) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {field};
        String selection = field + " = ?";
        String[] selectionArgs = {fieldData};

        Cursor cursor = db.query(table, projection, selection, selectionArgs, null, null, null);

        boolean b = cursor.moveToFirst();
        Log.i(b+"",fieldData);
        cursor.close();
        db.close();
        return b;
    }
}
