package com.al.boxipark_visitor.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.al.boxipark_visitor.Other.FontsSet;
import com.al.boxipark_visitor.R;
import com.al.boxipark_visitor.Other.ScreenSize;

public class ProductBottomSheet extends BottomSheetDialogFragment {
    private BottomSheetListener mListener;
    TextView jT1;
    TextView jPr;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       view = inflater.inflate(R.layout.bottom_sheet_layout, container, false);
        //initChargeLayoutViews();


        jT1= view.findViewById(R.id.aMenuItem);
        jPr= view.findViewById(R.id.aPriceBottom);

        String getArgument = getArguments().getString("key_value");//Get pass data with its key value
        String getArgument1 = getArguments().getString("price");//Get pass data with its key value
        String name = getArguments().getString("name");//Get pass data with its key value

        if(getArgument.equals(""))
        {
            jT1.setText(String.valueOf(name));
        }
        else
        {
            String newText=String.valueOf(getArgument.charAt(0)).toUpperCase()+ getArgument.substring(1);
            jT1.setText(newText);
        }

        if(!getArgument1.equals("$ null"))
        {
            jPr.setText(getArgument1);
        }


        return view;
    }

    public void style()
    {
        ScreenSize s=new ScreenSize();
        jT1.setTextSize((s.size(view.getContext())*1));
        jPr.setTextSize((s.size(view.getContext())*1));

        FontsSet f=new FontsSet();
        jT1.setTypeface(f.Bold(view.getContext()));
        jPr.setTypeface(f.Bold(view.getContext()));

    }
    public interface BottomSheetListener {
        void onButtonClicked(String text);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (BottomSheetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement BottomSheetListener");
        }
    }
}

