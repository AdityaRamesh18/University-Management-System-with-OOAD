/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collegemanagementsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Paul-Vance Pierre Nixon Jr.
 */
public class instructorRegistration extends javax.swing.JFrame {

    /**
     * Creates new form teacherRegistration
     */
    public instructorRegistration() {
        initComponents();
    }

    // Global variables
    Connection conn; // Connection to the MySQL database
    PreparedStatement pst; // To write queries to the database 
    ResultSet rs; // To access tables derived from queries
    DefaultTableModel dtm; // To modify the info table
    String id; // The faculty member's ID
    String dept = ""; // The department the faculty member works in 
    
    /*
    instructorRegistration.java's loaded constructor connects it to the MySQL database,
    initializes the dept variable w/ the department the faculty member works in, 
    loads the distinct locations of the department's courses into the location combo box, 
    and calls courseLoad() to load all the deptartment's courses w/ no assigned instructor
    into the table.
    Pre-condition: The teacher's department has at least one course entry in the
    database, and at least one of its courses has no assigned instructor.
    Post-condition: instructorRegistration.java's connected to the database, the
    the distinct locations of the department's courses are loaded into the location
    combo box, and all the courses with no assigned instructor are loaded into the
    table.
    */
    instructorRegistration(String id) // Loaded constructor
    {
        initComponents();
        conn = databaseConnection.Connect();
        this.id = id;
        
        try
        {       
            // Load each of the department's courses' distinct locations.
            pst = conn.prepareStatement("select department from staff where staffNo = ?");
            pst.setString(1, id);
            rs = pst.executeQuery();
            
            if (rs.next())
            {
                dept = rs.getString("department");
                pst = conn.prepareStatement("select distinct location from course where "
                                         + "department = ?");
                pst.setString(1, dept);
                rs = pst.executeQuery();
            }

            // Load all the locations into the location combo box.
            while (rs.next())
                location_combo_box.addItem(rs.getString("location"));
            
            // Display all courses with no assigned instructor.
            courseLoad();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(modifyDepartmentInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
    courseLoad() is a fetch function which retrieves every course in the instructor's
    depatment that deosn't have an assigned instructor and loads them into the table.
    Pre-condition: There's at least one course entry in the instructor's department
    that doesn't have an assigned instructor.
    Post-condition: The courses' info is loaded into the table.
    */
    private void courseLoad()
    {  
        try
        {
            /* Load every course in the instructor's department
               that doesn't have an assigned instructor.
            */
            pst = conn.prepareStatement("select * from course where department = ? "
                                     + "and isnull(coalesce(instructor_fName, instructor_mName, "
                                     + "instructor_lName))");
            pst.setString(1, dept);
            rs = pst.executeQuery();
            
            // Set the number of the table's rows to 0.
            ResultSetMetaData rsd = rs.getMetaData(); // To get the number of columns from the query
            dtm = (DefaultTableModel)course_info_table.getModel();
            dtm.setRowCount(0);
            
            // Load each course's info into the table.
            while (rs.next())
            {
                Vector vector = new Vector();
                
                for (int index = 1; index <= rsd.getColumnCount(); index++)
                {
                    vector.add(rs.getString("name"));
                    vector.add(rs.getString("courseNo"));
                    vector.add(rs.getString("description"));
                    vector.add(rs.getString("college"));
                    vector.add(rs.getString("department"));
                    vector.add(rs.getString("type"));
                    vector.add(rs.getString("mode"));
                    vector.add(rs.getString("instructor_prefix"));
                    vector.add(rs.getString("instructor_fName"));
                    vector.add(rs.getString("instructor_mName"));
                    vector.add(rs.getString("instructor_lName"));
                    vector.add(rs.getString("instructor_suffix"));
                    vector.add(rs.getString("numStudents"));
                    vector.add(rs.getString("maxStudents"));
                    vector.add(rs.getString("credits"));
                    vector.add(rs.getString("startTime"));
                    vector.add(rs.getString("endTime"));
                    vector.add(rs.getString("location"));
                    vector.add(rs.getString("classroomNo"));
                    vector.add(rs.getString("courseDays"));
                    vector.add(rs.getString("crn"));
                }
                
                dtm.addRow(vector); // Adds a course's info as one of the table's rows
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(instructorRegistration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        course_info_scroll_table = new javax.swing.JScrollPane();
        course_info_table = new javax.swing.JTable();
        course_info_form = new javax.swing.JPanel();
        name_label = new javax.swing.JLabel();
        name_text_field = new javax.swing.JTextField();
        description_label = new javax.swing.JLabel();
        description_text_field = new javax.swing.JTextField();
        type_label = new javax.swing.JLabel();
        mode_label = new javax.swing.JLabel();
        type_combo_box = new javax.swing.JComboBox<>();
        mode_combo_box = new javax.swing.JComboBox<>();
        startTime_label = new javax.swing.JLabel();
        startTime_text_field = new javax.swing.JFormattedTextField();
        endTime_label = new javax.swing.JLabel();
        endTime_text_field = new javax.swing.JFormattedTextField();
        location_label = new javax.swing.JLabel();
        location_combo_box = new javax.swing.JComboBox<>();
        courseDays_label = new javax.swing.JLabel();
        courseDays_combo_box = new javax.swing.JComboBox<>();
        clearButton = new javax.swing.JButton();
        registerButton = new javax.swing.JButton();
        search_by_name_button = new javax.swing.JButton();
        search_by_description_button = new javax.swing.JButton();
        search_by_type_button = new javax.swing.JButton();
        search_by_mode_button = new javax.swing.JButton();
        search_by_start_time_button = new javax.swing.JButton();
        search_by_end_time_button = new javax.swing.JButton();
        search_by_location_button = new javax.swing.JButton();
        search_by_course_days_button = new javax.swing.JButton();
        instructorRegistration_MenuBar = new javax.swing.JMenuBar();
        backButton = new javax.swing.JMenu();
        logoutButton = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        course_info_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "courseNo", "Description", "College", "Department", "Type", "Mode", "Instructor_Prefix", "Instructor_fName", "Instructor_mName", "Instructor_lName", "Instructor_Suffix", "numStudents", "maxStudents", "Credits", "startTime", "endTime", "Location", "classroomNo", "courseDays", "CRN"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        course_info_table.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        course_info_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                course_info_tableMouseClicked(evt);
            }
        });
        course_info_scroll_table.setViewportView(course_info_table);

        course_info_form.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        name_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        name_label.setText("Name:");

        description_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        description_label.setText("Description:");

        type_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        type_label.setText("Type:");

        mode_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mode_label.setText("Mode:");

        type_combo_box.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lecture", "Discussion", "Seminar", "Laboratory", "Studio", "Independent Study" }));
        type_combo_box.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        mode_combo_box.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Face-to-Face", "Online", "Hybrid" }));
        mode_combo_box.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        startTime_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        startTime_label.setText("Start Time:");

        startTime_text_field.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT))));

