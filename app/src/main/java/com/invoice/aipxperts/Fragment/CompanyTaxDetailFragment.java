package com.invoice.aipxperts.Fragment;


import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.invoice.aipxperts.Adapter.CompanyNumberTypeTaxAdapter;
import com.invoice.aipxperts.Adapter.NumberTypeTaxAdapter;
import com.invoice.aipxperts.Interface.OnDeleteClickTick;
import com.invoice.aipxperts.Interface.OnImageClickTick;
import com.invoice.aipxperts.Model.NumberDisplayTax;
import com.invoice.aipxperts.Model.NumberTaxType;
import com.invoice.aipxperts.R;
import com.invoice.aipxperts.Utils.Pref;
import com.invoice.aipxperts.Widget.TextView_Bold;
import com.invoice.aipxperts.databinding.CompanyTaxDetailFragmentLayoutBinding;

import java.util.ArrayList;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

import static com.invoice.aipxperts.Activity.BaseActivity.realm;

/**
 * Created by aipxperts-ubuntu-01 on 30/10/17.
 */

public class CompanyTaxDetailFragment extends Fragment {
    CompanyTaxDetailFragmentLayoutBinding mBinding;
    Context context;
    View rootView;
    Dialog dialog1=null;
    String uuid;
    NumberTaxType[] numberTaxType1;
    NumberTaxType[] number1;
    NumberDisplayTax[]  numberDisplayTaxe1;
    ArrayList<String> numberTaxTypeslist = new ArrayList<>();
    OnDeleteClickTick onDeleteClickTick;
    OnImageClickTick onImageClickTick;
    boolean isAddDialogUpdate=false;

