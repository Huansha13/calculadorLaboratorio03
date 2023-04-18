package xyz.android.guialaborario03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText num1, num2;
    Button btnCalcular;
    TextView textResultado;
    RadioGroup radioGroupOperacion;

    //
    //RadioButton rbtSumar, rbtRestar, rbtMultiplicar, rbtDividir;
    //

    CheckBox checkBoxRedondear;
    double resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = findViewById(R.id.editTextNum1);
        num2 = findViewById(R.id.editTextNum2);
        textResultado = findViewById(R.id.textViewResultado);
        btnCalcular = findViewById(R.id.buttonCalcular);
        radioGroupOperacion = findViewById(R.id.radioGroupOperacion);
        checkBoxRedondear = findViewById(R.id.checkBoxRedondear);
        checkBoxRedondear.setEnabled(false);

        //
        /*rbtSumar = findViewById(R.id.radioButtonSumar);
        rbtRestar = findViewById(R.id.radioButtonRestar);
        rbtMultiplicar = findViewById(R.id.radioButtonMultiplicar);
        rbtDividir = findViewById(R.id.radioButtonDividir);*/

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBoxRedondear.setChecked(false);

                String valor1 = num1.getText().toString();
                String valor2 = num2.getText().toString();

                if (valor1.isEmpty() || valor2.isEmpty()) {
                    textResultado.setText("Complete los campos");
                    return;
                }

                double n1 = Double.parseDouble(valor1);
                double n2 = Double.parseDouble(valor2);

                switch (radioGroupOperacion.getCheckedRadioButtonId()) {
                    case R.id.radioButtonSumar:
                        resultado = n1 + n2;
                        break;
                    case R.id.radioButtonRestar:
                        resultado = n1 - n2;
                        break;
                    case R.id.radioButtonMultiplicar:
                        resultado = n1 * n2;
                        break;
                    case R.id.radioButtonDividir:
                        resultado = n1 / n2;
                        break;
                    default:
                        textResultado.setText("No ha seleccionado una opción");
                        return;
                }

                /*if (rbtSumar.isChecked()) {
                    resultado =  n1 + n2;
                } else if (rbtRestar.isChecked()) {
                    resultado = n1 - n2;
                } else if (rbtMultiplicar.isChecked()) {
                    resultado = n1 * n2;
                } else if (rbtDividir.isChecked()) {
                    resultado = n1 / n2;
                } else {
                    textResultado.setText("No ha seleccionado una opción");
                    return;
                }*/

                textResultado.setText(getResultado(resultado));
                checkBoxRedondear.setEnabled(true);
            }
        });

    }

    public void redondearResultado(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        if (view.getId() == R.id.checkBoxRedondear) {
            if (checked) {
                textResultado.setText(getResultado(Math.round(resultado)));
                return;
            }

            textResultado.setText(getResultado(resultado));
        }
    }

    private String getResultado(Object resultado) {
        final String TXT_RESULTADO = "El resultado es: %s";
        return String.format(TXT_RESULTADO, resultado);
    }
}