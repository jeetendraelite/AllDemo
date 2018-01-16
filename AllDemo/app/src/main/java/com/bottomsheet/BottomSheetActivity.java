package com.bottomsheet;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alldemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BottomSheetActivity extends AppCompatActivity {



    @BindView(R.id.btn1)
    Button button;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);
     //   button= findViewById(R.id.btn1);
        ButterKnife.bind(this);
        //button.setText("HTHT");


       /* button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(BottomSheetActivity.this);
                View view = getLayoutInflater().inflate(R.layout.dialog,null);
                bottomSheetDialog.setContentView(view);
                BottomSheetBehavior bottomSheetBehavior= BottomSheetBehavior.from((View) view.getParent());
                bottomSheetBehavior.setPeekHeight(
                        (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,100,getResources().getDisplayMetrics()));
                bottomSheetDialog.show();

            }
        });*/
    }


    @OnClick(R.id.btn1)
    public void submit(Button btn) {

        // TODO submit data to server...
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(BottomSheetActivity.this);
        View view = getLayoutInflater().inflate(R.layout.dialog,null);
        Button button1 = view.findViewById(R.id.button1);
        Button button2 = view.findViewById(R.id.button2);
        bottomSheetDialog.setContentView(view);
        BottomSheetBehavior bottomSheetBehavior= BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setPeekHeight(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,100,getResources().getDisplayMetrics()));
        bottomSheetDialog.show();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BottomSheetActivity.this, "Button 1 is clicked", Toast.LENGTH_SHORT).show();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BottomSheetActivity.this, "Button 2 is clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
