import java.sql.*;
import java.util.*;
import java.lang.Math;
import java.time.LocalDate;

public class swastikpharma {
    public static void main(String[] args) {
        Connection conn = null;
        int attempt = 0;
        while (attempt < 3) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastikpharma", "root", "29122002");
                Statement statement;
                statement = conn.createStatement();
                ResultSet resultSet;
                Scanner sc = new Scanner(System.in);

                String username = new String();
                String password = new String();
                resultSet = statement.executeQuery("select * from admin");
                while (resultSet.next()) {
                    username = resultSet.getString("username");
                    password = resultSet.getString("password");
                }
                int choice = 0;
                if (login(username, password)) {
                    System.out.println("Welcome to Swastik Pharma Admin");
                    System.out.println("1.Add chemist\n2.Update chemist\n3.Add medicine\n4.Update medicine\n5.Add Order\n6.View Bills\n7.View Inventory\n8.Exit");
                    while (choice != -1) {
                        System.out.println();
                        System.out.print("Enter your choice: ");
                        choice = sc.nextInt();
                        switch (choice) {
                            case 1:
                                addChemist();
                                break;
                            case 2:
                                updateChemist();
                                break;
                            case 3:
                                addMedicine();
                                break;
                            case 4:
                                updateMedicine();
                                break;
                            case 5:
                                addOrder();
                                break;
                            case 6:
                                viewBills();
                                break;
                            case 7:
                                viewInventory();
                                break;
                            case 8:
                                System.out.println("Quiting...");
                                System.exit(0);
                            default:
                                break;
                        }
                    }
                } else {
                    System.out.println("Incorrect Username or Password !");
                    System.out.println((2-attempt)+" attempt remaining... Try again !!");
                }
            } catch (Exception exception) {
                System.out.println(exception);
            }
            attempt++;
        }
    }

    public static boolean login(String username, String password) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter username: ");
        String _username = sc.next();
        System.out.print("Enter password: ");
        String _password = sc.next();
        if (_username.equals(username) && _password.equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public static void addChemist() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastikpharma", "root", "29122002");
            Statement statement;
            statement = conn.createStatement();
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter name: ");
            String name = sc.nextLine();
            System.out.print("Enter address: ");
            String address = sc.nextLine();
            System.out.print("Enter phone number: ");
            long phoneno = sc.nextLong();
            System.out.print("Enter DL number: ");
            String dlno1 = sc.next();
            System.out.print("Enter DL number: ");
            String dlno2 = sc.next();
            System.out.print("Enter GST number: ");
            String gstin = sc.next();
            String addChemistQuery = "insert into chemist (chemist_name, address, phoneno, dlno1, dlno2, gstin) values ('"+ name + "','" + address + "','" + phoneno + "','" + dlno1 + "','" + dlno2 + "','" + gstin + "')";
            statement.executeUpdate(addChemistQuery);
            System.out.println("Added Successfully");
        } catch (Exception exception) {
            System.out.println("Error in adding chemist " + exception);
        }
    }

    public static void updateChemist() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastikpharma", "root", "29122002");
            Statement statement;
            statement = conn.createStatement();
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Chemist ID: ");
            int id = sc.nextInt();
            String name, address, dlno1, dlno2, gst;
            long phoneno;
            int cid;
            ResultSet resultSet = statement.executeQuery("select * from chemist where chemist_id = " + id);
            System.out.println("Chemist ID\tName\tAddress\tPhone\tDL No1\tDL No2\tGST Number");
            while (resultSet.next()) {
                cid = resultSet.getInt("chemist_id");
                name = resultSet.getString("chemist_name");
                address = resultSet.getString("address");
                phoneno = resultSet.getLong("phoneno");
                dlno1 = resultSet.getString("dlno1");
                dlno2 = resultSet.getString("dlno2");
                gst = resultSet.getString("gstin");
                System.out.println(cid + "\t" + name + "\t" + address + "\t" + phoneno + "\t" + dlno1 + "\t" + dlno2 + "\t" + gst);
            }
            System.out.print("Enter the field name you want to edit: ");
            String col = sc.next();
            System.out.print("Enter the new value: ");
            String value = sc.next();
            String updateChemistQuery = "update chemist set " + col + " = '" + value + "' where chemist_id = " + id;
            statement.executeUpdate(updateChemistQuery);
            System.out.println("Updated successfully");
        } catch (Exception exception) {
            System.out.println("Error in updating chemist " + exception);
        }
    }

    public static void addMedicine() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastikpharma", "root", "29122002");
            Statement statement;
            statement = conn.createStatement();
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter manufacturer: ");
            String mfr = sc.next();
            System.out.print("Enter medicine name: ");
            String name = sc.next();
            System.out.print("Enter pack: ");
            int pack = sc.nextInt();
            System.out.print("Enter HSN code: ");
            int hsn = sc.nextInt();
            System.out.print("Enter Batch number: ");
            String batchno = sc.next();
            System.out.print("Enter expiry date: ");
            String expdate = sc.next();
            System.out.print("Enter MRP: ");
            float mrp = sc.nextFloat();
            System.out.print("Enter TRP: ");
            float trp = sc.nextFloat();
            System.out.print("Enter quantity: ");
            int qty = sc.nextInt();
            System.out.print("Enter GST%: ");
            int gst = sc.nextInt();
            String addMedicineQuery = "insert into medicine (manufacturer, med_name, pack, hsn, batchno, expiry_date, mrp, trp, quantity, gst) values ('" + mfr + "','" + name + "'," + pack + "," + hsn + ",'" + batchno + "','" + expdate + "'," + mrp + "," + trp + "," + qty + "," + gst + ")";
            statement.executeUpdate(addMedicineQuery);
            System.out.println("Medicine added successfully");
        } catch (Exception exception) {
            System.out.println("Error in adding medicine " + exception);
        }
    }

    public static void updateMedicine() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastikpharma", "root",
                    "29122002");
            Statement statement;
            statement = conn.createStatement();
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter batch number: ");
            String batch = sc.next();
            ResultSet resultSet = statement.executeQuery("select * from medicine where batchno = '" + batch + "';");
            String mfr, name, pack, batchno, expdate;
            int hsn, gst, qty = 0;
            float mrp, trp;
            System.out.println(
                    "Manufacturer\tMedicine Name\tPack\tHSN Code\tBatch\tExpiry Date\tMRP\tTRP\tQuantity\tGST%");
            while (resultSet.next()) {
                mfr = resultSet.getString("manufacturer");
                name = resultSet.getString("med_name");
                pack = resultSet.getString("pack");
                hsn = resultSet.getInt("hsn");
                batchno = resultSet.getString("batchno");
                expdate = resultSet.getString("expiry_date");
                mrp = resultSet.getFloat("mrp");
                trp = resultSet.getFloat("trp");
                qty = resultSet.getInt("quantity");
                gst = resultSet.getInt("gst");
                System.out.println(mfr + "\t" + name + "\t" + pack + "\t" + hsn + "\t" + batchno + "\t" + expdate + "\t" + mrp + "\t" + trp + "\t" + qty + "\t" + gst);
            }
            System.out.print("Enter the field name you want to edit: ");
            String col = sc.next();
            if (col.equals("manufacturer") || col.equals("med_name") || col.equals("batchno") || col.equals("expiry_date")) {
                System.out.print("Enter the new value: ");
                String value = sc.next();
                String updateMedicineQuery = "update medicine set " + col + " = '" + value + "' where batchno = '" + batch + "';";
                statement.executeUpdate(updateMedicineQuery);
                System.out.println("Updated successfully");
            } else if (col.equals("pack") || col.equals("hsn") || col.equals("gst")) {
                System.out.print("Enter the new value: ");
                int value = sc.nextInt();
                String updateMedicineQuery = "update medicine set " + col + " = " + value + " where batchno = '" + batch + "';";
                statement.executeUpdate(updateMedicineQuery);
                System.out.println("Updated successfully");
            } else if (col.equals("quantity") || col.equals("Quantity")) {
                System.out.print("Enter new quantity value: ");
                int value = sc.nextInt();
                value = value + qty;
                String updateMedicineQuery = "update medicine set " + col + " = " + value + " where batchno = '" + batch + "';";
                statement.executeUpdate(updateMedicineQuery);
                System.out.println("Updated successfully");
            } else {
                System.out.print("Enter the new value: ");
                Float value = sc.nextFloat();
                String updateMedicineQuery = "update medicine set " + col + " = " + value + " where batchno = '" + batch + "';";
                statement.executeUpdate(updateMedicineQuery);
                System.out.println("Updated successfully");
            }

        } catch (Exception exception) {
            System.out.println("Error in updating medicine " + exception);
        }
    }

    public static void addOrder() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastikpharma", "root", "29122002");
            Statement statement;
            statement = conn.createStatement();
            Scanner sc = new Scanner(System.in);
            String dname = "Swastik Pharma";
            String daddress = "GP No 1343, SY No 598/2A Ground Floor, Shop No 1, Rameshwar Nagar, Macche, Belagavi, 590014";
            String ddlno1 = "20-B: KA-BG3-256791";
            String ddlno2 = "20-B: KA-BG3-256792";
            String dgstin = "29AEYFS5351J1Z3";
            String dphoneno = "9916151914";

            System.out.print("Enter chemist id: ");
            int id = sc.nextInt();
            String cname = "", caddress = "", cdlno1 = "", cdlno2 = "", cgstin = "";
            long cphoneno = 0;
            int cid;
            ResultSet resultSet = statement.executeQuery("select * from chemist where chemist_id = " + id);
            while (resultSet.next()) {
                cid = resultSet.getInt("chemist_id");
                cname = resultSet.getString("chemist_name");
                caddress = resultSet.getString("address");
                cphoneno = resultSet.getLong("phoneno");
                cdlno1 = resultSet.getString("dlno1");
                cdlno2 = resultSet.getString("dlno2");
                cgstin = resultSet.getString("gstin");
            }
            System.out.print("Enter the number of items: ");
            int n = sc.nextInt();
            ArrayList<ArrayList> alist = new ArrayList<ArrayList>(n);
            double totalamt = 0;
            for (int i = 0; i < n; i++) {
                ArrayList a = new ArrayList();
                System.out.print("Enter batch number: ");
                String batch = sc.next();
                System.out.print("Enter quantity: ");
                int q = sc.nextInt();
                System.out.print("Enter free quantity: ");
                int f = sc.nextInt();
                resultSet = statement.executeQuery("select * from medicine where batchno = '" + batch + "';");
                String mfr = "", name = "", pack = "", batchno = "", expdate = "";
                int hsn = 0, gst = 0, qty = 0;
                float mrp = 0, trp = 0, amt = 0;
                while (resultSet.next()) {
                    mfr = resultSet.getString("manufacturer");
                    name = resultSet.getString("med_name");
                    pack = resultSet.getString("pack");
                    hsn = resultSet.getInt("hsn");
                    batchno = resultSet.getString("batchno");
                    expdate = resultSet.getString("expiry_date");
                    mrp = resultSet.getFloat("mrp");
                    trp = resultSet.getFloat("trp");
                    qty = resultSet.getInt("quantity");
                    gst = resultSet.getInt("gst");
                }
                if ((q + f) > qty) {
                    System.out.println("Insufficient Stock !");
                    return;
                }
                a.add(mfr);
                a.add(name);
                a.add(hsn);
                a.add(pack);
                a.add(batchno);
                a.add(expdate);
                a.add(mrp);
                a.add(trp);
                amt += (trp * q);
                a.add(q);
                a.add(f);
                qty -= (q + f);
                String updateQuantityQuery = "update medicine set quantity" + " = " + qty + " where batchno = '" + batch + "';";
                statement.executeUpdate(updateQuantityQuery);
                a.add(gst);
                a.add(amt);
                alist.add(a);
                totalamt += amt;
            }
            double cgst = 0.06 * totalamt;
            double sgst = 0.06 * totalamt;
            double grandtotal = Math.round(totalamt + cgst + sgst);

            resultSet = statement.executeQuery("select * from orders;");
            int billno = 1;
            while (resultSet.next()) {
                billno++;
            }

            System.out.println();
            System.out.println("___________________________________");
            System.out.println("Bill Details");
            LocalDate date = LocalDate.now();
            System.out.println("Bill Number: " + billno);
            System.out.println("Date: " + date);
            System.out.println();
            System.out.println("___________________________________");
            System.out.println("Distributor Details");
            System.out.println("Name: " + dname);
            System.out.println("Address: " + daddress);
            System.out.println("DL No.: " + ddlno1);
            System.out.println("DL No.: " + ddlno2);
            System.out.println("GST No.: " + dgstin);
            System.out.println("Phone No.: " + dphoneno);
            System.out.println();
            System.out.println("___________________________________");
            System.out.println("Chemist Details");
            System.out.println("Name: " + cname);
            System.out.println("Address: " + caddress);
            System.out.println("DL No.: " + cdlno1);
            System.out.println("DL No.: " + cdlno2);
            System.out.println("GST No.: " + cgstin);
            System.out.println("Phone No.: " + cphoneno);
            System.out.println();
            System.out.println("___________________________________");
            System.out.println("Sl No.\tManufacturer\tName\tHSN Code\tPack\tBatch No.\tExpiry Date\tMRP\tTRP\tQuantity\tFree\tGST%\tAmount");
            for (int i = 0; i < alist.size(); i++) {
                System.out.print(i + 1 + "\t");
                for (int j = 0; j < alist.get(i).size(); j++) {
                    System.out.print(alist.get(i).get(j) + "\t");
                }
                System.out.println();
            }
            System.out.println();
            System.out.println("___________________________________");
            System.out.println("CGST: " + cgst);
            System.out.println("SGST: " + sgst);
            System.out.println("Grand Total: " + grandtotal);
            System.out.println();
            String addOrderQuery = "insert into orders (date, name, totalamt) values ('"+date+ "','" + cname + "'," + grandtotal + ")";
            statement.executeUpdate(addOrderQuery);
        } catch (Exception exception) {
            System.out.println("Error when adding new order " + exception);
        }
    }

    public static void viewBills() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastikpharma", "root", "29122002");
            Statement statement;
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from orders");
            String name="", date="";
            float amt=0;
            int billno=0;
            System.out.println("Bill No.\tDate\tChemist Name\tAmount");
            while(resultSet.next()){
                billno = resultSet.getInt("billno");
                date = resultSet.getString("date");
                name = resultSet.getString("name");
                amt = resultSet.getFloat("totalamt");
                System.out.println(billno+"\t"+date+"\t"+name+"\t"+amt);
            }
        } catch (Exception exception) {
            System.out.println("Error when viewing bills " + exception);
        }
    }

    public static void viewInventory() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastikpharma", "root", "29122002");
            Statement statement;
            statement = conn.createStatement();
            String viewInventoryQuery = "select * from medicine order by med_name";
            ResultSet resultSet = statement.executeQuery(viewInventoryQuery);
            String mfr, name, pack, batchno, expdate;
            int hsn, gst, qty;
            float mrp, trp;
            System.out.println("Manufacturer\tMedicine Name\tPack\tHSN Code\tBatch\tExpiry Date\tMRP\tTRP\tQuantity\tGST%");
            while (resultSet.next()) {
                mfr = resultSet.getString("manufacturer");
                name = resultSet.getString("med_name");
                pack = resultSet.getString("pack");
                hsn = resultSet.getInt("hsn");
                batchno = resultSet.getString("batchno");
                expdate = resultSet.getString("expiry_date");
                mrp = resultSet.getFloat("mrp");
                trp = resultSet.getFloat("trp");
                qty = resultSet.getInt("quantity");
                gst = resultSet.getInt("gst");
                System.out.println(mfr + "\t" + name + "\t" + pack + "\t" + hsn + "\t" + batchno + "\t" + expdate + "\t" + mrp + "\t" + trp + "\t" + qty + "\t" + gst);
            }
        } catch (Exception exception) {
            System.out.println("Error in viewing inventory " + exception);
        }
    }
}