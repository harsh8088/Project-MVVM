package com.example.project_mvvm.repositories;

import android.arch.lifecycle.MutableLiveData;

import com.example.project_mvvm.models.NicePlace;

import java.util.ArrayList;
import java.util.List;

public class NicePlaceRepository {
    private static NicePlaceRepository instance;
    private ArrayList<NicePlace> dataset = new ArrayList<>();

    public static NicePlaceRepository getInstance() {
        if (instance == null) {
            instance = new NicePlaceRepository();
        }
        return instance;
    }


    public MutableLiveData<List<NicePlace>> getNicePlaces() {
        setNicePlaces();
        MutableLiveData<List<NicePlace>> data = new MutableLiveData<>();
        data.setValue(dataset);
        return data;
    }

    private void setNicePlaces() {
//        dataset.add(new NicePlace(Fakeit.name().name(), ""));
//        dataset.add(new NicePlace(Fakeit.name().name(), ""));
//        dataset.add(new NicePlace(Fakeit.name().name(), ""));
//        dataset.add(new NicePlace(Fakeit.name().name(), ""));

    }
}
