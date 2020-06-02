package a01164474.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import a01164474.ui.UIController;
import a01164474.data.Customer;

/**
 * Project: Assignment 2
 * @author Nathan Souza, A01164474
 */

@SuppressWarnings("serial")
public class CustomerListDialog extends JDialog {
	
	private static final Logger lOG = LogManager.getLogger();
	private final JPanel contentPanel = new JPanel();
	
	private JList<Customer> customerList;
	private DefaultListModel<Customer> listModel;	

	/**
	 * Create the dialog.
	 */
	public CustomerListDialog(List<Customer> customer) {
		listModel = new DefaultListModel<Customer>();
		for(Customer c: customer) {
			listModel.addElement(c);
		}
		
		customerList = new JList<Customer>(listModel);
		
		buildMenu();
		addEventHandlers();
	}
	
	private void buildMenu() {
		setSize(550, 500);
		setLocationRelativeTo(null);
		setTitle("Customer List");
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton closeButton = new JButton("Close");
				closeButton.setActionCommand("Cancel");
				closeButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						CustomerListDialog.this.dispose();
					}

				});
				buttonPane.add(closeButton);
			}
		}
		
		add(new JScrollPane(customerList));
		customerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		customerList.setCellRenderer(new CustomerRenderer());
	}
	
	class CustomerRenderer extends JLabel implements ListCellRenderer<Customer> {

		@Override
		public Component getListCellRendererComponent(JList<? extends Customer> list, Customer customer, int index,
				boolean isSelected, boolean cellHasFocus) {
			
			setText(String.format("%s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s", 
					customer.getCustomerID(), 
					customer.getFirstName(), 
					customer.getLastName(),
					customer.getStreet(),
					customer.getCity(),
					customer.getPostalCode(),
					customer.getPhone(),
					customer.getEmailAddress(),
					customer.getJoinedDate()
					));
			
			if(isSelected) {
				setBackground(list.getSelectionBackground());
			}else {
				setBackground(list.getBackground());
			}
			return this;
		}
		
	}
	
	private void addEventHandlers() {
		lOG.debug("ListOfCustomer received EventHandlers");
		customerList.getSelectionModel().addListSelectionListener(new UIController.CustomerListSelection(customerList));
	}
}
