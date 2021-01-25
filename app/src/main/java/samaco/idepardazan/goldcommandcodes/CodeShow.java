package samaco.idepardazan.goldcommandcodes;


import android.content.ClipData;
import android.content.ClipboardManager;
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

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import samaco.idepardazan.goldcommandcodes.Adapter.MainAdapter;
import samaco.idepardazan.goldcommandcodes.Business.BuDetailsData;
import samaco.idepardazan.goldcommandcodes.Common.ClassHelper;
import samaco.idepardazan.goldcommandcodes.Entities.TO;


public class CodeShow extends ActionBarActivity {

    TabHost tabHost;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private String[] mPlanetTitles;
    String dokme_txt = "";

    ArrayList<String> DetailsItemList=new ArrayList<String>();
    ArrayList<String> ID_List=new ArrayList<String>();

    Typeface TextFont;

    TextView TxvHeader;
    TextView TxvShow;
    TextView TxvSmsNo;
    TextView TxvShowDetails;
    TextView TxtCommon;


    TextView TxvFullComment;
    TextView TxvCompleteComment;

    EditText EdtSmsBody;
    Button Btn;
    Button BtnNet;
    Button BtnEmail;
    Button BtnSms;

    Button BtnFavorite;
    Button BtnUnFavorite;

    Button BtnCopy;

    String Head_id;
    String Id;