        endTime_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        endTime_label.setText("End Time:");

        endTime_text_field.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT))));

        location_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        location_label.setText("Location:");

        location_combo_box.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        courseDays_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        courseDays_label.setText("Course Days:");

        courseDays_combo_box.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mon", "Tu", "Wed", "Th", "Fri", "Mon/Wed", "Tu/Th" }));
        courseDays_combo_box.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout course_info_formLayout = new javax.swing.GroupLayout(course_info_form);
        course_info_form.setLayout(course_info_formLayout);
        course_info_formLayout.setHorizontalGroup(
            course_info_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(course_info_formLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(course_info_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(course_info_formLayout.createSequentialGroup()
                        .addComponent(name_label)
                        .addGap(18, 18, 18)
                        .addComponent(name_text_field, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(course_info_formLayout.createSequentialGroup()
                        .addGroup(course_info_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(description_label)
                            .addComponent(type_label)
                            .addComponent(mode_label)
                            .addComponent(startTime_label)
                            .addComponent(endTime_label)
                            .addComponent(location_label))
                        .addGap(18, 18, 18)
                        .addGroup(course_info_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(location_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(endTime_text_field, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(startTime_text_field, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mode_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(type_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(description_text_field, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(course_info_formLayout.createSequentialGroup()
                        .addComponent(courseDays_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(courseDays_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        course_info_formLayout.setVerticalGroup(
            course_info_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(course_info_formLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(course_info_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(name_label)
                    .addComponent(name_text_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(course_info_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(description_label)
                    .addComponent(description_text_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(course_info_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(type_label)
                    .addComponent(type_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(course_info_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mode_label)
                    .addComponent(mode_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(course_info_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startTime_label)
                    .addComponent(startTime_text_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(course_info_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(endTime_label)
                    .addComponent(endTime_text_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(course_info_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(location_label)
                    .addComponent(location_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(course_info_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(courseDays_label)
                    .addComponent(courseDays_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        clearButton.setText("Clear");
        clearButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clearButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clearButtonMouseClicked(evt);
            }
        });

        registerButton.setText("Register");
        registerButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        registerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                registerButtonMouseClicked(evt);
            }
        });

        search_by_name_button.setText("Search By Name");
        search_by_name_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        search_by_name_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                search_by_name_buttonMouseClicked(evt);
            }
        });

        search_by_description_button.setText("Search By Description");
        search_by_description_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        search_by_description_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                search_by_description_buttonMouseClicked(evt);
            }
        });

        search_by_type_button.setText("Search By Type");
        search_by_type_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        search_by_type_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                search_by_type_buttonMouseClicked(evt);
            }
        });

        search_by_mode_button.setText("Search By Mode");
        search_by_mode_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        search_by_mode_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                search_by_mode_buttonMouseClicked(evt);
            }
        });

        search_by_start_time_button.setText("Search By Start Time");
        search_by_start_time_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        search_by_start_time_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                search_by_start_time_buttonMouseClicked(evt);
            }
        });

        search_by_end_time_button.setText("Search By End Time");
        search_by_end_time_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        search_by_end_time_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                search_by_end_time_buttonMouseClicked(evt);
            }
        });

        search_by_location_button.setText("Search By Location");
        search_by_location_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        search_by_location_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                search_by_location_buttonMouseClicked(evt);
            }
        });

        search_by_course_days_button.setText("Search By Course Days");
        search_by_course_days_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        search_by_course_days_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                search_by_course_days_buttonMouseClicked(evt);
            }
        });

        backButton.setText("Back");
        backButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backButtonMouseClicked(evt);
            }
        });
        instructorRegistration_MenuBar.add(backButton);

        logoutButton.setText("Logout");
        logoutButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logoutButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutButtonMouseClicked(evt);
            }
        });
        instructorRegistration_MenuBar.add(logoutButton);

        setJMenuBar(instructorRegistration_MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(course_info_form, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(course_info_scroll_table, javax.swing.GroupLayout.PREFERRED_SIZE, 876, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(search_by_location_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(search_by_mode_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(search_by_type_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(registerButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(search_by_name_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(search_by_description_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(search_by_start_time_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(search_by_end_time_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(search_by_course_days_button, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(course_info_form, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(course_info_scroll_table, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registerButton)
                    .addComponent(clearButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search_by_name_button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search_by_description_button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search_by_type_button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search_by_mode_button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search_by_start_time_button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search_by_end_time_button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search_by_location_button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search_by_course_days_button)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void course_info_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_course_info_tableMouseClicked
        // Get the clicked row.
        dtm = (DefaultTableModel)course_info_table.getModel();
        int selectedIndex = course_info_table.getSelectedRow();

        // Set every field in the form to its respective table value.
        name_text_field.setText(dtm.getValueAt(selectedIndex, 0).toString());
        description_text_field.setText(dtm.getValueAt(selectedIndex, 2).toString());
        type_combo_box.setSelectedItem(dtm.getValueAt(selectedIndex, 5));
        mode_combo_box.setSelectedItem(dtm.getValueAt(selectedIndex, 6));
        startTime_text_field.setText(dtm.getValueAt(selectedIndex, 15).toString());
        endTime_text_field.setText(dtm.getValueAt(selectedIndex, 16).toString());
        location_combo_box.setSelectedItem(dtm.getValueAt(selectedIndex, 17).toString());
        courseDays_combo_box.setSelectedItem(dtm.getValueAt(selectedIndex, 19));
    }//GEN-LAST:event_course_info_tableMouseClicked

    private void registerButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButtonMouseClicked
        try
        {
            // Get the clicked course's CRN.
            dtm = (DefaultTableModel)course_info_table.getModel();
            int selectedIndex = course_info_table.getSelectedRow();
            
            // Assign the instructor to the course.
            pst = conn.prepareStatement("select prefix, fName, mName, lName, suffix "
                                     + "from staff where staffNo = ?");
            pst.setString(1, id);
            rs = pst.executeQuery();
            
            if (rs.next())
            {
                pst = conn.prepareStatement("update course set instructor_prefix = ?, "
                                     + "instructor_fName = ?, instructor_mName = ?, "
                                     + "instructor_lName = ?, instructor_suffix = ? "
                                     + "where crn = ?");
                pst.setString(1, rs.getString("prefix"));
                pst.setString(2, rs.getString("fName"));
                pst.setString(3, rs.getString("mName"));
                pst.setString(4, rs.getString("lName"));
                pst.setString(5, rs.getString("suffix"));
                pst.setString(6, dtm.getValueAt(selectedIndex, 20).toString());
                pst.executeUpdate();
            }
            
            /*
            Inform the instructor they are now instructing the selected course,
            clear the text fields, and reload the courses that don't have an instructor.
            */
            JOptionPane.showMessageDialog(this, "You are now teaching the selected course!");
            name_text_field.setText("");
            description_text_field.setText("");
            startTime_text_field.setText("");
            endTime_text_field.setText("");
            courseLoad();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(instructorRegistration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_registerButtonMouseClicked

    private void backButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backButtonMouseClicked
        // Display staffHome.java.
        this.hide();
        new staffHome(id).setVisible(true);
    }//GEN-LAST:event_backButtonMouseClicked

    private void logoutButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutButtonMouseClicked
        // Display Login.java.
        this.hide();
        new Login().setVisible(true);
    }//GEN-LAST:event_logoutButtonMouseClicked

    private void search_by_name_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search_by_name_buttonMouseClicked
        /*
        Check if the instructor filled the 'Name' text field. If not, display a pop-up 
        telling them to fill it. If so, insert the course's info in the table.
        */
        if (name_text_field.getText().isEmpty() || name_text_field.getText().length() > 20)
        {
            JOptionPane.showMessageDialog(this, "The 'Name' text field is either "
                                         + "empty or exceeds 20 characters.");
        }
        else
        {
            try
            {
                /*
                Search for any course in the database that has a name close to or
                exactly like the one in the form.
                */
                pst = conn.prepareStatement("select * from course where department = ? "
                                             + "and name like %?%");
                pst.setString(1, dept);
                pst.setString(2, name_text_field.getText());
                rs = pst.executeQuery();
                
                // Set the number of the table's rows to 0.
                ResultSetMetaData rsd = rs.getMetaData(); // To get the number of columns from the query
                dtm = (DefaultTableModel)course_info_table.getModel();
                dtm.setRowCount(0);
                
                // Load each course's info into the table.
                while (rs.next())
                {
                    Vector vector = new Vector();
                    
                    for (int index = 1; index <= rsd.getColumnCount(); index++)
                    {
                        vector.add(rs.getString("name"));
                        vector.add(rs.getString("courseNo"));
                        vector.add(rs.getString("description"));
                        vector.add(rs.getString("type"));
                        vector.add(rs.getString("mode"));
                        vector.add(rs.getString("instructor_prefix"));
                        vector.add(rs.getString("instructor_fName"));
                        vector.add(rs.getString("instructor_mName"));
                        vector.add(rs.getString("instructor_lName"));
                        vector.add(rs.getString("instructor_suffix"));
                        vector.add(rs.getString("numStudents"));
                        vector.add(rs.getString("maxStudents"));
                        vector.add(rs.getString("credits"));
                        vector.add(rs.getString("startTime"));
                        vector.add(rs.getString("endTime"));
                        vector.add(rs.getString("location"));
                        vector.add(rs.getString("classroomNo"));
                        vector.add(rs.getString("courseDays"));
                        vector.add(rs.getString("crn"));
                    }
                    
                    dtm.addRow(vector); // Adds a course's info as one of the table's rows
                }
            }
            catch (SQLException ex)
            {
                Logger.getLogger(instructorRegistration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_search_by_name_buttonMouseClicked

    private void search_by_description_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search_by_description_buttonMouseClicked
        /*
        Check if the instructor filled the 'Description' text field. If not, display a pop-up 
        telling them to fill it. If so, insert the course's info in the table.
        */
        if (description_text_field.getText().isEmpty() || description_text_field.getText().length() > 30)
        {
            JOptionPane.showMessageDialog(this, "The 'Description' text field is either "
                                         + "empty or exceeds 30 characters.");
        }
        else
        {
            try
            {
                /*
                Search for any course in the database that has a description close to or
                exactly like the one in the form.
                */
                pst = conn.prepareStatement("select * from course where "
                                         + "department = ? and description like %?%");
                pst.setString(1, dept);
                pst.setString(2, description_text_field.getText());
                rs = pst.executeQuery();
                
                // Set the number of the table's rows to 0.
                ResultSetMetaData rsd = rs.getMetaData(); // To get the number of columns from the query
                dtm = (DefaultTableModel)course_info_table.getModel();
                dtm.setRowCount(0);
                
                // Load each course's info into the table.
                while (rs.next())
                {
                    Vector vector = new Vector();
                    
                    for (int index = 1; index <= rsd.getColumnCount(); index++)
                    {
                        vector.add(rs.getString("name"));
                        vector.add(rs.getString("courseNo"));
                        vector.add(rs.getString("description"));
                        vector.add(rs.getString("type"));
                        vector.add(rs.getString("mode"));
                        vector.add(rs.getString("instructor_prefix"));
                        vector.add(rs.getString("instructor_fName"));
                        vector.add(rs.getString("instructor_mName"));
                        vector.add(rs.getString("instructor_lName"));
                        vector.add(rs.getString("instructor_suffix"));
                        vector.add(rs.getString("numStudents"));
                        vector.add(rs.getString("maxStudents"));
                        vector.add(rs.getString("credits"));
                        vector.add(rs.getString("startTime"));
                        vector.add(rs.getString("endTime"));
                        vector.add(rs.getString("location"));
                        vector.add(rs.getString("classroomNo"));
                        vector.add(rs.getString("courseDays"));
                        vector.add(rs.getString("crn"));
                    }
                    
                    dtm.addRow(vector); // Adds a course's info as one of the table's rows
                }
            }
            catch (SQLException ex)
            {
                Logger.getLogger(instructorRegistration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_search_by_description_buttonMouseClicked

    private void search_by_type_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search_by_type_buttonMouseClicked
        try
        {
                /*
                Search for any course in the database that has the type
                selected in the form.
                */
                pst = conn.prepareStatement("select * from course where department = ? "
                                         + "and type = ?");
                pst.setString(1, dept);
                pst.setString(2, type_combo_box.getSelectedItem().toString());
                rs = pst.executeQuery();
                
                // Set the number of the table's rows to 0.
                ResultSetMetaData rsd = rs.getMetaData(); // To get the number of columns from the query
                dtm = (DefaultTableModel)course_info_table.getModel();
                dtm.setRowCount(0);
                
                // Load each course's info into the table.
                while (rs.next())
                {
                    Vector vector = new Vector();
                    
                    for (int index = 1; index <= rsd.getColumnCount(); index++)
                    {
                        vector.add(rs.getString("name"));
                        vector.add(rs.getString("courseNo"));
                        vector.add(rs.getString("description"));
                        vector.add(rs.getString("type"));
                        vector.add(rs.getString("mode"));
                        vector.add(rs.getString("instructor_prefix"));
                        vector.add(rs.getString("instructor_fName"));
                        vector.add(rs.getString("instructor_mName"));
                        vector.add(rs.getString("instructor_lName"));
                        vector.add(rs.getString("instructor_suffix"));
                        vector.add(rs.getString("numStudents"));
                        vector.add(rs.getString("maxStudents"));
                        vector.add(rs.getString("credits"));
                        vector.add(rs.getString("startTime"));
                        vector.add(rs.getString("endTime"));
                        vector.add(rs.getString("location"));
                        vector.add(rs.getString("classroomNo"));
                        vector.add(rs.getString("courseDays"));
                        vector.add(rs.getString("crn"));
                    }
                    
                    dtm.addRow(vector); // Adds a course's info as one of the table's rows
                }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(instructorRegistration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_search_by_type_buttonMouseClicked

    private void search_by_mode_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search_by_mode_buttonMouseClicked
        try
        {
            /*
            Search for any course in the database that has the mode
            selected in the form.
            */
            pst = conn.prepareStatement("select * from course where department = ? "
                                     + "and mode = ?");
            pst.setString(1, dept);
            pst.setString(2, mode_combo_box.getSelectedItem().toString());
            rs = pst.executeQuery();

            // Set the number of the table's rows to 0.
            ResultSetMetaData rsd = rs.getMetaData(); // To get the number of columns from the query
            dtm = (DefaultTableModel)course_info_table.getModel();
            dtm.setRowCount(0);

            // Load each course's info into the table.
            while (rs.next())
            {
                Vector vector = new Vector();

                for (int index = 1; index <= rsd.getColumnCount(); index++)
                {
                    vector.add(rs.getString("name"));
                    vector.add(rs.getString("courseNo"));
                    vector.add(rs.getString("description"));
                    vector.add(rs.getString("type"));
                    vector.add(rs.getString("mode"));
                    vector.add(rs.getString("instructor_prefix"));
                    vector.add(rs.getString("instructor_fName"));
                    vector.add(rs.getString("instructor_mName"));
                    vector.add(rs.getString("instructor_lName"));
                    vector.add(rs.getString("instructor_suffix"));
                    vector.add(rs.getString("numStudents"));
                    vector.add(rs.getString("maxStudents"));
                    vector.add(rs.getString("credits"));
                    vector.add(rs.getString("startTime"));
                    vector.add(rs.getString("endTime"));
                    vector.add(rs.getString("location"));
                    vector.add(rs.getString("classroomNo"));
                    vector.add(rs.getString("courseDays"));
                    vector.add(rs.getString("crn"));
                }

                dtm.addRow(vector); // Adds a course's info as one of the table's rows
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(instructorRegistration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_search_by_mode_buttonMouseClicked

    private void search_by_start_time_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search_by_start_time_buttonMouseClicked
        if (startTime_text_field.getText().isEmpty())
            JOptionPane.showMessageDialog(this, "The 'Start Time' text field is empty.");
        else
        {
            try
            {
                /*
                Search for any course in the database that has a start time
                exactly like the one in the form.
                */
                pst = conn.prepareStatement("select * from course where "
                                         + "department = ? and startTime = ?");
                pst.setString(1, dept);
                pst.setString(2, startTime_text_field.getText());
                rs = pst.executeQuery();

                // Set the number of the table's rows to 0.
                ResultSetMetaData rsd = rs.getMetaData(); // To get the number of columns from the query
                dtm = (DefaultTableModel)course_info_table.getModel();
                dtm.setRowCount(0);

                // Load each course's info into the table.
                while (rs.next())
                {
                    Vector vector = new Vector();

                    for (int index = 1; index <= rsd.getColumnCount(); index++)
                    {
                        vector.add(rs.getString("name"));
                        vector.add(rs.getString("courseNo"));
                        vector.add(rs.getString("description"));
                        vector.add(rs.getString("type"));
                        vector.add(rs.getString("mode"));
                        vector.add(rs.getString("instructor_prefix"));
                        vector.add(rs.getString("instructor_fName"));
                        vector.add(rs.getString("instructor_mName"));
                        vector.add(rs.getString("instructor_lName"));
                        vector.add(rs.getString("instructor_suffix"));
                        vector.add(rs.getString("numStudents"));
                        vector.add(rs.getString("maxStudents"));
                        vector.add(rs.getString("credits"));
                        vector.add(rs.getString("startTime"));
                        vector.add(rs.getString("endTime"));
                        vector.add(rs.getString("location"));
                        vector.add(rs.getString("classroomNo"));
                        vector.add(rs.getString("courseDays"));
                        vector.add(rs.getString("crn"));
                    }

                    dtm.addRow(vector); // Adds a course's info as one of the table's rows
                }
            }
            catch (SQLException ex)
            {
                Logger.getLogger(instructorRegistration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_search_by_start_time_buttonMouseClicked

    private void search_by_end_time_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search_by_end_time_buttonMouseClicked
        if (endTime_text_field.getText().isEmpty())
            JOptionPane.showMessageDialog(this, "The 'End Time' text field is empty.");
        else
        {
            try
            {
                /*
                Search for any course in the database that has an end time
                exactly like the one in the form.
                */
                pst = conn.prepareStatement("select * from course where "
                                         + "department = ? and endTime = ?");
                pst.setString(1, dept);
                pst.setString(2, endTime_text_field.getText());
                rs = pst.executeQuery();

                // Set the number of the table's rows to 0.
                ResultSetMetaData rsd = rs.getMetaData(); // To get the number of columns from the query
                dtm = (DefaultTableModel)course_info_table.getModel();
                dtm.setRowCount(0);

                // Load each course's info into the table.
                while (rs.next())
                {
                    Vector vector = new Vector();

                    for (int index = 1; index <= rsd.getColumnCount(); index++)
                    {
                        vector.add(rs.getString("name"));
                        vector.add(rs.getString("courseNo"));
                        vector.add(rs.getString("description"));
                        vector.add(rs.getString("type"));
                        vector.add(rs.getString("mode"));
                        vector.add(rs.getString("instructor_prefix"));
                        vector.add(rs.getString("instructor_fName"));
                        vector.add(rs.getString("instructor_mName"));
                        vector.add(rs.getString("instructor_lName"));
                        vector.add(rs.getString("instructor_suffix"));
                        vector.add(rs.getString("numStudents"));
                        vector.add(rs.getString("maxStudents"));
                        vector.add(rs.getString("credits"));
                        vector.add(rs.getString("startTime"));
                        vector.add(rs.getString("endTime"));
                        vector.add(rs.getString("location"));
                        vector.add(rs.getString("classroomNo"));
                        vector.add(rs.getString("courseDays"));
                        vector.add(rs.getString("crn"));
                    }

                    dtm.addRow(vector); // Adds a course's info as one of the table's rows
                }
            }
            catch (SQLException ex)
            {
                Logger.getLogger(instructorRegistration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_search_by_end_time_buttonMouseClicked

    private void search_by_location_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search_by_location_buttonMouseClicked
        try
        {
            /*
            Search for any course in the database that has the location
            selected in the form.
            */
            pst = conn.prepareStatement("select * from course where location = ?");
            pst.setString(1, location_combo_box.getSelectedItem().toString());
            rs = pst.executeQuery();

            // Set the number of the table's rows to 0.
            ResultSetMetaData rsd = rs.getMetaData(); // To get the number of columns from the query
            dtm = (DefaultTableModel)course_info_table.getModel();
            dtm.setRowCount(0);

            // Load each course's info into the table.
            while (rs.next())
            {
                Vector vector = new Vector();

                for (int index = 1; index <= rsd.getColumnCount(); index++)
                {
                    vector.add(rs.getString("name"));
                    vector.add(rs.getString("courseNo"));
                    vector.add(rs.getString("description"));
                    vector.add(rs.getString("type"));
                    vector.add(rs.getString("mode"));
                    vector.add(rs.getString("instructor_prefix"));
                    vector.add(rs.getString("instructor_fName"));
                    vector.add(rs.getString("instructor_mName"));
                    vector.add(rs.getString("instructor_lName"));
                    vector.add(rs.getString("instructor_suffix"));
                    vector.add(rs.getString("numStudents"));
                    vector.add(rs.getString("maxStudents"));
                    vector.add(rs.getString("credits"));
                    vector.add(rs.getString("startTime"));
                    vector.add(rs.getString("endTime"));
                    vector.add(rs.getString("location"));
                    vector.add(rs.getString("classroomNo"));
                    vector.add(rs.getString("courseDays"));
                    vector.add(rs.getString("crn"));
                }

                dtm.addRow(vector); // Adds a course's info as one of the table's rows
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(instructorRegistration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_search_by_location_buttonMouseClicked

    private void search_by_course_days_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search_by_course_days_buttonMouseClicked
        try
        {
            /*
            Search for any course in the database that has the course days
            selected in the form.
            */
            pst = conn.prepareStatement("select * from course where "
                                     + "department = ? and courseDays = ?");
            pst.setString(1, dept);
            pst.setString(2, courseDays_combo_box.getSelectedItem().toString());
            rs = pst.executeQuery();

            // Set the number of the table's rows to 0.
            ResultSetMetaData rsd = rs.getMetaData(); // To get the number of columns from the query
            dtm = (DefaultTableModel)course_info_table.getModel();
            dtm.setRowCount(0);

            // Load each course's info into the table.
            while (rs.next())
            {
                Vector vector = new Vector();

                for (int index = 1; index <= rsd.getColumnCount(); index++)
                {
                    vector.add(rs.getString("name"));
                    vector.add(rs.getString("courseNo"));
                    vector.add(rs.getString("description"));
                    vector.add(rs.getString("type"));
                    vector.add(rs.getString("mode"));
                    vector.add(rs.getString("instructor_prefix"));
                    vector.add(rs.getString("instructor_fName"));
                    vector.add(rs.getString("instructor_mName"));
                    vector.add(rs.getString("instructor_lName"));
                    vector.add(rs.getString("instructor_suffix"));
                    vector.add(rs.getString("numStudents"));
                    vector.add(rs.getString("maxStudents"));
                    vector.add(rs.getString("credits"));
                    vector.add(rs.getString("startTime"));
                    vector.add(rs.getString("endTime"));
                    vector.add(rs.getString("location"));
                    vector.add(rs.getString("classroomNo"));
                    vector.add(rs.getString("courseDays"));
                    vector.add(rs.getString("crn"));
                }

                dtm.addRow(vector); // Adds a course's info as one of the table's rows
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(courseRegistration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_search_by_course_days_buttonMouseClicked

    private void clearButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearButtonMouseClicked
        // Clear all the text fields.
        name_text_field.setText("");
        description_text_field.setText("");
        startTime_text_field.setText("");
        endTime_text_field.setText("");
    }//GEN-LAST:event_clearButtonMouseClicked

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
            java.util.logging.Logger.getLogger(instructorRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(instructorRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(instructorRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(instructorRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new instructorRegistration().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu backButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JComboBox<String> courseDays_combo_box;
    private javax.swing.JLabel courseDays_label;
    private javax.swing.JPanel course_info_form;
    private javax.swing.JScrollPane course_info_scroll_table;
    private javax.swing.JTable course_info_table;
    private javax.swing.JLabel description_label;
    private javax.swing.JTextField description_text_field;
    private javax.swing.JLabel endTime_label;
    private javax.swing.JFormattedTextField endTime_text_field;
    private javax.swing.JMenuBar instructorRegistration_MenuBar;
    private javax.swing.JComboBox<String> location_combo_box;
    private javax.swing.JLabel location_label;
    private javax.swing.JMenu logoutButton;
    private javax.swing.JComboBox<String> mode_combo_box;
    private javax.swing.JLabel mode_label;
    private javax.swing.JLabel name_label;
    private javax.swing.JTextField name_text_field;
    private javax.swing.JButton registerButton;
    private javax.swing.JButton search_by_course_days_button;
    private javax.swing.JButton search_by_description_button;
    private javax.swing.JButton search_by_end_time_button;
    private javax.swing.JButton search_by_location_button;
    private javax.swing.JButton search_by_mode_button;
    private javax.swing.JButton search_by_name_button;
    private javax.swing.JButton search_by_start_time_button;
    private javax.swing.JButton search_by_type_button;
    private javax.swing.JLabel startTime_label;
    private javax.swing.JFormattedTextField startTime_text_field;
    private javax.swing.JComboBox<String> type_combo_box;
    private javax.swing.JLabel type_label;
    // End of variables declaration//GEN-END:variables
}
