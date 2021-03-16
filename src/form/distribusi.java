/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import static form.penduduk.tabmode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;

/**
 *
 * @author Azhari
 */
public class distribusi extends javax.swing.JFrame {
    public String nobrg, namabrg, jenis, stok;
    public String idpen, idkk;
    
    public static DefaultTableModel tabmode;
    private Connection conn = new koneksi().connect();
    
    public void waktu(){
        Date tgl = new Date();
        jtanggal.setDate(tgl);
    }
    
    public void itemTerpilihBarang(){
        popup.DataBarang Pb = new popup.DataBarang();
        Pb.dis = this;
        txtidbrg.setText(nobrg);
        txtnamabrg.setText(namabrg);
        txtjenis.setText(jenis);
        txtstok.setText(stok);
        
    }
    
    public void itemTerpilihPenerima(){
        popup.DataPenerima Pp = new popup.DataPenerima();
        Pp.dis = this;
        txtidpen.setText(idpen);
        txtidkk.setText(idkk);
    }
    
    public void hitung(){
        int total = 0;
        for (int i=0; i<tbldatamasuk.getRowCount(); i++){
        int amount = Integer.valueOf(tbldatamasuk.getValueAt(i,5).toString());
        total += amount;
        }
        txttotbrg.setText(Integer.toString(total));
    }

    /**
     * Creates new form penerima
     */
    public distribusi() {
        initComponents();
        kosong();
        aktif();
        autoID();
        waktu();
    }
    
    protected void kosong(){
        txtidbrg.setText("");
        txtidkk.setText("");
        txtidpen.setText("");
        txtnamabrg.setText("");
        txtjenis.setText("");
        txtnama.setText("");
        txtjmlterima.setText("");
        txtnort.setText("");
        txtanggota.setText("");
        waktu();
    }
    
    protected void aktif(){
        datatable();
        txttotbrg.requestFocus();        
        Object[] Baris ={"No Distribusi","No KK","No Barang","Nama Barang","Jenis","Total Masuk","Sisa Stok"};     
        tabmode = new DefaultTableModel(null, Baris);      
        tbldatamasuk.setModel(tabmode);
    }
    
    protected void autoID(){
        try {
            String sql = "SELECT id_distribusi FROM distribusibantuan WHERE id_distribusi LIKE 'D%' order by id_distribusi asc";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            txtiddis.setText("D0001");
            while (rs.next()) {
            String id = rs.getString("id_distribusi").substring(1);
            int AN = Integer.parseInt(id) + 1;
            String Nol = "";
            
            if(AN<10)
            {Nol = "000";}
            else if(AN<100)
            {Nol = "00";}
            else if(AN<1000)
            {Nol = "0";}
            else if(AN<10000)
            {Nol = "";}
            
            txtiddis.setText("D"+Nol + AN);
           }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Auto Number Gagal" +e);
        }
    }
    
    protected void nourut(){
        int brs = tabmode.getRowCount();
        for(int i=0; i<brs; i++){
            String no = String.valueOf(i+1);
            tabmode.setValueAt(no+".",i,0);
        }
    }
    
