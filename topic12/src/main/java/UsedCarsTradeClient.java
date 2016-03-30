import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

/**
 * Created by Dima on 27.03.2016.
 */
public class UsedCarsTradeClient {

    public static int numberOfColumns;

    private JFrame enterFrame;

    private JFrame frame;
    private static Object[] ADS_TYPE = {"ALL_ADS","MY_ADS"};
    private static JScrollPane tableScrollPane;
    private static final Object[] TABLE_COLUMN_NAMES = {"VIN", "Price", "Information", "Owner_contacts", "Manufacturer", "Model", "Release year"};
    private static final int NUMBER_OF_SEARCH_CRITERIA = 6;
    private static int PLACE_OF_VIN_IN_TABLE = 0;
    private static JTable tableAds = null;

    private JFrame dialogFrame = null;

    private static User user;
    private JComboBox adsList;
    private static JButton deleteButton;


    private JTextField manufacturerTextField = null;
    private JTextField modelNameTextField = null;
    private JTextField loReleaseYearTextField = null;
    private JTextField hiReleaseYearTextField = null;
    private JTextField loPriceTextField = null;
    private JTextField hiPriceTextField = null;

    private JTextField nameTF = null;
    private JTextField surnameTF = null;
    private JTextField phoneTF = null;
    private JPasswordField passwordField = null;

    private JTextField vinCodeTF = null;
    private JTextField manufacturerTF = null;
    private JTextField modelTF = null;
    private JTextField releaseYearTF = null;
    private JTextField informationTF = null;
    private JTextField priceTF = null;


    public UsedCarsTradeClient() {
        UsedCarsTradeDB usedCarsTradeDB = new UsedCarsTradeDB ();

        enterFrame = new JFrame ();

        JTabbedPane jTabbedPane = new JTabbedPane ();
        JPanel signUpPanel = new JPanel ();
        signUpPanel.setLayout (new BoxLayout (signUpPanel, BoxLayout.Y_AXIS));

        JPanel namePanel = new JPanel ();
        JLabel nameLabel = new JLabel ("Enter your name");
        nameTF = new JTextField (20);
        namePanel.add (nameLabel);
        namePanel.add (nameTF);

        JPanel surnamePanel = new JPanel ();
        JLabel surnameLabel = new JLabel ("Enter your surname");
        surnameTF = new JTextField (20);
        surnamePanel.add (surnameLabel);
        surnamePanel.add (surnameTF);

        JPanel phonePanel = new JPanel ();
        JLabel phoneLabel = new JLabel ("Enter your phone");
        phoneTF = new JTextField (20);
        phonePanel.add (phoneLabel);
        phonePanel.add (phoneTF);

        JPanel passwordPanel = new JPanel ();
        JLabel passwordLabel = new JLabel ("Enter your password");
        passwordField = new JPasswordField (20);
        passwordPanel.add (passwordLabel);
        passwordPanel.add (passwordField);

        JPanel passwordRepeatPanel = new JPanel ();
        JLabel passwordRepeatLabel = new JLabel ("Repeat your password");
        JPasswordField passwordRepeatField = new JPasswordField (20);
        passwordRepeatPanel.add (passwordRepeatLabel);
        passwordRepeatPanel.add (passwordRepeatField);

        JButton signUpButton = new JButton("Sign up in service");
        signUpButton.addActionListener (new SignUpButtonActionListener ());

        signUpPanel.add(namePanel);
        signUpPanel.add (surnamePanel);
        signUpPanel.add (phonePanel);
        signUpPanel.add (passwordPanel);
        signUpPanel.add(passwordRepeatPanel);
        signUpPanel.add (signUpButton);

        jTabbedPane.add ("Sign up", signUpPanel);

        enterFrame.getContentPane ().add (jTabbedPane);
        enterFrame.setSize (600,600);
        enterFrame.setVisible (true);
    }

