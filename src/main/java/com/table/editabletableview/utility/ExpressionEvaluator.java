package com.table.editabletableview.utility;

import com.fathzer.soft.javaluator.StaticVariableSet;
import com.table.editabletableview.model.User;
import javafx.scene.control.TableView;
import com.fathzer.soft.javaluator.DoubleEvaluator;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionEvaluator {

    public static Double solveExpr(String expr, TableView<User> table_info) {
        List<String> list = new ArrayList<>();
        String regex = "[A-Z][1-9][0-9]{0,}";
        Pattern pattern_ex = Pattern.compile(regex);
        Matcher matcher_ex = pattern_ex.matcher(expr);
        while (matcher_ex.find()) {
            list.add(matcher_ex.group());
        }
        DoubleEvaluator eval = new DoubleEvaluator();
        StaticVariableSet<Double> variables = new StaticVariableSet<>();
        for(String match: list) {
            variables.set(match, Double.valueOf(table_info.getItems().get(
                    Integer.parseInt(String.valueOf(match.charAt(1))) - 1).getVar(String.valueOf(match.charAt(0)))));
        }
        return eval.evaluate(expr, variables);
    }

    public static String evalForTableView(String stroke, TableView<User> table_info) {
        if (stroke.charAt(0) == '=') {
            System.out.println(stroke.substring(1));
            System.out.println(String.valueOf(solveExpr(stroke.substring(1), table_info)));
            return String.valueOf(solveExpr(stroke.substring(1), table_info));
        }
        else {
            return stroke;
        }
    }

    public static String checkString(String str) {
        if (str.charAt(0) == '=') {
            return str.substring(1);
        }
        return str;
    }
}
