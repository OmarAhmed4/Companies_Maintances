package com.example.companies_maintances;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class Main_Activity_Mvc implements OnClickedInterface {

    private View  mView;
    private Context mContext;
    List<OnClickedInterface>listeners=new ArrayList<>();


    public Main_Activity_Mvc(LayoutInflater layoutInflater, ViewGroup parent ) {


     mView = layoutInflater.inflate(R.layout.activity_main,parent,false);
    Button button=findViewById(R.id.map_Button);
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onClickedMethod();
        }
    });

    }

    public void registerListener(OnClickedInterface onClickedInterface){
        listeners.add(onClickedInterface);

    }
    public void unRegisterListener(OnClickedInterface onClickedInterface){
        listeners.remove(onClickedInterface);

    }

    @Override
    public void onClickedMethod() {
        for (int i=0;i<listeners.size();i++)
        {
            if(listeners.get(i) instanceof MainActivity)
            listeners.get(i).onClickedMethod();
        }
    }

    public View getRootView()
    {
        return mView;
    }

    public <T extends View>   T  findViewById(int id )
    {

        return getRootView().findViewById(id);
    }

    public Context getContext()

    {
     return getRootView().getContext();
    }



}
