package www.mys.com.security.node.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.mayousheng.www.initview.ViewDesc;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import www.mys.com.security.node.R;
import www.mys.com.security.node.adapter.MainAdapter;
import www.mys.com.security.node.base.BaseAppbarLoadingActivity;
import www.mys.com.security.node.base.BaseNoteFragment;
import www.mys.com.security.node.base.StaticParam;
import www.mys.com.security.node.fragment.AddressFragment;
import www.mys.com.security.node.fragment.BankCardFragment;
import www.mys.com.security.node.fragment.CreditCardFragment;
import www.mys.com.security.node.fragment.ReceiptFragment;
import www.mys.com.security.node.fragment.TextFragment;
import www.mys.com.security.node.pojo.AllRealData;
import www.mys.com.security.node.pojo.BaseRealNode;
import www.mys.com.security.node.utils.MySettings;
import www.mys.com.security.node.utils.ToolUtils;
import www.mys.com.security.node.utils.DataUtils;
import www.mys.com.security.node.utils.LogUtils;
import www.mys.com.security.node.utils.StringUtils;
import www.mys.com.security.node.utils.encrypt.EncryptUtils;
import www.mys.com.security.node.utils.file.FileUtils;

public class MainActivity extends BaseAppbarLoadingActivity {

    @ViewDesc(viewId = R.id.drawer_layout)
    public DrawerLayout drawerLayout;
    @ViewDesc(viewId = R.id.nav_view)
    public NavigationView navigationView;
    @ViewDesc(viewId = R.id.view_pager_content)
    public ViewPager viewPagerContent;
    @ViewDesc(viewId = R.id.bottom_navigation)
    public BottomNavigationView bottomNavigation;
    @ViewDesc(viewId = R.id.action_add)
    public FloatingActionButton actionAdd;
    @ViewDesc(viewId = R.id.toolbar)
    public Toolbar toolbar;
    private List<Fragment> fragmentList;
    private HashMap<Integer, ArrayList<BaseRealNode>> allRealData;
    private ArrayList<BaseRealNode> currentData;
    private MainAdapter mainAdapter;
    private MenuItem menuItem;
    private int noteType = BaseRealNode.Type.TEXT;
    private int[] texts = new int[]{R.string.text, R.string.address, R.string.receipt, R.string.bank_card, R.string.credit_card};
    private static final int FILE_SELECT_CODE = 3;

