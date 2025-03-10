import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.*;
import java.util.Scanner;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


// Login functionality
class LoginManager {
    private Map<String, String> userCredentials;

    public LoginManager() {
        userCredentials = new HashMap<>();
        userCredentials.put("admin1", "adminpass");
        userCredentials.put("emp1", "password123");
    }

    public boolean validateLogin(String username, String password) {
        return userCredentials.containsKey(username) && userCredentials.get(username).equals(password);
    }
}

// User classes
abstract class User {
    private String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

class Employee extends User {

    private int employeeId;
    private String lastName;
    private String firstName;
    private String birthday;
    private String address;
    private String phoneNumber;
    private String sssNumber;
    private String philhealthNumber;
    private String tinNumber;
    private String pagIbigNumber;
    private String status;
    private String position;
    private String immediateSupervisor;
    private double basicSalary;
    private double riceSubsidy;
    private double phoneAllowance;
    private double clothingAllowance;
    private double grossSemiMonthlyPay;
    private double hourlyRate;

    public Employee(int employeeId, String lastName, String firstName, String birthday, String address, String phoneNumber,
                    String sssNumber, String philhealthNumber, String tinNumber, String pagIbigNumber, String status, String position, String immediateSupervisor,
                    double basicSalary, double riceSubsidy, double phoneAllowance, double clothingAllowance, double grossSemiMonthlyPay, double hourlyRate) {
        super(firstName); // Use firstName as username
        this.employeeId = employeeId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.sssNumber = sssNumber;
        this.philhealthNumber = philhealthNumber;
        this.tinNumber = tinNumber;
        this.pagIbigNumber = pagIbigNumber;
        this.status = status;
        this.position = position;
        this.immediateSupervisor = immediateSupervisor;
        this.basicSalary = basicSalary;
        this.riceSubsidy = riceSubsidy;
        this.phoneAllowance = phoneAllowance;
        this.clothingAllowance = clothingAllowance;
        this.grossSemiMonthlyPay = grossSemiMonthlyPay;
        this.hourlyRate = hourlyRate;
    }

    // Getters and setters for all fields
    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getBirthday() { return birthday; }
    public void setBirthday(String birthday) { this.birthday = birthday; }
    public String getAddress() { return address; }
    public void setAddress(String address) {this.address = address;}
    public String getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
    public String getSssNumber() {return sssNumber;}
    public void setSssNumber(String sssNumber) {this.sssNumber = sssNumber;}
    public String getPhilhealthNumber() {return philhealthNumber;}
    public void setPhilhealthNumber(String philhealthNumber) {this.philhealthNumber = philhealthNumber;}
    public String getTinNumber() {return tinNumber;}
    public void setTinNumber(String tinNumber) {this.tinNumber = tinNumber;}
    public String getPagIbigNumber() {return pagIbigNumber;}
    public void setPagIbigNumber(String pagIbigNumber) {this.pagIbigNumber = pagIbigNumber;}
    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}
    public String getPosition() {return position;}
    public void setPosition(String position) {this.position = position;}
    public String getImmediateSupervisor() {return immediateSupervisor;}
    public void setImmediateSupervisor(String immediateSupervisor) {this.immediateSupervisor = immediateSupervisor;}
    public double getBasicSalary() {return basicSalary;}
    public void setBasicSalary(double basicSalary) {this.basicSalary = basicSalary;}
    public double getRiceSubsidy() {return riceSubsidy;}
    public void setRiceSubsidy(double riceSubsidy) {this.riceSubsidy = riceSubsidy;}
    public double getPhoneAllowance() {return phoneAllowance;}
    public void setPhoneAllowance(double phoneAllowance) {this.phoneAllowance = phoneAllowance;}
    public double getClothingAllowance() {return clothingAllowance;}
    public void setClothingAllowance(double clothingAllowance) {this.clothingAllowance = clothingAllowance;}
    public double getGrossSemiMonthlyPay() {return grossSemiMonthlyPay;}
    public void setGrossSemiMonthlyPay(double grossSemiMonthlyPay) {this.grossSemiMonthlyPay = grossSemiMonthlyPay;}
    public double getHourlyRate() {return hourlyRate;}
    public void setHourlyRate(double hourlyRate) {this.hourlyRate = hourlyRate;}
}

// Payroll Setup Class
class PayrollSetup {
    private double taxRate;
    private String payPeriod;
    private String modeOfPayment;

