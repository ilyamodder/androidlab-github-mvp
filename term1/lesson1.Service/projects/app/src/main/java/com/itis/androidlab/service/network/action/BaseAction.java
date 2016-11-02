package com.itis.androidlab.service.network.action;

import android.content.Context;
import com.itis.androidlab.service.network.SessionRestManager;
import com.itis.androidlab.service.network.WeatherRest;
import com.orhanobut.logger.Logger;
import java.io.IOException;
import retrofit2.Response;

public abstract class BaseAction<ResponseBody> implements Action {

  protected Context context;

  @Override
  public void setContext(Context context) {
    this.context = context.getApplicationContext();
  }

  protected WeatherRest getRest() {
    return SessionRestManager.getInstance().getRest();
  }

  protected void processError(Response<?> response) {
    if (response.code() == 401) {
      // TODO: process 401 error
    } else {
      onHttpError(response);
    }
  }

  protected void processNetworkError() {
    // TODO: process network error
  }

  protected abstract Response<ResponseBody> makeRequest() throws IOException;

  protected abstract void onResponseSuccess(Response<ResponseBody> response);

  protected abstract void onHttpError(Response<?> response);

  @Override
  public void execute() {
    if (context == null) {
      Logger.e("Context is not set!");
      return;
    }
    Response<ResponseBody> response;
    try {
      response = makeRequest();
    } catch (IOException e) {
      processNetworkError();
      return;
    }
    if (response.isSuccessful()) {
      onResponseSuccess(response);
    } else {
      processError(response);
    }
  }
}
