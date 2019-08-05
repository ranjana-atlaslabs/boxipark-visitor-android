package com.al.boxipark_visitor.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.*;
import com.al.boxipark_visitor.Adapters.CustomProductAdapter;
import com.al.boxipark_visitor.MainMenu.MenuActivity;
import com.al.boxipark_visitor.Other.FontsSet;
import com.al.boxipark_visitor.Lists.MenuProducts;
import com.al.boxipark_visitor.Retrofit.RetrofitInterfaces.SingleplatformInterfaces.ISinglePlatform;
import com.al.boxipark_visitor.Retrofit.RetrofitClientInstances.SinglePlatform;
import com.al.boxipark_visitor.MainMenu.MainMenuModels.Singleplatform.Menus.RootObject;
import com.al.boxipark_visitor.R;
import com.al.boxipark_visitor.Other.ScreenSize;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductMenu extends AppCompatActivity implements ProductBottomSheet.BottomSheetListener {
    ArrayList<MenuProducts> arrayOfMenuProducts;
    LinearLayout jMenuImage;
CardView restCard;
ImageView jRestImage;
LinearLayout jScrollMenu;
    ISinglePlatform myApi;
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    int ID;
    Serializable rootObj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_menu);

        ImageView jBackMenu= findViewById(R.id.aMenuBackB);
        jBackMenu.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);

                        startActivity(intent);
                    }
                }
        );
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



        jMenuImage= findViewById(R.id.aMenuImage);
        jScrollMenu=findViewById(R.id.aScrollMenuProduct);
        String id= getIntent().getStringExtra("id");
        rootObj=getIntent().getSerializableExtra("jsonArr");

        if(id.equals("0"))
        {
            ID=8;
        }
        else if(id.equals("3"))
        {
            ID=0;
        }
        else if(id.equals("1"))
        {
            ID=1;
        }
        else if(id.equals("6"))
        {
            ID=2;
        }
        else if(id.equals("5"))
        {
            ID=3;
        }
        else if(id.equals("7"))
        {
            ID=4;
        }

        else if(id.equals("9"))
        {
            ID=5;
        }
        else if(id.equals("2"))
        {
            //la cajita
            ID=6;
        }

        else if(id.equals("4"))
        {
            //hops
            ID=7;
        }
        else if(id.equals("0"))
        {
            ID=8;
        }

        displayData((RootObject) rootObj);
        style();




      //  populateUsersList();
      //  Toast.makeText(getApplicationContext(),id, Toast.LENGTH_SHORT).show();
        restCard= findViewById(R.id.aRestCard);
        jRestImage= findViewById(R.id.aRestImage);
        setImages(Integer.parseInt(id));


    }
    public void style()
    {
        ScreenSize s=new ScreenSize();
        int size=(int)s.size(this)*20;
        ScreenSize ratio=new ScreenSize();
        float ration=ratio.ratio(this);
        if(ration>=1.3&&ration<=1.5)
        {
            jMenuImage.getLayoutParams().height= (int) s.size(this)*25;

        }
        else
        {
            jMenuImage.getLayoutParams().height=size;
        }
    }
    public void setClickListners(ListView l, final ArrayList<MenuProducts> newMenu)
    {

        l.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //   Toast.makeText(getApplicationContext(),String.valueOf(position), Toast.LENGTH_SHORT).show();
                        bottomSheet(view,newMenu.get(position).getDescn(),newMenu.get(position).getHometown(),newMenu.get(position).getName());

                    }
                }
        );
    }
    public void bottomSheet(View v, String desc,String price,String name)
    {
        ProductBottomSheet bottomSheet = new ProductBottomSheet();
        Bundle data = new Bundle();//create bundle instance

        data.putString("key_value",desc);//put string to pass with a key value1
        data.putString("name",name);//put string to pass with a key value1
        data.putString("price","$ "+price);//put string to pass with a key value1

        bottomSheet.setArguments(data);//Set bundle data to fragment
        bottomSheet.show(getSupportFragmentManager(), "exampleBottomSheet");
    }

    public void setImages(int id)
    {
        int[] images={R.drawable.restbarnona,R.drawable.restcanoni,R.drawable.restlaca,R.drawable.restfowl,R.drawable.resthops,R.drawable.restgrill,R.drawable.restclaw,R.drawable.restnaughty,R.drawable.restbarxi,R.drawable.restmelts};
        int[] colors={Color.rgb(243,239,228),Color.rgb(197,99,62),Color.rgb(246,213,142),Color.rgb(194,60,49),Color.rgb(63,80,98),Color.rgb(213,204,195),Color.rgb(0,0,0),Color.rgb(104,163,131),Color.rgb(34,30,31),Color.rgb(29,90,108)};

        restCard.setCardBackgroundColor(colors[id]);
        if(id==0)
        {
            jRestImage.setScaleY((float) 0.5);
            jRestImage.setScaleX((float) 0.5);

        }
        jRestImage.setImageResource(images[id]);
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);


            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount()));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    @Override
    public void onButtonClicked(String text) {
        //Toast.makeText(getApplicationContext(),text, Toast.LENGTH_SHORT).show();

    }

    public static ProgressDialog createProgressDialog(Context context) {
        ProgressDialog dialog = new ProgressDialog(context);
        try {
            dialog.show();
        } catch (WindowManager.BadTokenException e) {

        }
        dialog.setCancelable(false);
        dialog.getWindow()
                .setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.progressdialog);
        // dialog.setMessage(Message);
        return dialog;
    }

    public void API()
    {
        Retrofit retro= SinglePlatform.getInstance();
        myApi=retro.create(ISinglePlatform.class);

        fetchData(createProgressDialog(this));


    }
    private void fetchData(final ProgressDialog progressDialog)
    {
        SinglePlatform.getInstance().create(ISinglePlatform.class).getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<RootObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(RootObject rootObject) {

                        displayData(rootObject);
                        progressDialog.dismiss();
                    }


                    @Override
                    public void onError(Throwable e) {

                       addErrorText(jScrollMenu,"No DataWithCard Available.");
                        progressDialog.dismiss();
                    }
                });

    }

    private void displayData(RootObject rootObjects)
    {

        System.out.println("################################3");
        System.out.println(ID);
        System.out.println(rootObjects.data.menus.get(ID));

        for(int i=0;i<rootObjects.data.menus.get(ID).sections.size();i++)
        {
            addSpace(jScrollMenu,20);
            addTitles(jScrollMenu,rootObjects.data.menus.get(ID).sections.get(i).name);
            addSpace(jScrollMenu,20);
            if(!rootObjects.data.menus.get(ID).sections.get(i).description.equals(""))
            {
                addSubTiltes(jScrollMenu,rootObjects.data.menus.get(ID).sections.get(i).description);
            }

           // addSpace(jScrollMenu,10);

            ArrayList<MenuProducts> newMenu=new ArrayList<MenuProducts>();
            for(int ii=0;ii<rootObjects.data.menus.get(ID).sections.get(i).items.size();ii++)
            {

                MenuProducts m=new MenuProducts();


                for(int iii=0;iii<rootObjects.data.menus.get(ID).sections.get(i).items.get(ii).choices.size();iii++)
                {

                    Double number = (Double) rootObjects.data.menus.get(ID).sections.get(i).items.get(ii).choices.get(0).prices.min;
                    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
                    String numberAsString = decimalFormat.format(number);
                    m.setHometown(numberAsString);

                }
                m.setName(rootObjects.data.menus.get(ID).sections.get(i).items.get(ii).name);

                m.setDesc(rootObjects.data.menus.get(ID).sections.get(i).items.get(ii).description);
                newMenu.add(m);



            }

            addLists(jScrollMenu,newMenu);
           addSpace(jScrollMenu,60);
        }
    }

    public void addLists(LinearLayout scrollView,ArrayList<MenuProducts>newMenu)
    {
        ListView l=new ListView(this);
        // Construct the data source
        ArrayList<MenuProducts> array=newMenu;

        // Create the adapter to convert the array to views
        CustomProductAdapter adapter = new CustomProductAdapter(this,array);
        // Attach the adapter to a ListView
        l.setAdapter(adapter);

        scrollView.addView(l);
        setClickListners(l,newMenu);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

       l.setLayoutParams(lp);

        setListViewHeightBasedOnChildren(l);
      //  l.setPadding(0,0,0,20);

    }

    public void addTitles(LinearLayout scrollView,String Text)
    {
       /* LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        lp.setMargins(0, -20, 0, 0);*/
        ScreenSize s=new ScreenSize();
        FontsSet f=new FontsSet();
        TextView textHead=new TextView(this);
        scrollView.addView(textHead);
        textHead.setText(Text);
        textHead.setTextSize((float) (s.size(this)*0.75));
        textHead.setTypeface(f.Medium(this));
        textHead.setPadding(10,0,0,5
        );
        textHead.setTextColor(Color.parseColor("#49505D"));


    }
    public void addSpace(LinearLayout scrollView,int size)
    {
        Space s=new Space(this);

        scrollView.addView(s);
        s.getLayoutParams().height=size;

    }
    public void addSubTiltes(LinearLayout scrollView,String Text)
    {


        ScreenSize s=new ScreenSize();
        FontsSet f=new FontsSet();
        TextView textSub=new TextView(this);
        scrollView.addView(textSub);

        String newText=String.valueOf(Text.charAt(0)).toUpperCase()+Text.substring(1);

        textSub.setText(newText);
        textSub.setTextSize((float) (s.size(this)*0.6));
        textSub.setTypeface(f.Medium(this));
        textSub.setPadding(10,0,15,0);
        textSub.setLineSpacing(0, (float) 1.5);
        textSub.setTextColor(Color.parseColor("#49505D"));

    }

    public void addErrorText(LinearLayout scrollView,String Text)
    {
        ScreenSize s=new ScreenSize();
        FontsSet f=new FontsSet();
        TextView textSub=new TextView(this);
        scrollView.addView(textSub);
        textSub.setText(Text);
        textSub.setTextSize((s.size(this)*1));
        textSub.setTypeface(f.Book(this));
        textSub.setPadding(15,200,15,0);
        textSub.setLineSpacing(0, (float) 1.5);
        textSub.setTextColor(Color.parseColor("#49505D"));
        textSub.setGravity(Gravity.CENTER_HORIZONTAL);

    }

}
