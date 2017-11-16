package com.invoice.aipxperts.Activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.invoice.aipxperts.Fragment.CompanyListFragment;
import com.invoice.aipxperts.Fragment.CustomerListFragment;
import com.invoice.aipxperts.Fragment.InvoiceListFragment;
import com.invoice.aipxperts.Model.CompanyProfile;
import com.invoice.aipxperts.R;
import com.invoice.aipxperts.Utils.Pref;
import com.invoice.aipxperts.databinding.ActivityMainBinding;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import io.realm.RealmResults;

import static com.invoice.aipxperts.Activity.BaseActivity.realm;


public class MainActivity extends FragmentActivity implements View.OnClickListener {
    public ActivityMainBinding mBinding;
    //public SlidingMenu slidingMenu;

    /**
     * Realm initialization.
     */
    private CompanyProfile[] CompanyProfile1;
    ArrayList<CompanyProfile> companyArrayList = new ArrayList<CompanyProfile>();
    PdfPTable table;
    Document doc;
    File myFile;
    ResideMenu resideMenu;

    private ResideMenuItem itemInvoice;
    private ResideMenuItem itemCompany;
    private ResideMenuItem itemCustomer;
    private ResideMenuItem itemLineItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //
        preview();
        getdetailsfromDb();
        setUpMenu();
        if( savedInstanceState == null )
            changeFragment(new InvoiceListFragment());
        // create menu items;


