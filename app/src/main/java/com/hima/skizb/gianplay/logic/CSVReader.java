package com.hima.skizb.gianplay.logic;

import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {
    private String TAG = "CVSReader >: ";
    public CSVReader(){}

    public Map<String, String> getContent(InputStream fileName){
        Map<String, String> records = new HashMap<String, String>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.put(values[1], values[0]);
//                records.add(Arrays.asList(values));
            }
            Log.e(TAG, records.size()+"");
            return records;
        }catch (Exception e){
            Log.e(TAG, e.toString());
            return null;
        }
    }


}
