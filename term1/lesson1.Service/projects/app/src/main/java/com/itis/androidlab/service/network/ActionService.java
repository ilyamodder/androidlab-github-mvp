package com.itis.androidlab.service.network;

import android.app.IntentService;
import android.content.Intent;
import com.itis.androidlab.service.network.action.Action;
import com.itis.androidlab.service.utils.Constants;

public class ActionService extends IntentService {

  private static final String TAG = ActionService.class.getSimpleName();

  public ActionService() {
    super(TAG);
  }

  @Override
  protected void onHandleIntent(Intent intent) {
    Action action = intent.getParcelableExtra(Constants.ACTION_EXTRA);
    action.setContext(getApplicationContext());
    action.execute();
  }
}