    @Override
    public View.OnClickListener onLeftIconClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        };
    }

    @Override
    public String getAppBarTitle() {
        return getString(R.string.text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String tempStr = null;
        switch (item.getItemId()) {
            case R.id.export_clipboard:
                tempStr = ToolUtils.copyText(ToolUtils.getClipboardManager(getApplicationContext())
                        , EncryptUtils.encrypt(DataUtils.getAllData().toString()));
                Toast.makeText(getApplicationContext()
                        , getString(StringUtils.isEmpty(tempStr) ? R.string.export_error : R.string.export_success)
                        , Toast.LENGTH_LONG).show();
                return true;
            case R.id.export_sdcard:
                tempStr = EncryptUtils.encrypt(DataUtils.getAllData().toString());
                File resultFile = DataUtils.getExportFile();
                if (FileUtils.byte2File(resultFile, tempStr.getBytes(Charset.forName("utf-8")))) {
                    Toast.makeText(getApplicationContext()
                            , getString(R.string.export_success)
                            , Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext()
                            , getString(R.string.export_error)
                            , Toast.LENGTH_LONG).show();
                }
                return true;
            case R.id.import_clipboard:
                tempStr = ToolUtils.getText(ToolUtils.getClipboardManager(getApplicationContext()));
                importData(tempStr);
                return true;
            case R.id.import_sdcard:
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                try {
                    startActivityForResult(Intent.createChooser(intent
                            , getString(R.string.choose_file)),
                            FILE_SELECT_CODE);
                } catch (android.content.ActivityNotFoundException ex) {
                    LogUtils.log(getString(R.string.not_choose_file));
                }
                return true;
            default:
                LogUtils.log("unknow item:" + item);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ToolUtils.verifyStoragePermissions(this);
        setSupportActionBar(toolbar);
        fragmentList = new ArrayList<>();
        fragmentList.add(new TextFragment());
        fragmentList.add(new AddressFragment());
        fragmentList.add(new ReceiptFragment());
        fragmentList.add(new BankCardFragment());
        fragmentList.add(new CreditCardFragment());
        mainAdapter = new MainAdapter(getSupportFragmentManager(), fragmentList);
        viewPagerContent.setAdapter(mainAdapter);
        initEvent();
        initSecurityCode();
    }

    private void initSecurityCode() {
        if (ToolUtils.isFirstIn()) {
            final String securityCode = ToolUtils.getSecurityCode();
            MySettings.getInstance().saveSetting(StaticParam.INIT_ED, true);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.security_title);
            builder.setMessage(R.string.security_message);
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ToolUtils.copyText(ToolUtils.getClipboardManager(getApplicationContext()), securityCode);
                    MySettings.getInstance().saveSetting(StaticParam.SAVE_ED, true);
                    Toast.makeText(getApplicationContext(), R.string.save_ed, Toast.LENGTH_LONG).show();
                }
            });
            builder.create().show();
        }
    }

    private void initEvent() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_security_code:
                        startActivity(new Intent(getApplicationContext(), SecurityCodeActivity.class));
                        break;
                    case R.id.nav_about:
                        startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
        viewPagerContent.addOnPageChangeListener(
                new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        if (menuItem != null) {
                            menuItem.setChecked(false);
                        } else {
                            bottomNavigation.getMenu().getItem(0).setChecked(false);
                        }
                        menuItem = bottomNavigation.getMenu().getItem(position);
                        menuItem.setChecked(true);
                        noteType = position + 1;
                        refreshState();
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                }
        );
        bottomNavigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_text:
                                noteType = BaseRealNode.Type.TEXT;
                                viewPagerContent.setCurrentItem(0);
                                break;
                            case R.id.action_address:
                                noteType = BaseRealNode.Type.ADDRESS;
                                viewPagerContent.setCurrentItem(1);
                                break;
                            case R.id.action_receipt:
                                noteType = BaseRealNode.Type.RECEIPT;
                                viewPagerContent.setCurrentItem(2);
                                break;
                            case R.id.action_bank_card:
                                noteType = BaseRealNode.Type.BANK_CARD;
                                viewPagerContent.setCurrentItem(3);
                                break;
                            case R.id.action_credit_card:
                                noteType = BaseRealNode.Type.CREDIT_CARD;
                                viewPagerContent.setCurrentItem(4);
                                break;
                        }
                        refreshState();
                        return false;
                    }
                });
        actionAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (noteType) {
                    case BaseRealNode.Type.TEXT:
                        startActivity(new Intent(getApplicationContext(), TextActivity.class));
                        break;
                    case BaseRealNode.Type.ADDRESS:
                        startActivity(new Intent(getApplicationContext(), AddressActivity.class));
                        break;
                    case BaseRealNode.Type.RECEIPT:
                        startActivity(new Intent(getApplicationContext(), ReceiptActivity.class));
                        break;
                    case BaseRealNode.Type.BANK_CARD:
                        startActivity(new Intent(getApplicationContext(), BankCardActivity.class));
                        break;
                    case BaseRealNode.Type.CREDIT_CARD:
                        startActivity(new Intent(getApplicationContext(), CreditCardActivity.class));
                        break;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshState();
    }

    private void refreshState() {
        ((BaseNoteFragment) fragmentList.get(noteType - 1)).onLoadMore(0);
        allRealData = DataUtils.getResultData();
        currentData = allRealData.get(noteType);
        if (currentData == null || currentData.size() < 6) {
            actionAdd.animate().translationY(0);
            bottomNavigation.animate().translationY(0);
        }
        appBarTitle.setText(texts[noteType - 1]);
    }

    private void importData(String tempStr) {
        if (tempStr == null) {
            Toast.makeText(getApplicationContext(), getString(R.string.import_error)
                    , Toast.LENGTH_LONG).show();
        } else {
            tempStr = EncryptUtils.decrypt(tempStr);
            AllRealData allRealData = new AllRealData(tempStr);
            if (allRealData.datas == null || allRealData.datas.isEmpty()) {
                Toast.makeText(getApplicationContext()
                        , getString(R.string.import_failed2)
                        , Toast.LENGTH_LONG).show();
                return;
            }
            int result = DataUtils.importData(allRealData);
            Toast.makeText(getApplicationContext()
                    , getString(result > 0 ? R.string.import_success : R.string.import_failed)
                    , Toast.LENGTH_LONG).show();
        }
        refreshState();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case FILE_SELECT_CODE:
                    if (data != null && data.getData() != null) {
                        LogUtils.log("choose file path=" + data.getData().getPath());
                        String tempStr = FileUtils.readFile(data.getData().getPath());
                        if (StringUtils.isEmpty(FileUtils.readFile(data.getData().getPath()))) {
                            try {
                                tempStr = FileUtils.inputStream2String(getContentResolver()
                                        .openInputStream(data.getData()));
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), e.getMessage()
                                        , Toast.LENGTH_LONG).show();
                            }
                        }
                        if (!StringUtils.isEmpty(tempStr)) {
                            importData(tempStr);
                            break;
                        }
                    }
                    Toast.makeText(getApplicationContext(), getString(R.string.import_error)
                            , Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }
}
