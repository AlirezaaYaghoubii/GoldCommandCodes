package samaco.idepardazan.goldcommandcodes;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import samaco.idepardazan.goldcommandcodes.Adapter.MainAdapter;
import samaco.idepardazan.goldcommandcodes.Common.ClassHelper;
import samaco.idepardazan.goldcommandcodes.Entities.TO;


public class Rightel extends ActionBarActivity {

    LinearLayout LayerCode;
    LinearLayout  LayerSms;
    LinearLayout  LayerCall;
    LinearLayout  LayerWeb;
    LinearLayout  LayerEmail;



    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private String[] mPlanetTitles;
    String dokme_txt = "";

    ArrayList<String> DetailsItemList=new ArrayList<String>();
    ArrayList<String> ID_List=new ArrayList<String>();

    Typeface TextFont;

    TextView TxvHeader;
    TextView TxvCode;
    TextView TxvSms;
    TextView TxvCall;
    TextView TxvWeb;
    TextView TxvEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rightel);

        GetLayout();
        ChangeFont();
        GetSideBarMenu();
        GetMenuBar();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rightel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {

            case R.id.HomeMenu:
                Intent intent=new Intent(Rightel.this,MainActivity.class);
                startActivity(intent);
                return true;

            case R.id.SearchMenu:
                Intent intentsearch=new Intent(Rightel.this,Search.class);
                startActivity(intentsearch);
                return true;

            case R.id.myfavorite:
                TO.set_Head_id("10000");
                TO.set_Head_Title("کدهای مورد علاقه");
                Intent intentfav=new Intent(Rightel.this,CodeList.class);
                startActivity(intentfav);
                return true;

            case R.id.MyApp:
                Intent intentmyapp=new Intent(Rightel.this,AboutApp.class);
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

    public void ChangeFont()
    {
        TextFont= Typeface.createFromAsset(getAssets(), "fonts/BYekan.ttf");

        TxvHeader = (TextView)findViewById(R.id.TxvHeader);
        TxvCode = (TextView)findViewById(R.id.TxvCode);
        TxvSms = (TextView)findViewById(R.id.TxvSms);
        TxvCall = (TextView)findViewById(R.id.TxvCall);
        TxvWeb = (TextView)findViewById(R.id.TxvWeb);
        TxvEmail = (TextView)findViewById(R.id.TxvEmail);

        TxvHeader.setTypeface(TextFont,Typeface.BOLD);
        TxvCode.setTypeface(TextFont);
        TxvSms.setTypeface(TextFont);
        TxvCall.setTypeface(TextFont);
        TxvWeb.setTypeface(TextFont);
        TxvEmail.setTypeface(TextFont);

    }

    public void GetLayout() {

        LayerCode = (LinearLayout) findViewById(R.id.LayerCode);
        LayerSms = (LinearLayout) findViewById(R.id.LayerSms);
        LayerCall = (LinearLayout) findViewById(R.id.LayerCall);
        LayerWeb = (LinearLayout) findViewById(R.id.LayerWeb);
        LayerEmail = (LinearLayout) findViewById(R.id.LayerEmail);

        LayerEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TO.set_Group_Head_id("650");
                TO.set_Group_Head_Title("آدرس های ایمیل");

                Intent intent=new Intent(Rightel.this,GroupList.class);
                startActivity(intent);

            }
        });

        LayerWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TO.set_Group_Head_id("600");
                TO.set_Group_Head_Title("سرویس های وب");

                Intent intent=new Intent(Rightel.this,GroupList.class);
                startActivity(intent);

            }
        });

        LayerCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TO.set_Group_Head_id("550");
                TO.set_Group_Head_Title("شماره های تماس");

                Intent intent=new Intent(Rightel.this,GroupList.class);
                startActivity(intent);


            }
        });

        LayerSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TO.set_Group_Head_id("500");
                TO.set_Group_Head_Title("پیامک های رایتل");

                Intent intent=new Intent(Rightel.this,GroupList.class);
                startActivity(intent);

            }
        });

        LayerCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TO.set_Group_Head_id("170");
                TO.set_Group_Head_Title("کدهای دستوری رایتل");

                Intent intent=new Intent(Rightel.this,GroupList.class);
                startActivity(intent);
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
                    Intent intent=new Intent(Rightel.this,MainActivity.class);
                    startActivity(intent);
                }
                else if (Id==1)
                {
                    TO.set_Group_Head_id("100");
                    TO.set_Group_Head_Title("کدهای دستوری همراه اول");

                    Intent intent=new Intent(Rightel.this,GroupList.class);
                    startActivity(intent);
                }
                else if (Id==2)
                {

                    TO.set_Group_Head_id("200");
                    TO.set_Group_Head_Title("پیامک های همراه اول");

                    Intent intent=new Intent(Rightel.this,GroupList.class);
                    startActivity(intent);

                }
                else if (Id==3)
                {
                    TO.set_Group_Head_id("250");
                    TO.set_Group_Head_Title("شماره های تماس");

                    Intent intent=new Intent(Rightel.this,GroupList.class);
                    startActivity(intent);

                }
                else if (Id==4)
                {
                    TO.set_Group_Head_id("270");
                    TO.set_Group_Head_Title("سرویس های وب");

                    Intent intent=new Intent(Rightel.this,GroupList.class);
                    startActivity(intent);

                }
                else if (Id==5)
                {

                    TO.set_Group_Head_id("300");
                    TO.set_Group_Head_Title("آدرس های ایمیل");

                    Intent intent=new Intent(Rightel.this,GroupList.class);
                    startActivity(intent);

                }
                else if (Id==6)
                {
                    TO.set_Group_Head_id("150");
                    TO.set_Group_Head_Title("کدهای دستوری ایرانسل");

                    Intent intent=new Intent(Rightel.this,GroupList.class);
                    startActivity(intent);

                }
                else if (Id==7)
                {
                    TO.set_Group_Head_id("350");
                    TO.set_Group_Head_Title("پیامک های ایرانسل");

                    Intent intent=new Intent(Rightel.this,GroupList.class);
                    startActivity(intent);
                }
                else if (Id==8)
                {
                    TO.set_Group_Head_id("370");
                    TO.set_Group_Head_Title("شماره های تماس");

                    Intent intent=new Intent(Rightel.this,GroupList.class);
                    startActivity(intent);
                }
                else if (Id==9)
                {
                    TO.set_Group_Head_id("400");
                    TO.set_Group_Head_Title("سرویس های وب");

                    Intent intent=new Intent(Rightel.this,GroupList.class);
                    startActivity(intent);
                }
                else if (Id==10)
                {
                    TO.set_Group_Head_id("450");
                    TO.set_Group_Head_Title("آدرس های ایمیل");

                    Intent intent=new Intent(Rightel.this,GroupList.class);
                    startActivity(intent);
                }
                else if (Id==11)
                {
                    TO.set_Group_Head_id("170");
                    TO.set_Group_Head_Title("کدهای دستوری رایتل");

                    Intent intent=new Intent(Rightel.this,GroupList.class);
                    startActivity(intent);
                }
                else if (Id==12)
                {
                    TO.set_Group_Head_id("500");
                    TO.set_Group_Head_Title("پیامک های رایتل");

                    Intent intent=new Intent(Rightel.this,GroupList.class);
                    startActivity(intent);
                }
                else if (Id==13)
                {
                    TO.set_Group_Head_id("550");
                    TO.set_Group_Head_Title("شماره های تماس");

                    Intent intent=new Intent(Rightel.this,GroupList.class);
                    startActivity(intent);
                }
                else if (Id==14)
                {
                    TO.set_Group_Head_id("600");
                    TO.set_Group_Head_Title("سرویس های وب");

                    Intent intent=new Intent(Rightel.this,GroupList.class);
                    startActivity(intent);
                }
                else if (Id==15)
                {
                    TO.set_Group_Head_id("650");
                    TO.set_Group_Head_Title("آدرس های ایمیل");

                    Intent intent=new Intent(Rightel.this,GroupList.class);
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
}