    private void start(){
        frame = new JFrame ();
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frame,
                        "Are you sure to close this window?", "Really Closing?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    System.exit(0);
                    UsedCarsTradeImpl.closePreparedStatements();
                    UsedCarsTradeDB.closeConnection ();
                }
            }
        });
        frame.setDefaultCloseOperation (WindowConstants.DO_NOTHING_ON_CLOSE);

        numberOfColumns = TABLE_COLUMN_NAMES.length;
        Object[][] data = DatabaseOperations.getAllCarAds ();
        tableAds = new JTable (new DefaultTableModel (data,  TABLE_COLUMN_NAMES));
        tableScrollPane = new JScrollPane ();
        tableScrollPane.setViewportView (tableAds);
        frame.getContentPane ().add (tableScrollPane, BorderLayout.CENTER);


        JPanel panel = new JPanel ();
        JButton searchButton = new JButton ("Find the ad");
        searchButton.addActionListener (new SearchButtonActionListener());
        manufacturerTextField = new HintTextField ("manufacturer");
        modelNameTextField = new HintTextField ("model name");
        loReleaseYearTextField = new HintTextField ("from release year");
        hiReleaseYearTextField = new HintTextField ("to release year");
        loPriceTextField = new HintTextField ("lower price");
        hiPriceTextField = new HintTextField ("high price");
        JButton addButton = new JButton ("Add new ad");
        deleteButton = new JButton ("Delete selected ads");
        addButton.addActionListener (new AddButtonActionListener ());
        deleteButton.addActionListener (new DeleteButtonActionListener());
        deleteButton.setEnabled (false);
        adsList = new JComboBox(ADS_TYPE);
        adsList.addActionListener (new ListAdsListener ());

        panel.add (searchButton);
        panel.add (manufacturerTextField);
        panel.add (modelNameTextField);
        panel.add (loReleaseYearTextField);
        panel.add (hiReleaseYearTextField);
        panel.add (loPriceTextField);
        panel.add (hiPriceTextField);
        panel.add (addButton);
        panel.add (deleteButton);
        panel.add (adsList);

        frame.getContentPane ().add (panel, BorderLayout.NORTH);

        frame.setSize (1280, 720);
        frame.setVisible (true);
    }

    //Loads user ads or all ads
    private static void loadAdsChoice(Object adsTypeSelection){
        Object[][] data = null;
        DefaultTableModel model = (DefaultTableModel) tableAds.getModel ();
        if (adsTypeSelection.equals ("ALL_ADS")){
            data = DatabaseOperations.getAllCarAds ();
            deleteButton.setEnabled (false);
        }
        else {
            data = DatabaseOperations.getUserCarAds (user.getIdInDB ());
            deleteButton.setEnabled (true);
        }

        insertDataInTableModel(model,data);
    }


    private void showAddAdDialog(){
        dialogFrame = new JFrame ();

        JPanel dialogPanel = new JPanel ();
        dialogPanel.setLayout (new BoxLayout (dialogPanel, BoxLayout.Y_AXIS));

        JPanel vinCodePanel = new JPanel ();
        JLabel vinCodeLabel = new JLabel ("Enter car's VIN Code");
        vinCodeTF = new JTextField (20);
        vinCodePanel.add (vinCodeLabel);
        vinCodePanel.add (vinCodeTF);

        JPanel manufacturerPanel = new JPanel ();
        JLabel manufacturerLabel = new JLabel ("Enter car's manufacturer");
        manufacturerTF = new JTextField (20);
        manufacturerPanel.add (manufacturerLabel);
        manufacturerPanel.add (manufacturerTF);

        JPanel modelPanel = new JPanel ();
        JLabel modelLabel = new JLabel ("Enter car's model");
        modelTF = new JTextField (20);
        modelPanel.add (modelLabel);
        modelPanel.add (modelTF);

        JPanel releaseYearPanel = new JPanel ();
        JLabel releaseYearLabel = new JLabel ("Enter car's release year");
        releaseYearTF = new JTextField (20);
        releaseYearPanel.add (releaseYearLabel);
        releaseYearPanel.add (releaseYearTF);

        JPanel informationPanel = new JPanel ();
        JLabel informationLabel = new JLabel ("Enter detailed info about the car");
        informationTF = new JTextField (20);
        informationPanel.add (informationLabel);
        informationPanel.add (informationTF);

        JPanel pricePanel = new JPanel ();
        JLabel priceLabel = new JLabel ("Enter car's price");
        priceTF = new JTextField (20);
        pricePanel.add (priceLabel);
        pricePanel.add (priceTF);

        JButton createNewAdButton = new JButton ("Create Ad");
        createNewAdButton.addActionListener (new CreateNewAdActionListener ());

        dialogPanel.add (vinCodePanel);
        dialogPanel.add (manufacturerPanel);
        dialogPanel.add (modelPanel);
        dialogPanel.add (releaseYearPanel);
        dialogPanel.add (informationPanel);
        dialogPanel.add (pricePanel);
        dialogPanel.add (createNewAdButton);
        dialogFrame.add(dialogPanel);

        dialogFrame.setSize (540,720);
        dialogFrame.setVisible (true);
    }

    private void gatherFieldsText(){
        String vin = vinCodeTF.getText ();
        String manufacturer = manufacturerTF.getText ();
        String modelName = modelTF.getText ();
        String releaseYear = releaseYearTF.getText ();
        String information = informationTF.getText ();
        String price = priceTF.getText ();
        if (vin.isEmpty () || manufacturer.isEmpty () || modelName.isEmpty ()
                || releaseYear.isEmpty () || information.isEmpty () || price.isEmpty ()){
            JOptionPane.showMessageDialog (dialogFrame, "Write down all the fields");
        }
        else{
            String phone = Integer.toString (user.getPhone ());
            Car car = new Car(vin,manufacturer,modelName, Integer.parseInt (releaseYear), information, Double.parseDouble (price));
            try {
                DatabaseOperations.addCarAd (car, user.getIdInDB ());
                DefaultTableModel model = (DefaultTableModel) tableAds.getModel ();
                model.addRow (new Object[] {vin, price, information, phone, manufacturer, modelName, releaseYear});
            } catch (SQLException e) {
                JOptionPane.showMessageDialog (dialogFrame, "This VIN is already registered");
            }

        }
    }

    private void removeSelectedAds(){
        DefaultTableModel model = (DefaultTableModel) tableAds.getModel();
        int[] selectedRows = tableAds.getSelectedRows();
        if (selectedRows.length == 0) JOptionPane.showMessageDialog (frame, "None of ads selected");

        for (int i = selectedRows.length - 1; i >= 0; i--) {
            System.out.println(model.getValueAt (selectedRows[i],0).toString ());
            DatabaseOperations.deleteCarAd (model.getValueAt (selectedRows[i],PLACE_OF_VIN_IN_TABLE).toString ());
            model.removeRow(selectedRows[i]);
        }
    }

    private void searchWithParameters(){
        CarSearchParameter[] carSearchParameters = new CarSearchParameter[NUMBER_OF_SEARCH_CRITERIA];
        carSearchParameters[0] = new CarSearchParameter (manufacturerTextField.getText ());
        carSearchParameters[1] = new CarSearchParameter (modelNameTextField.getText ());
        carSearchParameters[2] = new CarSearchParameter (loReleaseYearTextField.getText ());
        carSearchParameters[3] = new CarSearchParameter (hiReleaseYearTextField.getText ());
        carSearchParameters[4] = new CarSearchParameter (loPriceTextField.getText ());
        carSearchParameters[5] = new CarSearchParameter (hiPriceTextField.getText ());
        adsList.setSelectedItem ("ALL_ADS");

        Object[][] data = DatabaseOperations.getSearchedCarAds (carSearchParameters);
        if (data.length == 0){
            JOptionPane.showMessageDialog (frame, "No ads for this request");
        }
        else {
            DefaultTableModel model = (DefaultTableModel) tableAds.getModel ();
            insertDataInTableModel (model, data);
        }
    }

    private static void insertDataInTableModel(DefaultTableModel model, Object[][] data){
        int rowCount = model.getRowCount ();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow (i);
        }
        for (int i = 0; i < data.length; i++) {
            model.insertRow (i, data[i]);
        }
    }

    class SignUpButtonActionListener implements  ActionListener{
        public void actionPerformed(ActionEvent e) {
            String name = nameTF.getText ();
            String surname = surnameTF.getText ();
            String phone = phoneTF.getText ();
            if (name.isEmpty () || surname.isEmpty () || phone.isEmpty () ){
                JOptionPane.showMessageDialog (enterFrame, "Not all fields are wrote down");
            }
            else {
                user = new User(name,surname,Integer.parseInt (phone),passwordField.getPassword ());
                DatabaseOperations.signUpUser (user);
                enterFrame.dispatchEvent(new WindowEvent (enterFrame, WindowEvent.WINDOW_CLOSING));
                start ();
            }
        }
    }

    class ListAdsListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            Object selection = adsList.getSelectedItem ();
            loadAdsChoice(selection);
        }
    }

    class CreateNewAdActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            gatherFieldsText();
        }
    }

    class AddButtonActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            showAddAdDialog();
        }
    }

    class DeleteButtonActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            removeSelectedAds();
        }
    }

    class SearchButtonActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            searchWithParameters();
        }
    }

    class HintTextField extends JTextField implements FocusListener {

        private final String hint;
        private boolean showingHint;

        public HintTextField(final String hint) {
            super (hint);
            this.hint = hint;
            this.showingHint = true;
            super.addFocusListener (this);
        }

        @Override
        public void focusGained(FocusEvent e) {
            if (this.getText ().isEmpty ()) {
                super.setText ("");
                showingHint = false;
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (this.getText ().isEmpty ()) {
                super.setText (hint);
                showingHint = true;
            }
        }

        @Override
        public String getText() {
            return showingHint ? "" : super.getText ();
        }
    }


    public static void main(String[] args) {
        new UsedCarsTradeClient ();
    }
}
