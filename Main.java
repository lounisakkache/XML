package MiniProject;

public class Main {
    public static void main(String[] args)  {
        MyDomParser XML=new MyDomParser();
        XML.XMLEXECUTE();
        String[][] Data=XML.getData();
        //Display Data in the terminal
        XML.Display(Data);
        //Display Data in Small Window
        AdvancedTableExample mainFrame = new AdvancedTableExample(Data);
        mainFrame.setVisible( true );
    }
}
