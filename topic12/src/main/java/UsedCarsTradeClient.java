import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.ObjectView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.Arc2D;

/**
 * Created by Dima on 27.03.2016.
 */
public class UsedCarsTradeClient {

    private JFrame frame;
    private static final Object[] TABLE_COLUMN_NAMES = {"VIN", "Price", "Information", "Owner_contacts"};
    private JTable tableAds = null;

    private JTextField manufacturerTextField = null;
    private JTextField modelNameTextField = null;
    private JTextField loReleaseYearTextField = null;
    private JTextField hiReleaseYearTextField = null;
    private JTextField loPriceTextField = null;
    private JTextField hiPriceTextField = null;

    private JTextField nameTF = null;
    private JTextField surnameTF = null;
    private JTextField phoneTF = null;
    private JTextField vinCodeTF = null;
    private JTextField manufacturerTF = null;
    private JTextField modelTF = null;
    private JTextField releaseYearTF = null;
    private JTextField informationTF = null;
    private JTextField priceTF = null;


    public UsedCarsTradeClient() {
        frame = new JFrame ();
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frame,
                        "Are you sure to close this window?", "Really Closing?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    System.exit(0);
                    UsedCarsTradeImpl.closePreparedStatements();
                }
            }
        });
        frame.setDefaultCloseOperation (WindowConstants.DO_NOTHING_ON_CLOSE);

        UsedCarsTradeImpl.initPreparedStatements ();
        Object[][] data = UsedCarsTradeImpl.preparedStatementQueryNoParameters ();
        tableAds = new JTable (new DefaultTableModel (data,  TABLE_COLUMN_NAMES));
        JScrollPane tableScrollPane = new JScrollPane (tableAds);
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
        JButton deleteButton = new JButton ("Delete selected ads");
        addButton.addActionListener (new AddButtonActionListener ());
        deleteButton.addActionListener (new DeleteButtonActionListener());
        panel.add (searchButton);
        panel.add (manufacturerTextField);
        panel.add (modelNameTextField);
        panel.add (loReleaseYearTextField);
        panel.add (hiReleaseYearTextField);
        panel.add (loPriceTextField);
        panel.add (hiPriceTextField);
        panel.add (addButton);
        panel.add (deleteButton);

        frame.getContentPane ().add (panel, BorderLayout.NORTH);

        frame.setSize (1280, 720);
        frame.setVisible (true);
    }


    private void showAddAdDialog(){
        JFrame dialogFrame = new JFrame ();

        JPanel dialogPanel = new JPanel ();
        dialogPanel.setLayout (new BoxLayout (dialogPanel, BoxLayout.Y_AXIS));

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

        dialogPanel.add (namePanel);
        dialogPanel.add (surnamePanel);
        dialogPanel.add (phonePanel);
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
        String name = nameTF.getText ();
        String surname = surnameTF.getText ();
        String phone = phoneTF.getText ();
        String vin = vinCodeTF.getText ();
        String manufacturer = manufacturerTF.getText ();
        String modelName = modelTF.getText ();
        String releaseYear = releaseYearTF.getText ();
        String information = informationTF.getText ();
        String price = priceTF.getText ();
        if (name.isEmpty () || surname.isEmpty () || phone.isEmpty () || vin.isEmpty () || manufacturer.isEmpty () || modelName.isEmpty ()
                || releaseYear.isEmpty () || information.isEmpty () || price.isEmpty ()){
            return;
        }
        else{
            boolean wasAdded = UsedCarsTradeImpl.preparedStatementAdd (name,surname,Integer.parseInt (phone),vin,manufacturer,modelName,Integer.parseInt (releaseYear),
                    information, Double.parseDouble (price));
            System.out.println(wasAdded);
            if (wasAdded){
                DefaultTableModel model = (DefaultTableModel) tableAds.getModel ();
                model.addRow (new Object[] {vin, modelName, price, phone});
            }
        }
    }

    private void removeSelectedAds(){
        DefaultTableModel model = (DefaultTableModel) tableAds.getModel();
        int[] selectedRows = tableAds.getSelectedRows();
        for (int i = selectedRows.length - 1; i >= 0; i--) {
            System.out.println(model.getValueAt (selectedRows[i],0).toString ());
            UsedCarsTradeImpl.preparedStatementUpdate (model.getValueAt (selectedRows[i],0).toString ());
            model.removeRow(selectedRows[i]);
        }
    }

    private void searchWithParameters(){
        String manufacturer = manufacturerTextField.getText ();
        String modelName = modelNameTextField.getText ();
        String fromYear = loReleaseYearTextField.getText ();
        String toYear = hiReleaseYearTextField.getText ();
        String fromPrice = loPriceTextField.getText ();
        String toPrice = hiPriceTextField.getText ();

        Object[][] data = UsedCarsTradeImpl.preparedStatementQueryWithParameters (manufacturer, modelName, fromYear, toYear, fromPrice, toPrice);
        DefaultTableModel model = (DefaultTableModel) tableAds.getModel();
        int rowCount = model.getRowCount ();
        for (int i = rowCount - 1; i >= 0; i--){
            model.removeRow (i);
        }
        for (int i = 0; i < data.length; i++){
            model.insertRow (i, data[i]);
        }
    }

    public static void main(String[] args) {
        new UsedCarsTradeClient ();
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
}
