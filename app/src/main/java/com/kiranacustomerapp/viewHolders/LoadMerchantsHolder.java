package com.kiranacustomerapp.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kiranacustomerapp.R;

/**
 * Created by Siddhi on 11/26/2016.
 */
public class LoadMerchantsHolder  extends RecyclerView.ViewHolder{

    public ImageView img_mer_avatar,imgFavEmpty,imgFavFill;
    public TextView tv_kiarana_name,tv_address;
    public LinearLayout lay_row;
    public RelativeLayout layoutFav;


    public LoadMerchantsHolder(View v) {
        super(v);
        tv_kiarana_name = (TextView) v.findViewById(R.id.tv_kirana_name);
        tv_address = (TextView) v.findViewById(R.id.textViewAddress);
        lay_row = (LinearLayout) v.findViewById(R.id.lay_row);
        layoutFav = (RelativeLayout) v.findViewById(R.id.layoutFav);
        imgFavEmpty = (ImageView) v.findViewById(R.id.imageViewEmpty);
        imgFavFill = (ImageView) v.findViewById(R.id.imageViewFill);
        //img_cust_avatar = (ImageView) v.findViewById(R.id.img_cust_avatar);


    }

}
