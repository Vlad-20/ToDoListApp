import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TaskComponent extends JPanel implements ActionListener {
    private JCheckBox checkBox;
    private JTextPane taskField;
    private JButton deleteButton;
    private JPanel parentPanel;

    public JTextPane getTaskField() {
        return taskField;
    }

    public TaskComponent(JPanel parentPanel) {
        this.parentPanel = parentPanel;

        //task field
        taskField = new JTextPane();
        taskField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        taskField.setPreferredSize(Constants.TASKFIELD_SIZE);
        taskField.setContentType("text/html");

        //indicates which task field is currently being edited
        taskField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                taskField.setBackground(Color.WHITE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                taskField.setBackground(null);
            }
        });

        //checkbox
        checkBox = new JCheckBox();
        checkBox.setPreferredSize(Constants.CHECKBOX_SIZE);
        checkBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        checkBox.addActionListener(this);

        //delete button
        ImageIcon binIcon = new ImageIcon(getClass().getResource("/resources/bin.png"));
        //resize icon to fit in the button
        Image image = binIcon.getImage();
        Image newImage = image.getScaledInstance(Constants.DELETEBUTTON_SIZE.width - 30, Constants.DELETEBUTTON_SIZE.height - 30, Image.SCALE_SMOOTH);
        binIcon = new ImageIcon(newImage);
        deleteButton = new JButton(binIcon);
        deleteButton.setPreferredSize(Constants.DELETEBUTTON_SIZE);
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteButton.setIcon(binIcon);
        deleteButton.addActionListener(this);

        //add to this task component
        add(checkBox);
        add(taskField);
        add(deleteButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(checkBox.isSelected()) {
            //replaces all html to empty strings
            String taskText = taskField.getText().replaceAll("<[^>]*>", "");


            //add strikethrough text
            taskField.setText("<html><s>" + taskText + "</s></html>");
        } else if(!checkBox.isSelected()) {
            String taskText = taskField.getText().replaceAll("<[^>]*>", "");

            taskField.setText(taskText);
        }

        //delete tasks
        if(e.getSource() == deleteButton) {
            //delete this component from the parent panel
            parentPanel.remove(this);
            parentPanel.repaint();
            parentPanel.revalidate();
        }
    }
}
