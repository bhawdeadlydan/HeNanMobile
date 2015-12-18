package com.headingwl.sjtu.rfid_nfc_test;


import android.app.Activity;
import android.app.PendingIntent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;


import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.headingwl.RfidManager.*;


public class MainActivity extends AppCompatActivity implements RfidNfc.TagUidCallBack{


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */

    private  String TAG = "MainActivity";

    private static RfidNfc nnfc;


    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    public void onTagUidGet(final NfcDataType.NfcDataTypeBase uid)
    {
        Log.i("MainActivity:", uid.toString());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), uid.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onSectorGet(String sector){

    }
    public void onBlockGet(final String block)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView tx = (TextView) findViewById(R.id.section_label);
                tx.setText("BLOCK:\n "+block);
            }
        });
    }

    @Override
    public void getItemInf(final NfcDataType.NfcDataTypeBase itemInf){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), itemInf.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void getREQInf(final NfcDataType.NfcDataTypeBase reqInf){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), reqInf.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onWriteTaskEnd(NfcTask.NfcTaskName nfcTaskName,final NfcDataType.NfcDataTypeBase nfcDataTypeBase,final boolean ret)
    {
        Log.i(TAG,"onWriteTaskEnd : " + (ret  ? "succeed":"failed\n" + nfcDataTypeBase.toString()));
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),  "写入" + nfcDataTypeBase.toString() + (ret  ? "succeed":"failed\n" + nfcDataTypeBase.toString()), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public  void onReadTag(final NfcTask.NfcTaskName nfcTaskName, final NfcDataType.NfcDataTypeBase nfcDataTypeBase)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),  nfcDataTypeBase.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void getTransInf(final NfcDataType.NfcDataTypeBase TransInf){

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), TransInf.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void getConsInf(final NfcDataType.NfcDataTypeBase consInf){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //Toast.makeText(getApplicationContext(), consInf.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Snackbar.make(view, nnfc.GetVersion(), Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }
        });

        nnfc = new RfidNfc(this);
        nnfc.hfInit(this);

    }

    public void ClearNfcTask(View v)
    {
        nnfc.nfcTask.clearNfcTask();
        Log.i(TAG,"clear task list");
    }

    public void addTask(View v)
    {

        switch(v.getId()){
            case R.id.btnGetConsInf:
                nnfc.nfcTask.addNfcTask(NfcTask.NfcTaskType.ReadData, NfcTask.NfcTaskName.ConsInf,null);
                Log.i(TAG,"btnGetConsInf");
                break;
            case R.id.btnGetItemInf:
                nnfc.nfcTask.addNfcTask(NfcTask.NfcTaskType.ReadData, NfcTask.NfcTaskName.ItemInf,null);
                Log.i(TAG, "btnGetItemInf");
                break;
            case R.id.btnGetREQInf:
                nnfc.nfcTask.addNfcTask(NfcTask.NfcTaskType.ReadData, NfcTask.NfcTaskName.REQInf,null);
                Log.i(TAG, "btnGetREQInf");
                break;
            case R.id.btnGetTransInf:
                nnfc.nfcTask.addNfcTask(NfcTask.NfcTaskType.ReadData, NfcTask.NfcTaskName.TransInf,new NfcTestData().transInf);
                Log.i(TAG, "btnGetTransInf");
                break;
            case R.id.btnWriteConsInf:
                nnfc.nfcTask.addNfcTask(NfcTask.NfcTaskType.WriteData, NfcTask.NfcTaskName.ConsInf,new NfcTestData().consInf);
                Log.i(TAG, "btnWriteConsInf");
                break;
            case R.id.btnWriteItemInf:
                nnfc.nfcTask.addNfcTask(NfcTask.NfcTaskType.WriteData, NfcTask.NfcTaskName.ItemInf,new NfcTestData().itemInf);
                Log.i(TAG, "btnWriteItemInf");
                break;
            case R.id.btnWriteREQInf:
                nnfc.nfcTask.addNfcTask(NfcTask.NfcTaskType.WriteData, NfcTask.NfcTaskName.REQInf, new NfcTestData().reqInf);
                Log.i(TAG, "btnWriteREQInf");
                break;
            case R.id.btnWriteTransInf:
                nnfc.nfcTask.addNfcTask(NfcTask.NfcTaskType.WriteData, NfcTask.NfcTaskName.TransInf,new NfcTestData().transInf);
                Log.i(TAG, "btnWriteTransInf");
                break;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(nnfc!=null)
            nnfc.onResume(this);

        /*
        //得到是否检测到ACTION_TECH_DISCOVERED触发
        if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(getIntent().getAction())) {
            //处理该intent
            String uid = MifareClassicTools.getUidFromIntent(getIntent());
            Toast.makeText(getApplicationContext(),uid, Toast.LENGTH_LONG).show();
            TextView tx = (TextView)findViewById(R.id.section_label);
            //tx.setText("UID: "+ uid);

        }*/

    }

    @Override
    protected void onPause()
    {
        super.onPause();
        if(nnfc!=null)
            nnfc.onPause(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            int fragmentid;
            switch (getArguments().getInt((ARG_SECTION_NUMBER))){
                case 3:
                    fragmentid = R.layout.fragment_page3;
                    break;
                case 2:
                    fragmentid = R.layout.frgment_page2;
                    break;
                case 1:
                    fragmentid = R.layout.fragment_main;
                    break;
                default:
                    fragmentid = R.layout.fragment_main;
                    break;
            }
            final View rootView = inflater.inflate(fragmentid, container, false);

            if(getArguments().getInt(ARG_SECTION_NUMBER)==1) {
                final TextView textView = (TextView) rootView.findViewById(R.id.section_label);
                textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
                final Button btnInitNfc = (Button) rootView.findViewById(R.id.btnInitNfc);
                Button btnReadBlock = (Button) rootView.findViewById(R.id.btnReadBlock);

                btnInitNfc.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(true == nnfc.hfInit(rootView.getContext()))
                        {
                            nnfc.enableReaderMode(getActivity());
                            btnInitNfc.setText("重新初始化NFC");
                        }
                        nnfc.enableForefroundDispatch((Activity) rootView.getContext());

                        Toast.makeText(rootView.getContext(), "test", Toast.LENGTH_SHORT).show();
                    }
                });

                btnReadBlock.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        nnfc.nfcTask.addNfcTask(NfcTask.NfcTaskType.ReadData, NfcTask.NfcTaskName.UID,null);
                        /*
                        String uid = MifareClassicTools.getUidFromIntent(getActivity().getIntent());
                        Toast.makeText(getContext(),uid,Toast.LENGTH_SHORT).show();
                        textView.setText("UID:"+uid);*/
                    }
                });
            }

            return rootView;
        }
    }

    //private void initFragment()//
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
/*
    private NdefRecord newTextRecord(String text, Locale locale, boolean encodeInUtf8) {
        byte[] langBytes = locale.getLanguage().getBytes(Charset.forName("US-ASCII"));

        Charset utfEncoding = encodeInUtf8 ? Charset.forName("UTF-8") : Charset.forName("UTF-16");
        byte[] textBytes = text.getBytes(utfEncoding);

        int utfBit = encodeInUtf8 ? 0 : (1 << 7);
        char status = (char) (utfBit + langBytes.length);

        byte[] data = new byte[1 + langBytes.length + textBytes.length];
        data[0] = (byte) status;
        System.arraycopy(langBytes, 0, data, 1, langBytes.length);
        System.arraycopy(textBytes, 0, data, 1 + langBytes.length, textBytes.length);

        return new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, new byte[0], data);
    }
    */
}
