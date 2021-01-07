/*
 * Created by JFormDesigner on Thu Jan 07 13:43:24 ICT 2021
 */

package client;

import java.awt.event.*;

import model.Message;
import model.Student;

import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class DetailStudentForm extends JFrame {
    private ClientControl control;
    private Student student;
    
    public DetailStudentForm(ClientControl control, Student student) {
        this.control = control;
        this.student = student;
        
        initComponents();
        initData();
    }

    private void initData() {
        txtId.setText(String.valueOf(student.getId()));
        txtName.setText(student.getName());
        txtClassYear.setText(student.getClassYear());
        txtBirth.setText(student.getBirthYear().toString());
        txtAddress.setText(student.getAddress());
    }

    private void btnUpdateMouseClicked(MouseEvent e) {
        try {
            student.setName(txtName.getText());
            student.setBirthYear(new SimpleDateFormat("yyyy-MM-dd").parse(txtBirth.getText()));
            student.setClassYear(txtClassYear.getText());
            student.setAddress(txtAddress.getText());
        } catch (ParseException parseException) {
            parseException.printStackTrace();
            JOptionPane.showMessageDialog(this, "Wrong date format! Please try again.");
        }

        Message request = new Message(student, Message.MessageType.UPDATE);
        control.sendData(request);

        Message response = control.receiveData();
        if(response.getType() == Message.MessageType.UPDATE){
            Boolean result = (Boolean) response.getObject();
            if(result) JOptionPane.showMessageDialog(this, "Update Success.");
            else JOptionPane.showMessageDialog(this, "Update Failed.");
        }
    }

    private void btnBackMouseClicked(MouseEvent e) {
        dispose();
        new ClientForm(control).setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        label1 = new JLabel();
        txtName = new JTextField();
        txtBirth = new JTextField();
        label2 = new JLabel();
        txtAddress = new JTextField();
        label3 = new JLabel();
        txtClassYear = new JTextField();
        label4 = new JLabel();
        label5 = new JLabel();
        txtId = new JTextField();
        btnUpdate = new JButton();
        btnBack = new JButton();

        //======== this ========
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText("Ten");

        //---- label2 ----
        label2.setText("Ngay Sinh");

        //---- label3 ----
        label3.setText("Khoa");

        //---- label4 ----
        label4.setText("Que Quan");

        //---- label5 ----
        label5.setText("ID");

        //---- txtId ----
        txtId.setEditable(false);

        //---- btnUpdate ----
        btnUpdate.setText("Update");
        btnUpdate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnUpdateMouseClicked(e);
            }
        });

        //---- btnBack ----
        btnBack.setText("Back");
        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnBackMouseClicked(e);
            }
        });

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(25, 25, 25)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(contentPaneLayout.createParallelGroup()
                            .addComponent(label5)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(label3, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                    .addGap(59, 59, 59)
                                    .addComponent(txtClassYear))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(label2)
                                    .addGap(35, 35, 35)
                                    .addComponent(txtBirth, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))
                                .addComponent(label1)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(btnBack)
                                        .addComponent(label4))
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtAddress, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addGap(90, 90, 90)
                                            .addComponent(btnUpdate))))))
                        .addGroup(contentPaneLayout.createParallelGroup()
                            .addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtName, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(89, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label5)
                        .addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1)
                        .addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addComponent(label2))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(txtBirth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label3)
                        .addComponent(txtClassYear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(26, 26, 26)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(txtAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label4))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnUpdate)
                        .addComponent(btnBack))
                    .addContainerGap(11, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel label1;
    private JTextField txtName;
    private JTextField txtBirth;
    private JLabel label2;
    private JTextField txtAddress;
    private JLabel label3;
    private JTextField txtClassYear;
    private JLabel label4;
    private JLabel label5;
    private JTextField txtId;
    private JButton btnUpdate;
    private JButton btnBack;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
