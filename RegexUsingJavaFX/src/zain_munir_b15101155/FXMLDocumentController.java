/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zain_munir_b15101155;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Zain
 */
public class FXMLDocumentController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }

    @FXML
    private TextField inputTextBox;

    @FXML
    private Label inputLabel;

    @FXML
    private Label outputLabel;

    @FXML
    void handleButtonAction(ActionEvent event) {
        
        inputLabel.setText(inputTextBox.getText());
        regexChecker(inputTextBox.getText());
        inputTextBox.clear();
    }
   
    public void regexChecker(String input)
    {
        Pattern regexIden = Pattern.compile("[A-Za-z]([A-Za-z]|[0-9])*|_([A-Za-z]|[0-9])+");
        Matcher checkIden = regexIden.matcher(input);
        boolean isIdentifier = checkIden.matches();
                
        Pattern regexInt = Pattern.compile("[+-]?([0-9])+");
        Matcher checkInt = regexInt.matcher(input);
        boolean isInteger = checkInt.matches();
                
        Pattern regexFloat = Pattern.compile("([+-]?([0-9])*[.]([0-9])+)|([+-]?([0-9])*[.]([0-9])+[eE]([+-]?([0-9])+))");
        Matcher checkFloat = regexFloat.matcher(input);
        boolean isFloat = checkFloat.matches();
                
        Pattern regexChar = Pattern.compile("\'\\\\(n|t|r|0|b|f)\'|\'(.)\'");
        Matcher checkChar = regexChar.matcher(input);
        boolean isChar = checkChar.matches();
        
        Pattern regexString = Pattern.compile("\"(.([^\"\\\\]))*((\\\\[n|t|r|0|b|f]*)?)([A-Za-z0-9])*\"");
        Matcher checkString = regexString.matcher(input);
        boolean isString = checkString.matches();
                
        if(isIdentifier)
        {
            outputLabel.setText("Identifier");
        }
        else if(isInteger)
        {
            outputLabel.setText("Integer");
        }
        else if(isFloat)
        {
            outputLabel.setText("Float");
        }
        else if(isChar)
        {
            outputLabel.setText("Character");
        }
        else if(isString)
        {
            outputLabel.setText("String");
        }
        else
        {
            outputLabel.setText("Invalid");
        }
    }
        
    
}