    private ClipboardManager Clipboard;
    private ClipData Clip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_show);

        Clipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);

        GetSideBarMenu();
        GetMenuBar();
        GetTab();
        ShowCode();
        Main_Action();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_code_show, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {

            case R.id.HomeMenu:
                Intent intent=new Intent(CodeShow.this,MainActivity.class);
                startActivity(intent);
                return true;

            case R.id.SearchMenu:
                Intent intentsearch=new Intent(CodeShow.this,Search.class);
                startActivity(intentsearch);
                return true;

            case R.id.myfavorite:
                TO.set_Head_id("10000");
                TO.set_Head_Title("کدهای مورد علاقه");
                Intent intentfav=new Intent(CodeShow.this,CodeList.class);
                startActivity(intentfav);
                return true;

            case R.id.MyApp:
                Intent intentmyapp=new Intent(CodeShow.this,AboutApp.class);
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

    public void Main_Action()
    {

        if ((TO.get_State().equals("1")) || (TO.get_State().equals("10")))
        {
            Btn.setText("اجرای کد");
        }
        else if (TO.get_State().equals("2") || TO.get_State().equals("20"))
        {
            Btn.setText("ارسال پیام");
        }
        else if (TO.get_State().equals("3"))
        {
            Btn.setText("تماس");
        }
        else if (TO.get_State().equals("4"))
        {
            Btn.setText("اجرای صفحه وب");
        }
        else if (TO.get_State().equals("5"))
        {
            Btn.setText("ارسال ایمیل");
        }

        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((TO.get_State().equals("1")) || (TO.get_State().equals("10")))
                {
                    Intent callIntent = new Intent
                            (Intent.ACTION_CALL, ussdToCallableUri(EdtSmsBody.getText().toString()));
                    startActivity(callIntent);
                }
                else if (TO.get_State().equals("2") || TO.get_State().equals("20"))
                {

                    Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
                    smsIntent.setType("vnd.android-dir/mms-sms");
                    smsIntent.putExtra("address", TxvSmsNo.getText().toString());
                    smsIntent.putExtra("sms_body", EdtSmsBody.getText().toString());
                    startActivity(smsIntent);


                }
                else if (TO.get_State().equals("3"))
                {
                    String number = "tel:" + TxvSmsNo.getText().toString();
                    Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(number));
                    startActivity(callIntent);
                }
                else if (TO.get_State().equals("4"))
                {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(TxvSmsNo.getText().toString()));
                    startActivity(browserIntent);

                }
                else if (TO.get_State().equals("5"))
                {
                    String shareBody = "";
                    Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "");
                    intent.putExtra(Intent.EXTRA_TEXT, shareBody);
                    intent.setData(Uri.parse("mailto:"+TxvSmsNo.getText().toString())); // or just "mailto:" for blank
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                    startActivity(intent);

                }

            }
        });
    }

    private Uri ussdToCallableUri(String ussd) {

        String uriString = "";

        if(!ussd.startsWith("tel:"))
            uriString += "tel:";

        for(char c : ussd.toCharArray()) {

            if(c == '#')
                uriString += Uri.encode("#");
            else
                uriString += c;
        }

        return Uri.parse(uriString);
    }

    public void ShowCode()
    {

        Head_id= TO.get_Head_id();
        Id= TO.get_Id();

        TxvHeader = (TextView) findViewById(R.id.TxvHeader);
        TxvShow = (TextView) findViewById(R.id.TxvShow);
        TxvSmsNo = (TextView) findViewById(R.id.TxvSmsNo);
        TxvShowDetails = (TextView) findViewById(R.id.TxvShowDetails);

        TxtCommon= (TextView) findViewById(R.id.TxtCommon);

        TxvFullComment= (TextView) findViewById(R.id.TxvFullComment);
        TxvCompleteComment= (TextView) findViewById(R.id.TxvCompleteComment);


        EdtSmsBody = (EditText) findViewById(R.id.EdtSmsBody);

        TxvHeader.setText(TO.get_Title());
        TxvShow.setText(TO.get_Smsformat());

        TxvSmsNo.setText(TO.get_Smsno());


        if (TO.get_State().equals("1"))
        {
            EdtSmsBody.setText(TO.get_Smsno());
        }
        else if (TO.get_State().equals("20"))
        {
            EdtSmsBody.setText(TO.get_Smsformat());
        }

        if (TO.get_State().equals("3") || TO.get_State().equals("4") || TO.get_State().equals("5"))
        {
            EdtSmsBody.setEnabled(false);
        }

        TxvShowDetails.setText(TO.get_Smsdetails());

        TxvFullComment.setText(TO.get_Smsdetails2());
        TxvCompleteComment.setText(TO.get_Smsdetails3());


        Btn = (Button) findViewById(R.id.BtnSendSms);
        BtnNet = (Button) findViewById(R.id.BtnNet);
        BtnEmail = (Button) findViewById(R.id.BtnEmail);
        BtnSms = (Button) findViewById(R.id.BtnSms);

        BtnFavorite = (Button) findViewById(R.id.BtnFavorite);
        BtnUnFavorite = (Button) findViewById(R.id.BtnUnFavorite);

        BtnCopy = (Button) findViewById(R.id.BtnCopy);


        Typeface TextFont = Typeface.createFromAsset(getAssets(), "fonts/BYekan.ttf");


        TxvHeader.setTypeface(TextFont,Typeface.BOLD);
        TxvShow.setTypeface(TextFont);
        TxvShowDetails.setTypeface(TextFont);
        TxtCommon.setTypeface(TextFont);
        TxvFullComment.setTypeface(TextFont);
        TxvCompleteComment.setTypeface(TextFont);


        Btn.setTypeface(TextFont,Typeface.BOLD);
        BtnNet.setTypeface(TextFont,Typeface.BOLD);
        BtnEmail.setTypeface(TextFont,Typeface.BOLD);
        BtnSms.setTypeface(TextFont,Typeface.BOLD);


        BtnFavorite.setTypeface(TextFont);
        BtnUnFavorite.setTypeface(TextFont);

        BtnCopy.setTypeface(TextFont);

        BtnFavorite.setEnabled(false);
        BtnUnFavorite.setEnabled(false);

        BuDetailsData buDetailsData = new BuDetailsData();
        Boolean Result=buDetailsData.GetFavoriteStatus(this, Id);


        if (Result) {

            BtnFavorite.setEnabled(false);
            BtnUnFavorite.setEnabled(true);
        }
        else {

            BtnFavorite.setEnabled(true);
            BtnUnFavorite.setEnabled(false);
        }

        BtnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BuDetailsData buDetailsData = new BuDetailsData();
                buDetailsData.SetFavorite(getApplicationContext(), Id);

                BtnFavorite.setEnabled(false);
                BtnUnFavorite.setEnabled(true);


                Toast   T=new Toast(getApplicationContext());
                T.setGravity(Gravity.CENTER,0, 0);
                T.setDuration(Toast.LENGTH_LONG);
                LayoutInflater inflate = (LayoutInflater) getApplicationContext().getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
                View view = inflate.inflate(R.layout.mycustometost, null);
                TextView tvMessage = (TextView) view.findViewById(R.id.tvMessage);
                Typeface TextFont= Typeface.createFromAsset(getAssets(), "fonts/BYekan.ttf");
                tvMessage.setTypeface(TextFont);
                tvMessage.setText("به لیست کدهای مورد علاقه اضافه شد");
                T.setView(view);
                T.show();

            }
        });

        BtnUnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BuDetailsData buDetailsData = new BuDetailsData();
                buDetailsData.SetUnFavorite(getApplicationContext(), Id);

                BtnFavorite.setEnabled(true);
                BtnUnFavorite.setEnabled(false);

                Toast   T=new Toast(getApplicationContext());
                T.setGravity(Gravity.CENTER,0, 0);
                T.setDuration(Toast.LENGTH_LONG);
                LayoutInflater inflate = (LayoutInflater) getApplicationContext().getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
                View view = inflate.inflate(R.layout.mycustometost, null);
                TextView tvMessage = (TextView) view.findViewById(R.id.tvMessage);
                Typeface TextFont= Typeface.createFromAsset(getAssets(), "fonts/BYekan.ttf");
                tvMessage.setTypeface(TextFont);
                tvMessage.setText("از لیست کدهای مورد علاقه حذف شد");
                T.setView(view);
                T.show();

            }
        });

        BtnCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = TxvSmsNo.getText().toString();
                Clip = ClipData.newPlainText("text", text);
                Clipboard.setPrimaryClip(Clip);

                Toast   T=new Toast(getApplicationContext());
                T.setGravity(Gravity.CENTER,0, 0);
                T.setDuration(Toast.LENGTH_LONG);
                LayoutInflater inflate = (LayoutInflater) getApplicationContext().getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
                View view = inflate.inflate(R.layout.mycustometost, null);
                TextView tvMessage = (TextView) view.findViewById(R.id.tvMessage);
                Typeface TextFont= Typeface.createFromAsset(getAssets(), "fonts/BYekan.ttf");
                tvMessage.setTypeface(TextFont);
                tvMessage.setText("در کلیپ بورد کپی شد");
                T.setView(view);
                T.show();
            }
        });

        BtnNet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Str="";

                if ((TO.get_State().equals("1"))|| (TO.get_State().equals("10")))
                {
                    Str=("کد : ");
                }
                else if ((TO.get_State().equals("2"))|| (TO.get_State().equals("20")))
                {
                    Str=("شماره پیامک : ");
                }
                else if (TO.get_State().equals("3"))
                {
                    Str=("تماس : ");
                }
                else if (TO.get_State().equals("4"))
                {
                    Str=("آدرس : ");
                }
                else if (TO.get_State().equals("4"))
                {
                    Str=("آدرس : ");
                }

                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    String shareBody = TxvHeader.getText().toString()+" \n "+Str+" \n "+TxvSmsNo.getText().toString();
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, TxvHeader.getText().toString());
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                    startActivity(Intent.createChooser(sharingIntent, "به اشتراک گذاری با ..."));

            }
        });

        BtnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Str="";

                if ((TO.get_State().equals("1"))|| (TO.get_State().equals("10")))
                {
                    Str=("کد : ");
                }
                else if ((TO.get_State().equals("2"))|| (TO.get_State().equals("20")))
                {
                    Str=("شماره پیامک : ");
                }
                else if (TO.get_State().equals("3"))
                {
                    Str=("تماس : ");
                }
                else if (TO.get_State().equals("4"))
                {
                    Str=("آدرس : ");
                }
                else if (TO.get_State().equals("4"))
                {
                    Str=("آدرس : ");
                }

                    String shareBody = TxvHeader.getText().toString()+" \n "+Str+" \n "+TxvSmsNo.getText().toString();
                    Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_SUBJECT, TxvHeader.getText().toString());
                    intent.putExtra(Intent.EXTRA_TEXT, shareBody);
                    intent.setData(Uri.parse("mailto:")); // or just "mailto:" for blank
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                    startActivity(intent);

            }
        });

        BtnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String Str="";

                if ((TO.get_State().equals("1"))|| (TO.get_State().equals("10")))
                {
                    Str=("کد : ");
                }
                else if ((TO.get_State().equals("2"))|| (TO.get_State().equals("20")))
                {
                    Str=("شماره پیامک : ");
                }
                else if (TO.get_State().equals("3"))
                {
                    Str=("تماس : ");
                }
                else if (TO.get_State().equals("4"))
                {
                    Str=("آدرس : ");
                }
                else if (TO.get_State().equals("4"))
                {
                    Str=("آدرس : ");
                }

                    String shareBody = TxvHeader.getText().toString()+" \n "+Str+" \n "+TxvSmsNo.getText().toString();
                    Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
                    smsIntent.setType("vnd.android-dir/mms-sms");
                    smsIntent.putExtra("address", "");
                    smsIntent.putExtra("sms_body", shareBody);
                    startActivity(smsIntent);

            }
        });

    }

    public void GetTab()
    {
        tabHost=(TabHost)findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec spec1=tabHost.newTabSpec("TAB 1");
        spec1.setContent(R.id.tab1);
        spec1.setIndicator("توضیحات تکمیلی");

        TabHost.TabSpec spec2=tabHost.newTabSpec("TAB 2");
        spec2.setIndicator("شرح");
        spec2.setContent(R.id.tab2);


        TabHost.TabSpec spec3=tabHost.newTabSpec("TAB 3");
        spec3.setContent(R.id.tab3);
        spec3.setIndicator("عملیات");

        tabHost.addTab(spec1);
        tabHost.addTab(spec2);
        tabHost.addTab(spec3);

        tabHost.setCurrentTab(2);

        TextFont= Typeface.createFromAsset(getAssets(), "fonts/BYekan.ttf");

        TextView t1 = (TextView) tabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        TextView t2 = (TextView) tabHost.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
        TextView t3 = (TextView) tabHost.getTabWidget().getChildAt(2).findViewById(android.R.id.title);

        t1.setTypeface(TextFont,Typeface.BOLD);
        t2.setTypeface(TextFont,Typeface.BOLD);
        t3.setTypeface(TextFont,Typeface.BOLD);
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
                    Intent intent=new Intent(CodeShow.this,MainActivity.class);
                    startActivity(intent);
                }
                else if (Id==1)
                {
                    TO.set_Group_Head_id("100");
                    TO.set_Group_Head_Title("کدهای دستوری همراه اول");

                    Intent intent=new Intent(CodeShow.this,GroupList.class);
                    startActivity(intent);
                }
                else if (Id==2)
                {

                    TO.set_Group_Head_id("200");
                    TO.set_Group_Head_Title("پیامک های همراه اول");

                    Intent intent=new Intent(CodeShow.this,GroupList.class);
                    startActivity(intent);

                }
                else if (Id==3)
                {
                    TO.set_Group_Head_id("250");
                    TO.set_Group_Head_Title("شماره های تماس");

                    Intent intent=new Intent(CodeShow.this,GroupList.class);
                    startActivity(intent);

                }
                else if (Id==4)
                {
                    TO.set_Group_Head_id("270");
                    TO.set_Group_Head_Title("سرویس های وب");

                    Intent intent=new Intent(CodeShow.this,GroupList.class);
                    startActivity(intent);

                }
                else if (Id==5)
                {

                    TO.set_Group_Head_id("300");
                    TO.set_Group_Head_Title("آدرس های ایمیل");

                    Intent intent=new Intent(CodeShow.this,GroupList.class);
                    startActivity(intent);

                }
                else if (Id==6)
                {
                    TO.set_Group_Head_id("150");
                    TO.set_Group_Head_Title("کدهای دستوری ایرانسل");

                    Intent intent=new Intent(CodeShow.this,GroupList.class);
                    startActivity(intent);

                }
                else if (Id==7)
                {
                    TO.set_Group_Head_id("350");
                    TO.set_Group_Head_Title("پیامک های ایرانسل");

                    Intent intent=new Intent(CodeShow.this,GroupList.class);
                    startActivity(intent);
                }
                else if (Id==8)
                {
                    TO.set_Group_Head_id("370");
                    TO.set_Group_Head_Title("شماره های تماس");

                    Intent intent=new Intent(CodeShow.this,GroupList.class);
                    startActivity(intent);
                }
                else if (Id==9)
                {
                    TO.set_Group_Head_id("400");
                    TO.set_Group_Head_Title("سرویس های وب");

                    Intent intent=new Intent(CodeShow.this,GroupList.class);
                    startActivity(intent);
                }
                else if (Id==10)
                {
                    TO.set_Group_Head_id("450");
                    TO.set_Group_Head_Title("آدرس های ایمیل");

                    Intent intent=new Intent(CodeShow.this,GroupList.class);
                    startActivity(intent);
                }
                else if (Id==11)
                {
                    TO.set_Group_Head_id("170");
                    TO.set_Group_Head_Title("کدهای دستوری رایتل");

                    Intent intent=new Intent(CodeShow.this,GroupList.class);
                    startActivity(intent);
                }
                else if (Id==12)
                {
                    TO.set_Group_Head_id("500");
                    TO.set_Group_Head_Title("پیامک های رایتل");

                    Intent intent=new Intent(CodeShow.this,GroupList.class);
                    startActivity(intent);
                }
                else if (Id==13)
                {
                    TO.set_Group_Head_id("550");
                    TO.set_Group_Head_Title("شماره های تماس");

                    Intent intent=new Intent(CodeShow.this,GroupList.class);
                    startActivity(intent);
                }
                else if (Id==14)
                {
                    TO.set_Group_Head_id("600");
                    TO.set_Group_Head_Title("سرویس های وب");

                    Intent intent=new Intent(CodeShow.this,GroupList.class);
                    startActivity(intent);
                }
                else if (Id==15)
                {
                    TO.set_Group_Head_id("650");
                    TO.set_Group_Head_Title("آدرس های ایمیل");

                    Intent intent=new Intent(CodeShow.this,GroupList.class);
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
