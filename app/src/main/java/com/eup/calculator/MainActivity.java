package com.eup.calculator;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.eup.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private MainViewModel viewModel ;
    private ActivityMainBinding binding;
    private String input="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        initView();
    }

    private void initView() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.tvResult.setText(viewModel.input);
    }


    public void ButtonClick(View view) {
        Button button = (Button) view;
        String data = button.getText().toString();
        switch (data){
            case "C":
                if (viewModel.input.length()>0){
                    String newText = viewModel.input.substring(0,input.length()-1);
                    viewModel.input = newText;
                }
                break;
            case "AC":
                viewModel.input = " ";
                break;
            case "=":
                Solve();
                binding.tvResult.setText(viewModel.input);
                break;
            case "*":
                viewModel.input+="*";
                break;
            default:
                if (viewModel.input==null){
                    viewModel.input="";
                }
                if (data.equals("+") || data.equals("-") || data.equals("/")||data.equals("*")){
                    // in ket quả
                }
                viewModel.input+=data;
        }
        binding.tvResult.setText(viewModel.input);
    }

    public void Solve(){
        if (viewModel.input.split("\\*").length==2){
            String number[] = viewModel.input.split("\\*");
            try {
                double mul = Double.parseDouble(number[0])*Double.parseDouble(number[1]);
                viewModel.input = mul+"";
            } catch (Exception e){

            }
        }
        if (viewModel.input.split("\\/").length==2){
            String number[] = input.split("\\/");
            try {
                double div = Double.parseDouble(number[0])/Double.parseDouble(number[1]);
                viewModel.input = div+"";
            } catch (Exception e){

            }
        }
        if (viewModel.input.split("\\+").length==2){
            String number[] = viewModel.input.split("\\+");
            try {
                double sum = Double.parseDouble(number[0])+Double.parseDouble(number[1]);
                viewModel.input = sum+"";
            } catch (Exception e){

            }
        }

        if (viewModel.input.split("\\-").length==2){
            String number[] = viewModel.input.split("\\-");
            try {
                double sub = Double.parseDouble(number[0])-Double.parseDouble(number[1]);
                viewModel.input = sub+"";
            } catch (Exception e){

            }
        }
    }

    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.white:
                White();
                return true;
            case R.id.night:
                Night();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void White() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    private void Night() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }
}