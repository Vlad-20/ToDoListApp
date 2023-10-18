import java.awt.*;

public class Constants {

    //frame configuration
    public static final Dimension GUI_SIZE = new Dimension(540, 760);

    //banner configuration
    public static final Dimension BANNER_SIZE = new Dimension(GUI_SIZE.width, 50);

    //task panel configuration
    public static final Dimension TASKPANEL_SIZE = new Dimension(GUI_SIZE.width - 30, GUI_SIZE.height - 175);

    //add task button configuration
    public static final Dimension ADD_TASKBUTTON_SIZE = new Dimension(GUI_SIZE.width - 270, 50);

    //task component configuration
    public static final Dimension TASKFIELD_SIZE = new Dimension((int)(TASKPANEL_SIZE.width * 0.75), 50);

    //checkbox configuration
    public static final Dimension CHECKBOX_SIZE = new Dimension((int)(TASKPANEL_SIZE.width * 0.05), 25);

    //delete button configuration
    public static final Dimension DELETEBUTTON_SIZE = new Dimension((int)(TASKPANEL_SIZE.width * 0.10), 50);
}
