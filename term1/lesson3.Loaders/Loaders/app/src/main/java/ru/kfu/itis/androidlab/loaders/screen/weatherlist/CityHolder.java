package ru.kfu.itis.androidlab.loaders.screen.weatherlist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.kfu.itis.androidlab.loaders.R;
import ru.kfu.itis.androidlab.loaders.model.City;

public class CityHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.city_name)
    TextView mCityName;

    @BindView(R.id.temperature)
    TextView mTemperature;

    public CityHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(@NonNull City city) {
        mCityName.setText(city.getName());
        if (city.getMain() != null) {
            String temp = mTemperature.getResources().getString(R.string.f_temperature, city.getMain().getTemp());
            mTemperature.setText(temp);
        }
    }
}
