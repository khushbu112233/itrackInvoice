package com.invoice.aipxperts.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.invoice.aipxperts.Interface.OnDeleteClickTick;
import com.invoice.aipxperts.Interface.OnImageClickTick;
import com.invoice.aipxperts.Model.CustomerNumberTaxType;
import com.invoice.aipxperts.Model.NumberTaxType;
import com.invoice.aipxperts.R;
import com.invoice.aipxperts.databinding.PopForAddNumberTypeForListBinding;

import java.util.ArrayList;

import io.realm.Realm;

import static com.invoice.aipxperts.Activity.BaseActivity.realm;

/**
 * Created by aipxperts-ubuntu-01 on 11/11/17.
 */

public class CustomerNumberTypeTaxAdapter extends BaseAdapter {
    Context context;
    boolean isChecked = false;
    OnDeleteClickTick onDeleteClickTick;
    OnImageClickTick onImageClickTick;
    ArrayList<CustomerNumberTaxType> numberTaxTypesList;
    public CustomerNumberTypeTaxAdapter(Context context, ArrayList<CustomerNumberTaxType> numberTaxTypesList){
        this.context=context;
        this.numberTaxTypesList = numberTaxTypesList;
    }
    public void OndeleteClickListener(OnDeleteClickTick onDeleteClickTick){
        this.onDeleteClickTick =onDeleteClickTick;
    }
    public void OnImageClickListener(OnImageClickTick onImageClickTick){
        this.onImageClickTick =onImageClickTick;
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

        final PopForAddNumberTypeForListBinding binding;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(
                    LayoutInflater.from(context),
                    R.layout.pop_for_add_number_type_for_list, parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (PopForAddNumberTypeForListBinding) convertView.getTag();
        }
        binding.edtGstNo.setText(numberTaxTypesList.get(i).getCutomerNumberTaxTypeName());
        if(numberTaxTypesList.get(i).isCutomerisChecked())
        {

                binding.imgCheck.setImageResource(R.mipmap.checkbox);



        }else
        {
            binding.imgCheck.setImageResource(R.mipmap.uncehck);
        }

        binding.imgCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isChecked)
                {
                    isChecked=false;
                    numberTaxTypesList.get(i).setCutomerisChecked(isChecked);

                }else
                {
                    isChecked=true;
                    numberTaxTypesList.get(i).setCutomerisChecked(isChecked);
//                    onImageClickTick.OnImageClickTick(i,isChecked,numberTaxTypesList.get(i).getNumberTaxTypeId());

                }
                onImageClickTick.OnImageClickTick(i,isChecked,numberTaxTypesList.get(i).getCutomerNumberTaxTypeId());
            }
        });
        binding.llDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i > 3) {
                    final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(context);

                    alertDialogBuilder.setMessage("Are you sure you want to delete this number type?");
                    // set positive button: Yes message
                    alertDialogBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            realm.executeTransaction(
                                    new Realm.Transaction() {
                                        @Override
                                        public void execute(Realm realm) {
                                            NumberTaxType numberTax = realm.where(NumberTaxType.class).equalTo("CutomerNumberTaxTypeId", numberTaxTypesList.get(i).getCutomerNumberTaxTypeId()).findFirst();
                                            numberTax.deleteFromRealm();
                                        }
                                    });

                            onDeleteClickTick.OnDeleteClickTick(i, numberTaxTypesList.get(i).getCutomerNumberTaxTypeId());
                            //notifyDataSetChanged();
                        }
                    });
                    // set negative button: No message
                    alertDialogBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // cancel the alert box and put a Toast to the user
                            dialog.cancel();

                        }
                    });
                    // set neutral button: Exit the app message

                    android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                    // show alert
                    alertDialog.show();

                }
            }
        });
        return convertView;
    }
}