        //   setSlidingMenu_click();
        mBinding.includeHeader.imgDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // mBinding.drawerLayout.openDrawer(Gravity.LEFT);
                /*InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                slidingMenu.toggle();*/
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);

            }
        });
       /* mBinding.txtPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    createPdf();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }
        });*/
        String str="{\n" +
                "    \"employees\": [\n" +
                "        {\"firstName\": \"John\", \"lastName\": \"Doe\"}, \n" +
                "        {\"firstName\": \"Anna\", \"lastName\": \"Smith\"}, \n" +
                "        {\"firstName\": \"Peter\"}\n" +
                "    ]\n" +
                "}";
        try {
            JSONObject jsonObject = new JSONObject(str);
            JSONArray jsonArray = jsonObject.getJSONArray("employees");
            for(int i=0;i<jsonArray.length();i++) {
                JSONObject issueObj = jsonArray.getJSONObject(i);
                Iterator iterator = issueObj.keys();
                while (iterator.hasNext()) {
                    String key = (String) iterator.next();
                    Log.e("test",""+key);

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * menu supported
     */
    private void setUpMenu() {

        // attach to current activity;
        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.mipmap.splash_screen);
        resideMenu.attachToActivity(this);
        resideMenu.setMenuListener(menuListener);
        //resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip.
        resideMenu.setScaleValue(0.6f);

        // create menu items;
        itemInvoice   = new ResideMenuItem(this, R.drawable.ic_launcher, "Invoice");
        itemCompany  = new ResideMenuItem(this, R.drawable.ic_launcher,  "Company");
        itemCustomer = new ResideMenuItem(this, R.drawable.ic_launcher, "Customer");
        itemLineItems = new ResideMenuItem(this, R.drawable.ic_launcher, "Line Items");

        itemInvoice.setOnClickListener(this);
        itemCompany.setOnClickListener(this);
        itemCustomer.setOnClickListener(this);
        itemLineItems.setOnClickListener(this);

        resideMenu.addMenuItem(itemInvoice, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemCompany, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemCustomer, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemLineItems, ResideMenu.DIRECTION_LEFT);
        // You can disable a direction by setting ->
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        mBinding.includeHeader.imgDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {

        if (view == itemInvoice){
            changeFragment(new InvoiceListFragment());
        }else if (view == itemCompany){
            Pref.setValue(MainActivity.this,"CompanyFrom","1");
            changeFragment(new CompanyListFragment());

        }else if (view == itemCustomer){
            changeFragment(new CustomerListFragment());
        }else if (view == itemLineItems){
            changeFragment(new CustomerListFragment());
        }

        resideMenu.closeMenu();
    }

    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
        }

        @Override
        public void closeMenu() {
        }
    };

    private void changeFragment(Fragment targetFragment){
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_main_container, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    // What good method is to access resideMenu？
    public ResideMenu getResideMenu(){
        return resideMenu;
    }
    private void createPdf()  throws FileNotFoundException, DocumentException {

        Font blackFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, new CMYKColor(0, 0, 0, 255));
        Font blackNormalFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.NORMAL, new CMYKColor(0, 0, 0, 255));
        Font yellowFont = FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new CMYKColor(0, 0, 255, 0));
        Document document = new Document();
        try
        {
            File pdfFolder = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "Invoice-");
            if (!pdfFolder.exists()) {
                pdfFolder.mkdir();
                Log.e("self", "Pdf Directory created");
            }

            //Create time stamp
            Date date = new Date();
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(date);

            myFile = new File(pdfFolder +""+ timeStamp + ".pdf");

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(myFile));


            document.open();
            addContent(document,blackFont,blackNormalFont);
            document.close();
            //document.add(new Paragraph("Styling Example"));

          /*  //Paragraph with color and font styles
            Paragraph paragraphOne = new Paragraph("Some colored paragraph text", redFont);
            document.add(paragraphOne);

            //Create chapter and sections
            Paragraph chapterTitle = new Paragraph("Chapter Title", yellowFont);
            Chapter chapter1 = new Chapter(chapterTitle, 1);
            chapter1.setNumberDepth(0);

            Paragraph sectionTitle = new Paragraph("Section Title", redFont);
            Section section1 = chapter1.addSection(sectionTitle);

            Paragraph sectionContent = new Paragraph("Section Text content", blueFont);
            section1.add(sectionContent);

            document.add(chapter1);

            document.close();*/
            writer.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void addContent(Document document,Font black,Font blackNormal) throws DocumentException {

        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(1);
        PdfPTable table = new PdfPTable(2);

        table.setHorizontalAlignment(Element.ALIGN_CENTER);

        PdfPCell cell = new PdfPCell();
        PdfPCell cell1 = new PdfPCell();

        Paragraph subPara;
        subPara=new Paragraph("VIHAAN TELECOMMUNICATION PVT. LTD.", black);
        cell.addElement(subPara);
        subPara=new Paragraph("1/A CHUNILAL PARK SOC.", blackNormal);
        cell.addElement(subPara);
        subPara=new Paragraph("NR. VIJAYNAGAR RAILWAY CROSING",blackNormal);
        cell.addElement(subPara);
        subPara=new Paragraph("NARANPURA",blackNormal);
        cell.addElement(subPara);
        subPara=new Paragraph("AHMEDABAD",blackNormal);
        cell.addElement(subPara);
        subPara=new Paragraph("CIN NO. U64204GJ2012PTC069117",blackNormal);
        cell.addElement(subPara);
        subPara=new Paragraph("GSTIN/UIN: 24AADCV9546Q1ZI",blackNormal);
        cell.addElement(subPara);
        subPara=new Paragraph("CIN: U64204GJ2012PTC069117",blackNormal);
        cell.addElement(subPara);
        subPara.setAlignment(Element.ALIGN_LEFT);
        cell.setPaddingRight(10f);

        Paragraph subPara1;

        subPara1=new Paragraph("AIPXPERTS TECHNOLABS PVT. LTD", black);
        cell1.addElement(subPara1);
        subPara1=new Paragraph("502 - TITANIUM ONE, PAKWAN CROSS ROAD,", blackNormal);
        cell1.addElement(subPara1);
        subPara1=new Paragraph("S.G. HIGHWAY, AHMEDABAD - 380054",blackNormal);
        cell1.addElement(subPara1);
        subPara1=new Paragraph("State Name : Gujarat, Code : 24",blackNormal);
        cell1.addElement(subPara1);
        subPara1=new Paragraph("GSTIN/UIN : 24AAPCA2035F1Z0",blackNormal);
        cell1.addElement(subPara1);
        subPara1.setAlignment(Element.ALIGN_RIGHT);
        cell1.setPaddingLeft(10f);
        cell.setBorder(Rectangle.NO_BORDER);
        cell1.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        table.addCell(cell1);
        // add a list
        catPart.add(table);

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 2);
        catPart.add(paragraph);

        PdfPTable table_invoice = new PdfPTable(1);
        PdfPCell pdfPCell = new PdfPCell();
        pdfPCell = new PdfPCell(new Phrase("InVoice No.: 2374",black));
        pdfPCell.setBorder(Rectangle.NO_BORDER);
        table_invoice.addCell(pdfPCell);
        pdfPCell = new PdfPCell(new Phrase("Date : 12-oct-2017",black));
        pdfPCell.setBorder(Rectangle.NO_BORDER);
        table_invoice.addCell(pdfPCell);
        catPart.add(table_invoice);

        Paragraph paragraph3 = new Paragraph();
        addEmptyLine(paragraph3, 2);
        catPart.add(paragraph3);
        // add a table
        createTable(catPart,black,blackNormal);



        // Second parameter is the number of the chapter
        // now add all this to the document
        document.add(catPart);

    }
    private static void createTable(Chapter subCatPart,Font black,Font blackNormal)
            throws BadElementException {
        PdfPTable table = new PdfPTable(4);
        table = new PdfPTable(new float[] { 15, 4,3,6 });
        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);

        PdfPCell c1 = new PdfPCell(new Phrase("Description of goods"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("HSN/SAC"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("GST Rate"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Amount"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);


        PdfPCell c3 =null;


        table.addCell(setvalueOfPdfCell("INTERNET LEASED LINE - 8Mbps",c3,1,black));
        table.addCell(setvalueOfPdfCell("998422",c3,2,blackNormal));
        table.addCell(setvalueOfPdfCell("18%",c3,3,blackNormal));
        table.addCell(setvalueOfPdfCell("20000",c3,4,black));

        table.addCell(setvalueOfPdfCell("INSTALLATION CHARGES",c3,11,black));
        table.addCell(setvalueOfPdfCell("998422",c3,21,blackNormal));
        table.addCell(setvalueOfPdfCell("18%",c3,31,blackNormal));
        table.addCell(setvalueOfPdfCell("30000",c3,41,black));

        table.addCell(setvalueOfPdfCell("CGST",c3,12,black));
        table.addCell(setvalueOfPdfCell("",c3,22,black));
        table.addCell(setvalueOfPdfCell("",c3,32,black));
        table.addCell(setvalueOfPdfCell("887.40",c3,42,black));

        table.addCell(setvalueOfPdfCell("SGST",c3,13,black));
        table.addCell(setvalueOfPdfCell("",c3,23,black));
        table.addCell(setvalueOfPdfCell("",c3,33,black));
        table.addCell(setvalueOfPdfCell("887.40",c3,43,black));

        table.addCell(setvalueOfPdfCell("ROUND OFF",c3,14,black));
        table.addCell(setvalueOfPdfCell("",c3,24,black));
        table.addCell(setvalueOfPdfCell("",c3,34,black));
        table.addCell(setvalueOfPdfCell("0.20",c3,44,black));
        subCatPart.add(table);

        PdfPTable table1 = new PdfPTable(4);
        table1 = new PdfPTable(new float[] { 15, 4,3,6 });
        PdfPCell c2 = new PdfPCell();
        c2 = new PdfPCell(new Phrase("ToTal",black));
        c2.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table1.addCell(c2);
        c2.setBorder(Rectangle.NO_BORDER);
        c2.setBorder(Rectangle.BOX);
        c2 = new PdfPCell(new Phrase(""));
        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(c2);

        c2 = new PdfPCell(new Phrase(""));
        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(c2);
        c2 = new PdfPCell(new Phrase("₹ 11,635.00",black));
        c2.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table1.addCell(c2);

        subCatPart.add(table1);
    }
    public static PdfPCell setvalueOfPdfCell(String value, PdfPCell cell, int position,Font black)
    {
        /*cell = new PdfPCell();
        Paragraph p=new Paragraph(value, black);
        cell.addElement(p);*/
        cell = new PdfPCell(new Phrase(value,black));
        if(position==1||position==2||position==11||position==21) {
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        }else
        {
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        }
        cell.setBorderWidthBottom(0);
        cell.setBorderWidthTop(0);
        return cell;
    }
    private static void createList(Paragraph subCatPart) {
        List list = new List(true, false, 10);
        list.add(new ListItem("First point"));
        list.add(new ListItem("Second point"));
        list.add(new ListItem("Third point"));
        subCatPart.add(list);
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
    public PdfPCell getBoldCell(String text, int alignment,Font font ) {
        Phrase f= new Phrase(text,font);
        // f.setFont(subFont);
        PdfPCell cell = new PdfPCell(f);
        cell.setPadding(0);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private void preview() {
        InvoiceListFragment fragment = new InvoiceListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_main_container, fragment).commit();

    }



    /**
     * set for drawer
     *//*
    public void setSlidingMenu_click() {
        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setShadowWidthRes(R.dimen.shadow_width);

//        slidingMenu.setShadowDrawable(R.drawable.shadow);
        slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        slidingMenu.setFadeDegree(0.35f);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setMenu(R.layout.sliding_menu);
        slidingMenu.setSlidingEnabled(true);

    }
    public void slidingMenuClicked(final int position) {

        // slidingMenuToggle();
        if (position == 0) {


        } else if (position == 1) {


        } else if (position == 2) {
            resideMenu.closeMenu();
            CustomerListFragment customerListFragment = new CustomerListFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.frame_main_container,customerListFragment).addToBackStack(null).commit();

        } else if (position == 3) {

        }
    }

    public void slidingMenuToggle() {
        slidingMenu.toggle();
    }*/

    public void getdetailsfromDb() {
        final RealmResults<CompanyProfile> CompanyProfile = realm.where(CompanyProfile.class).findAll();
        CompanyProfile1 = new CompanyProfile[CompanyProfile.size()];
        companyArrayList.clear();
        if (CompanyProfile.size() > 0) {

            for (int cat = 0; cat < CompanyProfile.size(); cat++) {

                CompanyProfile1[cat] = new CompanyProfile();

                CompanyProfile1[cat].setUserId(CompanyProfile.get(cat).getUserId());
                CompanyProfile1[cat].setUserName(CompanyProfile.get(cat).getUserName());
                CompanyProfile1[cat].setUserEmailId(CompanyProfile.get(cat).getUserEmailId());
                CompanyProfile1[cat].setUserPhoneNumber(CompanyProfile.get(cat).getUserPhoneNumber());
                CompanyProfile1[cat].setAddress1(CompanyProfile.get(cat).getAddress1());
                CompanyProfile1[cat].setAddress2(CompanyProfile.get(cat).getAddress2());
                CompanyProfile1[cat].setCity(CompanyProfile.get(cat).getCity());
                CompanyProfile1[cat].setState(CompanyProfile.get(cat).getState());
                CompanyProfile1[cat].setPincode(CompanyProfile.get(cat).getPincode());
                CompanyProfile1[cat].setCountry(CompanyProfile.get(cat).getCountry());
                CompanyProfile1[cat].setBankName(CompanyProfile.get(cat).getBankName());
                CompanyProfile1[cat].setA_CNo(CompanyProfile.get(cat).getA_CNo());
                CompanyProfile1[cat].setBranchName(CompanyProfile.get(cat).getBranchName());
                CompanyProfile1[cat].setIFSCode(CompanyProfile.get(cat).getIFSCode());
                CompanyProfile1[cat].setCompanyPAN(CompanyProfile.get(cat).getCompanyPAN());
                CompanyProfile1[cat].setTaxNo(CompanyProfile.get(cat).getTaxNo());
                CompanyProfile1[cat].setGSTNo(CompanyProfile.get(cat).getGSTNo());

                companyArrayList.add(CompanyProfile1[cat]);
            }
        }


    }


}
