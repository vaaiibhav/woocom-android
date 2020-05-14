package com.vectorcoder.androidwoocommerce.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.common.api.Api;
import com.vectorcoder.androidwoocommerce.R;
import com.vectorcoder.androidwoocommerce.activities.MainActivity;
import com.vectorcoder.androidwoocommerce.adapters.OrdersListAdapter;
import com.vectorcoder.androidwoocommerce.adapters.PointsAdapter;
import com.vectorcoder.androidwoocommerce.app.App;
import com.vectorcoder.androidwoocommerce.constant.ConstantValues;
import com.vectorcoder.androidwoocommerce.customs.DialogLoader;
import com.vectorcoder.androidwoocommerce.models.points.PointsList;
import com.vectorcoder.androidwoocommerce.models.points.PointsModel;
import com.vectorcoder.androidwoocommerce.network.APIClient;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Muhammad Nabeel on 01/03/2019.
 */
public class Points_Fragment extends Fragment {
    
    TextView totalPoints,emptyRecord;
    RecyclerView total_point_list;
    String customerID;
    
    List<PointsList> pointsLists;
    DialogLoader dialogLoader;
    PointsAdapter pointsListAdapter;
    int totalPointEarned;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       
        View v = inflater.inflate(R.layout.points_layout, container, false);
        MainActivity.actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.actionRewards));
    
    
        // Get the CustomerID from SharedPreferences
        customerID = this.getContext().getSharedPreferences("UserInfo", getContext().MODE_PRIVATE).getString("userID", "");
        
        initUI(v);
    
        return v;
    }
    
    private void initUI(View v){
        totalPoints = v.findViewById(R.id.totalPoints);
        total_point_list = v.findViewById(R.id.total_point_list);
        emptyRecord = v.findViewById(R.id.empty_record);
    
    
        // Hide some of the Views
        emptyRecord.setVisibility(View.GONE);
        dialogLoader = new DialogLoader(getContext());
    
    
        int size = ((App) getContext().getApplicationContext()).getPointsList().size();
        // Request for User's Orders
        if (!ConstantValues.IS_GUEST_LOGGED_IN) {
           pointsLists = ((App) getContext().getApplicationContext()).getPointsList();
           int size2 = pointsLists.size();
           pointsListAdapter = new PointsAdapter(getContext(), pointsLists);
    
           totalPoints.setText(""+getTotalPoint());
            // Set the Adapter and LayoutManager to the RecyclerView
            total_point_list.setAdapter(pointsListAdapter);
            total_point_list.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        }
        else {
            emptyRecord.setVisibility(View.VISIBLE);
        }
        
      //  int size = pointsLists.size();
      
    }
    
    
    // Request for all points List
    private void RequestPointList(){
        dialogLoader.showProgressDialog();
        Map<String, String> params = new LinkedHashMap<>();
        params.put("user_id", String.valueOf(customerID));
        params.put("insecure", "cool");
       
        Call<PointsModel> call = APIClient.getInstance().getPoints(params);
        
        call.enqueue(new Callback<PointsModel>() {
            @Override
            public void onResponse(Call<PointsModel> call, Response<PointsModel> response) {
                
                if(response.isSuccessful()){
                    pointsLists.addAll(response.body().getData());
                    dialogLoader.hideProgressDialog();
                    pointsListAdapter = new PointsAdapter(getContext(), pointsLists);
    
                    totalPoints.setText(""+getTotalPoint());
                    // Set the Adapter and LayoutManager to the RecyclerView
                    total_point_list.setAdapter(pointsListAdapter);
                    total_point_list.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    
                }
                
                else {
                    Snackbar.make(getView(),response.message(),Snackbar.LENGTH_SHORT).show();
                    dialogLoader.hideProgressDialog();
                }
        
            }
    
            @Override
            public void onFailure(Call<PointsModel> call, Throwable t) {
                Snackbar.make(getView(),t.toString(),Snackbar.LENGTH_SHORT).show();
                dialogLoader.hideProgressDialog();
            }
        });
        
        
    }
    
    private int getTotalPoint(){
        for (int i=0;i<pointsLists.size();i++){
            int pointValue = Integer.parseInt(pointsLists.get(i).getPoints());
            totalPointEarned += pointValue;
        }
        
        return totalPointEarned;
    }
}
