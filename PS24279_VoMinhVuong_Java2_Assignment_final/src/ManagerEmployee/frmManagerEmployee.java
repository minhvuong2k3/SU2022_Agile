package ManagerEmployee;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frmManagerEmployee extends javax.swing.JFrame {
    //Luu lich su dang xuat
    
    String path = "C:\\Users\\Admin\\Documents\\Github\\SU2022_Agile\\PS24279_VoMinhVuong_Java2_Assignment_final\\Employee.dat";

    List<Employee> list = new ArrayList<>();
    int index = 0;
    int index1 = 0,index2 = 0, index3 = 0, index4 = 0;
    int alt = -1;
        
    public void addEmployee(){
        Employee emp = new Employee();
        emp.setManv(txtMaNhanVien.getText());
        emp.setHoTen(txtHoVaTen.getText());
        emp.setTuoi(Integer.parseInt(txtTuoi.getText()));
        emp.setEmail(txtEmail.getText());
        emp.setLuong(Double.parseDouble(txtLuong.getText()));
        list.add(emp);
    }

    public void jbindex(int x){
        if(x==0)
            jbindex.setText("|| -  "+String.valueOf(index+1)+"  of  "+String.valueOf(list.size())+"  - ||");
        else 
            jbindex.setText("|| -  "+String.valueOf(index)+"  of  "+String.valueOf(list.size())+"  - ||");
    }
    
    public void historyLogout () {
        
    }
    
    public Object readFile(String path) throws FileNotFoundException, IOException, ClassNotFoundException{
        try (
                FileInputStream fis = new FileInputStream(path);
                ObjectInputStream ois = new ObjectInputStream(fis);
            ){
            Object oo = ois.readObject();
            ois.close();
            return oo; 
        }
    }
    
    public void writeFile (String path, Object data) throws FileNotFoundException, IOException {
        try (
                FileOutputStream fos = new FileOutputStream(path);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
            ){
            oos.writeObject(data);
            oos.close();
        }
    }
    
    public boolean check(int check1){
        if(txtMaNhanVien.getText().trim().length()==0){
            txtMaNhanVien.setBackground(Color.yellow);
            txtMaNhanVien.requestFocus();
            JOptionPane.showMessageDialog(this, "must not be empty!","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else {
            txtMaNhanVien.setBackground(Color.white);
            if(check1==0){
                for(int i = 0; i < list.size(); i++){
                    Employee emp = list.get(i);
                    if(txtMaNhanVien.getText().trim().equals(emp.getManv())){
                        JOptionPane.showMessageDialog(this, "ID already exist!","Error",JOptionPane.ERROR_MESSAGE);
                        txtMaNhanVien.setBackground(Color.yellow);
                        txtMaNhanVien.requestFocus();
                        return false;
                    }
                }              
            }
            else {
                for(int i = 0; i < list.size(); i++){
                    if(i!=index){
                        Employee emp = list.get(i);
                        if(txtMaNhanVien.getText().trim().equals(emp.getManv())){
                            JOptionPane.showMessageDialog(this, "ID already exist!","Error",JOptionPane.ERROR_MESSAGE);
                            txtMaNhanVien.setBackground(Color.yellow);
                            txtMaNhanVien.requestFocus();
                            return false;
                        }
                    }                   
                }                      
            }          
        }
        if(txtHoVaTen.getText().trim().length()==0){
            txtHoVaTen.setBackground(Color.yellow);
            txtHoVaTen.requestFocus();
            JOptionPane.showMessageDialog(this, "must not be empty!","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else {
            txtHoVaTen.setBackground(Color.white);           
        }
        try{
            if(txtTuoi.getText().trim().length()==0){
                txtTuoi.setBackground(Color.yellow);
                txtTuoi.requestFocus();
                JOptionPane.showMessageDialog(this, "must not be empty!","Error",JOptionPane.ERROR_MESSAGE);
                return false;
            }
            else {
                txtTuoi.setBackground(Color.white);
                if(Integer.parseInt(txtTuoi.getText()) < 16 || Integer.parseInt(txtTuoi.getText()) > 55 ){
                    txtTuoi.setBackground(Color.yellow);
                    txtTuoi.requestFocus();
                    JOptionPane.showMessageDialog(this, "Age must be between 16 - 55!","Error",JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        }
        catch(Exception e){
            txtTuoi.setBackground(Color.yellow);
            txtTuoi.requestFocus();
            JOptionPane.showMessageDialog(this, "Malformed data!","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String reEmail = "\\w+@+\\w+(\\.+\\w+){1,2}";
        if(txtEmail.getText().trim().length()==0){
            txtEmail.setBackground(Color.yellow);
            txtEmail.requestFocus();
            JOptionPane.showMessageDialog(this, "must not be empty!","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else {
            txtEmail.setBackground(Color.white);
            if(!txtEmail.getText().matches(reEmail)){
                txtEmail.setBackground(Color.yellow);
                txtEmail.requestFocus();
                JOptionPane.showMessageDialog(this, "Malformed email!","Error",JOptionPane.ERROR_MESSAGE);
                return false;
            }
            else {
                txtEmail.setBackground(Color.white);
            }
        }
        if(txtLuong.getText().trim().length()==0){
            txtLuong.setBackground(Color.yellow);
            txtLuong.requestFocus();
            JOptionPane.showMessageDialog(this, "must not be empty!","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else {
            txtLuong.setBackground(Color.white);
            if(Double.parseDouble(txtLuong.getText())<5000000){
                txtLuong.setBackground(Color.yellow);
                txtLuong.requestFocus();
                JOptionPane.showMessageDialog(this, "Salary must be > 5000000 VND!","Error",JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }
    
    public void checkBtn(){
        if(index == 0){
            btnFirst.setEnabled(false);
            btnLast.setEnabled(true);
        }
        else if(index == list.size()-1){
            btnLast.setEnabled(false);
            btnFirst.setEnabled(true);
        }
        else {
            btnLast.setEnabled(true);
            btnFirst.setEnabled(true);
        }
    }
    
    public void fillToTable(){
        DefaultTableModel model = (DefaultTableModel)tblNhanVien.getModel();
        model.setRowCount(0);
        for(Employee emp:list){
            Object[] rows = new Object[]{emp.getManv(),emp.getHoTen(),emp.getTuoi(),emp.getEmail(),emp.getLuong()};          
            model.addRow(rows);
        }
    }
    
    public void updateEmployee(){
        Employee emp = list.get(index);
        emp.setManv(txtMaNhanVien.getText());
        emp.setHoTen(txtHoVaTen.getText());
        emp.setTuoi(Integer.parseInt(txtTuoi.getText()));
        emp.setEmail(txtEmail.getText());
        emp.setLuong(Double.parseDouble(txtLuong.getText()));
    }
    
    public void updateSamary(){
        Employee emp = list.get(index);
        emp.setLuong(Double.parseDouble(txtLuong.getText()));
    }

    public void showDetail(){
        Employee emp = list.get(index);
        txtMaNhanVien.setText(emp.getManv());
        txtHoVaTen.setText(emp.getHoTen());
        txtTuoi.setText(String.valueOf(emp.getTuoi()));
        txtEmail.setText(emp.getEmail());
        txtLuong.setText(String.valueOf(emp.getLuong()));
    }
       
    public void clearForm(){
        txtMaNhanVien.setText("");
        txtHoVaTen.setText("");
        txtTuoi.setText("");
        txtEmail.setText("");
        txtLuong.setText("");
    }
    
    public void findByName(){
        List<Employee> list1 = new ArrayList<>();
        for(Employee emp:list){
            if(emp.getHoTen().contains(txtHoVaTen.getText()))
                list1.add(emp);
        }
        DefaultTableModel model = (DefaultTableModel)tblNhanVien.getModel();
        model.setRowCount(0);
        for(Employee emp:list1){
            Object[] rows = new Object[]{emp.getManv(),emp.getHoTen(),emp.getTuoi(),emp.getEmail(),emp.getLuong()};          
            model.addRow(rows);
        }

    }
    public frmManagerEmployee(int x) {
        if(x==0)
            alt = 0;
        else 
            alt = 1;
        initComponents();    
        jbindex(-1);
        new Thread(){
            @Override
            public void run(){
                while(true){
                    Date now = new Date();
                    SimpleDateFormat formater = new SimpleDateFormat("hh:mm:ss aa");
                    String time = formater.format(now);
                    jbClock.setText(time);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {}
                }
            }
        }.start();
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMaNhanVien = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtHoVaTen = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTuoi = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtLuong = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        btnNew = new javax.swing.JButton();
        btnOpen = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnFind = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnSapxepID = new javax.swing.JButton();
        btnSapxepLuong = new javax.swing.JButton();
        btnSapxepTuoi = new javax.swing.JButton();
        btnSapxepTen = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();
        jbindex = new javax.swing.JLabel();
        btnFirst = new javax.swing.JButton();
        btnPrevious = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        jbClock = new javax.swing.JLabel();
        btnUpdate1 = new javax.swing.JButton();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(50, 140, 229));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ManagerEmployee/user-free-icon-font (3).png"))); // NOI18N
        jLabel1.setText("Maneger Employee");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(50, 140, 229));
        jLabel2.setText("FORM");

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(50, 140, 229));
        jLabel3.setText("FUNCTION");

        jLabel4.setText("ID");

        txtMaNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNhanVienActionPerformed(evt);
            }
        });

        jLabel5.setText("NAME");

        jLabel6.setText("AGE");

        jLabel7.setText("EMAIL");

        jLabel8.setText("SAMARY");

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NAME", "AGE", "EMAIL", "SAMARY"
            }
        ));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ManagerEmployee/add-document-free-icon-font.png"))); // NOI18N
        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ManagerEmployee/upload-free-icon-font.png"))); // NOI18N
        btnOpen.setText("Open");
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ManagerEmployee/disk-free-icon-font.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ManagerEmployee/search-free-icon-font.png"))); // NOI18N
        btnFind.setText("Find");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ManagerEmployee/refresh-free-icon-font.png"))); // NOI18N
        btnUpdate.setText("Update Employee");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnSapxepID.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ManagerEmployee/settings-sliders-free-icon-font (1).png"))); // NOI18N
        btnSapxepID.setText("  Order by ID  ");
        btnSapxepID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSapxepIDActionPerformed(evt);
            }
        });

        btnSapxepLuong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ManagerEmployee/settings-sliders-free-icon-font (1).png"))); // NOI18N
        btnSapxepLuong.setText("Order by Samary");
        btnSapxepLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSapxepLuongActionPerformed(evt);
            }
        });

        btnSapxepTuoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ManagerEmployee/settings-sliders-free-icon-font (1).png"))); // NOI18N
        btnSapxepTuoi.setText("  Order by Age");
        btnSapxepTuoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSapxepTuoiActionPerformed(evt);
            }
        });

        btnSapxepTen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ManagerEmployee/settings-sliders-free-icon-font (1).png"))); // NOI18N
        btnSapxepTen.setText("   Order by Name");
        btnSapxepTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSapxepTenActionPerformed(evt);
            }
        });

        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ManagerEmployee/sign-out-free-icon-font.png"))); // NOI18N
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        jbindex.setForeground(new java.awt.Color(51, 153, 255));
        jbindex.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jbindex.setText("index");

        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ManagerEmployee/angle-double-left-free-icon-font.png"))); // NOI18N
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPrevious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ManagerEmployee/angle-left-free-icon-font.png"))); // NOI18N
        btnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousActionPerformed(evt);
            }
        });

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ManagerEmployee/angle-right-free-icon-font.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ManagerEmployee/angle-double-right-free-icon-font.png"))); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        jbClock.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jbClock.setForeground(new java.awt.Color(51, 153, 255));
        jbClock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jbClock.setText("Clock");

        btnUpdate1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ManagerEmployee/refresh-free-icon-font.png"))); // NOI18N
        btnUpdate1.setText("Update Samary");
        btnUpdate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTuoi, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                            .addComponent(txtMaNhanVien)
                            .addComponent(txtHoVaTen, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                            .addComponent(txtLuong))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(btnFind, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                                            .addComponent(btnNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(108, 108, 108))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnUpdate1)
                                        .addGap(10, 10, 10))
                                    .addComponent(jbClock, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(109, 109, 109)
                                        .addComponent(btnOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(btnSapxepID, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(btnSapxepTen, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jbindex, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSapxepTuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSapxepLuong)
                        .addGap(36, 36, 36))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jbClock))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnNew)
                                    .addComponent(btnSave))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(txtHoVaTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnFind))
                                    .addComponent(btnOpen, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnExit)
                                .addGap(19, 19, 19)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTuoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnSapxepID)
                                    .addComponent(btnSapxepTen))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSapxepTuoi)
                            .addComponent(btnSapxepLuong))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnUpdate1)
                                    .addComponent(btnUpdate))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(btnFirst))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnNext, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnLast, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jbindex)
                                        .addGap(8, 8, 8))))))
                    .addComponent(btnPrevious))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNhanVienActionPerformed
        // TODO add your handling codeere:
    }//GEN-LAST:event_txtMaNhanVienActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        clearForm();
        txtMaNhanVien.requestFocus();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        if(alt==0){
            if(check(0)){           
                try {
                    addEmployee();
                    fillToTable();
                    writeFile(path, list);               
                    index = list.size()-1;
                    jbindex(0);
                    tblNhanVien.setRowSelectionInterval(index, index);
                    checkBtn();
                } catch (IOException ex) {
                    Logger.getLogger(frmManagerEmployee.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
        }
        else 
            JOptionPane.showMessageDialog(this, "Bạn không đủ quyền hạng để thực hiện chức năng này!");
          
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        if(alt == 0){
            if(tblNhanVien.getSelectedRow()!=-1){
                if(check(1)){
                    int check = JOptionPane.showConfirmDialog(this, "Update ?","confirm",JOptionPane.YES_NO_OPTION);
                    if(check == JOptionPane.YES_OPTION){
                        try {
                            updateEmployee();
                            fillToTable();
                            writeFile(path, list);
                            tblNhanVien.setRowSelectionInterval(index, index);
                            JOptionPane.showMessageDialog(this, "Successfully Update!");
                            jbindex(0);
                        } catch (IOException ex) {
                            Logger.getLogger(frmManagerEmployee.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(this, "Select the employee to be corrected!","Error",JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }          
            }  
            else 
                JOptionPane.showMessageDialog(this, "Bạn không đủ quyền hạng để thực hiện chức năng này!");
        }
        
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        // TODO add your handling code here:
        if(txtMaNhanVien.getText().trim().length()!=0){
            for(int i=0;i<list.size();i++){
                if(list.get(i).getManv().equals(txtMaNhanVien.getText())){
                    index = i;
                    showDetail();
                    tblNhanVien.setRowSelectionInterval(index, index);
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Không có nhân viên nào trùng khớp!","error",JOptionPane.WARNING_MESSAGE);
        }
        else if(txtHoVaTen.getText().trim().length()!=0){
            findByName();
        }
        else {
            fillToTable();
            index=0;
            tblNhanVien.setRowSelectionInterval(index, index);
            showDetail();
        }
    }//GEN-LAST:event_btnFindActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new frmLogin().setVisible(true);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnSapxepIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSapxepIDActionPerformed
        // TODO add your handling code here:
        index2 ++;
        if(index2 % 2 !=0){
            Comparator<Employee> ma = new Comparator<>(){
                @Override
                public int compare(Employee o1, Employee o2) {
                    return o1.getManv().compareTo(o2.getManv());
                }           
            };
            Collections.sort(list, ma); 
        }
        else {
            Comparator<Employee> ma = new Comparator<>(){
                @Override
                public int compare(Employee o1, Employee o2) {
                    return o2.getManv().compareTo(o1.getManv());
                }           
            };
            Collections.sort(list, ma); 
        }
        fillToTable();
    }//GEN-LAST:event_btnSapxepIDActionPerformed

    private void btnSapxepTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSapxepTenActionPerformed
        // TODO add your handling code here:
        index1 ++;
        if(index1 % 2 != 0){
            Comparator<Employee> ten = new Comparator<>(){
                @Override
                public int compare(Employee o1, Employee o2) {
                    return o1.getHoTen().compareTo(o2.getHoTen());
                }           
            };
            Collections.sort(list, ten);            
        }
        else {
            Comparator<Employee> ten = new Comparator<>(){
                @Override
                public int compare(Employee o1, Employee o2) {
                    return o2.getHoTen().compareTo(o1.getHoTen());
                }           
            };
            Collections.sort(list, ten);
        }       
        fillToTable();
    }//GEN-LAST:event_btnSapxepTenActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:   
        if(list.size()>0){
            index = 0;
            tblNhanVien.setRowSelectionInterval(index, index);
            showDetail();
            checkBtn();
            jbindex(0);
        }
        
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousActionPerformed
        // TODO add your handling code here:
        if(list.size()>0){
            if(index > 0){
                index --;
                tblNhanVien.setRowSelectionInterval(index, index);
                showDetail();
            }
            checkBtn();
            jbindex(0);
        }
        
    }//GEN-LAST:event_btnPreviousActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        if(list.size()>0){
            if(index<list.size()-1){
                index ++;
                tblNhanVien.setRowSelectionInterval(index, index);
                showDetail();
            }
            checkBtn();
            jbindex(0);
        }
        
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here: 
        if(list.size()>0){
            index = list.size()-1;
            tblNhanVien.setRowSelectionInterval(index, index);
            showDetail();  
            checkBtn(); 
            jbindex(0);
        }
        
    }//GEN-LAST:event_btnLastActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        // TODO add your handling code here:      
        index = tblNhanVien.getSelectedRow();
        checkBtn();
        this.showDetail();
        jbindex(0);
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void btnSapxepTuoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSapxepTuoiActionPerformed
        // TODO add your handling code here:
        index3 ++;
        if(index3 % 2 !=0){
            Comparator<Employee> Age = new Comparator<>(){
                @Override
                public int compare(Employee o1, Employee o2) {
                    return o1.getTuoi() - o2.getTuoi();
                }           
            };
            Collections.sort(list, Age); 
        }
        else {
            Comparator<Employee>  Age = new Comparator<>(){
                @Override
                public int compare(Employee o1, Employee o2) {
                    return o2.getTuoi() - o1.getTuoi();
                }           
            };
            Collections.sort(list, Age); 
        }
        fillToTable();
        
    }//GEN-LAST:event_btnSapxepTuoiActionPerformed

    private void btnSapxepLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSapxepLuongActionPerformed
        // TODO add your handling code here:
        index4 ++;
        if(index4 % 2 !=0){
            Comparator<Employee> Samary = new Comparator<>(){
                @Override
                public int compare(Employee o1, Employee o2) {
                    return (int) (o1.getLuong() - o2.getLuong());
                }           
            };
            Collections.sort(list, Samary); 
        }
        else {
            Comparator<Employee>  Samary = new Comparator<>(){
                @Override
                public int compare(Employee o1, Employee o2) {
                    return (int) (o2.getLuong() - o1.getLuong());
                }           
            };
            Collections.sort(list, Samary); 
        }
        fillToTable();
    }//GEN-LAST:event_btnSapxepLuongActionPerformed

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
        try {
            // TODO add your handling code here:
            list = (List<Employee>) readFile(path);           
            if(list != null){
                index = 0;
                checkBtn();
                fillToTable();
                showDetail();
                tblNhanVien.setRowSelectionInterval(index, index);
                jbindex(0);
            }          
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }
    }//GEN-LAST:event_btnOpenActionPerformed

    private void btnUpdate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate1ActionPerformed
        // TODO add your handling code here:
        if(tblNhanVien.getSelectedRow()!=-1){
            if(check(1)){
                int check = JOptionPane.showConfirmDialog(this, "Update ?","confirm",JOptionPane.YES_NO_OPTION);
                if(check == JOptionPane.YES_OPTION){
                    try {
                        updateSamary();
                        fillToTable();
                        writeFile(path, list);
                        tblNhanVien.setRowSelectionInterval(index, index);
                        JOptionPane.showMessageDialog(this, "Successfully Update!");
                        jbindex(0);
                    } catch (IOException ex) {
                        Logger.getLogger(frmManagerEmployee.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(this, "Select the employee to be corrected!","Error",JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
        }
    }//GEN-LAST:event_btnUpdate1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmManagerEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmManagerEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmManagerEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmManagerEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmManagerEmployee(0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnOpen;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JButton btnSapxepID;
    private javax.swing.JButton btnSapxepLuong;
    private javax.swing.JButton btnSapxepTen;
    private javax.swing.JButton btnSapxepTuoi;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdate1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jbClock;
    private javax.swing.JLabel jbindex;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoVaTen;
    private javax.swing.JTextField txtLuong;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JTextField txtTuoi;
    // End of variables declaration//GEN-END:variables
}
