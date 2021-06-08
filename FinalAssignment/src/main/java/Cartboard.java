import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

public class Cartboard extends JFrame{
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton updateButton;
    private JLabel stateLabel2;
    private JButton readDataButton;
    private JButton editDataButton;
    private JButton getStatisticsButton;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JLabel label1;
    private JTextArea textArea1;
    private JButton seeDocumentButton;
    private FirebaseDatabase database;
    private DatabaseReference ref;
    private String[] list;
    private double[] ageList;

    public Cartboard() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(520,300));
        setContentPane(panel1);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("DocNotes");
        try {
            FileInputStream serviceAccount = new FileInputStream("src/main/resources/docnotes-19b4a-firebase-adminsdk-2aawv-144071d7aa.json");
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://docnotes-19b4a-default-rtdb.firebaseio.com/")
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                database = FirebaseDatabase.getInstance();
                ref = database.getReference(textField1.getText());
                new Thread(()->{
                    ref.child("Name").setValue(textField2.getText(), new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if (databaseError!=null){
                                stateLabel2.setText("x "+databaseError.getMessage());
                            }
                            else{
                                stateLabel2.setText("State: Updated ✓");
                            }
                        }
                    });
                }).start();
                new Thread(()->{
                    ref.child("Birthday").setValue(textField3.getText(), new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if (databaseError!=null){
                                stateLabel2.setText("x "+databaseError.getMessage());
                            }
                            else{
                                stateLabel2.setText("State: Updated ✓");
                            }
                        }
                    });
                }).start();
                new Thread(()->{
                    ref.child("Timestamp").setValue(textField4.getText(), new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if (databaseError!=null){
                                stateLabel2.setText("x "+databaseError.getMessage());
                            }
                            else{
                                stateLabel2.setText("State: Updated ✓");
                            }
                        }
                    });
                }).start();
                new Thread(()->{
                    ref.child("City").setValue(textField5.getText(), new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if (databaseError!=null){
                                stateLabel2.setText("x "+databaseError.getMessage());
                            }
                            else{
                                stateLabel2.setText("State: Updated ✓");
                            }
                        }
                    });
                }).start();
                new Thread(()->{
                    ref.child("UnderlyingDisease").setValue(textField6.getText(), new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if (databaseError!=null){
                                stateLabel2.setText("x "+databaseError.getMessage());
                            }
                            else{
                                stateLabel2.setText("State: Updated ✓");
                            }
                        }
                    });
                }).start();
            }
        });
        readDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                database = FirebaseDatabase.getInstance();
                ref = database.getReference();
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(textField1.getText())) {
                            if (textField1.getText().isBlank())
                                stateLabel2.setText("State: Type ID, please");
                            else stateLabel2.setText("State: ID found ✓");
                        }
                        else stateLabel2.setText("State: ID does not exist");
                        label1.setText("Patient Count: "+dataSnapshot.getChildrenCount());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        stateLabel2.setText(databaseError.getMessage());
                    }
                });
                new Thread(()->{
                    ref = database.getReference(textField1.getText()+"/Name");
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            textField2.setText(dataSnapshot.getValue(String.class));
                            stateLabel2.setText("State: ✓");
                            editDataButton.setEnabled(true);
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            stateLabel2.setText(databaseError.getMessage());
                        }
                    });
                }).start();
                new Thread(()->{
                    ref = database.getReference(textField1.getText()+"/Birthday");
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            textField3.setText(dataSnapshot.getValue(String.class));
                            stateLabel2.setText("State: ✓");
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            stateLabel2.setText(databaseError.getMessage());
                        }
                    });
                }).start();
                new Thread(()->{
                    ref = database.getReference(textField1.getText()+"/Timestamp");
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            textField4.setText(dataSnapshot.getValue(String.class));
                            stateLabel2.setText("State: ✓");
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            stateLabel2.setText(databaseError.getMessage());
                        }
                    });
                }).start();
                new Thread(()->{
                    ref = database.getReference(textField1.getText()+"/City");
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            textField5.setText(dataSnapshot.getValue(String.class));
                            stateLabel2.setText("State: ✓");
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            stateLabel2.setText(databaseError.getMessage());
                        }
                    });
                }).start();
                new Thread(()->{
                    ref = database.getReference(textField1.getText()+"/UnderlyingDisease");
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            textField6.setText(dataSnapshot.getValue(String.class));
                            stateLabel2.setText("State: ✓");
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            stateLabel2.setText(databaseError.getMessage());
                        }
                    });
                }).start();
            }
        });
        editDataButton.addActionListener(new ActionListener() {
            int i=1;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(i%2!=0){
                    textField2.setEditable(true);
                    textField3.setEditable(true);
                    textField4.setEditable(true);
                    textField5.setEditable(true);
                    textField6.setEditable(true);
                    updateButton.setEnabled(true);
                    editDataButton.setText("Lock Edit");
                    i++;
                }
                else if (i%2==0){
                    textField2.setEditable(false);
                    textField3.setEditable(false);
                    textField4.setEditable(false);
                    textField5.setEditable(false);
                    textField6.setEditable(false);
                    updateButton.setEnabled(false);
                    editDataButton.setText("Unlock Edit");
                    i++;
                }
            }
        });
        getStatisticsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(()-> {
                    database = FirebaseDatabase.getInstance();
                    ref = database.getReference();
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            list = new String[(int) dataSnapshot.getChildrenCount()];
                            ageList = new double[(int) dataSnapshot.getChildrenCount()];
                            for (int i = 1; i <= dataSnapshot.getChildrenCount(); i++) {
                                list[i - 1] = dataSnapshot.child(i + "/Name").getValue(String.class);
                                ageList[i - 1] = Year.now().getValue() - Integer.parseInt(dataSnapshot.child(i + "/Birthday").getValue(String.class).substring(6));
                            }
                            textArea1.setText(Arrays.stream(list).map(String::toUpperCase).sorted().collect(Collectors.toList())
                                    + "\nTotal number of patients: " + Arrays.stream(list).count()
                                    + "\nAverage age: " + Arrays.stream(ageList).average().getAsDouble()
                                    + "\nMinimum age: " + Arrays.stream(ageList).min().getAsDouble()
                                    + "\nMaximum age: " + Arrays.stream(ageList).max().getAsDouble());
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }).start();
            }
        });
        seeDocumentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DocumentViewer(textField1.getText()+".jpg");
            }
        });
    }
}
