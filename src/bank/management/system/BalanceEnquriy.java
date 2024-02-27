package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class BalanceEnquriy extends JFrame implements ActionListener {
    String pin;
    JLabel label1;
    JButton b1;
    BalanceEnquriy(String pin){

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2=i1.getImage().getScaledInstance(1550,830,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel l3=new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);

        JLabel label=new JLabel("Your Current Balance in Rs");
        label.setForeground(Color.white);
        label.setFont(new Font("System",Font.BOLD,16));
        label.setBounds(430,180,700,35);
        l3.add(label);

        label1=new JLabel();
        label1.setForeground(Color.white);
        label1.setFont(new Font("System",Font.BOLD,16));
        label1.setBounds(430,220,700,35);
        l3.add(label1);

        b1=new JButton("Back");
        b1.setBounds(700,406,150,35);
        b1.setBackground(new Color(65,125,128));
        b1.setForeground(Color.white);
        b1.addActionListener(this);
        l3.add(b1);

        int balance=0;

        try{
            Con c=new Con();
            ResultSet resultSet=c.statement.executeQuery("select * from bank where pin = '"+pin+"'");
            while (resultSet.next()){
                if(resultSet.getString("type").equals("Deposit")){
                    balance +=Integer.parseInt(resultSet.getString("amount"));
                }
                else{
                    // for minus the balance
                    balance-=Integer.parseInt(resultSet.getString("amount"));
                }
            }

        }catch (Exception E){
             E.printStackTrace();
        }

        label1.setText(""+balance);

        setLayout(null);
        setSize(1550,1080);
        setLocation(0,0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new main_Class(pin);
    }

    public static void main(String[] args) {
        new BalanceEnquriy("");
    }
}
