package cn.edu.bistu.cs.se.mycalculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Double2;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class JinzhiActivity extends AppCompatActivity {
    /*Button toBinary,toOctal,toHex;
    Button one,two,three,four,five,six,seven,eight,nine,zero,ce;
    EditText show_jinzhi;*/

    private Button swap;
    private TextView result_jinzhi;
    private EditText show_jinzhi;
    private Spinner before_jinzhi,after_jinzhi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jinzhi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  mainIntent = new Intent(JinzhiActivity.this,MainActivity.class);
                startActivity(mainIntent);
            }
        });

        //初始化
        swap = (Button)findViewById(R.id.swap);
        result_jinzhi = (TextView)findViewById(R.id.result_jinzhi);
        show_jinzhi = (EditText)findViewById(R.id.show_jinzhi);
        before_jinzhi = (Spinner)findViewById(R.id.before_jinzhi);
        after_jinzhi = (Spinner)findViewById(R.id.after_jinzhi);

        swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jinzhi();
            }
        });

    }

    //将下拉列表转化成int类型
    public int unitToInt(String unit) {
        if (unit.equals("二进制"))
            return 0;
        if (unit.equals("八进制"))
            return 1;
        if (unit.equals("十进制"))
            return 2;
        if (unit.equals("十六进制"))
            return 3;
        return -1;
    }


    private void jinzhi() {
        int getUnit1 = unitToInt(before_jinzhi.getSelectedItem().toString());
        int getUnit2 = unitToInt(after_jinzhi.getSelectedItem().toString());
        String num = show_jinzhi.getText().toString();
        int d;
        String result;
        //二进制转为二、八、十、十六
        if(getUnit1==0){
            switch(getUnit2){
                case 0:
                    result_jinzhi.setText(num);
                    break;
                case 1:
                    d = Integer.parseInt(num, 2);
                    result = Integer.toOctalString(d);
                    result_jinzhi.setText(result);
                    break;
                case 2:
                    d = Integer.parseInt(num, 2);
                    result = Integer.toString(d);
                    result_jinzhi.setText(result);
                    break;
                case 3:
                    d = Integer.parseInt(num, 2);
                    result = Integer.toHexString(d);
                    result_jinzhi.setText(result);
                    break;
            }
        }
        //八进制转为二、八、十、十六
        else if(getUnit1==1){
            switch(getUnit2){
                case 0:
                    d = Integer.parseInt(num, 8);
                    result = Integer.toBinaryString(d);
                    result_jinzhi.setText(result);
                    break;
                case 1:
                    result_jinzhi.setText(num);
                    break;
                case 2:
                    d = Integer.parseInt(num, 8);
                    result = Integer.toString(d);
                    result_jinzhi.setText(result);
                    break;
                case 3:
                    d = Integer.parseInt(num, 8);
                    result = Integer.toHexString(d);
                    result_jinzhi.setText(result);
                    break;
            }
        }
        //十进制转化为二、八、十、十六
        else if(getUnit1==2){
            switch(getUnit2){
                case 0:
                    result = Integer.toBinaryString(Integer.parseInt(num));
                    result_jinzhi.setText(result);
                    break;
                case 1:
                    result = Integer.toOctalString(Integer.parseInt(num));
                    result_jinzhi.setText(result);
                    break;
                case 2:
                    result_jinzhi.setText(num);
                    break;
                case 3:
                    result = Integer.toHexString(Integer.parseInt(num));
                    result_jinzhi.setText(result);
                    break;
            }
        }
        //十六进制转化为二、八、十、十六
        else if(getUnit1==3){
            switch(getUnit2){
                case 0:
                    d = Integer.parseInt(num, 16);
                    result = Integer.toBinaryString(d);
                    result_jinzhi.setText(result);
                    break;
                case 1:
                    d = Integer.parseInt(num, 16);
                    result = Integer.toOctalString(d);
                    result_jinzhi.setText(result);
                    break;
                case 2:
                    //这里有点问题看心情改吧
                    d = Integer.parseInt(num, 16);
                    result = Integer.toString(d);
                    result_jinzhi.setText(result);
                case 3:
                    result_jinzhi.setText(num);
                    break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_jinzhi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.help:
                AlertDialog.Builder builder = new AlertDialog.Builder(JinzhiActivity.this);
                builder.setTitle("帮助");
                builder.setMessage("这是一个简单的帮助");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(JinzhiActivity.this,"单击了取消",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(JinzhiActivity.this,"单击了确定",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.create();
                builder.show();
                break;
            case R.id.calculate:
                // 新建一个Intent对象
                Intent intent = new Intent();
                // 指定intent要启动的类
                intent.setClass(JinzhiActivity.this,MainActivity.class);
                // 启动一个新的Activity
                startActivity(intent);
                // 关闭当前的Activity
                JinzhiActivity.this.finish();
                break;
            case R.id.danwei:
                // 新建一个Intent对象
                Intent intent2 = new Intent();
                // 指定intent要启动的类
                intent2.setClass(JinzhiActivity.this,DanweiActivity.class);
                //启动一个新的Activity
                startActivity(intent2);
                // 关闭当前的Activity
                JinzhiActivity.this.finish();
                break;
            case R.id.exit:
                System.exit(-1);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}