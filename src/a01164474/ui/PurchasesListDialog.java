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
import javax.swing.border.EmptyBorder;
import a01164474.data.Buyer;

/**
 * Project: Assignment 2
 * @author Nathan Souza, A01164474
 */

@SuppressWarnings("serial")
public class PurchasesListDialog extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	
	private JList<Buyer> purchaseList;
	private DefaultListModel<Buyer> listModel;	

	/**
	 * Create the dialog.
	 */
	public PurchasesListDialog(List<Buyer> list) {
		listModel = new DefaultListModel<Buyer>();
		for(Buyer p: list) {
			listModel.addElement(p);
		}		
		purchaseList = new JList<Buyer>(listModel);
		
		buildMenu();
	}
	
	private void buildMenu() {
		setSize(550, 500);
		setLocationRelativeTo(null);
		setTitle("Purchase List");
		
		getContentPane().setLayout(new BorderLayout());
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
						PurchasesListDialog.this.dispose();
					}
				});
				buttonPane.add(closeButton);
			}
		}
		
        add(new JScrollPane(purchaseList));
        purchaseList.setCellRenderer(new PurchaseRenderer());
	}
	
	
	class PurchaseRenderer extends JLabel implements ListCellRenderer<Buyer> {

		@Override
		public Component getListCellRendererComponent(JList<? extends Buyer> list, Buyer purchase, int index,
				boolean isSelected, boolean cellHasFocus) {
			
			setText(String.format("%s %s   |   %s   |   $%,.2f   |   %s", purchase.getFirstName(), purchase.getLastName(), purchase.getTitle(), purchase.getPrice(), purchase.getCustomerID()));
			
			if(isSelected) {
				setBackground(list.getSelectionBackground());
			}else {
				setBackground(list.getBackground());
			}
			return this;
		}
		
	}
}
