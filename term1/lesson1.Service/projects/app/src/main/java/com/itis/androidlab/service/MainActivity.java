package com.itis.androidlab.service;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.itis.androidlab.service.network.ServiceHelper;
import com.itis.androidlab.service.network.action.ActionGetWeather;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ServiceHelper.getInstance().startActionService(this, new ActionGetWeather());
  }
}