    CompanyNumberTypeTaxAdapter companyNumberTypeTaxAdapter;
    ArrayList<NumberTaxType> numberTypeArrayList = new ArrayList<>();
    ArrayList<NumberDisplayTax> numberDisplayTaxesList= new ArrayList<>();
    NumberTypeTaxAdapter numberTypeTaxAdapter;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.company_tax_detail_fragment_layout, container, false);
        rootView = mBinding.getRoot();
        context = getActivity();

        if(Pref.getValue(context,"add_number_type","").equalsIgnoreCase("")) {
            AddNumberTypeDb();
        }
        mBinding.imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                openDialog_for_add_Tax();
            }
        });





        onDeleteClickTick = new OnDeleteClickTick() {
            @Override
            public void OnDeleteClickTick(int position, String id) {

                numberTypeArrayList.get(position).setDeleted(true);
                numberTypeArrayList.remove(position);
                numberTypeTaxAdapter.notifyDataSetChanged();
            }
        };


        onImageClickTick = new OnImageClickTick() {
            @Override
            public void OnImageClickTick(int position, final boolean value, final String str) {
                numberTypeArrayList.get(position).setChecked(value);
                updateNumberTypeTaxAdapter(str,value);
                numberTypeTaxAdapter.notifyDataSetChanged();
            }
        };

        /*    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_dropdown_item_1line, SPINNERLIST);
     mBinding.androidMaterialDesignSpinner.setAdapter(arrayAdapter);
        mBinding.androidMaterialDesignSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("click value",""+SPINNERLIST[i]);

            }
        });*/
        //Constants.setTextWatcher1(context,mBinding.edtGstNo,mBinding.txtGstNo);
        mBinding.txtDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  ((AddCompanyProfileActivity)context).SetCompanyPAN(mBinding.edtCompanyPan.getText().toString());
                // ((AddCompanyProfileActivity)context).SetTaxNo(mBinding.edtTaxNo.getText().toString());
                //      ((AddCompanyProfileActivity)context).SetGstNo(mBinding.edtGstNo.getText().toString());



                CompanyDetailMainFragment.mBinding.viewPager1.setCurrentItem(2);

            }
        });


        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        getDisplayNumberTypeFromdb();
    }

    public void updateNumberTypeTaxAdapter(final String id, final boolean value) {
        if (!id.equalsIgnoreCase(""))
        {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmResults<NumberTaxType> numberTaxTypes = realm.where(NumberTaxType.class).findAll();
                    numberTaxTypes.deleteAllFromRealm();
                }
            });

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmResults<NumberDisplayTax> numberTaxTypes = realm.where(NumberDisplayTax.class).findAll();
                    numberTaxTypes.deleteAllFromRealm();
                }
            });
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                    for (int cat = 0; cat < numberTypeArrayList.size(); cat++) {


                        numberTaxType1[cat] = new NumberTaxType();
                        if (numberTypeArrayList.get(cat).getNumberTaxTypeId().equalsIgnoreCase(id)) {

                            numberTaxType1[cat].setNumberTaxTypeId(numberTypeArrayList.get(cat).getNumberTaxTypeId());
                            numberTaxType1[cat].setNumberTaxTypeName(numberTypeArrayList.get(cat).getNumberTaxTypeName());
                            numberTaxType1[cat].setChecked(value);
                            numberTaxType1[cat].setDeleted(numberTypeArrayList.get(cat).isDeleted());
                            realm.insert(numberTaxType1[cat]);
                        } else {

                            numberTaxType1[cat].setNumberTaxTypeId(numberTypeArrayList.get(cat).getNumberTaxTypeId());
                            numberTaxType1[cat].setNumberTaxTypeName(numberTypeArrayList.get(cat).getNumberTaxTypeName());
                            numberTaxType1[cat].setChecked(numberTypeArrayList.get(cat).isChecked());
                            numberTaxType1[cat].setDeleted(numberTypeArrayList.get(cat).isDeleted());
                            realm.insert(numberTaxType1[cat]);
                        }
                    }

                }
            });

        }
    }


    public void AddNumberTypeDb() {
        Pref.setValue(context,"add_number_type","1");
        numberTaxTypeslist.clear();
        numberTaxTypeslist.add("CIN");
        numberTaxTypeslist.add("GSTIN/UIN");
        numberTaxTypeslist.add("Service Tax Number");
        numberTaxTypeslist.add("PAN Number");
        for(int i=0;i<numberTaxTypeslist.size();i++) {
            final int finalI = i;
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    Long tsLong = System.currentTimeMillis() / 1000;
                    String ts = tsLong.toString();
                    // Add a Category
                    NumberTaxType numberTaxType = realm.createObject(NumberTaxType.class);
                    uuid = UUID.randomUUID().toString();
                    numberTaxType.setNumberTaxTypeId(uuid);
                    numberTaxType.setNumberTaxTypeName(numberTaxTypeslist.get(finalI));
                    numberTaxType.setChecked(false);
                    numberTaxType.setDeleted(false);
                }
            });
        }
    }
    public void openDialog_for_add_Tax() {

        dialog1=new Dialog(context,R.style.PauseDialog);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.setContentView(R.layout.pop_for_add_number_type);
        LinearLayout ll_close = (LinearLayout)dialog1.findViewById(R.id.ll_close);
        LinearLayout ll_add = (LinearLayout)dialog1.findViewById(R.id.ll_add);
        ListView list_tax= (ListView)dialog1.findViewById(R.id.list_tax);
        final EditText edt_gst_no= (EditText)dialog1.findViewById(R.id.edt_gst_no);
        TextView_Bold txt_ok = (TextView_Bold) dialog1.findViewById(R.id.txt_ok);

        numberTypeTaxAdapter = new NumberTypeTaxAdapter(context,numberTypeArrayList);
        numberTypeTaxAdapter.OndeleteClickListener(onDeleteClickTick);
        numberTypeTaxAdapter.OnImageClickListener(onImageClickTick);
        list_tax.setAdapter(numberTypeTaxAdapter);
        getNumberTypeFromdb("");
/*        ColorCustomAdapter adapter=new ColorCustomAdapter(context,colorList);
        adapter.onColorClickTick(onColorClickTick);
        gridColor.setAdapter(adapter);*/

        ll_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.dismiss();
            }
        });
        txt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        RealmResults<NumberDisplayTax> numberTaxTypes = realm.where(NumberDisplayTax.class).findAll();
                        numberTaxTypes.deleteAllFromRealm();
                    }
                });
                for (int i=0;i<numberTypeArrayList.size();i++)
                {
                    if(numberTypeArrayList.get(i).isChecked()){
                        final int finalI = i;
                        if(numberDisplayTaxesList.size()>0)
                        {

                            for(int j =0;j<numberDisplayTaxesList.size();j++) {
                                if(!numberTypeArrayList.get(i).getNumberTaxTypeId().equalsIgnoreCase(numberDisplayTaxesList.get(j).getNumberDisplayTaxId())) {
                                    final int finalJ = j;
                                    realm.executeTransaction(new Realm.Transaction() {
                                        @Override
                                        public void execute(Realm realm) {

                                            NumberDisplayTax numberDisplayTax = realm.createObject(NumberDisplayTax.class);
                                            numberDisplayTax.setNumberDisplayTaxId(numberDisplayTaxesList.get(finalJ).getNumberDisplayTaxId());
                                            numberDisplayTax.setNumberDisplayTaxName(numberDisplayTaxesList.get(finalJ).getNumberDisplayTaxName());
                                            numberDisplayTax.setNumberDisplayTaxValue(numberDisplayTaxesList.get(finalJ).getNumberDisplayTaxValue());
                                            numberDisplayTax.setDelete(false);
                                        }
                                    });
                                }
                            }
                        }else
                        {
                            realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {

                                    NumberDisplayTax numberDisplayTax = realm.createObject(NumberDisplayTax.class);
                                    numberDisplayTax.setNumberDisplayTaxId(numberTypeArrayList.get(finalI).getNumberTaxTypeId());
                                    numberDisplayTax.setNumberDisplayTaxName(numberTypeArrayList.get(finalI).getNumberTaxTypeName());
                                    numberDisplayTax.setNumberDisplayTaxValue("");
                                    numberDisplayTax.setDelete(false);
                                }
                            });
                        }
                    }
                }
                getDisplayNumberTypeFromdb();
                dialog1.dismiss();
            }
        });

        ll_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edt_gst_no.getText().toString().trim().equalsIgnoreCase("")) {
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                    NumberTaxType number = realm.where(NumberTaxType.class).equalTo("NumberTaxTypeName", edt_gst_no.getText().toString()).findFirst();
                    if(number==null) {
                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                // Add a NumberTaxtype
                                NumberTaxType numberTaxType = realm.createObject(NumberTaxType.class);
                                String uuid = UUID.randomUUID().toString();
                                numberTaxType.setNumberTaxTypeId(uuid);
                                numberTaxType.setNumberTaxTypeName(edt_gst_no.getText().toString());
                                numberTaxType.setChecked(false);
                                numberTaxType.setDeleted(false);

                            }
                        });
                        getNumberTypeFromdb("");
                        edt_gst_no.setText("");
                    }
                }else
                {
                    Toast.makeText(context,"Please add number type tax",Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog1.show();
    }

    public void getNumberTypeFromdb(String str) {
        final RealmResults<NumberTaxType> number_tax = realm.where(NumberTaxType.class).findAll();
        numberTaxType1 = new NumberTaxType[number_tax.size()];
        numberTypeArrayList.clear();
        if (number_tax.size() > 0) {

            for (int num = 0; num < number_tax.size(); num++) {

                if(str.equalsIgnoreCase("")) {
                    numberTaxType1[num] = new NumberTaxType();

                    numberTaxType1[num].setNumberTaxTypeId(number_tax.get(num).getNumberTaxTypeId());
                    numberTaxType1[num].setNumberTaxTypeName(number_tax.get(num).getNumberTaxTypeName());
                    numberTaxType1[num].setChecked(number_tax.get(num).isChecked());
                    numberTaxType1[num].setDeleted(number_tax.get(num).isDeleted());
                    numberTypeArrayList.add(numberTaxType1[num]);
                }else
                {
                    if(str.equalsIgnoreCase(number_tax.get(num).getNumberTaxTypeId())) {
                        numberTaxType1[num] = new NumberTaxType();

                        numberTaxType1[num].setNumberTaxTypeId(number_tax.get(num).getNumberTaxTypeId());
                        numberTaxType1[num].setNumberTaxTypeName(number_tax.get(num).getNumberTaxTypeName());
                        numberTaxType1[num].setChecked(false);
                        numberTaxType1[num].setDeleted(number_tax.get(num).isDeleted());
                        numberTypeArrayList.add(numberTaxType1[num]);
                    }else
                    {

                        numberTaxType1[num] = new NumberTaxType();

                        numberTaxType1[num].setNumberTaxTypeId(number_tax.get(num).getNumberTaxTypeId());
                        numberTaxType1[num].setNumberTaxTypeName(number_tax.get(num).getNumberTaxTypeName());
                        numberTaxType1[num].setChecked(number_tax.get(num).isChecked());
                        numberTaxType1[num].setDeleted(number_tax.get(num).isDeleted());
                        numberTypeArrayList.add(numberTaxType1[num]);
                    }
                }
            }
        }
        numberTypeTaxAdapter.notifyDataSetChanged();
    }
    public void getDisplayNumberTypeFromdb() {
        final RealmResults<NumberDisplayTax> numberDisplayTaxes = realm.where(NumberDisplayTax.class).findAll();
        if (numberDisplayTaxes != null) {
            numberDisplayTaxe1 = new NumberDisplayTax[numberDisplayTaxes.size()];
            numberDisplayTaxesList.clear();
            if (numberDisplayTaxes.size() > 0) {

                for (int num = 0; num < numberDisplayTaxes.size(); num++) {

                    numberDisplayTaxe1[num] = new NumberDisplayTax();

                    numberDisplayTaxe1[num].setNumberDisplayTaxId(numberDisplayTaxes.get(num).getNumberDisplayTaxId());
                    numberDisplayTaxe1[num].setNumberDisplayTaxName(numberDisplayTaxes.get(num).getNumberDisplayTaxName());
                    numberDisplayTaxe1[num].setNumberDisplayTaxValue(numberDisplayTaxes.get(num).getNumberDisplayTaxValue());
                    numberDisplayTaxe1[num].setDelete(false);
                    numberDisplayTaxesList.add(numberDisplayTaxe1[num]);
                }
            }
        }
        companyNumberTypeTaxAdapter = new CompanyNumberTypeTaxAdapter(context, numberDisplayTaxesList);
        mBinding.listTax.setAdapter(companyNumberTypeTaxAdapter);
    }


}
