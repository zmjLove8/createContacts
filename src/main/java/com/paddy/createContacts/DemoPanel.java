package com.paddy.createContacts;

import com.ringcentral.RestException;
import com.ringcentral.definitions.PersonalContactInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class DemoPanel extends JPanel {
    private JLabel labelUser, labelExt, labelCount;            //标签      账号，Extension，创建的联系人数
    private JButton buttonCreate, buttonLogout;         //按钮      创建联系人，退出
    private JTextField textFieldUserName;             //文本框  用户名输入
    private JTextField testFieldCount;                //文本框 联系人数量
    private JTextField extensionField;               //extension
    private JPanel panelUserName;
    private JPanel panelExt;
    private JPanel panelCount;
    private JPanel panelCreateButton;
    private JPanel panelLogoutButton;

    public DemoPanel() {
        this.labelUser = new JLabel("PhoneNumber");
        this.labelExt = new JLabel("Extension");
        this.labelCount = new JLabel("ContactCounts");
        this.buttonCreate = new JButton("CreateContacts");
        this.buttonLogout = new JButton("Logout");
        this.textFieldUserName = new JTextField(10);
        this.testFieldCount = new JTextField(10);
        this.extensionField = new JTextField(10);
        this.panelExt = new JPanel();
        this.panelUserName = new JPanel();
        this.panelCount = new JPanel();
        this.panelCreateButton = new JPanel();
        this.panelLogoutButton = new JPanel();

        this.setLayout(new GridLayout(2, 3));  //网格式布局

        this.panelUserName.add(this.labelUser);
        this.panelUserName.add(this.textFieldUserName);
        this.panelCount.add(this.labelCount);
        this.panelCount.add(this.testFieldCount);
        this.panelExt.add(this.labelExt);
        this.panelExt.add(this.extensionField);
        this.panelCreateButton.add(buttonCreate);
        this.panelLogoutButton.add(buttonLogout);


        buttonCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App app = new App();
                try {
                    String accountValue = textFieldUserName.getText().trim();
                    int countValue = Integer.parseInt(testFieldCount.getText());
                    String ext = extensionField.getText().trim();
                    ArrayList<PersonalContactInfo> contacts = app.createContact(accountValue, ext, countValue);
                    StringBuffer buffer = new StringBuffer();
                    for (PersonalContactInfo contact : contacts) {
                        buffer.append(contact.firstName + contact.lastName + "\n");
                    }
                    App.restClient.revoke();
                    JOptionPane.showMessageDialog(null, "Here are the contacts that were successfully created: "+"\n"+buffer);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (RestException e1) {
                    e1.printStackTrace();
                }
            }
        });

        buttonLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        this.add(this.panelUserName);
        this.add(this.panelCount);
        this.add(this.panelExt);
        this.add(this.panelCreateButton);
        this.add(this.panelLogoutButton);
    }
}
