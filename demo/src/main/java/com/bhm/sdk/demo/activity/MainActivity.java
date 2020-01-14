package com.bhm.sdk.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bhm.sdk.demo.R;
import com.bhm.sdk.demo.adapter.MainUIAdapter;
import com.bhm.sdk.onresult.ActivityResult;
import com.bhm.sdk.onresult.ResultData;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    protected RecyclerView main_recycle_view;
    private MainUIAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView(){
        main_recycle_view = (RecyclerView) findViewById(R.id.main_recycle_view);
        LinearLayoutManager ms= new LinearLayoutManager(this);
        ms.setOrientation(LinearLayoutManager.VERTICAL);
        main_recycle_view.setLayoutManager(ms);
        main_recycle_view.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        main_recycle_view.setHasFixedSize(false);
        adapter = new MainUIAdapter(getItems());
        main_recycle_view.setAdapter(adapter);
    }

    private List<String> getItems(){
        List<String> list = new ArrayList<>();
        list.add("标题栏TitleBar(XML集成)");
        list.add("标题栏TitleBar(BaseActivity集成)");
        list.add("标题栏TitleBar(代码生成)");
        list.add("viewPager(取消预加载)");
        list.add("图库&拍照");
        list.add("webView");
        list.add("APP异常信息捕获");
        return list;
    }

    private void initListener(){
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                openUI(position);
            }
        });
    }

    private void openUI(int position){
        Intent intent = new Intent();
        switch (position){
            case 0:
                intent.setClass(this, TitleBarXMLActivity.class);
                /*new ActivityResult(this).startForResult(intent, new ActivityResult.Callback() {
                    @Override
                    public void onActivityResult(int resultCode, Intent data) {
                        Toast.makeText(MainActivity.this, data == null? "null data" + resultCode :
                                data.getStringExtra("data") + resultCode, Toast.LENGTH_SHORT).show();
                    }
                });*/
                new ActivityResult(this).startForResult(intent)
                        .subscribe(new Consumer<ResultData>() {
                            @Override
                            public void accept(ResultData resultData) throws Exception {
                                if(null == resultData){
                                    return;
                                }
                                int resultCode = resultData.getResultCode();
                                Intent data = resultData.getData();
                                Toast.makeText(MainActivity.this, data == null? "null data" + resultCode :
                                        data.getStringExtra("data") + resultCode, Toast.LENGTH_SHORT).show();
                            }
                        });
                return;
            case 1:
                intent.setClass(this, TitleBarExtendsBaseActivity.class);
                break;
            case 2:
                intent.setClass(this, TitleBarJavaActivity.class);
                break;
            case 3:
                intent.setClass(this, ViewPagerActivity.class);
                break;
            case 4:
                intent.setClass(this, GetPictureActivity.class);
                break;
            case 5:
                intent.setClass(this, TitleBarXMLActivity.class);
                break;
            case 6:
                //随意写一个闪退的异常
                int i = 10/0;
                return;
        }
        startActivity(intent);
    }
}
