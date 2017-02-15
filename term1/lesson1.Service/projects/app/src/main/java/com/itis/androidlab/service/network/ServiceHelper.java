package com.itis.androidlab.service.network;

import android.app.Activity;
import android.content.Intent;
import com.itis.androidlab.service.network.action.Action;
import com.itis.androidlab.service.utils.AndroidUtils;
import com.itis.androidlab.service.utils.Constants;

public class ServiceHelper {

  private static volatile ServiceHelper instance;

  public static ServiceHelper getInstance() {
    if (instance == null) {
      synchronized (ServiceHelper.class) {
        if (instance == null) instance = new ServiceHelper();
      }
    }
    return instance;
  }

  private ServiceHelper() {

  }

  public void startActionService(Activity activity, Action action) {
    AndroidUtils.hideSoftKeyboard(activity);
    Intent intent = new Intent(activity, ActionService.class);
    intent.putExtra(Constants.ACTION_EXTRA, action);
    activity.startService(intent);
  }
}
