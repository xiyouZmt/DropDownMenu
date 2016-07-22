package com.zmt.dropdownmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DropDownMenu dropDownMenu;
    private String [] headers = {"城市", "年龄", "性别", "星座"};
    private List<View> viewList = new ArrayList<>();

    private Adapter cityAdapter;
    private Adapter ageAdapter;
    private Adapter sexAdapter;
    private Adapter constellationAdapter;

    private String citys[] = {"不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州"};
    private String ages[] = {"不限", "18岁以下", "18-22岁", "23-26岁", "27-35岁", "35岁以上"};
    private String sexs[] = {"不限", "男", "女"};
    private String constellations[] = {"不限", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座", "水瓶座", "双鱼座"};
    private int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dropDownMenu = (DropDownMenu) findViewById(R.id.dropDownMenu);
        initView();
    }

    private void initView() {

        final ListView cityView = new ListView(this);
        cityView.setDividerHeight(0);
        cityAdapter = new Adapter(this, Arrays.asList(citys), R.layout.item_list_drop_down);
        cityView.setAdapter(cityAdapter);

        final ListView ageView = new ListView(this);
        ageView.setDividerHeight(0);
        ageAdapter = new Adapter(this, Arrays.asList(ages), R.layout.item_default_drop_down);
        ageView.setAdapter(ageAdapter);

        final ListView sexView = new ListView(this);
        sexView.setDividerHeight(0);
        sexAdapter = new Adapter(this, Arrays.asList(sexs), R.layout.item_default_drop_down);
        sexView.setAdapter(sexAdapter);

        final View constellationView = getLayoutInflater().inflate(R.layout.custom_layout, null);
        GridView constellation = (GridView)constellationView.findViewById(R.id.constellation);
        constellationAdapter = new Adapter(this, Arrays.asList(constellations), R.layout.item_constellation_layout);
        constellation.setAdapter(constellationAdapter);

        TextView ok = (TextView) constellationView.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropDownMenu.setTabText(pos == 0 ? headers[3] : constellations[pos]);
                dropDownMenu.closeMenu();
            }
        });

        viewList.add(cityView);
        viewList.add(ageView);
        viewList.add(sexView);
        viewList.add(constellationView);

        cityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cityAdapter.setChecked(position);
                dropDownMenu.setTabText(position == 0 ? headers[0] : citys[position]);
                dropDownMenu.closeMenu();
            }
        });

        ageView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ageAdapter.setChecked(position);
                dropDownMenu.setTabText(position == 0 ? headers[1] : ages[position]);
                dropDownMenu.closeMenu();
            }
        });

        sexView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sexAdapter.setChecked(position);
                dropDownMenu.setTabText(position == 0 ? headers[2] : sexs[position]);
                dropDownMenu.closeMenu();
            }
        });

        constellation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                constellationAdapter.setChecked(position);
                pos = position;
            }
        });
        TextView contentView = new TextView(this);

        dropDownMenu.setDropDownMenu(Arrays.asList(headers), viewList, contentView);
    }

}
