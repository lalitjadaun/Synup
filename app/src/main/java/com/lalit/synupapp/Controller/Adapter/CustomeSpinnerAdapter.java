package com.lalit.synupapp.Controller.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lalit.synupapp.Model.Variation;
import com.lalit.synupapp.R;

import java.util.List;

public class CustomeSpinnerAdapter extends BaseAdapter {

    private List<Variation> spinnerlist;
    private Context context;
    private LayoutInflater layoutInflater;


    public CustomeSpinnerAdapter(List<Variation> variations, Context context) {

        this.spinnerlist = variations;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return spinnerlist.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (convertView == null)
            view = layoutInflater.inflate(R.layout.layout_spinner_type, null);
        TextView textView = (TextView) view.findViewById(R.id.spinner_Text);
        textView.setText(spinnerlist.get(position).getName());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = super.getDropDownView(position, convertView, parent);
        LinearLayout l1 = (LinearLayout) view;
        TextView textView = (TextView) l1.findViewById(R.id.spinner_Text);
        textView.setGravity(Gravity.START);
        textView.setTextColor(Color.parseColor("#626262"));
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

//        final int positionss = position;
//        if (Category_Adapter.selectedPosition == positionss) {
//            textView.setTextColor(Color.parseColor("#FFAEADAD"));
//        }
//
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (Category_Adapter.selectedPosition == positionss) {
//
//                } else {
//                    Category_ViewHolder.spinner_type.setSelection(positionss);
//                    hideSpinnerDropDown(Category_ViewHolder.spinner_type);
//                }
//            }
//        });

        return view;

    }
    //9735120312=shakar

//    public static void hideSpinnerDropDown(Spinner spinner) {
//        try {
//            Method method = Spinner.class.getDeclaredMethod("onDetachedFromWindow");
//            method.setAccessible(true);
//            method.invoke(spinner);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
