package a01164474.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import a01164474.data.Customer;

/**
 * Project: Assignment 2
 * @author Nathan Souza, A01164474
 */

@SuppressWarnings("serial")
public class CustomerDialog extends JDialog {
	private static final Logger lOG = LogManager.getLogger();
	public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_ID;
	private JTextField textField_FirstName;
	private JTextField textField_LastName;
	private JTextField textField_Street;
	private JTextField textField_City;
	private JTextField textField_PostalCode;
	private JTextField textField_Phone;
	private JTextField textField_Email;
	private JTextField textField_JoinedDate;	
	private Customer customer;

	/**
	 * Create the dialog.
	 */
	public CustomerDialog(Customer customer) {
		this.customer = customer;
		buildMenu();
		setCustomer();
	}
	
	private void buildMenu() {
		lOG.debug("Customer dialog loaded");
		setSize(500, 350);
		setLocationRelativeTo(null);
		setTitle("Customer");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.inactiveCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));		
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][15.00][][302.00,grow][grow]", "[][][][][][][][][][][]"));
		
		{
			JLabel lblNewLabel = new JLabel("ID");
			contentPanel.add(lblNewLabel, "cell 2 2,alignx trailing");
		}
		{
			textField_ID = new JTextField();
			textField_ID.setBackground(SystemColor.activeCaptionBorder);
			contentPanel.add(textField_ID, "cell 3 2,growx");
			textField_ID.setColumns(10);
			textField_ID.setEditable(false);
		}
		{
			JLabel lblNewLabel = new JLabel("First Name");
			contentPanel.add(lblNewLabel, "cell 2 3,alignx trailing");
		}
		{
			textField_FirstName = new JTextField();
			textField_FirstName.setColumns(10);
			contentPanel.add(textField_FirstName, "cell 3 3,growx");
			textField_FirstName.setEditable(true);
		}
		{
			JLabel lblNewLabel = new JLabel("Last Name");
			contentPanel.add(lblNewLabel, "cell 2 4,alignx trailing");
		}
		{
			textField_LastName = new JTextField();
			textField_LastName.setColumns(10);
			contentPanel.add(textField_LastName, "cell 3 4,growx");
			textField_LastName.setEditable(true);
		}
		{
			JLabel lblNewLabel = new JLabel("Street");
			contentPanel.add(lblNewLabel, "cell 2 5,alignx trailing");
		}
		{
			textField_Street = new JTextField();
			textField_Street.setColumns(10);
			contentPanel.add(textField_Street, "cell 3 5,growx");
			textField_Street.setEditable(true);
		}
		{
			JLabel lblNewLabel = new JLabel("City");
			contentPanel.add(lblNewLabel, "cell 2 6,alignx trailing");
		}
		{
			textField_City = new JTextField();
			textField_City.setColumns(10);
			contentPanel.add(textField_City, "cell 3 6,growx");
			textField_City.setEditable(true);
		}
		{
			JLabel lblNewLabel = new JLabel("Postal Code");
			contentPanel.add(lblNewLabel, "cell 2 7,alignx trailing");
		}
		{
			textField_PostalCode = new JTextField();
			textField_PostalCode.setColumns(10);
			contentPanel.add(textField_PostalCode, "cell 3 7,growx");
			textField_PostalCode.setEditable(true);
		}
		{
			JLabel lblNewLabel = new JLabel("Phone");
			contentPanel.add(lblNewLabel, "cell 2 8,alignx trailing");
		}
		{
			textField_Phone = new JTextField();
			textField_Phone.setColumns(10);
			contentPanel.add(textField_Phone, "cell 3 8,growx");
			textField_Phone.setEditable(true);
		}
		{
			JLabel lblNewLabel = new JLabel("Email");
			contentPanel.add(lblNewLabel, "cell 2 9,alignx trailing");
		}
		{
			textField_Email = new JTextField();
			textField_Email.setColumns(10);
			contentPanel.add(textField_Email, "cell 3 9,growx");
			textField_Email.setEditable(true);
		}
		{
			JLabel lblNewLabel = new JLabel("Joined Date");
			contentPanel.add(lblNewLabel, "cell 2 10,alignx trailing");
		}
		{
			textField_JoinedDate = new JTextField();
			textField_JoinedDate.setColumns(10);
			contentPanel.add(textField_JoinedDate, "cell 3 10,growx");
			textField_JoinedDate.setEditable(true);
		}
		
		{
			LocalDate date = customer.getJoinedDate();
			
			textField_ID.setText(String.valueOf(customer.getCustomerID()));
			textField_FirstName.setText(customer.getFirstName());
			textField_LastName.setText(customer.getLastName());
			textField_Street.setText(customer.getStreet());
			textField_City.setText(customer.getCity());
			textField_PostalCode.setText(customer.getPostalCode().replaceAll("", ""));
			textField_Phone.setText(customer.getPhone().replaceAll("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3"));
			textField_Email.setText(customer.getEmailAddress());
			textField_JoinedDate.setText(DATE_FORMAT.format(date));
		}		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton BtnOK = new JButton("OK");
				BtnOK.setActionCommand("OK");
				buttonPane.add(BtnOK);
				getRootPane().setDefaultButton(BtnOK);
				BtnOK.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						updateCustomer();
						lOG.debug("Customer " + customer.getCustomerID() + " was updated.");
						CustomerDialog.this.dispose();						
					}
				});
			}
			{
				JButton BtnCancel = new JButton("Cancel");
				BtnCancel.setActionCommand("Cancel");
				buttonPane.add(BtnCancel);
				BtnCancel.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						CustomerDialog.this.dispose();
					}
				});
			}
		}
		//setVisible(true);
	}
	
	private void setCustomer() {
		lOG.debug("Set Customer");
		
		textField_ID.setText(String.valueOf(customer.getCustomerID()));
		textField_FirstName.setText(customer.getFirstName());
		textField_LastName.setText(customer.getLastName());
		textField_Street.setText(customer.getStreet());
		textField_City.setText(customer.getCity());
		textField_PostalCode.setText(customer.getPostalCode().replaceAll("", ""));
		textField_Phone.setText(customer.getPhone().replaceAll("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3"));
		textField_Email.setText(customer.getEmailAddress());
		textField_JoinedDate.setText(customer.getJoinedDate().toString());
	}

	private void updateCustomer() {		
		lOG.debug("Updating Customer");
		
		customer.setCustomerID(Integer.parseInt(textField_ID.getText()));
		customer.setFirstName(textField_FirstName.getText());
		customer.setLastName(textField_LastName.getText());
		customer.setStreet(textField_Street.getText());
		customer.setCity(textField_City.getText());
		customer.setPostalCode(textField_PostalCode.getText());
		customer.setPhone(textField_Phone.getText());
		customer.setEmailAddress(textField_Email.getText());
		customer.setJoinedDate(LocalDate.parse(textField_JoinedDate.getText()));
	}

	public Customer getCustomer() {
		return customer;
	}

}
