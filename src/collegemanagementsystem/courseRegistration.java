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
public class courseRegistration extends javax.swing.JFrame {

    /**
     * Creates new form courseRegistration
     */
    public courseRegistration() {
        initComponents();
    }

    // Global variables
    Connection conn; // Connection to the MySQL database
    PreparedStatement pst; // To write queries to the database 
    ResultSet rs; // To access tables derived from queries
    DefaultTableModel dtm; // To modify the table
    String id; // The student's ID
    
    /*
    courseRegistration.java's loaded constructor connects it to the MySQL database,
    calls courseLoad() to load every course with an instructor into the table,
    loads each college's name into the college combo box, and loads each distinct
    location of the courses into the location combo box.
    Pre-condition: courseRegistration.java isn't connected to the database, and
    there're at least one college & course entry in the database.
    Post-condition: courseRegistration.java's connected to the database, all the
    courses w/ an instructor are loaded into the table, and the college & location
    combo boxes are filled.
    */
    courseRegistration(String id) // Loaded constructor
    {
        initComponents();
        conn = databaseConnection.Connect();
        courseLoad();
        this.id = id;
        
        try
        {
            // Load each college's name.
            pst = conn.prepareStatement("select name from college");
            rs = pst.executeQuery();
            
            // Load all the colleges' names into the college combo box.
            while (rs.next())
                college_combo_box.addItem(rs.getString("name"));
            
            // Load each distinct location.
            pst = conn.prepareStatement("select distinct location from course");
            rs = pst.executeQuery();
            
            // Load all the locations into the location combo box.
            while (rs.next())
                location_combo_box.addItem(rs.getString("location"));
        }
        catch (SQLException ex)
        {
            Logger.getLogger(modifyDepartmentInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
    courseLoad() is a fetch function which retrieves every course which has an
    instructor's info from the MySQL database and renders them in the table.
    Pre-condition: There's at least one course with an assigned instructor
    in the database.
    Post-condition: The courses' info is rendered in the table.
    */
    private void courseLoad()
    {  
        try
        {
            // Load every course which has an assigned instructor.
            pst = conn.prepareStatement("select * from course where instructor_fName is NOT NULL");
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
            Logger.getLogger(modifyCourseInfo.class.getName()).log(Level.SEVERE, null, ex);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        course_info_form = new javax.swing.JPanel();
        name_label = new javax.swing.JLabel();
        name_text_field = new javax.swing.JTextField();
        description_label = new javax.swing.JLabel();
        description_text_field = new javax.swing.JTextField();
        college_label = new javax.swing.JLabel();
        college_combo_box = new javax.swing.JComboBox<>();
        dept_label = new javax.swing.JLabel();
        dept_combo_box = new javax.swing.JComboBox<>();
        type_label = new javax.swing.JLabel();
        mode_label = new javax.swing.JLabel();
        type_combo_box = new javax.swing.JComboBox<>();
        mode_combo_box = new javax.swing.JComboBox<>();
        credits_label = new javax.swing.JLabel();
        credits_spinner = new javax.swing.JSpinner();
        startTime_label = new javax.swing.JLabel();
        startTime_text_field = new javax.swing.JFormattedTextField();
        endTime_label = new javax.swing.JLabel();
        endTime_text_field = new javax.swing.JFormattedTextField();
        location_label = new javax.swing.JLabel();
        location_combo_box = new javax.swing.JComboBox<>();
        courseDays_label = new javax.swing.JLabel();
        courseDays_combo_box = new javax.swing.JComboBox<>();
        crn_label = new javax.swing.JLabel();
        crn_spinner = new javax.swing.JSpinner();
        course_info_scroll_table = new javax.swing.JScrollPane();
        course_info_table = new javax.swing.JTable();
        enrollButton = new javax.swing.JButton();
        search_by_name_button = new javax.swing.JButton();
        search_by_description_button = new javax.swing.JButton();
        search_by_department_button = new javax.swing.JButton();
        search_by_type_button = new javax.swing.JButton();
        search_by_mode_button = new javax.swing.JButton();
        search_by_start_time_button = new javax.swing.JButton();
        search_by_end_time_button = new javax.swing.JButton();
        search_by_location_button = new javax.swing.JButton();
        search_by_course_days_button = new javax.swing.JButton();
        search_by_crn_button = new javax.swing.JButton();
        search_by_college_button = new javax.swing.JButton();
        search_by_credits_button = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        backButton = new javax.swing.JMenu();
        logoutButton = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        course_info_form.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        name_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        name_label.setText("Name:");

        description_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        description_label.setText("Description:");

        college_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        college_label.setText("College:");

        college_combo_box.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        dept_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        dept_label.setText("Department:");

        dept_combo_box.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dept_combo_box.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dept_combo_boxMouseClicked(evt);
            }
        });

        type_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        type_label.setText("Type:");

        mode_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mode_label.setText("Mode:");

        type_combo_box.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lecture", "Discussion", "Seminar", "Laboratory", "Studio", "Independent Study" }));
        type_combo_box.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        mode_combo_box.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Face-to-Face", "Online", "Hybrid" }));
        mode_combo_box.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        credits_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        credits_label.setText("Credits:");

        credits_spinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        startTime_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        startTime_label.setText("Start Time:");

        startTime_text_field.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("hh:mm:ss"))));

        endTime_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        endTime_label.setText("End Time:");

        endTime_text_field.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("hh:mm:ss"))));

        location_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        location_label.setText("Location:");

        location_combo_box.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        courseDays_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        courseDays_label.setText("Course Days:");

        courseDays_combo_box.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mon", "Tu", "Wed", "Th", "Fri", "Mon/Wed", "Tu/Th" }));
        courseDays_combo_box.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        crn_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        crn_label.setText("CRN:");

        crn_spinner.setModel(new javax.swing.SpinnerNumberModel(10000, 10000, 99999, 1));

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

        enrollButton.setText("Enroll");
        enrollButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        enrollButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                enrollButtonMouseClicked(evt);
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

        search_by_department_button.setText("Search By Department");
        search_by_department_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        search_by_department_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                search_by_department_buttonMouseClicked(evt);
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

        search_by_crn_button.setText("Search By CRN");
        search_by_crn_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        search_by_crn_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                search_by_crn_buttonMouseClicked(evt);
            }
        });

        search_by_college_button.setText("Search By College");
        search_by_college_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        search_by_college_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                search_by_college_buttonMouseClicked(evt);
            }
        });

        search_by_credits_button.setText("Search By Credits");
        search_by_credits_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        search_by_credits_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                search_by_credits_buttonMouseClicked(evt);
            }
        });

        clearButton.setText("Clear");
        clearButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clearButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clearButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout course_info_formLayout = new javax.swing.GroupLayout(course_info_form);
        course_info_form.setLayout(course_info_formLayout);
        course_info_formLayout.setHorizontalGroup(
            course_info_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(course_info_formLayout.createSequentialGroup()
                .addGroup(course_info_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                    .addComponent(college_label))
                                .addGap(18, 18, 18)
                                .addGroup(course_info_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(college_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(description_text_field, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(course_info_formLayout.createSequentialGroup()
                                .addGroup(course_info_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dept_label)
                                    .addComponent(type_label)
                                    .addComponent(mode_label)
                                    .addComponent(credits_label)
                                    .addComponent(startTime_label)
                                    .addComponent(endTime_label)
                                    .addComponent(location_label))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(course_info_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(location_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(course_info_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(mode_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dept_combo_box, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(type_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(credits_spinner)
                                        .addComponent(startTime_text_field)
                                        .addComponent(endTime_text_field))))
                            .addGroup(course_info_formLayout.createSequentialGroup()
                                .addGroup(course_info_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(courseDays_label)
                                    .addComponent(crn_label))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(course_info_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(courseDays_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(crn_spinner, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(course_info_formLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(course_info_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(course_info_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(search_by_location_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(search_by_end_time_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(search_by_start_time_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(search_by_name_button, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(enrollButton, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(search_by_description_button)
                                .addComponent(search_by_department_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(search_by_type_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(search_by_mode_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(search_by_course_days_button, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(search_by_crn_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(search_by_college_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(search_by_credits_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(18, 18, 18)
                .addComponent(course_info_scroll_table, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        course_info_formLayout.setVerticalGroup(
            course_info_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(course_info_formLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(course_info_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(course_info_scroll_table, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(course_info_formLayout.createSequentialGroup()
                        .addGroup(course_info_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(name_label)
                            .addComponent(name_text_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(course_info_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(description_label)
                            .addComponent(description_text_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(course_info_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(college_label)
                            .addComponent(college_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(course_info_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dept_label)
                            .addComponent(dept_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addComponent(credits_label)
                            .addComponent(credits_spinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(course_info_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(crn_label)
                            .addComponent(crn_spinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(enrollButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search_by_name_button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search_by_description_button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search_by_department_button)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search_by_crn_button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search_by_college_button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search_by_credits_button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clearButton)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(course_info_form);

        backButton.setText("Back");
        backButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backButtonMouseClicked(evt);
            }
        });
        jMenuBar1.add(backButton);

        logoutButton.setText("Logout");
        logoutButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logoutButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutButtonMouseClicked(evt);
            }
        });
        jMenuBar1.add(logoutButton);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutButtonMouseClicked
        // Display Login.java.
        this.hide();
        new Login().setVisible(true);
    }//GEN-LAST:event_logoutButtonMouseClicked

    private void backButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backButtonMouseClicked
        // Display studentHome.java.
        this.hide();
        new studentHome(id).setVisible(true);
    }//GEN-LAST:event_backButtonMouseClicked

    private void dept_combo_boxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dept_combo_boxMouseClicked
        try
        {
            /*
            Load each department's name which corresponds to the college that the admin
            chose.
            */
            pst = conn.prepareStatement("select name from department where college = ?");
            pst.setString(1, college_combo_box.getSelectedItem().toString());
            rs = pst.executeQuery();

            // Load all the departments' names into the combo box.
            dept_combo_box.removeAllItems();
            while (rs.next())
                dept_combo_box.addItem(rs.getString("name"));
        }
        catch (SQLException ex)
        {
            Logger.getLogger(courseRegistration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_dept_combo_boxMouseClicked

    private void course_info_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_course_info_tableMouseClicked
        // Get the clicked row.
        dtm = (DefaultTableModel)course_info_table.getModel();
        int selectedIndex = course_info_table.getSelectedRow();

        // Set every field in the form to its respective table value.
        name_text_field.setText(dtm.getValueAt(selectedIndex, 0).toString());
        description_text_field.setText(dtm.getValueAt(selectedIndex, 2).toString());
        college_combo_box.setSelectedItem(dtm.getValueAt(selectedIndex, 3));
        dept_combo_box.setSelectedItem(dtm.getValueAt(selectedIndex, 4));
        type_combo_box.setSelectedItem(dtm.getValueAt(selectedIndex, 5));
        mode_combo_box.setSelectedItem(dtm.getValueAt(selectedIndex, 6));
        startTime_text_field.setText(dtm.getValueAt(selectedIndex, 15).toString());
        endTime_text_field.setText(dtm.getValueAt(selectedIndex, 16).toString());
        location_combo_box.setSelectedItem(dtm.getValueAt(selectedIndex, 17).toString());
        courseDays_combo_box.setSelectedItem(dtm.getValueAt(selectedIndex, 19));
        //crn_text_field.setText(dtm.getValueAt(selectedIndex, 20).toString());
    }//GEN-LAST:event_course_info_tableMouseClicked

    private void enrollButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enrollButtonMouseClicked
        try
        {
            // Get the clicked course's CRN & credits.
            dtm = (DefaultTableModel)course_info_table.getModel();
            int selectedIndex = course_info_table.getSelectedRow();
            String crn = dtm.getValueAt(selectedIndex, 20).toString();
            String credits = dtm.getValueAt(selectedIndex, 14).toString();

            // Enroll the student into the course.
            pst = conn.prepareStatement("select numStudents, maxStudents from course "
                + "where crn = ?");
            pst.setString(1, crn);
            rs = pst.executeQuery(); 

            if (rs.next() && (rs.getInt("numStudents") == rs.getInt("maxStudents")))
                JOptionPane.showMessageDialog(this, "Course's full, cannot enroll.");
            else
            {
                /*
                Insert the course's CRN & student's ID into the course enrollment
                list table.
                */
                pst = conn.prepareStatement("insert into course_enrollment_list "
                    + "values(?,?)");
                pst.setString(1, crn);
                pst.setString(2, id);
                pst.executeUpdate();

                // Update the student's charges.
                String charge = "" + (Integer.parseInt(credits) * 10);
                pst = conn.prepareStatement("update student set charges = charges + ? "
                    + "where stuID = ?");
                pst.setString(1, charge);
                pst.setString(2, id);
                pst.executeUpdate();

                // Update the number of students enrolled in the course.
                pst = conn.prepareStatement("update course set numStudents = numStudents + 1 "
                    + "where crn = ?");
                pst.setString(1, crn);
                pst.executeUpdate();

                // Confirm to the student that they're enrolled into the course.
                JOptionPane.showMessageDialog(this, "You're enrolled into the course!");
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(courseRegistration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_enrollButtonMouseClicked

    private void search_by_name_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search_by_name_buttonMouseClicked
        /*
        Check if the student filled the 'name' text field. If not, display a pop-up
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
                pst = conn.prepareStatement("select * from course where name like %?%");
                pst.setString(1, name_text_field.getText());
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
        }
    }//GEN-LAST:event_search_by_name_buttonMouseClicked

    private void search_by_description_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search_by_description_buttonMouseClicked
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
                    + "description like %?%");
                pst.setString(1, description_text_field.getText());
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
        }
    }//GEN-LAST:event_search_by_description_buttonMouseClicked

    private void search_by_department_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search_by_department_buttonMouseClicked
        try
        {
            /*
            Search for any course in the database that is in the department
            selected in the form.
            */
            pst = conn.prepareStatement("select * from course where "
                + "department = ?");
            pst.setString(1, dept_combo_box.getSelectedItem().toString());
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
    }//GEN-LAST:event_search_by_department_buttonMouseClicked

    private void search_by_type_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search_by_type_buttonMouseClicked
        try
        {
            /*
            Search for any course in the database that has the type
            selected in the form.
            */
            pst = conn.prepareStatement("select * from course where type = ?");
            pst.setString(1, type_combo_box.getSelectedItem().toString());
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
    }//GEN-LAST:event_search_by_type_buttonMouseClicked

    private void search_by_mode_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search_by_mode_buttonMouseClicked
        try
        {
            /*
            Search for any course in the database that has the mode
            selected in the form.
            */
            pst = conn.prepareStatement("select * from course where mode = ?");
            pst.setString(1, mode_combo_box.getSelectedItem().toString());
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
                    + "startTime = ?");
                pst.setString(1, startTime_text_field.getText());
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
                    + "endTime = ?");
                pst.setString(1, endTime_text_field.getText());
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
        }
    }//GEN-LAST:event_search_by_end_time_buttonMouseClicked

    private void search_by_location_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search_by_location_buttonMouseClicked
        try
        {
            /*
            Search for any course in the database that has the location
            selected in the form.
            */
            pst = conn.prepareStatement("select * from course where "
                + "location = ?");
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
            Logger.getLogger(courseRegistration.class.getName()).log(Level.SEVERE, null, ex);
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
                + "courseDays = ?");
            pst.setString(1, courseDays_combo_box.getSelectedItem().toString());
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

    private void search_by_crn_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search_by_crn_buttonMouseClicked
        try
        {
            /*
            Search for any course in the database that has the course days
            selected in the form.
            */
            pst = conn.prepareStatement("select * from course where "
                + "crn = ?");
            pst.setString(1, crn_spinner.getValue().toString());
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
    }//GEN-LAST:event_search_by_crn_buttonMouseClicked

    private void search_by_college_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search_by_college_buttonMouseClicked
        try
        {
            /*
            Search for any course in the database that has the course days
            selected in the form.
            */
            pst = conn.prepareStatement("select * from course where "
                + "college = ?");
            pst.setString(1, college_combo_box.getSelectedItem().toString());
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
    }//GEN-LAST:event_search_by_college_buttonMouseClicked

    private void search_by_credits_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search_by_credits_buttonMouseClicked
        try
        {
            /*
            Search for any course in the database that has the number of credits
            selected in the form.
            */
            pst = conn.prepareStatement("select * from course where "
                + "credits = ?");
            pst.setString(1, credits_spinner.getValue().toString());
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
    }//GEN-LAST:event_search_by_credits_buttonMouseClicked

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
            java.util.logging.Logger.getLogger(courseRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(courseRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(courseRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(courseRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new courseRegistration().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu backButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JComboBox<String> college_combo_box;
    private javax.swing.JLabel college_label;
    private javax.swing.JComboBox<String> courseDays_combo_box;
    private javax.swing.JLabel courseDays_label;
    private javax.swing.JPanel course_info_form;
    private javax.swing.JScrollPane course_info_scroll_table;
    private javax.swing.JTable course_info_table;
    private javax.swing.JLabel credits_label;
    private javax.swing.JSpinner credits_spinner;
    private javax.swing.JLabel crn_label;
    private javax.swing.JSpinner crn_spinner;
    private javax.swing.JComboBox<String> dept_combo_box;
    private javax.swing.JLabel dept_label;
    private javax.swing.JLabel description_label;
    private javax.swing.JTextField description_text_field;
    private javax.swing.JLabel endTime_label;
    private javax.swing.JFormattedTextField endTime_text_field;
    private javax.swing.JButton enrollButton;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> location_combo_box;
    private javax.swing.JLabel location_label;
    private javax.swing.JMenu logoutButton;
    private javax.swing.JComboBox<String> mode_combo_box;
    private javax.swing.JLabel mode_label;
    private javax.swing.JLabel name_label;
    private javax.swing.JTextField name_text_field;
    private javax.swing.JButton search_by_college_button;
    private javax.swing.JButton search_by_course_days_button;
    private javax.swing.JButton search_by_credits_button;
    private javax.swing.JButton search_by_crn_button;
    private javax.swing.JButton search_by_department_button;
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
