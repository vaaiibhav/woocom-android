package com.vectorcoder.androidwoocommerce.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vectorcoder.androidwoocommerce.R;

/**
 * Created by Muhammad Nabeel on 21/02/2019.
 */
public class PushNotification extends Fragment {
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        
        View rootView = inflater.inflate(R.layout.f_products_horizontal, container, false);
        
        return rootView;
    }
    
    private void initUI(View v){
    
    
    }
}
