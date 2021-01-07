/*
 * Created by JFormDesigner on Wed Jan 06 21:53:53 ICT 2021
 */

package client;

import model.Message;
import model.Student;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;

/**
 * @author unknown
 */
public class ClientForm extends JFrame {
    ClientControl control;
    DefaultTableModel model;

    public ClientForm() {
        control = new ClientControl();
        init();
        control.openConnect();
    }

    public ClientForm(ClientControl control){
        init();
        this.control = control;
    }

    private void init(){
        initComponents();

        model = (DefaultTableModel) tblStudent.getModel();

        model.setColumnCount(0);
        model.addColumn("ID");
        model.addColumn("Ten");
        model.addColumn("Ngay Sinh");
        model.addColumn("Khoa");
        model.addColumn("Que Quan");
    }

    private void btnSearchMouseClicked(MouseEvent e) {
        String name = txtName.getText();
        Message request = new Message(name, Message.MessageType.FIND);
        control.sendData(request);
        Message response = control.receiveData();
        if(response.getType() == Message.MessageType.FIND){
            ArrayList<Student> students = (ArrayList) response.getObject();
            model.setRowCount(0);
            for(Student s:students){
                model.addRow(s.toObject());
            }
        }
    }

    private void tblStudentMouseClicked(MouseEvent e) {
        int row = tblStudent.getSelectedRow();
        Student student = new Student(
                (int) model.getValueAt(row, 0),
                (String) model.getValueAt(row, 1),
                (Date) model.getValueAt(row, 2),
                (String) model.getValueAt(row, 3),
                (String) model.getValueAt(row, 4)
        );

        new DetailStudentForm(control, student).setVisible(true);
        dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        txtName = new JTextField();
        btnSearch = new JButton();
        scrollPane1 = new JScrollPane();
        tblStudent = new JTable();

        //======== this ========
        Container contentPane = getContentPane();

        //---- btnSearch ----
        btnSearch.setText("Search");
        btnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnSearchMouseClicked(e);
            }
        });

        //======== scrollPane1 ========
        {

            //---- tblStudent ----
            tblStudent.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    tblStudentMouseClicked(e);
                }
            });
            scrollPane1.setViewportView(tblStudent);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(txtName, GroupLayout.PREFERRED_SIZE, 425, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                    .addComponent(btnSearch)
                    .addGap(48, 48, 48))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 589, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(18, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(36, 36, 36)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearch))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientForm().setVisible(true);
            }
        });
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JTextField txtName;
    private JButton btnSearch;
    private JScrollPane scrollPane1;
    private JTable tblStudent;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