    public PayrollSetup() {
        this.taxRate = 0.20;
        this.payPeriod = "Monthly";
        this.modeOfPayment = "Bank Transfer";
    }

    public void modifyTaxRates(double newRate) {
        this.taxRate = newRate;
        System.out.println("Tax rate modified to: " + (taxRate * 100) + "%");
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setPayPeriod(String payPeriod) {
        this.payPeriod = payPeriod;
        System.out.println("Pay period set to: " + payPeriod);
    }

    public String getPayPeriod() {
        return payPeriod;
    }

    public void setModeOfPayment(String modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
        System.out.println("Mode of payment set to: " + modeOfPayment);
    }

    public String getModeOfPayment() {
        return modeOfPayment;
    }
}

// Class for Leave Management
class LeaveManagement {
    public void approveLeave(Employee employee) {
        System.out.println("Leave approved for employee: " + employee.getUsername());
    }

    public void denyLeave(Employee employee) {
        System.out.println("Leave denied for employee: " + employee.getUsername());
    }

    public void requestLeave(Employee employee){
        System.out.println("Leave requested by employee: " + employee.getUsername());
    }
}

// Class for Reports
class Report {
    public void runReport() {
        System.out.println("Generating report...");
    }

    public void viewReport() {
        System.out.println("Viewing report...");
    }
}

// Class for Time and Attendance
class TimeAndAttendance {
    public void clockIn(Employee employee) {
        System.out.println("Clocking in employee: " + employee.getUsername());
    }

    public void clockOut(Employee employee) {
        System.out.println("Clocking out employee: " + employee.getUsername());
    }
}

class TimeAndAttendanceRecords {
    private Map<Integer, List<TimeRecord>> attendanceData;
    private static final String ATTENDANCE_CSV = "attendance.csv";

    public TimeAndAttendanceRecords() {
        attendanceData = new HashMap<>();
        loadFromCSV();
    }

    public void loadFromCSV() {
        try (Scanner scanner = new Scanner(new File(ATTENDANCE_CSV))) {
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // Skip header
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",", -1); // Keep empty fields

                if (parts.length >= 6) { // Ensure all columns are present
                    try {
                        int employeeId = Integer.parseInt(parts[0].trim());
                        String dateStr = parts[3].trim(); // Date is the 4th column
                        String logInStr = parts[4].trim(); // Log In is the 5th column
                        String logOutStr = parts[5].trim(); // Log Out is the 6th column

                        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
                        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm:ss a");

                        // Parse the date and times
                        LocalDate date = LocalDate.parse(dateStr, dateFormatter);
                        LocalTime logIn = LocalTime.parse(logInStr, timeFormatter);
                        LocalTime logOut = LocalTime.parse(logOutStr, timeFormatter);

                        // Add the record to the employee's attendance
                        List<TimeRecord> records = attendanceData.getOrDefault(employeeId, new ArrayList<>());
                        records.add(new TimeRecord(date, logIn, logOut));
                        attendanceData.put(employeeId, records);
                    } catch (NumberFormatException | java.time.format.DateTimeParseException e) {
                        System.err.println("Error parsing attendance data: " + line);
                    }
                } else {
                    System.err.println("Invalid line in CSV (too few fields): " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Attendance CSV not found.");
        }
    }

    public Map<Integer, List<TimeRecord>> getAttendanceData() {
        return attendanceData;
    }
}


class TimeRecord {
    LocalDate date; // Use LocalDate for the date
    LocalTime logIn;
    LocalTime logOut;

    public TimeRecord(LocalDate date, LocalTime logIn, LocalTime logOut) {
        this.date = date;
        this.logIn = logIn;
        this.logOut = logOut;
    }

    public Duration getWorkDuration() {
        return Duration.between(logIn, logOut);
    }
}


// Class for Employee Records Management
class EmployeeRecords {
    protected Map<Integer, Employee> employees; // Key is now Integer (Employee ID)
    private static final String CSV_FILE = "MotorPH_Employee_Data.csv";

    public EmployeeRecords() {
        employees = new HashMap<>();
        loadFromCSV();
    }

    public void addEmployee(Employee employee, int employeeId) {
        employees.put(employeeId, employee); // Use Employee ID as key
        saveToCSV();
        System.out.println("Employee added successfully.");
    }

