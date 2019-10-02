package es.iessaladillo.pedrojoya.pr01.bmi;

import android.widget.EditText;

import androidx.core.app.ActivityCompat;

/**
 * Allow Bmi calculation and clasification
 */
public class BmiCalculator {

    private EditText weightEdtx;

    /**
     * @param weightInKgs Weight of the person in kgs
     * @param heightInMeters Height of the person in meters
     * @return The body mass index (BMI)
     */
    public float calculateBmi(float weightInKgs, float heightInMeters) {
        // TODO
        float bmi = weightInKgs/(float)Math.pow(heightInMeters, 2);

        return bmi;
    }


    /**
     * @param bmi Body mass index to get clasification from
     * @return A BmiClasification enum with the clasification of BMI
     */
    public BmiClasification getBmiClasification(float bmi) {
        // TODO

        if (bmi<18.5) {
            return BmiClasification.LOW_WEIGHT;
        }
        else if (bmi>=18.5||bmi<25) {
            return BmiClasification.NORMAL_WEIGHT;
        }
        else if (bmi>=25||bmi<30) {
            return BmiClasification.OVERWWEIGHT;
        }
        else if (bmi>=30||bmi<35) {
            return BmiClasification.OBESITY_GRADE_1;
        }
        else if (bmi>=35||bmi<40) {
            return BmiClasification.OBESITY_GRADE_2;
        }
        else {
            return BmiClasification.OBESITY_GRADE_3;
        }
    }

    public enum BmiClasification {
        LOW_WEIGHT, NORMAL_WEIGHT, OVERWWEIGHT, OBESITY_GRADE_1, OBESITY_GRADE_2, OBESITY_GRADE_3
    }
}
