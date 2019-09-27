package com.lalit.synupapp.Controller.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lalit.synupapp.Controller.ViewHolder.Category_ViewHolder;
import com.lalit.synupapp.Dto.VariantsDto;
import com.lalit.synupapp.Model.ExcludeList;
import com.lalit.synupapp.Model.VariantGroup;
import com.lalit.synupapp.Model.Variation;
import com.lalit.synupapp.R;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Category_Adapter extends RecyclerView.Adapter<Category_ViewHolder> {

    VariantsDto variantsDto;
    Context context;
    private Map<String, String> leaveTypeMap = new HashMap<String, String>();
    String prod_price = null;
    String[][] variationID;

    public Category_Adapter(VariantsDto variantsDto, Context context) {
        this.variantsDto = variantsDto;
        this.context = context;
    }

    @NonNull
    @Override
    public Category_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_category, parent, false);
        return new Category_ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final Category_ViewHolder holder, final int position) {
        final VariantGroup variants = variantsDto.getVariants().getVariantGroups().get(position);
        variationID = new String[variants.getVariations().size()][2];


        Locale locale = new Locale("en", "in");
        final java.text.NumberFormat fmt = java.text.NumberFormat.getCurrencyInstance(locale);
        CustomeSpinnerAdapter spinnerAdapter = new CustomeSpinnerAdapter(variants.getVariations(), context);
        holder.spinner_type.setAdapter(spinnerAdapter);
        holder.spinner_type.setSelected(false);
        holder.spinner_type.setSelection(0, true);
        holder.spinner_type.setDropDownHorizontalOffset(100);

        if (position == 0)
            holder.btn_variant_dt.setText("Crust");
        if (position==1)
            holder.btn_variant_dt.setText("size");
        if (position==2)
            holder.btn_variant_dt.setText("Sauce");
        //holder.btn_variant_dt.setText(variants.getName());

        //Only For Static Images
        if (position == 0) {
            Glide.with(context).load(R.drawable.pizz_crust).into(holder.img_cate_type);
            Glide.with(context).load(R.drawable.pizz_crust).into(holder.circle_image_small);
        } else if (position == 1) {
            Glide.with(context).load(R.drawable.pizza_size).into(holder.img_cate_type);
            Glide.with(context).load(R.drawable.pizza_size).into(holder.circle_image_small);
        } else if (position == 2) {
            Glide.with(context).load(R.drawable.pizza_sause).into(holder.img_cate_type);
            Glide.with(context).load(R.drawable.pizza_sause).into(holder.circle_image_small);
        }

        int i = 0;
        for (Variation leaveMastModel : variants.getVariations()) {
            leaveTypeMap.put(leaveMastModel.getName(), leaveMastModel.getId());
            variationID[i][0] = "";
            variationID[i][1] = "";
            i++;

        }
        // Log.e("variation Array List", "" + variationID);
        holder.spinner_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                if (variants != null && !variantsDto.getVariants().getVariantGroups().isEmpty() && variantsDto.getVariants().getVariantGroups() != null) {
                    variants.setGroupId(variantsDto.getVariants().getVariantGroups().get(holder.getAdapterPosition()).getGroupId());
                    variants.setName(variants.getVariations().get(Integer.valueOf(holder.spinner_type.getSelectedItem().toString())).getId());

                    int sdd = holder.getAdapterPosition();
                    String grp_id = variationID[sdd][0] = variants.getGroupId();
                    String prd_id = variationID[sdd][1] = variants.getName();

                }
                prod_price = fmt.format(Double.valueOf(variants.getVariations().get(Integer.valueOf(holder.spinner_type.getSelectedItem().toString())).getPrice()));
                holder.txt_price.setText(prod_price);

                String result = getValidation(variationID);

                if (result != null && result.length() >= 1) {
                    String[] qurysperation = result.split("/", 3);
                    System.out.println(qurysperation[0]);
                    System.out.println(qurysperation[1]);
                    System.out.println(qurysperation[2]);

                    if (qurysperation[0].equals("1")) {
                        holder.spinner_type.setSelection(0);
                        notifyDataSetChanged();

                        if (qurysperation[1].equals("1") && qurysperation[2].equals("2")) {
                            Toast.makeText(context, "You can not select Cheese burst with Small size pizza", Toast.LENGTH_SHORT).show();
                        } else if (qurysperation[1].equals("2") && qurysperation[2].equals("3")) {
                            Toast.makeText(context, "You can not select  Small size pizza with Mustard sauce", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


    }

    @Override
    public int getItemCount() {
        return variantsDto.getVariants().getVariantGroups().size();
    }

    public String getValidation(String[][] variationIDs) {
        String flags = "";
        List<List<ExcludeList>> camparisonData = variantsDto.getVariants().getExcludeList();


        for (List<ExcludeList> excludeLists : camparisonData) {
            if (getFindData(variationIDs, excludeLists.get(0).getGroupId(), excludeLists.get(0).getVariationId())) {


                if (getFindData(variationIDs, excludeLists.get(1).getGroupId(), excludeLists.get(1).getVariationId())) {


                    Log.e("", "" + variationIDs);
                    flags = 1 + "/" + excludeLists.get(0).getGroupId() + "/" + excludeLists.get(1).getGroupId();
                    break;
                }
            }
        }


        return flags;
    }

    Boolean getFindData(String[][] variationArray, String groupId, String variationID) {

        boolean flag = Boolean.FALSE;
        for (int i = 0; i < variationArray.length; i++) {

            Log.e("", "" + i);
            if (variationArray[i][0].equals(groupId) && variationArray[i][1].equals(variationID)) {
                flag = Boolean.TRUE;
                break;
            }
        }
        return flag;
    }
}