    public void deleteEmployee(int employeeId) {
        if (employees.containsKey(employeeId)) {
            employees.remove(employeeId);
            saveToCSV();
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    public Employee viewEmployee(int employeeId) {
        if (employees.containsKey(employeeId)) {
            return employees.get(employeeId);
        } else {
            System.out.println("Employee not found.");
            return null;
        }
    }

    public void editEmployee(int employeeId, Employee updatedEmployee) {
        List<String> lines = new ArrayList<>();
        boolean found = false;

        try (Scanner scanner = new Scanner(new File(CSV_FILE))) {
            scanner.nextLine(); // Skip header
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",", -1); // -1 keeps trailing empty strings
                if (parts.length > 0) {
                    try {
                        int currentId = Integer.parseInt(parts[0].trim());
                        if (currentId == employeeId) {
                            found = true;
                            lines.add(formatEmployeeToCSV(updatedEmployee));
                        } else {
                            lines.add(line);
                        }
                    } catch (NumberFormatException e) {
                        lines.add(line);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("CSV file not found.");
            return;
        }

        if (!found) {
            System.out.println("Employee not found.");
            return;
        }

        try (PrintWriter writer = new PrintWriter(new File(CSV_FILE))) {
            writer.println("Employee,Last Name,First Name,Birthday,Address,Phone Number,SSS #,Philhealth #,TIN #,Pag-ibig #,Status,Position,Immediate Supervisor,Basic Salary,Rice Subsidy,Phone Allowance,Clothing Allowance,Gross Semi-monthly Pay,Hourly Rate"); // Print header
            for (String line : lines) {
                writer.println(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error writing to CSV file.");
        }
        employees.put(employeeId, updatedEmployee);
        System.out.println("Employee updated in CSV.");
    }

    private String formatEmployeeToCSV(Employee emp) {
        return String.join(",",
                String.valueOf(emp.getEmployeeId()),
                emp.getLastName(),
                emp.getFirstName(),
                emp.getBirthday(),
                emp.getAddress(),
                emp.getPhoneNumber(),
                emp.getSssNumber(),
                emp.getPhilhealthNumber(),
                emp.getTinNumber(),
                emp.getPagIbigNumber(),
                emp.getStatus(),
                emp.getPosition(),
                emp.getImmediateSupervisor(),
                String.valueOf(emp.getBasicSalary()),
                String.valueOf(emp.getRiceSubsidy()),
                String.valueOf(emp.getPhoneAllowance()),
                String.valueOf(emp.getClothingAllowance()),
                String.valueOf(emp.getGrossSemiMonthlyPay()),
                String.valueOf(emp.getHourlyRate()));
    }


    public void loadFromCSV() {
        try (Scanner scanner = new Scanner(new File(CSV_FILE))) {
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // Skip header line
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = parseCSVLine(line);
                if (tokens.length == 19) {
                    try {
                        int employeeId = Integer.parseInt(tokens[0].trim());
                        String lastName = tokens[1].trim();
                        String firstName = tokens[2].trim();
                        String birthday = tokens[3].trim();
                        String address = tokens[4].trim();
                        String phoneNumber = tokens[5].trim();
                        String sssNumber = tokens[6].trim();
                        String philhealthNumber = tokens[7].trim();
                        String tinNumber = tokens[8].trim();
                        String pagIbigNumber = tokens[9].trim();
                        String status = tokens[10].trim();
                        String position = tokens[11].trim();
                        String immediateSupervisor = tokens[12].trim();
                        double basicSalary = Double.parseDouble(tokens[13].trim().replace(",", ""));
                        double riceSubsidy = Double.parseDouble(tokens[14].trim().replace(",", ""));
                        double phoneAllowance = Double.parseDouble(tokens[15].trim().replace(",", ""));
                        double clothingAllowance = Double.parseDouble(tokens[16].trim().replace(",", ""));
                        double grossSemiMonthlyPay = Double.parseDouble(tokens[17].trim().replace(",", ""));
                        double hourlyRate = Double.parseDouble(tokens[18].trim());

                        Employee employee = new Employee(employeeId, lastName, firstName, birthday, address, phoneNumber,
                                sssNumber, philhealthNumber, tinNumber, pagIbigNumber, status, position, immediateSupervisor,
                                basicSalary, riceSubsidy, phoneAllowance, clothingAllowance, grossSemiMonthlyPay, hourlyRate);
                        employees.put(employeeId, employee);
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing data in line: " + line);
                    }
                } else {
                    System.err.println("Incorrect number of fields in line: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("CSV file not found. Creating a new one.");
        }
    }
    private String[] parseCSVLine(String line) {
        boolean inQuotes = false;
        StringBuilder field = new StringBuilder();
        List<String> tokens = new ArrayList<>();

        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes; // Toggle quotes
            } else if (c == ',' && !inQuotes) {
                // End of a field
                tokens.add(field.toString());
                field.setLength(0); // Clear the field
            } else {
                field.append(c);
            }
        }
        // Add the last field
        tokens.add(field.toString());

        return tokens.toArray(new String[0]);
    }
    private void saveToCSV() {
        try (PrintWriter writer = new PrintWriter(new File(CSV_FILE))) {
            writer.println("Employee,Last Name,First Name,Birthday,Address,Phone Number,SSS #,Philhealth #,TIN #,Pag-ibig #,Status,Position,Immediate Supervisor,Basic Salary,Rice Subsidy,Phone Allowance,Clothing Allowance,Gross Semi-monthly Pay,Hourly Rate");
            for (Map.Entry<Integer, Employee> entry : employees.entrySet()) {
                writer.println(formatEmployeeToCSV(entry.getValue()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

// Main Application
public class PayrollApplication {
    public static void main(String[] args) {
        PayrollGUI gui = new PayrollGUI();
        gui.createLoginGUI();
    }
}

class PayrollGUI {
    private LoginManager loginManager;
    private PayrollSetup payrollSetup;
    private EmployeeRecords employeeRecords;
    private LeaveManagement leaveManagement;
    private TimeAndAttendance timeAndAttendance;
    private TimeAndAttendanceRecords attendanceRecords;

    public PayrollGUI() {
        loginManager = new LoginManager();
        payrollSetup = new PayrollSetup();
        employeeRecords = new EmployeeRecords();
        leaveManagement = new LeaveManagement();
        timeAndAttendance = new TimeAndAttendance();


    }

    public void createLoginGUI() {
        JFrame loginFrame = new JFrame("Payroll App Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(400, 200);

        JPanel panel = new JPanel();
        loginFrame.add(panel);
        placeLoginComponents(panel);

        loginFrame.setVisible(true);
    }

    private void placeLoginComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        JLabel messageLabel = new JLabel("");
        messageLabel.setBounds(10, 110, 300, 25);
        panel.add(messageLabel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());

                if (loginManager.validateLogin(username, password)) {
                    messageLabel.setText("");
                    if (username.equals("admin1")) {
                        accessAdminPortal();
                    } else if (username.equals("emp1")) {
                        accessEmployeePortal();
                    }
                } else {
                    messageLabel.setText("Invalid username or password.");
                }
            }
        });
    }

    public void accessAdminPortal() {
        JFrame adminFrame = new JFrame("Payroll Admin Portal");
        adminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminFrame.setSize(600, 600);

        JPanel mainPanel = new JPanel(); // Main panel with BoxLayout
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Vertical layout
        adminFrame.add(mainPanel);

// Payroll Management Panel
        JPanel payrollPanel = new JPanel(new GridLayout(0, 1)); // Grid layout for buttons
        payrollPanel.setBorder(BorderFactory.createTitledBorder("Payroll Management")); // Title
        JButton runPayrollButton = new JButton("Run Payroll");
        JButton setPayPeriodButton = new JButton("Set Pay Period");
        JButton viewPayPeriodButton = new JButton("View Pay Period");
        JButton modifyTaxButton = new JButton("Modify Tax Rates");
        JButton viewTaxRateButton = new JButton("View Tax Rate");
        JButton setModeOfPaymentButton = new JButton("Set Mode of Payment");
        JButton viewModeOfPaymentButton = new JButton("View Mode of Payment");
        payrollPanel.add(runPayrollButton);
        payrollPanel.add(setPayPeriodButton);
        payrollPanel.add(viewPayPeriodButton);
        payrollPanel.add(modifyTaxButton);
        payrollPanel.add(viewTaxRateButton);
        payrollPanel.add(setModeOfPaymentButton);
        payrollPanel.add(viewModeOfPaymentButton);
        mainPanel.add(payrollPanel);


// Reporting Panel
        JPanel reportingPanel = new JPanel(new GridLayout(0, 1));
        reportingPanel.setBorder(BorderFactory.createTitledBorder("Reporting"));
        JButton runReportButton = new JButton("Run Report");
        JButton viewReportButton = new JButton("View Report");
        reportingPanel.add(runReportButton);
        reportingPanel.add(viewReportButton);
        mainPanel.add(reportingPanel);


// Employee Management Panel
        JPanel employeePanel = new JPanel(new GridLayout(0, 1));
        employeePanel.setBorder(BorderFactory.createTitledBorder("Employee Management"));
        JButton addEmployeeButton = new JButton("Add Employee");
        JButton deleteEmployeeButton = new JButton("Delete Employee");
        JButton viewEmployeeButton = new JButton("View Employee");
        JButton editEmployeeButton = new JButton("Edit Employee");
        employeePanel.add(addEmployeeButton);
        employeePanel.add(deleteEmployeeButton);
        employeePanel.add(viewEmployeeButton);
        employeePanel.add(editEmployeeButton);
        mainPanel.add(employeePanel);

// Leave Management Panel
        JPanel leavePanel = new JPanel(new GridLayout(0, 1));
        leavePanel.setBorder(BorderFactory.createTitledBorder("Leave Management"));
        JButton approveLeaveButton = new JButton("Approve Leave");
        JButton denyLeaveButton = new JButton("Deny Leave");
        leavePanel.add(approveLeaveButton);
        leavePanel.add(denyLeaveButton);
        mainPanel.add(leavePanel);

        JButton exitButton = new JButton("Exit");
        mainPanel.add(exitButton);

        adminFrame.setVisible(true);

        adminFrame.setVisible(true);

        exitButton.addActionListener(e -> adminFrame.dispose());

        runPayrollButton.addActionListener(e -> {
            try {
                StringBuilder payrollReport = new StringBuilder("Payroll Report:\n");

                // Create an instance of TimeAndAttendanceRecords
                TimeAndAttendanceRecords attendanceRecords = new TimeAndAttendanceRecords();
                Map<Integer, List<TimeRecord>> attendanceData = attendanceRecords.getAttendanceData();

                for (Map.Entry<Integer, Employee> employeeEntry : employeeRecords.employees.entrySet()) {
                    int employeeId = employeeEntry.getKey();
                    Employee employee = employeeEntry.getValue();
                    List<TimeRecord> attendance = attendanceData.getOrDefault(employeeId, new ArrayList<>());
                    double totalHours = 0;

                    for (TimeRecord record : attendance) {
                        try {
                            totalHours += record.getWorkDuration().toHours();
                        } catch (ArithmeticException ex) {
                            JOptionPane.showMessageDialog(adminFrame, "Error calculating duration for employee " + employeeId + ": " + ex.getMessage());
                            return;
                        }
                    }

                    double pay = employee.getHourlyRate() * totalHours;

                    payrollReport.append("Employee ID: ").append(employeeId)
                            .append(", Name: ").append(employee.getFirstName()).append(" ").append(employee.getLastName())
                            .append(", Pay: $").append(String.format("%.2f", pay)).append("\n");
                }

                JOptionPane.showMessageDialog(adminFrame, payrollReport.toString(), "Payroll Report", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(adminFrame, "Error generating payroll: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });




        modifyTaxButton.addActionListener(e -> {
            String newRateStr = JOptionPane.showInputDialog(adminFrame, "Enter new tax rate (e.g., 0.25 for 25%):");
            try {
                double newRate = Double.parseDouble(newRateStr);
                if (newRate >= 0 && newRate <= 1) { // Validate input between 0 and 1
                    payrollSetup.modifyTaxRates(newRate);
                } else {
                    JOptionPane.showMessageDialog(adminFrame, "Invalid tax rate. Please enter a number between 0 and 1.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(adminFrame, "Invalid tax rate. Please enter a valid number.");
            }
        });

        setPayPeriodButton.addActionListener(e -> {
            String payPeriod = JOptionPane.showInputDialog(adminFrame, "Enter the pay period (e.g., Weekly, Bi-Weekly, Monthly):");
            payrollSetup.setPayPeriod(payPeriod);
        });

        setModeOfPaymentButton.addActionListener(e -> {
            String modeOfPayment = JOptionPane.showInputDialog(adminFrame, "Enter the mode of payment (e.g., Bank Transfer, Check):");
            payrollSetup.setModeOfPayment(modeOfPayment);
        });

        viewTaxRateButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(adminFrame, "Current tax rate: " + (payrollSetup.getTaxRate() * 100) + "%");
        });

        viewPayPeriodButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(adminFrame, "Current pay period: " + payrollSetup.getPayPeriod());
        });
        viewModeOfPaymentButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(adminFrame, "Current mode of payment: " + payrollSetup.getModeOfPayment());
        });
        runReportButton.addActionListener(e -> {
            Report report = new Report();
            report.runReport();
        });

        viewReportButton.addActionListener(e -> {
            Report report = new Report();
            report.viewReport();
        });
        addEmployeeButton.addActionListener(e -> {
            JFrame addEmployeeFrame = new JFrame("Add New Employee");
            addEmployeeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            addEmployeeFrame.setSize(400, 600); // Increased height

            JPanel panel = new JPanel(new GridLayout(20, 2)); // Grid Layout for labels and fields
            addEmployeeFrame.add(panel);

            JTextField idField = new JTextField();
            JTextField lastNameField = new JTextField();
            JTextField firstNameField = new JTextField();
            JTextField birthdayField = new JTextField();
            JTextField addressField = new JTextField();
            JTextField phoneField = new JTextField();
            JTextField sssField = new JTextField();
            JTextField philhealthField = new JTextField();
            JTextField tinField = new JTextField();
            JTextField pagIbigField = new JTextField();
            JTextField statusField = new JTextField();
            JTextField positionField = new JTextField();
            JTextField supervisorField = new JTextField();
            JTextField salaryField = new JTextField();
            JTextField riceField = new JTextField();
            JTextField phoneAllowanceField = new JTextField();
            JTextField clothingAllowanceField = new JTextField();
            JTextField grossSemiMonthlyPayField = new JTextField();
            JTextField hourlyRateField = new JTextField();


            panel.add(new JLabel("Employee ID:"));
            panel.add(idField);
            panel.add(new JLabel("Last Name:"));
            panel.add(lastNameField);
            panel.add(new JLabel("First Name:"));
            panel.add(firstNameField);
            panel.add(new JLabel("Birthday (MM/DD/YYYY):"));
            panel.add(birthdayField);
            panel.add(new JLabel("Address:"));
            panel.add(addressField);
            panel.add(new JLabel("Phone Number:"));
            panel.add(phoneField);
            panel.add(new JLabel("SSS Number:"));
            panel.add(sssField);
            panel.add(new JLabel("Philhealth Number:"));
            panel.add(philhealthField);
            panel.add(new JLabel("TIN Number:"));
            panel.add(tinField);
            panel.add(new JLabel("Pag-ibig Number:"));
            panel.add(pagIbigField);
            panel.add(new JLabel("Status:"));
            panel.add(statusField);
            panel.add(new JLabel("Position:"));
            panel.add(positionField);
            panel.add(new JLabel("Immediate Supervisor:"));
            panel.add(supervisorField);
            panel.add(new JLabel("Basic Salary:"));
            panel.add(salaryField);
            panel.add(new JLabel("Rice Subsidy:"));
            panel.add(riceField);
            panel.add(new JLabel("Phone Allowance:"));
            panel.add(phoneAllowanceField);
            panel.add(new JLabel("Clothing Allowance:"));
            panel.add(clothingAllowanceField);
            panel.add(new JLabel("Gross Semi-Monthly Pay:"));
            panel.add(grossSemiMonthlyPayField);
            panel.add(new JLabel("Hourly Rate:"));
            panel.add(hourlyRateField);

            JButton saveButton = new JButton("Save");
            panel.add(saveButton);

            saveButton.addActionListener(saveEvent -> {
                try {
                    int employeeId = Integer.parseInt(idField.getText());
                    String lastName = lastNameField.getText();
                    String firstName = firstNameField.getText();
                    String birthday = birthdayField.getText();
                    String address = addressField.getText();
                    String phoneNumber = phoneField.getText();
                    String sssNumber = sssField.getText();
                    String philhealthNumber = philhealthField.getText();
                    String tinNumber = tinField.getText();
                    String pagIbigNumber = pagIbigField.getText();
                    String status = statusField.getText();
                    String position = positionField.getText();
                    String immediateSupervisor = supervisorField.getText();
                    double basicSalary = Double.parseDouble(salaryField.getText());
                    double riceSubsidy = Double.parseDouble(riceField.getText());
                    double phoneAllowance = Double.parseDouble(phoneAllowanceField.getText());
                    double clothingAllowance = Double.parseDouble(clothingAllowanceField.getText());
                    double grossSemiMonthlyPay = Double.parseDouble(grossSemiMonthlyPayField.getText());
                    double hourlyRate = Double.parseDouble(hourlyRateField.getText());

                    if (employeeRecords.viewEmployee(employeeId) == null) {
                        Employee newEmployee = new Employee(employeeId, lastName, firstName, birthday, address, phoneNumber, sssNumber, philhealthNumber, tinNumber, pagIbigNumber, status, position, immediateSupervisor, basicSalary, riceSubsidy, phoneAllowance, clothingAllowance, grossSemiMonthlyPay, hourlyRate);
                        employeeRecords.addEmployee(newEmployee, employeeId);
                        JOptionPane.showMessageDialog(addEmployeeFrame, "Employee Added");
                        addEmployeeFrame.dispose(); // Close the add employee frame
                    } else {
                        JOptionPane.showMessageDialog(addEmployeeFrame, "Employee ID already exists.");
                    }
                } catch (NumberFormatException | NullPointerException ex) {
                    JOptionPane.showMessageDialog(addEmployeeFrame, "Invalid input. Please check all fields.");
                }
            });

            addEmployeeFrame.setVisible(true);
        });

        deleteEmployeeButton.addActionListener(e -> {
            String employeeIdToDeleteStr = JOptionPane.showInputDialog(adminFrame, "Enter Employee ID to delete:");
            try {
                int employeeIdToDelete = Integer.parseInt(employeeIdToDeleteStr);
                employeeRecords.deleteEmployee(employeeIdToDelete);
                JOptionPane.showMessageDialog(adminFrame, "Employee Deleted");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(adminFrame, "Invalid Employee ID. Please enter a number.");
            }
        });

        viewEmployeeButton.addActionListener(e -> {
            String employeeIdToViewStr = JOptionPane.showInputDialog(adminFrame, "Enter Employee ID to view:");
            try {
                int employeeIdToView = Integer.parseInt(employeeIdToViewStr);
                Employee employee = employeeRecords.viewEmployee(employeeIdToView);
                if (employee != null) {
                    // Create a formatted message
                    String employeeDetails = "Employee ID: " + employee.getEmployeeId() + "\n" +
                            "Last Name: " + employee.getLastName() + "\n" +
                            "First Name: " + employee.getFirstName() + "\n" +
                            "Birthday: " + employee.getBirthday() + "\n" +
                            "Address: " + employee.getAddress() +"\n" +
                            "Address: " + employee.getAddress() +"\n"; // Example: Add other details

                    // Use a JTextArea inside a JScrollPane for scrollability if the text is long
                    JTextArea textArea = new JTextArea(employeeDetails);
                    textArea.setEditable(false); // Make it read-only
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    scrollPane.setPreferredSize(new Dimension(300, 200)); // Set preferred size for the scroll pane

                    // Show the details in a single dialog
                    JOptionPane.showMessageDialog(adminFrame, scrollPane, "Employee Details", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(adminFrame, "Employee not found.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(adminFrame, "Invalid Employee ID. Please enter a number.");
            }
        });

        editEmployeeButton.addActionListener(e -> {
            String employeeIdStr = JOptionPane.showInputDialog(adminFrame, "Enter Employee ID to edit:");
            try {
                int employeeId = Integer.parseInt(employeeIdStr);
                Employee existingEmployee = employeeRecords.viewEmployee(employeeId);
                if (existingEmployee != null) {
                    // Input dialogs for all fields
                    String lastName = JOptionPane.showInputDialog(adminFrame, "Last Name:", existingEmployee.getLastName());
                    String firstName = JOptionPane.showInputDialog(adminFrame, "First Name:", existingEmployee.getFirstName());
                    String birthday = JOptionPane.showInputDialog(adminFrame, "Birthday:", existingEmployee.getBirthday());
                    String address = JOptionPane.showInputDialog(adminFrame, "Address:", existingEmployee.getAddress());
                    String phoneNumber = JOptionPane.showInputDialog(adminFrame, "Phone Number:", existingEmployee.getPhoneNumber());
                    String sssNumber = JOptionPane.showInputDialog(adminFrame, "SSS #:", existingEmployee.getSssNumber());
                    String philhealthNumber = JOptionPane.showInputDialog(adminFrame, "Philhealth #:", existingEmployee.getPhilhealthNumber());
                    String tinNumber = JOptionPane.showInputDialog(adminFrame, "TIN #:", existingEmployee.getTinNumber());
                    String pagIbigNumber = JOptionPane.showInputDialog(adminFrame, "Pag-ibig #:", existingEmployee.getPagIbigNumber());
                    String status = JOptionPane.showInputDialog(adminFrame, "Status:", existingEmployee.getStatus());
                    String position = JOptionPane.showInputDialog(adminFrame, "Position:", existingEmployee.getPosition());
                    String immediateSupervisor = JOptionPane.showInputDialog(adminFrame, "Immediate Supervisor:", existingEmployee.getImmediateSupervisor());

                    try {
                        double basicSalary = Double.parseDouble(JOptionPane.showInputDialog(adminFrame, "Basic Salary:", String.valueOf(existingEmployee.getBasicSalary())));
                        double riceSubsidy = Double.parseDouble(JOptionPane.showInputDialog(adminFrame, "Rice Subsidy:", String.valueOf(existingEmployee.getRiceSubsidy())));
                        double phoneAllowance = Double.parseDouble(JOptionPane.showInputDialog(adminFrame, "Phone Allowance:", String.valueOf(existingEmployee.getPhoneAllowance())));
                        double clothingAllowance = Double.parseDouble(JOptionPane.showInputDialog(adminFrame, "Clothing Allowance:", String.valueOf(existingEmployee.getClothingAllowance())));
                        double grossSemiMonthlyPay = Double.parseDouble(JOptionPane.showInputDialog(adminFrame, "Gross Semi-monthly Pay:", String.valueOf(existingEmployee.getGrossSemiMonthlyPay())));
                        double hourlyRate = Double.parseDouble(JOptionPane.showInputDialog(adminFrame, "Hourly Rate:", String.valueOf(existingEmployee.getHourlyRate())));

                        Employee updatedEmployee = new Employee(employeeId, lastName, firstName, birthday, address, phoneNumber,
                                sssNumber, philhealthNumber, tinNumber, pagIbigNumber, status, position, immediateSupervisor,
                                basicSalary, riceSubsidy, phoneAllowance, clothingAllowance, grossSemiMonthlyPay, hourlyRate);

                        employeeRecords.editEmployee(employeeId, updatedEmployee);
                        JOptionPane.showMessageDialog(adminFrame, "Employee updated.");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(adminFrame, "Invalid input for numeric fields.");
                    }

                } else {
                    JOptionPane.showMessageDialog(adminFrame, "Employee not found.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(adminFrame, "Invalid Employee ID.");
            }
        });

        approveLeaveButton.addActionListener(e -> {
            String employeeIdStr = JOptionPane.showInputDialog(adminFrame, "Enter Employee ID to approve leave:"); // Use Employee ID
            try {
                int employeeId = Integer.parseInt(employeeIdStr);
                Employee employee = employeeRecords.viewEmployee(employeeId);
                if (employee != null) {
                    leaveManagement.approveLeave(employee);
                } else {
                    JOptionPane.showMessageDialog(adminFrame, "Employee not found.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(adminFrame, "Invalid Employee ID. Please enter a number.");
            }
        });

        denyLeaveButton.addActionListener(e -> {
            String employeeIdStr = JOptionPane.showInputDialog(adminFrame, "Enter Employee ID to deny leave:"); // Use Employee ID
            try {
                int employeeId = Integer.parseInt(employeeIdStr);
                Employee employee = employeeRecords.viewEmployee(employeeId);
                if (employee != null) {
                    leaveManagement.denyLeave(employee);
                } else {
                    JOptionPane.showMessageDialog(adminFrame, "Employee not found.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(adminFrame, "Invalid Employee ID. Please enter a number.");
            }
        });

        exitButton.addActionListener(e -> adminFrame.dispose());
    }

    public void accessEmployeePortal() {
        JFrame employeeFrame = new JFrame("Employee Portal");
        employeeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        employeeFrame.setSize(400, 500);

        JPanel panel = new JPanel();
        employeeFrame.add(panel);
        panel.setLayout(new GridLayout(6, 1));

        JButton clockInButton = new JButton("Clock In");
        JButton clockOutButton = new JButton("Clock Out");
        JButton requestLeaveButton = new JButton("Request Leave");
        JButton exitButton = new JButton("Exit");

        panel.add(clockInButton);
        panel.add(clockOutButton);
        panel.add(requestLeaveButton);
        panel.add(exitButton);

        employeeFrame.setVisible(true);

        Employee emp = employeeRecords.viewEmployee(10001); // Get the employee with ID 10001


        exitButton.addActionListener(e -> employeeFrame.dispose());

        clockInButton.addActionListener(e -> {
            if(emp != null){
                timeAndAttendance.clockIn(emp);
            }
        });

        clockOutButton.addActionListener(e -> {
            if(emp != null){
                timeAndAttendance.clockOut(emp);
            }
        });

        requestLeaveButton.addActionListener(e -> {
            if(emp != null){
                leaveManagement.requestLeave(emp);
            }
        });
    }
}