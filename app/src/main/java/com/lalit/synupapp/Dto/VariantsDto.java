package com.lalit.synupapp.Dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lalit.synupapp.Model.Variants;

public class VariantsDto {

    @SerializedName("variants")
    @Expose
    private Variants variants;

    public Variants getVariants() {
        return variants;
    }

    public void setVariants(Variants variants) {
        this.variants = variants;
    }
}
