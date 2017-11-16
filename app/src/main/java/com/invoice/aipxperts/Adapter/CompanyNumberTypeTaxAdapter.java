package com.invoice.aipxperts.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.invoice.aipxperts.Model.NumberDisplayTax;
import com.invoice.aipxperts.Model.NumberTaxType;
import com.invoice.aipxperts.R;
import com.invoice.aipxperts.databinding.CompanyNumberTypeForListBinding;

import java.util.ArrayList;

import io.realm.Realm;

import static com.invoice.aipxperts.Activity.BaseActivity.realm;

/**
 * Created by aipxperts-ubuntu-01 on 11/11/17.
 */

public class CompanyNumberTypeTaxAdapter extends BaseAdapter {
    Context context;
    ArrayList<NumberDisplayTax> numberTaxTypesList;
    String uuid;

    public CompanyNumberTypeTaxAdapter(Context context, ArrayList<NumberDisplayTax> numberTaxTypesList){
        this.context=context;
        this.numberTaxTypesList = numberTaxTypesList;
    }

    @Override
    public int getCount() {
        return numberTaxTypesList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup parent) {

        final CompanyNumberTypeForListBinding binding;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(
                    LayoutInflater.from(context),
                    R.layout.company_number_type_for_list, parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (CompanyNumberTypeForListBinding) convertView.getTag();
        }
        binding.txtNumberType.setText(numberTaxTypesList.get(i).getNumberDisplayTaxName());
        binding.edtGstNo.setText(numberTaxTypesList.get(i).getNumberDisplayTaxValue());

        binding.edtGstNo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(final TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    realm.executeTransaction(
                            new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {

                                    NumberDisplayTax event = realm.where(NumberDisplayTax.class).equalTo("NumberDisplayTaxId", numberTaxTypesList.get(i).getNumberDisplayTaxId()).findFirst();
                                    event.setNumberDisplayTaxValue(v.getText().toString());

                                }
                            });
                    InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });
     /*   binding.edtGstNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(final Editable editable) {
                Log.e("test_edit",""+editable.toString());
                if(editable.toString().length()>0) {

                }
            }
        });*/
        binding.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                realm.executeTransaction(
                        new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                NumberTaxType event = realm.where(NumberTaxType.class).equalTo("NumberTaxTypeName", numberTaxTypesList.get(i).getNumberDisplayTaxName()).findFirst();
                                event.setChecked(false);

                                NumberDisplayTax num = realm.where(NumberDisplayTax.class).equalTo("NumberDisplayTaxId", numberTaxTypesList.get(i).getNumberDisplayTaxId()).findFirst();

                                numberTaxTypesList.remove(i);
                                num.deleteFromRealm();
                            }
                        });


                notifyDataSetChanged();

            }
        });


        return convertView;
    }
}
