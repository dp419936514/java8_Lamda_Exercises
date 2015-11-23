package derek.exercises;

import org.junit.Test;

import javax.swing.text.DateFormatter;
import java.awt.event.ActionListener;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class Chapter_2 {
    enum Operator {
        ADD, SUB, MUL, DIV;
    }

    ;

    public static void main(String[] args) {
    }

    @Test
    public void VariantOfLambda() {

        //with no argument
        Runnable noArgument = () -> System.out.println("This is a lambda expression with no argument");

        //with one argument
        ActionListener listener = event -> {
            System.out.println("This is a lambda expression with one Type-undeclared argument");
        };

        //with multi arguments
        BinaryOperator<Long> add = (x, y) -> x + y;
        BinaryOperator<Long> add1 = (x, y) -> {
            return x + y;
        };

        //with multi Type-declared arguments;
        BinaryOperator<Long> add2 = (Long x, Long y) -> x + y;
    }

    public void VariantOfLambda_exercise() {


        //TODO create a method as calculator by implementing Function interface;
        Function<Operator, Double> calculate = (Operator operator) -> {
            Double result = 0.0;
            switch (operator) {
                case ADD:
                    break;
                case SUB:
                    break;
                case MUL:
                    break;
                case DIV:
                    break;
                default:
                    System.err.println("wrong operator !");
                    break;
            }
            return result;
        };

        //Find ThreadLocal 的Factory方法
        ThreadLocal suppliedThreadLocal = null;

    }
    //TODO create a thread-safe DateFormatter object using constructor
    /**
     * ThreadLocal 类是一个多线程的工具类，它定义了一个属于线程的局部变量，并为每个线程都保存了一份副本。JDK1.5中该方法启用了泛型。
     */

    //这里必须显式地指定重写initialValue方法，initialValue方法要求返回ThreadLocal泛型所指定的对象。
    private static final ThreadLocal<DateFormatter> dateFormatterThreadLocal = ThreadLocal.withInitial(
            () -> {return new DateFormatter();});


}
