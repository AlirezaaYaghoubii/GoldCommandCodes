package samaco.idepardazan.goldcommandcodes;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import samaco.idepardazan.goldcommandcodes.Adapter.MainAdapter;
import samaco.idepardazan.goldcommandcodes.Adapter.SearchAdapter;
import samaco.idepardazan.goldcommandcodes.Business.SearchBu;
import samaco.idepardazan.goldcommandcodes.Common.ClassHelper;
import samaco.idepardazan.goldcommandcodes.Entities.MyGroupData;
import samaco.idepardazan.goldcommandcodes.Entities.TO;


public class Search extends ActionBarActivity {

    ArrayList<String> DetailsItemList=new ArrayList<String>();
    ArrayList<String> ID_List=new ArrayList<String>();


    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private String[] mPlanetTitles;
    String dokme_txt = "";

    Typeface TextFont;
    EditText TxtSearch;

    ListView Lst;

    ArrayList<String> Search_DetailsItemList=new ArrayList<String>();
    ArrayList<String> Search_DetailsItemList_No=new ArrayList<String>();
    ArrayList<String> Search_ID_List=new ArrayList<String>();
    ArrayList<MyGroupData> HeadTable=new ArrayList<MyGroupData>();


    SearchAdapter adapter;
    SearchBu searchBu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ChangeFont();
        GetList();
        GetSideBarMenu();
        GetMenuBar();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {

            case R.id.HomeMenu:
                Intent intent=new Intent(Search.this,MainActivity.class);
                startActivity(intent);
                return true;

            case R.id.SearchMenu:

                return true;

            case R.id.myfavorite:
                TO.set_Head_id("10000");
                TO.set_Head_Title("کدهای مورد علاقه");
                Intent intentfav=new Intent(Search.this,CodeList.class);
                startActivity(intentfav);
                return true;

            case R.id.MyApp:
                Intent intentmyapp=new Intent(Search.this,AboutApp.class);
                startActivity(intentmyapp);
                return true;

            case R.id.Comment:
                Insert_Comment();
                return true;

            case R.id.MyAppList:
                GetMyAppList();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }//switch (item.getItemId())
    }

    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    public void GetList()
    {


        Lst=(ListView)findViewById(R.id.LstVwDetails);



        TxtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                searchBu =new SearchBu();
                HeadTable=searchBu.SelectSearchData(getApplicationContext(), TxtSearch.getText().toString());

                Search_DetailsItemList.clear();
                Search_DetailsItemList_No.clear();
                Search_ID_List.clear();

                for(MyGroupData object: HeadTable){

                    Search_DetailsItemList.add(object.getTitle());
                    Search_DetailsItemList_No.add(object.getSmsno());
                    Search_ID_List.add(object.getMain_Id());
                }


                adapter = new SearchAdapter(getApplicationContext(), Search_DetailsItemList, Search_DetailsItemList_No,Search_ID_List);
                Lst.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        Lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                try
                {



                    TO.set_Title(HeadTable.get(position).getTitle().toString());
                    TO.set_Smsno(HeadTable.get(position).getSmsno().toString());
                    TO.set_Smsformat(HeadTable.get(position).getSmsformat().toString());
                    TO.set_Smsdetails(HeadTable.get(position).getSmsdetails().toString());
                    TO.set_Id(HeadTable.get(position).getId().toString());
                    TO.set_Head_id(HeadTable.get(position).getMain_Id().toString());

                    TO.set_Smsdetails2(HeadTable.get(position).getSmsdetails2().toString());
                    TO.set_Smsdetails3(HeadTable.get(position).getSmsdetails3().toString());
                    TO.set_State(HeadTable.get(position).getState().toString());

                    Intent intent=new Intent(Search.this,CodeShow.class);
                    startActivity(intent);
                }
                catch (Exception ex)
                {

                }

            }
        });



    }

    public void GetMenuBar()
    {
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff34ace0")));

        getSupportActionBar().setTitle("");

        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public  void GetSideBarMenu()
    {

        List_Fill();


        mPlanetTitles = getResources().getStringArray(R.array.dokmeha);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        MainAdapter adapter=new MainAdapter(this,DetailsItemList,ID_List);
        mDrawerList.setAdapter(adapter);
        mDrawerLayout.closeDrawer(mDrawerList);


        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int post, long arg3) {

                //dokme_txt = DetailsItemList.get(post);

                Integer Id=Integer.parseInt(ID_List.get(post));

                if (Id==0)
                {
                    Intent intent=new Intent(Search.this,MainActivity.class);
                    startActivity(intent);
                }
                else if (Id==1)
                {
                    TO.set_Group_Head_id("100");
                    TO.set_Group_Head_Title("کدهای دستوری همراه اول");

                    Intent intent=new Intent(Search.this,GroupList.class);
                    startActivity(intent);
                }
                else if (Id==2)
                {

                    TO.set_Group_Head_id("200");
                    TO.set_Group_Head_Title("پیامک های همراه اول");

                    Intent intent=new Intent(Search.this,GroupList.class);
                    startActivity(intent);

                }
                else if (Id==3)
                {
                    TO.set_Group_Head_id("250");
                    TO.set_Group_Head_Title("شماره های تماس");

                    Intent intent=new Intent(Search.this,GroupList.class);
                    startActivity(intent);

                }
                else if (Id==4)
                {
                    TO.set_Group_Head_id("270");
                    TO.set_Group_Head_Title("سرویس های وب");

                    Intent intent=new Intent(Search.this,GroupList.class);
                    startActivity(intent);

                }
                else if (Id==5)
                {

                    TO.set_Group_Head_id("300");
                    TO.set_Group_Head_Title("آدرس های ایمیل");

                    Intent intent=new Intent(Search.this,GroupList.class);
                    startActivity(intent);

                }
                else if (Id==6)
                {
                    TO.set_Group_Head_id("150");
                    TO.set_Group_Head_Title("کدهای دستوری ایرانسل");

                    Intent intent=new Intent(Search.this,GroupList.class);
                    startActivity(intent);

                }
                else if (Id==7)
                {
                    TO.set_Group_Head_id("350");
                    TO.set_Group_Head_Title("پیامک های ایرانسل");

                    Intent intent=new Intent(Search.this,GroupList.class);
                    startActivity(intent);
                }
                else if (Id==8)
                {
                    TO.set_Group_Head_id("370");
                    TO.set_Group_Head_Title("شماره های تماس");

                    Intent intent=new Intent(Search.this,GroupList.class);
                    startActivity(intent);
                }
                else if (Id==9)
                {
                    TO.set_Group_Head_id("400");
                    TO.set_Group_Head_Title("سرویس های وب");

                    Intent intent=new Intent(Search.this,GroupList.class);
                    startActivity(intent);
                }
                else if (Id==10)
                {
                    TO.set_Group_Head_id("450");
                    TO.set_Group_Head_Title("آدرس های ایمیل");

                    Intent intent=new Intent(Search.this,GroupList.class);
                    startActivity(intent);
                }
                else if (Id==11)
                {
                    TO.set_Group_Head_id("170");
                    TO.set_Group_Head_Title("کدهای دستوری رایتل");

                    Intent intent=new Intent(Search.this,GroupList.class);
                    startActivity(intent);
                }
                else if (Id==12)
                {
                    TO.set_Group_Head_id("500");
                    TO.set_Group_Head_Title("پیامک های رایتل");

                    Intent intent=new Intent(Search.this,GroupList.class);
                    startActivity(intent);
                }
                else if (Id==13)
                {
                    TO.set_Group_Head_id("550");
                    TO.set_Group_Head_Title("شماره های تماس");

                    Intent intent=new Intent(Search.this,GroupList.class);
                    startActivity(intent);
                }
                else if (Id==14)
                {
                    TO.set_Group_Head_id("600");
                    TO.set_Group_Head_Title("سرویس های وب");

                    Intent intent=new Intent(Search.this,GroupList.class);
                    startActivity(intent);
                }
                else if (Id==15)
                {
                    TO.set_Group_Head_id("650");
                    TO.set_Group_Head_Title("آدرس های ایمیل");

                    Intent intent=new Intent(Search.this,GroupList.class);
                    startActivity(intent);
                }

                mDrawerLayout.closeDrawer(mDrawerList);

            }

        });

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.mipmap.left2,
                R.string.drawer_open, R.string.drawer_close){

            /** هنگامی که منو کشویی کاملا بسته شده متد زیر اجرا میشه */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(dokme_txt);
                supportInvalidateOptionsMenu();
                // creates call to
                // onPrepareOptionsMenu()
            }

            /** هنگامی که منو کشویی کاملا باز شده متد زیر اجرا میشه */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("");
                supportInvalidateOptionsMenu(); // creates call to
                // onPrepareOptionsMenu()
            }


        };


    }

    private void List_Fill()
    {
        DetailsItemList.add("صفحه اصلی");
        ID_List.add("0");

        DetailsItemList.add("کدهای دستوری همراه اول");
        ID_List.add("1");

        DetailsItemList.add("پیامک های همراه اول");
        ID_List.add("2");

        DetailsItemList.add("شماره های تماس");
        ID_List.add("3");

        DetailsItemList.add("سرویس های وب");
        ID_List.add("4");

        DetailsItemList.add("آدرس های ایمیل");
        ID_List.add("5");

        DetailsItemList.add("کدهای دستوری ایرانسل");
        ID_List.add("6");

        DetailsItemList.add("پیامک های ایرانسل");
        ID_List.add("7");

        DetailsItemList.add("شماره های تماس");
        ID_List.add("8");

        DetailsItemList.add("سرویس های وب");
        ID_List.add("9");

        DetailsItemList.add("آدرس های ایمیل");
        ID_List.add("10");

        DetailsItemList.add("کدهای دستوری رایتل");
        ID_List.add("11");

        DetailsItemList.add("پیامک های رایتل");
        ID_List.add("12");

        DetailsItemList.add("شماره های تماس");
        ID_List.add("13");

        DetailsItemList.add("سرویس های وب");
        ID_List.add("14");

        DetailsItemList.add("آدرس های ایمیل");
        ID_List.add("15");



    }

    public void Insert_Comment()
    {

        if (ClassHelper.getMarketName().equals("cafebazaar")) {
            try {

                Intent intent = new Intent(Intent.ACTION_EDIT);
                intent.setData(Uri.parse("bazaar://details?id=" + "samaco.idepardazan.goldcommandcodes"));
                intent.setPackage("com.farsitel.bazaar");
                startActivity(intent);
            }
            catch (Exception ex)
            {
                Toast.makeText(getApplicationContext(), "نرم افزار بازار بر روی دستگاه شما نصب نیست", Toast.LENGTH_LONG).show();
            }

        }//if (ClassHelper.getMarketName().equals("cafebazaar"))

    }

    public void GetMyAppList()
    {
        if (ClassHelper.getMarketName().equals("cafebazaar")) {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("bazaar://collection?slug=by_author&aid=" + "alirezayaghoubi"));
                intent.setPackage("com.farsitel.bazaar");
                startActivity(intent);
            }
            catch (Exception ex)
            {
                Toast.makeText(getApplicationContext(),"نرم افزار بازار بر روی دستگاه شما نصب نیست",Toast.LENGTH_LONG).show();
            }

        }//if (MarketName.equals("cafebazaar"))

    }


    public void ChangeFont()
    {
        TextFont= Typeface.createFromAsset(getAssets(), "fonts/BYekan.ttf");

        TxtSearch = (EditText)findViewById(R.id.TxtSearch);


        TxtSearch.setTypeface(TextFont);

    }


}
