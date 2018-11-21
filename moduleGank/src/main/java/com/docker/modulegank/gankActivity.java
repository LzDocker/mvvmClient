package com.docker.modulegank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.docker.constantmodule.Constant.ConstantsRouter;

@Route(path = ConstantsRouter.ModuleGank.ACTIVITY_GANK)
public class gankActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modulegank_activity_gank);
    }
}