    protected void datatable(){
    Object[] Baris ={"No.","No Distribusi","No Penerima","Tgl Terima","No KK","Nama","Alamat","No RT","Anggota"};
    tabmode = new DefaultTableModel(null, Baris);
    tbdistribusi.setModel(tabmode);
    String cariitem=txtcari.getText();
    nourut();
    try {
        String sql = "SELECT\n" +
                    "a.id_distribusi,\n" +
                    "a.id_penerima,\n" +
                    "a.tgl,\n" +
                    "b.id_kk,\n" +
                    "c.nama,\n" +
                    "c.alamat,\n" +
                    "c.id_rt,\n" +
                    "c.jumlahanggota\n" +
                    "FROM\n" +
                    "distribusibantuan a\n" +
                    "INNER JOIN penerima b INNER JOIN kepalakeluarga c \n" +
                    "ON a.id_penerima = b.id_penerima and b.id_kk = c.id_kk where a.id_penerima like '%"+cariitem+"%'";
        Statement stat = conn.createStatement();
        ResultSet hasil = stat.executeQuery(sql);
        nourut();
        while (hasil.next()){
            tabmode.addRow(new Object[]{
                "",
                hasil.getString(1),
                hasil.getString(2),  
                hasil.getDate(3),
                hasil.getString(4),   
                hasil.getString(5),
                hasil.getString(6),
                hasil.getString(7),
                hasil.getString(8)
            }); 
//            String nodis = hasil.getString("a.id_distribusi");
//            String nopen = hasil.getString("a.id_penerima");
//            Date tgl = hasil.getDate("a.tgl");
//            String idkk = hasil.getString("b.id_kk");
//            String nama = hasil.getString("c.nama");
//            String alm = hasil.getString("c.alamat");
//            String nomorrt = hasil.getString("c.id_rt");
//            String jmlang = hasil.getString("c.jumlahanggota");
//            String[] data = {"",nodis,nopen,,tgl,idkk,nama,alm,nomorrt,jmlang};
//            tabmode.addRow(data);
            nourut();
        }
    }catch (Exception e) {
        JOptionPane.showMessageDialog(null, "data gagal dipanggil"+e);
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

        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btntambah = new javax.swing.JButton();
        txtnamabrg = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtidpen = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtidkk = new javax.swing.JTextField();
        txtjenis = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        bcaripen = new javax.swing.JButton();
        txtjmlterima = new javax.swing.JTextField();
        jtanggal = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        txtnama = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtanggota = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtnort = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtstok = new javax.swing.JTextField();
        txtsisa = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbdistribusi = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtiddis = new javax.swing.JTextField();
        txtcari = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtidbrg = new javax.swing.JTextField();
        bcaribrg = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbldatamasuk = new javax.swing.JTable();
        btnproses = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        txttotbrg = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setFont(new java.awt.Font("Sylfaen", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Daftar Pendistribusian Bantuan");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText("Nama Barang");

        btntambah.setText("Tambah");
        btntambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahActionPerformed(evt);
            }
        });

        txtnamabrg.setEditable(false);
        txtnamabrg.setEnabled(false);

        jLabel1.setText("No Penerima");

        txtidpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidpenActionPerformed(evt);
            }
        });

        jLabel4.setText("No KK");

        txtidkk.setEditable(false);

        txtjenis.setEditable(false);
        txtjenis.setEnabled(false);

        jLabel5.setText("Jenis");

        jLabel9.setText("Jumlah Terima");

        jLabel10.setText("Tanggal Masuk");

        bcaripen.setText("Cari");
        bcaripen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcaripenActionPerformed(evt);
            }
        });

        txtjmlterima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtjmlterimaActionPerformed(evt);
            }
        });

        jLabel8.setText("Nama");

        txtnama.setEditable(false);
        txtnama.setEnabled(false);

        jLabel11.setText("Jumlah Anggota");

        txtanggota.setEditable(false);
        txtanggota.setEnabled(false);

        jButton6.setText("Cari");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel12.setText("No RT");

        txtnort.setEditable(false);
        txtnort.setEnabled(false);

        jLabel14.setText("Stok Barang");

        txtsisa.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel15.setText("Sisa Stok");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btntambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtidpen, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bcaripen, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(223, 223, 223))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(txtnamabrg))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8)
                            .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(txtnort, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(txtanggota, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jtanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtjmlterima, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(txtstok, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtsisa, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(8, 8, 8))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtidkk, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtjenis, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnamabrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtidpen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bcaripen))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtidkk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtjenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6)
                            .addComponent(txtstok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtanggota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtnort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtjmlterima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtsisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14)
                .addComponent(btntambah, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbdistribusi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbdistribusi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbdistribusiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbdistribusi);

        jButton3.setText("Cetak Daftar Penerima");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel6.setText("No Distribusi");

        txtiddis.setEditable(false);
        txtiddis.setEnabled(false);

        txtcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcariKeyPressed(evt);
            }
        });

        jButton7.setText("Cari");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel7.setText("No Barang");

        bcaribrg.setText("Cari");
        bcaribrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcaribrgActionPerformed(evt);
            }
        });

        tbldatamasuk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbldatamasuk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbldatamasukMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbldatamasuk);

        btnproses.setText("Proses");
        btnproses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnprosesActionPerformed(evt);
            }
        });

        jButton4.setText("Batal");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Keluar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel13.setText("Total Barang");

        jButton8.setText("Hapus");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(248, 248, 248)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(51, 51, 51)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(txtiddis, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(txtidbrg, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bcaribrg, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnproses, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txttotbrg, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jLabel3))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton7)
                        .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtiddis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(txtidbrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bcaribrg)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txttotbrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton8))
                                .addGap(3, 3, 3)))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(btnproses)
                            .addComponent(jButton4)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        kosong();
        datatable();
        aktif();
        autoID();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        mainmenu frmbb = new mainmenu();
        frmbb.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tbdistribusiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbdistribusiMouseClicked
