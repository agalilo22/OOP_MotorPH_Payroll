import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.*;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import javax.swing.border.EmptyBorder;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
 import com.itextpdf.layout.Document;
 import com.itextpdf.layout.element.Paragraph;
 import com.itextpdf.layout.element.Text;
 import javax.swing.JFileChooser;
 import java.io.File;
 import java.io.IOException;
import javax.swing.JComboBox;


//     __                _          ____                 __  _                   ___ __
//   / /   ____  ____ _(_)___     / __/_  ______  _____/ /_(_)___  ____  ____ _/ (_) /___  __
//  / /   / __ \/ __ `/ / __ \   / /_/ / / / __ \/ ___/ __/ / __ \/ __ \/ __ `/ / / __/ / / /
// / /___/ /_/ / /_/ / / / / /  / __/ /_/ / / / / /__/ /_/ / /_/ / / / / /_/ / / / /_/ /_/ /
///_____/\____/\__, /_/_/ /_/  /_/  \__,_/_/ /_/\___/\__/_/\____/_/ /_/\__,_/_/_/\__/\__, /
//            /____/                                                                /____/
class LoginManager {
    private Map<String, String> userCredentials;
    private static final String USER_CREDENTIALS_CSV = "user_credentials.csv";

    public LoginManager() {
        userCredentials = new HashMap<>();
        loadCredentialsFromCSV(); // Load credentials from CSV at startup
    }

