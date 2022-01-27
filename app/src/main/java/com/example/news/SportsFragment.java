package com.example.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SportsFragment extends Fragment {
    private String apikey = "5ea2477c9947400180671da174f0894f";
    private RecyclerView recyclerviewofsports;
    private String category = "sports";
    ArrayList<ModelClass> modelClassArrayList;
    Adaptor adaptor;
    String country = "in";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sportsfragment, container,false);

        recyclerviewofsports = view.findViewById(R.id.recyclerviewofsports);
        modelClassArrayList = new ArrayList<>();
        recyclerviewofsports.setLayoutManager(new LinearLayoutManager(getContext()));
        adaptor = new Adaptor(getContext(), modelClassArrayList);
        recyclerviewofsports.setAdapter(adaptor);

        findNews();
        return view;

    }

    private void findNews() {
        ApiUtilities.getApiInterface().getcategoryNews(country, category, 100, apikey).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                if (response.isSuccessful()) {
                    modelClassArrayList.addAll(response.body().getArticles());
                    adaptor.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {

            }
        });

    }
}
