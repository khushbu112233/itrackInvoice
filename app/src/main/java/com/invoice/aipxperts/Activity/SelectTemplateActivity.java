package com.invoice.aipxperts.Activity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.invoice.aipxperts.Adapter.CustomGridTemplateAdapter;
import com.invoice.aipxperts.R;
import com.invoice.aipxperts.Utils.Pref;
import com.invoice.aipxperts.databinding.ActivitySelectTemplateBinding;

/**
 * Created by aipxperts-ubuntu-01 on 28/10/17.
 */

public class SelectTemplateActivity extends Activity {
    ActivitySelectTemplateBinding mBinding;
    int[] imageId = {
            R.mipmap.temp1,
            R.mipmap.temp2,
            R.mipmap.temp1,
            R.mipmap.temp2,
            R.mipmap.temp1,
            R.mipmap.temp2};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_select_template);

        CustomGridTemplateAdapter adapter = new CustomGridTemplateAdapter(SelectTemplateActivity.this, imageId);
        mBinding.grid.setAdapter(adapter);
        mBinding.grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(SelectTemplateActivity.this,MainActivity.class);
                startActivity(intent);
                Pref.setValue(SelectTemplateActivity.this,"first","1");
                finish();

            }
        });


    }
}
