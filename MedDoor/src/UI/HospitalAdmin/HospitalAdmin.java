package UI.HospitalAdmin;

import Business.Community.House;
import Business.Community.Tenant;
import Business.EcoSystem;
import Business.Employee.Employee;
import Business.Enterprise.Enterprise;
import Business.Enterprise.HospitalEnterprise;
import Business.Network.Network;
import Business.Organization.DoctorOrganization;
import Business.Organization.HospitalStaffOrganization;
import Business.Organization.Organization;
import Business.Role.DoctorRole;
import Business.Role.StaffRole;
import Business.UserAccount.UserAccount;
import Business.Voluntary.SurveyReport;
import Business.Voluntary.VitalSigns;
import Business.WorkQueue.HospitalWorkRequest;
import Business.WorkQueue.StaffWorkRequest;
import Business.WorkQueue.WorkQueue;
import Business.WorkQueue.WorkRequest;
import UI.Components.TableCustom;
import UI.Login.MainLoginPage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class HospitalAdmin extends javax.swing.JFrame {

    boolean a = true;
    static boolean maximized = true;
    private JFrame userProcessContainer;
    JFrame parentFrame;
    HospitalEnterprise hEnterPrise;
    Organization organization;
    UserAccount account;
    Network network;

    ArrayList<Tenant> tenantList;

    public HospitalAdmin(UserAccount account,
            Organization organization,
            Enterprise enterprise,
            EcoSystem business, JFrame parentFrame) {
        initComponents();
        TableCustom.apply(crudDoctorSP, TableCustom.TableType.DEFAULT);
//        TableCustom.apply(jScrollPane2, TableCustom.TableType.DEFAULT);
        TableCustom.apply(patientList, TableCustom.TableType.DEFAULT);
        TableCustom.apply(crudStaffSP, TableCustom.TableType.DEFAULT);
        hEnterPrise = (HospitalEnterprise) enterprise;
        this.organization = organization;
        this.parentFrame = parentFrame;
        this.account = account;
        network = business.getNetworkList().get(0);
        hospitalAdmin.setSelectedIndex(0);
//        initializeOrganizations();
        populateRequestTable();
    }

    public void populateRequestTable() {
        try {
            DefaultTableModel model = (DefaultTableModel) patientTable.getModel();

            model.setRowCount(0);
            if (hEnterPrise.getWorkQueue() == null) {
                hEnterPrise.setWorkQueue(new WorkQueue());
            }

            for (WorkRequest request : hEnterPrise.getWorkQueue().getWorkRequestList()) {
                if (request instanceof HospitalWorkRequest) {
                    Tenant tenant = ((HospitalWorkRequest) request).getPatient();
                    this.tenantList.add(tenant);
                    Object[] row = new Object[8];
                    row[0] = tenant.getFirstName();
                    row[1] = tenant.getVitalSign().getHeight();
                    row[2] = tenant.getVitalSign().getWeight();
                    row[3] = tenant.getVitalSign().getRespiratoryRate();
                    row[4] = tenant.getVitalSign().getHeartRate();
                    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                    String formattedDate = dateFormat.format(((HospitalWorkRequest) request).assignedDate);
                    row[5] = formattedDate;
                    row[6] = ((HospitalWorkRequest) request).getStatus();
                    row[7] = ((HospitalWorkRequest) request).getSender().getEmployee().getName();
                    model.addRow(row);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "system is down please contact system admin");
        }

    }
    //Method to change panel color on hover

//    public HospitalAdmin(UserAccount account, 
//            Organization organization, 
//            Enterprise enterprise, 
//            EcoSystem business,JFrame parentFrame) {
//        initComponents();
//          this.parentFrame = parentFrame;
////        this.userProcessContainer=userProcessContainer;
//    }
    public void populateDoctorTable() {
        try {
            DefaultTableModel model = (DefaultTableModel) crudDoctorTable.getModel();

            model.setRowCount(0);

            for (Organization org : hEnterPrise.getOrganizationDirectory().getOrganizationList()) {
                if (org.getName() == Organization.Type.Doctor.getValue()) {
                    for (UserAccount user : org.getUserAccountDirectory().getUserAccountList()) {
                        Object[] row = new Object[3];
                        row[0] = user.getEmployee().getName();
                        row[1] = user.getUsername();
                        row[2] = user.getPassword();
                        model.addRow(row);
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please try again");
        }
    }

    public void populateStaffTable() {
        try {
            DefaultTableModel model = (DefaultTableModel) crudStaffTable.getModel();

            model.setRowCount(0);
            for (Organization org : hEnterPrise.getOrganizationDirectory().getOrganizationList()) {
                if (org.getName() == Organization.Type.HospitalStaff.getValue()) {
                    for (UserAccount user : org.getUserAccountDirectory().getUserAccountList()) {
                        Object[] row = new Object[3];
                        row[0] = user.getEmployee().getName();
                        row[1] = user.getUsername();
                        row[2] = user.getPassword();
                        model.addRow(row);
                    }
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please try again");
        }
    }

//    public void populateDoctor() {
//doctorCombo.removeAllItems();
//        if (docOrg.getUserAccountDirectory().getUserAccountList().size() > 0) {
//
//            for (UserAccount account : docOrg.getUserAccountDirectory().getUserAccountList()) {
//                System.out.println("line 145" + account.getEmployee());
//                doctorCombo.addItem(account.getEmployee());
//            }
//        }
//    }
    public void populateStaff() {
        staffCombo.removeAllItems();
        try {
            for (Organization org : hEnterPrise.getOrganizationDirectory().getOrganizationList()) {
                if (org.getName() == Organization.Type.HospitalStaff.getValue()) {
                    for (UserAccount account : org.getUserAccountDirectory().getUserAccountList()) {
                        staffCombo.addItem(account.getEmployee());
                    }

                }
            }
        } catch (Exception e) {
            System.out.println("Error- " + e);
        }

    }

    public void changecolor(JPanel hover, Color rand) {
        hover.setBackground(rand);
    }

    //Method to change Background color of the panel 
    public void clickmenu(JPanel h1, JPanel h2, int numberbool) {
        if (numberbool == 1) {
            h1.setBackground(new Color(42, 58, 73));
            h2.setBackground(new Color(4, 16, 20));

        } else {
            h1.setBackground(new Color(4, 16, 20));
            h2.setBackground(new Color(42, 58, 73));
        }
    }
    //Method to change icon 

    public void changeImage(JLabel button, String rsimage) {
        ImageIcon aimg = new ImageIcon(getClass().getResource(rsimage));
        button.setIcon(aimg);
    }

    //Method to hide and expand menu button
    public void hideshow(JPanel menushowhide, boolean dashboard, JLabel button) {
        if (dashboard == true) {
            menushowhide.setPreferredSize(new Dimension(50, menushowhide.getHeight()));
            changeImage(button, "/com/vcare/icon/menu_32px.png");
        } else {
            menushowhide.setPreferredSize(new Dimension(270, menushowhide.getHeight()));
            changeImage(button, "/com/vcare/icon/back_32px.png");
        }
    }

    public void changecolorB(JButton hover, Color rand) {
        hover.setBackground(rand);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel14 = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        iconmaxclose = new javax.swing.JPanel();
        buttonClose = new javax.swing.JPanel();
        close = new javax.swing.JLabel();
        buttonMax = new javax.swing.JPanel();
        max = new javax.swing.JLabel();
        menu = new javax.swing.JPanel();
        MenuIcon = new javax.swing.JPanel();
        linehidemenu = new javax.swing.JPanel();
        hidemenu = new javax.swing.JPanel();
        buttonhidemenu = new javax.swing.JLabel();
        lineSetting = new javax.swing.JPanel();
        setting = new javax.swing.JPanel();
        buttonLogout = new javax.swing.JLabel();
        menuhide = new javax.swing.JPanel();
        menuhide1 = new javax.swing.JPanel();
        hospitalDashboard = new javax.swing.JPanel();
        side1 = new javax.swing.JPanel();
        statisticslbl = new javax.swing.JLabel();
        statisticsimg = new javax.swing.JLabel();
        doctorOrg = new javax.swing.JPanel();
        side3 = new javax.swing.JPanel();
        manageCategorylbl = new javax.swing.JLabel();
        manageCategoryIcon = new javax.swing.JLabel();
        staffOrg = new javax.swing.JPanel();
        side4 = new javax.swing.JPanel();
        manageMedicinelbl = new javax.swing.JLabel();
        manageMedicineIcon = new javax.swing.JLabel();
        hospitalAdmin = new javax.swing.JTabbedPane();
        dashboard = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        patientList = new javax.swing.JScrollPane();
        patientTable = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        staffCombo = new UI.Components.Combobox();
        updatePatientBtn1 = new UI.Components.Button();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textAreaMsg = new javax.swing.JTextArea();
        doctorTab = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtDoctorName = new UI.Components.TextField();
        txtDoctorPassword = new UI.Components.TextField();
        clearDoctor = new UI.Components.Button();
        deleteDoctor = new UI.Components.Button();
        addDoctor = new UI.Components.Button();
        updateDoctor = new UI.Components.Button();
        crudDoctorSP = new javax.swing.JScrollPane();
        crudDoctorTable = new javax.swing.JTable();
        txtDoctorUsername = new UI.Components.TextField();
        searchPanel2 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        searchStaff1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        staffTab = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        staffName = new UI.Components.TextField();
        staffUsername = new UI.Components.TextField();
        staffPassword = new UI.Components.TextField();
        addStaff = new UI.Components.Button();
        deleteStaff = new UI.Components.Button();
        updateStaff = new UI.Components.Button();
        clearStaff = new UI.Components.Button();
        searchPanel1 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        searchStaff = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        crudStaffSP = new javax.swing.JScrollPane();
        crudStaffTable = new javax.swing.JTable();

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        header.setBackground(new java.awt.Color(27, 152, 245));
        header.setPreferredSize(new java.awt.Dimension(800, 50));
        header.setLayout(new java.awt.BorderLayout());

        iconmaxclose.setBackground(new java.awt.Color(22, 116, 66));

        buttonClose.setBackground(new java.awt.Color(27, 152, 245));

        close.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vcare/icon/delete_32px.png"))); // NOI18N
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closeMouseExited(evt);
            }
        });

        javax.swing.GroupLayout buttonCloseLayout = new javax.swing.GroupLayout(buttonClose);
        buttonClose.setLayout(buttonCloseLayout);
        buttonCloseLayout.setHorizontalGroup(
            buttonCloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonCloseLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(close, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
        );
        buttonCloseLayout.setVerticalGroup(
            buttonCloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(close, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        buttonMax.setBackground(new java.awt.Color(27, 152, 245));

        max.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        max.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vcare/icon/full_screen_32px.png"))); // NOI18N
        max.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                maxMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                maxMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                maxMouseExited(evt);
            }
        });

        javax.swing.GroupLayout buttonMaxLayout = new javax.swing.GroupLayout(buttonMax);
        buttonMax.setLayout(buttonMaxLayout);
        buttonMaxLayout.setHorizontalGroup(
            buttonMaxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonMaxLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(max, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
        );
        buttonMaxLayout.setVerticalGroup(
            buttonMaxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttonMaxLayout.createSequentialGroup()
                .addComponent(max, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout iconmaxcloseLayout = new javax.swing.GroupLayout(iconmaxclose);
        iconmaxclose.setLayout(iconmaxcloseLayout);
        iconmaxcloseLayout.setHorizontalGroup(
            iconmaxcloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, iconmaxcloseLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(buttonMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(buttonClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        iconmaxcloseLayout.setVerticalGroup(
            iconmaxcloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(iconmaxcloseLayout.createSequentialGroup()
                .addGroup(iconmaxcloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        header.add(iconmaxclose, java.awt.BorderLayout.LINE_END);

        getContentPane().add(header, java.awt.BorderLayout.PAGE_START);

        menu.setBackground(new java.awt.Color(51, 51, 51));
        menu.setPreferredSize(new java.awt.Dimension(270, 450));
        menu.setLayout(new java.awt.BorderLayout());

        MenuIcon.setBackground(new java.awt.Color(4, 16, 20));
        MenuIcon.setPreferredSize(new java.awt.Dimension(50, 450));
        MenuIcon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        linehidemenu.setBackground(new java.awt.Color(0, 0, 0));
        linehidemenu.setPreferredSize(new java.awt.Dimension(50, 5));

        javax.swing.GroupLayout linehidemenuLayout = new javax.swing.GroupLayout(linehidemenu);
        linehidemenu.setLayout(linehidemenuLayout);
        linehidemenuLayout.setHorizontalGroup(
            linehidemenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        linehidemenuLayout.setVerticalGroup(
            linehidemenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        MenuIcon.add(linehidemenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 5));

        hidemenu.setBackground(new java.awt.Color(4, 16, 20));
        hidemenu.setPreferredSize(new java.awt.Dimension(50, 50));
        hidemenu.setLayout(new java.awt.BorderLayout());

        buttonhidemenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buttonhidemenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vcare/icon/back_32px.png"))); // NOI18N
        buttonhidemenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonhidemenuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonhidemenuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonhidemenuMouseExited(evt);
            }
        });
        hidemenu.add(buttonhidemenu, java.awt.BorderLayout.CENTER);

        MenuIcon.add(hidemenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, 50, 50));

        lineSetting.setBackground(new java.awt.Color(0, 0, 0));
        lineSetting.setPreferredSize(new java.awt.Dimension(50, 5));

        javax.swing.GroupLayout lineSettingLayout = new javax.swing.GroupLayout(lineSetting);
        lineSetting.setLayout(lineSettingLayout);
        lineSettingLayout.setHorizontalGroup(
            lineSettingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        lineSettingLayout.setVerticalGroup(
            lineSettingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        MenuIcon.add(lineSetting, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 55, 50, 5));

        setting.setBackground(new java.awt.Color(4, 16, 20));
        setting.setPreferredSize(new java.awt.Dimension(50, 50));
        setting.setLayout(new java.awt.BorderLayout());

        buttonLogout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buttonLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vcare/icon/logout_30px.png"))); // NOI18N
        buttonLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonLogoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonLogoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonLogoutMouseExited(evt);
            }
        });
        setting.add(buttonLogout, java.awt.BorderLayout.CENTER);

        MenuIcon.add(setting, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 50, 50));

        menu.add(MenuIcon, java.awt.BorderLayout.LINE_START);

        menuhide.setBackground(new java.awt.Color(51, 51, 51));
        menuhide.setLayout(new java.awt.BorderLayout());

        menuhide1.setBackground(new java.awt.Color(0, 91, 149));

        hospitalDashboard.setBackground(new java.awt.Color(0, 91, 149));
        hospitalDashboard.setPreferredSize(new java.awt.Dimension(220, 50));
        hospitalDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hospitalDashboardMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hospitalDashboardMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hospitalDashboardMouseExited(evt);
            }
        });

        side1.setBackground(new java.awt.Color(0, 91, 149));
        side1.setPreferredSize(new java.awt.Dimension(5, 50));

        javax.swing.GroupLayout side1Layout = new javax.swing.GroupLayout(side1);
        side1.setLayout(side1Layout);
        side1Layout.setHorizontalGroup(
            side1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        side1Layout.setVerticalGroup(
            side1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        statisticslbl.setBackground(new java.awt.Color(51, 51, 51));
        statisticslbl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        statisticslbl.setForeground(new java.awt.Color(255, 255, 255));
        statisticslbl.setText("Dashboard");

        statisticsimg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statisticsimg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vcare/icon/statisticsW_40px.png"))); // NOI18N

        javax.swing.GroupLayout hospitalDashboardLayout = new javax.swing.GroupLayout(hospitalDashboard);
        hospitalDashboard.setLayout(hospitalDashboardLayout);
        hospitalDashboardLayout.setHorizontalGroup(
            hospitalDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hospitalDashboardLayout.createSequentialGroup()
                .addComponent(side1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statisticsimg, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(statisticslbl, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        hospitalDashboardLayout.setVerticalGroup(
            hospitalDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hospitalDashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statisticslbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(statisticsimg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(side1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        doctorOrg.setBackground(new java.awt.Color(0, 91, 149));
        doctorOrg.setPreferredSize(new java.awt.Dimension(220, 50));
        doctorOrg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                doctorOrgMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                doctorOrgMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                doctorOrgMouseExited(evt);
            }
        });

        side3.setBackground(new java.awt.Color(0, 91, 149));
        side3.setPreferredSize(new java.awt.Dimension(5, 50));

        javax.swing.GroupLayout side3Layout = new javax.swing.GroupLayout(side3);
        side3.setLayout(side3Layout);
        side3Layout.setHorizontalGroup(
            side3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        side3Layout.setVerticalGroup(
            side3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        manageCategorylbl.setBackground(new java.awt.Color(51, 51, 51));
        manageCategorylbl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manageCategorylbl.setForeground(new java.awt.Color(255, 255, 255));
        manageCategorylbl.setText("Doctor Organization");

        manageCategoryIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        manageCategoryIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vcare/icon/doctors_folder_40px.png"))); // NOI18N

        javax.swing.GroupLayout doctorOrgLayout = new javax.swing.GroupLayout(doctorOrg);
        doctorOrg.setLayout(doctorOrgLayout);
        doctorOrgLayout.setHorizontalGroup(
            doctorOrgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(doctorOrgLayout.createSequentialGroup()
                .addComponent(side3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(manageCategoryIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(manageCategorylbl, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        doctorOrgLayout.setVerticalGroup(
            doctorOrgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, doctorOrgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(manageCategorylbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(manageCategoryIcon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(side3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        staffOrg.setBackground(new java.awt.Color(0, 91, 149));
        staffOrg.setPreferredSize(new java.awt.Dimension(220, 50));
        staffOrg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                staffOrgMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                staffOrgMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                staffOrgMouseExited(evt);
            }
        });

        side4.setBackground(new java.awt.Color(0, 91, 149));
        side4.setPreferredSize(new java.awt.Dimension(5, 50));

        javax.swing.GroupLayout side4Layout = new javax.swing.GroupLayout(side4);
        side4.setLayout(side4Layout);
        side4Layout.setHorizontalGroup(
            side4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        side4Layout.setVerticalGroup(
            side4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        manageMedicinelbl.setBackground(new java.awt.Color(51, 51, 51));
        manageMedicinelbl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manageMedicinelbl.setForeground(new java.awt.Color(255, 255, 255));
        manageMedicinelbl.setText(" Staff Organization");

        manageMedicineIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        manageMedicineIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vcare/icon/member_40px.png"))); // NOI18N

        javax.swing.GroupLayout staffOrgLayout = new javax.swing.GroupLayout(staffOrg);
        staffOrg.setLayout(staffOrgLayout);
        staffOrgLayout.setHorizontalGroup(
            staffOrgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(staffOrgLayout.createSequentialGroup()
                .addComponent(side4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(manageMedicineIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(manageMedicinelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        staffOrgLayout.setVerticalGroup(
            staffOrgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(manageMedicineIcon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(side4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(manageMedicinelbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout menuhide1Layout = new javax.swing.GroupLayout(menuhide1);
        menuhide1.setLayout(menuhide1Layout);
        menuhide1Layout.setHorizontalGroup(
            menuhide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(hospitalDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(doctorOrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(staffOrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        menuhide1Layout.setVerticalGroup(
            menuhide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuhide1Layout.createSequentialGroup()
                .addComponent(hospitalDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(doctorOrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(staffOrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        menuhide.add(menuhide1, java.awt.BorderLayout.CENTER);

        menu.add(menuhide, java.awt.BorderLayout.CENTER);

        getContentPane().add(menu, java.awt.BorderLayout.LINE_START);

        hospitalAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hospitalAdminMouseClicked(evt);
            }
        });

        dashboard.setBackground(new java.awt.Color(217, 241, 255));
        dashboard.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(217, 241, 255));

        patientTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Patient Name", "Height", "Weight", "Respiratory Rate", "Pulse Rate", "Assigned Date", "Sender", "Status"
            }
        ));
        patientList.setViewportView(patientTable);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel4.setText("Assign A Staff");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel12.setText("Patient List");

        staffCombo.setLabeText("Select Staff");
        staffCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                staffComboActionPerformed(evt);
            }
        });

        updatePatientBtn1.setBackground(new java.awt.Color(0, 91, 149));
        updatePatientBtn1.setForeground(new java.awt.Color(255, 255, 255));
        updatePatientBtn1.setText("Send Request");
        updatePatientBtn1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        updatePatientBtn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                updatePatientBtn1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                updatePatientBtn1MouseExited(evt);
            }
        });
        updatePatientBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatePatientBtn1ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel6.setText("Message");

        textAreaMsg.setColumns(20);
        textAreaMsg.setRows(5);
        jScrollPane2.setViewportView(textAreaMsg);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(patientList)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(198, 198, 198)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(staffCombo, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGap(150, 150, 150))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(287, 287, 287)
                .addComponent(updatePatientBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(patientList, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(staffCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addComponent(updatePatientBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
        );

        dashboard.add(jPanel1, java.awt.BorderLayout.CENTER);

        hospitalAdmin.addTab("Dashboard", dashboard);

        doctorTab.setBackground(new java.awt.Color(255, 255, 255));
        doctorTab.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(217, 241, 255));

        txtDoctorName.setLabelText("Full Name");
        txtDoctorName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDoctorNameActionPerformed(evt);
            }
        });

        txtDoctorPassword.setLabelText("Password");

        clearDoctor.setBackground(new java.awt.Color(0, 91, 149));
        clearDoctor.setForeground(new java.awt.Color(255, 255, 255));
        clearDoctor.setText("CLEAR");
        clearDoctor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        clearDoctor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                clearDoctorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                clearDoctorMouseExited(evt);
            }
        });
        clearDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearDoctorActionPerformed(evt);
            }
        });

        deleteDoctor.setBackground(new java.awt.Color(0, 91, 149));
        deleteDoctor.setForeground(new java.awt.Color(255, 255, 255));
        deleteDoctor.setText("DELETE");
        deleteDoctor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        deleteDoctor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                deleteDoctorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                deleteDoctorMouseExited(evt);
            }
        });
        deleteDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteDoctorActionPerformed(evt);
            }
        });

        addDoctor.setBackground(new java.awt.Color(0, 91, 149));
        addDoctor.setForeground(new java.awt.Color(255, 255, 255));
        addDoctor.setText("ADD");
        addDoctor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        addDoctor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addDoctorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addDoctorMouseExited(evt);
            }
        });
        addDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDoctorActionPerformed(evt);
            }
        });

        updateDoctor.setBackground(new java.awt.Color(0, 91, 149));
        updateDoctor.setForeground(new java.awt.Color(255, 255, 255));
        updateDoctor.setText("UPDATE");
        updateDoctor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        updateDoctor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                updateDoctorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                updateDoctorMouseExited(evt);
            }
        });
        updateDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateDoctorActionPerformed(evt);
            }
        });

        crudDoctorTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Doctor Name", "Username", "Password"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        crudDoctorTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                crudDoctorTableMouseClicked(evt);
            }
        });
        crudDoctorSP.setViewportView(crudDoctorTable);

        txtDoctorUsername.setLabelText("Username");
        txtDoctorUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDoctorUsernameActionPerformed(evt);
            }
        });

        searchPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vcare/icon/search_30px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGap(0, 10, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vcare/icon/print_30px.png"))); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout searchPanel2Layout = new javax.swing.GroupLayout(searchPanel2);
        searchPanel2.setLayout(searchPanel2Layout);
        searchPanel2Layout.setHorizontalGroup(
            searchPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPanel2Layout.createSequentialGroup()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchStaff1)
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        searchPanel2Layout.setVerticalGroup(
            searchPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(searchStaff1)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(462, 462, 462)
                                .addComponent(updateDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(104, 104, 104)
                                .addComponent(clearDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDoctorName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(295, 295, 295)
                                        .addComponent(txtDoctorUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(81, 81, 81)
                                        .addComponent(txtDoctorPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(addDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(88, 88, 88)
                                        .addComponent(deleteDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 77, Short.MAX_VALUE))
                    .addComponent(crudDoctorSP, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(searchPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(87, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDoctorName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDoctorPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDoctorUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(117, 117, 117)
                .addComponent(crudDoctorSP, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(312, 312, 312)
                    .addComponent(searchPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(313, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        doctorTab.add(jPanel3, java.awt.BorderLayout.CENTER);

        hospitalAdmin.addTab("Doctor Organization", doctorTab);

        staffTab.setBackground(new java.awt.Color(255, 255, 255));
        staffTab.setLayout(new java.awt.BorderLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(217, 241, 255));

        jPanel10.setBackground(new java.awt.Color(217, 241, 255));

        staffName.setLabelText("Full Name");
        staffName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                staffNameActionPerformed(evt);
            }
        });

        staffUsername.setLabelText("Username");
        staffUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                staffUsernameActionPerformed(evt);
            }
        });

        staffPassword.setLabelText("Password");

        addStaff.setBackground(new java.awt.Color(0, 91, 149));
        addStaff.setForeground(new java.awt.Color(255, 255, 255));
        addStaff.setText("ADD");
        addStaff.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        addStaff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addStaffMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addStaffMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addStaffMouseExited(evt);
            }
        });
        addStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStaffActionPerformed(evt);
            }
        });

        deleteStaff.setBackground(new java.awt.Color(0, 91, 149));
        deleteStaff.setForeground(new java.awt.Color(255, 255, 255));
        deleteStaff.setText("DELETE");
        deleteStaff.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        deleteStaff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                deleteStaffMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                deleteStaffMouseExited(evt);
            }
        });
        deleteStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteStaffActionPerformed(evt);
            }
        });

        updateStaff.setBackground(new java.awt.Color(0, 91, 149));
        updateStaff.setForeground(new java.awt.Color(255, 255, 255));
        updateStaff.setText("UPDATE");
        updateStaff.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        updateStaff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                updateStaffMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                updateStaffMouseExited(evt);
            }
        });
        updateStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateStaffActionPerformed(evt);
            }
        });

        clearStaff.setBackground(new java.awt.Color(0, 91, 149));
        clearStaff.setForeground(new java.awt.Color(255, 255, 255));
        clearStaff.setText("CLEAR");
        clearStaff.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        clearStaff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                clearStaffMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                clearStaffMouseExited(evt);
            }
        });
        clearStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearStaffActionPerformed(evt);
            }
        });

        searchPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vcare/icon/search_30px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(0, 10, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vcare/icon/print_30px.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout searchPanel1Layout = new javax.swing.GroupLayout(searchPanel1);
        searchPanel1.setLayout(searchPanel1Layout);
        searchPanel1Layout.setHorizontalGroup(
            searchPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPanel1Layout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchStaff)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        searchPanel1Layout.setVerticalGroup(
            searchPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(searchStaff)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        crudStaffTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Name", "Username", "Password"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        crudStaffTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                crudStaffTableMouseClicked(evt);
            }
        });
        crudStaffSP.setViewportView(crudStaffTable);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(staffName, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(addStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91)
                        .addComponent(deleteStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(updateStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(staffUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(staffPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(51, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(clearStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85))))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crudStaffSP))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(staffName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(staffUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(staffPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(83, 83, 83)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addComponent(searchPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(crudStaffSP, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        staffTab.add(jPanel7, java.awt.BorderLayout.CENTER);

        hospitalAdmin.addTab("Staff Organization", staffTab);

        getContentPane().add(hospitalAdmin, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void maxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_maxMouseClicked
        if (maximized) {
            HospitalAdmin.this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            HospitalAdmin.this.setMaximizedBounds(env.getMaximumWindowBounds());
            maximized = false;
        } else {
            setExtendedState(JFrame.NORMAL);
            maximized = true;
        }
    }//GEN-LAST:event_maxMouseClicked

    private void maxMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_maxMouseEntered
        changecolor(buttonMax, new Color(3, 138, 255));
    }//GEN-LAST:event_maxMouseEntered

    private void maxMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_maxMouseExited
        changecolor(buttonMax, new Color(27, 152, 245));
    }//GEN-LAST:event_maxMouseExited

    private void buttonhidemenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonhidemenuMouseClicked
        clickmenu(hidemenu, setting, 1);
        if (a == true) {
            hideshow(menu, a, buttonhidemenu);
            SwingUtilities.updateComponentTreeUI(this);
            a = false;
        } else {
            hideshow(menu, a, buttonhidemenu);
            SwingUtilities.updateComponentTreeUI(this);
            a = true;
        }
//        pharmacyAdmin.setSelectedIndex(0);
    }//GEN-LAST:event_buttonhidemenuMouseClicked

    private void buttonhidemenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonhidemenuMouseEntered
        changecolor(linehidemenu, new Color(190, 224, 236));

    }//GEN-LAST:event_buttonhidemenuMouseEntered

    private void buttonhidemenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonhidemenuMouseExited
        changecolor(linehidemenu, new Color(4, 16, 20));
    }//GEN-LAST:event_buttonhidemenuMouseExited

    private void buttonLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonLogoutMouseClicked
        clickmenu(setting, hidemenu, 1);
        int a = JOptionPane.showConfirmDialog(this, "Do you want to logout?", "Select", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            this.setVisible(false);
            parentFrame.setVisible(true);
        }
    }//GEN-LAST:event_buttonLogoutMouseClicked

    private void buttonLogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonLogoutMouseEntered
        changecolor(lineSetting, new Color(190, 224, 236));
    }//GEN-LAST:event_buttonLogoutMouseEntered

    private void buttonLogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonLogoutMouseExited
        changecolor(lineSetting, new Color(4, 16, 20));
    }//GEN-LAST:event_buttonLogoutMouseExited

    private void hospitalDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hospitalDashboardMouseClicked
        hospitalAdmin.setSelectedIndex(0);
        changecolor(hospitalDashboard, new Color(3, 138, 255));
        changecolor(side1, new Color(190, 224, 236));
    }//GEN-LAST:event_hospitalDashboardMouseClicked

    private void hospitalDashboardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hospitalDashboardMouseEntered
        changecolor(hospitalDashboard, new Color(3, 138, 255));
        changecolor(side1, new Color(190, 224, 236));
    }//GEN-LAST:event_hospitalDashboardMouseEntered

    private void hospitalDashboardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hospitalDashboardMouseExited
        changecolor(hospitalDashboard, new Color(0, 91, 149));
        changecolor(side1, new Color(0, 91, 149));
    }//GEN-LAST:event_hospitalDashboardMouseExited

    private void doctorOrgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_doctorOrgMouseClicked
        hospitalAdmin.setSelectedIndex(2);
        changecolor(doctorOrg, new Color(3, 138, 255));
        changecolor(side3, new Color(190, 224, 236));
    }//GEN-LAST:event_doctorOrgMouseClicked

    private void doctorOrgMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_doctorOrgMouseEntered
        changecolor(doctorOrg, new Color(3, 138, 255));
        changecolor(side3, new Color(190, 224, 236));
    }//GEN-LAST:event_doctorOrgMouseEntered

    private void doctorOrgMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_doctorOrgMouseExited
        changecolor(doctorOrg, new Color(0, 91, 149));
        changecolor(side3, new Color(0, 91, 149));
    }//GEN-LAST:event_doctorOrgMouseExited

    private void staffOrgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staffOrgMouseClicked
        hospitalAdmin.setSelectedIndex(0);
        changecolor(staffOrg, new Color(3, 138, 255));
        changecolor(side4, new Color(190, 224, 236));
    }//GEN-LAST:event_staffOrgMouseClicked

    private void staffOrgMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staffOrgMouseEntered
        changecolor(staffOrg, new Color(3, 138, 255));
        changecolor(side4, new Color(190, 224, 236));
    }//GEN-LAST:event_staffOrgMouseEntered

    private void staffOrgMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staffOrgMouseExited
        changecolor(staffOrg, new Color(0, 91, 149));
        changecolor(side4, new Color(0, 91, 149));
    }//GEN-LAST:event_staffOrgMouseExited

    private void closeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseExited
        changecolor(buttonClose, new Color(27, 152, 245));
    }//GEN-LAST:event_closeMouseExited

    private void closeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseEntered
        changecolor(buttonClose, new Color(3, 138, 255));
    }//GEN-LAST:event_closeMouseEntered

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        this.setVisible(false);
        parentFrame.setVisible(true);
    }//GEN-LAST:event_closeMouseClicked

    private void hospitalAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hospitalAdminMouseClicked
        // TODO add your handling code here:
        int index = hospitalAdmin.getSelectedIndex();
        switch (index) {
            case 0:
                populateStaff();
                break;
            case 1:
                 populateDoctorTable();
                   break;
            case 2:
               populateStaffTable();     //     populateEnterpriseName(network, cEntName);
               
                break;
            default:
                throw new AssertionError();
        }
    }//GEN-LAST:event_hospitalAdminMouseClicked

    private void updatePatientBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatePatientBtn1ActionPerformed

        int selectedrow = patientTable.getSelectedRow();
        if (selectedrow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row");

        }
        try {

            String message = textAreaMsg.getText();

            if (message.equals("") || message.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter something to send.");
                return;
            }

            StaffWorkRequest staffRequest = new StaffWorkRequest();

            staffRequest.setPatient((Tenant) this.tenantList.get(selectedrow));

            DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            Date date = new Date();
            staffRequest.setStatus("Patient Assigned");
            Employee emp = (Employee) staffCombo.getSelectedItem();
            staffRequest.setReceiver(getUser(emp.getId()));

            Organization org = null;
            for (Organization orn : hEnterPrise.getOrganizationDirectory().getOrganizationList()) {
                if (orn instanceof HospitalStaffOrganization) {
                    org = orn;
                    break;
                }
            }

            if (org != null) {
                org.getWorkQueue().getWorkRequestList().add(staffRequest);
                account.getWorkQueue().getWorkRequestList().add(staffRequest);
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_updatePatientBtn1ActionPerformed

    private void updatePatientBtn1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updatePatientBtn1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_updatePatientBtn1MouseExited

    private void updatePatientBtn1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updatePatientBtn1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_updatePatientBtn1MouseEntered

    private void staffComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_staffComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_staffComboActionPerformed

    private void txtDoctorUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDoctorUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDoctorUsernameActionPerformed

    private void crudDoctorTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_crudDoctorTableMouseClicked
        // TODO add your handling code here:

        int selectedRowIndex = crudDoctorTable.getSelectedRow();
        if (selectedRowIndex < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row to view");
            return;
        }
        DefaultTableModel model = (DefaultTableModel) crudDoctorTable.getModel();
        //          UserAccount user = (UserAccount)model.getValueAt(selectedRowInde);
        //UserAccount user = docOrg.getUserAccountDirectory().getUserAccountList().get(selectedRowIndex);
        for (Organization org : hEnterPrise.getOrganizationDirectory().getOrganizationList()) {
            if (org.getName() == Organization.Type.Doctor.getValue()) {
                UserAccount user = org.getUserAccountDirectory().getUserAccountList().get(selectedRowIndex);
                txtDoctorName.setText(user.getEmployee().getName());
                txtDoctorUsername.setText(user.getUsername());
                txtDoctorPassword.setText(user.getPassword());
            }
        }

    }//GEN-LAST:event_crudDoctorTableMouseClicked

    private void updateDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateDoctorActionPerformed

        int selectedRowIndex = crudDoctorTable.getSelectedRow();
        if (selectedRowIndex < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row to update!");
            return;
        }

        String username = txtDoctorUsername.getText();

        String password = txtDoctorPassword.getText();//String.valueOf(passwordCharArray);
        String name = txtDoctorName.getText();
        for (Organization org : hEnterPrise.getOrganizationDirectory().getOrganizationList()) {
            if (org.getName() == Organization.Type.Doctor.getValue()) {
                Employee employee = org.getEmployeeDirectory().getEmployeeList().get(selectedRowIndex);
                System.out.println("employee-" + employee.getName());

                    employee.setName(name);
                    UserAccount user = org.getUserAccountDirectory().getUserAccountList().get(selectedRowIndex);
                    System.out.println("uswr--"+ user.getPassword());
                    user.setEmployee(employee);
                    user.setPassword(password);
                    user.setUsername(username);
                     System.out.println("uswr updayed--"+ user.getPassword());
                    clearDoctor();
                    this.populateDoctorTable();
                    JOptionPane.showMessageDialog(this, "Doctor Updated Successfully!");
                   

               

            }
        }
    }//GEN-LAST:event_updateDoctorActionPerformed

    private void updateDoctorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateDoctorMouseExited
        changecolorB(updateDoctor, new Color(0, 91, 149));
    }//GEN-LAST:event_updateDoctorMouseExited

    private void updateDoctorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateDoctorMouseEntered
        changecolorB(updateDoctor, new Color(3, 138, 255));
    }//GEN-LAST:event_updateDoctorMouseEntered

    private void addDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDoctorActionPerformed
         if (txtDoctorName.getText().equals("")){
             JOptionPane.showMessageDialog(null,"Please enter Doctor Fullname");
             return;
         }
         else  if(txtDoctorPassword.getText().length()>25||txtDoctorPassword.getText().length()<5) {
             JOptionPane.showMessageDialog(null,"Please enter Valid Password");
             return;
         }
         else if (txtDoctorName.getText().equals("")){
             JOptionPane.showMessageDialog(null,"Do not enter an empty password!!!");
             return;
         } else if(txtDoctorUsername.getText().equals("")) {
             JOptionPane.showMessageDialog(null,"Please enter Doctor UserName");
             return;
         }
         else{
        String username = txtDoctorUsername.getText();
        //char[] passwordCharArray = doctorPassword.getPassword();

        String password = txtDoctorPassword.getText();//String.valueOf(passwordCharArray);
        String name = txtDoctorName.getText();

        for (Organization org : hEnterPrise.getOrganizationDirectory().getOrganizationList()) {
            if (org.getName() == Organization.Type.Doctor.getValue()) {
                Employee employee = org.getEmployeeDirectory().createEmployee(name);
                if (EcoSystem.isUserUnique(username)) {

                    org.getUserAccountDirectory().createUserAccount(username, password, employee, new DoctorRole());
                    JOptionPane.showMessageDialog(this, "Doctor Added Successfully!");
                    clearDoctor();
                    this.populateDoctorTable();

                } else {
                    JOptionPane.showMessageDialog(this, "Please give a unique username!");
                    clearDoctor();

                }

            }
        }
         }
    }//GEN-LAST:event_addDoctorActionPerformed

    private void addDoctorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addDoctorMouseExited
        changecolorB(addDoctor, new Color(0, 91, 149));
    }//GEN-LAST:event_addDoctorMouseExited

    private void addDoctorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addDoctorMouseEntered
        changecolorB(addDoctor, new Color(3, 138, 255));
    }//GEN-LAST:event_addDoctorMouseEntered

    private void deleteDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteDoctorActionPerformed
        int selectedRowIndex = crudDoctorTable.getSelectedRow();
        if (selectedRowIndex < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete");
            return;
        }
        DefaultTableModel model = (DefaultTableModel) crudDoctorTable.getModel();
        for (Organization org : hEnterPrise.getOrganizationDirectory().getOrganizationList()) {
            if (org.getName() == Organization.Type.Doctor.getValue()) {
                org.getUserAccountDirectory().getUserAccountList().remove(selectedRowIndex);

            }
        }

        JOptionPane.showMessageDialog(this, "Doctor Deleted Successfully!");
        clearDoctor();
        this.populateDoctorTable();
    }//GEN-LAST:event_deleteDoctorActionPerformed

    private void deleteDoctorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteDoctorMouseExited
        changecolorB(deleteDoctor, new Color(0, 91, 149));
    }//GEN-LAST:event_deleteDoctorMouseExited

    private void deleteDoctorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteDoctorMouseEntered
        changecolorB(deleteDoctor, new Color(3, 138, 255));
    }//GEN-LAST:event_deleteDoctorMouseEntered

    private void clearDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearDoctorActionPerformed
        clearDoctor();
    }//GEN-LAST:event_clearDoctorActionPerformed

    private void clearDoctorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearDoctorMouseExited
        changecolorB(clearDoctor, new Color(0, 91, 149));
    }//GEN-LAST:event_clearDoctorMouseExited

    private void clearDoctorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearDoctorMouseEntered
        changecolorB(clearDoctor, new Color(3, 138, 255));
    }//GEN-LAST:event_clearDoctorMouseEntered

    private void txtDoctorNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDoctorNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDoctorNameActionPerformed

    private void crudStaffTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_crudStaffTableMouseClicked
        // TODO add your handling code here:

        int selectedRowIndex = crudStaffTable.getSelectedRow();
        if (selectedRowIndex < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row to view");
            return;
        }
        DefaultTableModel model = (DefaultTableModel) crudStaffTable.getModel();
        for (Organization org : hEnterPrise.getOrganizationDirectory().getOrganizationList()) {
            if (org.getName() == Organization.Type.HospitalStaff.getValue()) {
                System.out.println("inside----");
                UserAccount user = org.getUserAccountDirectory().getUserAccountList().get(selectedRowIndex);
                staffName.setText(user.getEmployee().getName());
                staffUsername.setText(user.getUsername());
                staffPassword.setText(user.getPassword());
            }
        }
        
        
    }//GEN-LAST:event_crudStaffTableMouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel3MouseClicked

    private void clearStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearStaffActionPerformed
        clearStaff();
    }//GEN-LAST:event_clearStaffActionPerformed

    private void clearStaffMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearStaffMouseExited
        changecolorB(clearStaff, new Color(0, 91, 149));
    }//GEN-LAST:event_clearStaffMouseExited

    private void clearStaffMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearStaffMouseEntered
        changecolorB(clearStaff, new Color(3, 138, 255));
    }//GEN-LAST:event_clearStaffMouseEntered

    private void updateStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateStaffActionPerformed

        int selectedRowIndex = crudStaffTable.getSelectedRow();
        if (selectedRowIndex < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row to update");
            return;
        }

        String username = staffUsername.getText();

        String password = staffPassword.getText();//String.valueOf(passwordCharArray);
        String name = staffName.getText();

        for (Organization org : hEnterPrise.getOrganizationDirectory().getOrganizationList()) {
            if (org.getName() == Organization.Type.HospitalStaff.getValue()) {
                Employee employee = org.getEmployeeDirectory().getEmployeeList().get(selectedRowIndex);

                    employee.setName(name);
                    UserAccount user = org.getUserAccountDirectory().getUserAccountList().get(selectedRowIndex);
                    user.setEmployee(employee);
                    user.setPassword(password);
                    user.setUsername(username);
                    clearDoctor();
                    this.populateDoctorTable();
                    JOptionPane.showMessageDialog(this, "Staff Updated Successfully!");
                    

                

            }
        }
    }//GEN-LAST:event_updateStaffActionPerformed

    private void updateStaffMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateStaffMouseExited
        changecolorB(updateStaff, new Color(0, 91, 149));
    }//GEN-LAST:event_updateStaffMouseExited

    private void updateStaffMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateStaffMouseEntered
        changecolorB(updateStaff, new Color(3, 138, 255));
    }//GEN-LAST:event_updateStaffMouseEntered

    private void deleteStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteStaffActionPerformed

        int selectedRowIndex = crudStaffTable.getSelectedRow();
        if (selectedRowIndex < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete");
            return;
        }
        DefaultTableModel model = (DefaultTableModel) crudStaffTable.getModel();

        for (Organization org : hEnterPrise.getOrganizationDirectory().getOrganizationList()) {
            if (org.getName() == Organization.Type.HospitalStaff.getValue()) {
                org.getUserAccountDirectory().getUserAccountList().remove(selectedRowIndex);

            }
        }
        JOptionPane.showMessageDialog(this, "Staff Added Successfully!");
        clearStaff();
        this.populateStaffTable();
    }//GEN-LAST:event_deleteStaffActionPerformed

    private void deleteStaffMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteStaffMouseExited
        changecolorB(deleteStaff, new Color(0, 91, 149));
    }//GEN-LAST:event_deleteStaffMouseExited

    private void deleteStaffMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteStaffMouseEntered
        changecolorB(deleteStaff, new Color(3, 138, 255));
    }//GEN-LAST:event_deleteStaffMouseEntered

    private void addStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStaffActionPerformed
         if (staffUsername.getText().equals("")){
             JOptionPane.showMessageDialog(null,"Please enter Staff Username");
             return;
         }
         else  if(staffPassword.getText().length()>25||staffPassword.getText().length()<5) {
             JOptionPane.showMessageDialog(null,"Please enter Valid Password");
             return;
         }
         else if (staffPassword.getText().equals("")){
             JOptionPane.showMessageDialog(null,"Do not enter an empty password!!!");
             return;
         } else if(staffName.getText().equals("")) {
             JOptionPane.showMessageDialog(null,"Please enter Staff Full Name");
             return;
         }
         else{
        String username = staffUsername.getText();
        //        char[] passwordCharArray = doctorPassword.getPassword();

        String password = staffPassword.getText();//String.valueOf(passwordCharArray);
        String name = staffName.getText();

        for (Organization org : hEnterPrise.getOrganizationDirectory().getOrganizationList()) {
            if (org.getName() == Organization.Type.HospitalStaff.getValue()) {
                Employee employee = org.getEmployeeDirectory().createEmployee(name);
                if (EcoSystem.isUserUnique(username)) {

                    org.getUserAccountDirectory().createUserAccount(username, password, employee, new StaffRole());
                    JOptionPane.showMessageDialog(this, "Staff Added Successfully!");
                    clearStaff();
                    this.populateStaffTable();

                } else {

                    JOptionPane.showMessageDialog(this, "Please enter unique username!");
                    clearStaff();
                    return;
                }

            }
        }
         }
    }//GEN-LAST:event_addStaffActionPerformed

    private void addStaffMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addStaffMouseExited
        changecolorB(addStaff, new Color(0, 91, 149));
    }//GEN-LAST:event_addStaffMouseExited

    private void addStaffMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addStaffMouseEntered
        changecolorB(addStaff, new Color(3, 138, 255));
    }//GEN-LAST:event_addStaffMouseEntered

    private void addStaffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addStaffMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_addStaffMouseClicked

    private void staffUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_staffUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_staffUsernameActionPerformed

    private void staffNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_staffNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_staffNameActionPerformed

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel8MouseClicked
    private void clearDoctor() {
        txtDoctorName.setText("");
        txtDoctorUsername.setText("");
        txtDoctorPassword.setText("");
    }

    private void clearStaff() {

        staffName.setText("");
        staffUsername.setText("");
        staffPassword.setText("");

    }

    public UserAccount getUser(String empId) {
        for (UserAccount user : organization.getUserAccountDirectory().getUserAccountList()) {
            if (user.getEmployee().getId() == empId) {
                return user;
            }
        }
        return null;
    }

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
            java.util.logging.Logger.getLogger(HospitalAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HospitalAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HospitalAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HospitalAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new HospitalAdmin().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MenuIcon;
    private UI.Components.Button addDoctor;
    private UI.Components.Button addStaff;
    private javax.swing.JPanel buttonClose;
    private javax.swing.JLabel buttonLogout;
    private javax.swing.JPanel buttonMax;
    private javax.swing.JLabel buttonhidemenu;
    private UI.Components.Button clearDoctor;
    private UI.Components.Button clearStaff;
    private javax.swing.JLabel close;
    private javax.swing.JScrollPane crudDoctorSP;
    private javax.swing.JTable crudDoctorTable;
    private javax.swing.JScrollPane crudStaffSP;
    private javax.swing.JTable crudStaffTable;
    private javax.swing.JPanel dashboard;
    private UI.Components.Button deleteDoctor;
    private UI.Components.Button deleteStaff;
    private javax.swing.JPanel doctorOrg;
    private javax.swing.JPanel doctorTab;
    private javax.swing.JPanel header;
    private javax.swing.JPanel hidemenu;
    private javax.swing.JTabbedPane hospitalAdmin;
    private javax.swing.JPanel hospitalDashboard;
    private javax.swing.JPanel iconmaxclose;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel lineSetting;
    private javax.swing.JPanel linehidemenu;
    private javax.swing.JLabel manageCategoryIcon;
    private javax.swing.JLabel manageCategorylbl;
    private javax.swing.JLabel manageMedicineIcon;
    private javax.swing.JLabel manageMedicinelbl;
    private javax.swing.JLabel max;
    private javax.swing.JPanel menu;
    private javax.swing.JPanel menuhide;
    private javax.swing.JPanel menuhide1;
    private javax.swing.JScrollPane patientList;
    private javax.swing.JTable patientTable;
    private javax.swing.JPanel searchPanel1;
    private javax.swing.JPanel searchPanel2;
    private javax.swing.JTextField searchStaff;
    private javax.swing.JTextField searchStaff1;
    private javax.swing.JPanel setting;
    private javax.swing.JPanel side1;
    private javax.swing.JPanel side3;
    private javax.swing.JPanel side4;
    private UI.Components.Combobox staffCombo;
    private UI.Components.TextField staffName;
    private javax.swing.JPanel staffOrg;
    private UI.Components.TextField staffPassword;
    private javax.swing.JPanel staffTab;
    private UI.Components.TextField staffUsername;
    private javax.swing.JLabel statisticsimg;
    private javax.swing.JLabel statisticslbl;
    private javax.swing.JTextArea textAreaMsg;
    private UI.Components.TextField txtDoctorName;
    private UI.Components.TextField txtDoctorPassword;
    private UI.Components.TextField txtDoctorUsername;
    private UI.Components.Button updateDoctor;
    private UI.Components.Button updatePatientBtn1;
    private UI.Components.Button updateStaff;
    // End of variables declaration//GEN-END:variables
}
