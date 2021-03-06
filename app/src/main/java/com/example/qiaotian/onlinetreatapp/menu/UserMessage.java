package com.example.qiaotian.onlinetreatapp.menu;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qiaotian.onlinetreatapp.R;
import com.example.qiaotian.onlinetreatapp.sql.DBHelper;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

import static com.example.qiaotian.onlinetreatapp.login.LoginActivity.userID;

public class UserMessage extends AppCompatActivity {
    private DBHelper dbHelper;
   private TextView tx1,tx2,tx3,tx4,tx5;
    String id,name,sex,age,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_message);
        SQLiteStudioService.instance().start(this);
        initData();
        initView();
}

    private void initData() {
        dbHelper=new DBHelper(this,"UserStore.db",null,1);
        tx1=(TextView) findViewById(R.id.usertx1);
        tx2=(TextView) findViewById(R.id.usertx2);
        tx3=(TextView) findViewById(R.id.usertx3);
        tx4=(TextView) findViewById(R.id.usertx4);
        tx5=(TextView) findViewById(R.id.usertx5);

    }

    public boolean getMes(String UserId){
        //得到可读的SQLiteDatabase对象
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //参数1：表名
        //参数2：要想显示的列
        //参数3：where子句
        //参数4：where子句对应的条件值
        //参数5：分组方式
        //参数6：having条件
        //参数7：排序方式
       Cursor cursor = db.query("userMes", new String[]{"id","name","sex","age","email"}, "id=?", new String[]{UserId}, null, null, null);

        while(cursor.moveToNext()){
          id = cursor.getString(cursor.getColumnIndex("id"));
          name = cursor.getString(cursor.getColumnIndex("name"));
         sex = cursor.getString(cursor.getColumnIndex("sex"));
          age = cursor.getString(cursor.getColumnIndex("age"));
          email = cursor.getString(cursor.getColumnIndex("email"));
        }

     db.close();
        //db.execSQL("insert into userData (name,password) values (?,?)",new String[]{username,password});
     return true;
    }

    private void initView() {

        getMes(userID);
        tx1.setText(id);
        tx2.setText(name);
        tx3.setText(sex);
        tx4.setText(age);
        tx5.setText(email);
    }
}







