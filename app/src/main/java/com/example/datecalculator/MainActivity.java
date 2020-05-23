package com.example.datecalculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.datecalculator.databinding.ActivityMainBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void buttonClick(View view) {
        try {
//          날짜 입력 형식은 SimpleDateFormat("yyyy-MM-dd") 에서
//          정의한 "2020-5-13" 형식으로 입력해야함.
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

//          버튼이 클릭되면 제일 먼저 EditText 에 입력된 날짜를 문자열에 저장합니다.
            String startString = binding.onDate.getText().toString();
            String endString = binding.offDate.getText().toString();

//          문자열로 받은 날짜를 Date 형식으로 변환합니다.
            Date beginDate = formatter.parse(startString);
            Date endDate = formatter.parse(endString);


//          첫번째 TextView(id=onDays)에 두번째 EditText에 입력한 날짜에서
//          첫번째 EditText에 입력한 날짜를 뺀 일(218) 수를 나타나게 하기.

            long diff = endDate.getTime() - beginDate.getTime();
            long diffDays = diff / (24 * 60 * 60 * 1000);
            Log.e("몇일 차이", diffDays + "일");

            binding.onDays.setText(diffDays + "일");

            // 두번째 TextView(id=onDays2)에는 두 날짜 차이 218 에 0.45를 곱한 값(218*0.45=98.1)을 무조건 정수 올림하여 (99)
            // 두번째 EditText에 입력한 날짜에 더해서 날짜(2021-03-16일까지)로 나타나게 하고 싶습니다.

            // 계산된 날짜에 0.45 곱하기
            long result_045 = (long) (diffDays * 0.45);
            // 두번째 날짜에 0.45 계산된 숫자을 1일로 변환하여 더하기
            long result_time = endDate.getTime() + result_045 * (1000 * 60 * 60 * 24);
            String result_date = formatter.format(new Date(result_time));

            binding.onDays2.setText(result_date + " (" + result_045 + ")");

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}


//             년 몇개월 몇일 구하기 (개월마다 다른 일수로 1~2일 오차 있음)
//            long diffYear = (long) (diffDays / 365);
//            long diffMonth = (long) (diffDays % 365 / 30.417);
//            long diffDay = (long) (diffDays % 30.417);
//            Log.e("몇년 몇개월 몇일 차이", diffYear +"년 "+ diffMonth + "개월 "+ diffDay + "일");

//            몇개월 몇일 구하기 (개월마다 다른 일수로 1~2일 오차 있음)
//            diffMonth = (long) (diffDays / 30.417);
//            diffDay = (long) (diffDays % 30.417);
//            Log.e("몇개월 몇일 차이", diffMonth + "개월 "+ diffDay + "일");

