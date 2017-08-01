/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 *
 * @author Chanaka
 */
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class AdvancedComboFilter {

    private List<String> array;
    private JTextField jtf;
    private JTextComponent jtc;

    public static void Filter(JComboBox jComboBox, String sql) {
        jComboBox.setEditable(true);
        AdvancedComboFilter sdf = new AdvancedComboFilter();
        jComboBox.setModel(new DefaultComboBoxModel(sdf.populateArray(jComboBox, sql).toArray()));
        sdf.loadSearch(jComboBox, jComboBox, sql);
    }

    public static void Filter(JComboBox jComboBox, JComponent nextFocesComponent, String sql) {
        jComboBox.setEditable(true);
        AdvancedComboFilter sdf = new AdvancedComboFilter();
        jComboBox.setModel(new DefaultComboBoxModel(sdf.populateArray(jComboBox, sql).toArray()));
        sdf.loadSearch(jComboBox, nextFocesComponent, sql);
    }

    private void loadSearch(final JComboBox jComboBox1, final JComponent nextFoces,String sql) {
        this.array = populateArray(jComboBox1, sql);

        jComboBox1.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                try {
                    char ch = evt.getKeyChar();
                    if (ch != 65535) {
                        AdvancedComboFilter.this.jtf = ((JTextField) jComboBox1.getEditor().getEditorComponent());
                        AdvancedComboFilter.this.jtc = ((JTextComponent) jComboBox1.getEditor().getEditorComponent());
                        AdvancedComboFilter.this.comboFilter(AdvancedComboFilter.this.jtf.getText(), jComboBox1);
                    }
                    if (evt.getKeyCode() == 10) {
                        AdvancedComboFilter.this.jtc.setText(jComboBox1.getSelectedItem().toString());
                        jComboBox1.hidePopup();
                        nextFoces.grabFocus();
                    }
                } catch (Exception e) {
                }
            }
        });
    }

    private void comboFilter(String enteredText, JComboBox jComboBox) {
        List filterArray = new ArrayList();
        for (int i = 1; i < this.array.size(); i++) {
            if (((String) this.array.get(i)).toLowerCase().contains(enteredText.toLowerCase())) {
                filterArray.add(this.array.get(i));
            }
        }
        if (filterArray.size() > 0) {
            jComboBox.setModel(new DefaultComboBoxModel(filterArray.toArray()));
            this.jtf.setText(enteredText);
            jComboBox.showPopup();
        } else {
            jComboBox.setModel(new DefaultComboBoxModel());
            this.jtc.setText(enteredText);
            jComboBox.hidePopup();
        }
    }

    private List<String> populateArray(JComboBox jComboBox1, String sql) {
        List test = new ArrayList();
        test.add("");
        try {
            ResultSet r=new jdbc().getdata(sql);
            while (r.next()) {                
                test.add(r.getString(1));
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, test);
        }
        return test;
    }
}
