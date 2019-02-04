package android.example.mp1part2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

class Calculator
{
    private String Input[] = {"","","","","","","","","",""};  //fatal error if you don't initialize arrays
    private String operator[] = {"","","","","","","","","",""};
    private String answer = "";
    private int onemore = 0;

    //public String ArrayTest() {return Input[onemore];}
    public void clearInfo()  //function that clears all the arrays and junk
    {
        for(int a = 0; a < 10; a++)
        {
            Input[a] = "";
            operator[a] = "";
            answer = "";
            onemore = 0;
        }
    }
    public void addInput(String IN) {Input[onemore] += IN;}  //add to the current array element
    public void addOperator(String in) {operator[onemore] += in; onemore++;} //adds new operator
    public String giveOutput()  //sets the array values into one big string
    {
        String holder = "";
        for(int i = 0; i <= onemore; i++)
        {
            holder += (Input[i] + " " + operator [i] + " ");
        }
        holder += ("\n" + answer);
        return holder;
    }
    public void runTheNumbers()  //runs when the user hits enter (PEMDAS sensitive)
    {
        double bigHold = Double.parseDouble(Input[0]);

        for(int j = 0; j < onemore; j++)
        {
            double hold = Double.parseDouble(Input[j+1]);
            double hold2 = 0;
            if(onemore > j+1)  {hold2 = Double.parseDouble(Input[j+2]);}
            double isolated = hold * hold2;  //I was using () for my PEMDAS function, but java doesn't do that I guess

            switch (operator[j])
            {
                case "*":
                    bigHold = bigHold * hold;
                    break;
                case "+":
                    if (operator[j+1] != "*") {bigHold = (bigHold + isolated); j++;}
                    else {bigHold += hold;}
                    break;
                case "-":
                    if (operator[j+1] == "*") {bigHold = (bigHold - isolated); j++;}
                    else {bigHold = bigHold - hold;}
                    break;
            }
        }
        answer = (" = " + Double.toString(bigHold));
    }

}

public class MainActivity extends AppCompatActivity {

    Calculator mycalc = new Calculator();
    boolean cleared = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calcClick(View view) {
        TextView mytext = findViewById(R.id.textView);
        if(cleared)  //resets after the use hits enter, but not before they see their answer
        {
            mycalc.clearInfo();
            mytext.setText("Enter a Value");
            cleared = false;
        }

        switch (view.getId()) //all the button values
        {
            case R.id.one:
                mycalc.addInput("1");
                break;
            case R.id.two:
                mycalc.addInput("2");
                break;
            case R.id.three:
                mycalc.addInput("3");
                break;
            case R.id.four:
                mycalc.addInput("4");
                break;
            case R.id.five:
                mycalc.addInput("5");
                break;
            case R.id.six:
                mycalc.addInput("6");
                break;
            case R.id.seven:
                mycalc.addInput("7");
                break;
            case R.id.eight:
                mycalc.addInput("8");
                break;
            case R.id.nine:
                mycalc.addInput("9");
                break;
            case R.id.zero:
                mycalc.addInput("0");
                break;
            case R.id.dot:
                mycalc.addInput(".");
                break;
            case R.id.plus:
                mycalc.addOperator("+");
                break;
            case R.id.sub:
                mycalc.addOperator("-");
                break;
            case R.id.multi:
                mycalc.addOperator("*");
                break;
            case R.id.equals:
                mycalc.runTheNumbers();
                cleared = true;
                break;
            case R.id.clear:
                mycalc.clearInfo();
                mytext.setText("Enter a Value");
                break;
        }
        mytext.setText(mycalc.giveOutput()); //always output what the user just pressed
    }
}