//        // TODO add your handling code here:
//        int bar = tbdistribusi.getSelectedRow();
//        String a = tabmode.getValueAt(bar, 1).toString();
//        String b = tabmode.getValueAt(bar, 2).toString();
//        Date c =  (Date)tabmode.getValueAt(bar, 3);
//        String d = tabmode.getValueAt(bar, 4).toString();
//        String e = tabmode.getValueAt(bar, 5).toString();
//        String g = tabmode.getValueAt(bar, 7).toString();
//        String h = tabmode.getValueAt(bar, 8).toString();
//        txtiddis.setText(a);
//        txtidpen.setText(b);
//        jtanggal.setDate(c);
//        txtidkk.setText(d);
//        txtnama.setText(e);
//        txtnort.setText(g);
//        txtanggota.setText(h);
//        
//        bcaripen.setEnabled(false);
//        txtidpen.setEditable(false);
    }//GEN-LAST:event_tbdistribusiMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtcariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyPressed
        // TODO add your handling code here:
//        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            datatable();
//        }
    }//GEN-LAST:event_txtcariKeyPressed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        datatable();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void bcaribrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcaribrgActionPerformed
        popup.DataBarang Pb = new popup.DataBarang();
        Pb.dis = this;
        Pb.setVisible(true);
        Pb.setResizable(false);
    }//GEN-LAST:event_bcaribrgActionPerformed

    private void tbldatamasukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbldatamasukMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbldatamasukMouseClicked

    private void txtjmlterimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtjmlterimaActionPerformed
        int hitungstok=Integer.parseInt(txtstok.getText());
        int hitungqty=Integer.parseInt(txtjmlterima.getText());
        int jml = hitungstok - hitungqty;
        txtsisa.setText(String.valueOf(jml));
    }//GEN-LAST:event_txtjmlterimaActionPerformed

    private void bcaripenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcaripenActionPerformed
        popup.DataPenerima Pp = new popup.DataPenerima();
        Pp.dis = this;
        Pp.setVisible(true);
        Pp.setResizable(false);
    }//GEN-LAST:event_bcaripenActionPerformed

    private void txtidpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidpenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidpenActionPerformed

    private void btntambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahActionPerformed
        try { 
        String iddis = txtiddis.getText();
        String kk = txtidkk.getText();
        String kode = txtidbrg.getText();
        String namabrg = txtnamabrg.getText();
        String jenisbrg = txtjenis.getText();
        int total = Integer.parseInt(txtjmlterima.getText());
        int sisa = Integer.parseInt(txtsisa.getText());

        tabmode.addRow(new Object[]{iddis,kk,kode,namabrg,jenisbrg,total,sisa});
        tbldatamasuk.setModel(tabmode);
        }
        catch(Exception e)
        {
        JOptionPane.showMessageDialog(null, "data gagal dipanggil"+e);
        }
        txtidbrg.setText("");
        txtnamabrg.setText("");
        txtjenis.setText("");
        txtjmlterima.setText("");
        txtsisa.setText("");
        txtstok.setText("");
        hitung();
    }//GEN-LAST:event_btntambahActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        String carikk = txtidkk.getText();

        try {
            String sql = "SELECT * FROM kepalakeluarga where id_kk = '"+carikk+"'";
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){

                String nama = hasil.getString("nama");
                txtnama.setText(nama);

                String jumlahanggota = hasil.getString("jumlahanggota");
                txtanggota.setText(jumlahanggota);
                
                String idrt = hasil.getString("id_rt");
                txtnort.setText(idrt);

            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "data gagal dipanggil"+e);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        int index = tbldatamasuk.getSelectedRow();
        tabmode.removeRow(index);
        tbldatamasuk.setModel(tabmode);
        hitung();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void btnprosesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnprosesActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fd = sdf.format(jtanggal.getDate());
        String sqlnota = "insert into distribusibantuan values (?,?,?,?)";
        String sqlisi = "insert into isi_distribusi values (?,?,?,?,?)";
        String updtotal = "update barangbantuan set stok = ? where id_barang = ?";
        try{

        PreparedStatement stat = conn.prepareStatement(sqlnota);
        stat.setString(1, txtiddis.getText());
        stat.setString(2, txtidpen.getText());
        stat.setString(3, txtnort.getText());
        stat.setString(4, fd);

        stat.executeUpdate();

        int t = tbldatamasuk.getRowCount();
        for(int i=0; i < t ; i++)
        {
        String xkd = tbldatamasuk.getValueAt(i, 2).toString();
        String xnb = tbldatamasuk.getValueAt(i, 3).toString();
        String xjns = tbldatamasuk.getValueAt(i, 4).toString();
        String xtot = tbldatamasuk.getValueAt(i, 5).toString();

        PreparedStatement stat2 = conn.prepareStatement(sqlisi);
        stat2.setString(1, txtiddis.getText());
        stat2.setString(2, xkd);
        stat2.setString(3, xnb);
        stat2.setString(4, xjns);
        stat2.setString(5, xtot);
        
        stat2.executeUpdate();
        }
        
        int a = tbldatamasuk.getRowCount();
        for(int i=0; i < a ; i++)
        {
        String xkd2 = tbldatamasuk.getValueAt(i, 2).toString();
        String xsisa = tbldatamasuk.getValueAt(i, 6).toString();

        PreparedStatement stat3 = conn.prepareStatement(updtotal);
        stat3.setString(1, xsisa);
        stat3.setString(2, xkd2);

        stat3.executeUpdate();
        datatable();
        
        }
        JOptionPane.showMessageDialog(null, "data berhasil disimpan");
        }
        catch (SQLException e){
        JOptionPane.showMessageDialog(null, "data gagal disimpan"+e);
        }
        kosong();
        aktif();
        autoID();
    }//GEN-LAST:event_btnprosesActionPerformed

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
            java.util.logging.Logger.getLogger(distribusi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(distribusi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(distribusi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(distribusi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new distribusi().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bcaribrg;
    private javax.swing.JButton bcaripen;
    private javax.swing.JButton btnproses;
    private javax.swing.JButton btntambah;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private com.toedter.calendar.JDateChooser jtanggal;
    private javax.swing.JTable tbdistribusi;
    private javax.swing.JTable tbldatamasuk;
    private javax.swing.JTextField txtanggota;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtidbrg;
    private javax.swing.JTextField txtiddis;
    private javax.swing.JTextField txtidkk;
    private javax.swing.JTextField txtidpen;
    private javax.swing.JTextField txtjenis;
    private javax.swing.JTextField txtjmlterima;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtnamabrg;
    private javax.swing.JTextField txtnort;
    private javax.swing.JTextField txtsisa;
    private javax.swing.JTextField txtstok;
    private javax.swing.JTextField txttotbrg;
    // End of variables declaration//GEN-END:variables
}
