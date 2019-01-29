package com.example.project_mvvm.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.project_mvvm.models.NicePlace;
import com.example.project_mvvm.repositories.NicePlaceRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<List<NicePlace>> mNicePlaces;
    private NicePlaceRepository mRepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();


    public void init() {
        if (mNicePlaces != null) {
            return;
        }
        mRepo = NicePlaceRepository.getInstance();
        mNicePlaces = mRepo.getNicePlaces();

    }

    public LiveData<List<NicePlace>> getNicePlaces() {
        return mNicePlaces;
    }

    public void addNewValue(final NicePlace nicePlace) {

        List<NicePlace> currentPlaces = mNicePlaces.getValue();
        currentPlaces.add(nicePlace);
        mNicePlaces.postValue(currentPlaces);
        mIsUpdating.postValue(false);


    }


    public LiveData<Boolean> getIsUpdating() {
        return mIsUpdating;
    }


}
