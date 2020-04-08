package com.xpxcoder.xpxDroid.views;

import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.gjiazhe.wavesidebar.WaveSideBar;
import com.xpxcoder.xpxDroid.R;
import com.xpxcoder.xpxDroid.adapter.recyclerview.ContactsAdapter;
import com.xpxcoder.xpxDroid.models.Contact;
import com.xpxcoder.xpxDroid.tools.Cn2Spell;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XPSoft on 2018/2/23.
 */

public class ContactsActivity extends baseActivity {

    private List<Contact> mContacts= new ArrayList<>();
    private WaveSideBar sideBar;
    private RecyclerView rvContacts;
    private ContactsAdapter mAdatper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        rvContacts = (RecyclerView) findViewById(R.id.rv_contacts);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        mAdatper=new ContactsAdapter(mContacts, R.layout.recycle_item_contacts);
        rvContacts.setAdapter(mAdatper);
        sideBar = (WaveSideBar) findViewById(R.id.side_bar);
        /*sideBar.setTextColor(Color.BLACK);
        sideBar.setMaxOffset(100);
        sideBar.setPosition(WaveSideBar.POSITION_RIGHT);
        sideBar.setTextAlign(WaveSideBar.TEXT_ALIGN_CENTER);
        sideBar.setLazyRespond(true);*/
        sideBar.setOnSelectIndexItemListener(new WaveSideBar.OnSelectIndexItemListener() {
            @Override
            public void onSelectIndexItem(String index) {
                for (int i=0; i<mContacts.size(); i++) {
                    if (mContacts.get(i).getIndex().equals(index)) {
                        ((LinearLayoutManager) rvContacts.getLayoutManager()).scrollToPositionWithOffset(i, 0);
                        return;
                    }
                }
            }
        });
//        sideBar.setIndexItems("あ", "か", "さ", "た", "な", "は", "ま", "や", "ら", "わ");
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            //申请权限  第二个参数是一个 数组 说明可以同时申请多个权限
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_CONTACTS}, 1);
        } else {//已授权
            obtionContacts();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //权限申请成功
                obtionContacts();
            } else {
                Toast.makeText(this, "获取联系人的权限申请失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void obtionContacts() {
        //获取手机通讯录联系人
        ContentResolver resolver = this.getContentResolver();

        // 获取手机联系人
        Cursor phoneCursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, null,
                null, null, null);
        int index=-1;
        if (phoneCursor != null) {
            while (phoneCursor.moveToNext()) {
                index++;
                //得到手机号码
                String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                //当手机号码为空的或者为空字段 跳过当前循环
                if (TextUtils.isEmpty(phoneNumber))
                    continue;

                //得到联系人名称
                String contactName = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                //得到联系人ID
                Long contactid = phoneCursor.getLong(phoneCursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID));

                /*//得到联系人头像ID
                Long photoid = phoneCursor.getLong(PHONES_PHOTO_ID_INDEX);

                //得到联系人头像Bitamp
                Bitmap contactPhoto = null;

                //photoid 大于0 表示联系人有头像 如果没有给此人设置头像则给他一个默认的
                if (photoid > 0) {
                    Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactid);
                    InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(resolver, uri);
                    contactPhoto = BitmapFactory.decodeStream(input);
                } else {
                    contactPhoto = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                }*/
                mContacts.add(new Contact(Cn2Spell.getPinYinFirstLetter(contactName),contactName));
//                mContactsPhonto.add(contactPhoto);
            }
            phoneCursor.close();
        }
        for (int i = 0; i < mContacts.size(); i++) {
            Log.i(TAG, "电话号码: " + mContacts.get(i).getName());
        }
        for (int i = 0; i < mContacts.size(); i++) {
            Log.i(TAG, "姓名: " + mContacts.get(i).getName());
        }
//        Log.i(TAG, "头像的数量: " + mContactsPhonto.size());
        Log.i(TAG, "--------------------------------------------");
        List<String> _list = new ArrayList<>();
        for(Contact item : mContacts){
            _list.add(item.getIndex());
        }
        String[] array = new String[_list.size()];
        sideBar.setIndexItems((String[])_list.toArray(array));
        mAdatper.notifyDataSetChanged();
       /*
        */
    }
}
