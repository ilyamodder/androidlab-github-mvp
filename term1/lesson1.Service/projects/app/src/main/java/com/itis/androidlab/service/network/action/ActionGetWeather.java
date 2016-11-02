package com.itis.androidlab.service.network.action;

import android.os.Parcel;
import com.itis.androidlab.service.models.FullWeatherInfo;
import java.io.IOException;
import retrofit2.Response;

public class ActionGetWeather extends BaseAction<FullWeatherInfo> {

  @Override
  protected Response<FullWeatherInfo> makeRequest() throws IOException {
    return getRest().getTemperatureByCity("Kazan").execute();
  }

  @Override
  protected void onResponseSuccess(Response<FullWeatherInfo> response) {
    // TODO: process success response
  }

  @Override
  protected void onHttpError(Response<?> response) {
    // TODO: process http error response
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
  }

  public ActionGetWeather() {
  }

  protected ActionGetWeather(Parcel in) {
  }

  public static final Creator<ActionGetWeather> CREATOR = new Creator<ActionGetWeather>() {
    @Override
    public ActionGetWeather createFromParcel(Parcel source) {
      return new ActionGetWeather(source);
    }

    @Override
    public ActionGetWeather[] newArray(int size) {
      return new ActionGetWeather[size];
    }
  };
}
