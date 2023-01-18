
package UI.VolunteerAdmin;


import Business.Community.Community;
import Business.Community.House;
import Business.Community.HouseList;
import Business.Community.Tenant;
import Business.EcoSystem;
import Business.Employee.Employee;
import Business.Enterprise.Enterprise;
import Business.Enterprise.EnterpriseDirectory;
import Business.Enterprise.HospitalEnterprise;
import Business.Enterprise.VolunteerEnterprise;
import Business.Network.Network;
import Business.Organization.HomeCareVolunteerOrganization;
import Business.Organization.Organization;
import Business.Organization.SurveyVolunteerOrganization;
import Business.Role.HomeCareVolunteerRole;
import Business.Role.SurveyVolunteerRole;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.HomeCareVolunteerWorkRequest;
import Business.WorkQueue.SurveyVolunteerWorkRequest;
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
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class VolunteerAdmin extends javax.swing.JFrame {

    boolean a = true;
    static boolean maximized = true;
    private JFrame userProcessContainer;
    JFrame parentFrame;
    VolunteerEnterprise vEnterPrise;
    Organization organization;
    UserAccount account;
    Network network;
    Enterprise enterpriseD;
    public VolunteerAdmin(UserAccount account, 
            Organization organization, 
            Enterprise enterprise, 
            EcoSystem business,JFrame parentFrame) {
          initComponents();
          vEnterPrise = (VolunteerEnterprise) enterprise;
          this.organization = organization;
          this.parentFrame = parentFrame;
          this.enterpriseD = enterprise;
          this.account = account;
          network = business.getNetworkList().get(0);
          vAdminTab.setSelectedIndex(0);
          vAdminTask.setSelectedIndex(0);
    }
    //Method to change panel color on hover
 
    
    public void populateVolunteerEnterpriseOrganizations() {
        vOrganizationsCombo.removeAllItems();
        
        for (Organization org : vEnterPrise.getOrganizationDirectory().getOrganizationList()){
            vOrganizationsCombo.addItem(org);
        }
    }
    
    public void populateSurveyVolunteers() {
        sVolunteerCombo.removeAllItems();
        
        for (Organization org : vEnterPrise.getOrganizationDirectory().getOrganizationList()){
            if (org.getName() == Organization.Type.SurveyVolunteer.getValue()) {
                for(UserAccount account : org.getUserAccountDirectory().getUserAccountList()) {
                    sVolunteerCombo.addItem(account.getEmployee());
                }
            }
        }
      
    }
    
    public void populateHomeCareVolunteers() {
        hClistCombo.removeAllItems();
        
        for (Organization org : vEnterPrise.getOrganizationDirectory().getOrganizationList()){
            if (org.getName() == null ? Organization.Type.HomeCareVolunteer.getValue() == null : org.getName().equals(Organization.Type.HomeCareVolunteer.getValue())) {
                for(UserAccount account : org.getUserAccountDirectory().getUserAccountList()) {
                    hClistCombo.addItem(account.getEmployee());
                }
            }
        }
      
    }
    
    public void populateCommunities() {
        sVCommunity.removeAllItems();
        
        for (Community com : network.getCommunityDirectory().getCommunityList()){
            sVCommunity.addItem(com);
        }
      
    }
    
    public void populateHouses() {
        sVHouse.removeAllItems();
        Community com = (Community) sVCommunity.getSelectedItem();
        if (com != null) {
        for (House house : com.houselist.getHouses()){
            sVHouse.addItem(house);
        }
        }
    }
    
    
    public void populateCommunitiesHC() {
        sVCommCombo.removeAllItems();
        
        for (Community com : network.getCommunityDirectory().getCommunityList()){
            sVCommCombo.addItem(com);
        }
      
    }
    
    public void populateHousesHC() {
        if (sVCommCombo.getSelectedIndex() > 0) {
        sVHousCombo.removeAllItems();
        Community com = (Community) sVCommCombo.getSelectedItem();
        for (House house : com.getHouse().getHouses()){
            sVHousCombo.addItem(house);
        }
        }
      
    }
   
    
    public void populateTenantInfo(House house) {
        try{
             DefaultTableModel model = (DefaultTableModel) svWorkRTenantTable.getModel();

             model.setRowCount(0);
            for(WorkRequest wq: organization.getWorkQueue().getWorkRequestList()) {
            if(wq instanceof SurveyVolunteerWorkRequest)
            {
              
             House hous = ((SurveyVolunteerWorkRequest) wq).getAssignedHouse();
             for(Tenant tenant: hous.getTenats().getTenants()) {
                  
            Object[] row = new Object[8];
            row[0] = tenant.getFirstName();
            row[1] = tenant.getLastName();
            row[2] = tenant.getAge();
            row[3] = tenant.getGender();
            row[4] = tenant.report.getSicknessType();
            row[5] = tenant.report.isNeedHospitalization();
                model.addRow(row); 
          }
            }
        }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "system is down please contact system admin");
        }
        
    }
    
    public void changecolor(JPanel hover, Color rand) {
        hover.setBackground(rand);
    }

    //Method to change Background color of the panel 
    public void clickmenu(JPanel h1, JPanel h2, int numberbool) {
        if (numberbool == 1) {
            h1.setBackground(new Color(42,58,73));
            h2.setBackground(new Color(4,16,20));

        } else {
            h1.setBackground(new Color(4,16,20));
            h2.setBackground(new Color(42,58,73));
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
        vDashboard = new javax.swing.JPanel();
        side1 = new javax.swing.JPanel();
        statisticslbl = new javax.swing.JLabel();
        statisticsimg = new javax.swing.JLabel();
        manageVolunteers = new javax.swing.JPanel();
        side6 = new javax.swing.JPanel();
        manageMedicinelbl1 = new javax.swing.JLabel();
        manageMedicineIcon1 = new javax.swing.JLabel();
        vSurvey = new javax.swing.JPanel();
        side7 = new javax.swing.JPanel();
        manageMedicinelbl2 = new javax.swing.JLabel();
        manageMedicineIcon2 = new javax.swing.JLabel();
        hcvTasks = new javax.swing.JPanel();
        side5 = new javax.swing.JPanel();
        manageSupplierlbl = new javax.swing.JLabel();
        manageSupplierIcon = new javax.swing.JLabel();
        vAdminTab = new javax.swing.JTabbedPane();
        dashboard = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        addVolunteer = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        vOrganizationsCombo = new UI.Components.Combobox();
        vOrgUN = new UI.Components.MyTextFieldLogin();
        vOrgUName = new UI.Components.MyTextFieldLogin();
        createVolunteer = new UI.Components.Button();
        vOrgPW = new UI.Components.MyPasswordFieldLogin();
        manageTask = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        vAdminTask = new javax.swing.JTabbedPane();
        jPanel23 = new javax.swing.JPanel();
        sVolunteerCombo = new UI.Components.Combobox();
        sVCommunity = new UI.Components.Combobox();
        sVHouse = new UI.Components.Combobox();
        sVDate = new UI.Components.MyTextFieldLogin();
        button2 = new UI.Components.Button();
        jPanel24 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        sVCommCombo = new UI.Components.Combobox();
        sVHousCombo = new UI.Components.Combobox();
        jScrollPane7 = new javax.swing.JScrollPane();
        svWorkRTenantTable = new javax.swing.JTable();
        button1 = new UI.Components.Button();
        hClistCombo = new UI.Components.Combobox();
        button6 = new UI.Components.Button();
        jPanel2 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();

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

        vDashboard.setBackground(new java.awt.Color(0, 91, 149));
        vDashboard.setPreferredSize(new java.awt.Dimension(220, 50));
        vDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vDashboardMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                vDashboardMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                vDashboardMouseExited(evt);
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
        statisticslbl.setText("Volunteer Dashboard");

        statisticsimg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statisticsimg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vcare/icon/statisticsW_40px.png"))); // NOI18N

        javax.swing.GroupLayout vDashboardLayout = new javax.swing.GroupLayout(vDashboard);
        vDashboard.setLayout(vDashboardLayout);
        vDashboardLayout.setHorizontalGroup(
            vDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vDashboardLayout.createSequentialGroup()
                .addComponent(side1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statisticsimg, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(statisticslbl, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        vDashboardLayout.setVerticalGroup(
            vDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vDashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statisticslbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(statisticsimg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(side1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        manageVolunteers.setBackground(new java.awt.Color(0, 91, 149));
        manageVolunteers.setPreferredSize(new java.awt.Dimension(220, 50));
        manageVolunteers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                manageVolunteersMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                manageVolunteersMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                manageVolunteersMouseExited(evt);
            }
        });

        side6.setBackground(new java.awt.Color(0, 91, 149));
        side6.setPreferredSize(new java.awt.Dimension(5, 50));

        javax.swing.GroupLayout side6Layout = new javax.swing.GroupLayout(side6);
        side6.setLayout(side6Layout);
        side6Layout.setHorizontalGroup(
            side6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        side6Layout.setVerticalGroup(
            side6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        manageMedicinelbl1.setBackground(new java.awt.Color(51, 51, 51));
        manageMedicinelbl1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manageMedicinelbl1.setForeground(new java.awt.Color(255, 255, 255));
        manageMedicinelbl1.setText("Manage Volunteers");

        manageMedicineIcon1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        manageMedicineIcon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vcare/icon/Medicine_40px.png"))); // NOI18N

        javax.swing.GroupLayout manageVolunteersLayout = new javax.swing.GroupLayout(manageVolunteers);
        manageVolunteers.setLayout(manageVolunteersLayout);
        manageVolunteersLayout.setHorizontalGroup(
            manageVolunteersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manageVolunteersLayout.createSequentialGroup()
                .addComponent(side6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(manageMedicineIcon1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(manageMedicinelbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        manageVolunteersLayout.setVerticalGroup(
            manageVolunteersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, manageVolunteersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(manageMedicinelbl1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(manageMedicineIcon1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(side6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        vSurvey.setBackground(new java.awt.Color(0, 91, 149));
        vSurvey.setPreferredSize(new java.awt.Dimension(220, 50));
        vSurvey.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vSurveyMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                vSurveyMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                vSurveyMouseExited(evt);
            }
        });

        side7.setBackground(new java.awt.Color(0, 91, 149));
        side7.setPreferredSize(new java.awt.Dimension(5, 50));

        javax.swing.GroupLayout side7Layout = new javax.swing.GroupLayout(side7);
        side7.setLayout(side7Layout);
        side7Layout.setHorizontalGroup(
            side7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        side7Layout.setVerticalGroup(
            side7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        manageMedicinelbl2.setBackground(new java.awt.Color(51, 51, 51));
        manageMedicinelbl2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manageMedicinelbl2.setForeground(new java.awt.Color(255, 255, 255));
        manageMedicinelbl2.setText("Volunteer Survey");
        manageMedicinelbl2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                manageMedicinelbl2MouseClicked(evt);
            }
        });

        manageMedicineIcon2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        manageMedicineIcon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vcare/icon/Medicine_40px.png"))); // NOI18N

        javax.swing.GroupLayout vSurveyLayout = new javax.swing.GroupLayout(vSurvey);
        vSurvey.setLayout(vSurveyLayout);
        vSurveyLayout.setHorizontalGroup(
            vSurveyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vSurveyLayout.createSequentialGroup()
                .addComponent(side7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(manageMedicineIcon2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(manageMedicinelbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        vSurveyLayout.setVerticalGroup(
            vSurveyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vSurveyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(manageMedicinelbl2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(manageMedicineIcon2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(side7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        hcvTasks.setBackground(new java.awt.Color(0, 91, 149));
        hcvTasks.setPreferredSize(new java.awt.Dimension(220, 50));
        hcvTasks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hcvTasksMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hcvTasksMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hcvTasksMouseExited(evt);
            }
        });

        side5.setBackground(new java.awt.Color(0, 91, 149));
        side5.setPreferredSize(new java.awt.Dimension(5, 50));

        javax.swing.GroupLayout side5Layout = new javax.swing.GroupLayout(side5);
        side5.setLayout(side5Layout);
        side5Layout.setHorizontalGroup(
            side5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        side5Layout.setVerticalGroup(
            side5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        manageSupplierlbl.setBackground(new java.awt.Color(51, 51, 51));
        manageSupplierlbl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manageSupplierlbl.setForeground(new java.awt.Color(255, 255, 255));
        manageSupplierlbl.setText("Manage Supplier");

        manageSupplierIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        manageSupplierIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vcare/icon/supplier_40px.png"))); // NOI18N

        javax.swing.GroupLayout hcvTasksLayout = new javax.swing.GroupLayout(hcvTasks);
        hcvTasks.setLayout(hcvTasksLayout);
        hcvTasksLayout.setHorizontalGroup(
            hcvTasksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hcvTasksLayout.createSequentialGroup()
                .addComponent(side5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(manageSupplierIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(manageSupplierlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        hcvTasksLayout.setVerticalGroup(
            hcvTasksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hcvTasksLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(manageSupplierlbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(manageSupplierIcon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(side5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout menuhide1Layout = new javax.swing.GroupLayout(menuhide1);
        menuhide1.setLayout(menuhide1Layout);
        menuhide1Layout.setHorizontalGroup(
            menuhide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(hcvTasks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(menuhide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(menuhide1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(manageVolunteers, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)))
            .addGroup(menuhide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(menuhide1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(vSurvey, javax.swing.GroupLayout.PREFERRED_SIZE, 208, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        menuhide1Layout.setVerticalGroup(
            menuhide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuhide1Layout.createSequentialGroup()
                .addComponent(vDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132)
                .addComponent(hcvTasks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(menuhide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(menuhide1Layout.createSequentialGroup()
                    .addGap(54, 54, 54)
                    .addComponent(manageVolunteers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(665, Short.MAX_VALUE)))
            .addGroup(menuhide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(menuhide1Layout.createSequentialGroup()
                    .addGap(115, 115, 115)
                    .addComponent(vSurvey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(604, Short.MAX_VALUE)))
        );

        menuhide.add(menuhide1, java.awt.BorderLayout.CENTER);

        menu.add(menuhide, java.awt.BorderLayout.CENTER);

        getContentPane().add(menu, java.awt.BorderLayout.LINE_START);

        vAdminTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vAdminTabMouseClicked(evt);
            }
        });

        dashboard.setBackground(new java.awt.Color(255, 255, 255));
        dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboardMouseClicked(evt);
            }
        });
        dashboard.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(217, 241, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 715, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 736, Short.MAX_VALUE)
        );

        dashboard.add(jPanel1, java.awt.BorderLayout.CENTER);

        vAdminTab.addTab("Dashboard", dashboard);

        jPanel16.setBackground(new java.awt.Color(217, 241, 255));

        vOrganizationsCombo.setLabeText("Organization");
        vOrganizationsCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vOrganizationsComboActionPerformed(evt);
            }
        });

        vOrgUN.setLabelText("Username");

        vOrgUName.setLabelText("Name");
        vOrgUName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vOrgUNameActionPerformed(evt);
            }
        });

        createVolunteer.setBackground(new java.awt.Color(0, 91, 149));
        createVolunteer.setForeground(new java.awt.Color(255, 255, 255));
        createVolunteer.setText("Create User");
        createVolunteer.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        createVolunteer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                createVolunteerMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                createVolunteerMouseExited(evt);
            }
        });
        createVolunteer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createVolunteerActionPerformed(evt);
            }
        });

        vOrgPW.setLabelText("Password");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(218, 218, 218)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(vOrganizationsCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(vOrgPW, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(vOrgUN, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE))
                            .addComponent(vOrgUName, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(300, 300, 300)
                        .addComponent(createVolunteer, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(200, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(vOrganizationsCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(vOrgUN, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(vOrgPW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(vOrgUName, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(createVolunteer, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(321, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout addVolunteerLayout = new javax.swing.GroupLayout(addVolunteer);
        addVolunteer.setLayout(addVolunteerLayout);
        addVolunteerLayout.setHorizontalGroup(
            addVolunteerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        addVolunteerLayout.setVerticalGroup(
            addVolunteerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        vAdminTab.addTab("Add Volunteer", addVolunteer);

        vAdminTask.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vAdminTaskMouseClicked(evt);
            }
        });

        jPanel23.setBackground(new java.awt.Color(217, 241, 255));

        sVolunteerCombo.setLabeText("Survey Volunteers");

        sVCommunity.setLabeText("Community");
        sVCommunity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sVCommunityActionPerformed(evt);
            }
        });

        sVHouse.setLabeText("House");

        sVDate.setLabelText("Enter Date ");

        button2.setBackground(new java.awt.Color(0, 91, 149));
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setText("Assign Task");
        button2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sVHouse, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sVCommunity, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sVDate, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sVolunteerCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(303, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(sVolunteerCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sVCommunity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(sVHouse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(sVDate, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(283, Short.MAX_VALUE))
        );

        vAdminTask.addTab("Survey Volunteer", jPanel23);

        jPanel25.setBackground(new java.awt.Color(217, 241, 255));

        sVCommCombo.setLabeText("Community");
        sVCommCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sVCommComboActionPerformed(evt);
            }
        });

        sVHousCombo.setLabeText("Houses");
        sVHousCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sVHousComboActionPerformed(evt);
            }
        });

        svWorkRTenantTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "FName", "Age", "Gender", "Sickness", "Need Hospitalization"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane7.setViewportView(svWorkRTenantTable);

        button1.setText("View Tenant Report");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        hClistCombo.setLabeText("Home Care Volunteers");

        button6.setText("Assign Home Care");
        button6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sVHousCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sVCommCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(244, 244, 244)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hClistCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(sVCommCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(sVHousCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(hClistCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(158, 158, 158))
        );

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        vAdminTask.addTab("View Tasks", jPanel24);

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vAdminTask)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vAdminTask)
        );

        javax.swing.GroupLayout manageTaskLayout = new javax.swing.GroupLayout(manageTask);
        manageTask.setLayout(manageTaskLayout);
        manageTaskLayout.setHorizontalGroup(
            manageTaskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        manageTaskLayout.setVerticalGroup(
            manageTaskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        vAdminTab.addTab("Survey", manageTask);

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 715, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 736, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        vAdminTab.addTab("Home Care", jPanel2);

        getContentPane().add(vAdminTab, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void maxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_maxMouseClicked
        if (maximized) {
            VolunteerAdmin.this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            VolunteerAdmin.this.setMaximizedBounds(env.getMaximumWindowBounds());
            maximized = false;
        } else {
            setExtendedState(JFrame.NORMAL);
            maximized = true;
        }
    }//GEN-LAST:event_maxMouseClicked

    private void maxMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_maxMouseEntered
        changecolor(buttonMax, new Color(3,138,255));
    }//GEN-LAST:event_maxMouseEntered

    private void maxMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_maxMouseExited
        changecolor(buttonMax, new Color(27,152,245));
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
        changecolor(linehidemenu, new Color(190,224,236));
         
    }//GEN-LAST:event_buttonhidemenuMouseEntered

    private void buttonhidemenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonhidemenuMouseExited
        changecolor(linehidemenu, new Color(4,16,20));
    }//GEN-LAST:event_buttonhidemenuMouseExited

    private void buttonLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonLogoutMouseClicked
       clickmenu(setting, hidemenu, 1);
        int a = JOptionPane.showConfirmDialog(this, "Do you want to logout?", "Select", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            this.setVisible(false);
           parentFrame.setVisible(true);;
        }
    }//GEN-LAST:event_buttonLogoutMouseClicked

    private void buttonLogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonLogoutMouseEntered
        changecolor(lineSetting, new Color(190,224,236));
    }//GEN-LAST:event_buttonLogoutMouseEntered

    private void buttonLogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonLogoutMouseExited
        changecolor(lineSetting, new Color(4,16,20));
    }//GEN-LAST:event_buttonLogoutMouseExited

    private void vDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vDashboardMouseClicked
         vAdminTab.setSelectedIndex(0);
         changecolor(vDashboard, new Color(3,138,255));
        changecolor(side1, new Color(190, 224, 236));
    }//GEN-LAST:event_vDashboardMouseClicked

    private void vDashboardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vDashboardMouseEntered
        changecolor(vDashboard, new Color(3,138,255));
        changecolor(side1, new Color(190, 224, 236));
    }//GEN-LAST:event_vDashboardMouseEntered

    private void vDashboardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vDashboardMouseExited
        changecolor(vDashboard, new Color(0,91,149));
        changecolor(side1, new Color(0,91,149));
    }//GEN-LAST:event_vDashboardMouseExited

    private void closeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseExited
        changecolor(buttonClose, new Color(27,152,245));
    }//GEN-LAST:event_closeMouseExited

    private void closeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseEntered
        changecolor(buttonClose, new Color(3,138,255));
    }//GEN-LAST:event_closeMouseEntered

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        this.setVisible(false);
           parentFrame.setVisible(true);
    }//GEN-LAST:event_closeMouseClicked

    private void hcvTasksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hcvTasksMouseClicked
         vAdminTab.setSelectedIndex(3);
          changecolor(hcvTasks, new Color(3,138,255));
        changecolor(side5, new Color(190, 224, 236));
    }//GEN-LAST:event_hcvTasksMouseClicked

    private void hcvTasksMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hcvTasksMouseEntered
        changecolor(hcvTasks, new Color(3,138,255));
        changecolor(side5, new Color(190, 224, 236));
    }//GEN-LAST:event_hcvTasksMouseEntered

    private void hcvTasksMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hcvTasksMouseExited
        changecolor(hcvTasks, new Color(0,91,149));
        changecolor(side5, new Color(0,91,149));
    }//GEN-LAST:event_hcvTasksMouseExited

    private void manageVolunteersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageVolunteersMouseClicked
        vAdminTab.setSelectedIndex(1);
          changecolor(hcvTasks, new Color(3,138,255));
        changecolor(side5, new Color(190, 224, 236));
    }//GEN-LAST:event_manageVolunteersMouseClicked

    private void manageVolunteersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageVolunteersMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_manageVolunteersMouseEntered

    private void manageVolunteersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageVolunteersMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_manageVolunteersMouseExited

    private void vSurveyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vSurveyMouseClicked
          vAdminTab.setSelectedIndex(2);
          changecolor(hcvTasks, new Color(3,138,255));
        changecolor(side5, new Color(190, 224, 236));
    }//GEN-LAST:event_vSurveyMouseClicked

    private void vSurveyMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vSurveyMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_vSurveyMouseEntered

    private void vSurveyMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vSurveyMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_vSurveyMouseExited

    private void vAdminTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vAdminTabMouseClicked
        // TODO add your handling code here:
        int index = vAdminTab.getSelectedIndex();
       
        System.out.print(index);
        switch (index) {
            case 0:
                break;
            case 1:
                populateVolunteerEnterpriseOrganizations();
                break;
            case 2:
                 populateSurveyVolunteers();
                break;
            case 3:
                break;
            default:
                throw new AssertionError();
        }
    }//GEN-LAST:event_vAdminTabMouseClicked

    private void vOrgUNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vOrgUNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vOrgUNameActionPerformed

    private void createVolunteerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createVolunteerActionPerformed
        // TODO add your handling code here:
        
