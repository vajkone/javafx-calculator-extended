package calculator;

import calculator.model.Calculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;


public class CalculatorController {

    @FXML
    private TextField display;

    private Calculator calculator;
    private boolean startNumber = true;
    private double number1;
    private String operator = "";



    @FXML
    private void initialize() {
        calculator = new Calculator();
    }

    @FXML
    public void processDigit(ActionEvent event) {
        String digitPressed = ((Button) event.getSource()).getText();
        System.out.println(digitPressed);
        if (startNumber || display.getText().equals("0")) {
            display.setText(digitPressed);
        } else {
            display.setText(display.getText() + digitPressed);
        }
        startNumber = false;
    }

    @FXML
    public void clearEverything(){
        display.setText("0");
    }

    @FXML
    public void makeFraction(){ display.setText(display.getText() + "."); }

    @FXML
    public void changeSign(){

        if (display.getText().startsWith("-")){
            display.setText(StringUtils.remove(display.getText(),'-'));
        }else{
            display.setText("-"+display.getText());
        }

    }




    @FXML
    public void processOperator(ActionEvent event) {
        String operatorPressed = ((Button) event.getSource()).getText();
        System.out.println(operatorPressed);
        if (operatorPressed.equals("=")) {
           if (operator.isEmpty()) {
               return;
           }
           double number2 = Double.parseDouble(display.getText());
           double result = calculator.calculate(number1, number2, operator);
           if (result%1==0){
               display.setText(String.format("%.0f", result));
           }else{
               String format = StringUtils.strip(String.format("%.10f", result),"0").replace(',','.');
               if (format.startsWith(".")){
                   display.setText("0"+format);
               }else{
                   display.setText(format);
               }
           }

           operator = "";
            System.out.println(result);
        } else {
            if (! operator.isEmpty()) {
                return;
            }
            number1 = Double.parseDouble(display.getText());
            operator = operatorPressed;
        }
        startNumber=true;
    }

}
