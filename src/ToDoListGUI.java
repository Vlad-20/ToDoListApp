import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ToDoListGUI extends JFrame implements ActionListener {

    private JPanel taskPanel, taskComponentPanel;
    public ToDoListGUI() {
        super("My To Do List");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(Constants.GUI_SIZE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        addGuiComponents();
    }

    private void addGuiComponents() {
        //banner text
        JLabel bannerLabel = new JLabel("My TO DO List");
        bannerLabel.setFont(createFont("resources/LEMONMILK-Regular.otf", 25f));
        bannerLabel.setBounds((Constants.GUI_SIZE.width - bannerLabel.getPreferredSize().width) / 2, 15, Constants.BANNER_SIZE.width, Constants.BANNER_SIZE.height);

        //Task Panel
        taskPanel = new JPanel();

        //Task Component Panel
        taskComponentPanel = new JPanel();
        taskComponentPanel.setLayout(new BoxLayout(taskComponentPanel, BoxLayout.Y_AXIS));
        taskPanel.add(taskComponentPanel);

        //add scrolling
        JScrollPane scrollPane = new JScrollPane(taskPanel);
        scrollPane.setBounds(8, 70, Constants.TASKPANEL_SIZE.width, Constants.TASKPANEL_SIZE.height);
        scrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
        scrollPane.setMaximumSize(Constants.TASKPANEL_SIZE);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //modify the speed of scrolling
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(20);

        //add task button
        JButton addTaskButton = new JButton("Add Task");
        addTaskButton.setFont(createFont("resources/LEMONMILK-Regular.otf", 18f));
        addTaskButton.setBounds(135, Constants.GUI_SIZE.height - 95, Constants.ADD_TASKBUTTON_SIZE.width, Constants.ADD_TASKBUTTON_SIZE.height);
        addTaskButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addTaskButton.addActionListener(this);

        //add to the frame
        this.getContentPane().add(bannerLabel);
        this.getContentPane().add(scrollPane);
        this.getContentPane().add(addTaskButton);
    }

    private Font createFont(String resource, float size) {
        String filePath = getClass().getClassLoader().getResource(resource).getPath();

        //check to see if the path contains a folder
        if(filePath.contains("20%")) {
            filePath = getClass().getClassLoader().getResource(resource).getPath().replaceAll("%20", " ");
        }

        //create the font
        try {
            File customFontFile = new File(filePath);
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, customFontFile).deriveFont(size);
            return customFont;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equalsIgnoreCase("Add task")) {
            //create a task component
            TaskComponent taskComponent = new TaskComponent(taskComponentPanel);
            taskComponentPanel.add(taskComponent);

            //make previous task appear inactive
            if(taskComponentPanel.getComponentCount() > 1) {
                TaskComponent previousTask = (TaskComponent) taskComponentPanel.getComponent(taskComponentPanel.getComponentCount() - 2);
                previousTask.getTaskField().setBackground(null);
            }

            //focus on the task field
            taskComponent.getTaskField().requestFocus();
            repaint();
            revalidate();
        }
    }
}
