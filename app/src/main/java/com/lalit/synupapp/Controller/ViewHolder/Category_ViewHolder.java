package com.lalit.synupapp.Controller.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lalit.synupapp.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class Category_ViewHolder extends RecyclerView.ViewHolder {


    public TextView txt_price;
    public Button btn_variant_dt;
    public ImageView img_cate_type;
    public CircleImageView circle_image_small;
    public Spinner spinner_type;

    public Category_ViewHolder(@NonNull View itemView) {
        super(itemView);

        txt_price = (TextView) itemView.findViewById(R.id.txt_price);
        btn_variant_dt = (Button) itemView.findViewById(R.id.btn_variant_dt);
        img_cate_type = (ImageView) itemView.findViewById(R.id.img_cate_type);
        circle_image_small = (CircleImageView) itemView.findViewById(R.id.circle_image_small);
        spinner_type = (Spinner) itemView.findViewById(R.id.spinner_type);



    }
}
