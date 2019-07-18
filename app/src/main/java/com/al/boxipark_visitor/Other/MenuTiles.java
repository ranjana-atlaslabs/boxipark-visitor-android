package com.al.boxipark_visitor.Other;

import android.graphics.Color;
import com.al.boxipark_visitor.R;

public class MenuTiles {

    int[] colors={Color.rgb(243,239,228),Color.rgb(197,99,62),Color.rgb(246,213,142),Color.rgb(194,60,49),Color.rgb(63,80,98),Color.rgb(213,204,195),Color.rgb(0,0,0),Color.rgb(104,163,131),Color.rgb(34,30,31),Color.rgb(29,90,108)};
    int[] images={R.drawable.barnonalogon,R.drawable.canolilogon,R.drawable.lacajitalogon,R.drawable.fowllogon,R.drawable.hopslogon,R.drawable.grilllogon,R.drawable.clawlogon,R.drawable.naughtylogon,R.drawable.barxilogon,R.drawable.beforelogon};

    public int returnCol(int i)
    {
        return colors[i];
    }
    public int returnImg(int i)
    {
        return images[i];
    }


}