//         int flag = 0;
//        String fields[] = {"cEntUsername", "cEntPassword", "cEntEEname"};
//        for (int i = 0; i < fields.length; i++) {
//            if (hasError(fields[i])) {
//                flag++;
//            }
//        }
//        if (flag > 0) {
//            return;
//        }
Organization org = (Organization) vOrganizationsCombo.getSelectedItem();
int index = vOrganizationsCombo.getSelectedIndex();
        String username = vOrgUN.getText();
        char[] passwordCharArray = vOrgPW.getPassword();
        
        String password = String.valueOf(passwordCharArray);
        String name = vOrgUName.getText();

         Organization o=vEnterPrise.getOrganizationDirectory().getOrganizationList().get(index);
          if (EcoSystem.isUserUnique(username)) {
          
         Employee employee = o.getEmployeeDirectory().createEmployee(name);
         if(o.getName() == Organization.Type.HomeCareVolunteer.getValue()){
           
             
             o.getUserAccountDirectory().createUserAccount(username, password, employee, new HomeCareVolunteerRole());
         JOptionPane.showMessageDialog(this, " HC Volunteer Added Successfully!");
         }  
         else{
              o.getUserAccountDirectory().createUserAccount(username, password, employee, new SurveyVolunteerRole());
        JOptionPane.showMessageDialog(this, " SC Volunteer Added Successfully!");
         } 
          
          }
          else{
              
               JOptionPane.showMessageDialog(this, "Give a unique username!");
          }
         
        
    }//GEN-LAST:event_createVolunteerActionPerformed

    private void vAdminTaskMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vAdminTaskMouseClicked
        // TODO add your handling code here:
        int index = vAdminTask.getSelectedIndex();
       
        System.out.print(index);
        switch (index) {
            case 0:
                populateSurveyVolunteers();
               // populateCommunities();
                break;
            case 1:
               // populateVolunteerEnterpriseOrganizations();
                break;
           
            default:
                throw new AssertionError();
        }
    }//GEN-LAST:event_vAdminTaskMouseClicked
    
    public UserAccount getUser(String empId) {
        for(UserAccount user: organization.getUserAccountDirectory().getUserAccountList()) {
            if(user.getEmployee().getId() == empId) {
                return user;
            }
        }
        return null;
    }
    
    
    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
        
        try{
            SurveyVolunteerWorkRequest sV = new SurveyVolunteerWorkRequest();
            
            sV.setAssignedHouse((House)sVHouse.getSelectedItem());
       
            DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            Date date = format.parse(sVDate.getText());
            sV.setAssignedDate(date);
            sV.setStatus("new");
            Employee emp = (Employee)sVolunteerCombo.getSelectedItem();
            sV.setReceiver(getUser(emp.getId()));
            System.out.print(sV);
            
       Organization org = null;
       for (Organization orn : enterpriseD.getOrganizationDirectory().getOrganizationList())
        {   if (orn instanceof SurveyVolunteerOrganization){
                org = orn;
                break;
            }
        }
        if (org!=null){
            org.getWorkQueue().getWorkRequestList().add(sV);
            account.getWorkQueue().getWorkRequestList().add(sV);
        }
        
            
        } catch (ParseException e) {e.printStackTrace();}
        
        
    }//GEN-LAST:event_button2ActionPerformed

    private void sVCommunityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sVCommunityActionPerformed
        // TODO add your handling code here:
        //populateHouses();
    }//GEN-LAST:event_sVCommunityActionPerformed

    private void manageMedicinelbl2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageMedicinelbl2MouseClicked
        vAdminTab.setSelectedIndex(0);
    }//GEN-LAST:event_manageMedicinelbl2MouseClicked

    private void dashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_dashboardMouseClicked
