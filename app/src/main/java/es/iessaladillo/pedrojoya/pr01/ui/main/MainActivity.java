package es.iessaladillo.pedrojoya.pr01.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import es.iessaladillo.pedrojoya.pr01.R;
import es.iessaladillo.pedrojoya.pr01.bmi.BmiCalculator;

public class MainActivity extends AppCompatActivity {

    private EditText txtWeight, txtHeight;
    private Button btnCalculate, btnReset;
    private ImageView imgBmi;
    private BmiCalculator bmiC;
    private TextView lblResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // TODO
        bmiC = new BmiCalculator();
        setupViews();
    }

    // TODO

    private boolean validateWeight() {
        if (txtWeight.getText().toString().equals("")){
            txtWeight.requestFocus();
            txtWeight.setError(getString(R.string.main_invalid_weight));
            return false;
        }
        else if (Float.parseFloat(txtWeight.getText().toString()) > 500) {
            txtWeight.requestFocus();
            txtWeight.setError(getString(R.string.main_invalid_weight));
            return false;
        }
        else if (Float.parseFloat(txtWeight.getText().toString()) <= 0.5) {
            txtWeight.requestFocus();
            txtWeight.setError(getString(R.string.main_invalid_weight));
            return false;
        }
        else {
            return true;
        }
    }

    private boolean validateHeight() {
        if (txtHeight.getText().toString().equals("")){
            txtHeight.setError(getString(R.string.main_invalid_height));
            return false;
        }
        else if (Float.parseFloat(txtHeight.getText().toString()) > 3) {
            txtHeight.setError(getString(R.string.main_invalid_height));
            return false;
        }
        else if (Float.parseFloat(txtHeight.getText().toString()) <= 1) {
            txtHeight.setError(getString(R.string.main_invalid_height));
            return false;
        }
        else {
            return true;
        }
    }

    private void showImageAndText() {

        float bmi;
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        if (validateHeight()&&validateWeight()) {

            bmi = bmiC.calculateBmi(Float.parseFloat(txtWeight.getText().toString()), Float.parseFloat(txtHeight.getText().toString()));

            switch (bmiC.getBmiClasification(bmi)) {
                case LOW_WEIGHT:
                    lblResult.setText(getString(R.string.main_bmi, bmi, getString(R.string.main_low_weight)));
                    imgBmi.setImageResource(R.drawable.underweight);
                    break;
                case NORMAL_WEIGHT:
                    lblResult.setText(getString(R.string.main_bmi, bmi, getString(R.string.main_normal_weight)));
                    imgBmi.setImageResource(R.drawable.normal_weight);
                    break;
                case OVERWWEIGHT:
                    lblResult.setText(getString(R.string.main_bmi, bmi, getString(R.string.main_overweight)));
                    imgBmi.setImageResource(R.drawable.overweight);
                    break;
                case OBESITY_GRADE_1:
                    lblResult.setText(getString(R.string.main_bmi, bmi, getString(R.string.main_obesity_grade_1)));
                    imgBmi.setImageResource(R.drawable.obesity1);
                    break;
                case OBESITY_GRADE_2:
                    lblResult.setText(getString(R.string.main_bmi, bmi, getString(R.string.main_obesity_grade_2)));
                    imgBmi.setImageResource(R.drawable.obesity2);
                    break;
                case OBESITY_GRADE_3:
                    lblResult.setText(getString(R.string.main_bmi, bmi, getString(R.string.main_obesity_grade_3)));
                    imgBmi.setImageResource(R.drawable.obesity3);
                    break;
            }
        }
        inputMethodManager.hideSoftInputFromWindow(txtHeight.getWindowToken(), 0);
    }

    private void resetAll() {
        txtWeight.setText("");
        txtWeight.setError(null);
        txtHeight.setText("");
        txtHeight.setError(null);
        lblResult.setText("");
        imgBmi.setImageResource(R.drawable.bmi);
    }

    private void setupViews() {
        txtWeight = ActivityCompat.requireViewById(this, R.id.txtWeight);
        txtHeight = ActivityCompat.requireViewById(this, R.id.txtHeight);
        btnCalculate = ActivityCompat.requireViewById(this, R.id.btnCalculate);
        btnReset = ActivityCompat.requireViewById(this, R.id.btnReset);
        imgBmi = ActivityCompat.requireViewById(this, R.id.imgBmi);
        lblResult = ActivityCompat.requireViewById(this, R.id.lblResult);

        btnCalculate.setOnClickListener(x -> showImageAndText());
        btnReset.setOnClickListener(x -> resetAll());
    }

}
