package cn.edu.bistu.cs.se.mycalculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class DanweiActivity extends AppCompatActivity {
    /*Button toBinary,toOctal,toHex;
    Button one,two,three,four,five,six,seven,eight,nine,zero,ce;
    EditText show_jinzhi;*/

    private Button swap;
    private TextView result_danwei;
    private EditText show_danwei;
    private Spinner before_danwei, after_danwei;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danwei);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  mainIntent = new Intent(DanweiActivity.this,MainActivity.class);
                startActivity(mainIntent);
            }
        });

        //初始化
        swap = (Button)findViewById(R.id.swap);
        result_danwei = (TextView)findViewById(R.id.result_danwei);
        show_danwei = (EditText)findViewById(R.id.show_danwei);
        before_danwei = (Spinner)findViewById(R.id.before_danwei);
        after_danwei = (Spinner)findViewById(R.id.after_danwei);

        swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                danwei();
            }
        });

    }

    //将下拉列表转化成int类型
    public int unitToInt(String unit) {
        if (unit.equals("秒"))
            return 0;
        if (unit.equals("分钟"))
            return 1;
        if (unit.equals("小时"))
            return 2;
        return -1;
    }


    private void danwei() {
        int getUnit1 = unitToInt(before_danwei.getSelectedItem().toString());
        int getUnit2 = unitToInt(after_danwei.getSelectedItem().toString());
        String time = show_danwei.getText().toString();
        double result;
        //秒转化为秒，分钟，小时
        if(getUnit1==0){
            switch(getUnit2){
                case 0:
                    result_danwei.setText(time);
                    break;
                case 1:
                    result = Double.parseDouble(time) / 60;
                    result_danwei.setText(toString().valueOf(result));
                    break;
                case 2:
                    result = Double.parseDouble(time) / 60 /60;
                    result_danwei.setText(toString().valueOf(result));
                    break;
            }
        }
        //小时转为秒，小时，分钟
        else if(getUnit1==1){
            switch(getUnit2){
                case 0:
                    result = Double.parseDouble(time) * 60;
                    result_danwei.setText(toString().valueOf(result));
                    break;
                case 1:
                    result_danwei.setText(time);
                    break;
                case 2:
                    result = Double.parseDouble(time) / 60;
                    result_danwei.setText(toString().valueOf(result));
                    break;
            }
        }
        //小时转化为秒，分钟，小时
        else if(getUnit1==2){
            switch(getUnit2){
                case 0:
                    result = Double.parseDouble(time) * 60;
                    result_danwei.setText(toString().valueOf(result));
                    break;
                case 1:
                    result = Double.parseDouble(time) * 60 * 60;
                    result_danwei.setText(toString().valueOf(result));
                    break;
                case 2:
                    result_danwei.setText(time);
                    break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_danwei, menu);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(DanweiActivity.this);
                builder.setTitle("帮助");
                builder.setMessage("这是一个简单的帮助");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(DanweiActivity.this,"单击了取消",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(DanweiActivity.this,"单击了确定",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.create();
                builder.show();
                break;
            case R.id.calculate:
                // 新建一个Intent对象
                Intent intent = new Intent();
                // 指定intent要启动的类
                intent.setClass(DanweiActivity.this,MainActivity.class);
                // 启动一个新的Activity
                startActivity(intent);
                // 关闭当前的Activity
                DanweiActivity.this.finish();
                break;
            case R.id.jinzhi:
                // 新建一个Intent对象
                Intent intent2 = new Intent();
                // 指定intent要启动的类
                intent2.setClass(DanweiActivity.this,JinzhiActivity.class);
                //启动一个新的Activity
                startActivity(intent2);
                // 关闭当前的Activity
                DanweiActivity.this.finish();
                break;
            case R.id.exit:
                System.exit(-1);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}