    private void loadCredentialsFromCSV() {
        try (Scanner scanner = new Scanner(new File(USER_CREDENTIALS_CSV))) {
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // Skip header line
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",", -1);
                if (parts.length == 3) { // Assuming CSV format: username,password,role
                    String username = parts[0].trim();
                    String password = parts[1].trim();
                    String role = parts[2].trim(); // Role is not used in validation but can be used later
                    userCredentials.put(username, password);
                } else {
                    System.err.println("Invalid format in user credentials CSV: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("User credentials CSV file not found.");
        }
    }
    public boolean validateLogin(String username, String password) {
        return userCredentials.containsKey(username) && userCredentials.get(username).equals(password);
    }
    public void updateUserPassword(String username, String newPassword) {
        if (userCredentials.containsKey(username)) {
            userCredentials.put(username, newPassword);
            saveCredentialsToCSV();
            System.out.println("Password updated for user: " + username);
        } else {
            System.out.println("User not found: " + username);
        }
    }

    // save credentials to CSV
    private void saveCredentialsToCSV() {
        try (PrintWriter writer = new PrintWriter(new File(USER_CREDENTIALS_CSV))) {
            writer.println("username,password,role"); // CSV header
            for (Map.Entry<String, String> entry : userCredentials.entrySet()) {
                writer.println(entry.getKey() + "," + entry.getValue() + "," + (entry.getKey().equals("admin1") ? "admin" : entry.getKey().equals("itadmin") ? "it" : "employee"));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error saving credentials to CSV: " + e.getMessage());
        }
    }

    //IT admin credentials
    public void ensureITAdminExists() {
        if (!userCredentials.containsKey("itadmin")) {
            userCredentials.put("itadmin", "itpass");
            saveCredentialsToCSV();
        }
    }
}

//    __  __                       __
//  / / / /_______  _____   _____/ /___ ______________  _____
// / / / / ___/ _ \/ ___/  / ___/ / __ `/ ___/ ___/ _ \/ ___/
/// /_/ (__  )  __/ /     / /__/ / /_/ (__  |__  )  __(__  )
//\____/____/\___/_/      \___/_/\__,_/____/____/\___/____/
//
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
        super(String.valueOf(employeeId)); // Use firstName as username - IMPORTANT for current login logic
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


//     ____      __            ____                   ____              ______              ____           __           __  _
//   /  _/___  / /____  _____/ __/___ _________     / __/___  _____   /_  __/___ __  __   / __ \___  ____/ /_  _______/ /_(_)___  ____  _____
//   / // __ \/ __/ _ \/ ___/ /_/ __ `/ ___/ _ \   / /_/ __ \/ ___/    / / / __ `/ |/_/  / / / / _ \/ __  / / / / ___/ __/ / __ \/ __ \/ ___/
// _/ // / / / /_/  __/ /  / __/ /_/ / /__/  __/  / __/ /_/ / /       / / / /_/ />  <   / /_/ /  __/ /_/ / /_/ / /__/ /_/ / /_/ / / / (__  )
///___/_/ /_/\__/\___/_/  /_/  \__,_/\___/\___/  /_/  \____/_/       /_/  \__,_/_/|_|  /_____/\___/\__,_/\__,_/\___/\__/_/\____/_/ /_/____/
//
interface TaxDeduction {
    double calculateDeduction(double grossPay);
    String getDeductionName();
}

// Concrete classes for each tax deduction
class BIRDeduction implements TaxDeduction {
    private static double RATE = 0.15; // Remove 'final' to allow modification

    @Override
    public double calculateDeduction(double grossPay) {
        return grossPay * RATE;
    }

    @Override
    public String getDeductionName() {
        return "BIR";
    }

    // New setter method to update the tax rate
    public static void setRate(double newRate) {
        if (newRate >= 0 && newRate <= 1) { // Validate range: 0 to 100%
            RATE = newRate;
        } else {
            throw new IllegalArgumentException("Tax rate must be between 0 and 100%.");
        }
    }

    // Optional: Getter for the current rate
    public static double getRate() {
        return RATE;
    }
}

class SSSDeduction implements TaxDeduction {
    private static final double RATE = 0.05;

    @Override
    public double calculateDeduction(double grossPay) {
        return grossPay * RATE;
    }

    @Override
    public String getDeductionName() {
        return "SSS";
    }
}

class PhilHealthDeduction implements TaxDeduction {
    private static final double RATE = 0.05;

    @Override
    public double calculateDeduction(double grossPay) {
        return grossPay * RATE;
    }

    @Override
    public String getDeductionName() {
        return "PhilHealth";
    }
}

class PagIbigDeduction implements TaxDeduction {
    private static final double RATE = 0.02;

    @Override
    public double calculateDeduction(double grossPay) {
        return grossPay * RATE;
    }

    @Override
    public String getDeductionName() {
        return "Pag-IBIG";
    }
}

// payroll setup

class PayrollSetup {
    private String payPeriod;
    private String modeOfPayment;

    public PayrollSetup() {
        this.payPeriod = "Monthly";
        this.modeOfPayment = "Bank Transfer";
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

// leave request

class LeaveRequest {
    private int requestId;
    private int employeeId;
    private String employeeName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private String status;

    public LeaveRequest(int requestId, int employeeId, String employeeName, LocalDate startDate, LocalDate endDate, String reason) {
        this.requestId = requestId;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.status = "Pending"; // Default status
    }

    // Existing Getters
    public int getRequestId() { return requestId; }
    public int getEmployeeId() { return employeeId; }
    public String getEmployeeName() { return employeeName; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public String getReason() { return reason; }
    public String getStatus() { return status; }

    // Existing Setter
    public void setStatus(String status) { this.status = status; }

    // New Setters to Fix Errors
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public void setReason(String reason) { this.reason = reason; }

    @Override
    public String toString() {
        return "Request ID: " + requestId + ", Employee: " + employeeName + ", Start Date: " + startDate + ", End Date: " + endDate + ", Status: " + status;
    }
}



//     __                                                                                     __
//   / /__  ____ __   _____     ____ ___  ____ _____  ____ _____ ____  ____ ___  ___  ____  / /_
//  / / _ \/ __ `/ | / / _ \   / __ `__ \/ __ `/ __ \/ __ `/ __ `/ _ \/ __ `__ \/ _ \/ __ \/ __/
// / /  __/ /_/ /| |/ /  __/  / / / / / / /_/ / / / / /_/ / /_/ /  __/ / / / / /  __/ / / / /_
///_/\___/\__,_/ |___/\___/  /_/ /_/ /_/\__,_/_/ /_/\__,_/\__, /\___/_/ /_/ /_/\___/_/ /_/\__/
//                                                       /____/
class LeaveManagement {
    private List<LeaveRequest> leaveRequests;
    private static final String LEAVE_REQUESTS_CSV = "LEAVE_REQUESTS_CSV.csv";
    private int nextRequestId = 1;

    public LeaveManagement() {
        leaveRequests = new ArrayList<>();
        loadFromCSV();
        if (!leaveRequests.isEmpty()) {
            nextRequestId = leaveRequests.stream()
                    .max(Comparator.comparingInt(LeaveRequest::getRequestId))
                    .get()
                    .getRequestId() + 1;
        }
    }

    public void requestLeave(Employee employee, LocalDate startDate, LocalDate endDate, String reason) {
        LeaveRequest request = new LeaveRequest(getNextRequestId(), employee.getEmployeeId(), employee.getFirstName() + " " + employee.getLastName(), startDate, endDate, reason);
        leaveRequests.add(request);
        saveToCSV();
        System.out.println("Leave requested by employee: " + employee.getUsername());
    }

    public void approveLeave(int requestId) {
        LeaveRequest request = findLeaveRequestById(requestId);
        if (request != null) {
            request.setStatus("Approved");
            saveToCSV();
            System.out.println("Leave approved for request ID: " + requestId);
        } else {
            System.out.println("Leave request not found: " + requestId);
        }
    }

    public void denyLeave(int requestId) {
        LeaveRequest request = findLeaveRequestById(requestId);
        if (request != null) {
            request.setStatus("Denied");
            saveToCSV();
            System.out.println("Leave denied for request ID: " + requestId);
        } else {
            System.out.println("Leave request not found: " + requestId);
        }
    }

    public List<LeaveRequest> getPendingLeaveRequests() {
        List<LeaveRequest> pendingRequests = new ArrayList<>();
        for (LeaveRequest request : leaveRequests) {
            if (request.getStatus().equalsIgnoreCase("Pending")) {
                pendingRequests.add(request);
            }
        }
        return pendingRequests;
    }

    private LeaveRequest findLeaveRequestById(int requestId) {
        for (LeaveRequest request : leaveRequests) {
            if (request.getRequestId() == requestId) {
                return request;
            }
        }
        return null;
    }

    private int getNextRequestId() {
        return nextRequestId++;
    }

    private void loadFromCSV() {
        try (Scanner scanner = new Scanner(new File(LEAVE_REQUESTS_CSV))) {
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // Skip header
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",", -1);
                if (parts.length == 7) { // Ensure all fields are present
                    try {
                        int requestId = Integer.parseInt(parts[0].trim());
                        int employeeId = Integer.parseInt(parts[1].trim());
                        String employeeName = parts[2].trim();
                        LocalDate startDate = LocalDate.parse(parts[3].trim());
                        LocalDate endDate = LocalDate.parse(parts[4].trim());
                        String reason = parts[5].trim();
                        String status = parts[6].trim();

                        LeaveRequest request = new LeaveRequest(requestId, employeeId, employeeName, startDate, endDate, reason);
                        request.setStatus(status); // Set status from CSV
                        leaveRequests.add(request);
                    } catch (NumberFormatException | DateTimeParseException e) {
                        System.err.println("Error parsing leave request from CSV: " + line);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Leave requests CSV file not found. Creating a new one.");
        }
    }


    public void saveToCSV() {
        try (PrintWriter writer = new PrintWriter(new File(LEAVE_REQUESTS_CSV))) {
            writer.println("Request ID,Employee ID,Employee Name,Start Date,End Date,Reason,Status");
            DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;
            for (LeaveRequest request : leaveRequests) {
                writer.println(String.join(",",
                        String.valueOf(request.getRequestId()),
                        String.valueOf(request.getEmployeeId()),
                        request.getEmployeeName(),
                        request.getStartDate().format(dateFormatter),
                        request.getEndDate().format(dateFormatter),
                        request.getReason(),
                        request.getStatus()
                ));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error saving leave requests to CSV.");
        }
    }
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequests;
    }
}


//                                __               __
//   ________  ____  ____  _____/ /______   _____/ /___ ___________
//  / ___/ _ \/ __ \/ __ \/ ___/ __/ ___/  / ___/ / __ `/ ___/ ___/
// / /  /  __/ /_/ / /_/ / /  / /_(__  )  / /__/ / /_/ (__  |__  )
///_/   \___/ .___/\____/_/   \__/____/   \___/_/\__,_/____/____/
//         /_/
class Report {
    private TimeAndAttendanceRecords attendanceRecords;
    private EmployeeRecords employeeRecords;
    private PayrollGUI.PayrollRecords payrollRecords;

    public Report(TimeAndAttendanceRecords attendanceRecords, EmployeeRecords employeeRecords, PayrollGUI.PayrollRecords payrollRecords) {
        this.attendanceRecords = attendanceRecords;
        this.employeeRecords = employeeRecords;
        this.payrollRecords = payrollRecords;
    }

    // Updated: Generate payroll report with date-only range filter, one report per day
    public String generatePayrollReport(LocalDate startDate, LocalDate endDate) {
        List<PayrollGUI.PayrollData> allPayroll = payrollRecords.getAllPayrollData();
        if (allPayroll.isEmpty()) return "No payroll data available.";

        // Group payroll data by date only (ignoring time)
        Map<LocalDate, List<PayrollGUI.PayrollData>> payrollByDate = new TreeMap<>();
        for (PayrollGUI.PayrollData data : allPayroll) {
            LocalDate payrollDate = data.getRunDate().toLocalDate();
            if (startDate != null && endDate != null &&
                    (payrollDate.isBefore(startDate) || payrollDate.isAfter(endDate))) {
                continue; // Skip if outside date range
            }
            payrollByDate.computeIfAbsent(payrollDate, k -> new ArrayList<>()).add(data);
        }

        if (payrollByDate.isEmpty()) {
            return "No payroll data found for the specified date range.";
        }

        StringBuilder report = new StringBuilder("<html><body>");
        report.append("<h1>Payroll Report")
                .append(startDate != null && endDate != null
                        ? " from " + startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) +
                        " to " + endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                        : "")
                .append("</h1>");

        double grandTotalGrossPay = 0;
        double grandTotalNetPay = 0;
        double grandTotalDeductions = 0;

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Iterate through each unique date
        for (Map.Entry<LocalDate, List<PayrollGUI.PayrollData>> entry : payrollByDate.entrySet()) {
            LocalDate payrollDate = entry.getKey();
            List<PayrollGUI.PayrollData> dailyPayroll = entry.getValue();

            report.append("<h2>Payroll Date: ").append(payrollDate.format(dateFormatter)).append("</h2>");

            double dailyTotalGrossPay = 0;
            double dailyTotalNetPay = 0;
            double dailyTotalDeductions = 0;

            // List all employees for this date
            for (PayrollGUI.PayrollData data : dailyPayroll) {
                double totalDeductions = data.getBirDeduction() + data.getSssDeduction() +
                        data.getPhilHealthDeduction() + data.getPagIbigDeduction();

                report.append("<b>").append(data.getFirstName()).append(" ").append(data.getLastName())
                        .append(" (ID: ").append(data.getEmployeeId()).append(")</b><br>")
                        .append("Gross Pay: $").append(String.format("%.2f", data.getGrossPay())).append("<br>")
                        .append("Net Pay: $").append(String.format("%.2f", data.getNetPay())).append("<br>")
                        .append("Deductions: $").append(String.format("%.2f", totalDeductions)).append("<br>")
                        .append("  BIR: $").append(String.format("%.2f", data.getBirDeduction())).append("<br>")
                        .append("  SSS: $").append(String.format("%.2f", data.getSssDeduction())).append("<br>")
                        .append("  PhilHealth: $").append(String.format("%.2f", data.getPhilHealthDeduction())).append("<br>")
                        .append("  Pag-IBIG: $").append(String.format("%.2f", data.getPagIbigDeduction())).append("<br><br>");

                dailyTotalGrossPay += data.getGrossPay();
                dailyTotalNetPay += data.getNetPay();
                dailyTotalDeductions += totalDeductions;
            }

            // Summary for this date
            report.append("<h3>Daily Summary</h3>")
                    .append("Total Gross Pay: $").append(String.format("%.2f", dailyTotalGrossPay)).append("<br>")
                    .append("Total Net Pay: $").append(String.format("%.2f", dailyTotalNetPay)).append("<br>")
                    .append("Total Deductions: $").append(String.format("%.2f", dailyTotalDeductions)).append("<br><br>");

            grandTotalGrossPay += dailyTotalGrossPay;
            grandTotalNetPay += dailyTotalNetPay;
            grandTotalDeductions += dailyTotalDeductions;
        }

        // Grand total across all days
        report.append("<h2>Grand Total Across All Days</h2>")
                .append("Total Gross Pay: $").append(String.format("%.2f", grandTotalGrossPay)).append("<br>")
                .append("Total Net Pay: $").append(String.format("%.2f", grandTotalNetPay)).append("<br>")
                .append("Total Deductions: $").append(String.format("%.2f", grandTotalDeductions)).append("</body></html>");

        return report.toString();
    }

    // Updated: Generate payslips with date-only range filter
    public String generatePayslips(int employeeId, LocalDate startDate, LocalDate endDate) {
        List<PayrollGUI.PayrollData> employeePayroll = payrollRecords.getPayrollDataForEmployee(employeeId);
        if (employeePayroll.isEmpty()) return "No payslips found for this employee.";

        // Filter payslips by date range if provided
        List<PayrollGUI.PayrollData> filteredPayroll = (startDate == null || endDate == null)
                ? employeePayroll
                : employeePayroll.stream()
                .filter(data -> {
                    LocalDate runDate = data.getRunDate().toLocalDate();
                    return !runDate.isBefore(startDate) && !runDate.isAfter(endDate);
                })
                .collect(Collectors.toList());

        if (filteredPayroll.isEmpty()) return "No payslips found for this employee in the specified date range.";

        StringBuilder payslips = new StringBuilder("<html><body><h1>Payslips for Employee ID: " + employeeId +
                (startDate != null && endDate != null ? " from " + startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) +
                        " to " + endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "") + "</h1>");
        for (PayrollGUI.PayrollData data : filteredPayroll) {
            payslips.append("<h2>Pay Date: ").append(data.getRunDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("</h2>")
                    .append("<b>").append(data.getFirstName()).append(" ").append(data.getLastName()).append("</b><br>")
                    .append("Gross Pay: $").append(String.format("%.2f", data.getGrossPay())).append("<br>")
                    .append("Net Pay: $").append(String.format("%.2f", data.getNetPay())).append("<br>")
                    .append("Deductions:<br>")
                    .append("  BIR: $").append(data.getBirDeduction()).append("<br>")
                    .append("  SSS: $").append(data.getSssDeduction()).append("<br>")
                    .append("  PhilHealth: $").append(data.getPhilHealthDeduction()).append("<br>")
                    .append("  Pag-IBIG: $").append(data.getPagIbigDeduction()).append("<br><br>");
        }
        payslips.append("</body></html>");
        return payslips.toString();
    }

}

//    __  _                                   __         __  __                 __
//  / /_(_)___ ___  ___     ____ _____  ____/ /  ____ _/ /_/ /____  ____  ____/ /___ _________
// / __/ / __ `__ \/ _ \   / __ `/ __ \/ __  /  / __ `/ __/ __/ _ \/ __ \/ __  / __ `/ ___/ _ \
/// /_/ / / / / / /  __/  / /_/ / / / / /_/ /  / /_/ / /_/ /_/  __/ / / / /_/ / /_/ / /__/  __/
//\__/_/_/ /_/ /_/\___/   \__,_/_/ /_/\__,_/   \__,_/\__/\__/\___/_/ /_/\__,_/\__,_/\___/\___/
//
class TimeAndAttendance {
    private TimeAndAttendanceRecords records;

    public TimeAndAttendance(TimeAndAttendanceRecords records) {
        this.records = records;
    }

    public void clockIn(Employee employee) {
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        records.recordClockIn(employee.getEmployeeId(), employee.getLastName(), employee.getFirstName(), today, now);
        System.out.println("Clocked in employee ID: " + employee.getEmployeeId() + " at " + now);
    }

    public void clockOut(Employee employee) {
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        records.recordClockOut(employee.getEmployeeId(), today, now);
        System.out.println("Clocked out employee ID: " + employee.getEmployeeId() + " at " + now);
    }
}

class TimeAndAttendanceRecords {
    private Map<Integer, List<TimeRecord>> attendanceData;
    private static final String ATTENDANCE_CSV = "attendance.csv";

    public TimeAndAttendanceRecords() {
        attendanceData = new HashMap<>();
        loadFromCSV();
    }

    public void recordClockIn(int employeeId, String lastName, String firstName, LocalDate date, LocalTime logIn) {
        List<TimeRecord> records = attendanceData.getOrDefault(employeeId, new ArrayList<>());
        // Check if there's an existing record for today without a log out
        TimeRecord existingRecord = records.stream()
                .filter(r -> r.date.equals(date) && r.logOut == null)
                .findFirst()
                .orElse(null);
        if (existingRecord == null) {
            TimeRecord newRecord = new TimeRecord(date, logIn, null);
            records.add(newRecord);
            attendanceData.put(employeeId, records);
            saveToCSV(employeeId, lastName, firstName, newRecord);
        } else {
            System.out.println("Employee " + employeeId + " has already clocked in today at " + existingRecord.logIn);
        }
    }

    public void recordClockOut(int employeeId, LocalDate date, LocalTime logOut) {
        List<TimeRecord> records = attendanceData.getOrDefault(employeeId, new ArrayList<>());
        // Find today's record with a log in but no log out
        TimeRecord recordToUpdate = records.stream()
                .filter(r -> r.date.equals(date) && r.logOut == null)
                .findFirst()
                .orElse(null);
        if (recordToUpdate != null) {
            recordToUpdate.logOut = logOut;
            attendanceData.put(employeeId, records);
            saveToCSV(employeeId); // Update the CSV with the latest data
        } else {
            System.out.println("No clock-in record found for employee " + employeeId + " on " + date);
        }
    }

    private void loadFromCSV() {
        try (Scanner scanner = new Scanner(new File(ATTENDANCE_CSV))) {
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // Skip header
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",", -1); // Keep empty fields
                if (parts.length == 6) {
                    try {
                        int employeeId = Integer.parseInt(parts[0].trim());
                        String dateStr = parts[3].trim();
                        String logInStr = parts[4].trim();
                        String logOutStr = parts[5].trim();

                        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
                        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm:ss a");

                        LocalDate date = LocalDate.parse(dateStr, dateFormatter);
                        LocalTime logIn = logInStr.isEmpty() ? null : LocalTime.parse(logInStr, timeFormatter);
                        LocalTime logOut = logOutStr.isEmpty() ? null : LocalTime.parse(logOutStr, timeFormatter);

                        List<TimeRecord> records = attendanceData.getOrDefault(employeeId, new ArrayList<>());
                        records.add(new TimeRecord(date, logIn, logOut));
                        attendanceData.put(employeeId, records);
                    } catch (NumberFormatException | DateTimeParseException e) {
                        System.err.println("Error parsing attendance data: " + line);
                    }
                } else {
                    System.err.println("Invalid line in CSV: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Attendance CSV not found. Creating a new one.");
        }
    }

    private void saveToCSV(int employeeId, String lastName, String firstName, TimeRecord newRecord) {
        List<String> lines = new ArrayList<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm:ss a");

        // Read existing lines except for the new record's employee
        try (Scanner scanner = new Scanner(new File(ATTENDANCE_CSV))) {
            if (scanner.hasNextLine()) {
                lines.add(scanner.nextLine()); // Keep header
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",", -1);
                if (parts.length > 0 && !parts[0].trim().equals(String.valueOf(employeeId))) {
                    lines.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            lines.add("Employee #,Last Name,First Name,Date,Log In,Log Out"); // Add header if file doesn't exist
        }

        // Add all records for this employee
        List<TimeRecord> records = attendanceData.get(employeeId);
        if (records != null) {
            for (TimeRecord record : records) {
                String logInStr = record.logIn != null ? record.logIn.format(timeFormatter) : "";
                String logOutStr = record.logOut != null ? record.logOut.format(timeFormatter) : "";
                lines.add(String.join(",",
                        String.valueOf(employeeId),
                        lastName,
                        firstName,
                        record.date.format(dateFormatter),
                        logInStr,
                        logOutStr));
            }
        }

        // Write back to CSV
        try (PrintWriter writer = new PrintWriter(new File(ATTENDANCE_CSV))) {
            for (String line : lines) {
                writer.println(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error saving to attendance CSV: " + e.getMessage());
        }
    }

    private void saveToCSV(int employeeId) {
        // Overloaded method to update CSV for an existing employee without needing name parameters
        Employee employee = new EmployeeRecords().viewEmployee(employeeId); // Temporary fetch to get names
        if (employee != null) {
            saveToCSV(employeeId, employee.getLastName(), employee.getFirstName(), null);
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
    protected Map<Integer, Employee> employees; // Key Integer (Employee ID)
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
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",", -1);
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
                "\"" + emp.getAddress() + "\"",
                emp.getPhoneNumber(),
                "\"" + emp.getSssNumber() + "\"",
                "\"" + emp.getPhilhealthNumber() + "\"",
                "\"" + emp.getTinNumber() + "\"",
                "\"" + emp.getPagIbigNumber() + "\"",
                "\"" + emp.getStatus()  + "\"",
                "\"" + emp.getPosition()  + "\"",
                "\"" + emp.getImmediateSupervisor()  + "\"",
                String.format("%.2f", emp.getBasicSalary()),
                String.format("%.2f", emp.getRiceSubsidy()),
                String.format("%.2f", emp.getPhoneAllowance()),
                String.format("%.2f", emp.getClothingAllowance()),
                String.format("%.2f", emp.getGrossSemiMonthlyPay()),
                String.format("%.2f", emp.getHourlyRate())
        );
    }

    public void loadFromCSV() {
        try (Scanner scanner = new Scanner(new File(CSV_FILE))) {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
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
                        String phoneNumber = tokens[5].trim().replace("-", "").replaceAll("[^\\d.]", "");
                        String sssNumber = tokens[6].trim().replace("-", "").replaceAll("[^\\d.]", "");
                        String philhealthNumber = tokens[7].replace("-", "").replaceAll("[^\\d.]", "");
                        String tinNumber = tokens[8].replace("-", "").replaceAll("[^\\d.]", "");
                        String pagIbigNumber = tokens[9].replace("-", "").replaceAll("[^\\d.]", "");
                        String status = tokens[10].trim();
                        String position = tokens[11].trim();
                        String immediateSupervisor = tokens[12].trim();
                        String basicSalaryStr = tokens[13].trim().replace(",", "").replaceAll("[^\\d.]", "");
                        String riceSubsidyStr = tokens[14].trim().replace(",", "").replaceAll("[^\\d.]", "");
                        String phoneAllowanceStr = tokens[15].trim().replace(",", "").replaceAll("[^\\d.]", "");
                        String clothingAllowanceStr = tokens[16].trim().replace(",", "").replaceAll("[^\\d.]", "");
                        String grossSemiMonthlyPayStr = tokens[17].trim().replace(",", "").replaceAll("[^\\d.]", "");
                        String hourlyRateStr = tokens[18].trim().replaceAll("[^\\d.]", ""); //Hourly rate can have .

                        double basicSalary = Double.parseDouble(basicSalaryStr.isEmpty() ? "0" : basicSalaryStr); //Default to 0 if empty after cleaning
                        double riceSubsidy = Double.parseDouble(riceSubsidyStr.isEmpty() ? "0" : riceSubsidyStr);
                        double phoneAllowance = Double.parseDouble(phoneAllowanceStr.isEmpty() ? "0" : phoneAllowanceStr);
                        double clothingAllowance = Double.parseDouble(clothingAllowanceStr.isEmpty() ? "0" : clothingAllowanceStr);
                        double grossSemiMonthlyPay = Double.parseDouble(grossSemiMonthlyPayStr.isEmpty() ? "0" : grossSemiMonthlyPayStr);
                        double hourlyRate = Double.parseDouble(hourlyRateStr.isEmpty() ? "0" : hourlyRateStr);


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
            writer.println("Employee,Last Name,First Name,Birthday,Address,Phone Number,SSS #,Philhealth #,TIN #,Pag-ibig #,Status,Position,Immediate Supervisor,Basic Salary,Rice Subsidy,Phone Allowance,Clothing Allowance,Gross Semi-monthly Pay, Hourly Rate");
            for (Map.Entry<Integer, Employee> entry : employees.entrySet()) {
                writer.println(formatEmployeeToCSV(entry.getValue()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

//                     _                            ___            __  _
//   ____ ___  ____ _(_)___     ____ _____  ____  / (_)________ _/ /_(_)___  ____
//  / __ `__ \/ __ `/ / __ \   / __ `/ __ \/ __ \/ / / ___/ __ `/ __/ / __ \/ __ \
// / / / / / / /_/ / / / / /  / /_/ / /_/ / /_/ / / / /__/ /_/ / /_/ / /_/ / / / /
///_/ /_/ /_/\__,_/_/_/ /_/   \__,_/ .___/ .___/_/_/\___/\__,_/\__/_/\____/_/ /_/
//                                /_/   /_/
public class PayrollApplication {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName()); // Set cross-platform L&F
        } catch (Exception e) {
            e.printStackTrace(); // Handle L&F setup errors
        }
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
    private static final Dimension BUTTON_SIZE = new Dimension(150, 25);
    private DefaultListModel<LeaveRequest> leaveRequestListModel = new DefaultListModel<>();
    private Employee loggedInEmployee; // To track logged-in employee
    private PayrollRecords payrollRecords;
    private Map<String, Integer> failedLoginAttempts;


    public PayrollGUI() {
        loginManager = new LoginManager();
        payrollSetup = new PayrollSetup();
        employeeRecords = new EmployeeRecords();
        leaveManagement = new LeaveManagement();
        attendanceRecords = new TimeAndAttendanceRecords();
        timeAndAttendance = new TimeAndAttendance(attendanceRecords); // Pass records to constructor
        payrollRecords = new PayrollRecords();
        loggedInEmployee = null;
        failedLoginAttempts = new HashMap<>();
    }

     private void generatePayslipPDF(PayrollData payslip, JFrame parentFrame) throws IOException {
     JFileChooser fileChooser = new JFileChooser();
     fileChooser.setDialogTitle("Save Payslip PDF");
     fileChooser.setSelectedFile(new File("Payslip_" + payslip.getEmployeeId() + "_" +
         payslip.getRunDate().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".pdf"));

     int userSelection = fileChooser.showSaveDialog(parentFrame);
     if (userSelection != JFileChooser.APPROVE_OPTION) {
         return; // User canceled the save operation
     }

     File fileToSave = fileChooser.getSelectedFile();
     String filePath = fileToSave.getAbsolutePath();
     if (!filePath.endsWith(".pdf")) {
         filePath += ".pdf";
     }

     // Create PDF using iText
     PdfWriter writer = new PdfWriter(filePath);
     PdfDocument pdf = new PdfDocument(writer);
     Document document = new Document(pdf);

     // Add content to the PDF
     document.add(new Paragraph(new Text("Payslip").setBold().setFontSize(16)));
     document.add(new Paragraph("Pay Date: " + payslip.getRunDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
     document.add(new Paragraph("Employee: " + payslip.getFirstName() + " " + payslip.getLastName() +
         " (ID: " + payslip.getEmployeeId() + ")"));
     document.add(new Paragraph("Gross Pay: $" + String.format("%.2f", payslip.getGrossPay())));
     document.add(new Paragraph("Net Pay: $" + String.format("%.2f", payslip.getNetPay())));
     document.add(new Paragraph("Deductions:"));
     document.add(new Paragraph("  BIR: $" + String.format("%.2f", payslip.getBirDeduction())));
     document.add(new Paragraph("  SSS: $" + String.format("%.2f", payslip.getSssDeduction())));
     document.add(new Paragraph("  PhilHealth: $" + String.format("%.2f", payslip.getPhilHealthDeduction())));
     document.add(new Paragraph("  Pag-IBIG: $" + String.format("%.2f", payslip.getPagIbigDeduction())));

     // Close the document
     document.close();
 }

  // login pane
    public void createLoginGUI() {
        JFrame loginFrame = new JFrame("Payroll App Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(430, 180);

        JPanel panel = new JPanel();
        loginFrame.add(panel);
        placeLoginComponents(panel);

        loginFrame.setVisible(true);
    }
    private void placeLoginComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 120, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(130, 20, 250, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(130, 50, 250, 25);
        panel.add(passwordText);

        JButton loginButton = createButton("Login", "Log in to the Payroll Application");
        int panelWidth = 609;
        int buttonWidth = BUTTON_SIZE.width;
        int xPosition = (panelWidth - buttonWidth) / 2;
        loginButton.setBounds(xPosition, 80, BUTTON_SIZE.width, BUTTON_SIZE.height);
        panel.add(loginButton);

        JLabel messageLabel = new JLabel("");
        messageLabel.setBounds(10, 110, 400, 25);
        panel.add(messageLabel);

        loginManager.ensureITAdminExists(); // Ensure IT admin account exists

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());

                if (loginManager.validateLogin(username, password)) {
                    messageLabel.setText("");
                    failedLoginAttempts.remove(username); // Reset failed attempts on successful login
                    if (username.equals("admin1")) { // Admin login
                        accessAdminPortal();
                        loggedInEmployee = null;
                    } else if (username.equals("itadmin")) { // IT admin login
                        accessCredentialManagementPortal();
                        loggedInEmployee = null;
                    } else {
                        try {
                            int employeeId = Integer.parseInt(username); // Username is employeeId
                            System.out.println("Attempting employee login with Employee ID: " + employeeId);
                            loggedInEmployee = employeeRecords.viewEmployee(employeeId);
                            if (loggedInEmployee != null) {
                                accessEmployeePortal();
                            } else {
                                messageLabel.setText("Employee not found for ID: " + employeeId);
                            }
                        } catch (NumberFormatException ex) {
                            messageLabel.setText("Username must be a valid Employee ID.");
                        }
                    }
                } else {
                    // Increment failed login attempts
                    int attempts = failedLoginAttempts.getOrDefault(username, 0) + 1;
                    failedLoginAttempts.put(username, attempts);

                    // Update message based on number of attempts
                    if (attempts >= 2) {
                        messageLabel.setText("Invalid username or password. Need help? Contact your administrator.");
                    } else {
                        messageLabel.setText("Invalid username or password.");
                    }
                }
            }
        });
    }

    //              __          _                           __        __
    //  ____ _____/ /___ ___  (_)___     ____  ____  _____/ /_____ _/ /
    // / __ `/ __  / __ `__ \/ / __ \   / __ \/ __ \/ ___/ __/ __ `/ /
    /// /_/ / /_/ / / / / / / / / / /  / /_/ / /_/ / /  / /_/ /_/ / /
    //\__,_/\__,_/_/ /_/ /_/_/_/ /_/  / .___/\____/_/   \__/\__,_/_/
    //                               /_/

    public void accessAdminPortal() {
        JFrame adminFrame = new JFrame("Payroll Admin Portal");
        adminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminFrame.setSize(900, 600);
        adminFrame.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome, Admin", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        adminFrame.add(welcomeLabel, BorderLayout.NORTH);

        JTabbedPane tabbedPane = new JTabbedPane();

        // (っ◔◡◔)っ ♥ payroll management ♥ //

        JPanel payrollPanel = new JPanel(new BorderLayout(10, 10));
        payrollPanel.setBorder(new EmptyBorder(10, 10, 10, 10));


        JPanel configPanel = new JPanel(new GridBagLayout());
        configPanel.setBorder(BorderFactory.createTitledBorder("Payroll Configuration"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10); // Uniform padding
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // font for labels and values
        Font labelFont = new Font("Arial", Font.PLAIN, 12);
        Font valueFont = new Font("Arial", Font.BOLD, 12);

        // Mode of Payment (Dropdown)
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel modeOfPaymentLabel = new JLabel("Mode of Payment:");
        modeOfPaymentLabel.setFont(labelFont);
        configPanel.add(modeOfPaymentLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        String[] modeOptions = {"Direct Deposit", "Cash", "Checks"};
        JComboBox<String> modeOfPaymentComboBox = new JComboBox<>(modeOptions);
        modeOfPaymentComboBox.setFont(valueFont);
        modeOfPaymentComboBox.setSelectedItem(payrollSetup.getModeOfPayment());
        configPanel.add(modeOfPaymentComboBox, gbc);

        modeOfPaymentComboBox.addActionListener(e -> {
            String selectedMode = (String) modeOfPaymentComboBox.getSelectedItem();
            payrollSetup.setModeOfPayment(selectedMode);
            JOptionPane.showMessageDialog(adminFrame, "Mode of payment updated to: " + selectedMode);
        });

        // Pay Period (Dropdown)
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel payPeriodLabel = new JLabel("Pay Period:");
        payPeriodLabel.setFont(labelFont);
        configPanel.add(payPeriodLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        String[] periodOptions = {"Weekly", "Bi-Weekly", "Semi-Monthly", "Monthly"};
        JComboBox<String> payPeriodComboBox = new JComboBox<>(periodOptions);
        payPeriodComboBox.setFont(valueFont);
        payPeriodComboBox.setSelectedItem(payrollSetup.getPayPeriod());
        configPanel.add(payPeriodComboBox, gbc);

        payPeriodComboBox.addActionListener(e -> {
            String selectedPeriod = (String) payPeriodComboBox.getSelectedItem();
            payrollSetup.setPayPeriod(selectedPeriod);
            JOptionPane.showMessageDialog(adminFrame, "Pay period updated to: " + selectedPeriod);
        });

        // Tax Rate
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel taxRateLabel = new JLabel("Tax Rate (BIR):");
        taxRateLabel.setFont(labelFont);
        configPanel.add(taxRateLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        JLabel currentTaxRate = new JLabel(String.format("%.2f%%", BIRDeduction.getRate() * 100)); // Use getter
        currentTaxRate.setFont(valueFont);
        configPanel.add(currentTaxRate, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        JButton modifyTaxButton = createButton("Modify", "Change the BIR tax rate");
        modifyTaxButton.setPreferredSize(new Dimension(100, 25));
        configPanel.add(modifyTaxButton, gbc);

        modifyTaxButton.addActionListener(e -> {
            String taxInput = JOptionPane.showInputDialog(adminFrame, "Enter new BIR tax rate (as a percentage, e.g., 15 for 15%):",
                    String.format("%.2f", BIRDeduction.getRate() * 100));
            if (taxInput != null && !taxInput.trim().isEmpty()) {
                try {
                    double newRate = Double.parseDouble(taxInput.trim()) / 100.0; // Convert percentage to decimal
                    BIRDeduction.setRate(newRate); // Use the setter
                    currentTaxRate.setText(String.format("%.2f%%", BIRDeduction.getRate() * 100));
                    JOptionPane.showMessageDialog(adminFrame, "BIR tax rate updated to " + String.format("%.2f%%", newRate * 100));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(adminFrame, "Invalid number format. Please enter a valid percentage.", "Input Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(adminFrame, ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Payroll Overview Panel
        JPanel overviewPanel = new JPanel(new BorderLayout(5, 5));
        overviewPanel.setBorder(BorderFactory.createTitledBorder("Latest Payroll Run Overview"));
        JTextPane overviewTextPane = new JTextPane();
        overviewTextPane.setContentType("text/html");
        overviewTextPane.setEditable(false);
        overviewTextPane.setFont(new Font("Arial", Font.PLAIN, 12));
        JScrollPane overviewScrollPane = new JScrollPane(overviewTextPane);
        overviewPanel.add(overviewScrollPane, BorderLayout.CENTER);

        // Populate the latest payroll run overview
        List<PayrollData> payrollDataList = payrollRecords.getAllPayrollData();
        if (!payrollDataList.isEmpty()) {
            PayrollData latestPayroll = payrollDataList.get(payrollDataList.size() - 1); // Get the most recent payroll
            StringBuilder overview = new StringBuilder("<html><body>");
            overview.append("<h2>Latest Payroll Run: ").append(latestPayroll.getRunDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("</h2>");

            double totalGrossPay = 0;
            double totalNetPay = 0;
            double totalDeductions = 0;
            int employeeCount = 0;

            LocalDateTime latestRunDate = latestPayroll.getRunDate();
            for (PayrollData data : payrollDataList) {
                if (data.getRunDate().equals(latestRunDate)) {
                    totalGrossPay += data.getGrossPay();
                    totalNetPay += data.getNetPay();
                    totalDeductions += (data.getBirDeduction() + data.getSssDeduction() + data.getPhilHealthDeduction() + data.getPagIbigDeduction());
                    employeeCount++;
                }
            }

            overview.append("<p><b>Employee Count:</b> ").append(employeeCount).append("</p>")
                    .append("<p><b>Total Gross Pay:</b> $").append(String.format("%.2f", totalGrossPay)).append("</p>")
                    .append("<p><b>Total Net Pay:</b> $").append(String.format("%.2f", totalNetPay)).append("</p>")
                    .append("<p><b>Total Deductions:</b> $").append(String.format("%.2f", totalDeductions)).append("</p>")
                    .append("</body></html>");
            overviewTextPane.setText(overview.toString());
        } else {
            overviewTextPane.setText("<html><body><p>No payroll runs available.</p></body></html>");
        }

        // Buttons Panel (Bottom Section) - Unchanged
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JButton runPayrollButton = createButton("Run Payroll", "Initiate the payroll process");
        runPayrollButton.setPreferredSize(new Dimension(150, 30));
        buttonsPanel.add(runPayrollButton);
        runPayrollButton.addActionListener(e -> runPayroll());

        // Assemble the payroll panel
        payrollPanel.add(configPanel, BorderLayout.NORTH);
        payrollPanel.add(overviewPanel, BorderLayout.CENTER);
        payrollPanel.add(buttonsPanel, BorderLayout.SOUTH);
        tabbedPane.addTab("Payroll Management", payrollPanel);



        // --- (っ◔◡◔)っ ♥ Employee Management ♥ ---

        JPanel employeePanel = new JPanel(new BorderLayout(10, 10));
        employeePanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Employee List Panel
        JPanel employeeListPanel = new JPanel(new BorderLayout());
        employeeListPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        JList<Employee> employeeJList = new JList<>();
        JScrollPane employeeListScrollPane = new JScrollPane(employeeJList);
        DefaultListModel<Employee> employeeListModel = new DefaultListModel<>();
        for (Employee emp : employeeRecords.employees.values()) {
            employeeListModel.addElement(emp);
        }
        employeeJList.setModel(employeeListModel);
        employeeJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        employeeJList.setCellRenderer(new EmployeeListRenderer());
        employeeListPanel.add(employeeListScrollPane, BorderLayout.CENTER);
        employeePanel.add(employeeListPanel, BorderLayout.WEST);

        // Employee Search and Add Panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel searchLabel = new JLabel("Search Employee ID:");
        JTextField searchTextField = new JTextField(10);
        JButton searchButton = createButton("Search", "Find employee by ID");
        JButton addEmployeeButton = createButton("Add Employee", "Register a new employee");
        searchPanel.add(searchLabel);
        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);
        searchPanel.add(addEmployeeButton);
        employeePanel.add(searchPanel, BorderLayout.NORTH);

        // Employee Form Panel
        JPanel formPanel = new JPanel(new GridLayout(20, 2, 5, 5));
        formPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JTextField idField = new JTextField(); idField.setEditable(false);
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

        formPanel.add(new JLabel("Employee ID:")); formPanel.add(idField);
        formPanel.add(new JLabel("Last Name:")); formPanel.add(lastNameField);
        formPanel.add(new JLabel("First Name:")); formPanel.add(firstNameField);
        formPanel.add(new JLabel("Birthday (MM/DD/YYYY):")); formPanel.add(birthdayField);
        formPanel.add(new JLabel("Address:")); formPanel.add(addressField);
        formPanel.add(new JLabel("Phone Number:")); formPanel.add(phoneField);
        formPanel.add(new JLabel("SSS Number:")); formPanel.add(sssField);
        formPanel.add(new JLabel("Philhealth Number:")); formPanel.add(philhealthField);
        formPanel.add(new JLabel("TIN Number:")); formPanel.add(tinField);
        formPanel.add(new JLabel("Pag-ibig Number:")); formPanel.add(pagIbigField);
        formPanel.add(new JLabel("Status:")); formPanel.add(statusField);
        formPanel.add(new JLabel("Position:")); formPanel.add(positionField);
        formPanel.add(new JLabel("Immediate Supervisor:")); formPanel.add(supervisorField);
        formPanel.add(new JLabel("Basic Salary:")); formPanel.add(salaryField);
        formPanel.add(new JLabel("Rice Subsidy:")); formPanel.add(riceField);
        formPanel.add(new JLabel("Phone Allowance:")); formPanel.add(phoneAllowanceField);
        formPanel.add(new JLabel("Clothing Allowance:")); formPanel.add(clothingAllowanceField);
        formPanel.add(new JLabel("Gross Semi-Monthly Pay:")); formPanel.add(grossSemiMonthlyPayField);
        formPanel.add(new JLabel("Hourly Rate:")); formPanel.add(hourlyRateField);

        employeePanel.add(formPanel, BorderLayout.CENTER);

        // Button Panel
        JPanel employeeButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Renamed to employeeButtonPanel
        JButton saveButton = createButton("Save Changes", "Save the edited employee information");
        JButton deleteButton = createButton("Delete Employee", "Permanently delete the selected employee");
        JButton cancelButton = createButton("Cancel", "Clear the form");
        employeeButtonPanel.add(saveButton);
        employeeButtonPanel.add(deleteButton);
        employeeButtonPanel.add(cancelButton);
        employeePanel.add(employeeButtonPanel, BorderLayout.SOUTH);

        // Action Listeners
        employeeJList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Employee selectedEmployee = employeeJList.getSelectedValue();
                if (selectedEmployee != null) {
                    populateEmployeeForm(selectedEmployee, idField, lastNameField, firstNameField, birthdayField, addressField, phoneField, sssField, philhealthField, tinField, pagIbigField, statusField, positionField, supervisorField, salaryField, riceField, phoneAllowanceField, clothingAllowanceField, grossSemiMonthlyPayField, hourlyRateField);
                    saveButton.setText("Save Changes");
                    // Ensure Delete button is visible when an employee is selected
                    if (!employeeButtonPanel.isAncestorOf(deleteButton)) {
                        employeeButtonPanel.add(deleteButton, 1); // Add Delete button back at index 1
                        employeeButtonPanel.revalidate();
                        employeeButtonPanel.repaint();
                    }
                }
            }
        });

        searchButton.addActionListener(e -> {
            String employeeIdStr = searchTextField.getText();
            if (!employeeIdStr.trim().isEmpty()) {
                try {
                    int employeeIdToSearch = Integer.parseInt(employeeIdStr.trim());
                    Employee foundEmployee = employeeRecords.viewEmployee(employeeIdToSearch);
                    if (foundEmployee != null) {
                        populateEmployeeForm(foundEmployee, idField, lastNameField, firstNameField, birthdayField, addressField, phoneField, sssField, philhealthField, tinField, pagIbigField, statusField, positionField, supervisorField, salaryField, riceField, phoneAllowanceField, clothingAllowanceField, grossSemiMonthlyPayField, hourlyRateField);
                        for (int i = 0; i < employeeListModel.getSize(); i++) {
                            if (employeeListModel.getElementAt(i).getEmployeeId() == employeeIdToSearch) {
                                employeeJList.setSelectedIndex(i);
                                employeeJList.ensureIndexIsVisible(i);
                                break;
                            }
                        }
                        saveButton.setText("Save Changes");
                        // Ensure Delete button is visible after search
                        if (!employeeButtonPanel.isAncestorOf(deleteButton)) {
                            employeeButtonPanel.add(deleteButton, 1);
                            employeeButtonPanel.revalidate();
                            employeeButtonPanel.repaint();
                        }
                    } else {
                        JOptionPane.showMessageDialog(adminFrame, "Employee with ID " + employeeIdToSearch + " not found.", "Employee Not Found", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(adminFrame, "Invalid Employee ID format.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(adminFrame, "Please enter Employee ID to search.", "Input Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        addEmployeeButton.addActionListener(e -> {
            idField.setText("");
            lastNameField.setText("");
            firstNameField.setText("");
            birthdayField.setText("");
            addressField.setText("");
            phoneField.setText("");
            sssField.setText("");
            philhealthField.setText("");
            tinField.setText("");
            pagIbigField.setText("");
            statusField.setText("");
            positionField.setText("");
            supervisorField.setText("");
            salaryField.setText("");
            riceField.setText("");
            phoneAllowanceField.setText("");
            clothingAllowanceField.setText("");
            grossSemiMonthlyPayField.setText("");
            hourlyRateField.setText("");
            employeeJList.clearSelection();
            saveButton.setText("OK");
            // Remove Delete button when Add Employee is clicked
            employeeButtonPanel.remove(deleteButton);
            employeeButtonPanel.revalidate();
            employeeButtonPanel.repaint();
        });

        saveButton.addActionListener(e -> {
            try {
                if (saveButton.getText().equals("OK")) {
                    int newId = generateNewEmployeeId();
                    idField.setText(String.valueOf(newId));
                    Employee newEmployee = createEmployeeFromForm(idField, lastNameField, firstNameField, birthdayField, addressField, phoneField, sssField, philhealthField, tinField, pagIbigField, statusField, positionField, supervisorField, salaryField, riceField, phoneAllowanceField, clothingAllowanceField, grossSemiMonthlyPayField, hourlyRateField);
                    employeeRecords.addEmployee(newEmployee, newId);
                    employeeListModel.addElement(newEmployee);
                    JOptionPane.showMessageDialog(adminFrame, "Employee added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    // Re-add Delete button after saving new employee
                    if (!employeeButtonPanel.isAncestorOf(deleteButton)) {
                        employeeButtonPanel.add(deleteButton, 1);
                        employeeButtonPanel.revalidate();
                        employeeButtonPanel.repaint();
                    }
                } else {
                    Employee updatedEmployee = createEmployeeFromForm(idField, lastNameField, firstNameField, birthdayField, addressField, phoneField, sssField, philhealthField, tinField, pagIbigField, statusField, positionField, supervisorField, salaryField, riceField, phoneAllowanceField, clothingAllowanceField, grossSemiMonthlyPayField, hourlyRateField);
                    employeeRecords.editEmployee(Integer.parseInt(idField.getText()), updatedEmployee);
                    int selectedIndex = employeeJList.getSelectedIndex();
                    if (selectedIndex >= 0) {
                        employeeListModel.set(selectedIndex, updatedEmployee);
                    }
                    JOptionPane.showMessageDialog(adminFrame, "Employee information updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
                saveButton.setText("Save Changes");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(adminFrame, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(adminFrame, "Error processing employee: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        deleteButton.addActionListener(e -> {
            String idText = idField.getText();
            if (!idText.isEmpty()) {
                int employeeId = Integer.parseInt(idText);
                int confirm = JOptionPane.showConfirmDialog(adminFrame, "Are you sure you want to permanently delete employee ID " + employeeId + "? This action cannot be undone.", "Confirm Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (confirm == JOptionPane.YES_OPTION) {
                    employeeRecords.deleteEmployee(employeeId);
                    int selectedIndex = employeeJList.getSelectedIndex();
                    if (selectedIndex >= 0) {
                        employeeListModel.remove(selectedIndex);
                    }
                    idField.setText(""); lastNameField.setText(""); firstNameField.setText("");
                    birthdayField.setText(""); addressField.setText(""); phoneField.setText("");
                    sssField.setText(""); philhealthField.setText(""); tinField.setText("");
                    pagIbigField.setText(""); statusField.setText(""); positionField.setText("");
                    supervisorField.setText(""); salaryField.setText(""); riceField.setText("");
                    phoneAllowanceField.setText(""); clothingAllowanceField.setText("");
                    grossSemiMonthlyPayField.setText(""); hourlyRateField.setText("");
                    JOptionPane.showMessageDialog(adminFrame, "Employee deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(adminFrame, "No employee selected to delete.", "Selection Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> {
            idField.setText(""); lastNameField.setText(""); firstNameField.setText("");
            birthdayField.setText(""); addressField.setText(""); phoneField.setText("");
            sssField.setText(""); philhealthField.setText(""); tinField.setText("");
            pagIbigField.setText(""); statusField.setText(""); positionField.setText("");
            supervisorField.setText(""); salaryField.setText(""); riceField.setText("");
            phoneAllowanceField.setText(""); clothingAllowanceField.setText("");
            grossSemiMonthlyPayField.setText(""); hourlyRateField.setText("");
            employeeJList.clearSelection();
            saveButton.setText("Save Changes");
            // Re-add Delete button if it was removed
            if (!employeeButtonPanel.isAncestorOf(deleteButton)) {
                employeeButtonPanel.add(deleteButton, 1);
                employeeButtonPanel.revalidate();
                employeeButtonPanel.repaint();
            }
        });

        tabbedPane.addTab("Employee Management", employeePanel);

        // --- (っ◔◡◔)っ ♥ Reporting Tab ♥ ---

        JPanel reportingPanel = new JPanel(new BorderLayout(10, 10));
        reportingPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel startDateLabel = new JLabel("Start Date:");
        JSpinner startDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor startDateEditor = new JSpinner.DateEditor(startDateSpinner, "yyyy-MM-dd");
        startDateSpinner.setEditor(startDateEditor);

        JLabel endDateLabel = new JLabel("End Date:");
        JSpinner endDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor endDateEditor = new JSpinner.DateEditor(endDateSpinner, "yyyy-MM-dd");
        endDateSpinner.setEditor(endDateEditor);

        JButton clearFilterButton = createButton("Clear Filter", "Reset date filters");
        JButton runPayrollReportButton = createButton("Generate Report", "Generate payroll report for the selected date range");

        filterPanel.add(startDateLabel);
        filterPanel.add(startDateSpinner);
        filterPanel.add(endDateLabel);
        filterPanel.add(endDateSpinner);
        filterPanel.add(clearFilterButton);
        filterPanel.add(runPayrollReportButton);

        JPanel payrollListPanel = new JPanel(new BorderLayout());
        payrollListPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        JLabel payrollListLabel = new JLabel("All Payroll Runs:");
        DefaultListModel<String> payrollListModel = new DefaultListModel<>();
        JList<String> payrollJList = new JList<>(payrollListModel);
        JScrollPane payrollListScrollPane = new JScrollPane(payrollJList);
        payrollListPanel.add(payrollListLabel, BorderLayout.NORTH);
        payrollListPanel.add(payrollListScrollPane, BorderLayout.CENTER);

        Report report = new Report(attendanceRecords, employeeRecords, payrollRecords);
        List<PayrollData> allPayroll = payrollRecords.getAllPayrollData();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (PayrollData data : allPayroll) {
            payrollListModel.addElement(data.getRunDate().format(dateFormatter));
        }

        JTextPane payrollDetailsPane = new JTextPane();
        payrollDetailsPane.setContentType("text/html");
        payrollDetailsPane.setEditable(false);
        JScrollPane detailsScrollPane = new JScrollPane(payrollDetailsPane);
        detailsScrollPane.setPreferredSize(new Dimension(400, 150));
        reportingPanel.add(detailsScrollPane, BorderLayout.SOUTH);

        reportingPanel.add(filterPanel, BorderLayout.NORTH);
        reportingPanel.add(payrollListPanel, BorderLayout.CENTER);

        runPayrollReportButton.addActionListener(e -> {
            Date startDateValue = (Date) startDateSpinner.getValue();
            Date endDateValue = (Date) endDateSpinner.getValue();
            LocalDate startDate = startDateValue.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endDate = endDateValue.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if (startDate.isAfter(endDate)) {
                JOptionPane.showMessageDialog(adminFrame, "Start date must be before end date.", "Invalid Date Range", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String reportText = report.generatePayrollReport(startDate, endDate);
            JTextPane reportTextPane = new JTextPane();
            reportTextPane.setContentType("text/html");
            reportTextPane.setText(reportText);
            reportTextPane.setEditable(false);
            JScrollPane reportScrollPane = new JScrollPane(reportTextPane);
            JDialog reportDialog = new JDialog(adminFrame, "Payroll Report", true);
            reportDialog.setSize(600, 400);
            reportDialog.add(reportScrollPane);
            reportDialog.setLocationRelativeTo(adminFrame);
            reportDialog.setVisible(true);
        });

        clearFilterButton.addActionListener(e -> {
            startDateSpinner.setValue(new Date());
            endDateSpinner.setValue(new Date());
            payrollDetailsPane.setText("");
        });

        payrollJList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedIndex = payrollJList.getSelectedIndex();
                if (selectedIndex >= 0) {
                    PayrollData selectedPayroll = allPayroll.get(selectedIndex);
                    StringBuilder details = new StringBuilder("<html><body>");
                    details.append("<h2>Payroll Run: ").append(selectedPayroll.getRunDate().format(dateFormatter)).append("</h2>")
                            .append("<b>").append(selectedPayroll.getFirstName()).append(" ").append(selectedPayroll.getLastName())
                            .append(" (ID: ").append(selectedPayroll.getEmployeeId()).append(")</b><br>")
                            .append("Gross Pay: $").append(String.format("%.2f", selectedPayroll.getGrossPay())).append("<br>")
                            .append("Net Pay: $").append(String.format("%.2f", selectedPayroll.getNetPay())).append("<br>")
                            .append("Deductions:<br>")
                            .append("  BIR: $").append(String.format("%.2f", selectedPayroll.getBirDeduction())).append("<br>")
                            .append("  SSS: $").append(String.format("%.2f", selectedPayroll.getSssDeduction())).append("<br>")
                            .append("  PhilHealth: $").append(String.format("%.2f", selectedPayroll.getPhilHealthDeduction())).append("<br>")
                            .append("  Pag-IBIG: $").append(String.format("%.2f", selectedPayroll.getPagIbigDeduction())).append("<br>")
                            .append("</body></html>");
                    payrollDetailsPane.setText(details.toString());
                }
            }
        });

        tabbedPane.addTab("Reporting", reportingPanel);

        // --- (っ◔◡◔)っ ♥ Leave Management Tab ♥ ---
        JPanel leavePanel = new JPanel(new BorderLayout(10, 10));
        leavePanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Filter Panel
        JPanel leaveFilterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        leaveFilterPanel.setBorder(BorderFactory.createTitledBorder("Filter Options"));
        JLabel filterLabel = new JLabel("Filter by Status:");
        filterLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        String[] statusOptions = {"All", "Pending", "Approved", "Denied"};
        JComboBox<String> statusFilter = new JComboBox<>(statusOptions);
        statusFilter.setFont(new Font("Arial", Font.PLAIN, 12));
        statusFilter.setPreferredSize(new Dimension(120, 25));
        leaveFilterPanel.add(filterLabel);
        leaveFilterPanel.add(statusFilter);

        // Leave Requests List Panel
        JPanel leaveRequestsPanel = new JPanel(new BorderLayout(5, 5));
        leaveRequestsPanel.setBorder(BorderFactory.createTitledBorder("Leave Requests"));
        DefaultListModel<LeaveRequest> leaveRequestListModel = new DefaultListModel<>();
        JList<LeaveRequest> leaveJList = new JList<>(leaveRequestListModel);
        leaveJList.setCellRenderer(new LeaveRequestListRenderer()); // Custom renderer
        leaveJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane leaveListScrollPane = new JScrollPane(leaveJList);
        leaveListScrollPane.setPreferredSize(new Dimension(300, 400));
        leaveRequestsPanel.add(leaveListScrollPane, BorderLayout.CENTER);

        // Details Panel
        JPanel detailsPanel = new JPanel(new GridBagLayout());
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Request Details"));
        GridBagConstraints leaveGbc = new GridBagConstraints(); // Renamed to avoid conflict
        leaveGbc.insets = new Insets(5, 10, 5, 10);
        leaveGbc.anchor = GridBagConstraints.WEST;
        leaveGbc.fill = GridBagConstraints.HORIZONTAL;
        leaveGbc.weightx = 1.0;

        Font leaveLabelFont = new Font("Arial", Font.PLAIN, 12); // Renamed to avoid conflict
        Font leaveValueFont = new Font("Arial", Font.BOLD, 12);  // Renamed to avoid conflict

        JLabel requestIdLabel = new JLabel("Request ID:");
        requestIdLabel.setFont(leaveLabelFont);
        JLabel requestIdValue = new JLabel("");
        requestIdValue.setFont(leaveValueFont);

        JLabel employeeIdLabel = new JLabel("Employee ID:");
        employeeIdLabel.setFont(leaveLabelFont);
        JLabel employeeIdValue = new JLabel("");
        employeeIdValue.setFont(leaveValueFont);

        JLabel employeeNameLabel = new JLabel("Employee Name:");
        employeeNameLabel.setFont(leaveLabelFont);
        JLabel employeeNameValue = new JLabel("");
        employeeNameValue.setFont(leaveValueFont);

        JLabel leaveStartDateLabel = new JLabel("Start Date:");
        leaveStartDateLabel.setFont(leaveLabelFont);
        JLabel startDateValue = new JLabel("");
        startDateValue.setFont(leaveValueFont);

        JLabel leaveEndDateLabel = new JLabel("End Date:");
        leaveEndDateLabel.setFont(leaveLabelFont);
        JLabel endDateValue = new JLabel("");
        endDateValue.setFont(leaveValueFont);

        JLabel reasonLabel = new JLabel("Reason:");
        reasonLabel.setFont(leaveLabelFont);
        JTextArea reasonValue = new JTextArea(3, 20);
        reasonValue.setFont(leaveValueFont);
        reasonValue.setEditable(false);
        reasonValue.setLineWrap(true);
        reasonValue.setWrapStyleWord(true);
        JScrollPane reasonScrollPane = new JScrollPane(reasonValue);

        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setFont(leaveLabelFont);
        JLabel statusValue = new JLabel("");
        statusValue.setFont(leaveValueFont);

        // Layout details panel
        leaveGbc.gridx = 0; leaveGbc.gridy = 0;
        detailsPanel.add(requestIdLabel, leaveGbc);
        leaveGbc.gridx = 1;
        detailsPanel.add(requestIdValue, leaveGbc);

        leaveGbc.gridx = 0; leaveGbc.gridy = 1;
        detailsPanel.add(employeeIdLabel, leaveGbc);
        leaveGbc.gridx = 1;
        detailsPanel.add(employeeIdValue, leaveGbc);

        leaveGbc.gridx = 0; leaveGbc.gridy = 2;
        detailsPanel.add(employeeNameLabel, leaveGbc);
        leaveGbc.gridx = 1;
        detailsPanel.add(employeeNameValue, leaveGbc);

        leaveGbc.gridx = 0; leaveGbc.gridy = 3;
        detailsPanel.add(leaveStartDateLabel, leaveGbc);
        leaveGbc.gridx = 1;
        detailsPanel.add(startDateValue, leaveGbc);

        leaveGbc.gridx = 0; leaveGbc.gridy = 4;
        detailsPanel.add(leaveEndDateLabel, leaveGbc);
        leaveGbc.gridx = 1;
        detailsPanel.add(endDateValue, leaveGbc);

        leaveGbc.gridx = 0; leaveGbc.gridy = 5;
        detailsPanel.add(reasonLabel, leaveGbc);
        leaveGbc.gridx = 1;
        detailsPanel.add(reasonScrollPane, leaveGbc);

        leaveGbc.gridx = 0; leaveGbc.gridy = 6;
        detailsPanel.add(statusLabel, leaveGbc);
        leaveGbc.gridx = 1;
        detailsPanel.add(statusValue, leaveGbc);

        // Buttons Panel
        JPanel leaveButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JButton approveButton = createButton("Approve", "Approve selected leave request");
        JButton denyButton = createButton("Deny", "Deny selected leave request");
        leaveButtonsPanel.add(approveButton);
        leaveButtonsPanel.add(denyButton);

        // Split Pane for List and Details
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leaveRequestsPanel, detailsPanel);
        splitPane.setDividerLocation(300);
        splitPane.setResizeWeight(0.4);

        // Assemble Leave Panel
        leavePanel.add(leaveFilterPanel, BorderLayout.NORTH);
        leavePanel.add(splitPane, BorderLayout.CENTER);
        leavePanel.add(leaveButtonsPanel, BorderLayout.SOUTH);

        // Populate initial list
        refreshLeaveRequestListModel("All", leaveRequestListModel);


        // Action Listeners
        approveButton.addActionListener(e -> {
            LeaveRequest selectedRequest = leaveJList.getSelectedValue();
            if (selectedRequest != null) {
                int requestId = selectedRequest.getRequestId();
                leaveManagement.approveLeave(requestId);
                JOptionPane.showMessageDialog(adminFrame, "Leave request approved.", "Leave Approved", JOptionPane.INFORMATION_MESSAGE);
                refreshLeaveRequestListModel((String) statusFilter.getSelectedItem(), leaveRequestListModel);
                // Clear details if the approved request is no longer in the filtered list
                if (!leaveRequestListModel.contains(selectedRequest)) {
                    clearDetails(requestIdValue, employeeIdValue, employeeNameValue, startDateValue, endDateValue, reasonValue, statusValue);
                } else {
                    populateDetails(selectedRequest, requestIdValue, employeeIdValue, employeeNameValue, startDateValue, endDateValue, reasonValue, statusValue);
                }
            } else {
                JOptionPane.showMessageDialog(adminFrame, "No leave request selected for approval.", "Selection Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        denyButton.addActionListener(e -> {
            LeaveRequest selectedRequest = leaveJList.getSelectedValue();
            if (selectedRequest != null) {
                int requestId = selectedRequest.getRequestId();
                leaveManagement.denyLeave(requestId);
                JOptionPane.showMessageDialog(adminFrame, "Leave request denied.", "Leave Denied", JOptionPane.INFORMATION_MESSAGE);
                refreshLeaveRequestListModel((String) statusFilter.getSelectedItem(), leaveRequestListModel);
                // Clear details if the denied request is no longer in the filtered list
                if (!leaveRequestListModel.contains(selectedRequest)) {
                    clearDetails(requestIdValue, employeeIdValue, employeeNameValue, startDateValue, endDateValue, reasonValue, statusValue);
                } else {
                    populateDetails(selectedRequest, requestIdValue, employeeIdValue, employeeNameValue, startDateValue, endDateValue, reasonValue, statusValue);
                }
            } else {
                JOptionPane.showMessageDialog(adminFrame, "No leave request selected for denial.", "Selection Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        statusFilter.addActionListener(e -> {
            String selectedStatus = (String) statusFilter.getSelectedItem();
            refreshLeaveRequestListModel(selectedStatus, leaveRequestListModel);
            clearDetails(requestIdValue, employeeIdValue, employeeNameValue, startDateValue, endDateValue, reasonValue, statusValue);
            leaveJList.clearSelection();
        });

        leaveJList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                LeaveRequest selectedRequest = leaveJList.getSelectedValue();
                if (selectedRequest != null) {
                    populateDetails(selectedRequest, requestIdValue, employeeIdValue, employeeNameValue, startDateValue, endDateValue, reasonValue, statusValue);
                } else {
                    clearDetails(requestIdValue, employeeIdValue, employeeNameValue, startDateValue, endDateValue, reasonValue, statusValue);
                }
            }
        });

        tabbedPane.addTab("Leave Management", leavePanel);

        adminFrame.add(tabbedPane, BorderLayout.CENTER);
        adminFrame.setVisible(true);
        adminFrame.setLocationRelativeTo(null);
    }

    // Helper method to generate a new employee ID
    private int generateNewEmployeeId() {
        if (employeeRecords.employees.isEmpty()) {
            return 1;
        }
        return Collections.max(employeeRecords.employees.keySet()) + 1;
    }

    private void refreshLeaveRequestListModel(String statusFilter, DefaultListModel<LeaveRequest> listModel) {
        listModel.clear();
        List<LeaveRequest> allRequests = leaveManagement.getAllLeaveRequests();
        for (LeaveRequest request : allRequests) {
            if (statusFilter.equals("All")) {
                listModel.addElement(request);
            } else if (request.getStatus().equalsIgnoreCase(statusFilter)) {
                listModel.addElement(request);
            }
        }
    }

    // populate to form
    private void populateEmployeeForm(Employee employee, JTextField idField, JTextField lastNameField, JTextField firstNameField, JTextField birthdayField, JTextField addressField, JTextField phoneField, JTextField sssField, JTextField philhealthField, JTextField tinField, JTextField pagIbigField, JTextField statusField, JTextField positionField, JTextField supervisorField, JTextField salaryField, JTextField riceField, JTextField phoneAllowanceField, JTextField clothingAllowanceField, JTextField grossSemiMonthlyPayField, JTextField hourlyRateField) {
        idField.setText(String.valueOf(employee.getEmployeeId()));
        lastNameField.setText(employee.getLastName());
        firstNameField.setText(employee.getFirstName());
        birthdayField.setText(employee.getBirthday());
        addressField.setText(employee.getAddress());
        phoneField.setText(employee.getPhoneNumber());
        sssField.setText(employee.getSssNumber());
        philhealthField.setText(employee.getPhilhealthNumber());
        tinField.setText(employee.getTinNumber());
        pagIbigField.setText(employee.getPagIbigNumber());
        statusField.setText(employee.getStatus());
        positionField.setText(employee.getPosition());
        supervisorField.setText(employee.getImmediateSupervisor());
        salaryField.setText(String.valueOf(employee.getBasicSalary()));
        riceField.setText(String.valueOf(employee.getRiceSubsidy()));
        phoneAllowanceField.setText(String.valueOf(employee.getPhoneAllowance()));
        clothingAllowanceField.setText(String.valueOf(employee.getClothingAllowance()));
        grossSemiMonthlyPayField.setText(String.valueOf(employee.getGrossSemiMonthlyPay()));
        hourlyRateField.setText(String.valueOf(employee.getHourlyRate()));
    }

    private Employee createEmployeeFromForm(JTextField idField, JTextField lastNameField, JTextField firstNameField, JTextField birthdayField, JTextField addressField, JTextField phoneField, JTextField sssField, JTextField philhealthField, JTextField tinField, JTextField pagIbigField, JTextField statusField, JTextField positionField, JTextField supervisorField, JTextField salaryField, JTextField riceField, JTextField phoneAllowanceField, JTextField clothingAllowanceField, JTextField grossSemiMonthlyPayField, JTextField hourlyRateField) {
        // --- Input Validation --- //
        if (lastNameField.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Last Name cannot be empty.");
        }
        if (firstNameField.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("First Name cannot be empty.");
        }
        if (birthdayField.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Birthday cannot be empty.");
        }
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate.parse(birthdayField.getText().trim(), dateFormatter); // Validate date format
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Birthday must be in MM/DD/YYYY format.");
        }
        if (addressField.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Address cannot be empty.");
        }
        if (phoneField.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Phone Number cannot be empty.");
        }
        if (!phoneField.getText().trim().matches("\\d+")) { // Simple digit-only phone validation
            throw new IllegalArgumentException("Phone Number must contain only digits.");
        }
        if (sssField.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("SSS Number cannot be empty.");
        }
        if (philhealthField.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Philhealth Number cannot be empty.");
        }
        if (tinField.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("TIN Number cannot be empty.");
        }
        if (pagIbigField.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Pag-ibig Number cannot be empty.");
        }
        if (statusField.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be empty.");
        }
        if (positionField.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Position cannot be empty.");
        }
        if (supervisorField.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Immediate Supervisor cannot be empty.");
        }

        try {
            Double.parseDouble(salaryField.getText().trim());
            Double.parseDouble(riceField.getText().trim());
            Double.parseDouble(phoneAllowanceField.getText().trim());
            Double.parseDouble(clothingAllowanceField.getText().trim());
            Double.parseDouble(grossSemiMonthlyPayField.getText().trim());
            Double.parseDouble(hourlyRateField.getText().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Salary, Rice Subsidy, Phone Allowance, Clothing Allowance, Gross Semi-Monthly Pay, and Hourly Rate must be valid numbers.");
        }


        return new Employee(
                Integer.parseInt(idField.getText()), // ID from non-editable field
                lastNameField.getText(), firstNameField.getText(), birthdayField.getText(),
                addressField.getText(), phoneField.getText(), sssField.getText(),
                philhealthField.getText(), tinField.getText(), pagIbigField.getText(),
                statusField.getText(), positionField.getText(), supervisorField.getText(),
                Double.parseDouble(salaryField.getText()), Double.parseDouble(riceField.getText()),
                Double.parseDouble(phoneAllowanceField.getText()), Double.parseDouble(clothingAllowanceField.getText()),
                Double.parseDouble(grossSemiMonthlyPayField.getText()), Double.parseDouble(hourlyRateField.getText())
        );



    }
    // Custom renderers
    private static class EmployeeListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Employee) {
                Employee employee = (Employee) value;
                String displayName = employee.getLastName() + ", " + employee.getFirstName().charAt(0) + ".";
                label.setText(displayName);
            }
            return label;
        }
    }

    private static class LeaveRequestListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof LeaveRequest) {
                LeaveRequest request = (LeaveRequest) value;
                String displayText = String.format("%d, %s, %c., %s",
                        request.getEmployeeId(),
                        request.getEmployeeName().split(" ")[1], // Last Name
                        request.getEmployeeName().split(" ")[0].charAt(0), // First Initial
                        request.getStatus());
                label.setText(displayText);
            }
            return label;
        }
    }

    // Helper method to populate details
    private void populateDetails(LeaveRequest request, JLabel requestIdValue, JLabel employeeIdValue, JLabel employeeNameValue,
                                 JLabel startDateValue, JLabel endDateValue, JTextArea reasonValue, JLabel statusValue) {
        requestIdValue.setText(String.valueOf(request.getRequestId()));
        employeeIdValue.setText(String.valueOf(request.getEmployeeId()));
        employeeNameValue.setText(request.getEmployeeName());
        startDateValue.setText(request.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        endDateValue.setText(request.getEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        reasonValue.setText(request.getReason());
        statusValue.setText(request.getStatus());
    }

    // Helper method to clear details
    private void clearDetails(JLabel requestIdValue, JLabel employeeIdValue, JLabel employeeNameValue,
                              JLabel startDateValue, JLabel endDateValue, JTextArea reasonValue, JLabel statusValue) {
        requestIdValue.setText("");
        employeeIdValue.setText("");
        employeeNameValue.setText("");
        startDateValue.setText("");
        endDateValue.setText("");
        reasonValue.setText("");
        statusValue.setText("");
    }

    //                         __                                          __        __
    //  ___  ____ ___  ____  / /___  __  _____  ___     ____  ____  _____/ /_____ _/ /
    // / _ \/ __ `__ \/ __ \/ / __ \/ / / / _ \/ _ \   / __ \/ __ \/ ___/ __/ __ `/ /
    ///  __/ / / / / / /_/ / / /_/ / /_/ /  __/  __/  / /_/ / /_/ / /  / /_/ /_/ / /
    //\___/_/ /_/ /_/ .___/_/\____/\__, /\___/\___/  / .___/\____/_/   \__/\__,_/_/
    //             /_/            /____/            /_/

    public void accessEmployeePortal() {
        JFrame employeeFrame = new JFrame("Employee Portal");
        employeeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        employeeFrame.setSize(900, 600);
        employeeFrame.setLayout(new BorderLayout());

        // Welcome Label
        JLabel welcomeLabel = new JLabel("Welcome, " + loggedInEmployee.getFirstName() + "!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        employeeFrame.add(welcomeLabel, BorderLayout.NORTH);

        // Main content panel with tabs
        JTabbedPane tabbedPane = new JTabbedPane();

        // --- (っ◔◡◔)っ ♥ Pay Tab ♥ ---
        JPanel payPanel = new JPanel(new BorderLayout(10, 10));
        payPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // All Payslips Scrollable List
        JPanel payslipsListPanel = new JPanel(new BorderLayout());
        payslipsListPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        JLabel payslipsLabel = new JLabel("All Payslips:");
        DefaultListModel<String> payslipListModel = new DefaultListModel<>();
        JList<String> payslipJList = new JList<>(payslipListModel);
        JScrollPane payslipScrollPane = new JScrollPane(payslipJList); // Define only once here
        payslipsListPanel.add(payslipsLabel, BorderLayout.NORTH);
        payslipsListPanel.add(payslipScrollPane, BorderLayout.CENTER);

        // Details Panel (for expanded payslip details)
        JTextPane payslipDetailsPane = new JTextPane();
        payslipDetailsPane.setContentType("text/html");
        payslipDetailsPane.setEditable(false);
        JScrollPane detailsScrollPane = new JScrollPane(payslipDetailsPane);
        detailsScrollPane.setPreferredSize(new Dimension(400, 200));
        payslipsListPanel.add(detailsScrollPane, BorderLayout.SOUTH);

        // Generate Payslip Button (moved below the list)
        JPanel payButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
         JButton downloadPayslipButton = createButton("Download as PDF", "Download the selected payslip as a PDF");
         downloadPayslipButton.setEnabled(false);
        JButton generatePayslipButton = createButton("Generate Payslip", "Generate payslip for a specific date range");
        payButtonPanel.add(generatePayslipButton);
         payButtonPanel.add(downloadPayslipButton);

        // Add components to payPanel
        payPanel.add(payslipsListPanel, BorderLayout.CENTER);
        payPanel.add(payButtonPanel, BorderLayout.SOUTH); // Button panel below the list
        tabbedPane.addTab("Pay", payPanel);


        // --- (っ◔◡◔)っ ♥ Leave Tab ♥ ---
        JPanel leavePanel = new JPanel(new BorderLayout(10, 10));
        leavePanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Filter Panel
        JPanel leaveFilterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel filterLabel = new JLabel("Filter by Status:");
        String[] statusOptions = {"All", "Pending", "Approved", "Denied"};
        JComboBox<String> statusFilter = new JComboBox<>(statusOptions);
        statusFilter.setSelectedItem("All"); // Default to "All"
        leaveFilterPanel.add(filterLabel);
        leaveFilterPanel.add(statusFilter);

        // Leave Requests List Panel
        JPanel leaveListPanel = new JPanel(new BorderLayout(5, 5));
        leaveListPanel.setBorder(BorderFactory.createTitledBorder("Your Leave Requests"));
        DefaultListModel<LeaveRequest> leaveListModel = new DefaultListModel<>();
        JList<LeaveRequest> leaveJList = new JList<>(leaveListModel);
        leaveJList.setCellRenderer(new LeaveRequestListRenderer());
        leaveJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane leaveScrollPane = new JScrollPane(leaveJList);
        leaveScrollPane.setPreferredSize(new Dimension(300, 400));
        leaveListPanel.add(leaveScrollPane, BorderLayout.CENTER);

        // Details Panel
        JPanel detailsPanel = new JPanel(new GridBagLayout());
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Request Details"));
        GridBagConstraints empLeaveGbc = new GridBagConstraints();
        empLeaveGbc.insets = new Insets(5, 10, 5, 10);
        empLeaveGbc.anchor = GridBagConstraints.WEST;
        empLeaveGbc.fill = GridBagConstraints.HORIZONTAL;
        empLeaveGbc.weightx = 1.0;

        Font empLeaveLabelFont = new Font("Arial", Font.PLAIN, 12);
        Font empLeaveValueFont = new Font("Arial", Font.BOLD, 12);

        JLabel empRequestIdLabel = new JLabel("Request ID:");
        empRequestIdLabel.setFont(empLeaveLabelFont);
        JLabel empRequestIdValue = new JLabel("");
        empRequestIdValue.setFont(empLeaveValueFont);

        JLabel empEmployeeIdLabel = new JLabel("Employee ID:");
        empEmployeeIdLabel.setFont(empLeaveLabelFont);
        JLabel empEmployeeIdValue = new JLabel("");
        empEmployeeIdValue.setFont(empLeaveValueFont);

        JLabel empEmployeeNameLabel = new JLabel("Employee Name:");
        empEmployeeNameLabel.setFont(empLeaveLabelFont);
        JLabel empEmployeeNameValue = new JLabel("");
        empEmployeeNameValue.setFont(empLeaveValueFont);

        JLabel empStartDateLabel = new JLabel("Start Date:");
        empStartDateLabel.setFont(empLeaveLabelFont);
        JLabel empStartDateValue = new JLabel("");
        empStartDateValue.setFont(empLeaveValueFont);

        JLabel empEndDateLabel = new JLabel("End Date:");
        empEndDateLabel.setFont(empLeaveLabelFont);
        JLabel empEndDateValue = new JLabel("");
        empEndDateValue.setFont(empLeaveValueFont);

        JLabel empReasonLabel = new JLabel("Reason:");
        empReasonLabel.setFont(empLeaveLabelFont);
        JTextArea empReasonValue = new JTextArea(3, 20);
        empReasonValue.setFont(empLeaveValueFont);
        empReasonValue.setEditable(false);
        empReasonValue.setLineWrap(true);
        empReasonValue.setWrapStyleWord(true);
        JScrollPane empReasonScrollPane = new JScrollPane(empReasonValue);

        JLabel empStatusLabel = new JLabel("Status:");
        empStatusLabel.setFont(empLeaveLabelFont);
        JLabel empStatusValue = new JLabel("");
        empStatusValue.setFont(empLeaveValueFont);

        empLeaveGbc.gridx = 0; empLeaveGbc.gridy = 0;
        detailsPanel.add(empRequestIdLabel, empLeaveGbc);
        empLeaveGbc.gridx = 1;
        detailsPanel.add(empRequestIdValue, empLeaveGbc);

        empLeaveGbc.gridx = 0; empLeaveGbc.gridy = 1;
        detailsPanel.add(empEmployeeIdLabel, empLeaveGbc);
        empLeaveGbc.gridx = 1;
        detailsPanel.add(empEmployeeIdValue, empLeaveGbc);

        empLeaveGbc.gridx = 0; empLeaveGbc.gridy = 2;
        detailsPanel.add(empEmployeeNameLabel, empLeaveGbc);
        empLeaveGbc.gridx = 1;
        detailsPanel.add(empEmployeeNameValue, empLeaveGbc);

        empLeaveGbc.gridx = 0; empLeaveGbc.gridy = 3;
        detailsPanel.add(empStartDateLabel, empLeaveGbc);
        empLeaveGbc.gridx = 1;
        detailsPanel.add(empStartDateValue, empLeaveGbc);

        empLeaveGbc.gridx = 0; empLeaveGbc.gridy = 4;
        detailsPanel.add(empEndDateLabel, empLeaveGbc);
        empLeaveGbc.gridx = 1;
        detailsPanel.add(empEndDateValue, empLeaveGbc);

        empLeaveGbc.gridx = 0; empLeaveGbc.gridy = 5;
        detailsPanel.add(empReasonLabel, empLeaveGbc);
        empLeaveGbc.gridx = 1;
        detailsPanel.add(empReasonScrollPane, empLeaveGbc);

        empLeaveGbc.gridx = 0; empLeaveGbc.gridy = 6;
        detailsPanel.add(empStatusLabel, empLeaveGbc);
        empLeaveGbc.gridx = 1;
        detailsPanel.add(empStatusValue, empLeaveGbc);

        // Buttons Panel
        JPanel leaveButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JButton requestLeaveButton = createButton("Request Leave", "Submit a new leave request");
        JButton editLeaveButton = createButton("Edit", "Edit the selected pending leave request");
        editLeaveButton.setEnabled(false); // Disabled by default
        leaveButtonPanel.add(requestLeaveButton);
        leaveButtonPanel.add(editLeaveButton);

        // Split Pane for List and Details
        JSplitPane leaveSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leaveListPanel, detailsPanel);
        leaveSplitPane.setDividerLocation(300);
        leaveSplitPane.setResizeWeight(0.4);

        // Assemble Leave Panel
        leavePanel.add(leaveFilterPanel, BorderLayout.NORTH);
        leavePanel.add(leaveSplitPane, BorderLayout.CENTER);
        leavePanel.add(leaveButtonPanel, BorderLayout.SOUTH);

        // Populate initial list
        refreshEmployeeLeaveListModel(leaveListModel, loggedInEmployee.getEmployeeId(), (String) statusFilter.getSelectedItem());

        // Action Listeners
        statusFilter.addActionListener(e -> {
            String selectedStatus = (String) statusFilter.getSelectedItem();
            refreshEmployeeLeaveListModel(leaveListModel, loggedInEmployee.getEmployeeId(), selectedStatus);
            clearDetails(empRequestIdValue, empEmployeeIdValue, empEmployeeNameValue, empStartDateValue, empEndDateValue, empReasonValue, empStatusValue);
            leaveJList.clearSelection();
        });

        requestLeaveButton.addActionListener(e -> {
            createRequestLeaveDialog(employeeFrame);
            refreshEmployeeLeaveListModel(leaveListModel, loggedInEmployee.getEmployeeId(), (String) statusFilter.getSelectedItem());
        });

        editLeaveButton.addActionListener(e -> {
            LeaveRequest selectedRequest = leaveJList.getSelectedValue();
            if (selectedRequest != null && "Pending".equalsIgnoreCase(selectedRequest.getStatus())) {
                createEditLeaveDialog(employeeFrame, selectedRequest, leaveListModel, (String) statusFilter.getSelectedItem());
                refreshEmployeeLeaveListModel(leaveListModel, loggedInEmployee.getEmployeeId(), (String) statusFilter.getSelectedItem());
            }
        });
        leaveJList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                LeaveRequest selectedRequest = leaveJList.getSelectedValue();
                if (selectedRequest != null) {
                    populateDetails(selectedRequest, empRequestIdValue, empEmployeeIdValue, empEmployeeNameValue,
                            empStartDateValue, empEndDateValue, empReasonValue, empStatusValue);
                    editLeaveButton.setEnabled("Pending".equalsIgnoreCase(selectedRequest.getStatus()));
                } else {
                    clearDetails(empRequestIdValue, empEmployeeIdValue, empEmployeeNameValue,
                            empStartDateValue, empEndDateValue, empReasonValue, empStatusValue);
                    editLeaveButton.setEnabled(false);
                }
            }
        });

        tabbedPane.addTab("Leave", leavePanel);

        // --- (っ◔◡◔)っ ♥ Attendance Tab ♥ ---
        JPanel attendancePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        attendancePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JButton clockInButton = createButton("Clock In", "Record your clock-in time");
        JButton clockOutButton = createButton("Clock Out", "Record your clock-out time");
        attendancePanel.add(clockInButton);
        attendancePanel.add(clockOutButton);
        tabbedPane.addTab("Attendance", attendancePanel);

        employeeFrame.add(tabbedPane, BorderLayout.CENTER);

        // --- Populate Payslip List ---
        Report report = new Report(attendanceRecords, employeeRecords, payrollRecords);
        List<PayrollGUI.PayrollData> employeePayroll = payrollRecords.getPayrollDataForEmployee(loggedInEmployee.getEmployeeId());
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (PayrollGUI.PayrollData data : employeePayroll) {
            payslipListModel.addElement(data.getRunDate().format(dateFormatter));
        }

        // Generate Payslip Button
        generatePayslipButton.addActionListener(e -> {
            JDialog filterDialog = new JDialog(employeeFrame, "Filter Payslip", true);
            filterDialog.setLayout(new BorderLayout());
            filterDialog.setSize(400, 200);

            JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            filterPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

            JLabel startDateLabel = new JLabel("Start Date:");
            JSpinner startDateSpinner = new JSpinner(new SpinnerDateModel());
            JSpinner.DateEditor startDateEditor = new JSpinner.DateEditor(startDateSpinner, "yyyy-MM-dd");
            startDateSpinner.setEditor(startDateEditor);

            JLabel endDateLabel = new JLabel("End Date:");
            JSpinner endDateSpinner = new JSpinner(new SpinnerDateModel());
            JSpinner.DateEditor endDateEditor = new JSpinner.DateEditor(endDateSpinner, "yyyy-MM-dd");
            endDateSpinner.setEditor(endDateEditor);

            filterPanel.add(startDateLabel);
            filterPanel.add(startDateSpinner);
            filterPanel.add(endDateLabel);
            filterPanel.add(endDateSpinner);

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JButton okButton = createButton("OK", "Generate payslip for the selected date range");
            JButton cancelButton = createButton("Cancel", "Close the filter dialog");
            buttonPanel.add(okButton);
            buttonPanel.add(cancelButton);

            filterDialog.add(filterPanel, BorderLayout.CENTER);
            filterDialog.add(buttonPanel, BorderLayout.SOUTH);

            okButton.addActionListener(ev -> {
                Date startDateValue = (Date) startDateSpinner.getValue();
                Date endDateValue = (Date) endDateSpinner.getValue();
                LocalDate startDate = startDateValue.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate endDate = endDateValue.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                if (startDate.isAfter(endDate)) {
                    JOptionPane.showMessageDialog(filterDialog, "Start date must be before end date.", "Invalid Date Range", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String payslips = report.generatePayslips(loggedInEmployee.getEmployeeId(), startDate, endDate);
                JTextPane payslipTextPane = new JTextPane();
                payslipTextPane.setContentType("text/html");
                payslipTextPane.setText(payslips);
                payslipTextPane.setEditable(false);
                JScrollPane generatedPayslipScrollPane = new JScrollPane(payslipTextPane); // Define new scroll pane here

                // Use the new scroll pane instead of reusing payslipScrollPane
                JOptionPane.showMessageDialog(employeeFrame, generatedPayslipScrollPane, "Payslip", JOptionPane.INFORMATION_MESSAGE);
                filterDialog.dispose();
            });

            cancelButton.addActionListener(ev -> filterDialog.dispose());

            filterDialog.setLocationRelativeTo(employeeFrame);
            filterDialog.setVisible(true);
        });

        // Payslip List Selection Listener (Expand Details)
        payslipJList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedIndex = payslipJList.getSelectedIndex();
                if (selectedIndex >= 0) {
                    PayrollGUI.PayrollData selectedPayslip = employeePayroll.get(selectedIndex);
                    StringBuilder details = new StringBuilder("<html><body>");
                    details.append("<h2>Pay Date: ").append(selectedPayslip.getRunDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("</h2>")
                            .append("<b>").append(selectedPayslip.getFirstName()).append(" ").append(selectedPayslip.getLastName()).append("</b><br>")
                            .append("Gross Pay: $").append(String.format("%.2f", selectedPayslip.getGrossPay())).append("<br>")
                            .append("Net Pay: $").append(String.format("%.2f", selectedPayslip.getNetPay())).append("<br>")
                            .append("Deductions:<br>")
                            .append("  BIR: $").append(String.format("%.2f", selectedPayslip.getBirDeduction())).append("<br>")
                            .append("  SSS: $").append(String.format("%.2f", selectedPayslip.getSssDeduction())).append("<br>")
                            .append("  PhilHealth: $").append(String.format("%.2f", selectedPayslip.getPhilHealthDeduction())).append("<br>")
                            .append("  Pag-IBIG: $").append(String.format("%.2f", selectedPayslip.getPagIbigDeduction())).append("<br>")
                            .append("</body></html>");
                    payslipDetailsPane.setText(details.toString());
                     downloadPayslipButton.setEnabled(true);
                }

                else {
                    payslipDetailsPane.setText("");
                     downloadPayslipButton.setEnabled(false);
                }
            }
        });
 downloadPayslipButton.addActionListener(e -> {
             int selectedIndex = payslipJList.getSelectedIndex();
             if (selectedIndex >= 0) {
                 PayrollGUI.PayrollData selectedPayslip = employeePayroll.get(selectedIndex);
                 try {
                     generatePayslipPDF(selectedPayslip, employeeFrame);
                     JOptionPane.showMessageDialog(employeeFrame, "Payslip downloaded successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                 } catch (IOException ex) {
                     JOptionPane.showMessageDialog(employeeFrame, "Error generating PDF: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                 }
             }
         });


        // Populate Leave Requests List
        List<LeaveRequest> allRequests = leaveManagement.getAllLeaveRequests();
        for (LeaveRequest request : allRequests) {
            if (request.getEmployeeId() == loggedInEmployee.getEmployeeId()) {
                leaveListModel.addElement(request);
            }
        }

        // Request Leave Button
        requestLeaveButton.addActionListener(e -> createRequestLeaveDialog(employeeFrame));

        // Clock In/Out Buttons
        // Clock In/Out Buttons
        clockInButton.addActionListener(e -> {
            timeAndAttendance.clockIn(loggedInEmployee);
            JOptionPane.showMessageDialog(employeeFrame, "Clocked in successfully at " + LocalTime.now().format(DateTimeFormatter.ofPattern("h:mm:ss a")));
        });

        clockOutButton.addActionListener(e -> {
            timeAndAttendance.clockOut(loggedInEmployee);
            JOptionPane.showMessageDialog(employeeFrame, "Clocked out successfully at " + LocalTime.now().format(DateTimeFormatter.ofPattern("h:mm:ss a")));
        });

        employeeFrame.setVisible(true);
        employeeFrame.setLocationRelativeTo(null);
    }

    // dialog for request leave
    private void createRequestLeaveDialog(JFrame parentFrame) {
        JDialog dialog = new JDialog(parentFrame, "Request Leave", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(new Dimension(400, 300));

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        formPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel startDateLabel = new JLabel("Start Date:");
        JSpinner startDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor startDateEditor = new JSpinner.DateEditor(startDateSpinner, "yyyy-MM-dd");
        startDateSpinner.setEditor(startDateEditor);

        JLabel endDateLabel = new JLabel("End Date:");
        JSpinner endDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor endDateEditor = new JSpinner.DateEditor(endDateSpinner, "yyyy-MM-dd");
        endDateSpinner.setEditor(endDateEditor);

        JLabel reasonLabel = new JLabel("Reason for Leave:");
        JTextArea reasonTextArea = new JTextArea(5, 20);
        JScrollPane reasonScrollPane = new JScrollPane(reasonTextArea);

        formPanel.add(startDateLabel); formPanel.add(startDateSpinner);
        formPanel.add(endDateLabel); formPanel.add(endDateSpinner);
        formPanel.add(reasonLabel); formPanel.add(reasonScrollPane);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton requestButton = createButton("Request Leave", "Submit leave request");
        JButton cancelButton = createButton("Cancel", "Cancel and close");
        buttonPanel.add(requestButton);
        buttonPanel.add(cancelButton);

        requestButton.addActionListener(e -> {
            try {
                Date startDateValue = (Date) startDateSpinner.getValue();
                Date endDateValue = (Date) endDateSpinner.getValue();

                // Convert Date to LocalDate
                LocalDate startDate = startDateValue.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate endDate = endDateValue.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                String reason = reasonTextArea.getText();

                if (loggedInEmployee != null) { // Use loggedInEmployee directly
                    leaveManagement.requestLeave(loggedInEmployee, startDate, endDate, reason); // Use loggedInEmployee

                    JOptionPane.showMessageDialog(dialog, "Leave request submitted successfully.", "Request Submitted", JOptionPane.INFORMATION_MESSAGE);
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Employee data not found. Cannot submit request.", "Error", JOptionPane.ERROR_MESSAGE);
                }


            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Error submitting leave request: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        cancelButton.addActionListener(e -> dialog.dispose());


        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }

    // Helper method to refresh employee leave list
    private void refreshEmployeeLeaveListModel(DefaultListModel<LeaveRequest> listModel, int employeeId, String statusFilter) {
        listModel.clear();
        List<LeaveRequest> allRequests = leaveManagement.getAllLeaveRequests();
        for (LeaveRequest request : allRequests) {
            if (request.getEmployeeId() == employeeId) {
                if ("All".equalsIgnoreCase(statusFilter) || request.getStatus().equalsIgnoreCase(statusFilter)) {
                    listModel.addElement(request);
                }
            }
        }
    }

    // Method to create edit leave dialog
    private void createEditLeaveDialog(JFrame parentFrame, LeaveRequest request, DefaultListModel<LeaveRequest> listModel, String statusFilter) {
        JDialog dialog = new JDialog(parentFrame, "Edit Leave Request", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(new Dimension(400, 300));

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        formPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel startDateLabel = new JLabel("Start Date:");
        JSpinner startDateSpinner = new JSpinner(new SpinnerDateModel());
        startDateSpinner.setValue(Date.from(request.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        JSpinner.DateEditor startDateEditor = new JSpinner.DateEditor(startDateSpinner, "yyyy-MM-dd");
        startDateSpinner.setEditor(startDateEditor);

        JLabel endDateLabel = new JLabel("End Date:");
        JSpinner endDateSpinner = new JSpinner(new SpinnerDateModel());
        endDateSpinner.setValue(Date.from(request.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        JSpinner.DateEditor endDateEditor = new JSpinner.DateEditor(endDateSpinner, "yyyy-MM-dd");
        endDateSpinner.setEditor(endDateEditor);

        JLabel reasonLabel = new JLabel("Reason for Leave:");
        JTextArea reasonTextArea = new JTextArea(request.getReason(), 5, 20);
        JScrollPane reasonScrollPane = new JScrollPane(reasonTextArea);

        formPanel.add(startDateLabel);
        formPanel.add(startDateSpinner);
        formPanel.add(endDateLabel);
        formPanel.add(endDateSpinner);
        formPanel.add(reasonLabel);
        formPanel.add(reasonScrollPane);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton saveButton = createButton("Save", "Save changes to the leave request");
        JButton cancelButton = createButton("Cancel", "Cancel and close");
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        saveButton.addActionListener(e -> {
            try {
                Date startDateValue = (Date) startDateSpinner.getValue();
                Date endDateValue = (Date) endDateSpinner.getValue();
                LocalDate startDate = startDateValue.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate endDate = endDateValue.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                String reason = reasonTextArea.getText().trim();

                if (startDate.isAfter(endDate)) {
                    JOptionPane.showMessageDialog(dialog, "Start date must be before end date.", "Invalid Date Range", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (reason.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Reason cannot be empty.", "Input Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Update the existing request
                request.setStartDate(startDate);
                request.setEndDate(endDate);
                request.setReason(reason);
                leaveManagement.saveToCSV(); // Persist changes

                // Refresh with the current status filter
                refreshEmployeeLeaveListModel(listModel, loggedInEmployee.getEmployeeId(), statusFilter);
                JOptionPane.showMessageDialog(dialog, "Leave request updated successfully.", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Error updating leave request: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }

    //                      __           __  _       __                                                               __                      __        __
    //  _____________  ____/ /__  ____  / /_(_)___ _/ /  ____ ___  ____ _____  ____ _____ ____  ____ ___  ___  ____  / /_   ____  ____  _____/ /_____ _/ /
    // / ___/ ___/ _ \/ __  / _ \/ __ \/ __/ / __ `/ /  / __ `__ \/ __ `/ __ \/ __ `/ __ `/ _ \/ __ `__ \/ _ \/ __ \/ __/  / __ \/ __ \/ ___/ __/ __ `/ /
    /// /__/ /  /  __/ /_/ /  __/ / / / /_/ / /_/ / /  / / / / / / /_/ / / / / /_/ / /_/ /  __/ / / / / /  __/ / / / /_   / /_/ / /_/ / /  / /_/ /_/ / /
    //\___/_/   \___/\__,_/\___/_/ /_/\__/_/\__,_/_/  /_/ /_/ /_/\__,_/_/ /_/\__,_/\__, /\___/_/ /_/ /_/\___/_/ /_/\__/  / .___/\____/_/   \__/\__,_/_/
    //                                                                            /____/                                /_/

    public void accessCredentialManagementPortal() {
        JFrame itFrame = new JFrame("Credential Management Portal");
        itFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        itFrame.setSize(900, 600);
        itFrame.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome, IT Admin", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        itFrame.add(welcomeLabel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Search Panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel searchLabel = new JLabel("Search Employee ID:");
        JTextField searchTextField = new JTextField(10);
        JButton searchButton = createButton("Search", "Find employee by ID");
        searchPanel.add(searchLabel);
        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);
        mainPanel.add(searchPanel, BorderLayout.NORTH);

        // Employee List Panel
        JPanel employeeListPanel = new JPanel(new BorderLayout());
        employeeListPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        DefaultListModel<Employee> employeeListModel = new DefaultListModel<>();
        JList<Employee> employeeJList = new JList<>(employeeListModel);
        JScrollPane employeeListScrollPane = new JScrollPane(employeeJList);
        for (Employee emp : employeeRecords.employees.values()) {
            employeeListModel.addElement(emp);
        }
        employeeJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        employeeJList.setCellRenderer(new EmployeeListRenderer());
        employeeListPanel.add(employeeListScrollPane, BorderLayout.CENTER);
        mainPanel.add(employeeListPanel, BorderLayout.WEST);

        // Credential Edit Panel
        JPanel credentialPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        credentialPanel.setBorder(BorderFactory.createTitledBorder("Edit Credentials"));
        JLabel usernameLabel = new JLabel("Username (Employee ID):");
        JTextField usernameField = new JTextField();
        usernameField.setEditable(false); // Username is employee ID, non-editable
        JLabel passwordLabel = new JLabel("New Password:");
        JTextField passwordField = new JTextField();
        credentialPanel.add(usernameLabel);
        credentialPanel.add(usernameField);
        credentialPanel.add(passwordLabel);
        credentialPanel.add(passwordField);
        mainPanel.add(credentialPanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton saveButton = createButton("Save", "Save the new password");
        JButton clearButton = createButton("Clear", "Clear the form");
        buttonPanel.add(saveButton);
        buttonPanel.add(clearButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Action Listeners
        employeeJList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Employee selectedEmployee = employeeJList.getSelectedValue();
                if (selectedEmployee != null) {
                    usernameField.setText(String.valueOf(selectedEmployee.getEmployeeId()));
                    passwordField.setText(""); // Clear password field for new input
                }
            }
        });

        searchButton.addActionListener(e -> {
            String employeeIdStr = searchTextField.getText();
            if (!employeeIdStr.trim().isEmpty()) {
                try {
                    int employeeId = Integer.parseInt(employeeIdStr.trim());
                    Employee foundEmployee = employeeRecords.viewEmployee(employeeId);
                    if (foundEmployee != null) {
                        usernameField.setText(String.valueOf(foundEmployee.getEmployeeId()));
                        passwordField.setText("");
                        for (int i = 0; i < employeeListModel.getSize(); i++) {
                            if (employeeListModel.getElementAt(i).getEmployeeId() == employeeId) {
                                employeeJList.setSelectedIndex(i);
                                employeeJList.ensureIndexIsVisible(i);
                                break;
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(itFrame, "Employee with ID " + employeeId + " not found.", "Employee Not Found", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(itFrame, "Invalid Employee ID format.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(itFrame, "Please enter an Employee ID to search.", "Input Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        saveButton.addActionListener(e -> {
            String username = usernameField.getText();
            String newPassword = passwordField.getText();
            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(itFrame, "Please select an employee to update.", "Selection Error", JOptionPane.WARNING_MESSAGE);
            } else if (newPassword.isEmpty()) {
                JOptionPane.showMessageDialog(itFrame, "New password cannot be empty.", "Input Error", JOptionPane.WARNING_MESSAGE);
            } else {
                loginManager.updateUserPassword(username, newPassword);
                JOptionPane.showMessageDialog(itFrame, "Password updated successfully for Employee ID: " + username, "Success", JOptionPane.INFORMATION_MESSAGE);
                passwordField.setText(""); // Clear password field after saving
            }
        });

        clearButton.addActionListener(e -> {
            usernameField.setText("");
            passwordField.setText("");
            employeeJList.clearSelection();
        });

        itFrame.add(mainPanel, BorderLayout.CENTER);
        itFrame.setVisible(true);
        itFrame.setLocationRelativeTo(null);
    }


    // --- Utility method to create buttons with consistent styling ---
    private JButton createButton(String text, String tooltip) {
        JButton button = new JButton(text);
        button.setPreferredSize(BUTTON_SIZE);
        button.setToolTipText(tooltip);
        return button;
    }

    // run payroll related methods //

    private void runPayroll() {
        JFrame payrollFrame = new JFrame("Run Payroll");
        payrollFrame.setLayout(new FlowLayout());

        // Initial confirmation dialog
        int confirmationResult = JOptionPane.showConfirmDialog(payrollFrame,
                "Run payroll for the current cut off?",
                "Confirm Payroll",
                JOptionPane.OK_CANCEL_OPTION);
        if (confirmationResult != JOptionPane.OK_OPTION) {
            payrollFrame.dispose();
            return;
        }

        // Create a custom dialog for employee selection
        JDialog employeeSelectionDialog = new JDialog(payrollFrame, "Select Employees for Payroll", true);
        employeeSelectionDialog.setLayout(new BorderLayout());
        employeeSelectionDialog.setSize(500, 400); // Fixed size for the dialog
        employeeSelectionDialog.setLocationRelativeTo(payrollFrame);

        // Employee selection panel with scrollable list
        JPanel employeeSelectionPanel = new JPanel(new GridLayout(0, 1));
        List<JCheckBox> employeeCheckBoxes = new ArrayList<>();
        List<Employee> allEmployees = new ArrayList<>(employeeRecords.employees.values());

        for (Employee employee : allEmployees) {
            JCheckBox checkBox = new JCheckBox(employee.getFirstName() + " " +
                    employee.getLastName() + " (ID: " + employee.getEmployeeId() + ")");
            employeeCheckBoxes.add(checkBox);
            employeeSelectionPanel.add(checkBox);
        }

        // Add the employee panel to a scroll pane
        JScrollPane scrollPane = new JScrollPane(employeeSelectionPanel);
        scrollPane.setPreferredSize(new Dimension(450, 300)); // Limit scrollable area size
        employeeSelectionDialog.add(scrollPane, BorderLayout.CENTER);

        // Button panel for OK and Cancel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JButton okButton = createButton("OK", "Confirm selected employees for payroll");
        JButton cancelButton = createButton("Cancel", "Cancel payroll process");
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        employeeSelectionDialog.add(buttonPanel, BorderLayout.SOUTH);

        // Action listeners for buttons
        final boolean[] proceed = {false}; // To track if OK was clicked
        okButton.addActionListener(e -> {
            proceed[0] = true;
            employeeSelectionDialog.dispose();
        });
        cancelButton.addActionListener(e -> employeeSelectionDialog.dispose());

        // Show the dialog and wait for user input
        employeeSelectionDialog.setVisible(true);

        if (!proceed[0]) {
            payrollFrame.dispose();
            return; // Exit if canceled
        }

        // Process selected employees
        List<Employee> selectedEmployees = new ArrayList<>();
        for (int i = 0; i < allEmployees.size(); i++) {
            if (employeeCheckBoxes.get(i).isSelected()) {
                selectedEmployees.add(allEmployees.get(i));
            }
        }

        if (selectedEmployees.isEmpty()) {
            JOptionPane.showMessageDialog(payrollFrame,
                    "No employees selected for payroll.",
                    "Selection Error",
                    JOptionPane.WARNING_MESSAGE);
            payrollFrame.dispose();
            return;
        }

        // Calculate payroll and show overview
        StringBuilder overviewBuilder = new StringBuilder("<html><body><h1>Payroll Overview</h1>");
        double totalNetPay = 0;
        double totalGrossPay = 0;
        double totalGovDeductions = 0;

        TimeAndAttendanceRecords attendanceRecords = new TimeAndAttendanceRecords();
        Map<Integer, List<TimeRecord>> attendanceData = attendanceRecords.getAttendanceData();
        LocalDateTime now = LocalDateTime.now();

        for (Employee employee : selectedEmployees) {
            int employeeId = employee.getEmployeeId();
            List<TimeRecord> attendance = attendanceData.getOrDefault(employeeId, new ArrayList<>());
            double totalHours = 0;
            for (TimeRecord record : attendance) {
                totalHours += record.getWorkDuration().toHours();
            }

            double hourlyRate = employee.getHourlyRate();
            double riceSubsidy = employee.getRiceSubsidy();
            double phoneAllowance = employee.getPhoneAllowance();
            double clothingAllowance = employee.getClothingAllowance();

            double grossPay = (hourlyRate * totalHours) + riceSubsidy + phoneAllowance + clothingAllowance;

            TaxDeduction birDeduction = new BIRDeduction();
            TaxDeduction sssDeduction = new SSSDeduction();
            TaxDeduction philHealthDeduction = new PhilHealthDeduction();
            TaxDeduction pagIbigDeduction = new PagIbigDeduction();

            double birAmount = birDeduction.calculateDeduction(grossPay);
            double sssAmount = sssDeduction.calculateDeduction(grossPay);
            double philHealthAmount = philHealthDeduction.calculateDeduction(grossPay);
            double pagIbigAmount = pagIbigDeduction.calculateDeduction(grossPay);

            double totalEmployeeDeductions = sssAmount + philHealthAmount + pagIbigAmount;
            double totalGovernmentDeductionsForEmployee = birAmount + totalEmployeeDeductions;
            double netPay = grossPay - totalGovernmentDeductionsForEmployee;

            totalNetPay += netPay;
            totalGrossPay += grossPay;
            totalGovDeductions += totalGovernmentDeductionsForEmployee;

            overviewBuilder.append("<h2>").append(employee.getFirstName()).append(" ").append(employee.getLastName())
                    .append(" (ID: ").append(employeeId).append(")</h2>")
                    .append("Gross Pay: $").append(String.format("%.2f", grossPay)).append("<br>")
                    .append("Net Pay: $").append(String.format("%.2f", netPay)).append("<br>")
                    .append("Government Deductions: $").append(String.format("%.2f", totalGovernmentDeductionsForEmployee)).append("<br>")
                    .append("  - ").append(birDeduction.getDeductionName()).append(": $").append(String.format("%.2f", birAmount)).append("<br>")
                    .append("  - ").append(sssDeduction.getDeductionName()).append(": $").append(String.format("%.2f", sssAmount)).append("<br>")
                    .append("  - ").append(philHealthDeduction.getDeductionName()).append(": $").append(String.format("%.2f", philHealthAmount)).append("<br>")
                    .append("  - ").append(pagIbigDeduction.getDeductionName()).append(": $").append(String.format("%.2f", pagIbigAmount)).append("<br><br>");

            PayrollData payrollData = new PayrollData(now, employeeId, employee.getFirstName(), employee.getLastName(),
                    grossPay, netPay, birAmount, sssAmount, philHealthAmount, pagIbigAmount);
            payrollRecords.addPayrollData(payrollData);
        }

        overviewBuilder.append("<h1>Total Payroll Summary</h1>")
                .append("Total Gross Pay for Selected Employees: $").append(String.format("%.2f", totalGrossPay)).append("<br>")
                .append("Total Net Pay for Selected Employees: $").append(String.format("%.2f", totalNetPay)).append("<br>")
                .append("Total Government Deductions: $").append(String.format("%.2f", totalGovDeductions)).append("<br>")
                .append("</body></html>");

        // Show payroll overview in a scrollable pane
        JTextPane overviewPane = new JTextPane();
        overviewPane.setContentType("text/html");
        overviewPane.setText(overviewBuilder.toString());
        overviewPane.setEditable(false);
        JScrollPane overviewScrollPane = new JScrollPane(overviewPane);
        overviewScrollPane.setPreferredSize(new Dimension(600, 400));

        int overviewResult = JOptionPane.showConfirmDialog(payrollFrame,
                overviewScrollPane,
                "Payroll Overview",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE);

        if (overviewResult == JOptionPane.OK_OPTION) {
            JOptionPane.showMessageDialog(payrollFrame,
                    "Payroll is All Set!",
                    "Payroll Complete",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(payrollFrame,
                    "Payroll process cancelled.",
                    "Cancelled",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        payrollFrame.dispose();
    }

    // Helper class to hold payroll data
    class PayrollRecords {
        private List<PayrollData> payrollData;
        private static final String PAYROLL_CSV = "Payroll_Report.csv";

        public PayrollRecords() {
            payrollData = new ArrayList<>();
            loadFromCSV();
        }

        public void addPayrollData(PayrollData data) {
            payrollData.add(data);
            saveToCSV();
        }

        public List<PayrollData> getAllPayrollData() {
            return payrollData;
        }

        public List<PayrollData> getPayrollDataForEmployee(int employeeId) {
            return payrollData.stream()
                    .filter(data -> data.getEmployeeId() == employeeId)
                    .collect(Collectors.toList());
        }

        private void loadFromCSV() {
            try (Scanner scanner = new Scanner(new File(PAYROLL_CSV))) {
                if (scanner.hasNextLine()) {
                    scanner.nextLine(); // Skip header
                }
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(",", -1); // -1 to keep empty fields
                    if (parts.length == 10) {
                        try {
                            LocalDateTime runDate = LocalDateTime.parse(parts[0].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                            int employeeId = Integer.parseInt(parts[1].trim());
                            String firstName = parts[2].trim();
                            String lastName = parts[3].trim();
                            double grossPay = Double.parseDouble(parts[4].trim());
                            double netPay = Double.parseDouble(parts[5].trim());
                            double birDeduction = Double.parseDouble(parts[6].trim());
                            double sssDeduction = Double.parseDouble(parts[7].trim());
                            double philHealthDeduction = Double.parseDouble(parts[8].trim());
                            double pagIbigDeduction = Double.parseDouble(parts[9].trim());

                            PayrollData data = new PayrollData(runDate, employeeId, firstName, lastName, grossPay, netPay,
                                    birDeduction, sssDeduction, philHealthDeduction, pagIbigDeduction);
                            payrollData.add(data);
                        } catch (NumberFormatException | DateTimeParseException e) {
                            System.err.println("Error parsing payroll data: " + line);
                        }
                    } else {
                        System.err.println("Invalid line in payroll CSV: " + line);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Payroll CSV not found. Creating a new one.");
            }
        }

        private void saveToCSV() {
            try (PrintWriter writer = new PrintWriter(new File(PAYROLL_CSV))) {
                writer.println("Payroll Run Date,Employee ID,First Name,Last Name,Gross Pay,Net Pay,BIR Deduction,SSS Deduction,PhilHealth Deduction,Pag-IBIG Deduction");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                for (PayrollData data : payrollData) {
                    writer.println(String.join(",",
                            data.getRunDate().format(formatter),
                            String.valueOf(data.getEmployeeId()),
                            data.getFirstName(),
                            data.getLastName(),
                            String.format("%.2f", data.getGrossPay()),
                            String.format("%.2f", data.getNetPay()),
                            String.format("%.2f", data.getBirDeduction()),
                            String.format("%.2f", data.getSssDeduction()),
                            String.format("%.2f", data.getPhilHealthDeduction()),
                            String.format("%.2f", data.getPagIbigDeduction())
                    ));
                }
            } catch (FileNotFoundException e) {
                System.err.println("Error saving payroll data to CSV: " + e.getMessage());
            }
        }
    }

    class PayrollData {
        private LocalDateTime runDate;
        private int employeeId;
        private String firstName;
        private String lastName;
        private double grossPay;
        private double netPay;
        private double birDeduction;
        private double sssDeduction;
        private double philHealthDeduction;
        private double pagIbigDeduction;

        public PayrollData(LocalDateTime runDate, int employeeId, String firstName, String lastName, double grossPay, double netPay,
                           double birDeduction, double sssDeduction, double philHealthDeduction, double pagIbigDeduction) {
            this.runDate = runDate;
            this.employeeId = employeeId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.grossPay = grossPay;
            this.netPay = netPay;
            this.birDeduction = birDeduction;
            this.sssDeduction = sssDeduction;
            this.philHealthDeduction = philHealthDeduction;
            this.pagIbigDeduction = pagIbigDeduction;
        }

        // Getters
        public LocalDateTime getRunDate() { return runDate; }
        public int getEmployeeId() { return employeeId; }
        public String getFirstName() { return firstName; }
        public String getLastName() { return lastName; }
        public double getGrossPay() { return grossPay; }
        public double getNetPay() { return netPay; }
        public double getBirDeduction() { return birDeduction; }
        public double getSssDeduction() { return sssDeduction; }
        public double getPhilHealthDeduction() { return philHealthDeduction; }
        public double getPagIbigDeduction() { return pagIbigDeduction; }
    }
}