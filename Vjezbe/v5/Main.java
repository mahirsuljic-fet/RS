import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MyWindow window = new MyWindow();
    }
}

class MyWindow extends JFrame {
    DefaultListModel<String> listModel = new DefaultListModel<>();
    Panel panel = new Panel();

    public MyWindow() {
        setTitle("My Window");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

//        listModel.addElement("Test");

        JList<String> list = new JList<>(listModel);

//        String[] options = {"A", "B", "C", "D", "E", "F", "G", "H"};
//        JOptionPane.showMessageDialog(this, "Hello World", "Title", JOptionPane.PLAIN_MESSAGE);
//        Integer opt = JOptionPane.showConfirmDialog(this, list, "My Window", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
//        String s = JOptionPane.showInputDialog(this, list, "My Window", JOptionPane.PLAIN_MESSAGE);
//        Integer i = JOptionPane.showOptionDialog(this, "Hello World", "Title", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
//        listModel.addElement(options[i]);
//        add(list);

        panel.setLayout(new BorderLayout());

        JCheckBox checkBox = new JCheckBox("Check box");

        JButton addButton = new JButton("Insert");
        JButton removeButton = new JButton("Remove");

        addButton.setSize(100, 100);
        removeButton.setSize(100, 100);

        addButton.addActionListener(e -> {
            String str = JOptionPane.showInputDialog(this, "Add new list element", "Input", JOptionPane.PLAIN_MESSAGE);
            listModel.addElement(str);
        });

        removeButton.addActionListener(e -> {
            List<String> tempList = list.getSelectedValuesList();
            tempList.forEach(listModel::removeElement);
        });

        panel.add(list, BorderLayout.CENTER);
        panel.add(addButton, BorderLayout.NORTH);
        panel.add(removeButton, BorderLayout.SOUTH);

        add(panel);
    }
}
