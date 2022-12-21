import CustomExceptions.DivideZero;
import CustomExceptions.Overflow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class GuiDesign {
    private JButton clear1;
    private JButton neg1;
    private JButton percent1;
    private JButton div1;
    private JButton seven1;
    private JButton eight1;
    private JButton nine1;
    private JButton mul1;
    private JButton four1;
    private JButton five1;
    private JButton six1;
    private JButton sub1;
    private JButton one1;
    private JButton two1;
    private JButton three1;
    private JButton add1;
    private JButton zero1;
    private JButton dot1;
    private JButton equal1;
    private JButton left;
    private JButton right;
    private JButton del;
    private JButton clear2;
    private JButton neg2;
    private JButton percent2;
    private JButton div2;
    private JButton seven2;
    private JButton eight2;
    private JButton nine2;
    private JButton mul2;
    private JButton four2;
    private JButton five2;
    private JButton six2;
    private JButton sub2;
    private JButton one2;
    private JButton two2;
    private JButton three2;
    private JButton add2;
    private JButton zero2;
    private JButton dot2;
    private JButton equal2;
    private JLabel expression1;
    private JLabel expression2;
    private JLabel display1;
    private JLabel display2;
    protected JPanel mainPanel;
    private JPanel Left;
    private JPanel Right;
    private JPanel Middle;
    private JPanel LeftBottons;
    private JPanel RightBottons;
    private JPanel LeftDisplay;
    private JPanel RightDisplay;
    private JButton sound;

    public enum Last {
        number, operator, equal
    }

    private Last last1 = Last.number;
    private Last last2 = Last.number;


    private String token1 = "0";
    private String token2 = "0";
    private String lastCompleteExpression1 = "";
    private String lastCompleteExpression2 = "";

    private final String DIVIDE_ZERO = "DivideZero";
    private final String OVERFLOW = "Overflow";


    SoundEffect soundEffect = new SoundEffect();
    private boolean sound_enabled = false;




    private Boolean isNotOverflow(String token) {
        String[] splits;

        // 不计入负号
        if (token.startsWith("-"))
            token = token.substring(1);

        // 如果是小数
        if (token.contains(".")) {
            // 使用正则表达式去掉整数部分
            splits = token.split(".*\\.");
            if (token.endsWith("."))
                return true;
            else
                return splits[1].length() < 9;
        } else    // 整数直接返回长度
            return token.length() < 9;

    }

    private void numberListener(String left_or_right, String num) {
        soundEffect.da(sound_enabled);

        if (left_or_right.equals("left")) {
            if (last1 == Last.equal)
                token1 = "0";

            if (isNotOverflow(token1)) {
                if (token1.equals("0") || last1 == Last.operator)
                    token1 = num;
                else
                    token1 = token1 + num;
            }
            // 更改控件display
            display1.setText(token1);
            // 更改控件expression
            expression1.setText(lastCompleteExpression1 + token1);


            // 更新flags和AC
            clear1.setText("C");
            last1 = Last.number;

        } else if (left_or_right.equals("right")) {
            if (last2 == Last.equal)
                token2 = "0";

            if (isNotOverflow(token2)) {
                if (token2.equals("0") || last2 == Last.operator)
                    token2 = num;
                else
                    token2 = token2 + num;
            }
            // 更改控件display
            display2.setText(token2);
            // 更改控件expression
            expression2.setText(lastCompleteExpression2 + token2);


            // 更新flags和AC
            clear2.setText("C");
            last2 = Last.number;
        }
    }

    private void operatorListener(String left_or_right, String op) {
        soundEffect.da(sound_enabled);

        if (left_or_right.equals("left")) {

            if (token1.equals(DIVIDE_ZERO))
                return;

            if (last1 == Last.number) {
                lastCompleteExpression1 = lastCompleteExpression1 + token1;
                token1 = "0";
                lastCompleteExpression1 = lastCompleteExpression1 + op;
                expression1.setText(lastCompleteExpression1);
            } else if (last1 == Last.operator) {
                lastCompleteExpression1 = lastCompleteExpression1.substring(0, lastCompleteExpression1.length() - 1);
                lastCompleteExpression1 = lastCompleteExpression1 + op;
                expression1.setText(lastCompleteExpression1);
            } else if (last1 == Last.equal) {
                lastCompleteExpression1 = token1 + op;
                token1 = "0";
                expression1.setText(lastCompleteExpression1);
            }
            last1 = Last.operator;

        } else if (left_or_right.equals("right")) {

            if (token2.equals(DIVIDE_ZERO))
                return;

            if (last2 == Last.number) {
                lastCompleteExpression2 = lastCompleteExpression2 + token2;
                token2 = "0";
                lastCompleteExpression2 = lastCompleteExpression2 + op;
                expression2.setText(lastCompleteExpression2);
            } else if (last2 == Last.operator) {
                lastCompleteExpression2 = lastCompleteExpression2.substring(0, lastCompleteExpression2.length() - 1);
                lastCompleteExpression2 = lastCompleteExpression2 + op;
                expression2.setText(lastCompleteExpression2);
            } else if (last2 == Last.equal) {
                lastCompleteExpression2 = token2 + op;
                token2 = "0";
                expression2.setText(lastCompleteExpression2);
            }
            last2 = Last.operator;
        }
    }

    public GuiDesign() {

        // 左侧
        zero1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberListener("left", "0");
            }
        });

        one1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberListener("left", "1");
            }
        });

        two1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberListener("left", "2");
            }
        });

        three1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberListener("left", "3");
            }
        });

        four1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberListener("left", "4");
            }
        });

        five1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberListener("left", "5");
            }
        });

        six1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberListener("left", "6");
            }
        });

        seven1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberListener("left", "7");
            }
        });

        eight1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberListener("left", "8");
            }
        });

        nine1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberListener("left", "9");
            }
        });


        add1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operatorListener("left", "+");
            }
        });

        sub1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operatorListener("left", "-");
            }
        });

        mul1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operatorListener("left", "×");
            }
        });

        div1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operatorListener("left", "÷");
            }
        });

        clear1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soundEffect.da(sound_enabled);

                if (Objects.equals(clear1.getText(), "C")) {
                    if (last1 == Last.number)
                        lastCompleteExpression1 = lastCompleteExpression1 + token1;
                    lastCompleteExpression1 = lastCompleteExpression1.substring(0, lastCompleteExpression1.length() - token1.length());
                    expression1.setText(lastCompleteExpression1);
                    token1 = "0";
                    display1.setText(token1);
                } else if (Objects.equals(clear1.getText(), "AC")) {
                    token1 = "0";
                    display1.setText(token1);
                    lastCompleteExpression1 = "";
                    expression1.setText(lastCompleteExpression1);
                }
                clear1.setText("AC");
                last1 = Last.number;
            }
        });

        neg1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soundEffect.da(sound_enabled);

                if (token1.equals(DIVIDE_ZERO))
                    return;

                // 没有负0
                if (Objects.equals(token1, "0"))
                    return;

                // 有数字输入才启用
                if (last1 == Last.number || last1 == Last.equal) {
                    // display
                    if (token1.startsWith("-"))
                        token1 = token1.substring(1);
                    else
                        token1 = "-" + token1;
                    display1.setText(token1);
                    // expression
                    expression1.setText(lastCompleteExpression1 + token1);
                }
                last1 = Last.number;
            }
        });

        percent1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soundEffect.da(sound_enabled);

                if (token1.equals(DIVIDE_ZERO))
                    return;

                if (last1 == Last.number || last1 == Last.equal) {
                    BigDecimal num = new BigDecimal(String.valueOf(token1));
                    BigDecimal hundred = new BigDecimal(100);
                    num = num.divide(hundred, 9, RoundingMode.HALF_UP);
                    // display
                    token1 = num.stripTrailingZeros().toPlainString();
                    display1.setText(token1);
                    // expression
                    expression1.setText(lastCompleteExpression1 + token1);
                }
                last1 = Last.number;
            }
        });

        dot1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soundEffect.da(sound_enabled);

                // 如果建议存在小数点，失效
                if (token1.contains("."))
                    return;

                // 如果上一个输入的是运算符，说明是新输入的数字是直接以.开头的形式
                if (last1 == Last.operator)
                    token1 = "0.";
                else if (last1 == Last.number)
                    token1 = token1 + ".";
                display1.setText(token1);
                expression1.setText(lastCompleteExpression1 + token1);

                // 更新flags和AC
                clear1.setText("C");
                last1 = Last.number;
            }
        });

        equal1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soundEffect.ding(sound_enabled);

                if (last1 == Last.equal) {
                    return;
                }

                if (last1 == Last.number)
                    lastCompleteExpression1 = lastCompleteExpression1 + token1;

                Expression expression = new Expression(lastCompleteExpression1);
                try {
                    token1 = expression.get_eval();
                } catch (DivideZero E) {
                    token1 = DIVIDE_ZERO;
                } catch (Overflow E) {
                    token1 = OVERFLOW;
                }

                display1.setText(token1);

                expression1.setText(lastCompleteExpression1 + " = " + token1);
                lastCompleteExpression1 = "";

                clear1.setText("AC");
                last1 = Last.equal;
            }

        });

        // 右侧
        zero2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberListener("right", "0");
            }
        });

        one2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberListener("right", "1");
            }
        });

        two2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberListener("right", "2");
            }
        });

        three2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberListener("right", "3");
            }
        });

        four2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberListener("right", "4");
            }
        });

        five2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberListener("right", "5");
            }
        });

        six2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberListener("right", "6");
            }
        });

        seven2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberListener("right", "7");
            }
        });

        eight2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberListener("right", "8");
            }
        });

        nine2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberListener("right", "9");
            }
        });

        add2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operatorListener("right", "+");
            }
        });

        sub2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operatorListener("right", "-");
            }
        });

        mul2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operatorListener("right", "×");
            }
        });

        div2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operatorListener("right", "÷");
            }
        });

        clear2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soundEffect.da(sound_enabled);

                if (Objects.equals(clear2.getText(), "C")) {
                    if (last2 == Last.number)
                        lastCompleteExpression2 = lastCompleteExpression2 + token2;
                    lastCompleteExpression2 = lastCompleteExpression2.substring(0, lastCompleteExpression2.length() - token2.length());
                    expression2.setText(lastCompleteExpression2);
                    token2 = "0";
                    display2.setText(token2);
                } else if (Objects.equals(clear2.getText(), "AC")) {
                    token2 = "0";
                    display2.setText(token2);
                    lastCompleteExpression2 = "";
                    expression2.setText(lastCompleteExpression2);
                }
                clear2.setText("AC");
                last2 = Last.number;
            }
        });

        neg2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soundEffect.da(sound_enabled);

                if (token2.equals(DIVIDE_ZERO))
                    return;

                // 没有负0
                if (Objects.equals(token2, "0"))
                    return;

                // 有数字输入才启用
                if (last2 == Last.number || last2 == Last.equal) {
                    // display
                    if (token2.startsWith("-"))
                        token2 = token2.substring(1);
                    else
                        token2 = "-" + token2;
                    display2.setText(token2);
                    // expression
                    expression2.setText(lastCompleteExpression2 + token2);
                }
                last2 = Last.number;
            }
        });

        percent2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soundEffect.da(sound_enabled);

                if (token2.equals(DIVIDE_ZERO))
                    return;

                if (last2 == Last.number || last2 == Last.equal) {
                    BigDecimal num = new BigDecimal(String.valueOf(token2));
                    BigDecimal hundred = new BigDecimal(100);
                    num = num.divide(hundred, 9, RoundingMode.HALF_UP);
                    // display
                    token2 = num.stripTrailingZeros().toPlainString();
                    display2.setText(token2);
                    // expression
                    expression2.setText(lastCompleteExpression2 + token2);
                }
                last2 = Last.number;
            }
        });

        dot2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soundEffect.da(sound_enabled);

                // 如果建议存在小数点，失效
                if (token2.contains("."))
                    return;

                // 如果上一个输入的是运算符，说明是新输入的数字是直接以.开头的形式
                if (last2 == Last.operator)
                    token2 = "0.";
                else if (last2 == Last.number)
                    token2 = token2 + ".";
                display2.setText(token2);
                expression2.setText(lastCompleteExpression2 + token2);

                // 更新flags和AC
                clear2.setText("C");
                last2 = Last.number;
            }
        });

        equal2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soundEffect.ding(sound_enabled);

                if (last2 == Last.equal)
                    return;

                if (last2 == Last.number)
                    lastCompleteExpression2 = lastCompleteExpression2 + token2;


                Expression expression = new Expression(lastCompleteExpression2);
                try {
                    token1 = expression.get_eval();
                } catch (DivideZero E) {
                    token1 = DIVIDE_ZERO;
                } catch (Overflow E) {
                    token1 = OVERFLOW;
                }
                display2.setText(token2);

                expression2.setText(lastCompleteExpression2 + " = " + token2);
                lastCompleteExpression2 = "";

                clear2.setText("AC");
                last2 = Last.equal;
            }

        });

        // 中间的Panel
        left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soundEffect.da(sound_enabled);

                if (token2.equals(DIVIDE_ZERO))
                    return;

                token1 = token2;
                display1.setText(token1);
                expression1.setText(lastCompleteExpression1 + token1);
                last1 = Last.number;
            }
        });

        right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soundEffect.da(sound_enabled);

                if (token1.equals(DIVIDE_ZERO))
                    return;

                token2 = token1;
                display2.setText(token1);
                expression2.setText(lastCompleteExpression2 + token2);
                last2 = Last.number;
            }
        });

        del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soundEffect.da(sound_enabled);

                token1 = "0";
                token2 = "0";
                lastCompleteExpression1 = "";
                lastCompleteExpression2 = "";
                display1.setText("0");
                display2.setText("0");
                expression1.setText("");
                expression2.setText("");
            }
        });

        sound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sound_enabled = !sound_enabled;
                if(sound_enabled)
                    soundEffect.ding(true);

            }
        });
    }
}
