package cc.cwalk.com;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cc.cwalk.com.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {


    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }
}