public void changecolorB(JButton hover, Color rand) {
        hover.setBackground(rand);
    }
    private void createVolunteerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createVolunteerMouseEntered
        changecolorB(createVolunteer, new Color(3,138,255));
    }//GEN-LAST:event_createVolunteerMouseEntered

    private void createVolunteerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createVolunteerMouseExited
         changecolorB(createVolunteer, new Color(0,91,149));
    }//GEN-LAST:event_createVolunteerMouseExited

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
         Community selectedCommunity = (Community) sVCommCombo.getSelectedItem();
        if (selectedCommunity != null) {
            House selectedHouse = (House) sVHousCombo.getSelectedItem();
            if (selectedHouse != null) {
                 populateTenantInfo(selectedHouse);
            } else {
                 JOptionPane.showMessageDialog(this, "Please select a community and house view");
                 return;
            }
            
           
        } else {
             JOptionPane.showMessageDialog(this, "Please select a community and house view");
              return;
        }
        
    }//GEN-LAST:event_button1ActionPerformed

    private void sVCommComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sVCommComboActionPerformed
        // TODO add your handling code here:
       if (sVCommCombo.getSelectedIndex() > 0) {
        //populateHousesHC();
       }
    }//GEN-LAST:event_sVCommComboActionPerformed

    private void sVHousComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sVHousComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sVHousComboActionPerformed

    private void button6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button6ActionPerformed
        // TODO add your handling code here:
        try{
            HomeCareVolunteerWorkRequest sV = new HomeCareVolunteerWorkRequest();
            int selectedRow = svWorkRTenantTable.getSelectedRow();

        if (selectedRow < 0){
            JOptionPane.showMessageDialog(null,"Please select a row!!");
            
            return;
        }
        Tenant tenat = (Tenant)svWorkRTenantTable.getValueAt(selectedRow, 0);
        sV.setPatient(tenat);
            DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            Date date = format.parse(sVDate.getText());
            sV.setRequestDate(date);
            sV.setStatus("New");
            Employee emp = (Employee)hClistCombo.getSelectedItem();
            sV.setReceiver(getUser(emp.getId()));
            sV.setSender(account);
            
       Organization org = null;
       for (Organization orn : enterpriseD.getOrganizationDirectory().getOrganizationList())
        {   
            if (orn instanceof HomeCareVolunteerOrganization){
                org = orn;
                break;
            }
        }
        if (org!=null){
            org.getWorkQueue().getWorkRequestList().add(sV);
            account.getWorkQueue().getWorkRequestList().add(sV);
        }
        
            
        } catch (ParseException e) {e.printStackTrace();}
        
    }//GEN-LAST:event_button6ActionPerformed

    private void vOrganizationsComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vOrganizationsComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vOrganizationsComboActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(PharmacyAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(PharmacyAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(PharmacyAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(PharmacyAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new PharmacyAdmin().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MenuIcon;
    private javax.swing.JPanel addVolunteer;
    private UI.Components.Button button1;
    private UI.Components.Button button2;
    private UI.Components.Button button6;
    private javax.swing.JPanel buttonClose;
    private javax.swing.JLabel buttonLogout;
    private javax.swing.JPanel buttonMax;
    private javax.swing.JLabel buttonhidemenu;
    private javax.swing.JLabel close;
    private UI.Components.Button createVolunteer;
    private javax.swing.JPanel dashboard;
    private UI.Components.Combobox hClistCombo;
    private javax.swing.JPanel hcvTasks;
    private javax.swing.JPanel header;
    private javax.swing.JPanel hidemenu;
    private javax.swing.JPanel iconmaxclose;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JPanel lineSetting;
    private javax.swing.JPanel linehidemenu;
    private javax.swing.JLabel manageMedicineIcon1;
    private javax.swing.JLabel manageMedicineIcon2;
    private javax.swing.JLabel manageMedicinelbl1;
    private javax.swing.JLabel manageMedicinelbl2;
    private javax.swing.JLabel manageSupplierIcon;
    private javax.swing.JLabel manageSupplierlbl;
    private javax.swing.JPanel manageTask;
    private javax.swing.JPanel manageVolunteers;
    private javax.swing.JLabel max;
    private javax.swing.JPanel menu;
    private javax.swing.JPanel menuhide;
    private javax.swing.JPanel menuhide1;
    private UI.Components.Combobox sVCommCombo;
    private UI.Components.Combobox sVCommunity;
    private UI.Components.MyTextFieldLogin sVDate;
    private UI.Components.Combobox sVHousCombo;
    private UI.Components.Combobox sVHouse;
    private UI.Components.Combobox sVolunteerCombo;
    private javax.swing.JPanel setting;
    private javax.swing.JPanel side1;
    private javax.swing.JPanel side5;
    private javax.swing.JPanel side6;
    private javax.swing.JPanel side7;
    private javax.swing.JLabel statisticsimg;
    private javax.swing.JLabel statisticslbl;
    private javax.swing.JTable svWorkRTenantTable;
    private javax.swing.JTabbedPane vAdminTab;
    private javax.swing.JTabbedPane vAdminTask;
    private javax.swing.JPanel vDashboard;
    private UI.Components.MyPasswordFieldLogin vOrgPW;
    private UI.Components.MyTextFieldLogin vOrgUN;
    private UI.Components.MyTextFieldLogin vOrgUName;
    private UI.Components.Combobox vOrganizationsCombo;
    private javax.swing.JPanel vSurvey;
    // End of variables declaration//GEN-END:variables
}
