package com.project.diana.antenatalandpostnatalcare.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.project.diana.antenatalandpostnatalcare.Prevalent;
import com.project.diana.antenatalandpostnatalcare.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import im.dacer.androidcharts.LineView;

/**
 * Created by Kiduyu klaus
 * on 10/11/2020 02:04 2020
 */
public class ReportFragment extends Fragment {
    int randomint = 36;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_report, container, false);


        final LineView lineView = (LineView) view.findViewById(R.id.line_view_float);

        initLineView(lineView);


        randomSet(lineView);

        return view;
    }
    private void initLineView(LineView lineView) {
        ArrayList<String> test = new ArrayList<String>();
        for (int i = 0; i < 36; i++) {
            test.add(String.valueOf(i + 1));
        }
        lineView.setBottomTextList(test);
        lineView.setColorArray(new int[] {
                Color.parseColor("#F44336"), Color.parseColor("#9C27B0"),
                Color.parseColor("#2196F3"), Color.parseColor("#009688")
        });
        lineView.setDrawDotLine(true);
        lineView.setShowPopup(LineView.SHOW_POPUPS_NONE);
    }

    private void randomSet(LineView lineView) {
        ArrayList<Integer> dataList = new ArrayList<>();
        for (int number : generateIncreasingRandoms(36, 35)) {
            dataList.add(number);
        }

        ArrayList<Integer> dataList2 = new ArrayList<>();
        for (int number : generateIncreasingRandoms(36, 40)) {
            dataList2.add(number);
        }
        ArrayList<Integer> dataListxv = new ArrayList<>();
        for (int number : generateIncreasingRandoms(36, 50)) {
            dataListxv.add(number);
        }

        ArrayList<Integer> dataList3 = new ArrayList<>();
        for (int number : generateIncreasingRandoms(36, 26)) {
            dataList3.add(number);
        }

        ArrayList<ArrayList<Integer>> dataLists = new ArrayList<>();
        dataLists.add(dataList);
        dataLists.add(dataList2);
        dataLists.add(dataList3);
        dataLists.add(dataListxv);

        lineView.setDataList(dataLists);



    }
    public static int[] generateIncreasingRandoms(int amount, int max) {
        int[] randomNumbers = new int[amount];
        Random random = new Random();
        for (int i = 0; i < randomNumbers.length; i++) {
            randomNumbers[i] = random.nextInt(max);
        }
        Arrays.sort(randomNumbers);
        return randomNumbers;
    }

}
